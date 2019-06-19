/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg11ideal.clases;

/**
 *
 * @author estudiante.fit
 */
public class DatosJugador{
    private int codigo;
    
    private String nombre;
    
    public DatosJugador(String nombre,int codigo){
        this.nombre = nombre;
        this.codigo = codigo;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }
    
}
