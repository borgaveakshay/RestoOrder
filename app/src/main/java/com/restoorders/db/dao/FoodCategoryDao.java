package com.restoorders.db.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.restoorders.db.entities.FoodCategoryEntity;

import java.util.List;

/**
 * Created by akshayborgave on 15/08/17.
 */

public interface FoodCategoryDao {

    @Query("SELECT * FROM FoodCategoryEntity")
    List<FoodCategoryEntity> getAll();

    @Query("SELECT * FROM FoodCategoryEntity WHERE uid IN (:foodIds)")
    List<FoodCategoryEntity> loadAllByIds(int[] foodIds);

    @Query("SELECT * FROM FoodCategoryEntity WHERE Food_Category LIKE :food "
            + "LIMIT 1")
    FoodCategoryEntity findByName(String first, String last);

    @Insert
    void insertAll(FoodCategoryEntity... foodCategories);

    @Insert
    void insertCategory(FoodCategoryEntity foodCategoryEntity);

    @Delete
    void delete(FoodCategoryEntity foodCategory);
}
