package Objetos;



import java.awt.TextArea;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class AFD implements Cloneable {

    protected String alfabeto;
    protected int numeroEstados;
    protected String estadoInicial;
    protected String estadosFinales;
    protected Nodo[] estados;
    protected JTextArea caja;
    
    public AFD(AFD afd) {
        this(afd.getAlfabeto(), afd.getEstados().length, afd.getEstadoInicial(), afd.getEstadosFinales(), afd.getEstados(),afd.getCaja());
    }
    
    public AFD(String alfabeto, int numeroEstados, String estadoInicial, String estadosFinales, Nodo[] estados, JTextArea caja) {
        this.alfabeto = alfabeto;
        this.numeroEstados = numeroEstados;
        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;
        this.caja = caja;
        this.estados = new Nodo[this.numeroEstados];
        
        for(int i = 0; i < this.numeroEstados; i++){
            this.estados[i] = new Nodo(estados[i]);
        }
    }

    public AFD(String alfabeto, int numeroEstados, JTextArea caja) {
        this.alfabeto = alfabeto;
        this.numeroEstados = numeroEstados;
        this.estadoInicial = "";
        this.estadosFinales = "";
        estados = new Nodo[numeroEstados];
        this.caja = caja;
    }
    
    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }
    
    public String getAlfabeto() {
        return alfabeto;
    }
    
    public int getNumeroEstados() {
        return numeroEstados;
    }
    
    public Nodo[] getEstados() {
        return estados;
    }
    
    public String getEstadosFinales() {
        return estadosFinales;
    }
    
    public String getEstadoInicial() {
        return estadoInicial;
    }

    public JTextArea getCaja() {
        return caja;
    }

    public void setCaja(JTextArea caja) {
        this.caja = caja;
    }
    
    

    public Nodo obtenerNodo(String nombreNodo) {
        Nodo estado = null;
        for (Nodo aux : this.estados) {
            if (aux.nombre.equals(nombreNodo)) {
                estado = aux;
            }
        }
        return estado;
    }

    public Nodo obtenerNodoInicial() {
        Nodo estado = null;
        for (Nodo aux : this.estados) {
            if (aux.inicial) {
                estado = aux;
            }
        }
        return estado;
    }

    public boolean nodoEsFinal(Nodo n) {
        boolean f = false;
        for (Nodo aux : this.estados) {
            if (n.finall == true) {
                f = true;
            }
        }
        return f;
    }
    
    public boolean eliminarNodo(Nodo nodo) {
        boolean estaEliminado = false;
        try {

            Nodo[] ndo = new Nodo[numeroEstados - 1];
            int k = 0;
            for (int i = 0; i < numeroEstados; i++) {
                if (!estados[i].nombre.equals(nodo.nombre)) {
                    ndo[k] = estados[i];
                    k++;
                } else {
                    estaEliminado = true;
                }
            }
            estados = ndo;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return estaEliminado;
    }
    
    public String imprimirAutomata() {
        String resultados = caja.getText();
        for (int i = 0; i < numeroEstados; i++) {
            String estadoTransiciones = "";
            resultados += ("El estado " + estados[i].nombre + " va hacia --> \n");
            for (String s : this.alfabeto.split(",")) {
                estadoTransiciones += (estados[i].transiciones.get(s) + " en " + s + "\n");
            }
            resultados += " " + estadoTransiciones+"\n";
        }
        
        return resultados;
    }
    
    public void llenarEstados(ArrayList<Estado> estadosL) {
        for (int i = 0; i < this.numeroEstados; i++) {
            String nombre = estadosL.get(i).getName();
            boolean inicial, finall;
            if (this.estadoInicial.equals("")) {
                inicial = estadosL.get(i).isInitial();
                this.estadoInicial = inicial ? nombre : "";
            } else {
                inicial = false;
            }

            finall = estadosL.get(i).isFina();

            this.estadosFinales += finall ? nombre + "," : "";

            estados[i] = new Nodo(nombre, inicial, finall);
            
            estados[i].llenarTransiciones(this.alfabeto);
        }
    }
}
