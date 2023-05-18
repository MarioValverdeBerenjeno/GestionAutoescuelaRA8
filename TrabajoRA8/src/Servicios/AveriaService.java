package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.Estudiantes;
import Modelos.ParteAveria;

public class AveriaService {
	private final String tabla = "parteaveria";

	public void save(Connection conexion, ParteAveria parte) throws SQLException {
		// compara si existe el id, si existe hace update y si no hace un insert into
		try {
			PreparedStatement consulta;
			if (parte.getIdParte() == 0) {
				consulta = conexion.prepareStatement("INSERT INTO " + this.tabla
						+ "(datos_averia,dni_Instructor_Informante,id_Parte,id_Vehiculo_Averiado) VALUES(?, ?, ?, ?)");
				consulta.setString(1, parte.getDatosAveria());
				consulta.setString(2, parte.getDniInstructor());
				consulta.setInt(3, parte.getIdParte());
				consulta.setInt(4, parte.getIdVehiculoAveriado());

			} else {
				consulta = conexion.prepareStatement("UPDATE " + this.tabla
						+ " SET datos_averia = ?,dni_Instructor_Informante = ?, id_Vehiculo_Averiado = ? WHERE id_Parte = ?");
				consulta.setString(1, parte.getDatosAveria());
				consulta.setString(2, parte.getDniInstructor());
				consulta.setInt(3, parte.getIdVehiculoAveriado());
				consulta.setInt(4, parte.getIdParte());
			}
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public void saveNewAveria(Connection conexion, ParteAveria parte) throws SQLException, ClassNotFoundException {
		try {
			PreparedStatement consulta;

			consulta = conexion.prepareStatement("INSERT INTO " + this.tabla
					+ "(datos_averia,dni_Instructor_Informante,id_Parte,id_Vehiculo_Averiado) VALUES(?, ?, ?, ?)");
			consulta.setString(1, parte.getDatosAveria());
			consulta.setString(2, parte.getDniInstructor());
			consulta.setInt(3, parte.getIdParte());
			consulta.setInt(4, parte.getIdVehiculoAveriado());

			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	// devuelve el usuario a traves de pasarle como parametro el id de usuario
	public ParteAveria getAveria(Connection conexion, int id_Parte) throws SQLException {
		ParteAveria parte = null;
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT datos_averia,dni_Instructor_Informante,id_Vehiculo_Averiado " + " FROM "
							+ this.tabla + " WHERE id_Parte = ?");
			consulta.setInt(1, id_Parte);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				parte = new ParteAveria(id_Parte, resultado.getInt("id_Vehiculo_Averiado"),
						resultado.getString("datos_averia"), resultado.getString("dni_Instructor_Informante"));
			}

		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		System.out.println(parte);
		return parte;
	}

	// devuelve el usuario a traves de pasarle como parametro el nombre de usuario
//	public Usuario getAveriaNombre(Connection conexion, String nombre) throws SQLException {
//		Usuario user = null;
//		
//	      try{
//	         PreparedStatement consulta = conexion.prepareStatement("SELECT idUsuario,nombre,contrasenya,rol "
//	                 + " FROM " + this.tabla + " WHERE nombre = ?" );
//	         consulta.setString(1, nombre);
//	         ResultSet resultado = consulta.executeQuery();
//	         while(resultado.next()){
//	        	 user = new Usuario(resultado.getInt("idUsuario"),resultado.getString("nombre"), resultado.getString("contrasenya"), 
//		                    resultado.getString("rol") );
//	         }
//	      }catch(SQLException ex){
//	         throw new SQLException(ex);
//	      }
//	      System.out.println(user.getId());
//	      return user;
//	   }
	// elimina pasandole un usuario como parametro
	public void remove(Connection conexion, ParteAveria parte) throws SQLException {
		try {
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE id_Parte = ?");
			consulta.setInt(1, parte.getIdParte());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	// elimina pasandole un usuario como parametro
	public void removeId(Connection conexion, int id_Parte) throws SQLException {
		try {
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE id_Parte = ?");
			consulta.setInt(1, id_Parte);
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}
}