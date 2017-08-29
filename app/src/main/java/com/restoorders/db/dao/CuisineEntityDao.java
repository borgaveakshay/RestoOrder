package com.restoorders.db.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.restoorders.db.entities.CuisineEntity;
import com.restoorders.db.entities.FoodItemEntity;
import com.restoorders.db.entities.OrderEntity;

import java.util.List;

/**
 * Created by akshayborgave on 29/08/17.
 */

public interface CuisineEntityDao {

    @Insert
    void insertCuisine(CuisineEntity... cuisineEntities);

    @Update
    void updateCuisine(CuisineEntity cuisineEntity);

    @Query("SELECT * FROM CuisineEntity")
    List<CuisineEntity> getAll();

    @Delete
    void deleteCuisines(CuisineEntity... cuisineEntities);
}
