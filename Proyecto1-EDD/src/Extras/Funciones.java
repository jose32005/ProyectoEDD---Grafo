package Extras;

import EDD.Grafo;
import EDD.Lista;
import EDD.Nodo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

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

    public void escribir_txt(Grafo grafo){
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

    // Enviar siempre un grafo vacio
    public Grafo leer_txt(){
        Grafo grafo = new Grafo();
        String line;
        String usuarios_txt = "";
        String path = "test\\usuarios.txt";
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
                    }
                }
                System.out.println("Lectura exitosa");
            }
        }catch(IOException err){
            System.out.println("Error al leer archivo");
        }
        return grafo;
    }
    
    
}
