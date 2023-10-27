package Extras;

import EDD.Grafo;
import EDD.Lista;
import EDD.Nodo;
import Interfaces.FileChooser;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author G. Angelo, S. Estefania y S. Jose
 *
 */
public class Funciones {

    // JFileChooser
    /**
     * Abre y carga el contenido de un archivo de texto seleccionado por el
     * usuario. Se lee su contenido y se carga en la aplicación.
     * 
     * @author G. Angelo, S. Estefania y S. Jose
     * @version: 27/10/2023
     */
    public void abrir_archivo() {
        String aux = "";
        String texto = "";
        try {
            /**
             * llamamos el metodo que permite cargar la ventana
             */
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".TXT", "txt");
            file.setFileFilter(filter);
            Component FileChooser = null;
            file.showOpenDialog(FileChooser);
            /**
             * abrimos el archivo seleccionado
             */
            File abre = file.getSelectedFile();

            /**
             * recorremos el archivo, lo leemos para plasmarlo en el area de
             * texto
             */
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                try (BufferedReader lee = new BufferedReader(archivos)) {
                    while ((aux = lee.readLine()) != null) {
                        texto += aux + "\n";
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
        try {
            if (!texto.equals("")) {
                PrintWriter pw = new PrintWriter("test\\db.txt");
                pw.print(texto);
                pw.close();
                JOptionPane.showMessageDialog(null, 
                    "Archivo cargado exitosamente",
                    "", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception err){
            JOptionPane.showMessageDialog(null, 
                    "Error en la carga del archivo",
                    "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Guarda el contenido del grafo en un archivo de texto.
     *
     * @author G. Angelo, S. Estefania y S. Jose
     * @param grafo Se guarada el contenido del grafo en un archivo de texto.
     * @version: 27/10/2023
     */
    public void guardar_archivo(Grafo grafo) {
        try {
            Component FileChooser = null;
            String nombre = "";
            JFileChooser file = new JFileChooser();
            file.showSaveDialog(FileChooser);
            File guarda = file.getSelectedFile();
            String usuarios = "usuarios\n";
            String relaciones = "relaciones\n";
            String output = "";

            if (guarda != null) {
                
                if (!grafo.getListaNodos().esVacio()) {
                    Nodo aux = grafo.getListaNodos().getpPrim();
                    for (int i = 0; i < grafo.getListaNodos().getiN(); i++) {
                        usuarios += aux.gettInfo() + "\n";
                        Nodo ady = aux.getAdyacentes().getpPrim();
                        for (int n = 0; n < aux.getAdyacentes().getiN(); n++) {
                            relaciones += aux.gettInfo() + ", " + ady.gettInfo() + "\n";
                            ady = ady.getpSig();
                        }
                        aux = aux.getpSig();
                    }
                    output = usuarios + relaciones;

                }
                try {
                    PrintWriter pw = new PrintWriter("test\\db.txt");
                    pw.print(output);
                    pw.close();
                    System.out.println("Guardado exitoso");
                } catch (Exception err) {
                    System.out.println("Error al guardar");
                }
                
                /*guardamos el archivo y le damos el formato directamente,
                * si queremos que se guarde en formato doc lo definimos como .doc*/
                FileWriter save = new FileWriter(guarda + ".txt");
                save.write(output);
                save.close();

                JOptionPane.showMessageDialog(FileChooser,
                        "El archivo se ha guardado exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Solicita y valida un nombre de usuario ingresado en un campo de texto.
     *
     * @author G. Angelo, S. Estefania y S. Jose
     * @param texto El campo de texto que contiene el nombre de usuario
     * ingresado.
     * @return El nombre de usuario validado o una cadena vacía si es inválido.
     * @version: 27/10/2023
     */
    public String solicitar_usuario(JTextField texto){
        String textoTemp = texto.getText();
        if (!"".equals(textoTemp)){
            char pChar = textoTemp.charAt(0);
            String textoChar = String.valueOf(pChar);
            if (!"@".equals(textoChar)){
                JOptionPane.showMessageDialog(null,
                        "Nombre de usuario invalido. El usuario debe comenzar con '@'",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return "";
            }
        }
        return textoTemp;
    }
    
    
}
