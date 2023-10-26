/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;

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
        if (!"".equals(info)) {
            Nodo<T> nodoExistente = getListaNodos().obtenerNodo((String) info);
            if (nodoExistente != null) {
                JOptionPane.showMessageDialog(null,
                        ("Ya existe el usuario: " + info + ". No se puede agregar otro."),
                        "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                getListaNodos().insertar(info);
                graph.addNode((String) info);
                this.graph.getNode((String) info).setAttribute("ui.label", (String) info);
                graph.getNode((String) info).setAttribute("ui.style", "fill-color: rgb(" + 178 + "," + 178 + "," + 232 + "); shape: circle; size: 30;");
                setNumVertices(getNumVertices() + 1);
            }
        }
    }

    // Método para eliminar un nodo al grafo
    public void eliminarNodo(T info) {
        if (!"".equals(info)) {
            Nodo<T> nodoAEliminar = getListaNodos().obtenerNodo(info);

            if (nodoAEliminar == null) {
                JOptionPane.showMessageDialog(null,
                        ("El usuario: " + (String) info + " no existe en el grafo"),
                        "", JOptionPane.INFORMATION_MESSAGE);
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
            graph.removeNode((String) info);
            setNumVertices(getNumVertices() - 1);
            JOptionPane.showMessageDialog(null,
                    ((String) info + " eliminado con exito"),
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para conectar dos nodos en el grafo (agregar arista entre dos nodos)
    public void conectarNodos(T infoOrigen, T infoDestino) {
        if (!"".equals(infoDestino) && !"".equals(infoOrigen)) {
            if (infoOrigen.equals(infoDestino)) {
                JOptionPane.showMessageDialog(null,
                        ("El usuario " + infoOrigen + " no se puede conectar a sí mismo"),
                        "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Nodo<T> nodoOrigen = getListaNodos().obtenerNodo(infoOrigen);
            Nodo<T> nodoDestino = getListaNodos().obtenerNodo(infoDestino);

            if (nodoOrigen == null || nodoDestino == null) {
                JOptionPane.showMessageDialog(null,
                        "Uno o ambos usuarios no existen en el grafo",
                        "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Nodo<T> adyacente = nodoOrigen.getAdyacentes().obtenerNodo(infoDestino);
            if (adyacente != null) {
                JOptionPane.showMessageDialog(null,
                        ("Los usuarios " + (String) infoOrigen + " y " + (String) infoDestino + " ya están conectados"),
                        "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            nodoOrigen.getAdyacentes().insertar(infoDestino);
            this.graph.addEdge((String) nodoOrigen.gettInfo() + "-" + (String) nodoDestino.gettInfo(), (String) nodoOrigen.gettInfo(), (String) nodoDestino.gettInfo(), true);
        }
    }

    // Método para desconectar dos nodos en el grafo (eliminar arista entre dos nodos)
    public void desconectarNodos(T infoOrigen, T infoDestino) {
        if (!"".equals(infoDestino) && !"".equals(infoOrigen)) {
            Nodo<T> nodoOrigen = getListaNodos().obtenerNodo(infoOrigen);
            Nodo<T> nodoDestino = getListaNodos().obtenerNodo(infoDestino);

            if (nodoOrigen == null || nodoDestino == null) {
                JOptionPane.showMessageDialog(null,
                        "Uno o ambos usuarios no existen en el grafo",
                        "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (nodoOrigen.getAdyacentes().obtenerNodo(infoDestino) != null) {
                nodoOrigen.getAdyacentes().eliminar(infoDestino);
                graph.removeEdge((String) infoOrigen, (String) infoDestino);
                JOptionPane.showMessageDialog(null,
                        ("Los usuarios " + infoOrigen + " y " + infoDestino + " fueron desconectados con exito"),
                        "", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    //Metodo que imprime el grafo
    public void imprimirGrafo() {
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }

    public Color getRandomColor() {
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
    public void obtenerSCCs() {
        graph.setAttribute("ui.stylesheet", "graph { fill-color: oldlace; }");
        System.setProperty("org.graphstream.ui", "swing");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

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
                    graph.getNode((String) nodoSCC.gettInfo()).setAttribute("ui.style", "fill-color: rgb(" + randomColor.getRed() + "," + randomColor.getGreen() + "," + randomColor.getBlue() + "); shape: circle; size: 30;");
                    nodoSCC = nodoSCC.getpSig();
                }
            }
        }
        this.imprimirGrafo();
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

    public void llenar_grafo() {
        String line;
        String usuarios_txt = "";
        String path = "test\\db.txt";
        File file = new File(path);
        System.setProperty("org.graphstream.ui", "swing");
        this.graph.setAttribute("ui.stylesheet", "graph { fill-color: gainsboro; }");
        this.graph.setAttribute("ui.quality");
        this.graph.setAttribute("ui.antialias");

        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                FileReader fr = new FileReader(file);
                try (BufferedReader br = new BufferedReader(fr)) {
                    while ((line = br.readLine()) != null) {
                        if (!line.isEmpty()) {
                            usuarios_txt += line + "\n";
                        }
                    }
                    if (!"".equals(usuarios_txt)) {
                        String[] usuarios_split = usuarios_txt.split("\n");
                        boolean add_user = true;
                        for (int i = 1; i < usuarios_split.length; i++) {
                            String nuevo_elemento = usuarios_split[i];
                            if ("relaciones".equals(nuevo_elemento)) {
                                add_user = false;
                            }
                            if (add_user == true) {
                                this.agregarNodo((T) nuevo_elemento);
                            } else if ((add_user == false) && !"relaciones".equals(nuevo_elemento)) {
                                String[] nueva_relacion = nuevo_elemento.split(", ");
                                this.conectarNodos((T) nueva_relacion[0], (T) nueva_relacion[1]);
                            }

                        }
                    }
                }

            }
        } catch (IOException err) {
            JOptionPane.showMessageDialog(null,
                    "Error al leer archivo",
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void colorInicial() {
        Nodo<T> pAux = this.listaNodos.getpPrim();
        while (pAux != null) {
            graph.getNode((String) pAux.gettInfo()).setAttribute("ui.style", "fill-color: rgb(" + 178 + "," + 178 + "," + 232 + "); shape: circle; size: 30;");
            pAux = pAux.getpSig();
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
