package com.restoorders.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by akshayborgave on 2
 * 8/08/17.
 */

@Entity(foreignKeys = @ForeignKey(entity = HotelEntity.class,
        parentColumns = "hotelId",
        childColumns = "hotel_id"))
public class BillEntity {

    @PrimaryKey
    int billId;

    @ColumnInfo(name = "hotel_id")
    int hotelId;

    
}
