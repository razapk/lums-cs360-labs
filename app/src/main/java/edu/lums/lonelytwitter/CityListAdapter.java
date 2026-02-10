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

public class CityListAdapter extends ArrayAdapter<City> {
    private final Context context;
    private final List<City> cityList;

    public CityListAdapter(@NonNull Context context, @NonNull List<City> cityList) {
        super(context, R.layout.list_item_city, cityList);
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_city, parent, false);
        } else {
        }

        TextView titleTextView = convertView.findViewById(R.id.city_name_text);
        TextView provinceTextView = convertView.findViewById(R.id.city_province_text);
        City city = cityList.get(position);
        titleTextView.setText(city.getName());
        provinceTextView.setText(city.getProvince());

        return convertView;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Nullable
    @Override
    public City getItem(int position) {
        return cityList.get(position);
    }

    public void addCity(City city) {
        cityList.add(city);
        notifyDataSetChanged();
    }

    public void updateCity(int position, City city) {
        if (position >= 0 && position < cityList.size()) {
            cityList.set(position, city);
            notifyDataSetChanged();
        }
    }

    public void removeCity(int position) {
        if (position >= 0 && position < cityList.size()) {
            cityList.remove(position);
            notifyDataSetChanged();
        }
    }
}
