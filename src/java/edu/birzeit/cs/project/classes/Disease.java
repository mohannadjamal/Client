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
public class Disease {
    private int id;
    private String name;
    private int[] medicineIds;
    private String cause;
    private String majorSymptom;
    private String minorSymptom;

    public Disease(int id, String name, int[] medicineIds, String cause, String majorSymptom, String minorSymptom) {
        this.id = id;
        this.name = name;
        this.medicineIds = medicineIds;
        this.cause = cause;
        this.majorSymptom = majorSymptom;
        this.minorSymptom = minorSymptom;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCause() {
        return cause;
    }

    public String getMajorSymptom() {
        return majorSymptom;
    }

    public int[] getMedicineIds() {
        return medicineIds;
    }

    public String getMinorSymptom() {
        return minorSymptom;
    }
}
