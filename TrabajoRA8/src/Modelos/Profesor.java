package Modelos;

public class Profesor extends Persona{
	private int idProfesor;
	
	public Profesor(String dni, String nombre, String direccion, int idProfesor) {
		super(dni, nombre, direccion);
		this.idProfesor = idProfesor;
	}

	public int getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	@Override
	public String toString() {
		return "Profesor [idProfesor=" + idProfesor + ", toString()=" + super.toString() + "]";
	}
}