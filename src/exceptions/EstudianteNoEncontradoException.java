package exceptions;

/**
 * Excepción lanzada cuando se busca un estudiante por ID
 * y no existe en el HashMap del sistema.
 * Usada en: GestorEstudiantes → buscarEstudiante() / eliminarEstudiante()
 */
public class EstudianteNoEncontradoException extends Exception {

    private String mensaje;

    public EstudianteNoEncontradoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
