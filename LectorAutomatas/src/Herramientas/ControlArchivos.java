package Herramientas;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ControlArchivos {
    public void guardarAutomata(String ruta){
        
    }
    public static File abrirArchivo(Component componente){
        File archivo = null;
        JFileChooser ventanita = new JFileChooser();
        ventanita.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter tiposArchivos = new FileNameExtensionFilter("Archivo de automata Jff","jff");
        ventanita.addChoosableFileFilter(tiposArchivos);
        ventanita.setFileFilter(tiposArchivos);
        if(ventanita.showOpenDialog(ventanita) == JFileChooser.APPROVE_OPTION)
            archivo = ventanita.getSelectedFile();
        return archivo;
    }
    public static File abrirArchivo(Component componente, String tituloOpenFile){
        File archivo = null;
        JFileChooser ventanita = new JFileChooser();
        ventanita.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter tiposArchivos = new FileNameExtensionFilter("Archivo de automata Jff","jff");
        ventanita.addChoosableFileFilter(tiposArchivos);
        ventanita.setFileFilter(tiposArchivos);
        ventanita.setDialogTitle(tituloOpenFile);
        if(ventanita.showOpenDialog(ventanita) == JFileChooser.APPROVE_OPTION)
            archivo = ventanita.getSelectedFile();
        return archivo;
    }
}
