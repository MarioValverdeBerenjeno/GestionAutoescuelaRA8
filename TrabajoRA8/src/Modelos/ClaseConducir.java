package Modelos;

import java.sql.Date;

public class ClaseConducir {
	private int idClase;
	private Date fecha,hora;
	public ClaseConducir() {
		super();
	}
	public ClaseConducir(int idClase, Date fecha, Date hora) {
		super();
		this.idClase = idClase;
		this.fecha = fecha;
		this.hora = hora;
	}
	public int getIdClase() {
		return idClase;
	}
	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	@Override
	public String toString() {
		return "ClaseConducir [idClase=" + idClase + ", fecha=" + fecha + ", hora=" + hora + "]";
	}
	
}
