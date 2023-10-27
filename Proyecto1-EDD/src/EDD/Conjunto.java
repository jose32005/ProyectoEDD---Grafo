/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author G. Angelo, S. Estefania y S. Jose
 * @param <T>
 */

public class Conjunto<T> {

    private Lista<T> lista;
    /**
     * Crea un nuevo conjunto vacío. Inicializa un nuevo conjunto sin elementos.
     * El conjunto se utiliza para almacenar elementos únicos y no duplicados.
     *
     * @version: 27/10/2023
     */
    public Conjunto() {
        this.lista = new Lista<>();
    }

    /**
     * Se toma un elemento de tipo T y se inserta en el conjunto si y solo si el
     * elemento no está contenido previamente en la estructura.
     *
     * @version: 27/10/2023
     * @param info
     */
    public void insertar(T info) {
        if (!contiene(info)) {
            lista.insertar(info);
        }
    }
    /**
     * Verifica si el conjunto contiene un elemento específico.
     *
     * @param info El elemento que se busca en el conjunto.
     * @return true si el elemento está presente en el conjunto, de lo
     * contrario, es false.
     * @version: 27/10/2023
     */
    public boolean contiene(T info) {
        Nodo<T> nodo = lista.obtenerNodo(info);
        return nodo != null;
    }
    
    

    /**
     * @return the lista
     * @version: 27/10/2023
     */
    public Lista<T> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     * @version: 27/10/2023
     */
    public void setLista(Lista<T> lista) {
        this.lista = lista;
    }
    
    
}


