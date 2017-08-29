package com.restoorders.db.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.restoorders.db.entities.BillEntity;
import com.restoorders.db.entities.FoodItemEntity;

import java.util.List;

/**
 * Created by akshayborgave on 29/08/17.
 */

public interface BillEntityDao {

    @Insert
    void insertBillEntry(BillEntity... billEntities);

    @Update
    void updateBill(BillEntity billEntity);

    @Query("SELECT * FROM BillEntity")
    List<BillEntity> getAllBills();

    @Delete
    void deleteBillItems(BillEntity... billEntities);
}
