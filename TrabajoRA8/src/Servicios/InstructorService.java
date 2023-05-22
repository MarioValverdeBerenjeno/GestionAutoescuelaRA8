package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Instructor;

public class InstructorService {
	private final String tabla = "instructor";

	// Update de instructor

	public void saveUpdate(Connection conexion, Instructor instructor) throws SQLException {
		try {
			PreparedStatement consulta;

			consulta = conexion.prepareStatement(
					"UPDATE " + this.tabla + " SET dni = ?,nombre = ?, direccion = ? WHERE Id_instructor = ?");
			consulta.setString(1, instructor.getDni());
			consulta.setString(2, instructor.getNombre());
			consulta.setString(3, instructor.getDireccion());
			consulta.setInt(4, instructor.getId_Instructor());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	// Crear nuevo instructor

	public void saveNewInstructor(Connection conexion, Instructor instructor)
			throws SQLException, ClassNotFoundException {
		try {
			PreparedStatement consulta;

			consulta = conexion.prepareStatement(
					"INSERT INTO " + this.tabla + "(dni, nombre, direccion, id_instructor) VALUES(?, ?, ?, ?)");
			consulta.setString(1, instructor.getDni());
			consulta.setString(2, instructor.getNombre());
			consulta.setString(3, instructor.getDireccion());
			// El id se crea solo, a no ser que lo cree el admin mediante el CRUD
			consulta.setInt(4, instructor.getId_Instructor());

			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	// Obtener un solo instructor

	public Instructor getInstructor(Connection conexion, int Id_instructor) throws SQLException {
		Instructor instructor = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"SELECT dni,nombre,direccion " + " FROM " + this.tabla + " WHERE id_Instructor = ?");
			consulta.setInt(1, Id_instructor);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				instructor = new Instructor(resultado.getString("dni"), resultado.getString("nombre"),
						resultado.getString("direccion"), Id_instructor);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return instructor;
	}

	// Obtener todos los alumnos

	public List<Instructor> getAllInstructores(Connection conexion) throws SQLException {
		List<Instructor> instructores = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT dni,nombre,direccion,id_Instructor " + " FROM " + this.tabla);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				instructores.add(new Instructor(resultado.getString("dni"), resultado.getString("nombre"),
						resultado.getString("direccion"), resultado.getInt("id_Instructor")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return instructores;
	}

	// Eliminar instructor

	public void remove(Connection conexion, Instructor instructor) throws SQLException {
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("DELETE FROM " + this.tabla + " WHERE id_Instructor = ?");
			consulta.setInt(1, instructor.getId_Instructor());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		UsuarioService userServi = new UsuarioService();
		userServi.removeId(conexion, instructor.getId_Instructor());
	}

	public void removeId(Connection conexion, int id) throws SQLException {
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("DELETE FROM " + this.tabla + " WHERE id_Instructor = ?");
			consulta.setInt(1, id);
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		UsuarioService userServi = new UsuarioService();
		userServi.removeId(conexion, id);
	}

}
