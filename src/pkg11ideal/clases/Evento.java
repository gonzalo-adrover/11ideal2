/*]
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg11ideal.clases;

/**
 * Clase que crea a un objeto Evento. El constructor de esta clase toma un String con el id del evento y tambien un array de Strings
 * que contiene datos particulares de cada evento.
 * Los atributos pertenecientes a este array de strings {@link Evento#EventData EventData} son cargados por el metodo {@linkplain LectorArchivos#cargarEventos(java.lang.String) cargarEventos}.
 * Los atributos de {@link Evento#league league}, {@link Evento#season season} y {@link Evento#date date} son cargados por {@linkplain LectorArchivos#cargarTemporadasYLigasEnEventos(pkg11ideal.clases.Lista, pkg11ideal.clases.Pila) cargarTemporadasYLigasEnEventos}
 * La clase es utilizada cada vez que se intenta obtender informacion precisa de cada evento.
 * @author Gonzalo Adrover
 */
public class Evento {
    /**
     * String con el id del evento.
     */
    public String id_odsp;
    /**
     * String con la liga en la que ocurre el evento.
     */
    public String league;
    /**
     * String con la temporada en la que ocurre el evento.
     */
    public String season;
    /**
     * String con la fecha en la que ocurre el evento.
     */
    public String date;
    /**
     * Array de Strings con los datos de cada evento.
     */
    public String[] EventData = new String[16];
        
    /**
     * Constructor del evento.
     * @param id_event id del evento
     */
    public Evento(String id_event)
    {
        this.id_odsp = id_event;
        this.EventData = new String[16];
    }
    
    /**
     * Metodo para settear la liga en la que ocurre el evento.
     * @param league league
     */
    public void setLeague(String league)
    {
        this.league = league;
    }
    
    /**
     * Metodo para settear la temporada en la que ocurre el evento.
     * @param season season
     */
    public void setSeason(String season)
    {
        this.season = season;
    }
    
    /**
     * Metodo para settear la fecha en la que ocurre el evento.
     * @param date date
     */
    public void setDate(String date)
    {
        this.date = date;
    }
    
    /**
     * Metodo para settear el array de Strings con los datos del evento.
     * @param eventData Datos del evento
     */
    public void setEventData(String[] eventData){
        this.EventData = eventData;    
    }
    
    /**
     * Metodo que retorna la liga del evento.
     * @return league
     */
    public String getLeague()
    {
        return this.league;
    }
    
    /**
     * Metodo que retorna la temporada del evento.
     * @return season
     */
    public String getSeason()
    {
        return this.season;
    }
    
    /**
     * Metodo que retorna la fecha del evento.
     * @return date
     */
    public String getDate()
    {
        return this.date;
    }
    
    /**
     * Metodo que retorna el array de Strings del evento.
     * @return EventData
     */
    public String[] getEventData()
    {
        return this.EventData;
    }
    
    /**
     * Metodo que retorna el lugar de la cancha en el que ocurre del evento.
     * @return EventData[3]
     */
    public String getSide()
    {
        return this.EventData[3];
    }

    /**
     * Metodo que retorna el id del evento.
     * @return EventData[0]
     */
    public String getId_event()
    {
        return this.EventData[0];
    }
    
    /**
     * Metodo que retorna el tipo de evento 1 del evento.
     * @return EventData[1]
     */
    public String getEvent_type1()
    {
        return this.EventData[1];
    } 
    
    /**
     * Metodo que retorna el tipo de evento 2 del evento.
     * @return EventData[2]
     */
    public String getEvent_type2()
    {
        return this.EventData[2];
    } 
    
    /**
     * Metodo que retorna el equipo local del evento.
     * @return EventData[4]
     */
    public String getEvent_team()
    {
        return this.EventData[4];
    } 
    
    /**
     * Metodo que retorna el equipo oponente del evento.
     * @return EventData[5]
     */
    public String getOpponent()
    {
        return this.EventData[5];
    } 
    
    /**
     * Metodo que retorna el jugador del evento.
     * @return EventData[6]
     */
    public String getPlayer()
    {
        return this.EventData[6];
    } 
    
    /**
     * Metodo que retorna el jugador segundario del evento.
     * @return EventData[7]
     */
    public String getPlayer2()
    {
        return this.EventData[7];
    } 
    
    /**
     * Metodo que retorna el resultado del tiro ocurrido en el evento.
     * @return EventData[8]
     */
    public String getShot_outcome()
    {
        return this.EventData[8];
    } 
    
    /**
     * Metodo que retorna si ocurrio un gol en el evento.
     * @return EventData[9]
     */
    public String getIs_goal()
    {
        return this.EventData[9];
    } 

}
