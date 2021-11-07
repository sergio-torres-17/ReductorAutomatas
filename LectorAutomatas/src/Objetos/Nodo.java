package Objetos;

///PROGRAMA CREADO POR JOSE DE JESUS ARENAS MANRIQUE

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Nodo {

    protected String nombre;
    protected boolean inicial, finall;
    // Esta variable no se necesita para la simplificación...
    //protected Nodo apuntado;
    protected Map<String, String> transiciones;
    
    public Nodo(Nodo nodo){
        this(nodo.getNombre(), nodo.esInicial(), nodo.esFinall(), nodo.getTransiciones());
    }
    
    public Nodo(String nombre, boolean inicial, boolean finall, Map<String, String> transiciones){
        this.nombre = nombre;
        this.inicial = inicial;
        this.finall = finall;
        this.transiciones = transiciones;
    }
    public Nodo(String name, boolean inicial, boolean finall) {
        this.nombre = name;
        this.inicial = inicial;
        this.finall = finall;
        this.transiciones = new HashMap();
    }

    public void llenarTransiciones(String alfabeto) {
        for (String simbolo : alfabeto.split(",")) {
            transiciones.put(simbolo, JOptionPane.showInputDialog(
                    "La transicion de " + this.nombre + " cuando es (" + simbolo + ") va hacia:"));
        }
    }
    public String getNombre() {
        return nombre;
    }

    public Map<String, String> getTransiciones() {
        return transiciones;
    }
    public boolean esInicial() {
        return inicial;
    }
    
    public boolean esFinall() {
        return finall;
    }
    
    public String getTransicion(String simbolo){
        return this.transiciones.get(simbolo);
    }
}
