package Modelos;

public class Profesor extends Persona{
	private int id_Profesor;
	
	public Profesor(String dni, String nombre, String direccion, int id_Profesor) {
		super(dni, nombre, direccion);
		this.id_Profesor = id_Profesor;
	}

	public int getId_Profesor() {
		return id_Profesor;
	}

	public void setId_Profesor(int idProfesor) {
		this.id_Profesor = idProfesor;
	}

	@Override
	public String toString() {
		return "Profesor [idProfesor=" + id_Profesor + ", toString()=" + super.toString() + "]";
	}
}