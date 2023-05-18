package Modelos;

public class Vehiculo {
	private int id_Vehiculo;
	private String imagenVehiculo, modelo, tipo;

	public Vehiculo(int id_Vehiculo, String imagenVehiculo, String modelo, String tipo) {
		super();
		this.id_Vehiculo = id_Vehiculo;
		this.imagenVehiculo = imagenVehiculo;
		this.modelo = modelo;
		this.tipo = tipo;
	}

	public Vehiculo() {
		super();
	}

	public int getId_Vehiculo() {
		return id_Vehiculo;
	}

	public void setId_Vehiculo(int id_Vehiculo) {
		this.id_Vehiculo = id_Vehiculo;
	}

	public String getImagenVehiculo() {
		return imagenVehiculo;
	}

	public void setImagenVehiculo(String imagenVehiculo) {
		this.imagenVehiculo = imagenVehiculo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {

		return "Vehiculo [id_Vehiculo=" + id_Vehiculo + ", imagenVehiculo=" + imagenVehiculo + ", modelo=" + modelo
				+ ", tipo=" + tipo + "]";
	}

}
