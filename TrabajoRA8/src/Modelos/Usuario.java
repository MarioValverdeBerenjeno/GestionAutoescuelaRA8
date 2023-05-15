package Modelos;

public class Usuario {

	private int id;
	private String nombre,password,rol;
	
	public Usuario() {
		super();
	}

	public Usuario(int id, String nombre, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.rol="ALUMNO";
	}

	public Usuario(int id, String nombre, String password, String rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", password=" + password + ", rol=" + rol + "]";
	}

	
}
