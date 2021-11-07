package Objetos;




import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AFDs_Equivalentes {
    protected AFD M1;
    protected AFD M2;
    protected AFD M3;
    public String alfabeto;
    private JTextArea caja;

    public AFDs_Equivalentes(JTextArea caja) {
        this.caja = caja;
    }

    
    public void comprobarEquivalencia(){
        Equivalencia eq = new Equivalencia(M1,M2,alfabeto,caja);
        eq.compararAutomatas(M1.obtenerNodoInicial(), M2.obtenerNodoInicial());
    }
    public void simplificacion(String alfabetoP, int numEstados, ArrayList<Estado> estados, JTextArea caja) {
        alfabeto = alfabetoP;
        int numeroEstadosM3 = numEstados;
        M3 = new AFD(alfabeto,numeroEstadosM3,caja);
        M3.llenarEstados(estados);
        
        Simplificacion simplificacion  = new Simplificacion(M3, alfabeto, numeroEstadosM3,caja);
        simplificacion.simplificarAFD();
    }
}
