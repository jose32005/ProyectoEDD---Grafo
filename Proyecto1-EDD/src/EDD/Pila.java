package EDD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author G. Angelo, S. Estefania y S. Jose 
 * @param <T>
 */
public class Pila<T> {

    private Nodo<T> cima;
    private int iN;

    /**
     * Crea una nueva pila vacía. Inicializa una nueva pila sin elementos.
     *
     * @author G. Angelo, S. Estefania y S. Jose 
     * @version: 27/10/2023
     */
    public Pila() {
        this.cima = null;
        this.iN = 0;
    }

    /**
     * Apila (agrega) un nuevo elemento en la cima de la pila.
     *
     * @author G. Angelo, S. Estefania y S. Jose
     * @param dato El elemento que se va a apilar en la cima de la pila.
     * @version: 27/10/2023
     */
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

    /**
     * Desapila (quita) un elemento en la cima de la pila.
     *
     * @author G. Angelo, S. Estefania y S. Jose
     * @return T Se extrae el elemento que se encuentra en la cima de la lista.
     * @version: 27/10/2023
     */
    public T desapilar() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        T datoRetirado = cima.gettInfo();
        cima = cima.getpSig();
        iN--;
        return datoRetirado;
    }

    /**
     * Accede al nodo que se encuentra en la cima de la pila.
     *
     * @author G. Angelo, S. Estefania y S. Jose
     * @return T
     * @version: 27/10/2023
     */
    public T cima() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return cima.gettInfo();
    }

    /**
     * Verifica si la pila está vacia.
     *
     * @author G. Angelo, S. Estefania y S. Jose
     * @return boolean true si la pila está vacia y false si en la pila existe
     * al menos un elemento.
     * @version: 27/10/2023
     */
    public boolean estaVacia() {
        return cima == null;
    }

    /**
     * Imprime los elementos de la pila sin modificar el orden. Este método
     * imprime los elementos de la pila en el mismo orden en que se encuentran,
     * sin modificar la pila original. Utiliza una pila temporal para almacenar
     * los elementos antes de imprimirlos.
     *
     * @author G. Angelo, S. Estefania y S. Jose
     * @version: 27/10/2023
     */
    public void imprimirPila() {
        Pila<T> pilaTemporal = new Pila<>();
        while (!this.estaVacia()) {
            T elemento = this.desapilar();
            System.out.print(elemento + " ");
            pilaTemporal.apilar(elemento);
        }
        System.out.println(); // Para agregar una nueva línea al final

        // Restaurar la pila original
        while (!pilaTemporal.estaVacia()) {
            this.apilar(pilaTemporal.desapilar());
        }
    }

    /**
     * Se obtiene la cantidad de elementos que se encuentran en la pila.
     *
     * @author G. Angelo, S. Estefania y S. Jose
     * @version: 27/10/2023
     */
    public int getSize() {
        return iN;
    }

    /**
     * Se vacila la pila de los elementos y se establece la cantidad de
     * elementos a 0.
     *
     * @author G. Angelo, S. Estefania y S. Jose
     * @version: 27/10/2023
     */
    public void vaciar() {
        cima = null;
        iN = 0;
    }

}
