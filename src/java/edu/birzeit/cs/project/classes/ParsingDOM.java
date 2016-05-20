/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.birzeit.cs.project.classes;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParsingDOM {

    ArrayList<Disease> diseases = new ArrayList<Disease>();
    ArrayList<Medicine> meds = new ArrayList<Medicine>();

    public static void main(String[] args) throws Exception {
        ParsingDOM p = new ParsingDOM();
        
    }
    
    public ParsingDOM() {
        try {
            parseXML();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void parseXML() throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        
        Document docDise = docBuilder.parse(new FileInputStream("diseases.xml"));
        NodeList listDise = docDise.getElementsByTagName("*");
        
        Document docMedi = docBuilder.parse(new FileInputStream("medicines.xml"));
        NodeList listMedi = docMedi.getElementsByTagName("*");

        int disId = 0;
        String disName = null;

        ArrayList<Integer> medicineIds = new ArrayList<Integer>();
        ArrayList<String> minorsymptoms = new ArrayList<String>();
        String organism = null;
        String cause = null;
        String majorsymptom = null;

        int tagCounter = -1;
        
        for (int i = 0; i < listDise.getLength(); i++) {

            Element element = (Element) listDise.item(i);
            String nodeName = element.getNodeName();
            
            if (nodeName.equals("disease")) {
                disId = Integer.parseInt(element.getAttribute("id"));
            } else if (nodeName.equals("name")) {
                disName = element.getChildNodes().item(0).getNodeValue();
            } else if (nodeName.equals("organism")) {
                organism = element.getChildNodes().item(0).getNodeValue();
            } else if (nodeName.equals("cause")) {
                cause = element.getChildNodes().item(0).getNodeValue();
            } else if (nodeName.equals("majorsymptom")) {
                majorsymptom = element.getChildNodes().item(0).getNodeValue();
            } else if (nodeName.equals("medicines")) {
                   
                
                NodeList listMediIds = element.getElementsByTagName("*");
                for (int j = 0; j < listMediIds.getLength(); j++) {
         
                    Element medId = (Element) listMediIds.item(j);
                    medicineIds.add(Integer.parseInt(medId.getFirstChild().getNodeValue()));
                
                }
                
                i += listMediIds.getLength();
                
            } else if (nodeName.equals("minorsymptoms")) {

                NodeList listMinSym = element.getElementsByTagName("*");
                for (int j = 0; j < listMinSym.getLength(); j++) {

                    Element minorSym = (Element) listMinSym.item(j);
                    // Add minorSym to the disease's list of minor symptoms
                    minorsymptoms.add(minorSym.getFirstChild().getNodeValue());
                    
                }
                i += listMinSym.getLength();
            }
            
            if (++tagCounter == 7) {
                diseases.add(new Disease(disId,disName,(ArrayList<Integer>)medicineIds.clone(),organism,cause,majorsymptom,minorsymptoms));
                tagCounter = 0;
            
                medicineIds = new ArrayList<Integer>();
                minorsymptoms = new ArrayList<String>();
                organism = null;
                cause = null;
                majorsymptom = null;
            
            }
        }
        
        // MEDICINES
        int medId = 0;
        String medName = null;
        double price = 0.0;
        String content = null;
        
        
        tagCounter = -1;
        
        for (int i = 0; i < listMedi.getLength(); i++) {

            Element element = (Element) listMedi.item(i);
            String nodeName = element.getNodeName();

            if (nodeName.equals("medicine")) {
                medId = Integer.parseInt(element.getAttribute("id"));
            } else if (nodeName.equals("name")) {
                medName = element.getChildNodes().item(0).getNodeValue();
            } else if (nodeName.equals("price")) {
                price = Double.parseDouble(element.getChildNodes().item(0).getNodeValue());
            } else if (nodeName.equals("majorcontent")) {
                content = element.getChildNodes().item(0).getNodeValue();
            }
            if (++tagCounter == 4) {
                
                meds.add(new Medicine(medId, medName, price, content));
                tagCounter = 0;
                
                medId = 0;
                medName = null;
                price = 0.0;
                content = null;
        
            }
        }

        for (int i = 0; i < meds.size(); i++)
            System.out.println(meds.get(i));
        
    }

}
