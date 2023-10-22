package Extras;

import EDD.Grafo;
import EDD.Lista;
import EDD.Nodo;
import Intefaces.FileChooser;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joses
 *
 */
public class Funciones {

    public void escribir_txt(Grafo grafo) {
        String usuarios = "usuarios\n";
        String relaciones = "relaciones\n";
        String output = "";
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
            PrintWriter pw = new PrintWriter("test\\usuarios.txt");
            pw.print(output);
            pw.close();
            System.out.println("Guardado exitoso");
        } catch (Exception err) {
            System.out.println("Error al guardar");
        }

    }

    //Lee el db.txt actual
    public Grafo llenar_grafo() {
        Grafo grafo = new Grafo();
        String line;
        String usuarios_txt = "";
        String path = "test\\db.txt";
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                FileReader fr = new FileReader(file);
                try (BufferedReader br = new BufferedReader(fr)) {
                    while ((line = br.readLine()) != null) {
                        if (!line.isEmpty()) {
                            usuarios_txt += line + "\n";
                        }
                    }
                    if (!"".equals(usuarios_txt)) {
                        String[] usuarios_split = usuarios_txt.split("\n");
                        boolean add_user = true;
                        for (int i = 1; i < usuarios_split.length; i++) {
                            String nuevo_elemento = usuarios_split[i];
                            if ("relaciones".equals(nuevo_elemento)) {
                                add_user = false;
                            }
                            if (add_user == true) {
                                grafo.agregarNodo(nuevo_elemento);
                            } else if ((add_user == false) && !"relaciones".equals(nuevo_elemento)) {
                                String[] nueva_relacion = nuevo_elemento.split(", ");
                                grafo.conectarNodos(nueva_relacion[0], nueva_relacion[1]);
                            }

                        }
                        System.out.println("Lectura exitosa");
                    }
                }

            }
        } catch (IOException err) {
            System.out.println("Error al leer archivo");
        }
        return grafo;
    }

    // JFileChooser
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
                System.out.println("Guardado exitoso");
            }
        } catch (Exception err) {
            System.out.println("Error al guardar");
        }
    }

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
                        "El archivo se a guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
