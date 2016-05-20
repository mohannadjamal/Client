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
    public ArrayList<Disease> diagnose(@WebParam(name = "symptoms") ArrayList symptoms) {

        ArrayList<Disease> diseasesToReturn = new ArrayList<Disease>();

        for (int i = 0; i < symptoms.size(); i++) {
            for (int j = 0; j < parser.diseases.size(); j++) {

                if (parser.diseases.get(j).getMajorSymptom().equals(symptoms.get(i))) {
                    diseasesToReturn.add(parser.diseases.get(j));
                    continue;
                }

                for (int k = 0; k < parser.diseases.get(j).getMinorSymptom().size(); k++) {
                    if (parser.diseases.get(j).getMinorSymptom().get(k).equals(symptoms.get(i))) {
                        diseasesToReturn.add(parser.diseases.get(j));
                        break;
                    }
                }
            }
        }

        return diseasesToReturn;

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCure")
    public ArrayList<Medicine> getCure(@WebParam(name = "diseaseId") int diseaseId) {

        ArrayList<Medicine> medsToReturn = new ArrayList<>();

        for (int i = 0; i < parser.diseases.size(); i++) {
            if (parser.diseases.get(i).getId() == diseaseId) {

                ArrayList<Integer> foundIds = parser.diseases.get(i).getMedicineIds();

                for (int j = 0; j < foundIds.size(); j++) {
                    for (int k = 0; k < parser.meds.size(); k++) {
                        if (parser.meds.get(k).getId() == foundIds.get(j)) {
                            medsToReturn.add(parser.meds.get(k));
                        }
                    }
                }

                return medsToReturn;

            }
        }

        return null;
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
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "Order")
//    @Oneway
//    public void Order(@WebParam(name = "pharmId") int pharmId, @WebParam(name = "medId") int medId) {
//        //Adds a report of the chosen pharmacy to get a medicine from to reports.xml
//        Pharmacy pharm = getPharmacy(pharmId);
//        Medicine med = getMedicine(medId);
//        if (pharm != null && med != null) {
//            ArrayList<Pharmacy> temp = getPharmacies(medId);
//            for (int i = 0; i < temp.size(); i++) {
//                if (temp.get(i).getId() == pharmId) {
//                    report.write(pharm.getName(), pharm.getLocation(), med.getName());
//                }
//            }
//        }
//    }

}
