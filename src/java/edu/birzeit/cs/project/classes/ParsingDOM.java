/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.birzeit.cs.project.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParsingDOM {

    public ArrayList<Disease> diseases = new ArrayList<Disease>();
    public ArrayList<Medicine> meds = new ArrayList<Medicine>();
    public ArrayList<Pharmacy> pharmacies = new ArrayList<Pharmacy>();

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
        String xmlLoc = new File("src").getAbsolutePath() + "\\java\\edu\\birzeit\\cs\\project\\xml\\";
        
        Document docDise = docBuilder.parse(new FileInputStream(xmlLoc + "diseases.xml"));
        NodeList listDise = docDise.getElementsByTagName("*");

        Document docMedi = docBuilder.parse(new FileInputStream(xmlLoc + "medicines.xml"));
        NodeList listMedi = docMedi.getElementsByTagName("*");

        Document docPharm = docBuilder.parse(new FileInputStream(xmlLoc + "pharmacies.xml"));
        NodeList listPharm = docPharm.getElementsByTagName("*");

        // DISEASES
        // Info to add to each disease
        int disId = 0;
        String disName = null;
        ArrayList<Integer> medicineIds = new ArrayList<Integer>();
        ArrayList<String> minorsymptoms = new ArrayList<String>();
        String organism = null;
        String cause = null;
        String majorsymptom = null;
        // End info
        
        // tagCounter is used to count tags read. Once we reach a certain number
        // of tags, this means we have obtained full info for one element.
        // It starts with -1 to bypass the head tag, which is usually the plural
        // of elements
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
                // <medicines> contains multiple tags <medicine>
                NodeList listMediIds = element.getElementsByTagName("*");
                for (int j = 0; j < listMediIds.getLength(); j++) {

                    Element medId = (Element) listMediIds.item(j);
                    medicineIds.add(Integer.parseInt(medId.getFirstChild().getNodeValue()));

                }
                
                // We don't want the main loop to go through the sub tags of the
                // medicines, so we increase i (the iterator) by its length
                i += listMediIds.getLength();

            } else if (nodeName.equals("minorsymptoms")) {

                // <minorsymptoms> contains multiple tags <minorsymptom>
                
                NodeList listMinSym = element.getElementsByTagName("*");
                for (int j = 0; j < listMinSym.getLength(); j++) {

                    Element minorSym = (Element) listMinSym.item(j);
                    minorsymptoms.add(minorSym.getFirstChild().getNodeValue());

                }
                // We don't want the main loop to go through the sub tags of the
                // medicines, so we increase i (the iterator) by its length
                i += listMinSym.getLength();
            
            }

            // Each time a tag is read, we increment tagCounter. Once it reaches
            // 7, this means we have read enough info to add one disease
            if (++tagCounter == 7) {
                
                diseases.add(new Disease(disId, disName, (ArrayList<Integer>) medicineIds.clone(), organism, cause, majorsymptom, minorsymptoms));
                tagCounter = 0; // Reset to 0. First time we put -1 to bypass
                                // the header tag

                // Reset info for more diseases just in case
                medicineIds = new ArrayList<Integer>();
                minorsymptoms = new ArrayList<String>();
                organism = null;
                cause = null;
                majorsymptom = null;

            }
        }

        // MEDICINES
        // Info
        int medId = 0;
        String medName = null;
        double price = 0.0;
        String content = null;
        // End Info
        
        // tagCounter is explained above
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

        //PHARMACIES
        // Info
        int pharmId = 0;
        String pharmName = null;
        String pharmLocation = null;
        ArrayList<Integer> pharmMedicineIds = new ArrayList<Integer>();
        // End Info
        
        // tagCounter is explained above
        tagCounter = -1;
        for (int i = 0; i < listPharm.getLength(); i++) {
            Element element = (Element) listPharm.item(i);
            String nodeName = element.getNodeName();

            if (nodeName.equals("pharmacy")) {
                pharmId = Integer.parseInt(element.getAttribute("id"));

            } else if (nodeName.equals("name")) {
                pharmName = element.getChildNodes().item(0).getNodeValue();
            } else if (nodeName.equals("location")) {
                pharmLocation = element.getChildNodes().item(0).getNodeValue();

            } else if (nodeName.equals("medicines")) {
                // <medicines> contains multiple tags, <medicine>
                NodeList listMediIds = element.getElementsByTagName("*");
                for (int j = 0; j < listMediIds.getLength(); j++) {

                    Element xmedId = (Element) listMediIds.item(j);
                    medicineIds.add(Integer.parseInt(xmedId.getFirstChild().getNodeValue()));

                }

                i += listMediIds.getLength();

            }

            if (++tagCounter == 4) {
                pharmacies.add(new Pharmacy(pharmId, pharmName, pharmLocation, medicineIds));
                tagCounter = 0;

                pharmId = 0;
                pharmName = null;
                pharmLocation = null;
                medicineIds = new ArrayList<Integer>();

            }

        }

    }

}
