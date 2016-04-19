package panawala.pratik.retrofit_gson_realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import panawala.pratik.retrofit_gson_realm.Adapter.UserAdapter;
import panawala.pratik.retrofit_gson_realm.Interface.UserApi;
import panawala.pratik.retrofit_gson_realm.Model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView userList;
    ProgressBar progressBar;
    List<User> mUserList;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList = (ListView) findViewById(R.id.userList);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mUserList = new ArrayList<>();
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
//        Realm.deleteRealm(realmConfig);
//         Set the default Realm configuration at the beginning.
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getDefaultInstance();

        RealmResults<User> businesses = realm.where(User.class).findAll();
        if (businesses != null && !businesses.isEmpty()) {
            Log.d("REalm", "REalmCall");
            UserAdapter userAdapter = new UserAdapter(getBaseContext(), businesses);
            userList.setAdapter(userAdapter);
            progressBar.setVisibility(View.GONE);
        } else {
            callUser();
        }
    }

    private void callUser() {

//Make Custom gson for user one object(pojo) class for Realm and Retrofit

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();

//This is use for create retrofit object and call that url in baseurl and convert in GsonConverterFactory so directly convert that
        //response according to pojo
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://Your Domain/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        UserApi businessApi = retrofit.create(UserApi.class);

        Call<List<User>> call = businessApi.loadUser();
        //asynchronous call
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                UserAdapter userAdapter = new UserAdapter(getBaseContext(), response.body());
                //that is open realm connection and perform your query and last commit that transaction
                realm.beginTransaction();
                realm.copyToRealm(response.body());
                realm.commitTransaction();

                userList.setAdapter(userAdapter);
//... add or update objects here ...

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //must must close realm object
        realm.close();
    }
}