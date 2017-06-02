package global.kz.test.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import global.kz.test.R;
import global.kz.test.data.network.model.Weather;

/**
 * Created by root on 6/2/17.
 */

public class ListViewAdapter extends ArrayAdapter<Weather> {
    public ListViewAdapter(Context context, ArrayList<Weather> cities) {
        super(context, 0, cities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        Weather weather = getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.choose_city, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvLvCity.setText(weather.getName());
        holder.tvLvTemp.setText(String.valueOf(weather.getMain().getTemp()));
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.tv_lv_city)
        TextView tvLvCity;
        @BindView(R.id.tv_lv_temp)
        TextView tvLvTemp;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
