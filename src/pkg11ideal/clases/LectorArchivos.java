/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg11ideal.clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de leer y procesar todos los archivos. Al iniciar el programa se cargan todos los archivos y se procesan de una manera indicada por los métodos de la misma clase.
 * <p>
 * Se cuenta con 6 métodos:
 * {@linkplain LectorArchivos#cargarListaJugadores(java.lang.String) cargarListaJugadores}, {@linkplain  LectorArchivos#cargarPosicionJugador(java.lang.String, pkg11ideal.clases.Lista) cargarPosicionJugador}, {@linkplain  LectorArchivos#cargarEventos(java.lang.String) cargarEventos}, {@linkplain  LectorArchivos#cargarEventoEnJugadores(pkg11ideal.clases.Pila, pkg11ideal.clases.Lista) cargarEventoEnJugadores}, {@linkplain  LectorArchivos#cargarTemporadasYLigasEnEventos(pkg11ideal.clases.Lista, pkg11ideal.clases.Pila) cargarTemporadasYLigasEnEventos} y {@linkplain  LectorArchivos#cargarPartidos(java.lang.String) cargarPartidos}
 * Cada uno de estos carga la informacion de una manera particular, sea en la forma Lista o Pila; con el objetivo de obtener los datos de una manera comprensible para ser analizados más tarde.
 * @author Gonzalo Adrover
 */
public class LectorArchivos {    
    TArbolBB<Jugador> arbolJugadores = new TArbolBB<Jugador>();

    
//    public void atajada(Pila<Evento> pilaEventos, Lista<Jugador> listaJugadores, String rutaMatchStandings)
//    {
//        Nodo<Jugador> actual = listaJugadores.primero;
//        while(actual != null)
//        {
//            if(actual.getDato().listaPosiciones.buscar("A") != null)
//            {                
//                Nodo<Evento> evento = actual.getDato().listaEventos.getPrimero();
//                while(evento != null)
//                {
//                    if(evento.getDato().getSeason().equals(Temporada))
//                    {
//                        if("1".equals(evento.getDato().getEvent_type1()))
//                        {
//                            if( "1".equals(evento.getDato().getShot_outcome()) && "0".equals(evento.getDato().getIs_goal()))
//                            {
//                                if(listaGolerosConAtajadas.buscar(actual.getDato().nombre) == null)
//                                {
//                                    Object[] goleroQueAtaja = {evento.getDato().getPlayer(),1};          
//                                    Nodo<Object[]> nodo = new Nodo<>(evento.getDato().getPlayer(),goleroQueAtaja);
//                                    listaGolerosConAtajadas.insertar(nodo);
//                                }  
//                                else
//                                {
//                                    int golesAtajados = (int) listaGolerosConAtajadas.buscar(actual.getDato().nombre).getDato()[1];
//                                    golesAtajados++;
//                                    listaGolerosConAtajadas.buscar(actual.getDato().nombre).getDato()[1] = golesAtajados;
//                                }
//                            }                            
//                        }
//                    }
//                    evento = evento.getSiguiente();
//                }
//            }
//            actual = actual.getSiguiente();
//        }   
//                //recorro player
//            //if player.posicion == arquero
//                //recorro evento
//                    //if evento.temporada == Temporada
//                        //if event_type1 == 1
//                            //if shot_place = 1 && ig_goal == 0 
//    }
    
    /**
     * Método encargado de abrir el archivo, leer todas sus lineas, e ir insertando en Nodo(s) del tipo Jugador, el id y el nombre de estos. Todos los nodos los inserta en una Lista del tipo Jugador.
     * @param ruta Recibe la ruta del archivo "players.csv"
     * @return Una Lista del tipo Jugador
     */
    public static TArbolBB<Jugador> cargarListaJugadores(String ruta,  TArbolBB<DatosJugador> datosJugador)
    {
    TArbolBB<Jugador> arbolJugadores = new TArbolBB<Jugador>();
    try {
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);
        String lineaActual = br.readLine();
//        Salteo la linea de descripcion
        lineaActual = br.readLine();
        while (lineaActual != null) 
        {
            lineaActual = lineaActual.replaceAll("\"","");
            String[] array = lineaActual.split(";");
            Jugador jugador = new Jugador(array[0],array[1]); 
            DatosJugador datos = new DatosJugador(array[1],Integer.parseInt(array[0]));
            TElementoAB<DatosJugador> nodoDatos = new TElementoAB<>(array[1],datos);
            TElementoAB<Jugador> nodoJugador = new TElementoAB<>(array[0],jugador);
            datosJugador.insertar(nodoDatos);
            arbolJugadores.insertar(nodoJugador);
            lineaActual = br.readLine();
        }
        br.close();
        fr.close();
        } 
    catch (IOException ex) {
            Logger.getLogger(LectorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arbolJugadores;
    }     
    
    /**
     * Método encargado de cargar las posiciones de cada jugador. Recibe por parámetro la lista encontrada anteriormente y busca si tiene la posicion agregada, de no serlo se la agrega. 
     * <p>
     * Tambien elimina jugadores sin posición.
     * @param ruta Recibe la ruta del archivo "match-standings.csv"
     * @param listaJugadores Recibe una Lista del tipo Jugador 
     */
    public static void cargarPosicionJugador(String ruta, TArbolBB<Jugador> arbolJugadores)
    {
    try {
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);
        String lineaActual = br.readLine();
        lineaActual = br.readLine();
        while (lineaActual != null) 
        {
            String lineaJugador = lineaActual.replaceAll("\"","");
            String[] array = lineaJugador.split(";");
            for (int i = 2; i<24; i++){
                TElementoAB<Jugador> nodoJugador = arbolJugadores.buscar(array[i]);
                if (nodoJugador != null)
                {
                    nodoJugador.getDatos().cargarPosicion(array[i+22]);
                }
            }          
            lineaActual = br.readLine();
        }
        
        br.close();
        fr.close();
        } 
    catch (IOException ex) {
            Logger.getLogger(LectorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * Método encargado de procesar los eventos. Carga el archivo y por cada linea crea un array de Strings y le asigna una información determinada. A continuación crea un nodo con esta informacion y le realiza un push a la Pila.
     * @param ruta Recibe la ruta del archivo "events.csv"
     * @return Retorna una Pila del tipo Evento con todos los eventos que ocurrieron.
     */
    public static Pila<Evento> cargarEventos(String ruta)
    {
        Pila<Evento> pilaEventos = new Pila<Evento>();
    try {
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);
        String lineaActual = br.readLine();
        lineaActual = br.readLine();
        int count = 0;
        while (lineaActual != null) 
        {
            lineaActual = lineaActual.replaceAll("\"","");
            String[] array = lineaActual.split(";");
            Evento evento = new Evento(array[0]);
            String[] eventData = {array[0],array[5], array[6], array[7], array[8], array[9], array[10], array[11],
                array[15], array[16],null, null, null, null, null, null};
            evento.setEventData(eventData);
            Nodo<Evento> eventoJugador = new Nodo<>(array[1],evento);
            pilaEventos.push(eventoJugador);
            lineaActual = br.readLine();
        }
        br.close();
        fr.close();
        } 
    catch (IOException ex) {
            Logger.getLogger(LectorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return pilaEventos;      
    }
        
    /**
     * Método encargado de vincular la Pila encontrada anteriormente con la Lista de los jugadores. Se fija cuando cargar los datos a el player o player2 del evento y lo carga.
     * @param pilaEventos Recibe una Pila del tipo Evento 
     * @param listaJugadores Recibe una Lista del tipo Jugador
     * @return Una Lista del tipo Jugador con los juadores y estos con una pila de eventos.
     */
    public static void cargarEventoEnJugadores(Pila<Evento> pilaEventos,TArbolBB<DatosJugador> datosJugador, TArbolBB<Jugador> arbolJugadores)
    {
        while(!pilaEventos.esVacia())
        {
            Nodo<Evento> evento = pilaEventos.pop();
            TElementoAB<DatosJugador> jugador1 = datosJugador.buscar(evento.getDato().getPlayer());
            TElementoAB<DatosJugador> jugador2 = datosJugador.buscar(evento.getDato().getPlayer2());
            if(jugador1 != null)
            {
                TElementoAB<Jugador> idJugador1 = arbolJugadores.buscar(evento.getDato().getPlayer());
                if(idJugador1 != null)
                {
                    idJugador1.getDatos().cargarEventos(evento);
                }
            }
            if(jugador2 != null)
            {
                TElementoAB<Jugador> idJugador2 = arbolJugadores.buscar(evento.getDato().getPlayer());
                if(idJugador2 != null)
                {
                    idJugador2.getDatos().cargarEventos(evento);
                }
            }
            evento = evento.getSiguiente();
        }
    }
    
    /**
     * Método encargado de cargar todos los partidos y colocar la información de cada un Nodo del tipo array de Strings para despues insertarlos en una Lista.
     * @param ruta Recibe la ruta del archivo "matches.csv"
     * @return Retorna una Lista del tipo array de Strings con los datos de los partidos.
     */
    public static TArbolBB<String[]> cargarPartidos(String ruta)
    {
        TArbolBB<String[]> arbolPartidos = new TArbolBB<>();
        try {
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);
        String lineaActual = br.readLine();
        //Salteo la linea de descripcion
        lineaActual = br.readLine();
        while (lineaActual != null) 
        {
            lineaActual = lineaActual.replaceAll("\"","");
            String[] lineaPartido = lineaActual.split(";");
            TElementoAB<String[]> elementoPartido = new TElementoAB(lineaPartido[0],lineaPartido);
            arbolPartidos.insertar(elementoPartido);
            lineaActual = br.readLine(); 
        }
        br.close();
        fr.close();
        } 
    catch (IOException ex) {
            Logger.getLogger(LectorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return arbolPartidos;
    }
    
    /**
     * Metodo encargado de cargarle los datos de Temporada, Liga y Fecha a la pila creada anteriormente.
     * @param listaPartidos Recibe una lista de arrays de String con partidos
     * @param pilaEventos Recibe una Pila del tipo Evento
     * @return Retorna una nueva Pila del tipo Evento con los datos de Temporada, Liga y Fecha cargados.
     */
    public static Pila<Evento> cargarTemporadasYLigasEnEventos(TArbolBB<String[]> arbolPartidos,Pila<Evento> pilaEventos)
    {
        Pila<Evento> nuevaPilaEventos = new Pila<>();
        
        Nodo<Evento> nodoEvento = pilaEventos.getPrimero();
        
        TElementoAB<String[]> partido = arbolPartidos.buscar(nodoEvento.getDato().getId_event());
        while(!pilaEventos.esVacia())
        {
            if(!partido.getDatos()[0].equalsIgnoreCase(nodoEvento.getDato().getId_event()))
            {
                partido = arbolPartidos.buscar(nodoEvento.getDato().getId_event());
            }
            
            nodoEvento.getDato().setDate(partido.getDatos()[1]);
            nodoEvento.getDato().setLeague(partido.getDatos()[2]);
            nodoEvento.getDato().setSeason(partido.getDatos()[3]);
            nuevaPilaEventos.push(nodoEvento.clonar());
            nodoEvento = pilaEventos.pop();
        }
        return nuevaPilaEventos;
    }
    

    

}

