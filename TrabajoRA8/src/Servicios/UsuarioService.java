package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.Usuario;

public class UsuarioService {

	private final String tabla = "usuario";
	
	public void save(Connection conexion, Usuario user) throws SQLException{
	      try{
	         PreparedStatement consulta;
	         if(user.getId() == 0){
	            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(nombre, contrasenya, rol) VALUES(?, ?, ?)");
	            consulta.setString(1, user.getNombre());
	            consulta.setString(2, user.getPassword());
	            consulta.setString(3, user.getRol());
	            
	         }else{
	            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET nombre = ?,contrasenya = ?, rol = ? WHERE id = ?");
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
	
	
}
