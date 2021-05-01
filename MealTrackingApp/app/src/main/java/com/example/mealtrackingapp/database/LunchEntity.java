package com.example.mealtrackingapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LunchEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;
    public String foodName;
    public String foodCal;
}