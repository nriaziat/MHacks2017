package com.example.shashank.pilldispenser;

import android.support.v7.app.AlertDialog;

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
                        int minutes, int tray) {

        if (tray <= 0 || tray > 4) {
            return;
        }

        for (Pill pills : l) {
            //Checks if pill is already in the tray, adds pills to that
            if (pills.getName() == name && pills.getTrayNum() == tray) {
                pills.addPills(quantity);
                boolean isThere = false;
                for (int i : pills.getTime()) {
                    if (i == minutes) {
                        isThere = true;
                    }
                }
                if (isThere) {
                    pills.setMinutes(minutes);
                }
                return;
            } else if (pills.getName() == name) { //Checks if pill is stored, but in diff tray
                return;
            }
        }

        Pill p = new Pill();
        p.setName(name);
        p.setOutput(output);
        p.setQuantity(quantity);
        p.setMinutes(minutes);
        p.setDays(day);
        p.setMeal(meal);
        p.setTrayNum(tray);
        this.l.add(tray - 1, p);
    }

    public void removePill(int tray) {
        l.remove(tray - 1);
    }

    public List<Pill> getNeededPills(int currentMin) {
        int curr = currentMin + 1;
        List<Pill> list = new ArrayList<Pill>();
        for (Pill p : l) {
            for (int t : p.getTime()) {
                if (t == currentMin) {
                    list.add(p);
                }
            }
        }
        return list;
    }


}




