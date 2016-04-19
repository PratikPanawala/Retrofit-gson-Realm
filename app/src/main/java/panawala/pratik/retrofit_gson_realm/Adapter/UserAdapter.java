package panawala.pratik.retrofit_gson_realm.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import panawala.pratik.retrofit_gson_realm.Model.User;
import panawala.pratik.retrofit_gson_realm.R;


/**
 * Created by Praatik on 09-04-2016.
 */
public class UserAdapter extends BaseAdapter {

    Context context;
    List<User> businesses;

    public UserAdapter(Context context, List<User> businesses) {
        this.context = context;
        this.businesses = businesses;

    }

    @Override
    public int getCount() {
        return businesses.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.each_raw, null);

        TextView businessName = (TextView) convertView.findViewById(R.id.name);
        businessName.setText(businesses.get(position).getDisplayName());
        return convertView;
    }
}
