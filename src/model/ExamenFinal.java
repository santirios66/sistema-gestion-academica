package model;

/**
 * Clase que representa un examen final en el sistema universitario.
 * Hereda de Evaluacion e indica si requiere habilitación.
 */
public class ExamenFinal extends Evaluacion {

    // Atributos
    private boolean requiereHabilitacion; // Este atributos indica si el estudiante debe presentar habilitación.

    // Constructor que inicializa un examen final con sus datos básicos.
    public ExamenFinal(String codigo, String nombre, double nota,
            double porcentaje, boolean requiereHabilitacion) {
        super(codigo, nombre, nota, porcentaje);
        this.requiereHabilitacion = requiereHabilitacion;
    }
    // Getters and Setters 
    public boolean isRequiereHabilitacion() {return requiereHabilitacion;}
    public void setRequiereHabilitacion(boolean requiereHabilitacion) {this.requiereHabilitacion = requiereHabilitacion;}

    // Clase heredada de Evaluacion. Calcula el aporte del examen final multiplicando nota por porcentaje.
    @Override
    public double calcularAporte(){
        return getNota() * getPorcentaje();
    }

}
