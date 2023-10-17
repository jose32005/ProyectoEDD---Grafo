import EDD.Grafo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author PC
 */
public class main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Grafo miGrafo = new Grafo();
        
        miGrafo.agregarNodo("@jose");
        miGrafo.agregarNodo("@angelo");
        miGrafo.agregarNodo("@estefania");
        miGrafo.agregarNodo("@guillen");
        miGrafo.conectarNodos("@jose", "@guillen");
        miGrafo.conectarNodos("@jose", "@angelo");
        miGrafo.conectarNodos("@jose", "@angelo");
        miGrafo.conectarNodos("@angelo", "@estefania");
        miGrafo.conectarNodos("@angelo", "@jose");
        miGrafo.conectarNodos("@estefania", "@guillen");
        miGrafo.conectarNodos("@estefania", "@jose");
        miGrafo.conectarNodos("@jose", "@jose");
        miGrafo.desconectarNodos("@estefania", "@jose");
        miGrafo.desconectarNodos("@estefania", "@jose");

        
        miGrafo.imprimirGrafo();
        miGrafo.imprimirSCCs();
        //miGrafo.transpuesto().imprimirGrafo();
        //miGrafo.imprimirSCCs();
  
    }
    
}
