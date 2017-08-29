package com.restoorders.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * Created by akshayborgave on 28/08/17.
 */

@Entity(foreignKeys = @ForeignKey(entity = BillEntity.class,
        parentColumns = "billId",childColumns = "bill_id"))
public class OrderEntity {

    @PrimaryKey
    int orderId;

    @ColumnInfo(name = "order_total_price")
    float orderTotal;

    @ColumnInfo(name = "order_quantity")
    String orderQuantity;

    @ColumnInfo(name = "bill_id")
    int billId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }
}
