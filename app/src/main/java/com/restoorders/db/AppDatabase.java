package com.restoorders.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.restoorders.db.entities.FoodCategory;

/**
 * Created by akshayborgave on 15/08/17.
 */

@Database(entities = {FoodCategory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RestoDuo RestoDuo();
}
