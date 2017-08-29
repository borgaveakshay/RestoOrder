package com.restoorders.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * Created by akshayborgave on 28/08/17.
 */

@Entity
public class HotelEntity {

    @PrimaryKey
    int hotelId;

    @ColumnInfo(name = "hotel_name")
    String hotelName;

    @ColumnInfo(name = "hotel_description")
    String hotelDescription;

    @ColumnInfo(name = "hotel_address")
    String hotelAdress;

    @ColumnInfo(name = "hotel_logo")
    Bitmap hotelLogo;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelAdress() {
        return hotelAdress;
    }

    public void setHotelAdress(String hotelAdress) {
        this.hotelAdress = hotelAdress;
    }

    public Bitmap getHotelLogo() {
        return hotelLogo;
    }

    public void setHotelLogo(Bitmap hotelLogo) {
        this.hotelLogo = hotelLogo;
    }
}
