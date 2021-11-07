package Objetos;



import java.awt.TextArea;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Simplificacion {
    private AFD M1;
    private AFD M2;
    private AFD M3;
    private AFD MC;
    private int numeroEstados;
    private int contador;
    private String alfabeto;
    protected Map<String, String> transiciones;
    private JTextArea caja;

    
    public Simplificacion(AFD afd, String alfabeto, int numeroEstados, JTextArea caja) {
        this.M3 = afd;
        this.MC = afd;
        this.alfabeto = alfabeto;
        this.numeroEstados = numeroEstados;
        this.caja = caja;
    }
    
    public void simplificarAFD() {
        AFD a = (MC);
        String cadena = "";
        
        while(a != null) {
            a = simplifica();
            if (a != null) {
                cadena +=("Procedimiento...\n");
                MC = new AFD(a);
                cadena +=("_________________________________________\n");
                MC.imprimirAutomata();
                cadena +=("_________________________________________\n");
            }
        }
        
        cadena +=("<<<<<<<<<<<Automata Original>>>>>>>>>>\n");
        cadena += M3.imprimirAutomata()+"\n";
        cadena +=("<<<<<<<<<<<Automata Simplificado>>>>>>>>>>\n");
        cadena += MC.imprimirAutomata();
        caja.setText(cadena);
    }
    
    private AFD simplifica() {
        AFD aux = null;
        M1 = new AFD(MC);
        M2 = new AFD(MC);
        boolean C = false;
        
        for (int i = 0; i < MC.numeroEstados; i++) {
            for (int j = i+1; j < MC.numeroEstados; j++) {
                cambiarEstadoInicial(M1, i, 1);
                
                cambiarEstadoInicial(M2, j, 2);
                
                Equivalencia equivalencia = new Equivalencia(M1, M2, alfabeto, caja);
                
                if (equivalencia.compararAutomatas(M1.obtenerNodoInicial(), M2.obtenerNodoInicial()) == true) {
                    cambiarEntradas(M1.obtenerNodoInicial(), M2.obtenerNodoInicial());
                    
                    eliminarSalidas(M2.obtenerNodoInicial(), M1.obtenerNodoInicial());
                    
                    aux = new AFD(M2);
                    C = true;
                    break;
                }
                
            }
            if (C == true) {
                break;
            }
        }
        return aux;
    }
    
   private void cambiarEstadoInicial(AFD afd, int i, int n) {
       Nodo nodo;
       nodo = afd.obtenerNodoInicial();
       
       nodo.inicial = false;
       afd.estados[i].inicial = true;
       afd.setEstadoInicial(afd.estados[i].nombre);
       
   }
   
   private void cambiarEntradas(Nodo nodo, Nodo nodo2) {
        transiciones = new TreeMap();
        for (int i = 0; i < numeroEstados; i++) {
            for (String s : this.alfabeto.split(",")) {
                if (M2.estados[i].transiciones.get(s).equals(nodo2.nombre)) {
                    M2.estados[i].transiciones.replace(s, nodo.nombre);
                }
            }

        }

    }

    private void eliminarSalidas(Nodo nodo, Nodo nodoInicial) {
        
        for (int i = 0; i < numeroEstados; i++) {
            if(M2.estados[i].nombre.equals(nodoInicial.nombre)){
                M2.estados[i].inicial = true;
                M2.estadoInicial = nodoInicial.nombre;
            }
        }
        M2.eliminarNodo(nodo);
        
        this.numeroEstados = M2.getEstados().length;
        
    }

}


