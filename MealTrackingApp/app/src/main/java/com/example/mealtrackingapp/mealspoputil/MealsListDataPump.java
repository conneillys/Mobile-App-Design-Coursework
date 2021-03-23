package com.example.mealtrackingapp.mealspoputil;

import java.util.ArrayList;

public class MealsListDataPump {

    public static ArrayList<String> getBreakfastNames() {
        ArrayList<String> breakfastNames = new ArrayList<String>();
        breakfastNames.add("Bacon");
        breakfastNames.add("Eggs");
        breakfastNames.add("Toast");
        return breakfastNames;
    }

    public static ArrayList<String> getBreakfastCalories() {
        ArrayList<String> breakfastCals = new ArrayList<String>();
        breakfastCals.add("200");
        breakfastCals.add("300");
        breakfastCals.add("100");
        return breakfastCals;
    }

    public static ArrayList<String> getLunchNames() {
        ArrayList<String> lunchNames = new ArrayList<String>();
        lunchNames.add("Ham and Cheese Sandwich");
        lunchNames.add("Chips");
        return lunchNames;
    }

    public static ArrayList<String> getLunchCalories() {
        ArrayList<String> lunchCals = new ArrayList<String>();
        lunchCals.add("750");
        lunchCals.add("300");
        return lunchCals;
    }

    public static ArrayList<String> getDinnerNames() {
        ArrayList<String> dinnerNames = new ArrayList<String>();
        dinnerNames.add("Soup");
        return dinnerNames;
    }

    public static ArrayList<String> getDinnerCalories() {
        ArrayList<String> dinnerCals = new ArrayList<String>();
        dinnerCals.add("250");
        return dinnerCals;
    }

    public static ArrayList<String> getSnackNames() {
        ArrayList<String> snackNames = new ArrayList<String>();
        snackNames.add("Cookies");
        return snackNames;
    }

    public static ArrayList<String> getSnackCalories() {
        ArrayList<String> snackCals = new ArrayList<String>();
        snackCals.add("500");
        return snackCals;
    }
}
