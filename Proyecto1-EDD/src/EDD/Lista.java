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

public class Lista<T> {

    //Definicion de atributos de la clase lista
    private Nodo<T> pPrim;
    private int iN;

    /**
     * Crea una nueva lista vacía. Inicializa una nueva lista sin elementos.
     * @author G. Angelo, S. Estefania y S. Jose.
     * @version: 27/10/2023
     */
    public Lista() {
        this.iN = 0;
        this.pPrim = null;
    }

    /**
     * Verifica si la lista está vacia.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @return boolean, true si la lista está vacia y false si en la lista
     * existe al menos un elemento.
     * @version: 27/10/2023
     */
    public boolean esVacio() {
        return getpPrim() == null;
    }

    /**
     * Devuelve el primer nodo de la lista.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @return Nodo
     * @version: 27/10/2023
     */
    public Nodo<T> primero() {
        return getpPrim();
    }

    /**
     * Avanza al siguiente nodo en la lista, dado un nodo como referencia.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param pValor El nodo de referencia a partir del cual se avanzará al
     * siguiente nodo.
     * @version: 27/10/2023
     */
    public void proximo(Nodo<T> pValor) {
        if (pValor != null && pValor.getpSig() != null) {
            pValor = pValor.getpSig();
        }
    }

    /**
     * Accede a la información del nodo, dado un nodo como referencia.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param pValor El nodo de referencia a partir del se obtendrá la
     * información.
     * @return T Siendo T la información que guarda el nodo.
     * @version: 27/10/2023
     */
    public T acceder(Nodo<T> pValor) {
        if (pValor != null) {
            return pValor.gettInfo();
        }
        return null;
    }

    /**
     * Inserta un nuevo elemento al final de la lista.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param nuevoElemento El elemento que se va a agregar al final de la
     * lista.
     * @version: 27/10/2023
     */
    public void insertar(T nuevoElemento) {
        Nodo<T> nuevoNodo = new Nodo<>(nuevoElemento);
        if (this.esVacio()) {
            this.pPrim = nuevoNodo;
        } else {
            Nodo<T> pAux = this.pPrim;
            while (pAux.getpSig() != null) {
                pAux = pAux.getpSig();
            }
            pAux.setpSig(nuevoNodo);
        }
        this.iN++;
    }

    /**
     * Elimina un elemento específico de la lista, si existe.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param elemento El elemento que se va a eliminar de la lista.
     * @version: 27/10/2023
     */
    public void eliminar(T elemento) {
        if (pPrim == null || elemento == null) {
            return;
        }

        if (pPrim.gettInfo().equals(elemento)) {
            pPrim = pPrim.getpSig();
            iN--;
            return;
        }

        Nodo<T> actual = pPrim;
        while (actual.getpSig() != null && !actual.getpSig().gettInfo().equals(elemento)) {
            actual = actual.getpSig();
        }

        if (actual.getpSig() != null) {
            actual.setpSig(actual.getpSig().getpSig());
            iN--;
        }
    }

    /**
     * Accede a la cantidad de nodos que se encuentran en la lista.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @return int
     * @version: 27/10/2023
     */
    public int tamano() {
        return getiN();
    }

    /**
     * Busca y devuelve el nodo que contiene un elemento específico en la lista.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param elem El elemento que se va a buscar en la lista.
     * @return El nodo que contiene el elemento o null si el elemento no se
     * encuentra en la lista.
     * @version: 27/10/2023
     */
    public Nodo<T> obtenerNodo(Object elem) {
        Nodo<T> actual = this.pPrim;
        while (actual != null) {
            if (actual.gettInfo().equals(elem)) {
                return actual;
            }
            actual = actual.getpSig();
        }
        return null;
    }

    // Gets & Sets
    /**
     * @author G. Angelo, S. Estefania y S. Jose.
     * @return the pPrim
     * @version: 27/10/2023
     */
    public Nodo<T> getpPrim() {
        return pPrim;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param pPrim the pPrim to set
     * @version: 27/10/2023
     */
    public void setpPrim(Nodo<T> pPrim) {
        this.pPrim = pPrim;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose.
     * @return the iN
     * @version: 27/10/2023
     */
    public int getiN() {
        return iN;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param iN the iN to set
     * @version: 27/10/2023
     */
    public void setiN(int iN) {
        this.iN = iN;
    }

}
