package app;

import exceptions.*;
import model.*;
import services.*;

import java.util.Scanner;

/**
 * Clase principal del sistema de gestión universitaria.
 * Contiene el menú interactivo que permite acceder a todas las funcionalidades.
 */
public class Main {

    private static GestorEstudiantes gestorEstudiantes = new GestorEstudiantes();
    private static GestorMaterias gestorMaterias = new GestorMaterias();
    private static GestorHorarios gestorHorarios = new GestorHorarios();
    private static GestorRutas gestorRutas = new GestorRutas();
    private static GestorReportes gestorReportes = new GestorReportes();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                ejecutarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        } while (opcion != 25);
    }

    /**
     * Muestra el menú principal en consola.
     */
    private static void mostrarMenu() {
        System.out.println("============================================================");
        System.out.println("   PLANIFICACIÓN ACADÉMICA - SISTEMA UNIVERSITARIO");
        System.out.println("============================================================");
        System.out.println("=== GESTIÓN DE ESTUDIANTES ===");
        System.out.println("1.  Registrar estudiante");
        System.out.println("2.  Buscar estudiante por ID");
        System.out.println("3.  Listar todos los estudiantes");
        System.out.println("4.  Eliminar estudiante");
        System.out.println("=== GESTIÓN DE MATERIAS ===");
        System.out.println("5.  Crear materia");
        System.out.println("6.  Agregar prerequisito");
        System.out.println("7.  Mostrar prerequisitos");
        System.out.println("8.  Inscribir estudiante");
        System.out.println("9.  Cancelar inscripción");
        System.out.println("10. Mostrar cola de espera");
        System.out.println("=== GESTIÓN DE HORARIOS ===");
        System.out.println("11. Agregar aula");
        System.out.println("12. Reservar horario en aula");
        System.out.println("13. Liberar horario");
        System.out.println("14. Consultar disponibilidad");
        System.out.println("=== RUTAS ENTRE EDIFICIOS ===");
        System.out.println("15. Agregar edificio");
        System.out.println("16. Agregar conexión entre edificios");
        System.out.println("17. Calcular ruta más corta");
        System.out.println("=== REPORTES ACADÉMICOS ===");
        System.out.println("18. Registrar nota");
        System.out.println("19. Ver reporte académico");
        System.out.println("20. Ver materias reprobadas");
        System.out.println("=== SISTEMA DESHACER/REHACER ===");
        System.out.println("21. Deshacer última operación");
        System.out.println("22. Rehacer última operación");
        System.out.println("=== PROCESAMIENTO POR LOTES ===");
        System.out.println("23. Procesar archivo CSV");
        System.out.println("=== SALIR ===");
        System.out.println("25. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Ejecuta la opción seleccionada por el usuario.
     */
    private static void ejecutarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 : registrarEstudiante();
                case 2 : buscarEstudiante();
                case 3 : gestorEstudiantes.listarEstudiantes();
                case 4 : eliminarEstudiante();
                case 5 : crearMateria();
                case 6 : agregarPrerequisito();
                case 7 : mostrarPrerequisitos();
                case 8 : inscribirEstudiante();
                case 9 : cancelarInscripcion();
                case 10 : mostrarColaEspera();
                case 11 : agregarAula();
                case 12 : reservarHorario();
                case 13 : liberarHorario();
                case 14 : consultarDisponibilidad();
                case 15 : agregarEdificio();
                case 16 : agregarConexion();
                case 17 : calcularRuta();
                case 18 : registrarNota();
                case 19 : verReporte();
                case 20 : verReprobadas();
                case 21 : gestorEstudiantes.deshacer();
                case 22 : gestorEstudiantes.rehacer();
                case 23 : procesarCSV();
                case 25 : System.out.println("Hasta luego!");
                default : System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ==================== ESTUDIANTES ====================

    private static void registrarEstudiante() throws EstudianteYaExisteException {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Semestre: ");
        int semestre = Integer.parseInt(scanner.nextLine());
        gestorEstudiantes.registrarEstudiante(new Estudiante(nombre, id, email, semestre));
    }

    private static void buscarEstudiante() throws EstudianteNoEncontradoException {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        Estudiante e = gestorEstudiantes.buscarEstudiante(id);
        System.out.println(e.mostrarInformacion());
    }

    private static void eliminarEstudiante() throws EstudianteNoEncontradoException {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        gestorEstudiantes.eliminarEstudiante(id);
    }

    // ==================== MATERIAS ====================

    private static void crearMateria() throws MateriaYaExisteException {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Cupos: ");
        int cupos = Integer.parseInt(scanner.nextLine());
        System.out.print("Créditos: ");
        int creditos = Integer.parseInt(scanner.nextLine());
        gestorMaterias.crearMateria(codigo, nombre, cupos, creditos);
    }

    private static void agregarPrerequisito() throws MateriaNoEncontradaException {
        System.out.print("Código materia: ");
        String codigo = scanner.nextLine();
        System.out.print("Código prerequisito: ");
        String codigoPre = scanner.nextLine();
        Materia materia = gestorMaterias.buscarMateria(codigo);
        Materia prerequisito = gestorMaterias.buscarMateria(codigoPre);
        materia.agregarPrerequisito(prerequisito);
        System.out.println("Prerequisito agregado.");
    }

    private static void mostrarPrerequisitos() throws MateriaNoEncontradaException {
        System.out.print("Código materia: ");
        String codigo = scanner.nextLine();
        Materia materia = gestorMaterias.buscarMateria(codigo);
        System.out.println("Prerequisitos de " + materia.getNombre() + ":");
        for (Materia pre : materia.getPrerequisitos()) {
            System.out.println("- " + pre.getNombre());
        }
    }

    private static void inscribirEstudiante()
            throws EstudianteNoEncontradoException, MateriaNoEncontradaException,
            PreRequisitoNoAprobadoException {
        System.out.print("ID estudiante: ");
        String idEstudiante = scanner.nextLine();
        System.out.print("Código materia: ");
        String codigoMateria = scanner.nextLine();
        Estudiante estudiante = gestorEstudiantes.buscarEstudiante(idEstudiante);
        gestorMaterias.inscribirEstudiante(estudiante, codigoMateria);
    }

    private static void cancelarInscripcion()
            throws MateriaNoEncontradaException, EstudianteNoEncontradoEnMateriaException {
        System.out.print("ID estudiante: ");
        String idEstudiante = scanner.nextLine();
        System.out.print("Código materia: ");
        String codigoMateria = scanner.nextLine();
        gestorMaterias.cancelarInscripcion(idEstudiante, codigoMateria);
    }

    private static void mostrarColaEspera() throws MateriaNoEncontradaException {
        System.out.print("Código materia: ");
        String codigo = scanner.nextLine();
        gestorMaterias.mostrarColaEspera(codigo);
    }

    // ==================== HORARIOS ====================

    private static void agregarAula() throws AulaYaExisteException {
        System.out.print("Nombre aula: ");
        String nombre = scanner.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = Integer.parseInt(scanner.nextLine());
        gestorHorarios.agregarAula(new Aula(nombre, capacidad));
        System.out.println("Aula agregada exitosamente.");
    }

    private static void reservarHorario()
            throws AulaNoEncontradaException, HorarioConflictivoException {
        System.out.print("Nombre aula: ");
        String aula = scanner.nextLine();
        System.out.print("Día (0=Dom, 1=Lun ... 6=Sab): ");
        int dia = Integer.parseInt(scanner.nextLine());
        System.out.print("Hora (0-23): ");
        int hora = Integer.parseInt(scanner.nextLine());
        System.out.print("Duración (horas): ");
        int duracion = Integer.parseInt(scanner.nextLine());
        gestorHorarios.reservar(aula, dia, hora, duracion);
    }

    private static void liberarHorario() throws AulaNoEncontradaException {
        System.out.print("Nombre aula: ");
        String aula = scanner.nextLine();
        System.out.print("Día: ");
        int dia = Integer.parseInt(scanner.nextLine());
        System.out.print("Hora: ");
        int hora = Integer.parseInt(scanner.nextLine());
        System.out.print("Duración: ");
        int duracion = Integer.parseInt(scanner.nextLine());
        gestorHorarios.liberar(aula, dia, hora, duracion);
    }

    private static void consultarDisponibilidad() throws AulaNoEncontradaException {
        System.out.print("Nombre aula: ");
        String aula = scanner.nextLine();
        System.out.print("Día: ");
        int dia = Integer.parseInt(scanner.nextLine());
        System.out.print("Hora: ");
        int hora = Integer.parseInt(scanner.nextLine());
        boolean disponible = gestorHorarios.consultarDisponibilidad(aula, dia, hora);
        System.out.println(disponible ? "Hora disponible." : "Hora ocupada.");
    }

    // ==================== RUTAS ====================

    private static void agregarEdificio() {
        System.out.print("ID edificio: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        gestorRutas.agregarEdificio(new Edificio(id, nombre));
    }

    private static void agregarConexion() throws EdificioNoEncontradoException {
        gestorRutas.listarEdificios();
        System.out.print("ID origen: ");
        int origen = Integer.parseInt(scanner.nextLine());
        System.out.print("ID destino: ");
        int destino = Integer.parseInt(scanner.nextLine());
        System.out.print("Distancia en metros: ");
        int distancia = Integer.parseInt(scanner.nextLine());
        gestorRutas.agregarConexion(origen, destino, distancia);
    }

    private static void calcularRuta()
            throws EdificioNoEncontradoException, RutaNoExisteException {
        gestorRutas.listarEdificios();
        System.out.print("ID origen: ");
        int origen = Integer.parseInt(scanner.nextLine());
        System.out.print("ID destino: ");
        int destino = Integer.parseInt(scanner.nextLine());
        gestorRutas.mostrarRuta(origen, destino);
    }

    // ==================== REPORTES ====================

    private static void registrarNota() throws EstudianteNoEncontradoException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine();
        System.out.print("Semestre (1-10): ");
        int semestre = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.print("Materia (1-20): ");
        int materia = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.print("Nota (0.0 - 5.0): ");
        double nota = Double.parseDouble(scanner.nextLine());
        Estudiante estudiante = gestorEstudiantes.buscarEstudiante(id);
        estudiante.agregarNota(semestre, materia, nota);
        System.out.println("Nota registrada exitosamente.");
    }

    private static void verReporte()
            throws EstudianteNoEncontradoException, SinNotasException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine();
        Estudiante estudiante = gestorEstudiantes.buscarEstudiante(id);
        gestorReportes.generarReporte(estudiante);
    }

    private static void verReprobadas()
            throws EstudianteNoEncontradoException, SinNotasException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine();
        Estudiante estudiante = gestorEstudiantes.buscarEstudiante(id);
        gestorReportes.reporteReprobadas(estudiante);
    }

    // ==================== BATCH ====================

    private static void procesarCSV() {
        System.out.print("Nombre del archivo CSV: ");
        String archivo = scanner.nextLine();
        gestorMaterias.cargarBatch(archivo);
        gestorMaterias.procesarBatch();
    }
}