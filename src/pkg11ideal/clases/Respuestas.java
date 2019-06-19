/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg11ideal.clases;
/**
 * Clase encargada de los algoritmos que contestan las preguntas. Aquí se utilizan los métodos de LectorArchivos para cargar la información. Despues de hacer esto
 * se cuenta con varios métodos que utilizan lo cargado anteriormente para responder las preguntas indicadas.
 * @author Gonzalo Adrover
 */
public class Respuestas{
    
    /**
     * Lista del tipo Jugador propia de la clase
     */
    public TArbolBB<Jugador> ArbolJugadores;
    private TArbolBB<DatosJugador> ArbolDatos;

    /**
     * Se carga toda la información necesaria mediante los métodos de LectorArchivos 
     */
    public Respuestas() 
    {   this.ArbolDatos = new TArbolBB<>();
        this.ArbolJugadores = LectorArchivos.cargarListaJugadores("src\\archivos\\players.csv",this.ArbolDatos);
        LectorArchivos.cargarPosicionJugador("src\\archivos\\match-standings.csv", this.ArbolJugadores);
        Pila<Evento> pilaEventos = LectorArchivos.cargarEventos("src\\archivos\\events.csv");
        TArbolBB<String[]> arbolPartidos = LectorArchivos.cargarPartidos("src\\archivos\\matches.csv");
        pilaEventos = LectorArchivos.cargarTemporadasYLigasEnEventos(arbolPartidos, pilaEventos);
        LectorArchivos.cargarEventoEnJugadores(pilaEventos, this.ArbolDatos,this.ArbolJugadores);
    }
    

    /**
     * Método encargado de contestar la pregunta 1. 
     * @param TemporadaP1 Temporada por el usuario
     */
    public void Pregunta1(String TemporadaP1){   
        Nodo<Jugador> actual = ListaJugadores.primero;
        Lista<Object[]> listaPartidos = new Lista<>();
        while(actual != null)
        {
            Nodo<Evento> evento = actual.getDato().listaEventos.getPrimero();
            while(evento != null)
            {
                if(evento.getDato().getSeason().equals(TemporadaP1))
                {                 
                    Nodo<Object[]> partido = listaPartidos.buscar(actual.getDato().jugadorID);
                    if(partido!= null)
                    {   
                        int cantidadParticipaciones = (int) partido.getDato()[1];
                        cantidadParticipaciones++;
                        partido.getDato()[1] = cantidadParticipaciones;
                    }
                    else
                    {   
                        Object[] datosJugador = {actual.getDato().jugadorID,1,actual.getDato().nombre,evento.getDato().getLeague()};
                        Nodo<Object[]> nodo = new Nodo<>(actual.getDato().jugadorID,datosJugador);
                        listaPartidos.insertar(nodo);
                    }
                }
                evento = evento.getSiguiente();
            }
            actual = actual.getSiguiente();
        }
        
        Object[] vacio = {null,0,null, null};
        Nodo<Object[]> temporada = listaPartidos.primero;  

        while (temporada!= null)
        {   
            if((int)temporada.getDato()[1] > (int) vacio[1])
            {   
                vacio[0] = temporada.getDato()[0];
                vacio[1] = temporada.getDato()[1];
                vacio[2] = temporada.getDato()[2];
                vacio[3] = temporada.getDato()[3]; 
            }
            temporada = temporada.getSiguiente();
        }
        System.out.println("El jugador con mas participaciones fue: " + vacio[2] + ", en la liga: " + vacio[3]);
    }
    
