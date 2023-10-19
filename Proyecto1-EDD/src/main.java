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
        
        miGrafo.agregarNodo("@0");
        miGrafo.agregarNodo("@1");
        miGrafo.agregarNodo("@2");
        miGrafo.agregarNodo("@3");
        miGrafo.agregarNodo("@4");
        //miGrafo.agregarNodo("@5");
        //miGrafo.agregarNodo("@6");
        //miGrafo.agregarNodo("@7");
        miGrafo.conectarNodos("@0", "@1");
        miGrafo.conectarNodos("@1", "@2");
        miGrafo.conectarNodos("@1", "@0");
        miGrafo.conectarNodos("@1", "@3");
        miGrafo.conectarNodos("@1", "@4");
        miGrafo.conectarNodos("@3", "@1");
        miGrafo.conectarNodos("@4", "@2");
        miGrafo.conectarNodos("@4", "@3");
        //miGrafo.conectarNodos("@4", "@2");
        //miGrafo.conectarNodos("@6", "@4");
        //miGrafo.conectarNodos("@6", "@7");
        

        
        //miGrafo.desconectarNodos("@p", "@j");
        //miGrafo.desconectarNodos("@e", "@j");

        
        //miGrafo.imprimirGrafo();
        //miGrafo.imprimirSCCs();
        miGrafo.Traspuesto().imprimirGrafo();
        miGrafo.obtenerSCCs();
        //miGrafo.imprimirSCCs();
  
    }
    
    
}
