package com.restoorders.db;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.restoorders.db.entities.FoodCategory;

import java.util.List;

/**
 * Created by akshayborgave on 15/08/17.
 */

public interface RestoDuo {

    @Query("SELECT * FROM food_category")
    List<FoodCategory> getAll();

    @Query("SELECT * FROM food_category WHERE uid IN (:userIds)")
    List<FoodCategory> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM food_category WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    FoodCategory findByName(String first, String last);

    @Insert
    void insertAll(FoodCategory... foodCategories);

    @Delete
    void delete(FoodCategory foodCategory);
}
