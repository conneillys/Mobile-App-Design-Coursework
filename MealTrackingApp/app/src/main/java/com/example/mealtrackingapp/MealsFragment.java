package com.example.mealtrackingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.mealtrackingapp.mealspoputil.MealsListAdapter;
import com.example.mealtrackingapp.mealspoputil.MealsListDataPump;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealsFragment extends Fragment {

    ListAdapter breakfastAdapter;
    ListAdapter lunchAdapter;
    ListAdapter dinnerAdapter;
    ListAdapter snacksAdapter;

    ListView breakfastListView;
    ListView lunchListView;
    ListView dinnerListView;
    ListView snacksListView;

    ArrayList<String> breakfastNames;
    ArrayList<String> breakfastCals;
    ArrayList<String> lunchNames;
    ArrayList<String> lunchCals;
    ArrayList<String> dinnerNames;
    ArrayList<String> dinnerCals;
    ArrayList<String> snackNames;
    ArrayList<String> snackCals;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MealsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MealsFragment newInstance(String param1, String param2) {
        MealsFragment fragment = new MealsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        breakfastListView = (ListView) getActivity().findViewById(R.id.breakfast_list);
        lunchListView = (ListView) getActivity().findViewById(R.id.lunch_list);
        dinnerListView = (ListView) getActivity().findViewById(R.id.dinner_list);
        snacksListView = (ListView) getActivity().findViewById(R.id.snacks_list);

        breakfastNames = MealsListDataPump.getBreakfastNames();
        breakfastCals = MealsListDataPump.getBreakfastCalories();
        lunchNames = MealsListDataPump.getLunchNames();
        lunchCals = MealsListDataPump.getLunchCalories();
        dinnerNames = MealsListDataPump.getDinnerNames();
        dinnerCals = MealsListDataPump.getDinnerCalories();
        snackNames = MealsListDataPump.getSnackNames();
        snackCals = MealsListDataPump.getSnackCalories();

        breakfastAdapter = new MealsListAdapter(this.getContext(), breakfastNames, breakfastCals);
        lunchAdapter = new MealsListAdapter(this.getContext(), lunchNames, lunchCals);
        dinnerAdapter = new MealsListAdapter(this.getContext(), dinnerNames, dinnerCals);
        snacksAdapter = new MealsListAdapter(this.getContext(), snackNames, snackCals);

        breakfastListView.setAdapter(breakfastAdapter);
        lunchListView.setAdapter(lunchAdapter);
        dinnerListView.setAdapter(dinnerAdapter);
        snacksListView.setAdapter(snacksAdapter);

    }
}