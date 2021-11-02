/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import Objetos.Automata;
import Objetos.Estado;
import Objetos.Transicion;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
public class Conversores {

    private File archivo;

    public Conversores(File archivo) {
        this.archivo = archivo;
    }

    public ArrayList<Estado> conversorXMLAEstados() {
        ArrayList<Estado> listaEstados = new ArrayList<>();
        try {
            DocumentBuilder db;
            Document documento;

            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            documento = (Document) db.parse(this.archivo);
            documento.getDocumentElement().normalize();
            NodeList lista = documento.getElementsByTagName("state");
            Node nNode;
            for (int temp = 0; temp < lista.getLength(); temp++) {
                nNode = lista.item(temp);
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
            System.out.println(ex);
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }
        return listaEstados;
    }
    public ArrayList<Transicion> conversorXMLATransicion() {
        ArrayList<Transicion> listaTransiciones = new ArrayList<Transicion>();
        try {
            DocumentBuilder db;
            Document documento;

            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            documento = (Document) db.parse(this.archivo);
            documento.getDocumentElement().normalize();
            NodeList lista = documento.getElementsByTagName("transition");
            Node nNode;
            for (int temp = 0; temp < lista.getLength(); temp++) {
                nNode = lista.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    listaTransiciones.add(new Transicion(
                            eElement.getElementsByTagName("from").item(0).getTextContent(),
                            (eElement.getElementsByTagName("to").item(0).getTextContent()),
                            eElement.getElementsByTagName("read").item(0).getTextContent()
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (SAXException ex) {
            System.out.println(ex);
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }
        return listaTransiciones;
    }
    public String conversorAutomataJSON(){
        String dev = null;
        
        
        return dev;
    }
    public static String detectorDelLenguaje(ArrayList<Transicion> listaTransiciones){
        ArrayList<String> aux = new ArrayList<String>();
        String dev = "{"; 
        for (Transicion transicion : listaTransiciones) {
            if(!buscarEnLista(aux, transicion.getValor()))
                aux.add(transicion.getValor());
        }
        for (int i = 0; i < aux.size(); i++) {
            if(i < aux.size()-1)
                dev+=aux.get(i)+",";
            else
                dev+=aux.get(i);
        }
        dev+="}";
        return dev;
    }
    public Automata constructorAutomata(){
        return new Automata(this.conversorXMLAEstados(), this.conversorXMLATransicion());
    }
    public static boolean buscarEnLista(ArrayList<String> listaElementos, String elementoABuscar){
        for (String listaElemento : listaElementos) {
            if(listaElemento.equals(elementoABuscar))
                return true;
        }
        return false;
    }
    public static DefaultTableModel tabularEstadosYTransiciones(String[] palabrasLenguaje, ArrayList<Transicion> listaTransiciones,JTable tabla, ArrayList<Estado> lista){
        DefaultTableModel dev = new DefaultTableModel();
        
        dev.addColumn("Origen");
        dev.addColumn("Parametro");
        dev.addColumn("destino");
        for (Transicion transicion : listaTransiciones)
            dev.addRow(new String[]{buscarAutomataPorId(transicion.getDe(), lista),transicion.getValor(),buscarAutomataPorId(transicion.getHacia(), lista)});
        return dev;
    }
    public static DefaultListModel devolverListaEstados(ArrayList<Estado> estados){
        DefaultListModel dev = new DefaultListModel();
        int contador = 0;
        for (Estado estado : estados) {
            dev.add(contador, estado.getName());
            contador++;
        }
        return dev;
    }
    public static void limpiar(JTable tabla){
        for (int i = 0; i < tabla.getRowCount(); i++) {
            
        }
    }
    public static String buscarAutomataPorId(String id, ArrayList<Estado> estados){
        for (Estado estado : estados)
            if(estado.getId() ==Integer.parseInt(id))
                return estado.getName();
        
        return null;
    }
}
