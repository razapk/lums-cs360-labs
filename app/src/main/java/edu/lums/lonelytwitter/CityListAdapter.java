package edu.lums.lonelytwitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CityListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> cityList;

    public CityListAdapter(@NonNull Context context, @NonNull List<String> cityList) {
        super(context, R.layout.list_item_city, cityList);
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_city, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.cityNameTextView = convertView.findViewById(R.id.city_name_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String cityName = cityList.get(position);
        viewHolder.cityNameTextView.setText(cityName);

        return convertView;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return cityList.get(position);
    }

    public void addCity(String cityName) {
        cityList.add(cityName);
        notifyDataSetChanged();
    }

    public void removeCity(int position) {
        if (position >= 0 && position < cityList.size()) {
            cityList.remove(position);
            notifyDataSetChanged();
        }
    }

    private static class ViewHolder {
        TextView cityNameTextView;
    }
}
