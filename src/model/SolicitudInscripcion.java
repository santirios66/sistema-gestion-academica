package model;
/**
 * Clase que representa una solicitud de inscripción en el sistema.
 * Cada línea del CSV se convierte en un objeto SolicitudInscripcion
 * que se mete a la cola de procesamiento batch.
 */
public class SolicitudInscripcion {

    private String idEstudiante;
    private String codigoMateria;
    private String  estado;// Estado de la solicitud: "PENDIENTE", "EXITOSA", "FALLIDA"
    private String motivo;// Si la solicitud falla, explica el motivo

    /**
     * Constructor que inicializa una solicitud con estado PENDIENTE por defecto.
     */
    public SolicitudInscripcion(String idEstudiante, String codigoMateria) {
        this.idEstudiante = idEstudiante;
        this.codigoMateria = codigoMateria;
        this.estado = "PENDIENTE";
        this.motivo = "";
    }

    // Getters y setters
    public String getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(String idEstudiante) { this.idEstudiante = idEstudiante; }
    public String getCodigoMateria() { return codigoMateria; }
    public void setCodigoMateria(String codigoMateria) { this.codigoMateria = codigoMateria; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    /**
     * Muestra la solicitud como texto legible para el reporte del batch.
     */
    @Override
    public String toString() {
        return idEstudiante + " → " + codigoMateria + " → " + estado;
    }
}
