/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Objetos.Estado;
import Objetos.Transicion;
import Parser.Parser;


/**
 *
 * @author SERGIO
 */
public class Runner {
    
    public static void main(String[] args) {
        Parser par =  new Parser("SEjercicio1.jff"); 
        System.out.print("-----------------Estados-------------------");
         for (Estado estado : par.conversorXMLAEstados())
             System.out.println(estado.toString());
         System.out.println("Numero de estados: "+par.conversorXMLAEstados().size());
         System.out.println("-----------------TRANSICIONES-------------------");
         for (Transicion transicion : par.conversorXMLATransicion())
             System.out.println(transicion.toString());
         System.out.println("Numero de transiciones: "+par.conversorXMLATransicion().size());
    }
    
}
