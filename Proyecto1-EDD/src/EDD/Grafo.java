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

    /**
     * Crea un nuevo grafo vacío. Inicializa un nuevo grafo sin nodos ni
     * aristas. El grafo se utiliza para representar relaciones entre elementos.
     * @author G. Angelo, S. Estefania y S. Jose. 
     * @version: 27/10/2023 
     */
    public Grafo() {
        this.numVertices = 0;
        this.listaNodos = new Lista<>();
        this.graph = new MultiGraph("Prueba 1");

    }

    /**
     * Agrega un nuevo nodo al grafo si no existe previamente. En caso de que el
     * nodo ya existe, muestra un mensaje de advertencia.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param info La información que se utilizará como identificador del nodo.
     * @version: 27/10/2023
     */
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

    /**
     * Elimina un nodo del grafo si existe y lo desconecta de los nodos con los
     * que tiene relacion. Si el nodo no existe, muestra un mensaje de
     * advertencia.
     *
     * @author G. Angelo, S. Estefania y S. Jose. 
     * @param info La información que identifica el nodo a eliminar.  
     * @version: 27/10/2023 
     */
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

    /**
     * Conecta dos nodos en el grafo si ambos existen y no están ya conectados.
     * Si uno o ambos nodos no existen o ya están conectados, muestra mensajes
     * de advertencia.
     *
     * @author G. Angelo, S. Estefania y S. Jose. 
     * @param infoOrigen La información que identifica el nodo origen.
     * @param infoDestino La información que identifica el nodo destino.
     * @version: 27/10/2023
     */
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

    /**
     * Desconecta dos nodos en el grafo solo si ambos existen y están
     * conectados. En caso de que uno o ambos nodos no existan o no están
     * conectados, muestra mensaje de advertencia.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param infoOrigen La información que identifica el nodo origen.
     * @param infoDestino La información que identifica el nodo destino.
     * @version: 27/10/2023
     */
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

    /**
     * Muestra la representación gráfica del grafo a través del visualizador de
     * GraphStream.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     */
    public void imprimirGrafo() {
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
    
    /**
     * Se genera y retorna un color aleatorio
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @return Color
     * @version: 27/10/2023
     */
    public Color getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    // Algoritmo de Kosaraju
    /**
     * Crea y devuelve un grafo traspuesto a partir del grafo actual.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @return Grafo Un nuevo grafo que es el grafo traspuesto del grafo actual,
     * donde las direcciones de las aristas se invierten.
     * @version: 27/10/2023
     */
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

    /**
     * Realiza una búsqueda de Componentes Fuertemente Conectados(SCCs) en el
     * grafo y los representa graficamente. Se utiliza el algoritmo de Kosaraju
     * para encontrar los SCCs y asigna colores aleatorios a los nodos que
     * forman el SCCs. Luego, muestra el grafo resultante con los SCCs
     * coloreados.
     * 
     * @author G. Angelo, S. Estefania y S. Jose.
     * @version: 27/10/2023
     */
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

    /**
     * Llena un Componente Fuertemente Conectado(SCC) a partir de un nodo dado
     * en el grafo traspuesto. Este método realiza un recorrido en profundidad a
     * través del grafo traspuesto para identificar todos los nodos que
     * pertenecen al mismo SCC y los agrega a la lista de SCC actual.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param nodo El nodo a partir del cual se inicia el recorrido DFS.
     * @param visitados Un conjunto que realiza un seguimiento de los nodos
     * visitados.
     * @param sccActual Una lista que representa el SCC actual y se llenará con
     * los nodos del SCC.
     * @param traspuesto El grafo traspuesto sobre el cual se realiza el
     * recorrido DFS.
     * @version: 27/10/2023
     */
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

    /**
     * Llena una pila con los nodos del grafo en el orden en que se visitan
     * durante un recorrido en profundidad. Este método inicia el recorrido en
     * profundidad desde un nodo dado y agrega los nodos a la pila en el orden
     * en que se visitan.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param nodo El nodo a partir del cual se inicia el recorrido DFS.
     * @param visitados Un conjunto que realiza un seguimiento de los nodos
     * visitados.
     * @param pila La pila que se llenará con los nodos en el orden de visita.
     * @version: 27/10/2023
     */
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

    /**
     * Busca y devuelve el nodo con la información especificada en el grafo.
     *
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param info La información que se busca en el nodo.
     * @return El nodo con la información correspondiente, o null si no se
     * encuentra en el grafo.
     * @version: 27/10/2023
     */
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
    
    /**
     * Lee información desde un archivo de texto y utiliza esa información para
     * llenar el grafo. El archivo debe contener una lista de usuarios y sus
     * relaciones. El método procesa el archivo y agrega nodos al grafo
     * representando a los usuarios, así como aristas representando las
     * relaciones entre ellos.
     * 
     * @author G. Angelo, S. Estefania y S. Jose.
     * @version: 27/10/2023
     */
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

    /**
     * Establece un color inicial en todos los nodos del grafo. Este método
     * asigna un color predefinido a todos los nodos del grafo para su
     * visualización inicial.
     * 
     * @author G. Angelo, S. Estefania y S. Jose.
     * @version: 27/10/2023
     */
    public void colorInicial() {
        Nodo<T> pAux = this.listaNodos.getpPrim();
        while (pAux != null) {
            graph.getNode((String) pAux.gettInfo()).setAttribute("ui.style", "fill-color: rgb(" + 178 + "," + 178 + "," + 232 + "); shape: circle; size: 30;");
            pAux = pAux.getpSig();
        }

    }

    // Gets and Sets
    /**
     * @author G. Angelo, S. Estefania y S. Jose.
     * @return the listaNodos
     * @version: 27/10/2023
     */
    public Lista<T> getListaNodos() {
        return listaNodos;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param listaNodos the listaNodos to set
     * @version: 27/10/2023
     */
    public void setListaNodos(Lista<T> listaNodos) {
        this.listaNodos = listaNodos;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose.
     * @return the numVertices
     * @version: 27/10/2023
     */
    public int getNumVertices() {
        return numVertices;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose.
     * @param numVertices the numVertices to set
     * @version: 27/10/2023
     */
    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

}
