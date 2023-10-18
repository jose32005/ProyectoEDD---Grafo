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
        
        miGrafo.agregarNodo("@j");
        miGrafo.agregarNodo("@a");
        miGrafo.agregarNodo("@e");
        miGrafo.agregarNodo("@g");
        miGrafo.agregarNodo("@p");
        miGrafo.agregarNodo("@s");
        miGrafo.agregarNodo("@m");
        miGrafo.agregarNodo("@r");
        miGrafo.conectarNodos("@j", "@a");
        miGrafo.conectarNodos("@j", "@e");
        miGrafo.conectarNodos("@a", "@j");
        miGrafo.conectarNodos("@p", "@j");
        miGrafo.conectarNodos("@e", "@m");
        miGrafo.conectarNodos("@e", "@s");
        miGrafo.conectarNodos("@s", "@r");
        miGrafo.conectarNodos("@r", "@s");
        //miGrafo.desconectarNodos("@p", "@j");
        //miGrafo.desconectarNodos("@e", "@j");

        
        miGrafo.imprimirGrafo();
        miGrafo.imprimirSCCs();
        miGrafo.Transpuesto().imprimirGrafo();
        //miGrafo.imprimirSCCs();
  
    }
    
    
}
