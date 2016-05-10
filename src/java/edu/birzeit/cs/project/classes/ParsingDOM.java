/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.birzeit.cs.project.classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParsingDOM {

    static ArrayList<Disease> diseases = new ArrayList<Disease>();
    static ArrayList<Medicine> meds = new ArrayList<Medicine>();

    public ParsingDOM() {
        try {
            parse();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ParsingDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ParsingDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParsingDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void parse() throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();

        InputStream diseasesXmlStream = ParsingDOM.class.getResourceAsStream("diseases.xml");
        InputStream medicinesXmlStream = ParsingDOM.class.getResourceAsStream("medicines.xml");

        Document docDise = docBuilder.parse(diseasesXmlStream);
        Document docMedi = docBuilder.parse(medicinesXmlStream);

        NodeList listDise = docDise.getElementsByTagName("*");
        NodeList listMedi = docMedi.getElementsByTagName("*");

        int disId = 0;
        String disName = null;

        ArrayList<Integer> medicineIds = new ArrayList<Integer>();
        ArrayList<String> minorsymptoms = new ArrayList<String>();
        String organism;
        String cause = null;
        String majorsymptom = null;

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

                    Element medicineId = (Element) listMediIds.item(j);

                    for (int k = 0; k < listMedi.getLength(); k++) {

                        Element medicine = (Element) listMedi.item(k);

                        if (medicine.getAttribute("id").equals(medicineId.getFirstChild().getNodeValue())) {

                            // Add this medicine to disease's medicine list
                            medicineIds.add(Integer.parseInt(medicineId.getFirstChild().getNodeValue()));
                        }

                    }

                }

            } else if (nodeName.equals("minorsymptoms")) {

                NodeList listMinSym = element.getElementsByTagName("*");
                for (int j = 0; j < listMinSym.getLength(); j++) {

                    Element minorSym = (Element) listMinSym.item(j);
                    // Add minorSym to the disease's list of minor symptoms
                    minorsymptoms.add(minorSym.getFirstChild().getNodeValue());

                }

            }

        }

        // Using the variables above, add them to a new disease
        
        diseases.add(new Disease(disId,disName,medicineIds,cause,majorsymptom,minorsymptoms));
        // MEDICINES
        int medId;
        String medName;
        double price;
        String content;

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

        }

        // Using the variables above, create new medicines
    }

}
