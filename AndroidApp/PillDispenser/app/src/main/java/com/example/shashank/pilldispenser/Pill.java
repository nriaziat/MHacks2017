package com.example.shashank.pilldispenser;

/**
 * Created by Shashank on 3/25/2017.
 */



public class Pill {

    public static final int NUM_DAYS_IN_WEEK = 7;
    public static final int TIME_RANGES = 144;

    private String name; //Name of pill
    private int quantity; //How many pills loaded
    private int output; //How many to dispense
    private boolean meal;
    private int trayNum;
    private int[] minutes = new int[TIME_RANGES]; // Times to dispense in 10 min ranges [Ranges 0-143, corresponding to 00:00 to 24:00]
    private boolean[] days = new boolean[NUM_DAYS_IN_WEEK]; // 0 = Sunday, 1 = Monday, 2 = Tuesday, 3 = Wednesday, 4 = Thursday, 5 = Friday, 6 = Saturday


    Pill(){}

    /////////////////Initial Set Functions//////////////////////////////////////////////////////////
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quant) {
        this.quantity = quant;
    }

    public void setOutput(int dispense){
        this.output = dispense;
    }

    public void setMinutes(int[] times) {
        this.minutes = times;
    }

    public void setTrayNum(int num) {this.trayNum = num;}

    public void setMeal(boolean eatWithMeal) {
        this.meal = eatWithMeal;
    }

    public void setDays(boolean[] days) {
        this.days = days;
    }

    /////////////////Update Functions///////////////////////////////////////////////////////////////
    public void updateQuant(int added) {
        this.quantity += added;
    }

    /////////////////Get Functions//////////////////////////////////////////////////////////////////
    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getOutput() {
        return this.output;
    }

    public boolean getMeal() {return this.meal;}

    public int[] getTime() {return this.minutes;}

    public boolean[] getDays() {return this.days;}



    ////////////////Other Functions///////////////////////////////////////////////////////////////
    public void takePill() {this.quantity -= this.output;}
}
