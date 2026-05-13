package model;
/**
 * Clase que representa una operación realizada en el sistema.
 * Se almacena en las pilas de deshacer y rehacer para poder
 * revertir o repetir acciones realizadas por el usuario.
 */
public class Operacion {
    private String tipo; // Tipo de operación, por ejemplo: "INSCRIPCION", "ELIMINAR_ESTUDIANTE"
    private String descripcion;// Descripción legible de lo que hizo la operación

    /**
     * Se usa Object porque el estado anterior puede ser cualquier tipo:
     * un Estudiante, una Materia, una nota Double, etc.
     * Object es la clase padre de todo en Java, por eso acepta cualquier objeto.
     */

    private Object estadoAnterior;

    private String timestamp;// Fecha y hora en que se realizó la operación

    // Constructor que inicializa una operación con todos sus datos.
    public Operacion(String tipo, String descripcion, Object estadoAnterior, String timestamp) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estadoAnterior = estadoAnterior;
        this.timestamp = timestamp;
    }

    // Getters and Stters 
    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public Object getEstadoAnterior() {return estadoAnterior;}
    public void setEstadoAnterior(Object estadoAnterior) {this.estadoAnterior = estadoAnterior;}
    public String getTimestamp() {return timestamp;}
    public void setTimestamp(String timestamp) {this.timestamp = timestamp;}

    /**
     * Muestra la operación como texto legible para mostrar en consola
     * cuando el usuario consulta el historial de operaciones.
     */
    @Override
    public String toString() {
        return "Operacion [tipo=" + tipo + 
               ", descripcion=" + descripcion + 
               ", timestamp=" + timestamp + "]";
    }
}
