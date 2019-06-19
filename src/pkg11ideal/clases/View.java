/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg11ideal.clases;

import java.util.Scanner;

/**
 * Clase encargada de crear y mostrar el UI del programa. Tras mostrar el menú principal, permite seleccionar diferentes opciones entre las provistas según se desee obtener la infomrmación.
 * Se utiliza la herramienta Scanner propia de Java para poder almacenar las respuestas del usuario, y también se instancia al Model que será el que contestará las preguntas.
 * <p>
 * El menú cuenta con las opciones del 1 al 5 para contestar las interrogantes planteadas, y una opcion 6 que termina el programa.
 * @author Gonzalo Adrover
 */
public class View {
    
    private Scanner inputDevice = new Scanner(System.in);
    private Respuestas respuesta;
    
    /**
     * Imprime el menú 
     * @param Pregunta El parámetro es usado para pasarle la información seleccionada por el usuario al Model.
     */
    public View(Respuestas Pregunta)
    {
        respuesta = Pregunta;

        System.out.println("=============================================================================================================================================");
        System.out.println("|                                                              Los Once Ideales                                                             |");
        System.out.println("=============================================================================================================================================");
        System.out.println("| Opciones:                                                                                                                                 |");
        System.out.println("|        1. ¿Quién es el jugador que tuvo más participación en los partidos en una temporada en una misma liga? ¿En qué liga fue?           |");
        System.out.println("|        2. ¿Quién fue el arquero más efectivo en una temporada? ¿Y el delantero menos efectivo?                                            |");
        System.out.println("|        3. Obtén todas las ligas en las que ha participado un determinado jugador.                                                         |");  
        System.out.println("|        4. ¿Cuál fue el equipo que más goles efectuó en una determinada temporada? ¿Y el que recibió más goles?                            |");        
        System.out.println("|        5. Once Ideal                                                                                                                      |"); 
        System.out.println("|        6. Exit                                                                                                                            |"); 
        System.out.println("=============================================================================================================================================");
    }
    
    /**
     * Mediante un switch se capta el input del usuario y se determina las acciones que debe hacer. 
     */
    public void Modelo(){
    Boolean esValido = true;
        while(esValido)
        {
            System.out.println("Escriba el número de la opción deseada y presione enter:");
            int Seleccion = inputDevice.nextInt();            
            Boolean esValido2 = true;
            while(esValido2){
                switch (Seleccion) 
                {
                    case 1:
                        System.out.println("Ingrese temporada deseada:");
                        String TemporadaP1 = inputDevice.next();
                        respuesta.Pregunta1(TemporadaP1);
                        System.out.println("Fin de opcion 1");
                        esValido2 = false;
                        break;
                    case 2:
                        System.out.println("Ingrese temporada deseada:");
                        String Temporada = inputDevice.next();
                        respuesta.pregunta2b(Temporada);
                        System.out.println("Fin de opcion 2");
                        esValido2 = false;
                        break;
                    case 3:
                        System.out.println("Ingrese nombre:");
                        String Nombre = inputDevice.next();                        
                        System.out.println("Ingrese apellido si aplica (de lo contrario presione enter):");
                        String Apellido = inputDevice.next();
                        System.out.println("El jugador participo en las siguientes ligas:");
                        respuesta.Pregunta3(Nombre, Apellido);
                        System.out.println("Fin de opcion 3");
                        esValido2 = false;
                        break;
                    case 4:
                        System.out.println("Ingrese temporada deseada:");
                        String TemporadaP4 = inputDevice.next();
                        respuesta.Pregunta4(TemporadaP4);
                        System.out.println("Fin de opcion 4");
                        esValido2 = false;
                        break;
                    case 5:
                        System.out.println("El 11 ideal es:");
                        respuesta.Pregunta5();
                        System.out.println("Fin de opcion 5");
                        esValido2 = false;
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        esValido2 = false;
                        esValido = false;
                        break;
                    default:
                        System.out.println("Seleccion invalida");
                        esValido2 = false;
                }                        
            }
        }
    }
}


