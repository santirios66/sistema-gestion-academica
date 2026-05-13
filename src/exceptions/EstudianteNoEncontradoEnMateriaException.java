package exceptions;


public class EstudianteNoEncontradoEnMateriaException extends Exception {

    public EstudianteNoEncontradoEnMateriaException(String mensaje) {

        super(mensaje);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}