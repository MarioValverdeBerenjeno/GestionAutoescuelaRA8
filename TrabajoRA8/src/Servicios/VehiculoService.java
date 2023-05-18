package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Instructor;
import Modelos.Vehiculo;

public class VehiculoService {
	private final String tabla = "vehiculo";

	// Update de vehiculo

	public void saveUpdate(Connection conexion, Vehiculo vehiculo) throws SQLException {
		try {
			PreparedStatement consulta;
//	         if(vehiculo.getId_Vehiculo() == 0){
//			consulta = conexion.prepareStatement(
//					"INSERT INTO " + this.tabla + "(imagenVehiculo, modelo, tipo) VALUES(?, ?, ?)");
//			consulta.setString(1, vehiculo.getImagenVehiculo());
//			consulta.setString(2, vehiculo.getModelo());
//			consulta.setString(3, vehiculo.getTipo());

//	         }else{
			consulta = conexion
					.prepareStatement("UPDATE " + this.tabla + " SET imagen = ?,modelo = ?, tipo = ? WHERE id = ?");
			consulta.setString(1, vehiculo.getImagenVehiculo());
			consulta.setString(2, vehiculo.getModelo());
			consulta.setString(3, vehiculo.getTipo());
//	         }
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	// Crear nuevo vehiculo

	public void saveNewVehiculo(Connection conexion, Vehiculo vehiculo) throws SQLException, ClassNotFoundException {
		try {

			PreparedStatement consulta;

			consulta = conexion
					.prepareStatement("INSERT INTO " + this.tabla + "(imagen, modelo, tipo) VALUES(?, ?, ?)");
			consulta.setString(1, vehiculo.getImagenVehiculo());
			consulta.setString(2, vehiculo.getModelo());
			consulta.setString(3, vehiculo.getTipo());

			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	// Obtener un solo vehiculo

	public Vehiculo getVehiculo(Connection conexion, int Id_Vehiculo) throws SQLException {
		Vehiculo vehiculo = null;
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT imagen, modelo, tipo " + " FROM " + this.tabla + " WHERE id = ?");
			consulta.setInt(1, Id_Vehiculo);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				vehiculo = new Vehiculo(Id_Vehiculo, resultado.getString("imagen"), resultado.getString("modelo"),
						resultado.getString("tipo"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		System.out.println(vehiculo);
		return vehiculo;
	}

	// Obtener todos los alumnos

	public List<Vehiculo> getAllVehiculos(Connection conexion) throws SQLException {
		List<Vehiculo> vehiculos = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT id, imagen, modelo, tipo " + " FROM " + this.tabla);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				vehiculos.add(new Vehiculo(resultado.getInt("id"), resultado.getString("imagen"),
						resultado.getString("modelo"), resultado.getString("tipo")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		for (Vehiculo v : vehiculos) {
			System.out.println(v.getId_Vehiculo() + " " + v.getModelo() + " " + v.getTipo());
		}
		return vehiculos;
	}

	// Eliminar instructor

	public void remove(Connection conexion, Vehiculo vehiculo) throws SQLException {
		try {
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE id = ?");
			consulta.setInt(1, vehiculo.getId_Vehiculo());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}
}
