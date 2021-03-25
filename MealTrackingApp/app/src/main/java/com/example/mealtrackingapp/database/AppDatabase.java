package com.example.mealtrackingapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={WeightEntity.class, CaloriesEntity.class, BreakfastEntity.class,
        LunchEntity.class, DinnerEntity.class, SnacksEntity.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WeightDao weightDao();
    public abstract CaloriesDao caloriesDao();
    public abstract BreakfastDao breakfastDao();
    public abstract LunchDao lunchDao();
    public abstract DinnerDao dinnerDao();
    public abstract SnacksDao snacksDao();
}
