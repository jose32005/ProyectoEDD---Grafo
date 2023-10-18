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
public class Grafo<T> {

    private Lista<T> listaNodos; // Lista principal de nodos
    private int numVertices;

    //Constructor
    public Grafo() {
        this.numVertices = 0;
        this.listaNodos = new Lista<>();
    }

    //Metodos
    // Método para agregar un nodo al grafo
    public void agregarNodo(T info) {
        Nodo<T> nodoExistente = getListaNodos().obtenerNodo((String) info);
        if (nodoExistente != null) {
            System.out.println("Ya existe un nodo bajo el usuario: " + info + ". No se puede agregar otro.");
        } else {
            getListaNodos().insertar(info);
            setNumVertices(getNumVertices() + 1);
        }
    }

    // Método para eliminar un nodo al grafo
    public void eliminarNodo(T info) {
        Nodo<T> nodoAEliminar = getListaNodos().obtenerNodo(info);

        if (nodoAEliminar == null) {
            System.out.println("El nodo a eliminar no existe en el grafo");
            return;
        }

        // Desconectar el nodo a eliminar de todos los otros nodos
        Nodo<T> actual = getListaNodos().getpPrim();
        while (actual != null) {
            desconectarNodos(actual.gettInfo(), info);
            actual = actual.getpSig();
        }

        // Eliminar el nodo de la lista principal de nodos
        getListaNodos().eliminar(info);
        setNumVertices(getNumVertices() - 1);
    }

    // Método para conectar dos nodos en el grafo (agregar arista entre dos nodos)
    public void conectarNodos(T infoOrigen, T infoDestino) {

        if (infoOrigen.equals(infoDestino)) {
            System.out.println("El usuario " + infoOrigen + " no se puede conectar a sí mismo");
            return;
        }
        Nodo<T> nodoOrigen = getListaNodos().obtenerNodo(infoOrigen);
        Nodo<T> nodoDestino = getListaNodos().obtenerNodo(infoDestino);

        if (nodoOrigen == null || nodoDestino == null) {
            System.out.println("Uno o ambos nodos no existen en el grafo");
            return;
        }

        Nodo<T> adyacente = nodoOrigen.getAdyacentes().obtenerNodo(infoDestino);
        if (adyacente != null) {
            System.out.println("Los nodos " + infoOrigen + " y " + infoDestino + " ya están conectados");
            return;
        }

        nodoOrigen.getAdyacentes().insertar(infoDestino);
    }

    // Método para desconectar dos nodos en el grafo (eliminar arista entre dos nodos)
    public void desconectarNodos(T infoOrigen, T infoDestino) {
        Nodo<T> nodoOrigen = getListaNodos().obtenerNodo(infoOrigen);
        Nodo<T> nodoDestino = getListaNodos().obtenerNodo(infoDestino);

        if (nodoOrigen == null || nodoDestino == null) {
            System.out.println("Uno o ambos nodos no existen en el grafo");
            return;
        }

        Nodo<T> adyacente = nodoOrigen.getAdyacentes().obtenerNodo(infoDestino);
        if (adyacente == null) {
            System.out.println("Los nodos " + infoOrigen + " y " + infoDestino + " no están conectados");
            return;
        }

        nodoOrigen.getAdyacentes().eliminar(infoDestino);
    }

    // Metodos adaptados
    //Metodo que imprime el grafo
    public void imprimirGrafo() {
        System.out.println("El grafo contiene " + getNumVertices() + " vértices: \n");

        Nodo<T> actual = getListaNodos().getpPrim();
        while (actual != null) {
            System.out.print("Vértice " + actual.gettInfo() + ": ");
            escribir(actual.getAdyacentes());
            actual = actual.getpSig();
        }
    }

    //Metodo auxiliar
    public void escribir(Lista<T> lista) {
        Nodo<T> pAux = lista.getpPrim();
        while (pAux != null) {
            System.out.print(pAux.gettInfo() + ", ");
            pAux = pAux.getpSig();
        }
        System.out.println("FIN");
    }

    public Nodo<T> obtenerNodo(T info) {
        return (Nodo<T>) getListaNodos().obtenerNodo(info);
    }

    
    
    
    // Algoritmo de Kosaraju
// Crear el orden
    public Pila<T> obtenerOrden() {
        Conjunto<T> visitados = new Conjunto<>();
        Pila<T> pila = new Pila<>();
        Nodo<T> nodoActual = getListaNodos().primero();

        while (nodoActual != null) {
            if (!visitados.contiene(nodoActual.gettInfo())) {
                llenarOrden(nodoActual, visitados, pila);
            }
            nodoActual = nodoActual.getpSig();
        }

        return pila;
    }

// Llenar la pila
    public void llenarOrden(Nodo<T> nodo, Conjunto<T> visitados, Pila<T> pila) {
        visitados.insertar(nodo.gettInfo());

        Nodo<T> adyacenteActual = nodo.getAdyacentes().primero();
        while (adyacenteActual != null) {
            if (!visitados.contiene(adyacenteActual.gettInfo())) {
                llenarOrden(adyacenteActual, visitados, pila);
            }
            adyacenteActual = adyacenteActual.getpSig();
        }
        pila.apilar(nodo.gettInfo());
    }

// Creacion del grafo transpuesto 
    public Grafo<T> Transpuesto() {
        Grafo<T> transpuesto = new Grafo<>();

        // Agregamos todos los nodos al grafo transpuesto
        Nodo<T> nodoActual = getListaNodos().primero();
        while (nodoActual != null) {
            transpuesto.agregarNodo(nodoActual.gettInfo());
            nodoActual = nodoActual.getpSig();
        }

        // Invertimos las aristas
        nodoActual = getListaNodos().primero();
        while (nodoActual != null) {
            Nodo<T> adyActual = nodoActual.getAdyacentes().primero();
            while (adyActual != null) {
                transpuesto.conectarNodos(adyActual.gettInfo(), nodoActual.gettInfo());
                adyActual = adyActual.getpSig();
            }
            nodoActual = nodoActual.getpSig();
        }

        return transpuesto;
    }

// Imprimir los sccs
    public void imprimirSCCs() {
        Pila<T> pila = obtenerOrden();
        Grafo<T> grafoTranspuesto = Transpuesto();
        Conjunto<T> visitados = new Conjunto<>();

        while (!pila.estaVacia()) {
            T elementoActual = pila.desapilar();
            Nodo<T> nodo = obtenerNodo(elementoActual);

            if (!visitados.contiene(nodo.gettInfo())) {
                Pila<T> componente = new Pila<>();
                llenarOrden(grafoTranspuesto.obtenerNodo(nodo.gettInfo()), visitados, componente);
                System.out.print("[ ");
                while (!componente.estaVacia()) {
                    System.out.print(componente.desapilar() + " ");
                }
                System.out.println("]");
            }
        }
    }
    
    
    
    // Gets and Sets

    /**
     * @return the listaNodos
     */
    public Lista<T> getListaNodos() {
        return listaNodos;
    }

    /**
     * @param listaNodos the listaNodos to set
     */
    public void setListaNodos(Lista<T> listaNodos) {
        this.listaNodos = listaNodos;
    }

    /**
     * @return the numVertices
     */
    public int getNumVertices() {
        return numVertices;
    }

    /**
     * @param numVertices the numVertices to set
     */
    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }
    
    

}
