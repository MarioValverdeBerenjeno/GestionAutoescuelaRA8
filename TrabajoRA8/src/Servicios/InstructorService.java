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

	//Update de instructor
	public void saveUpdate(Connection conexion, Instructor instructor) throws SQLException {
		try {
			PreparedStatement consulta;
//	         if(estudiante.getId_Instructor() == 0){
//			consulta = conexion.prepareStatement(
//					"INSERT INTO " + this.tabla + "(dni, nombre, direccion,id_Alumno) VALUES(?, ?, ?, ?)");
//			consulta.setString(1, instructor.getDni());
//			consulta.setString(2, instructor.getNombre());
//			consulta.setString(3, instructor.getDireccion());
//			consulta.setInt(4, instructor.getId_Alumno());

//	         }else{
	            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET dni = ?,nombre = ?, direccion = ? WHERE Id_instructor = ?");
	            consulta.setString(1, instructor.getDni());
	            consulta.setString(2, instructor.getNombre());
	            consulta.setString(3, instructor.getDireccion());
	            consulta.setInt(4, instructor.getId_Instructor());
//	         }
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}
	
	//Crear nuevo instructor
	public void saveNewAlumno(Connection conexion, Instructor instructor) throws SQLException, ClassNotFoundException {
		try {
			UsuarioService usu=new UsuarioService();
			
			PreparedStatement consulta;

			consulta = conexion.prepareStatement(
					"INSERT INTO " + this.tabla + "(dni, nombre, direccion,Id_instructor) VALUES(?, ?, ?, ?)");
			consulta.setString(1, instructor.getDni());
			consulta.setString(2, instructor.getNombre());
			consulta.setString(3, instructor.getDireccion());
			consulta.setInt(4, instructor.getId_Instructor());

			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	//Obtener un solo instructor
	public Instructor getAlumno(Connection conexion, int Id_instructor) throws SQLException {
		Instructor instructor = null;
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT dni,nombre,direccion " + " FROM " + this.tabla + " WHERE id_Instructor = ?");
			consulta.setInt(1, Id_instructor);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				instructor = new Instructor(resultado.getString("dni"), resultado.getString("nombre"),
						resultado.getString("direccion"), Id_instructor);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		System.out.println(instructor);
		return instructor;
	}
	
	//Obtener todos los alumnos
	public List<Instructor> getAllinstructores(Connection conexion) throws SQLException {
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
		for (Instructor p : instructores) {
			System.out.println(p.getDni() + " " + p.getNombre());
		}
		return instructores;
	}
	
	//Eliminar instructor
	public void remove(Connection conexion, Instructor instructor) throws SQLException{
	      try{
	         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " 
	      + this.tabla + " WHERE id = ?");
	         consulta.setInt(1, instructor.getId_Instructor());
	         consulta.executeUpdate();
	      }catch(SQLException ex){
	         throw new SQLException(ex);
	      }
	      UsuarioService userServi=new UsuarioService();
	      userServi.removeId(conexion, instructor.getId_Instructor());
	   }

}

