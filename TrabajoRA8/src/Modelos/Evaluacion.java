package Modelos;

public class Evaluacion {
	private String dni_estudiante;
	private int id_clase;
	private float evaluacion;

	public Evaluacion() {
		super();
	}
	
	public Evaluacion(int id_clase, float evaluacion) {
		super();
		this.id_clase = id_clase;
		this.evaluacion = evaluacion;
	}

	public Evaluacion(String dni_estudiante, int id_clase, float evaluacion) {
		super();
		this.dni_estudiante = dni_estudiante;
		this.id_clase = id_clase;
		this.evaluacion = evaluacion;
	}

	public String getDni_estudiante() {
		return dni_estudiante;
	}

	public void setDni_estudiante(String dni_estudiante) {
		this.dni_estudiante = dni_estudiante;
	}

	public int getId_clase() {
		return id_clase;
	}

	public void setId_clase(int id_clase) {
		this.id_clase = id_clase;
	}

	public float getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(float evaluacion) {
		this.evaluacion = evaluacion;
	}

	@Override
	public String toString() {
		return "Evaluacion [dni_estudiante=" + dni_estudiante + ", id_clase=" + id_clase + ", evaluacion=" + evaluacion
				+ "]";
	}

}
