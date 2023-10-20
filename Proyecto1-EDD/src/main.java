import EDD.Grafo;
import Extras.Funciones;

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
        
        Funciones func = new Funciones();
        Grafo miGrafo = func.leer_txt();
        //miGrafo.agregarNodo("@jose");
        //miGrafo.conectarNodos("@jose", "@pepe");
        //miGrafo.conectarNodos("@pepe", "@jose");
        //miGrafo.conectarNodos("@jose", "@mazinger");
        //miGrafo.eliminarNodo("@jose");
        //func.escribir_txt(miGrafo);
        
        
        
        
        

        
        //miGrafo.imprimirGrafo();
        //miGrafo.Traspuesto().imprimirGrafo();
        miGrafo.obtenerSCCs();
        
  
    }
    
    
}
