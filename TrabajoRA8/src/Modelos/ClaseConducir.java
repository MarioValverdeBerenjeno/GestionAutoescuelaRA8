package Modelos;

import java.sql.Date;

public class ClaseConducir {
	private int id_Clase;
	private Date fecha,hora;
	public ClaseConducir() {
		super();
	}
	public ClaseConducir(int id_Clase, Date fecha, Date hora) {
		super();
		this.id_Clase = id_Clase;
		this.fecha = fecha;
		this.hora = hora;
	}
	public int getId_Clase() {
		return id_Clase;
	}
	public void setId_Clase(int id_Clase) {
		this.id_Clase = id_Clase;
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
		return "ClaseConducir [id_Clase=" + id_Clase + ", fecha=" + fecha + ", hora=" + hora + "]";
	}
	
}
