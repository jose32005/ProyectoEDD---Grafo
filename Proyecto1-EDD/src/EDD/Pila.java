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
public class Pila<T> {

    private Nodo<T> cima;
    private int iN;

    // Constructor
    public Pila() {
        this.cima = null;
        this.iN = 0;
    }

    // Apilar un elemento en la cima de la pila.
    public void apilar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cima == null) {
            cima = nuevoNodo;
        } else {
            nuevoNodo.setpSig(cima);
            cima = nuevoNodo;
        }
        iN++;
    }

    // Desapilar un elemento de la cima de la pila.
    public T desapilar() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        T datoRetirado = cima.gettInfo();
        cima = cima.getpSig();
        iN--;
        return datoRetirado;
    }

    // Observar el elemento de la cima sin retirarlo.
    public T cima() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return cima.gettInfo();
    }

    // Verificar si la pila está vacía.
    public boolean estaVacia() {
        return cima == null;
    }

    // Obtener el tamaño de la pila.
    public int getSize() {
        return iN;
    }

    // Vaciar la pila.
    public void vaciar() {
        cima = null;
        iN = 0;
    }

}
