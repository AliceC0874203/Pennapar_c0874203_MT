package com.example.pennapar_c0874203_mt;

import java.util.ArrayList;
import java.util.List;

public class UserCar {
    private String model;
    private int driverAge;
    private int days;
    private boolean isGPS;
    private boolean isChildSeat;
    private boolean isUnlimit;
    private double amount;
    private double totalPayment;

    public UserCar(String model, int driverAge, int days, boolean isGPS, boolean isChildSeat, boolean isUnlimit, double amount, double totalPayment) {
        this.model = model;
        this.driverAge = driverAge;
        this.days = days;
        this.isGPS = isGPS;
        this.isChildSeat = isChildSeat;
        this.isUnlimit = isUnlimit;
        this.amount = amount;
        this.totalPayment = totalPayment;
    }

    @Override
    public String toString() {
        return "UserCar{" +
                "model='" + model + '\'' +
                ", driverAge=" + driverAge +
                ", days=" + days +
                ", isGPS=" + isGPS +
                ", isChildSeat=" + isChildSeat +
                ", isUnlimit=" + isUnlimit +
                ", amount=" + amount +
                ", totalPayment=" + totalPayment +
                '}';
    }

    public static List<UserCar> selectedCarList = new ArrayList<>();
}
