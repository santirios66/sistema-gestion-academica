package model;
/**
 * Clase abstracta que representa una persona en el sistema universitario.
 * No se puede instanciar directamente, sirve como base para Estudiante y Profesor.
 * Cualquier clase que herede de Persona DEBE implementar mostrarInformacion().
 */
public abstract class Persona {
    
    //Atributos 
    private String nombre;
    private String id;
    private String email;

    //Constructor 
    public Persona(String nombre, String id, String email) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
    }

    // Getters and Setters
    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public String getEmail() { return email; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setId(String id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }

    /**
     * Método abstracto que obliga a Estudiante y Profesor a mostrar
     * su información de forma personalizada según su tipo.
     */
    public abstract String mostrarInformacion();

}