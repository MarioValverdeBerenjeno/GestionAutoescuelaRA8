package Modelos;

public class Vehiculo {
	private int idVehiculo;
	private String imagenVehiculo,modelo,tipo;
	public Vehiculo(int idVehiculo, String imagenVehiculo, String modelo, String tipo) {
		super();
		this.idVehiculo = idVehiculo;
		this.imagenVehiculo = imagenVehiculo;
		this.modelo = modelo;
		this.tipo = tipo;
	}
	public Vehiculo() {
		super();
	}
	public int getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
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
		return "Vehiculo [idVehiculo=" + idVehiculo + ", imagenVehiculo=" + imagenVehiculo + ", modelo=" + modelo
				+ ", tipo=" + tipo + "]";
	}
	
	
}
