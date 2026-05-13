package model;

/**
 * Clase que representa un parcial en el sistema .
 * Hereda de Evaluacion e indica si es el primer parcial.
 */
public class Parcial extends Evaluacion {
    private boolean esParcial1;

    // Constructor que inicializa un parcial con sus datos básicos.
    public Parcial(String codigo, String nombre, double nota,
         double porcentaje, boolean esParcial1) {
        super(codigo, nombre, nota, porcentaje);
        this.esParcial1 = esParcial1;
    }
    // Getters and Stters
    public boolean isEsParcial1() {return esParcial1;}
    public void setEsParcial1(boolean esParcial1) {this.esParcial1 = esParcial1;}

    // Clase que hereda de Evaluacion. Calcula el aporte del parcial multiplicando nota por porcentaje.
    @Override
    public double calcularAporte(){
        return getNota() * getPorcentaje();
    }
}
