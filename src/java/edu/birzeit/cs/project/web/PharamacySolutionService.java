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
/**
 *
 * @author jamal
 */
@WebService(serviceName = "PharamacySolutionService")
public class PharamacySolutionService {

    ParsingDOM parser = new ParsingDOM();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "diagnose")
    public ArrayList<Disease> diagnose(@WebParam(name = "symptoms") ArrayList symptoms) {
        
        ArrayList<Disease> diseasesToReturn = new ArrayList<>();
        
        for (int i = 0; i < symptoms.size(); i++)
            for (int j = 0; j < parser.diseases.size(); j++) {
                
                if (parser.diseases.get(j).getMajorSymptom().equals(symptoms.get(i))) {
                    diseasesToReturn.add(parser.diseases.get(j));
                    continue;
                }
                
                for (int k = 0; k < parser.diseases.get(j).getMinorSymptom().size(); k++)
                    if (parser.diseases.get(j).getMinorSymptom().get(k).equals(symptoms.get(i))) {
                        diseasesToReturn.add(parser.diseases.get(j));
                        break;
                    }
            }
        
        return diseasesToReturn;
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCure")
    public ArrayList<Medicine> getCure(@WebParam(name = "diseaseId") int diseaseId ) {
        
        ArrayList<Medicine> medsToReturn = new ArrayList<>();
        
        for (int i = 0; i < parser.diseases.size(); i++)
            if (parser.diseases.get(i).getId() == diseaseId) {
                
                ArrayList<Integer> foundIds =  parser.diseases.get(i).getMedicineIds();
                
                for (int j = 0; j < foundIds.size(); j++)
                    for (int k = 0; k < parser.meds.size(); k++)
                        if (parser.meds.get(k).getId() == foundIds.get(j))
                            medsToReturn.add(parser.meds.get(k));
                    
                return medsToReturn;
                
            }
        
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Purchase")
    public String Purchase(@WebParam(name = "medicineid") int medicineid) {
        //Purchase a medicine
        //TODO write your implementation code here:
        return null;
    }

}
