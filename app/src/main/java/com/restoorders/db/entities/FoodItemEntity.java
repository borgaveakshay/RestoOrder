package com.restoorders.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.graphics.Bitmap;

/**
 * Created by akshayborgave on 19/08/17.
 */
@Entity(foreignKeys = @ForeignKey(entity = CuisineEntity.class, parentColumns = "cuisineId", childColumns = "cuisine_Id") )
public class FoodItemEntity {

    @PrimaryKey
    int itemId;

    @ColumnInfo(name = "Food_Name")
    String foodName;

    @ColumnInfo(name = "food_description")
    String foodDescription;


    @ColumnInfo(name = "food_logo")
    Bitmap foodLogo;

    @ColumnInfo(name = "food_price")
    int foodPrice;

    @ColumnInfo(name = "cuisine_Id")
    int cuisineId;

    @Relation(entity = FoodCategoryEntity.class, parentColumn = "foodCategoryId", entityColumn = "foodCategory_Id")
    int foodCategoryId;


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public Bitmap getFoodLogo() {
        return foodLogo;
    }

    public void setFoodLogo(Bitmap foodLogo) {
        this.foodLogo = foodLogo;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    public int getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(int foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
    }
}
