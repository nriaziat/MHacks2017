package com.example.shashank.pilldispenser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashank on 3/25/2017.
 */

public class UserInfo {

    public static final int TRAY_NUM = 4;

    private List<Pill> l = new ArrayList<Pill>();
    private String name;
    private boolean[] trays = new boolean[TRAY_NUM];



    //Constructors
    public UserInfo(){
        this("User");
    }
    public UserInfo(String name) {
        this.name = name;
    }

    ////////////////
    public void addPill(String name, int output, int quantity, boolean meal, boolean[] day,
                        int[] minutes) {
        Pill p = new Pill();

        p.setName(name);
        p.setOutput(output);
        p.setQuantity(quantity);
        p.setMinutes(minutes);
        p.setDays(day);
        p.setMeal(meal);
        this.l.add(p);
    }

}




