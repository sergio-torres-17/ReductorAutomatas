/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SERGIO
 */
@XmlRootElement
public class Structure {
    private Automaton automaton;
    private String type;
    
    
    public Structure() {
    }

    public Structure(Automaton automaton, String type) {
        //super();
        this.automaton = automaton;
        this.type = type;
    }
    @XmlElement(name="automaton")  
    public Automaton getAutomaton() {
        return automaton;
    }
    @XmlElement(name="type")  
    public String getType() {
        return type;
    }

    
    
    
}
