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
    try {
        cargarDatosPrueba();
    } catch (Exception e) {
        System.out.println("Error cargando datos: " + e.getMessage());
    }
    int opcion = 0;
    do {
        mostrarMenu();
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;
            opcion = Integer.parseInt(input);
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
            case 1: registrarEstudiante(); break;
            case 2: buscarEstudiante(); break;
            case 3: gestorEstudiantes.listarEstudiantes(); break;
            case 4: eliminarEstudiante(); break;
            case 5: crearMateria(); break;
            case 6: agregarPrerequisito(); break;
            case 7: mostrarPrerequisitos(); break;
            case 8: inscribirEstudiante(); break;
            case 9: cancelarInscripcion(); break;
            case 10: mostrarColaEspera(); break;
            case 11: agregarAula(); break;
            case 12: reservarHorario(); break;
            case 13: liberarHorario(); break;
            case 14: consultarDisponibilidad(); break;
            case 15: agregarEdificio(); break;
            case 16: agregarConexion(); break;
            case 17: calcularRuta(); break;
            case 18: registrarNota(); break;
            case 19: verReporte(); break;
            case 20: verReprobadas(); break;
            case 21: gestorEstudiantes.deshacer(); break;
            case 22: gestorEstudiantes.rehacer(); break;
            case 23: procesarCSV(); break;
            case 25: System.out.println("Hasta luego!"); break;
            default: System.out.println("Opción inválida.");
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    

    // ==================== ESTUDIANTES ====================
   private static void registrarEstudiante() throws EstudianteYaExisteException {
    System.out.print("ID: ");
    String id = scanner.nextLine().trim();
    System.out.print("Nombre: ");
    String nombre = scanner.nextLine().trim();
    System.out.print("Email: ");
    String email = scanner.nextLine().trim();
    System.out.print("Semestre: ");
    int semestre = Integer.parseInt(scanner.nextLine().trim());
    gestorEstudiantes.registrarEstudiante(new Estudiante(nombre, id, email, semestre));
}

    private static void buscarEstudiante() throws EstudianteNoEncontradoException {
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();
        Estudiante e = gestorEstudiantes.buscarEstudiante(id);
        System.out.println(e.mostrarInformacion());
    }

    private static void eliminarEstudiante() throws EstudianteNoEncontradoException {
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();
        gestorEstudiantes.eliminarEstudiante(id);
    }

    // ==================== MATERIAS ====================

    private static void crearMateria() throws MateriaYaExisteException {
        System.out.print("Código: ");
        String codigo = scanner.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Cupos: ");
        int cupos = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Créditos: ");
        int creditos = Integer.parseInt(scanner.nextLine().trim());
        gestorMaterias.crearMateria(codigo, nombre, cupos, creditos);
    }

    private static void agregarPrerequisito() throws MateriaNoEncontradaException {
        System.out.print("Código materia: ");
        String codigo = scanner.nextLine().trim();
        System.out.print("Código prerequisito: ");
        String codigoPre = scanner.nextLine().trim();
        Materia materia = gestorMaterias.buscarMateria(codigo);
        Materia prerequisito = gestorMaterias.buscarMateria(codigoPre);
        materia.agregarPrerequisito(prerequisito);
        System.out.println("Prerequisito agregado.");
    }

    private static void mostrarPrerequisitos() throws MateriaNoEncontradaException {
        System.out.print("Código materia: ");
        String codigo = scanner.nextLine().trim();
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
        String idEstudiante = scanner.nextLine().trim();
        System.out.print("Código materia: ");
        String codigoMateria = scanner.nextLine().trim();
        Estudiante estudiante = gestorEstudiantes.buscarEstudiante(idEstudiante);
        gestorMaterias.inscribirEstudiante(estudiante, codigoMateria);
    }

    private static void cancelarInscripcion()
            throws MateriaNoEncontradaException, EstudianteNoEncontradoEnMateriaException {
        System.out.print("ID estudiante: ");
        String idEstudiante = scanner.nextLine().trim();
        System.out.print("Código materia: ");
        String codigoMateria = scanner.nextLine().trim();
        gestorMaterias.cancelarInscripcion(idEstudiante, codigoMateria);
    }

    private static void mostrarColaEspera() throws MateriaNoEncontradaException {
        System.out.print("Código materia: ");
        String codigo = scanner.nextLine().trim();
        gestorMaterias.mostrarColaEspera(codigo);
    }

    // ==================== HORARIOS ====================

    private static void agregarAula() throws AulaYaExisteException {
        System.out.print("Nombre aula: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Capacidad: ");
        int capacidad = Integer.parseInt(scanner.nextLine().trim());
        gestorHorarios.agregarAula(new Aula(nombre, capacidad));
        System.out.println("Aula agregada exitosamente.");
    }

    private static void reservarHorario()
            throws AulaNoEncontradaException, HorarioConflictivoException {
        System.out.print("Nombre aula: ");
        String aula = scanner.nextLine().trim();
        System.out.print("Día (0=Dom, 1=Lun ... 6=Sab): ");
        int dia = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Hora (0-23): ");
        int hora = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Duración (horas): ");
        int duracion = Integer.parseInt(scanner.nextLine().trim());
        gestorHorarios.reservar(aula, dia, hora, duracion);
    }

    private static void liberarHorario() throws AulaNoEncontradaException {
        System.out.print("Nombre aula: ");
        String aula = scanner.nextLine().trim();
        System.out.print("Día: ");
        int dia = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Hora: ");
        int hora = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Duración: ");
        int duracion = Integer.parseInt(scanner.nextLine().trim());
        gestorHorarios.liberar(aula, dia, hora, duracion);
    }

    private static void consultarDisponibilidad() throws AulaNoEncontradaException {
        System.out.print("Nombre aula: ");
        String aula = scanner.nextLine().trim();
        System.out.print("Día: ");
        int dia = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Hora: ");
        int hora = Integer.parseInt(scanner.nextLine().trim());
        boolean disponible = gestorHorarios.consultarDisponibilidad(aula, dia, hora);
        System.out.println(disponible ? "Hora disponible." : "Hora ocupada.");
    }

    // ==================== RUTAS ====================

    private static void agregarEdificio() {
        System.out.print("ID edificio: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        gestorRutas.agregarEdificio(new Edificio(id, nombre));
    }

    private static void agregarConexion() throws EdificioNoEncontradoException {
        gestorRutas.listarEdificios();
        System.out.print("ID origen: ");
        int origen = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("ID destino: ");
        int destino = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Distancia en metros: ");
        int distancia = Integer.parseInt(scanner.nextLine().trim());
        gestorRutas.agregarConexion(origen, destino, distancia);
    }

    private static void calcularRuta()
            throws EdificioNoEncontradoException, RutaNoExisteException {
        gestorRutas.listarEdificios();
        System.out.print("ID origen: ");
        int origen = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("ID destino: ");
        int destino = Integer.parseInt(scanner.nextLine().trim());
        gestorRutas.mostrarRuta(origen, destino);
    }

    // ==================== REPORTES ====================

    private static void registrarNota() throws EstudianteNoEncontradoException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine().trim();
        System.out.print("Semestre (1-10): ");
        int semestre = Integer.parseInt(scanner.nextLine().trim()) - 1;
        System.out.print("Materia (1-20): ");
        int materia = Integer.parseInt(scanner.nextLine().trim()) - 1;
        System.out.print("Nota (0.0 - 5.0): ");
        double nota = Double.parseDouble(scanner.nextLine().trim());
        Estudiante estudiante = gestorEstudiantes.buscarEstudiante(id);
        estudiante.agregarNota(semestre, materia, nota);
        System.out.println("Nota registrada exitosamente.");
    }

    private static void verReporte()
            throws EstudianteNoEncontradoException, SinNotasException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine().trim();
        Estudiante estudiante = gestorEstudiantes.buscarEstudiante(id);
        gestorReportes.generarReporte(estudiante);
    }

    private static void verReprobadas()
            throws EstudianteNoEncontradoException, SinNotasException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine().trim();
        Estudiante estudiante = gestorEstudiantes.buscarEstudiante(id);
        gestorReportes.reporteReprobadas(estudiante);
    }

    // ==================== BATCH ====================

    private static void procesarCSV() {
        System.out.print("Nombre del archivo CSV: ");
        String archivo = scanner.nextLine().trim();
        gestorMaterias.cargarBatch(archivo);
        gestorMaterias.procesarBatch();
    }


    // es una prueba de datos 
    private static void cargarDatosPrueba() throws Exception {
    // Registrar estudiantes
    for (int i = 1; i <= 20; i++) {
        String id = "202400" + (i < 10 ? "0" + i : i);
        gestorEstudiantes.registrarEstudiante(
            new Estudiante("Estudiante " + i, id, "est" + i + "@universidad.edu", 1));
    }

    // Crear materias
    gestorMaterias.crearMateria("PROG101", "Programacion 1", 30, 3);
    gestorMaterias.crearMateria("MATDIS", "Matematicas Discretas", 30, 3);
    gestorMaterias.crearMateria("CALC101", "Calculo 1", 30, 4);
    gestorMaterias.crearMateria("FIS101", "Fisica 1", 30, 4);
    gestorMaterias.crearMateria("ALGE1", "Algebra", 30, 3);
    gestorMaterias.crearMateria("QUIM2", "Quimica 2", 30, 3);
    gestorMaterias.crearMateria("BIO10", "Biologia", 30, 3);
    gestorMaterias.crearMateria("HUMAN1", "Humanidades", 30, 2);

    System.out.println("Datos de prueba cargados exitosamente.");
}
}