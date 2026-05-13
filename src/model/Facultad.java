package model;
/**
 * Clase que representa una facultad en el sistema universitario.
 * Se almacena en un arreglo fijo de tamaño 5.
 */

public class Facultad {
    // Atributos }
    private String nombre; 
    private String codigo; 
    private String decano;
    /**
     * Constructor que inicializa una facultad con sus datos básicos.
     */
    public Facultad(String nombre, String codigo, String decano) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.decano = decano;
    }

    // Getters and Setters
    public String getNombre() {return nombre;}
    public String getCodigo() {return codigo;}
    public String getDecano() {return decano;}
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setDecano(String decano) { this.decano = decano; }

    // Muestra la información completa de la facultad en consola.
    public void mostrarInformacion(){
        System.out.println("Facultad: " + nombre);
        System.out.println("Código: " + codigo);
        System.out.println("Decano: " + decano);
    }
}
