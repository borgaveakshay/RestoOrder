package com.restoorders.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * Created by akshayborgave on 28/08/17.
 */

@Entity
public class CuisineEntity {

    @PrimaryKey
    int cuisineId;

    @ColumnInfo(name = "cuisine_name")
    String cuisineName;

    @ColumnInfo(name = "cuisine_description")
    String cuisineDescription;


    @ColumnInfo(name = "cuisine_logo")
    Bitmap cuisineLogo;

}
