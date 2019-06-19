/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg11ideal.clases;

/**
 *
 * @author lalo2
 */
public class Pila<T> {

    private Nodo<T> primero;

    public Pila() {
        primero = null;
    }
    public boolean esVacia() {
        return primero == null;
    }
    public Nodo<T> getPrimero()
    {
        return this.primero;
    }
    public void push(Nodo<T> unNodo) {
        unNodo.setSiguiente(primero);
        this.primero = unNodo;
    }
    public Nodo<T> pop()
    {
        if (this.esVacia())
        {
            return null;
        }
        Nodo<T> aux = this.primero.clonar();
        this.primero = this.primero.getSiguiente();
        return aux;
    }
    public int cantElementos() {
        int contador = 0;
        if (esVacia()) {
            System.out.println("Cantidad de elementos 0.");
            return 0;
        } else {
            INodo aux = primero;
            while (aux != null) {
                contador++;
                aux = aux.getSiguiente();
            }
        }
        return contador;
    }
}
