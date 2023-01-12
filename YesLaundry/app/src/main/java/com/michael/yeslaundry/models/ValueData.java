package com.michael.yeslaundry.models;

import java.util.List;

public class ValueData<T> {
    private int success;
    private String message;
    private List<Transaksi> data;

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Transaksi> getData() {
        return data;
    }
}
