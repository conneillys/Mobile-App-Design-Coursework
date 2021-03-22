package com.example.mealtrackingapp.nutritionpoputil;

// Adapted from JournalDev ExpandableListView tutorial:
// https://www.journaldev.com/9942/android-expandablelistview-example-tutorial

import java.util.LinkedHashMap;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mealtrackingapp.R;

public class NutritionListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> nutritionListTitle;
    private LinkedHashMap<String, List<String>> nutritionListDetail;

    public NutritionListAdapter(Context context, List<String> listTitle, LinkedHashMap<String, List<String>> listDetail) {
        this.context = context;
        this.nutritionListTitle = listTitle;
        this.nutritionListDetail = listDetail;
    }

    @Override
    public Object getChild(int listPosition, int nutritionListPosition) {
        return this.nutritionListDetail
                .get(this.nutritionListTitle.get(listPosition))
                .get(nutritionListPosition);
    }

    @Override
    public long getChildId(int listPosition, int nutritionListPosition) {
        return nutritionListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int nutritionListPosition, boolean isLastChild,
                           View convertView, ViewGroup parent) {
        final String nutritionListText = (String) getChild(listPosition, nutritionListPosition);
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.nutrition_item, null);
        }
        TextView nutritionItemTextView =
                (TextView) convertView.findViewById(R.id.nutrition_item_name);
        nutritionItemTextView.setText(nutritionListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.nutritionListDetail.get(this.nutritionListTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.nutritionListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.nutritionListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.nutrition_group, null);
        }
        TextView listTitleTextView =
                (TextView) convertView.findViewById(R.id.nutrition_category_name);
        listTitleTextView.setText(listTitle);

        // Handle hiding arrows for categories that cannot be expanded
        ImageView arrow_view = (ImageView) convertView.findViewById(R.id.nutrition_category_arrow);

        boolean noChildren = false;
        try {
            if (nutritionListDetail.get(this.nutritionListTitle.get(listPosition)).get(0) == null) {
            }
            else {
                if (isExpanded) {
                    arrow_view.setImageResource(R.drawable.arrow_up);
                }
                else {
                    arrow_view.setImageResource(R.drawable.arrow_down);
                }
            }
        }
        catch (Exception e) {
            noChildren = true;
        }
        if (noChildren) {
            arrow_view.setVisibility(View.INVISIBLE);
        }
        else {
            arrow_view.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int nutritionListPosition) {
        return false;
    }
}
