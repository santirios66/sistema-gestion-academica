package exceptions;

/**
 * Excepción lanzada cuando se intenta registrar un estudiante
 * con un ID que ya existe en el HashMap del sistema.
 * Usada en: GestorEstudiantes → registrarEstudiante()
 */
public class EstudianteYaExisteException extends Exception {

    private String mensaje;

    public EstudianteYaExisteException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
