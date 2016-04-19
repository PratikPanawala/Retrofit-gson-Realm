package panawala.pratik.retrofit_gson_realm.Interface;


import java.util.List;

import panawala.pratik.retrofit_gson_realm.Model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {
    @GET("path/subpath")
    Call<List<User>> loadUser();
}



