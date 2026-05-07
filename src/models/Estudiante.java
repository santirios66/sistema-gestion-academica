import java.util.LinkedList;

/**
 * Esta clase que representa un estudiante en el sistema universitario.
 * Hereda de Persona y agrega atributos académicos como semestre,
 * notas e historial de materias cursadas.
 */
public class Estudiante extends Persona {

    // Atributos
    private int semestre; // El semestre en el que se encuentra
    private Double[][] notas; // Matriz de notas : 10 semestres x 20 materias por semestre
    private LinkedList<Materia> historialMaterias; // Lista enlazada con el historial de materias cursadas


    /**
     * Constructor que inicializa un estudiante con sus datos básicos.
     * La matriz de notas se inicializa vacía y el historial vacío.
     */
    public Estudiante(String nombre, String id, String email, int semestre) {
        super(nombre, id, email);
        this.semestre = semestre;
        this.notas = new Double[10][20]; // se inicializa la matriz de 10 por 20 vacia 
        this.historialMaterias =  new LinkedList<>();
    }

    // Getters y setters
    public int getSemestre() { return semestre; }
    public Double[][] getNotas() { return notas; }
    public LinkedList<Materia> getHistorialMaterias() { return historialMaterias; }
    public void setSemestre(int semestre) { this.semestre = semestre; }

    /**
     * Registra una nota en la posición indicada por semestre y materia.
     * semestre va de 0 a 9, materia va de 0 a 19.
     */

    public void agregarNota(int semestre, int materia, Double nota){
        notas[semestre][materia] = nota;
    }

        /**
     * Calcula el promedio de un semestre específico
     * ignorando las posiciones vacías (null).
     */
    public double calcularPromedio(int semestre) {
        double suma = 0;
        int count = 0;
        for (int i = 0; i < 20; i++) {
            if (notas[semestre][i] != null) {
                suma += notas[semestre][i];
                count++;
            }
        }
        return count == 0 ? 0 : suma / count;/** Asi funciona esta parte del codigo 
                                            Si no hay ninguna nota (count == 0) retorna 0
                                            Si hay notas retorna suma / count que es el promedio */
    }


        /**
     * Calcula el promedio acumulado de todos los semestres.
     */
    public double calcularPromedioAcumulado() {
        double suma = 0;
        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (notas[i][j] != null) {
                    suma += notas[i][j];
                    count++;
                }
            }
        }
        return count == 0 ? 0 : suma / count;
    }


    /**
     * Implementación obligatoria de mostrarInformacion() heredado de Persona.
     * Muestra los datos del estudiante de forma personalizada.
     */

    public String mostrarInformacion(){
        return "ID: " + getId() +
               "\nNombre: " + getNombre() +
               "\nEmail: " + getEmail() +
               "\nSemestre: " + semestre +
               "\nPromedio acumulado: " + calcularPromedioAcumulado();
    }
}