    /**
     * Aquí se responde la segunda parte de la pregunta 2, quien fue el delantero menos efectivo.
     * @param Temporada Temporada indicada por el usuario.
     */
    public void pregunta2b(String Temporada)
    {
        Lista<Object[]> listaJugadoresMalos = new Lista<>();
        Nodo<Jugador> actual = ListaJugadores.primero;
        while(actual != null)
        {
            if(actual.getDato().listaPosiciones.buscar("DD") != null || actual.getDato().listaPosiciones.buscar("DC") != null || actual.getDato().listaPosiciones.buscar("DI") != null)
            {
                Nodo<Evento> evento = actual.getDato().listaEventos.getPrimero();
                while(evento != null)
                {
                    if(evento.getDato().getSeason().equals(Temporada))
                    {
                        if("1".equals(evento.getDato().getShot_outcome()) && "0".equals(evento.getDato().getIs_goal()) || "2".equals(evento.getDato().getShot_outcome()) || "3".equals(evento.getDato().getShot_outcome()) || "4".equals(evento.getDato().getShot_outcome()))
                        {
                            if(listaJugadoresMalos.buscar(actual.getDato().nombre) == null)
                            {
                                Object[] jugadorMalo = {evento.getDato().getPlayer(),1};          
                                Nodo<Object[]> nodo = new Nodo<>(evento.getDato().getPlayer(),jugadorMalo);
                                listaJugadoresMalos.insertar(nodo);
                            }                
                            else
                            {
                                int golesErrados = (int) listaJugadoresMalos.buscar(actual.getDato().nombre).getDato()[1];
                                golesErrados++;
                                listaJugadoresMalos.buscar(actual.getDato().nombre).getDato()[1] = golesErrados;
                            }                            
                        }                                       
                    }
                    evento = evento.getSiguiente();
                }
            }
            actual = actual.getSiguiente();
        }        
        Object[] noGoleador = {null,0};
        Nodo<Object[]> peor = listaJugadoresMalos.primero; 
        
        while(peor != null)
        {
            if((int) peor.getDato()[1] > (int) noGoleador[1])
                {
                    noGoleador[0] = peor.getDato()[0];
                    noGoleador[1] = peor.getDato()[1];
                }
            peor = peor.getSiguiente();
        }
        System.out.println("El delantero menos efectivo en la temporada " + Temporada + " fue: " + noGoleador[0]);

    }
    
    /**
     * En este método se contesta la pregunta 3, todas las ligas en las que participó un jugador.
     * @param Nombre
     * @param Apellido 
     */
    public static void Pregunta3(String Nombre, String Apellido)
    {
        String nombre = Nombre + " " + Apellido;
        Respuestas p = new Respuestas();
        Lista<String> ligas = new Lista<>();
        Nodo<Jugador> actual = p.ListaJugadores.primero;
        while(actual!= null)
        {        
           if(nombre.equalsIgnoreCase(actual.getDato().nombre))
           {
                Nodo<Evento> evento = actual.getDato().listaEventos.getPrimero();
                while(evento!=null)
                {                  
                    Nodo<String> liga = ligas.buscar(evento.getDato().getLeague());
                    if(liga== null){  
                        if("SP1".equals(evento.getDato().getLeague()))
                        {
                            ligas.insertar(new Nodo(evento.getDato().getLeague(),"La Liga (España)") );
                            evento = evento.getSiguiente();
                            continue;
                        }
                        if("E0".equals(evento.getDato().getLeague()))
                        {
                            ligas.insertar(new Nodo(evento.getDato().getLeague(),"Premier League (Reino Unido)") );
                            evento = evento.getSiguiente();
                            continue;
                        }
                        if("F1".equals(evento.getDato().getLeague()))
                        {
                            ligas.insertar(new Nodo(evento.getDato().getLeague(),"Ligue 1 (Francia)") );
                            evento = evento.getSiguiente();
                            continue;
                        }
                        if("I1".equals(evento.getDato().getLeague()))
                        {
                            ligas.insertar(new Nodo(evento.getDato().getLeague(),"Serie A (Italia)") );
                            evento = evento.getSiguiente();
                            continue;
                        }
                        if("D1".equals(evento.getDato().getLeague()))
                        {
                            ligas.insertar(new Nodo(evento.getDato().getLeague(),"Bundesliga (Alemania)") );
                            evento = evento.getSiguiente();
                            continue;
                        }
                    }
                    evento = evento.getSiguiente();
                }
           }           
           actual = actual.getSiguiente();
        }
        System.out.println(ligas.imprimirDato());
    }

