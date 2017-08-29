package com.restoorders.db.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.restoorders.db.entities.FoodItemEntity;

import java.util.List;

/**
 * Created by akshayborgave on 29/08/17.
 */

public interface FoodItemDao {

    @Insert
    void insertItem(FoodItemEntity... foodItemEntities);

    @Update
    void updateItem(FoodItemEntity foodItemEntity);

    @Query("SELECT * FROM FoodItemEntity")
    List<FoodItemEntity> getAll();

    @Delete
    void deleteFoodItems(FoodItemEntity... foodItemEntities);
}
