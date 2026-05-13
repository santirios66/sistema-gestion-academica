package model;
/**
 * Clase que representa un edificio en el campus universitario.
 * Se usa como nodo del grafo para calcular rutas con Dijkstra.
 */
public class Edificio {
    // Atributos 
    private int id;
    private String nombre;
    /**
     * Constructor que inicializa un edificio con su id y nombre.
     */
    public Edificio(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters and Setters 
    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public void setId(int id){this.id = id;}
     public void setNombre(String nombre) { this.nombre = nombre; }
}
