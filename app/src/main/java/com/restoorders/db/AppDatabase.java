package com.restoorders.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.restoorders.db.dao.FoodCategoryDao;
import com.restoorders.db.entities.FoodCategoryEntity;

/**
 * Created by akshayborgave on 15/08/17.
 */

@Database(entities = {FoodCategoryEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FoodCategoryDao foodCategoryDao();
}
