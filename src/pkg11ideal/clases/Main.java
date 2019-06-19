/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg11ideal.clases;

import java.io.IOException;

/**
 * Clase "Main". Se instancia a View, encargado de la visualizacion de la información y a Model, encargado del procesamiento de algoritmos para contestar las preguntas.
 * @author Gonzalo Adrover
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
          
        Respuestas cargar = new Respuestas();
        View view = new View(cargar);
        view.Modelo();
    }        
}
    

