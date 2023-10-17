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
        Nodo<T> nodoExistente = listaNodos.obtenerNodo((String) info);
        if (nodoExistente != null) {
            System.out.println("Ya existe un nodo bajo el usuario: " + info + ". No se puede agregar otro.");
        } else {
            listaNodos.insertar(info);
            numVertices++;
        }
    }

    // Método para eliminar un nodo al grafo
    public void eliminarNodo(T info) {
        Nodo<T> nodoAEliminar = listaNodos.obtenerNodo(info);

        if (nodoAEliminar == null) {
            System.out.println("El nodo a eliminar no existe en el grafo");
            return;
        }

        // Desconectar el nodo a eliminar de todos los otros nodos
        Nodo<T> actual = listaNodos.getpPrim();
        while (actual != null) {
            desconectarNodos(actual.gettInfo(), info);
            actual = actual.getpSig();
        }

        // Eliminar el nodo de la lista principal de nodos
        listaNodos.eliminar(info);
        numVertices--;
    }

    // Método para conectar dos nodos en el grafo (agregar arista entre dos nodos)
    public void conectarNodos(T infoOrigen, T infoDestino) {

        if (infoOrigen.equals(infoDestino)) {
            System.out.println("El usuario " + infoOrigen + " no se puede conectar a sí mismo");
            return;
        }
        Nodo<T> nodoOrigen = listaNodos.obtenerNodo(infoOrigen);
        Nodo<T> nodoDestino = listaNodos.obtenerNodo(infoDestino);

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
        Nodo<T> nodoOrigen = listaNodos.obtenerNodo(infoOrigen);
        Nodo<T> nodoDestino = listaNodos.obtenerNodo(infoDestino);

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
        System.out.println("El grafo contiene " + numVertices + " vértices: \n");

        Nodo<T> actual = listaNodos.getpPrim();
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
        return (Nodo<T>) listaNodos.obtenerNodo(info);
    }

    //Paso 1: Crear el set
    public Pila<Nodo<T>> obtenerOrden() {
        Conjunto<Nodo<T>> visitados = new Conjunto<>();
        Pila<Nodo<T>> pila = new Pila<>();

        Nodo<T> nodoActual = (Nodo<T>) listaNodos.primero();
        while (nodoActual != null) {
            if (!visitados.contiene(nodoActual)) {
                llenarOrden(nodoActual, visitados, (Pila<T>) pila);
            }
            nodoActual = nodoActual.getpSig();
        }

        return pila;
    }

    public void llenarOrden(Nodo<T> nodo, Conjunto<Nodo<T>> visitados, Pila<T> pila) {
        visitados.agregar((Nodo<T>) nodo); // Marcar el nodo actual como visitado

        // Recorrer los nodos adyacentes
        Nodo<T> adyacenteActual = nodo.getAdyacentes().primero();
        while (adyacenteActual != null) {
            if (!visitados.contiene((Nodo<T>) adyacenteActual)) {
                llenarOrden(adyacenteActual, visitados, pila);
            }
            adyacenteActual = adyacenteActual.getpSig();
        }

        // Insertar el nodo actual a la pila
        pila.insertar(nodo.gettInfo());
    }

    // Paso 2: Obtener el grafo transpuesto
    private Grafo<T> Transpuesto() {
        Grafo<T> transpuesto = new Grafo<>();

        Nodo<T> nodoActual = (Nodo<T>) listaNodos.primero();
        while (nodoActual != null) {
            Nodo<T> nuevoNodo = new Nodo<>(nodoActual.gettInfo());
            transpuesto.agregarNodo(nuevoNodo.gettInfo());

            Nodo<T> adyActual = nodoActual.getAdyacentes().primero();
            while (adyActual != null) {
                transpuesto.conectarNodos(adyActual.gettInfo(), nodoActual.gettInfo());
                adyActual = adyActual.getpSig();
            }

            nodoActual = nodoActual.getpSig();
        }

        return transpuesto;
    }

    // Paso 3: DFS en el grafo transpuesto en orden descendente de finalización
    public void imprimirSCCs() {
        Pila<Nodo<T>> pila = obtenerOrden();
        Grafo<T> grafoTranspuesto = Transpuesto();

        Conjunto<Nodo<T>> visitados = new Conjunto<>();

        while (!pila.pilaVacia()) {
            T elementoActual = (T) pila.eliminar();
            Nodo<T> nodo = obtenerNodo(elementoActual);

            if (!visitados.contiene(nodo)) {
                DFS(grafoTranspuesto.obtenerNodo(nodo.gettInfo()), visitados);
                System.out.println("FIN");
            }
        }
    }

    public void DFS(Nodo<T> nodo, Conjunto<Nodo<T>> visitados) {
        visitados.agregar(nodo);
        System.out.print(nodo.gettInfo() + ", ");

        Nodo<T> adyacenteActual = nodo.getAdyacentes().primero();
        while (adyacenteActual != null) {
            if (!visitados.contiene(adyacenteActual)) {
                DFS(adyacenteActual, visitados);
            }
            adyacenteActual = adyacenteActual.getpSig();
        }

    }

}
