/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author joses
 * @param <T>
 */

public class Conjunto<T> {

    private Lista<T> lista;

    public Conjunto() {
        this.lista = new Lista<>();
    }

    
    public void insertar(T info) {
        if (!contiene(info)) {
            lista.insertar(info);
        }
    }

    public boolean contiene(T info) {
        Nodo<T> nodo = lista.obtenerNodo(info);
        return nodo != null;
    }
    
    

    /**
     * @return the lista
     */
    public Lista<T> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(Lista<T> lista) {
        this.lista = lista;
    }
    
    
}


