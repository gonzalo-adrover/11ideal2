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
public class buscarNombreJugador extends Lista<Jugador> {
    
    public Nodo<Jugador> buscarNombreJugador (String nombre)
    {
        if (esVacia()) {
            return null;
        }
        else {
            Nodo<Jugador> aux = primero;
            while (aux != null) {
                if (aux.getDato().getNombre().equals(nombre)) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return null;
    }
  
}
