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
    private String de, hacia;
    private String valor;

    public Transicion(String de, String hacia, String valor) {
        this.de = de;
        this.hacia = hacia;
        this.valor = valor;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getHacia() {
        return hacia;
    }

    public void setHacia(String hacia) {
        this.hacia = hacia;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
