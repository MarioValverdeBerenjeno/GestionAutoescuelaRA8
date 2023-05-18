package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.Estudiantes;
import Modelos.Usuario;

public class UsuarioService {

	private final String tabla = "usuario";
	
	public void save(Connection conexion, Usuario user) throws SQLException{
		//compara si existe el id, si existe hace update y si no hace un insert into
	      try{
	         PreparedStatement consulta;
	         if(user.getId() == 0){
	            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(nombre, contrasenya, rol) VALUES(?, ?, ?)");
	            consulta.setString(1, user.getNombre());
	            consulta.setString(2, user.getPassword());
	            consulta.setString(3, user.getRol());
	            
	         }else{
	            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET nombre = ?,contrasenya = ?, rol = ? WHERE idUsuario = ?");
	            consulta.setString(1, user.getNombre());
	            consulta.setString(2, user.getPassword());
	            consulta.setString(3, user.getRol());
	            consulta.setInt(4, user.getId());
	         }
	         consulta.executeUpdate();
	      }catch(SQLException ex){
	         throw new SQLException(ex);
	      }
	   }
	
	public void saveInstructor(Connection conexion, Usuario user) throws SQLException{

		PreparedStatement consulta;		
		consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(nombre, contrasenya, rol) VALUES(?, ?, ?)");
        consulta.setString(1, user.getNombre());
        consulta.setString(2, user.getPassword());
        consulta.setString(3, "INSTRUCTOR");
        
        consulta.executeUpdate();
	}
	//devuelve el usuario a traves de pasarle como parametro el id de usuario
	public Usuario getUsuario(Connection conexion, int idUsuario) throws SQLException {
		Usuario user = null;
	      try{
	         PreparedStatement consulta = conexion.prepareStatement("SELECT nombre,contrasenya,rol "
	                 + " FROM " + this.tabla + " WHERE idUsuario = ?" );
	         consulta.setInt(1, idUsuario);
	         ResultSet resultado = consulta.executeQuery();
	         while(resultado.next()){
	        	 user = new Usuario(idUsuario,resultado.getString("nombre"), resultado.getString("contrasenya"), 
	                    resultado.getString("rol") );
	         }
	      }catch(SQLException ex){
	         throw new SQLException(ex);
	      }
	      System.out.println(user);
	      return user;
	   }
		//devuelve el usuario a traves de pasarle como parametro el nombre de usuario
	public Usuario getUsuarioNombre(Connection conexion, String nombre) throws SQLException {
		Usuario user = null;
		
	      try{
	         PreparedStatement consulta = conexion.prepareStatement("SELECT idUsuario,nombre,contrasenya,rol "
	                 + " FROM " + this.tabla + " WHERE nombre = ?" );
	         consulta.setString(1, nombre);
	         ResultSet resultado = consulta.executeQuery();
	         while(resultado.next()){
	        	 user = new Usuario(resultado.getInt("idUsuario"),resultado.getString("nombre"), resultado.getString("contrasenya"), 
		                    resultado.getString("rol") );
	         }
	      }catch(SQLException ex){
	         throw new SQLException(ex);
	      }
	      System.out.println(user.getId());
	      return user;
	   }
	//elimina pasandole un usuario como parametro
	public void remove(Connection conexion, Usuario user) throws SQLException{
	      try{
	         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " 
	      + this.tabla + " WHERE id = ?");
	         consulta.setInt(1, user.getId());
	         consulta.executeUpdate();
	      }catch(SQLException ex){
	         throw new SQLException(ex);
	      }
	   }
	//elimina pasandole un usuario como parametro
	public void removeId(Connection conexion, int idUser) throws SQLException{
	      try{
	         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " 
	      + this.tabla + " WHERE id = ?");
	         consulta.setInt(1, idUser);
	         consulta.executeUpdate();
	      }catch(SQLException ex){
	         throw new SQLException(ex);
	      }
	   }
}
