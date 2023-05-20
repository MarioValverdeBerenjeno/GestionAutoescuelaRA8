package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.ClaseConducir;
import Modelos.Instructor;
import Modelos.Vehiculo;

public class ClaseConducirService {
	private final String tabla = "claseconducir";

	// Update de Clase de conducir

	public void saveUpdate(Connection conexion, ClaseConducir claseconducir) throws SQLException {
		try {
			PreparedStatement consulta;
//	         if(claseconducir.getId_Clase() == 0){
//			consulta = conexion.prepareStatement(
//					"INSERT INTO " + this.tabla + "(id_Clase, fecha, hora, id_Vehiculo, dni_Instructor) VALUES(?, ?, ?)");
//			consulta.setInt(1, claseconducir.getId_Clase());
//			consulta.setDate(2, claseconducir.getFecha());
//			consulta.setTime(3, claseconducir.getHora());
//			consulta.setInt(4, claseconducir.getId_Vehiculo());
//			consulta.setString(5, claseconducir.getDni_Instructor());

//	         }else{
			consulta = conexion.prepareStatement("UPDATE " + this.tabla
					+ " SET fecha = ?,hora = ?, id_vehiculo = ?, dni_instructor = ?, evaluacion WHERE id_Clase = ?");
			consulta.setDate(1, claseconducir.getFecha());
			consulta.setTime(2, claseconducir.getHora());
			consulta.setInt(3, claseconducir.getId_Vehiculo());
			consulta.setString(4, claseconducir.getDni_Instructor());
			consulta.setInt(5, claseconducir.getId_Clase());
			consulta.setFloat(6, claseconducir.getEvaluacion());
//	         }
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	// Crear nueva Clase de conducir

	public void saveNewClaseConducir(Connection conexion, ClaseConducir clase_conducir)
			throws SQLException, ClassNotFoundException {
		try {

			PreparedStatement consulta;

			consulta = conexion.prepareStatement("INSERT INTO " + this.tabla
					+ "(id, fecha, hora, dni_Instructor, id_Vehiculo) VALUES(?, ?, ?, ?, ?)");

			consulta = conexion.prepareStatement(
					"INSERT INTO " + this.tabla + "(id,fecha,hora,dni_Instructor,id_Vehiculo) VALUES(?, ?, ?, ?, ?)");

			consulta.setInt(1, clase_conducir.getId_Clase());
			consulta.setDate(2, clase_conducir.getFecha());
			consulta.setTime(3, clase_conducir.getHora());
			consulta.setString(4, clase_conducir.getDni_Instructor());
			consulta.setInt(5, clase_conducir.getId_Vehiculo());

			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	// Obtener una sola clase

	public ClaseConducir getClaseConducir(Connection conexion, int id_Clase) throws SQLException {
		ClaseConducir claseconducir = null;
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT fecha, hora, id_vehiculo, dni_instructor, evaluacion " + " FROM "
							+ this.tabla + " WHERE id = ?");
			consulta.setInt(1, id_Clase);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				claseconducir = new ClaseConducir(id_Clase, resultado.getInt("id_vehiculo"), resultado.getDate("fecha"),
						resultado.getTime("hora"), resultado.getString("dni_instructor"),
						resultado.getFloat("evaluacion"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		System.out.println(claseconducir);
		return claseconducir;
	}

	// Obtener todas las clases

	public List<ClaseConducir> getAllClases(Connection conexion) throws SQLException {
		List<ClaseConducir> clases = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT id, fecha, hora, id_Vehiculo, dni_Instructor, evaluacion " + " FROM " + this.tabla);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				clases.add(
						new ClaseConducir(resultado.getInt("id"), resultado.getInt("id_vehiculo"), resultado.getDate("fecha"),
								resultado.getTime("hora"), resultado.getString("dni_instructor"),
								resultado.getFloat("evaluacion")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		for (ClaseConducir c : clases) {
			System.out.println(c.getId_Clase() + " " + c.getFecha() + " " + c.getHora());
		}
		return clases;
	}

	// Eliminar clase

	public void remove(Connection conexion, ClaseConducir claseconducir) throws SQLException {
		try {
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE id = ?");
			consulta.setInt(1, claseconducir.getId_Clase());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

}
