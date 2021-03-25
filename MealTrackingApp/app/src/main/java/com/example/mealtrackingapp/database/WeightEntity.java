package com.example.mealtrackingapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class WeightEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public Calendar date;
    public int weight;
}