    /**
     * Método encargado de contestar la pregunta 4. En esta se solicita contestar cual fue el equipo que efectuó mas goles y el que recibió más.
     * <p>
     * Para esto se crea un nodo con el primer jugador de la lista de jugadores y se itera. Para cada jugador se toma el primer evento de la lista de eventos y también se itera.
     * <p>
     * Si la temporada del evento coincide con la que indicó el usuario, se chequea si ocurrió un gol. 
     * <p>
     * De haber uno se crea un array de objects con los datos del equipo del evento y del oponente. Si el equipo no esta registrado se le asigna una cierto array con su id y dos espacios vacios donde irán los goles a favor y en contra.
     * Si el equipo ya tiene este array creado se le suman goles a favor o en contra (dependiendo si es el que metió el gol o oponente).
     * <p>
     * Para finalizar los que tienen más goles a favor y en contra en variables diferentes y se retornan
     * 
     * @param TemporadaP4 Temporada indicada por el usuario.
     */
    public void Pregunta4(String TemporadaP4)
    {             
        Lista<Object[]> listaEquipos = new Lista<>();
        Nodo<Jugador> actual = ListaJugadores.primero;
        while(actual != null)
        {                        
            Nodo<Evento> eventos = actual.getDato().listaEventos.getPrimero();
            while(eventos != null)
            {
                if(TemporadaP4.equals(eventos.getDato().getSeason()))
                {
                    
                    if(eventos.getDato().getIs_goal().equalsIgnoreCase("1"))
                    {System.out.println("a");
                        System.out.println(eventos.getDato().getLeague());
                        Nodo<Object[]> equipo = listaEquipos.buscar(eventos.getDato().getEvent_team());
                        Nodo<Object[]> equipo2 = listaEquipos.buscar(eventos.getDato().getOpponent());
                        if(equipo == null)
                        {
                            Object[] datosPartido = {eventos.getDato().getEvent_team(),0,0};
                            Nodo<Object[]> nodo = new Nodo<>(eventos.getDato().getEvent_team(),datosPartido);
                            listaEquipos.insertar(nodo);

                        }
                        else
                        {
                            int golesFavor = (int) equipo.getDato()[1];
                            golesFavor++;
                            equipo.getDato()[1] = golesFavor;
                        }
                        if(equipo2 == null)
                        {
                            Object[] datosPartido2 = {eventos.getDato().getOpponent(),0,0};
                            Nodo<Object[]> nodo = new Nodo<>(eventos.getDato().getOpponent(), datosPartido2);
                            listaEquipos.insertar(nodo);

                        }     
                        else
                        {
                            int golesContra = (int) equipo2.getDato()[2];
                            golesContra++;
                            equipo2.getDato()[2] = golesContra;
                        }
                    }
                }
                eventos = eventos.getSiguiente();
            }
            actual = actual.getSiguiente();
        }        
        Nodo<Object[]> nodo = listaEquipos.primero;
        Object[] goleador = {null,0,0};
        Object[] goleado = {null,0,0};
        while(nodo != null)
        {
            System.out.println(nodo.getDato()[0]);
            if((int) nodo.getDato()[1] > (int) goleador[1])
            {
                goleador[0] = nodo.getDato()[0];
                goleador[1] = nodo.getDato()[1];
                goleador[2] = nodo.getDato()[2];
            }
            if((int) nodo.getDato()[2] > (int) goleado[2])
            {
                goleado[0] = nodo.getDato()[0];
                goleado[1] = nodo.getDato()[1];
                goleado[2] = nodo.getDato()[2];
            }
            nodo = nodo.getSiguiente();
        } 
        System.out.println("El equipo que efectuo mas goles en la temporada " + TemporadaP4 + " fue el equipo " + goleador[0] + "\n" + "El equipo que recibio mas goles en la temporada " + TemporadaP4 + " fue el equipo " + goleado[0]); 
    }
    

