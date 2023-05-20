package Modelos;

public class Estudiantes extends Persona {
	private int id_Alumno;
	private boolean activado;

	public Estudiantes(String dni, String nombre, String direccion) {
		super(dni, nombre, direccion);
		activado = true;
	}

	public Estudiantes(String dni, String nombre, String direccion, int id_Alumno) {
		super(dni, nombre, direccion);
		this.id_Alumno = id_Alumno;
		activado = true;
	}
	
	public Estudiantes(String dni, String nombre, String direccion, int id_Alumno, boolean activado, float evaluacion1,
			float evaluacion2, float evaluacion3) {
		super(dni, nombre, direccion);
		this.id_Alumno = id_Alumno;
		this.activado = activado;
	}

	public int getId_Alumno() {
		return id_Alumno;
	}

	public void setId_Alumno(int id_Alumno) {
		this.id_Alumno = id_Alumno;
	}

	public boolean isActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	@Override
	public String toString() {
		return "Estudiante [id_Alumno=" + id_Alumno + ", toString()=" + super.toString() + "]";
	}
}