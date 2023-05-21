package Modelos;

import java.sql.Date;
import java.sql.Time;

public class ClaseConducir {
	private int id_Clase, id_Vehiculo;
	private Date fecha;
	private Time hora;
	private String dni_Instructor;

	public ClaseConducir() {
		super();
	}

	public ClaseConducir(int id_Vehiculo, Date fecha, Time hora, String dni_Instructor) {
		super();
		this.id_Vehiculo = id_Vehiculo;
		this.fecha = fecha;
		this.hora = hora;
		this.dni_Instructor = dni_Instructor;
	}

	public ClaseConducir(int id_Clase, Date fecha, Time hora, int id_Vehiculo, String dni_Instructor) {
		super();
		this.id_Clase = id_Clase;
		this.id_Vehiculo = id_Vehiculo;
		this.fecha = fecha;
		this.hora = hora;
		this.dni_Instructor = dni_Instructor;
	}

	public int getId_Clase() {
		return id_Clase;
	}

	public void setId_Clase(int id_Clase) {
		this.id_Clase = id_Clase;
	}

	public int getId_Vehiculo() {
		return id_Vehiculo;
	}

	public void setId_Vehiculo(int id_Vehiculo) {
		this.id_Vehiculo = id_Vehiculo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getDni_Instructor() {
		return dni_Instructor;
	}

	public void setDni_Instructor(String dni_Instructor) {
		this.dni_Instructor = dni_Instructor;
	}

	

	@Override
	public String toString() {
		return "ClaseConducir [id_Clase=" + id_Clase + ", id_Vehiculo=" + id_Vehiculo + ", fecha=" + fecha + ", hora="
				+ hora + ", dni_Instructor=" + dni_Instructor + ", evaluacion=" + "]";
	}

}
