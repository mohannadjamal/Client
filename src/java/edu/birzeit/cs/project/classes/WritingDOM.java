/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.birzeit.cs.project.classes;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jamal
 */
public class WritingDOM {

    public void write(String pharmName, String pharmLoc, String medicine) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        String xmlLoc = "/home/mohannad/NetBeansProjects/Course-Project/src/java/edu/birzeit/cs/project/xml/";
        Document doc = docBuilder.parse(new FileInputStream(xmlLoc + "reports.xml"));

        Node root = doc.getFirstChild();
        int ticket = 0;
        NodeList list = doc.getElementsByTagName("report");
        if (list.getLength() != 0) {
            Element lastElement = (Element) list.item(list.getLength() - 1);
            ticket = Integer.parseInt(lastElement.getAttribute("ticket")) + 1;

        }
        Element report = doc.createElement("report");
        Element pharmacyName = doc.createElement("PharmacyName");
        Element pharmacyLocation = doc.createElement("PharmacyLocation");
        Element medicineName = doc.createElement("Medicine");
        Element priceTag = doc.createElement("Price");

        pharmacyName.appendChild(doc.createTextNode(pharmName));
        pharmacyLocation.appendChild(doc.createTextNode(pharmLoc));
        medicineName.appendChild(doc.createTextNode(medicine));

        report.setAttribute("ticket", "" + ticket);
        report.appendChild(pharmacyName);
        report.appendChild(pharmacyLocation);
        report.appendChild(medicineName);

        root.appendChild(report);
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(xmlLoc + "reports.xml"));
        transformer.transform(source, result);

        System.out.println("Done");
    }
}
