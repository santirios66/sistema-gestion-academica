package model;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Clase que representa una materia en el sistema .
 * Maneja su propia cola de espera, lista de prerequisitos
 * y lista de estudiantes inscritos.
 */
public class Materia {
    // Atributos 
    private String codigo; 
    private String nombre;
    private int cuposMaximos;
    private int cuposActuales;
    private int creditos;
    //Lista enlazada con los prerequisitos de la materia.
    private LinkedList<Materia> prerequisitos; 
    //Lista de estudiantes actualmente inscritos.
    private List<Estudiante> estudiantesInscritos; 
    //Cola  de estudiantes los cuales estan en cola de espera.
    private Queue<Estudiante> colaEspera;
    /**
     * Constructor que inicializa una materia con sus datos básicos.
     * Los cupos actuales arrancan en 0 y las estructuras vacías.
     */
    public Materia(String codigo, String nombre, int cuposMaximos, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuposMaximos = cuposMaximos;
        this.cuposActuales = 0;
        this.creditos = creditos;
        this.prerequisitos = new LinkedList<>();
        this.estudiantesInscritos = new ArrayList<>();
        this.colaEspera = new ArrayDeque<>();
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCuposMaximos() { return cuposMaximos; }
    public int getCuposActuales() { return cuposActuales; }
    public int getCreditos() { return creditos; }
    public LinkedList<Materia> getPrerequisitos() { return prerequisitos; }
    public Queue<Estudiante> getColaEspera() { return colaEspera; }
    public List<Estudiante> getEstudiantesInscritos() { return estudiantesInscritos; } 

    // Agrega un prerequisito a la lista enlazada de la materia. 
    public void agregarPrerequisito(Materia materia){prerequisitos.add(materia);}
    /**
     * Verifica si el estudiante tiene todas las materias requeridas 
     * prerequisito en su historial.
     */
    public Boolean verificarPrerequisitos(Estudiante estudiante){
        for ( Materia pre : prerequisitos) {
            if(!estudiante.getHistorialMaterias().contains(pre)){
                return false;
            }
        }
        return true;
    }
    /**
     * Inscribe un estudiante si hay cupos disponibles.
     * Si está llena lo agrega a la cola de espera.
     */
    public void inscribir(Estudiante estudiante){
        if(!estaLlena()){
            estudiantesInscritos.add(estudiante);
            cuposActuales++;
        }else{
            colaEspera.add(estudiante);
        }
    }
    /**
     * Cancela la inscripción de un estudiante y asigna
     * el cupo al primero de la cola de espera si hay alguien.
     */
    public void cancelarInscripcion(Estudiante estudiante){
        if(estudiantesInscritos.remove(estudiante)){
            cuposActuales --;
            liberarCupo();
        }
    }

     /**
     * Asigna el cupo liberado al primer estudiante de la cola de espera.
     */
    public void liberarCupo(){
        if(!colaEspera.isEmpty()){
            Estudiante siguiente = colaEspera.poll();
            estudiantesInscritos.add(siguiente);
            cuposActuales ++;
            System.out.println("Cupo asginado a : " + siguiente.getNombre());
        }
    }
   
    // Esta clase me indica si la materia ya no tiene cupos "que esta llena" y muestra true si es asi.
    public boolean estaLlena(){
        return cuposActuales >= cuposMaximos;
    }
    // Retorna la cantidad de cupus disponibles.
    public int getCuposDisponibles() {
        return cuposMaximos - cuposActuales;
    }
}
