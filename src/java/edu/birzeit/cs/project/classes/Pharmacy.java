/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.birzeit.cs.project.classes;

import java.util.ArrayList;

/**
 *
 * @author jamal
 */
public class Pharmacy {

    private int id;
    private String name;
    private String location;
    private ArrayList<Integer> medicineIds;

    public Pharmacy(int id, String name, String location, ArrayList<Integer> medicineIds) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.medicineIds = medicineIds;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Integer> getMedicineIds() {
        return medicineIds;
    }

    public String getName() {
        return name;
    }

    public String list() {
        String s = "---ID: " + id + " Name: " + name + "\nLocation: " + location;
        for (int i = 0; i < medicineIds.size(); i++) {
            s = s + " [" + medicineIds.get(i) + "] ";
        }
        s = s + "\n";
        return s;
    }
}
