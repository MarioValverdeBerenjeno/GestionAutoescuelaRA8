package Modelos;

public class Profesor extends Persona{
	private String idProfesor;
	
	public Profesor(String dni, String nombre, String direccion, String idProfesor) {
		super(dni, nombre, direccion);
		this.idProfesor = idProfesor;
	}

	public String getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}

	@Override
	public String toString() {
		return "Profesor [idProfesor=" + idProfesor + ", toString()=" + super.toString() + "]";
	}
}