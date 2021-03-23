package com.example.mealtrackingapp.mealspoputil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mealtrackingapp.R;

import java.util.List;

public class MealsListAdapter extends BaseAdapter {

    private Context context;
    private List<String> itemNames;
    private List<String> itemCalories;

    public MealsListAdapter(Context context, List<String> itemNames, List<String> itemCalories) {
        this.context = context;
        this.itemNames = itemNames;
        this.itemCalories = itemCalories;
    }

    @Override
    public int getCount() {
        return itemNames.size();
    }

    @Override
    public String[] getItem(int i) {
        String item[] = {itemNames.get(i), itemCalories.get(i)};
        return item;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup container) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.meals_item, container, false);
        }

        TextView mealNameView = convertView.findViewById(R.id.meals_item_name);
        TextView mealValueView = convertView.findViewById(R.id.meals_item_cal_value);

        mealNameView.setText(getItem(i)[0]);
        mealValueView.setText(getItem(i)[1]);

        return convertView;
    }
}
