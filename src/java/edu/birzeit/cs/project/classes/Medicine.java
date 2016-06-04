/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.birzeit.cs.project.classes;

/**
 *
 * @author jamal
 */
public class Medicine {

    private int id;
    private String name;
    private double price;
    private String contents;

    public Medicine(int id, String name, double price, String contents) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getContents() {
        return contents;
    }

    public String list() {
        String s = "--ID: " + id + " Name: " + name + " Price: " +price +"$";
        s = s + "\n";
        return s;
    }
}
