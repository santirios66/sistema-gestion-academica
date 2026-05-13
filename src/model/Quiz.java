package model;

/**
 * Clase que representa un quiz en el sistema universitario.
 * Hereda de Evaluacion y agrega el número de preguntas.
 */
public class Quiz extends Evaluacion {

    // Atributos 
    private  int cantidaPreguntas; // Numero de preguntas que tiene el Quiz 

    //Constructor que inicializa un quiz con sus datos básicos.
    public Quiz(String codigo, String nombre, double nota,
         double porcentaje, int cantidaPreguntas) {
        super(codigo, nombre, nota, porcentaje);
        this.cantidaPreguntas = cantidaPreguntas;
    }
    //Getters and Stters 
    public int getCantidaPreguntas() {return cantidaPreguntas;}
    public void setCantidaPreguntas(int cantidaPreguntas) {this.cantidaPreguntas = cantidaPreguntas;}


    //Calcula el aporte del quiz multiplicando nota por porcentaje.
    @Override
    public double calcularAporte(){return getNota() * getPorcentaje();}
    
}
