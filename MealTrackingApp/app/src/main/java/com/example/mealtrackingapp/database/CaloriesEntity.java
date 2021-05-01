package com.example.mealtrackingapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CaloriesEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;
    public int calories;
}
