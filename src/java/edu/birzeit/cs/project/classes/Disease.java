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
public class Disease {
    private int id;
    private String name;
    private ArrayList<Integer> medicineIds;
    private String organism;
    private String cause;
    private String majorSymptom;
    private ArrayList<String> minorSymptom;

    public Disease(int id, String name, ArrayList<Integer> medicineIds, String organism, String cause, String majorSymptom, ArrayList<String> minorSymptom) {
        this.id = id;
        this.name = name;
        this.medicineIds = medicineIds;
        this.organism = organism;
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

    public String getOrganism() {
        return organism;
    }
    
    public String getMajorSymptom() {
        return majorSymptom;
    }

    public ArrayList<Integer> getMedicineIds() {
        return medicineIds;
    }

    public ArrayList<String>getMinorSymptom() {
        return minorSymptom;
    }
    
}
