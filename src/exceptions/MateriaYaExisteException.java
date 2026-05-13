package exceptions;

/**
 * Excepción lanzada cuando se intenta crear una materia
 * con un código que ya existe en el HashMap del sistema.
 * Usada en: GestorMaterias → crearMateria()
 */
public class MateriaYaExisteException extends Exception {

    private String mensaje;

    public MateriaYaExisteException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
