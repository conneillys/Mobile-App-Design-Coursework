package com.example.mealtrackingapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class SnacksEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public Calendar date;
    public String foodName;
    public String foodCal;
}