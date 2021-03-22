package com.example.mealtrackingapp.nutritionpoputil;

// Adapted from JournalDev ExpandableListView tutorial:
// https://www.journaldev.com/9942/android-expandablelistview-example-tutorial

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class NutritionListDataPump {

    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> nutritionListDetail = new LinkedHashMap<String, List<String>>();

        List<String> fats = new ArrayList<String>();
        fats.add("Saturated Fat");
        fats.add("Trans Fat");

        List<String> carbs = new ArrayList<String>();
        carbs.add("Dietary Fiber");
        carbs.add("Total Sugars");

        List<String> vitamins = new ArrayList<String>();
        vitamins.add("Biotin");
        vitamins.add("Choline");
        vitamins.add("Folate");
        vitamins.add("Niacin");
        vitamins.add("Pantothenic Acid");
        vitamins.add("Riboflavin");
        vitamins.add("Thiamin");
        vitamins.add("Vitamin A");
        vitamins.add("Vitamin B6");
        vitamins.add("Vitamin B12");
        vitamins.add("Vitamin C");
        vitamins.add("Vitamin D");
        vitamins.add("Vitamin E");
        vitamins.add("Vitamin K");

        List<String> minerals = new ArrayList<String>();
        minerals.add("Calcium");
        minerals.add("Chloride");
        minerals.add("Chromium");
        minerals.add("Copper");
        minerals.add("Iodine");
        minerals.add("Iron");
        minerals.add("Magnesium");
        minerals.add("Manganese");
        minerals.add("Molybdenum");
        minerals.add("Phosphorus");
        minerals.add("Potassium");
        minerals.add("Selenium");
        minerals.add("Sodium");
        minerals.add("Zinc");

        nutritionListDetail.put("CARBOHYDRATES", carbs);
        nutritionListDetail.put("FATS", fats);
        nutritionListDetail.put("PROTEIN", new ArrayList<String>());
        nutritionListDetail.put("CHOLESTEROL", new ArrayList<String>());
        nutritionListDetail.put("VITAMINS", vitamins);
        nutritionListDetail.put("MINERALS", minerals);

        return nutritionListDetail;
    }
}
