/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author SERGIO
 */
public class Transicion {
    private int de, hacia;
    String valor;

    public Transicion(int de, int hacia, String valor) {
        this.de = de;
        this.hacia = hacia;
        this.valor = valor;
    }

    public int getDe() {
        return de;
    }

    public void setDe(int de) {
        this.de = de;
    }

    public int getHacia() {
        return hacia;
    }

    public void setHacia(int hacia) {
        this.hacia = hacia;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Transicion{" + "de=" + de + ", hacia=" + hacia + ", valor=" + valor + '}';
    }

    
    
    
}
