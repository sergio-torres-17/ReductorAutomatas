/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import Objetos.Estado;
import Objetos.Transicion;
import Vista.Runner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author SERGIO
 */
public class Parser {
    private String rutaFila;

    public Parser(String rutaFila) {
        this.rutaFila = rutaFila;
    }
    
    public ArrayList<Estado> conversorXMLAEstados() {
        ArrayList<Estado> listaEstados = new ArrayList<>();
        try {
            DocumentBuilder db;
            Document documento;

            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            documento = (Document) db.parse(new File(this.rutaFila));
            documento.getDocumentElement().normalize();
            NodeList lista = documento.getElementsByTagName("state");
            Node nNode;
            for (int temp = 0; temp < lista.getLength(); temp++) {
                nNode = lista.item(temp);
                System.out.println("\n");
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    listaEstados.add(new Estado(
                            Integer.parseInt(eElement.getAttribute("id")),
                            eElement.getAttribute("name"),
                            (String.valueOf(eElement.getElementsByTagName("initial").item(0)).contains("[initial")),
                            (String.valueOf(eElement.getElementsByTagName("final").item(0)).contains("[final")),
                            Float.parseFloat(eElement.getElementsByTagName("x").item(0).getTextContent()),
                            Float.parseFloat(eElement.getElementsByTagName("y").item(0).getTextContent())
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (SAXException ex) {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEstados;
    }
    
    
    public ArrayList<Transicion> conversorXMLATransicion() {
        ArrayList<Transicion> listaTransiciones = new ArrayList<Transicion>();
        try {
            DocumentBuilder db;
            Document documento;

            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            documento = (Document) db.parse(new File(this.rutaFila));
            documento.getDocumentElement().normalize();
            NodeList lista = documento.getElementsByTagName("transition");
            Node nNode;
            for (int temp = 0; temp < lista.getLength(); temp++) {
                nNode = lista.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    listaTransiciones.add(new Transicion(
                            Integer.parseInt(eElement.getElementsByTagName("from").item(0).getTextContent()),
                            Integer.parseInt(eElement.getElementsByTagName("to").item(0).getTextContent()),
                            eElement.getElementsByTagName("read").item(0).getTextContent()
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (SAXException ex) {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTransiciones;
    }
}
