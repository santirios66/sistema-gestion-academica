package model;

/**
 * Clase abstracta que representa una evaluación en el sistema universitario.
 * No se puede instanciar directamente, sirve como base para Quiz, Parcial y
 * ExamenFinal.
 * Cualquier clase que herede de Evaluacion DEBE implementar calcularAporte().
 */
public abstract class Evaluacion {
    // Atributos
    private String codigo;
    private String nombre;
    private double nota;
    private double porcentaje;

    // Constructor que inicializa los datos básicos de una evaluación.
    public Evaluacion(String codigo, String nombre, double nota, double porcentaje) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nota = nota;
        this.porcentaje = porcentaje;
    }

    // Getters y setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getNota() { return nota; }
    public double getPorcentaje() { return porcentaje; }
    public void setNota(double nota) { this.nota = nota; }

    /**
     * este es el método abstracto que obliga a las clases hijas a calcular
     * el aporte de la evaluación según su tipo.
     */
    public abstract double calcularAporte();
}
