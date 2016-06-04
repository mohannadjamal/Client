/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.birzeit.cs.project.web;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import edu.birzeit.cs.project.classes.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.Oneway;

/**
 *
 * @author jamal
 */
@WebService(serviceName = "PharamacySolutionService")
public class PharamacySolutionService {

    ParsingDOM parser = new ParsingDOM();
    WritingDOM report = new WritingDOM();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "diagnose")
    public String diagnose() {
        String[] symptoms = {"Immune System Breakdown"};
        ArrayList<Disease> diseasesToReturn = new ArrayList<Disease>();
        String s = "";

        for (int i = 0; i < symptoms.length; i++) {
            s = s + "here ";
            for (int j = 0; j < parser.diseases.size(); j++) {
                s = s + " here " + j;
                if (parser.diseases.get(j).getMajorSymptom().equals(symptoms[i])) {
                    diseasesToReturn.add(parser.diseases.get(j));
                    s = s + parser.diseases.get(j).getName();
                    continue;
                }

                for (int k = 0; k < parser.diseases.get(j).getMinorSymptom().size(); k++) {
                    if (parser.diseases.get(j).getMinorSymptom().get(k).equals(symptoms[i])) {
                        diseasesToReturn.add(parser.diseases.get(j));
                        s = s + parser.diseases.get(j).getName();
                        break;
                    }
                }
            }
        }
        Disease[] f = new Disease[diseasesToReturn.size()];
        for (int i = 0; i < diseasesToReturn.size(); i++) {
            f[i] = diseasesToReturn.get(i);
        }
        return s;

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCure")
    public String getCure(@WebParam(name = "diseaseId") int diseaseId) {
        String s = "";
        ArrayList<Medicine> medsToReturn = new ArrayList<>();

        for (int i = 0; i < parser.diseases.size(); i++) {
            if (parser.diseases.get(i).getId() == diseaseId) {

                ArrayList<Integer> foundIds = parser.diseases.get(i).getMedicineIds();

                for (int j = 0; j < foundIds.size(); j++) {
                    for (int k = 0; k < parser.meds.size(); k++) {
                        if (parser.meds.get(k).getId() == foundIds.get(j)) {
                            s = s + parser.meds.get(k).list();
                        }
                    }
                }

                return s;

            }
        }

        return "ERROR: Check inputs";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPharmacies")
    public ArrayList<Pharmacy> getPharmacies(@WebParam(name = "medicineid") int medicineid) {
        //Find pharmacies selling a medicine
        //TODO write your implementation code here:
        ArrayList<Pharmacy> pharmaciesReturn = new ArrayList<Pharmacy>();
        for (int i = 0; i < parser.pharmacies.size(); i++) {
            for (int j = 0; j < parser.pharmacies.get(i).getMedicineIds().size(); j++) {
                if (parser.pharmacies.get(i).getMedicineIds().get(j) == medicineid) {
                    pharmaciesReturn.add(parser.pharmacies.get(i));
                }
            }
        }
        return pharmaciesReturn;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPharmacy")
    public Pharmacy getPharmacy(@WebParam(name = "pharmacyId") int pharmacyId) {
        //Retruns a  Pharmacy with all of it's info
        //TODO write your implementation code here:
        for (int i = 0; i < parser.pharmacies.size(); i++) {
            if (parser.pharmacies.get(i).getId() == pharmacyId) {
                return parser.pharmacies.get(i);
            }
        }
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getMedicine")
    public Medicine getMedicine(@WebParam(name = "medicineId") int medicineId) {
        //Retruns a Medicine with all of it's info
        //TODO write your implementation code here:
        for (int i = 0; i < parser.meds.size(); i++) {
            if (parser.meds.get(i).getId() == medicineId) {
                return parser.meds.get(i);
            }

        }
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getDisease")
    public Disease getDisease(@WebParam(name = "diseaseId") int diseaseId) {
        //Retruns a Disease with all of it's info
        //TODO write your implementation code here:
        for (int i = 0; i < parser.diseases.size(); i++) {
            if (parser.diseases.get(i).getId() == diseaseId) {
                return parser.diseases.get(i);
            }
        }
        return null;
    }
//    PROBLEMS WITH USING SERVICES

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Order")
    public boolean Order(@WebParam(name = "pharmId") int pharmId, @WebParam(name = "medId") int medId) {
        //Adds a report of the chosen pharmacy to get a medicine from to reports.xml
        for (int i = 0; i < parser.pharmacies.size(); i++) {
            if (pharmId == parser.pharmacies.get(i).getId()) {
                for (int j = 0; j < parser.pharmacies.get(i).getMedicineIds().size(); j++) {
                    if (medId == parser.pharmacies.get(i).getMedicineIds().get(j)) {
                        for (int k = 0; k < parser.meds.size(); k++) {
                            if (medId == parser.meds.get(k).getId()) {
                                try {
                                    report.write(parser.pharmacies.get(i).getName(), parser.pharmacies.get(i).getLocation(), parser.meds.get(k).getName());
                                    return true;
                                } catch (Exception ex) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @WebMethod(operationName = "listDisease")
    public String listDisease() {
        String s = "";
        //Retruns a Disease with all of it's info
        //TODO write your implementation code here:
        for (int i = 0; i < parser.diseases.size(); i++) {
            s = s + parser.diseases.get(i).list();
        }
        return s;
    }

    @WebMethod(operationName = "listMedicine")
    public String listMedicine() {
        String s = "";
 

        //TODO write your implementation code here:
        for (int i = 0; i < parser.meds.size(); i++) {
            s = s + parser.meds.get(i).list();
        }
        return s;
    }

    @WebMethod(operationName = "listPharmacy")
    public String listPharmacy() {
        String s = "";

        //TODO write your implementation code here:
        for (int i = 0; i < parser.pharmacies.size(); i++) {
            s = s + parser.pharmacies.get(i).list();
        }
        return s;
    }
}