    /**
     * Método encargado de contestar la pregunta 5. En esta se solicita contestar el 11 ideal. En base a criterios determinados para cada posición, se filtran jugadores hasta encontrar los mejores para cada posición.
     */
    public void Pregunta5()
    {
        Lista<Object[]> Delanteros = new Lista<>();
        Lista<Object[]> Defensas = new Lista<>();
        Lista<Object[]> Mediocampistas = new Lista<>();
        Lista<Jugador> lista = this.ArbolJugadores.inorden();
        Nodo<Jugador> actual = lista.primero;
        while(actual != null)
        {
            if(actual.getDato().listaPosiciones.buscar("DD") != null || actual.getDato().listaPosiciones.buscar("DC") != null || actual.getDato().listaPosiciones.buscar("DI") != null)
            {
                Nodo<Evento> eventos = actual.getDato().listaEventos.getPrimero();
                while(eventos != null)
                {
                    if("1".equals(eventos.getDato().getIs_goal()))
                    {
                        if(Delanteros.buscar(actual.getDato().jugadorID) == null)
                        {
                            Object[] delanteroGoleador = {eventos.getDato().getPlayer(),1,actual.getDato().nombre};          
                            Nodo<Object[]> nodo = new Nodo<>(actual.getDato().jugadorID,delanteroGoleador);
                            Delanteros.insertar(nodo);
                        }                
                        else
                        {
                            int goles = (int) Delanteros.buscar(actual.getDato().jugadorID).getDato()[1];
                            goles++;
                            Delanteros.buscar(actual.getDato().jugadorID).getDato()[1] = goles;
                        } 
                    }
                    eventos = eventos.getSiguiente();
                }
                actual = actual.getSiguiente();
                continue;
            }
            if(actual.getDato().listaPosiciones.buscar("MD") != null || actual.getDato().listaPosiciones.buscar("MC") != null || actual.getDato().listaPosiciones.buscar("MI") != null)
            {
                Nodo<Evento> eventos = actual.getDato().listaEventos.getPrimero();
                while(eventos != null)
                {
                    if("12".equals(eventos.getDato().getEvent_type2()))
                    {
                        if(Mediocampistas.buscar(actual.getDato().jugadorID) == null)
                        {
                            Object[] mediocampistaAsistente = {eventos.getDato().getPlayer(),1,actual.getDato().nombre};          
                            Nodo<Object[]> nodo = new Nodo<>(actual.getDato().jugadorID,mediocampistaAsistente);
                            Mediocampistas.insertar(nodo);
                        }                
                        else
                        {
                            int asistencias = (int) Mediocampistas.buscar(actual.getDato().jugadorID).getDato()[1];
                            asistencias++;
                            Mediocampistas.buscar(actual.getDato().jugadorID).getDato()[1] = asistencias;
                        }                         
                    }
                    eventos = eventos.getSiguiente();
                }
                actual = actual.getSiguiente();
                continue;
            }
            if(actual.getDato().listaPosiciones.buscar("ZD") != null || actual.getDato().listaPosiciones.buscar("ZC") != null || actual.getDato().listaPosiciones.buscar("ZI") != null)
            {
                Nodo<Evento> eventos = actual.getDato().listaEventos.getPrimero();
                while(eventos != null)
                {
                    if("3".equals(eventos.getDato().getEvent_type1()))
                    {
                        if(Defensas.buscar(actual.getDato().jugadorID) == null)
                        {
                            Object[] defensaConFaltas = {eventos.getDato().getPlayer(),1,actual.getDato().nombre};          
                            Nodo<Object[]> nodo = new Nodo<>(actual.getDato().jugadorID,defensaConFaltas);
                            Defensas.insertar(nodo);
                        }                
                        else
                        {
                            int fouls = (int) Defensas.buscar(actual.getDato().jugadorID).getDato()[1];
                            fouls++;
                            Defensas.buscar(actual.getDato().jugadorID).getDato()[1] = fouls;
                        }    
                    }
                    eventos = eventos.getSiguiente();
                }
                actual = actual.getSiguiente();
                continue;
            }
            actual = actual.getSiguiente();
        }
        Object[] delanteros = new Object[3];
        Object[] mediocampistas = new Object[3];
        Object[] defensas = new Object[4];

        int cuenta = 0;
        int cuenta2 = 0;
        int cuenta3 = 0;
        while(cuenta < 3)
        {
            Nodo<Object[]> del = Delanteros.primero; 
            Object[] delantero = {null,0,null};           
            while(del!= null)
            {
                if((int) del.getDato()[1] > (int) delantero[1])
                {
                    delantero[0] = del.getEtiqueta();
                    delantero[1] = del.getDato()[1];
                    delantero[2] = del.getDato()[2];
                    
                }
                del = del.getSiguiente();
            }
            Delanteros.eliminar((Comparable)delantero[0]);
            delanteros[cuenta] = delantero[2];
            cuenta++;
        }
        while(cuenta2 <3)
        {
            Nodo<Object[]> med = Mediocampistas.primero;
            Object[] mediocampista = {null,0,null};
            while(med!= null)
            {
                if((int) med.getDato()[1] > (int) mediocampista[1])
                {
                    mediocampista[0] = med.getEtiqueta();
                    mediocampista[1] = med.getDato()[1];
                    mediocampista[2] = med.getDato()[2];
                }
                med = med.getSiguiente();
            }
            Mediocampistas.eliminar((Comparable)mediocampista[0]);
            mediocampistas[cuenta2] = mediocampista[2];
            cuenta2++;
        }
//        while(cuenta3 < 3)
//        {
//            Nodo<Object[]> def = Defensas.primero;
//            Object[] defensa = {null,Defensas.primero.getDato()[1],null};
//            while(def!= null)
//            {
//                if((int) def.getDato()[1] < (int) defensa[1])
//                {
//                    defensa[0] = def.getEtiqueta();
//                    defensa[1] = def.getDato()[1];
//                    defensa[2] = def.getDato()[2];
//                }
//                def = def.getSiguiente();
//            }
//            Defensas.eliminar((Comparable)defensa[0]);
//            defensas[cuenta3] = defensa[2];
//            cuenta3++;
//        }
        System.out.println(delanteros[0] +"\n"+ delanteros[1] +"\n" +delanteros[2]+"\n" 
                +mediocampistas[0]+"\n" +mediocampistas[1]+"\n" +mediocampistas[2]+"\n");
                //+defensas[0] +"\n"  + defensas[1] +"\n" + defensas[2] +"\n" + defensas[3]);
        }

    
    

}
    
    
    
    

