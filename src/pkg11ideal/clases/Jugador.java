package pkg11ideal.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase que crea a un objeto Jugador. Dicha clase cuenta con los atributos de nombre e id (particulares 
 * para cada judagor), que son cargados en el metodo de LectorArchivos, {@linkplain LectorArchivos#cargarListaJugadores(java.lang.String) cargarListaJugadores}.
 * Tambien cuenta con una lista de posiciones que es cargada en {@linkplain LectorArchivos#cargarPosicionJugador(java.lang.String, pkg11ideal.clases.Lista) cargarPosicionJugador}
 * y una pila de eventos cargada en {@linkplain LectorArchivos#cargarEventos(java.lang.String) cargarEventos}.
 * Este objeto es el que almacena toda la informacion respecto cada jugador, siendo su utilizacion fundamental
 * para contestar las preguntas solicitadas.
 * @author Gonzalo Adrover
 */
public class Jugador{
    
    /**
     * Lista con las posiciones que ocupa el jugador.
     */
    public Lista<String> listaPosiciones = new Lista<>(); 
    /**
     * Pila de eventos en los que participa el jugador.
     */
    public Pila<Evento> listaEventos = new Pila<>();
    /**
     * Id del jugador.
     */
    public String jugadorID;
    /**
     * Nombre del jugador.
     */
    public String nombre;

    /**
     * Contructor de la clase Jugador.
     * @param jugadorID String con el id del jugador.
     * @param nombre String con el nombre del jugador.
     */
    public Jugador(String jugadorID,String nombre)
    {
        this.nombre = nombre;
        this.jugadorID = jugadorID;
    }
    
    /**
     * Metodo que retorna el nombre del jugador.
     * @return nombre
     */
    public String getNombre()
    {
        return this.nombre;
    }    
    
    /**
     * Metodo que retorna el id del jugador.
     * @return jugadorID
     */
    public String getJugadorID()
    {
        return this.jugadorID;
    }
    
    /**
     * Metodo que retorna la pila de eventos.
     * @return Pila del tipo Evento
     */
    public Pila<Evento> getListaEvento ()
    {
        return this.listaEventos;
    }
    
    /**
     * Metodo para settear el id del jugador.
     * @param jugadorid jugadorID
     */
    public void setJugadorID(String jugadorid) {
        this.jugadorID = jugadorid;
    }
    
    /**
     * Metodo para settear el nombre del jugador.
     * @param Nombre nombre
     */
    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }
    

    /**
     * Metodo que carga un nodo del tipo Evento que se le pasa por parametro a la pila de eventos.
     * @param evento Nodo del tipo Evento
     */
    public void cargarEventos(Nodo<Evento> evento)
    {
        this.listaEventos.push(evento.clonar());
    }
    
     /**
      * Metodo que carga una posicion pasada por parametro a la lista de posiciones.
      * @param posicion posicion
      */
    public void cargarPosicion(String posicion)
    { 
        if(this.listaPosiciones.buscar(posicion)==null)
        {
            Nodo<String> nodo = new Nodo(posicion, posicion);           
            this.listaPosiciones.insertar(nodo);
        }
    }
}

