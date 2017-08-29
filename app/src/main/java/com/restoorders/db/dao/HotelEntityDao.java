package com.restoorders.db.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.restoorders.db.entities.FoodItemEntity;
import com.restoorders.db.entities.HotelEntity;

import java.util.List;

/**
 * Created by akshayborgave on 29/08/17.
 */

public interface HotelEntityDao {

    @Insert
    void insertHotelDetails(HotelEntity... hotelEntities);

    @Update
    void updateHotelDetails(HotelEntity hotelEntity);

    @Query("SELECT * FROM HotelEntity")
    List<HotelEntity> getAll();

    @Delete
    void deleteHotelDetails(HotelEntity... hotelEntities);
}
