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
public class Estado {
    private int id;
    private String name;
    private boolean initial,fina;
    private float x,y;

    public Estado(int id, String name, boolean initial, boolean fina, float x, float y) {
        this.id = id;
        this.name = name;
        this.initial = initial;
        this.fina = fina;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInitial() {
        return initial;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public boolean isFina() {
        return fina;
    }

    public void setFina(boolean fina) {
        this.fina = fina;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Estado{" + "id=" + id + ", name=" + name + ", Es inicial=" + ((initial)?"Sí":"No") + ", Es final=" + ((fina)?"Sí":"No") + ", x=" + x + ", y=" + y + '}';
    }
    
      
     
}
