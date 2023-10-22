/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import java.awt.Color;
import java.util.Random;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

/**
 *
 * @author G. Angelo, S. Estefania y S. Jose
 * @param <T>
 */
public class Grafo<T> {

    private Lista<T> listaNodos; // Lista principal de nodos
    private int numVertices;
    public Graph graph;

    //Constructor
    public Grafo() {
        this.numVertices = 0;
        this.listaNodos = new Lista<>();
        this.graph = new MultiGraph("Prueba 1");

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
        System.out.println("Nodo Eliminado con Exito");
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

        if (nodoOrigen.getAdyacentes().obtenerNodo(infoDestino) != null) {
            nodoOrigen.getAdyacentes().eliminar(infoDestino);
            System.out.println("Los nodos " + infoOrigen + " y " + infoDestino + " fueron desconectados con exito");
        }

    }

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

    public Graph llenarGraph() {
        System.setProperty("org.graphstream.ui", "swing");
        this.graph.setAttribute("ui.stylesheet", "graph { fill-color: gainsboro; }");
        this.graph.setAttribute("ui.quality");
        this.graph.setAttribute("ui.antialias");

        Nodo<T> pAux = listaNodos.getpPrim();

        //Se agregan todos los nodos al graph
        while (pAux != null) {
            this.graph.addNode((String) pAux.gettInfo());
            this.graph.getNode((String) pAux.gettInfo()).setAttribute("ui.label", (String) pAux.gettInfo());

            graph.getNode((String) pAux.gettInfo()).setAttribute("ui.style", "shape: circle; size: 30;");

            pAux = pAux.getpSig();
        }

        pAux = listaNodos.getpPrim();
        while (pAux != null) {

            Nodo<T> pAuxAdy = pAux.getAdyacentes().getpPrim();

            while (pAuxAdy != null) {
                //Generamos un color aleatorio para las listas de adyacencia de un nodo especifico
                Color randomColor = getRandomColor();
                this.graph.addEdge(pAux.gettInfo() + "" + pAuxAdy.gettInfo(), (String) pAux.gettInfo(), (String) pAuxAdy.gettInfo(), true);

                graph.getNode((String) pAux.gettInfo()).setAttribute("ui.style", "fill-color: rgb(" + randomColor.getRed() + "," + randomColor.getGreen() + "," + randomColor.getBlue() + "); shape: circle; size: 30;");

                pAuxAdy = pAuxAdy.getpSig();
            }
            pAux = pAux.getpSig();
        }
//        graph.display();
        return graph;
    }

    public static Color getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    // Algoritmo de Kosaraju
// Creacion del grafo traspuesto 
    public Grafo<T> Traspuesto() {
        Grafo<T> transpuesto = new Grafo<>();

        // Agregamos todos los nodos al grafo transpuesto
        Nodo<T> nodoActual = this.listaNodos.getpPrim();
        while (nodoActual != null) {
            transpuesto.agregarNodo(nodoActual.gettInfo());
            nodoActual = nodoActual.getpSig();
        }

        // Invertimos las aristas
        nodoActual = this.listaNodos.getpPrim();
        while (nodoActual != null) {
            Nodo<T> adyActual = nodoActual.getAdyacentes().getpPrim();
            while (adyActual != null) {
                transpuesto.conectarNodos(adyActual.gettInfo(), nodoActual.gettInfo());
                adyActual = adyActual.getpSig();
            }
            nodoActual = nodoActual.getpSig();
        }

        return transpuesto;
    }

    // Imprimir los sccs
    public Graph obtenerSCCs() {
        Graph graphPrueba = this.llenarGraph();
        graphPrueba.setAttribute("ui.stylesheet", "graph { fill-color: oldlace; }");
        System.setProperty("org.graphstream.ui", "swing");
        graphPrueba.setAttribute("ui.quality");
        graphPrueba.setAttribute("ui.antialias");

        Grafo<T> traspuesto = this.Traspuesto();
        Pila<T> pila = new Pila<>();
        Conjunto<T> visitados = new Conjunto<>();

        // Llenar la pila y el conjunto de visitados
        Nodo<T> nodoActual = this.listaNodos.getpPrim();
        while (nodoActual
                != null) {
            if (!visitados.contiene(nodoActual.gettInfo())) {
                llenarOrden(nodoActual, visitados, pila);
            }
            nodoActual = nodoActual.getpSig();
        }

        // Limpiar el conjunto de nodos visitados para el siguiente DFS
        visitados = new Conjunto<>();

        // Procesar nodos en el orden determinado por la pila
        while (!pila.estaVacia()) {
            T nodoInfo = pila.desapilar();
            Color randomColor = getRandomColor();
            if (!visitados.contiene(nodoInfo)) {
                Lista<T> sccActual = new Lista();
                llenarSCC(traspuesto.buscarNodo(nodoInfo), visitados, sccActual, traspuesto);

                // Imprimir el SCC
                Nodo<T> nodoSCC = sccActual.getpPrim();
                //Añadir elementos al scc
                while (nodoSCC != null) {
                    graphPrueba.getNode((String) nodoSCC.gettInfo()).setAttribute("ui.style", "fill-color: rgb(" + randomColor.getRed() + "," + randomColor.getGreen() + "," + randomColor.getBlue() + "); shape: circle; size: 40;");
                    System.out.print(nodoSCC.gettInfo());
                    nodoSCC = nodoSCC.getpSig();
                    if (nodoSCC != null) {
                        System.out.print(", ");
                    }
                }
                System.out.println();

            }
        }
        return graphPrueba;

    }

    //Llena los SCC con DFS
    public void llenarSCC(Nodo<T> nodo, Conjunto<T> visitados, Lista<T> sccActual, Grafo<T> traspuesto) {
        visitados.insertar(nodo.gettInfo());
        sccActual.insertar(nodo.gettInfo());
        Lista<T> adyacentes = nodo.getAdyacentes();
        Nodo<T> adyacenteActual = adyacentes.getpPrim();

        while (adyacenteActual != null) {
            Nodo<T> nodoAdyacente = traspuesto.buscarNodo(adyacenteActual.gettInfo()); // Buscar el nodo real basado en la información del nodo adyacente

            if (!visitados.contiene(nodoAdyacente.gettInfo())) {
                llenarSCC(nodoAdyacente, visitados, sccActual, traspuesto);
            }

            adyacenteActual = adyacenteActual.getpSig();
        }
    }

    //Crea el orden del la Pila y el conjunto inicial
    public void llenarOrden(Nodo<T> nodo, Conjunto<T> visitados, Pila<T> pila) {
        visitados.insertar(nodo.gettInfo());
        Lista<T> adyacentes = nodo.getAdyacentes();
        Nodo<T> adyacenteActual = adyacentes.getpPrim();

        while (adyacenteActual != null) {
            Nodo<T> nodoAdyacente = buscarNodo(adyacenteActual.gettInfo()); // Buscar el nodo real basado en la información del nodo adyacente

            if (!visitados.contiene(nodoAdyacente.gettInfo())) {
                llenarOrden(nodoAdyacente, visitados, pila);
            }

            adyacenteActual = adyacenteActual.getpSig();
        }

        pila.apilar(nodo.gettInfo());
    }

    // Método para buscar un nodo en el grafo basado en su información
    public Nodo<T> buscarNodo(T info) {
        Nodo<T> nodoActual = this.listaNodos.getpPrim();
        while (nodoActual != null) {
            if (nodoActual.gettInfo().equals(info)) {
                return nodoActual;
            }
            nodoActual = nodoActual.getpSig();
        }
        return null; // Retorna null si no encuentra el nodo
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
