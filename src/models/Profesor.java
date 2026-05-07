
/**
 * Clase que representa un profesor en el sistema universitario.
 * Hereda de Persona y agrega atributos propios del rol docente.
 * Es opcional pero demuestra correctamente la jerarquía de herencia.
 */
public class Profesor extends Persona {
    private String departamento;// Departamento al que pertenece el profesor
    private double salario; // Salario mensual del profesor

    // Construtor con informacion basica del Profesor.
    public Profesor(String nombre, String id, String email, String departamento, double salario) {
        super(nombre, id, email);
        this.departamento = departamento;
        this.salario = salario;
    }

    // Getters and Setters 
    public String getDepartamento() { return departamento; }
    public double getSalario() { return salario; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public void setSalario(double salario) { this.salario = salario; }

    /**
     * Implementación obligatoria de mostrarInformacion() heredado de Persona.
     * Muestra los datos del profesor de forma personalizada.
     */

     @Override
    public String mostrarInformacion() {
        return "ID: " + getId() +
               "\nNombre: " + getNombre() +
               "\nEmail: " + getEmail() +
               "\nDepartamento: " + departamento +
               "\nSalario: $" + salario;
    }


}
