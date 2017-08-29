package com.restoorders.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.restoorders.db.dao.BillEntityDao;
import com.restoorders.db.dao.CuisineEntityDao;
import com.restoorders.db.dao.FoodCategoryDao;
import com.restoorders.db.dao.FoodItemDao;
import com.restoorders.db.dao.HotelEntityDao;
import com.restoorders.db.dao.OrderEntityDao;
import com.restoorders.db.entities.BillEntity;
import com.restoorders.db.entities.CuisineEntity;
import com.restoorders.db.entities.FoodCategoryEntity;
import com.restoorders.db.entities.FoodItemEntity;
import com.restoorders.db.entities.HotelEntity;
import com.restoorders.db.entities.OrderEntity;

/**
 * Created by akshayborgave on 15/08/17.
 */

@Database(entities = {FoodCategoryEntity.class,
        HotelEntity.class,
        OrderEntity.class,
        FoodItemEntity.class,
        CuisineEntity.class,
        BillEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FoodCategoryDao foodCategoryDao();

    public abstract BillEntityDao billEntityDao();

    public abstract CuisineEntityDao cuisineEntityDao();

    public abstract FoodItemDao foodItemDao();

    public abstract HotelEntityDao hotelEntityDao();

    public abstract OrderEntityDao orderEntityDao();
}
