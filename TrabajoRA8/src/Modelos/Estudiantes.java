package Modelos;

public class Estudiantes extends Persona{
	private int id_Alumno;

	public Estudiantes(String dni, String nombre, String direccion, int id_Alumno) {
		super(dni, nombre, direccion);
		this.id_Alumno = id_Alumno;
	}

	public int getId_Alumno() {
		return id_Alumno;
	}

	public void setId_Alumno(int id_Alumno) {
		this.id_Alumno = id_Alumno;
	}

	@Override
	public String toString() {
		return "Estudiante [id_Alumno=" + id_Alumno + ", toString()=" + super.toString() + "]";
	}
}