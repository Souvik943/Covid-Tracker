package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<Countries> {

    private Context context;
    private List<Countries> countriesList;
    private List<Countries> countriesListFiltered;


    public CountryAdapter( Context context, List<Countries> countriesList) {
        super(context, R.layout.list_item,countriesList);

        this.context = context;
        this.countriesList = countriesList;
        this.countriesListFiltered = countriesList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,true);
        TextView tvCountry = view.findViewById(R.id.tvCountry);
        ImageView flags = view.findViewById(R.id.imgFlag);

        tvCountry.setText(countriesListFiltered.get(position).getCountry());
        Glide.with(context).load(countriesListFiltered.get(position).getFlag()).into(flags);
        return view;
    }

    @NonNull
    @Override
    public int getCount() {
        return countriesListFiltered.size();
    }

    @Nullable
    @Override
    public Countries getItem(int position) {
        return countriesListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
         Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countriesList.size();
                    filterResults.values = countriesList;
                }
                else {
                    List<Countries> resultModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(Countries itemsCountries :countriesList) {
                        if (itemsCountries.getCountry().toLowerCase().contains(searchStr)) {
                            resultModel.add(itemsCountries);
                        }

                    }
                    filterResults.count = resultModel.size();
                    filterResults.values = resultModel;
                }


                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countriesListFiltered = (List<Countries>) results.values;
                Details.countriesList = (List<Countries>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
