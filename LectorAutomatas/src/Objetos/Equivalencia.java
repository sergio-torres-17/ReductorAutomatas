package Objetos;



import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JTextArea;

public class Equivalencia {

    private AFD M1;
    private AFD M2;
    private String alfabeto;
    private int contador = 0;
    private String cadena = "";

    protected Map<Integer, Nodo[]> columna0;
    protected Comparacion[] columnas;

    private String listaAlfabeto = "";

    private boolean compatible = true;
    private boolean completo = false;
    private boolean esEquivalente = false;
    private JTextArea caja;
    int z = 0;

    public Equivalencia(AFD automata1, AFD automata2, String alfa,JTextArea caja) {
        this.M1 = automata1;
        this.M2 = automata2;
        this.alfabeto = alfa;
        this.caja = caja;
    }

    public String cadenaAlfabeto() {
        String cad = "  Alfabeto        \n|";
        int i = 0;
        for (String s : this.alfabeto.split(",")) {
            cad += "    " + s + "    |";
            i++;
        }
        return cad;
    }

    public String cadenaColumna0() {
        String cad = "  Estados\n| M1   M2 |\n";
        for (int r = 0; r < columna0.size(); r++) {
            Nodo[] t = columna0.get(r);
            cad += (" [" + t[0].nombre + ", " + t[1].nombre + "]\n");
        }
        return cad;
    }
    
    public boolean llenarFilaDeSimbolos(Nodo m1, Nodo m2) {
        columnas = new Comparacion[z];
        Nodo[] aux = new Nodo[2];
        Nodo a = null;
        Nodo b = null;
        aux[0] = m1;
        aux[1] = m2;
        int x = 0;
        String cadena = caja.getText();

        for (String simbolo : this.alfabeto.split(",")) {

            a = this.M1.obtenerNodo(aux[0].getTransicion(simbolo));
            b = this.M2.obtenerNodo(aux[1].getTransicion(simbolo));

            if ((a.finall && b.finall) || (!a.finall && !b.finall)) {
                columnas[x] = new Comparacion(simbolo, a, b);
                x++;
            } else {
                cadena += ("En el simbolo " + simbolo + " los estados "
                        + a.nombre + " y " + b.nombre + " No son compatibles");
                this.compatible = false;
            }

            listaAlfabeto += (" [" + a.nombre + ", " + b.nombre + "]  ");
        }

        listaAlfabeto += "\n";
        caja.setText(cadena);
        return this.compatible;
    }
    
    public boolean compararAutomatas(Nodo aM1, Nodo aM2) {
        Nodo[] aux = new Nodo[2];
        aux[0] = aM1;
        aux[1] = aM2;
        this.z = this.alfabeto.split(",").length;
        String result = caja.getText();

        this.columna0 = new TreeMap();
        boolean b = false;

        columna0.put(0, aux);

        int i = 0;
        while (this.compatible == true && completo == false) {
            cadena = "";
            cadena = cadenaColumna0();
            aux = columna0.get(i);
            b = verificarEstadosEnColumna0(aux[0], aux[1]);
            i = i + 1;
        }

        if (this.compatible == true) {
            result += ("\nLos automatas finitos deterministicos son equivalentes.\n");
            aux[0] = columna0.get(columna0.size() - 1)[0];
            aux[1] = columna0.get(columna0.size() - 1)[1];
            esEquivalente = true;
            llenarFilaDeSimbolos(aux[0], aux[1]);

            result+=(cadena);
            result+=(cadenaAlfabeto());
            result+=(listaAlfabeto);
        } else {
            result += ("\nLos automatas finitos deterministicos no son equivalentes.");
        }
        this.caja.setText(result);
        return esEquivalente;

    }

    public boolean verificarEstadosEnColumna0(Nodo iM1, Nodo iM2) {
        Nodo[] aux = new Nodo[2];
        aux[0] = iM1;
        aux[1] = iM2;
        int t = 0;
        int w = 0;
        boolean c = true;

        c = llenarFilaDeSimbolos(aux[0], aux[1]);
        boolean v = true;

        if (c == true) {
            for (int i = 0; i < z; i++) {
                for (int y = 0; y < columna0.size(); y++) {
                    v = Arrays.equals(columna0.get(y), columnas[i].nodos);
                    if (v == true) {
                        w = w + 1;
                    }
                }
                if (w == 0) {
                    contador = contador + 1;
                    columna0.put(contador, columnas[i].nodos);
                } else {
                    t = t + 1;
                }
            }
        }
        if (t == z) {
            completo = true;
        }
        return completo;
    }
}
