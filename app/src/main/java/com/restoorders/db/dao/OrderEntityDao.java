package com.restoorders.db.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.restoorders.db.entities.FoodItemEntity;
import com.restoorders.db.entities.HotelEntity;
import com.restoorders.db.entities.OrderEntity;

import java.util.List;

/**
 * Created by akshayborgave on 29/08/17.
 */

public interface OrderEntityDao {

    @Insert
    void insertOrder(OrderEntity... orderEntities);

    @Update
    void updateOrder(OrderEntity orderEntity);

    @Query("SELECT * FROM OrderEntity")
    List<OrderEntity> getAll();

    @Delete
    void deleteOrderDetails(OrderEntity... orderEntities);
}
