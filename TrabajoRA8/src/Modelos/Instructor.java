package Modelos;

public class Instructor extends Persona{
	private int id_Instructor;
	
	
	public Instructor(String dni, String nombre, String direccion, int id_Instructor) {
		super(dni, nombre, direccion);
		this.id_Instructor = id_Instructor;
	}

	public Instructor(String dni, String nombre, String direccion) {
		super(dni, nombre, direccion);
	}

	public int getId_Instructor() {
		return id_Instructor;
	}

	public void setId_Instructor(int id_Instructor) {
		this.id_Instructor = id_Instructor;
	}

	@Override
	public String toString() {
		return "Profesor [id_Instructor=" + id_Instructor + ", toString()=" + super.toString() + "]";
	}
}