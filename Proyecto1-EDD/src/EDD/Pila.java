package EDD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 * @param <T>
 */
public class Pila<T>{
    
    private Nodo cima;
    
    public Pila(){
        cima = null;
    }
    
    public boolean pilaVacia(){
        return cima == null;
    }
    
    public void insertar(Object tInfo){
        Nodo nuevo = new Nodo(tInfo);
        nuevo.setpSig(cima);
        setCima(nuevo);
    }
    
    public Object eliminar(){
        if (pilaVacia()) {
            System.out.println("La pila se encuentra vacia");
        }
        Object aux = getCima().gettInfo();
        setCima(getCima().getpSig());
        return aux;
        
    }
    
    public void mostrarPila(){
        if (!this.pilaVacia()) {
            Nodo aux = cima;
            while (cima != null) {                
                System.out.println(cima.gettInfo());
            }
        }
    }

    public Nodo getCima() {
        return cima;
    }

    public void setCima(Nodo cima) {
        this.cima = cima;
    }
    
    
    
}
