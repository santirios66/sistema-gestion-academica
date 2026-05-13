package services;

import exceptions.EdificioNoEncontradoException;
import exceptions.RutaNoExisteException;
import model.Edificio;

/**
 * Servicio que gestiona las rutas entre edificios del campus.
 * Usa una matriz de adyacencia int[N][N] para guardar las distancias
 * y el algoritmo de Dijkstra para encontrar la ruta más corta.
 */
public class GestorRutas {

    // Arreglo de edificios registrados en el campus
    private Edificio[] edificios;

    // Matriz de distancias entre edificios
    // distancias[i][j] = metros entre edificio i y edificio j
    // 0 significa que no hay conexión directa
    private int[][] distancias;

    // Cantidad actual de edificios registrados
    private int numEdificios;

    // Máximo de edificios permitidos
    private static final int MAX_EDIFICIOS = 10;

    // Valor que representa infinito (sin conexión)
    private static final int INFINITO = Integer.MAX_VALUE;

    /**
     * Constructor que inicializa las estructuras vacías.
     */
    public GestorRutas() {
        this.edificios = new Edificio[MAX_EDIFICIOS];
        this.distancias = new int[MAX_EDIFICIOS][MAX_EDIFICIOS];
        this.numEdificios = 0;
    }

    /**
     * Agrega un edificio al campus.
     */
    public void agregarEdificio(Edificio edificio) {
        edificios[numEdificios] = edificio;
        numEdificios++;
        System.out.println("Edificio agregado: " + edificio.getNombre());
    }

    /**
     * Agrega una conexión entre dos edificios con su distancia en metros.
     * Como el grafo es no dirigido se registra en ambas direcciones.
     * Lanza excepción si alguno de los edificios no existe.
     */
    public void agregarConexion(int idOrigen, int idDestino, int distancia)
            throws EdificioNoEncontradoException {
        if (edificios[idOrigen] == null || edificios[idDestino] == null) {
            throw new EdificioNoEncontradoException(
                "Uno de los edificios no existe.");
        }
        distancias[idOrigen][idDestino] = distancia;
        distancias[idDestino][idOrigen] = distancia;
        System.out.println("Conexión agregada entre " +
            edificios[idOrigen].getNombre() + " y " +
            edificios[idDestino].getNombre() +
            " (" + distancia + "m)");
    }

    /**
     * Implementación del algoritmo de Dijkstra.
     * Encuentra la distancia mínima desde el edificio origen
     * hasta todos los demás edificios del campus.
     *
     * @param idOrigen id del edificio de inicio
     * @return arreglo con la distancia mínima a cada edificio
     * @throws EdificioNoEncontradoException si el edificio origen no existe
     */
    public int[] dijkstra(int idOrigen) throws EdificioNoEncontradoException {
        if (edificios[idOrigen] == null) {
            throw new EdificioNoEncontradoException(
                "No existe edificio con id: " + idOrigen);
        }

        int[] distMin = new int[numEdificios];
        boolean[] visitado = new boolean[numEdificios];

        // Inicializar todas las distancias en infinito
        for (int i = 0; i < numEdificios; i++) {
            distMin[i] = INFINITO;
        }
        distMin[idOrigen] = 0;

        for (int i = 0; i < numEdificios - 1; i++) {
            // Busca el nodo no visitado con menor distancia
            int u = minDistancia(distMin, visitado);
            visitado[u] = true;

            // Actualiza distancias de los vecinos
            for (int v = 0; v < numEdificios; v++) {
                if (!visitado[v] &&
                    distancias[u][v] != 0 &&
                    distMin[u] != INFINITO &&
                    distMin[u] + distancias[u][v] < distMin[v]) {
                    distMin[v] = distMin[u] + distancias[u][v];
                }
            }
        }
        return distMin;
    }

    /**
     * Busca el índice con la distancia mínima entre los no visitados.
     * Método auxiliar usado internamente por Dijkstra.
     */
    private int minDistancia(int[] distMin, boolean[] visitado) {
        int min = INFINITO;
        int minIndex = -1;
        for (int v = 0; v < numEdificios; v++) {
            if (!visitado[v] && distMin[v] <= min) {
                min = distMin[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    /**
     * Muestra la ruta más corta entre dos edificios.
     * Lanza excepción si no existe ruta o si algún edificio no existe.
     */
    public void mostrarRuta(int idOrigen, int idDestino)
            throws EdificioNoEncontradoException, RutaNoExisteException {
        if (edificios[idOrigen] == null || edificios[idDestino] == null) {
            throw new EdificioNoEncontradoException(
                "Uno de los edificios no existe.");
        }

        int[] distMin = dijkstra(idOrigen);

        if (distMin[idDestino] == INFINITO) {
            throw new RutaNoExisteException(
                "No existe ruta entre " +
                edificios[idOrigen].getNombre() + " y " +
                edificios[idDestino].getNombre());
        }

        System.out.println("Ruta más corta:");
        System.out.println(edificios[idOrigen].getNombre() +
            " → " + edificios[idDestino].getNombre());
        System.out.println("Distancia total: " + distMin[idDestino] + " metros");
    }

    /**
     * Lista todos los edificios registrados en el campus.
     */
    public void listarEdificios() {
        if (numEdificios == 0) {
            System.out.println("No hay edificios registrados.");
            return;
        }
        System.out.println("Edificios registrados:");
        for (int i = 0; i < numEdificios; i++) {
            System.out.println(i + ": " + edificios[i].getNombre());
        }
    }
}