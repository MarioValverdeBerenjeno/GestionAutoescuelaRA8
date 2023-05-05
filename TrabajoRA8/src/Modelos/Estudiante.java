package Modelos;

public class Estudiante extends Persona{
	private String idEstudiante;

	public Estudiante(String dni, String nombre, String direccion, String idEstudiante) {
		super(dni, nombre, direccion);
		this.idEstudiante = idEstudiante;
	}

	public String getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(String idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	@Override
	public String toString() {
		return "Estudiante [idEstudiante=" + idEstudiante + ", toString()=" + super.toString() + "]";
	}
}