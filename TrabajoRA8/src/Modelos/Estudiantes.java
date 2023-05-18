package Modelos;

public class Estudiantes extends Persona {
	private int id_Alumno;
	private boolean activado;
	private float evaluacion1, evaluacion2, evaluacion3;

	public Estudiantes(String dni, String nombre, String direccion) {
		super(dni, nombre, direccion);
		activado = true;
	}

	public Estudiantes(String dni, String nombre, String direccion, int id_Alumno) {
		super(dni, nombre, direccion);
		this.id_Alumno = id_Alumno;
		activado = true;
	}

	public int getId_Alumno() {
		return id_Alumno;
	}

	public void setId_Alumno(int id_Alumno) {
		this.id_Alumno = id_Alumno;
	}

	public float getEvaluacion1() {
		return evaluacion1;
	}

	public void setEvaluacion1(float evaluacion1) {
		this.evaluacion1 = evaluacion1;
	}

	public float getEvaluacion2() {
		return evaluacion2;
	}

	public void setEvaluacion2(float evaluacion2) {
		this.evaluacion2 = evaluacion2;
	}

	public float getEvaluacion3() {
		return evaluacion3;
	}

	public void setEvaluacion3(float evaluacion3) {
		this.evaluacion3 = evaluacion3;
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