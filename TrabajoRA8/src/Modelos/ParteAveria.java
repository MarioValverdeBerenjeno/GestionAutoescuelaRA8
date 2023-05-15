package Modelos;

public class ParteAveria {
	private int idParte, idVehiculoAveriado;
	private String datosAveria, dniInstructor;

	public ParteAveria(int idVehiculoAveriado, String datosAveria, String dniInstructor) {
		super();
		this.idVehiculoAveriado = idVehiculoAveriado;
		this.datosAveria = datosAveria;
		this.dniInstructor = dniInstructor;
	}

	public int getIdParte() {
		return idParte;
	}

	public void setIdParte(int idParte) {
		this.idParte = idParte;
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
		return "ParteAveria [idParte=" + idParte + ", idVehiculoAveriado=" + idVehiculoAveriado + ", datosAveria="
				+ datosAveria + ", dniInstructor=" + dniInstructor + "]";
	}
}