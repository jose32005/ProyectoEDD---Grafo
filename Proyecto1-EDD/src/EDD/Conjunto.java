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

    public void agregar(T elemento) {
        if (!contiene(elemento)) {
            lista.insertar(elemento);
        }
    }

    public boolean contiene(T elemento) {
        return lista.obtenerNodo(elemento) != null;
    }
}


