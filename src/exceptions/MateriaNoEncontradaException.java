package exceptions;

/**
 * Excepción lanzada cuando se busca una materia por código
 * y no existe en el HashMap del sistema.
 * Usada en: GestorMaterias → buscarMateria() / cancelarInscripcionDeEstudiante()
 */
public class MateriaNoEncontradaException extends Exception {

    private String mensaje;

    public MateriaNoEncontradaException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
