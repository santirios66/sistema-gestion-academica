package model;

/**
 * Clase que representa un aula en el sistema universitario.
 * Maneja su disponibilidad horaria con una matriz boolean[7][24]
 * donde 7 son los días de la semana y 24 las horas del día.
 */
public class Aula {

    private String nombre;
    private int capacidad;

    // Matriz de disponibilidad: [dia][hora]
    // true = ocupado, false = libre
    private boolean[][] disponibilidad;

    /**
     * Constructor que inicializa el aula con todas las horas libres.
     */
    public Aula(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.disponibilidad = new boolean[7][24];
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Reserva un horario en el aula verificando disponibilidad.
     * dia: 0=Domingo, 1=Lunes, 2=Martes, 3=Miércoles, 4=Jueves, 5=Viernes, 6=Sábado
     * hora: 0 a 23
     * duracion: cantidad de horas a reservar
     */
    public void reservar(int dia, int hora, int duracion) {
        for (int i = hora; i < hora + duracion; i++) {
            disponibilidad[dia][i] = true;
        }
    }

    /**
     * Libera un horario previamente reservado.
     */
    public void liberar(int dia, int hora, int duracion) {
        for (int i = hora; i < hora + duracion; i++) {
            disponibilidad[dia][i] = false;
        }
        System.out.println("Horario liberado en aula " + nombre);
    }

    /**
     * Consulta si una hora específica está disponible.
     * Retorna true si está libre, false si está ocupada.
     */
    public boolean consultarDisponibilidad(int dia, int hora) {
        return !disponibilidad[dia][hora];
    }

    /**
     * Muestra el horario completo del aula en consola.
     */
    public void mostrarHorario() {
        String[] dias = { "Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab" };
        for (int i = 0; i < 7; i++) {
            System.out.print(dias[i] + ": ");
            for (int j = 0; j < 24; j++) {
                System.out.print(disponibilidad[i][j] ? "X" : "O");
            }
            System.out.println();
        }
    }
}
