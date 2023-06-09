package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Estudiantes;

public class EstudianteService {
	private final String tabla = "estudiante";

	// Update de Alumnos
	public void saveUpdate(Connection conexion, Estudiantes estudiante) throws SQLException {
		try {
			PreparedStatement consulta;
			consulta = conexion.prepareStatement(
					"UPDATE " + this.tabla + " SET dni = ?,nombre = ?, direccion = ? WHERE id_Alumno = ?");
			consulta.setString(1, estudiante.getDni());
			consulta.setString(2, estudiante.getNombre());
			consulta.setString(3, estudiante.getDireccion());
			consulta.setInt(4, estudiante.getId_Alumno());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	// Crear nuevo alumno
	public void saveNewAlumno(Connection conexion, Estudiantes estudiante) throws SQLException, ClassNotFoundException {
		try {
			UsuarioService usu = new UsuarioService();

			PreparedStatement consulta;

			consulta = conexion.prepareStatement(
					"INSERT INTO " + this.tabla + "(dni, nombre, direccion,id_Alumno,activado) VALUES(?, ?, ?, ?, ?)");
			consulta.setString(1, estudiante.getDni());
			consulta.setString(2, estudiante.getNombre());
			consulta.setString(3, estudiante.getDireccion());
			consulta.setInt(4, estudiante.getId_Alumno());
			consulta.setBoolean(5, estudiante.isActivado());

			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public void activarEstudiante(Connection conexion, Estudiantes estudiante)
			throws SQLException, ClassNotFoundException {
		try {

			PreparedStatement consulta;

			consulta = conexion.prepareStatement("UPDATE " + this.tabla
					+ " SET dni = ?, nombre = ?, direccion = ?, activado = ? WHERE id_Alumno = ?");

			if (!estudiante.isActivado()) {
				consulta.setString(1, estudiante.getDni());
				consulta.setString(2, estudiante.getNombre());
				consulta.setString(3, estudiante.getDireccion());
				consulta.setBoolean(4, true);
				consulta.setInt(5, estudiante.getId_Alumno());
			}
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public void desactivarEstudiante(Connection conexion, Estudiantes estudiante)
			throws SQLException, ClassNotFoundException {
		try {

			PreparedStatement consulta;

			consulta = conexion.prepareStatement("UPDATE " + this.tabla
					+ " SET dni = ?, nombre = ?, direccion = ?, activado = ? WHERE id_Alumno = ?");

			if (estudiante.isActivado()) {
				consulta.setString(1, estudiante.getDni());
				consulta.setString(2, estudiante.getNombre());
				consulta.setString(3, estudiante.getDireccion());
				consulta.setBoolean(4, false);
				consulta.setInt(5, estudiante.getId_Alumno());
			}

			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	// Obtener un solo alumno
	public Estudiantes getAlumno(Connection conexion, int id_Alumno) throws SQLException {
		Estudiantes estudiante = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"SELECT dni,nombre,direccion,activado" + " FROM " + this.tabla + " WHERE id_Alumno = ?");
			consulta.setInt(1, id_Alumno);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				estudiante = new Estudiantes(resultado.getString("dni"), resultado.getString("nombre"),
						resultado.getString("direccion"), id_Alumno, resultado.getBoolean("activado"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return estudiante;
	}

	// Obtener todos los alumnos
	public List<Estudiantes> getAllAlumnos(Connection conexion) throws SQLException {
		List<Estudiantes> alumnos = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT dni,nombre,direccion,id_Alumno,activado" + " FROM " + this.tabla);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				alumnos.add(new Estudiantes(resultado.getString("dni"), resultado.getString("nombre"),
						resultado.getString("direccion"), resultado.getInt("id_Alumno"),
						resultado.getBoolean("activado")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return alumnos;
	}

	// Eliminar alumno
	public void remove(Connection conexion, Estudiantes alumno) throws SQLException {
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("DELETE FROM " + this.tabla + " WHERE id_Alumno = ?");
			consulta.setInt(1, alumno.getId_Alumno());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		UsuarioService userServi = new UsuarioService();
		userServi.removeId(conexion, alumno.getId_Alumno());
	}

	// Eliminar alumno pasandole como parametro un id de alumno
	public void removeId(Connection conexion, int id_Alumno) throws SQLException {
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("DELETE FROM " + this.tabla + " WHERE id_Alumno = ?");
			consulta.setInt(1, id_Alumno);
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		UsuarioService userServi = new UsuarioService();
		userServi.removeId(conexion, id_Alumno);
	}
}
