package Objetos;



public class Comparacion {
    protected String nombreColumna;
    protected final Nodo nodos [] = new Nodo[2];
    
    public Comparacion(String nombreColumna,Nodo estado1, Nodo estado2){
        this.nombreColumna = nombreColumna;
        nodos[0] = estado1;
        nodos[1] = estado2;
    }
    
    public Nodo[] getEstadosColumna(String nombreColumna){
        return this.nodos;
    }

}
