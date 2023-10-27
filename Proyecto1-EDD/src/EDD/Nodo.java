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

public class Nodo<T> {

    private T tInfo;
    private Nodo<T> pSig; // Proximo nodo
    private Lista<T> adyacentes; // Lista de nodos adyacentes

    /**
     * Crea un nuevo nodo con un elemento específico. Además inicializa una
     * lista de nodos adyacentes y el puntero al siguiente nodo apunta a null.
     *
     * @author G. Angelo, S. Estefania y S. Jose 
     * @param elem El elemento que se asocia con el nodo.
     * @version: 27/10/2023
     */
    public Nodo(T elem) {
        this.tInfo = elem;
        this.pSig = null;
        this.adyacentes = new Lista<>();

    }
    
    //Gets & Sets

    /**
     * @author G. Angelo, S. Estefania y S. Jose 
     * @return the tInfo
     * @version: 27/10/2023
     */
    public T gettInfo() {
        return tInfo;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose 
     * @param tInfo the tInfo to set
     * @version: 27/10/2023
     */
    public void settInfo(T tInfo) {
        this.tInfo = tInfo;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose 
     * @return the pSig
     * @version: 27/10/2023
     */
    public Nodo<T> getpSig() {
        return pSig;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose 
     * @param pSig the pSig to set
     * @version: 27/10/2023
     */
    public void setpSig(Nodo<T> pSig) {
        this.pSig = pSig;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose 
     * @return the adyacentes
     * @version: 27/10/2023
     */
    public Lista<T> getAdyacentes() {
        return adyacentes;
    }

    /**
     * @author G. Angelo, S. Estefania y S. Jose
     * @param adyacentes the adyacentes to set
     * @version: 27/10/2023
     */
    public void setAdyacentes(Lista<T> adyacentes) {
        this.adyacentes = adyacentes;
    }

    
}
