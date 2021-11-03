package Objetos;

import java.util.ArrayList;

public class Automata {
    private ArrayList<Estado> estados;
    private ArrayList<Transicion> trasiciones;

    public Automata(ArrayList<Estado> estados, ArrayList<Transicion> trasiciones) {
        this.estados = estados;
        this.trasiciones = trasiciones;
    }
    public ArrayList<Estado> getEstados() {
        return estados;
    }
    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }
    public ArrayList<Transicion> getTrasiciones() {
        return trasiciones;
    }
    public void setTrasiciones(ArrayList<Transicion> trasiciones) {
        this.trasiciones = trasiciones;
    }

    
    
    
    
}
