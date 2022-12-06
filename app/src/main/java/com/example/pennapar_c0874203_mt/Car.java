package com.example.pennapar_c0874203_mt;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String model;
    private double dailyRentPrice;

    public Car(String model, double dailyRentPrice) {
        this.model = model;
        this.dailyRentPrice = dailyRentPrice;
    }

    public String getModel() {
        return model;
    }

    public double getDailyRentPrice() {
        return dailyRentPrice;
    }

    @Override
    public String toString() {
        return model;
    }}
