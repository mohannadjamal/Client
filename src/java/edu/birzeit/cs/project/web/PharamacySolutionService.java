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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "diagnose")
    public ArrayList<Disease> diagnose(@WebParam(name = "symptoms") ArrayList symptoms) {
        //TODO write your implementation code here:
        // Send an array of symptomps and return matching dieseases with such symptomps
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCure")
    public ArrayList<Medicine> getCure(@WebParam(name = "diseaseId") int diseaseId ) {
        //TODO write your implementation code here:
        // Get the list of medicines for the disease from its id
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
