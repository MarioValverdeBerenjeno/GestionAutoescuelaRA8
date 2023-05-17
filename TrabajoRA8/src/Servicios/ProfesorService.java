package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Profesor;

public class ProfesorService {
	private final String tabla = "profesor";

	//Update de Profesor
	public void saveUpdate(Connection conexion, Profesor profesor) throws SQLException {
		try {
			PreparedStatement consulta;
//	         if(estudiante.getId_Profesor() == 0){
//			consulta = conexion.prepareStatement(
//					"INSERT INTO " + this.tabla + "(dni, nombre, direccion,id_Alumno) VALUES(?, ?, ?, ?)");
//			consulta.setString(1, profesor.getDni());
//			consulta.setString(2, profesor.getNombre());
//			consulta.setString(3, profesor.getDireccion());
//			consulta.setInt(4, profesor.getId_Alumno());

//	         }else{
	            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET dni = ?,nombre = ?, direccion = ? WHERE id_Profesor = ?");
	            consulta.setString(1, profesor.getDni());
	            consulta.setString(2, profesor.getNombre());
	            consulta.setString(3, profesor.getDireccion());
	            consulta.setInt(4, profesor.getId_Profesor());
//	         }
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}
	
	//Crear nuevo profesor
	public void saveNewAlumno(Connection conexion, Profesor profesor) throws SQLException, ClassNotFoundException {
		try {
			UsuarioService usu=new UsuarioService();
			
			PreparedStatement consulta;

			consulta = conexion.prepareStatement(
					"INSERT INTO " + this.tabla + "(dni, nombre, direccion,id_Profesor) VALUES(?, ?, ?, ?)");
			consulta.setString(1, profesor.getDni());
			consulta.setString(2, profesor.getNombre());
			consulta.setString(3, profesor.getDireccion());
			consulta.setInt(4, profesor.getId_Profesor());

			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	//Obtener un solo profesor
	public Profesor getAlumno(Connection conexion, int id_Profesor) throws SQLException {
		Profesor profesor = null;
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT dni,nombre,direccion " + " FROM " + this.tabla + " WHERE id_Alumno = ?");
			consulta.setInt(1, id_Profesor);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				profesor = new Profesor(resultado.getString("dni"), resultado.getString("nombre"),
						resultado.getString("direccion"), id_Profesor);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		System.out.println(profesor);
		return profesor;
	}
	
	//Obtener todos los alumnos
	public List<Profesor> getAllProfesores(Connection conexion) throws SQLException {
		List<Profesor> profesores = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT dni,nombre,direccion,id_Profesor " + " FROM " + this.tabla);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				profesores.add(new Profesor(resultado.getString("dni"), resultado.getString("nombre"),
						resultado.getString("direccion"), resultado.getInt("id_Profesor")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		for (Profesor p : profesores) {
			System.out.println(p.getDni() + " " + p.getNombre());
		}
		return profesores;
	}
	
	//Eliminar profesor
	public void remove(Connection conexion, Profesor profesor) throws SQLException{
	      try{
	         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " 
	      + this.tabla + " WHERE id = ?");
	         consulta.setInt(1, profesor.getId_Profesor());
	         consulta.executeUpdate();
	      }catch(SQLException ex){
	         throw new SQLException(ex);
	      }
	      UsuarioService userServi=new UsuarioService();
	      userServi.removeId(conexion, profesor.getId_Profesor());
	   }

}

