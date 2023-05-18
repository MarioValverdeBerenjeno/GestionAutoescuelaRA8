package Modelos;

public class ParteAveria {
	private int id_Parte, idVehiculoAveriado;
	private String datosAveria, dniInstructor;

	
	public ParteAveria() {
		super();
	}

	public ParteAveria(int idVehiculoAveriado, String datosAveria, String dniInstructor) {
		super();
		this.idVehiculoAveriado = idVehiculoAveriado;
		this.datosAveria = datosAveria;
		this.dniInstructor = dniInstructor;
	}

	public ParteAveria(int id_Parte, int idVehiculoAveriado, String datosAveria, String dniInstructor) {
		super();
		this.id_Parte = id_Parte;
		this.idVehiculoAveriado = idVehiculoAveriado;
		this.datosAveria = datosAveria;
		this.dniInstructor = dniInstructor;
	}

	public int getIdParte() {
		return id_Parte;
	}

	public void setIdParte(int idParte) {
		this.id_Parte = idParte;
	}

	public int getIdVehiculoAveriado() {
		return idVehiculoAveriado;
	}

	public void setIdVehiculoAveriado(int idVehiculoAveriado) {
		this.idVehiculoAveriado = idVehiculoAveriado;
	}

	public String getDatosAveria() {
		return datosAveria;
	}

	public void setDatosAveria(String datosAveria) {
		this.datosAveria = datosAveria;
	}

	public String getDniInstructor() {
		return dniInstructor;
	}

	public void setDniInstructor(String dniInstructor) {
		this.dniInstructor = dniInstructor;
	}

	@Override
	public String toString() {
		return "ParteAveria [idParte=" + id_Parte + ", idVehiculoAveriado=" + idVehiculoAveriado + ", datosAveria="
				+ datosAveria + ", dniInstructor=" + dniInstructor + "]";
	}
}