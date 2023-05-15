package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Alumno;

public class AlumnosService {
	private final String tabla = "alumno";
	
	public void save(Connection conexion, Alumno estudiante) throws SQLException{
	      try{
	         PreparedStatement consulta;
	         if(estudiante.getId_Alumno() == 0){
	            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(dni, nombre, direccion) VALUES(?, ?)");
	            consulta.setString(1, estudiante.getDni());
	            consulta.setString(2, estudiante.getNombre());
	            consulta.setString(3, estudiante.getDireccion());
	            
	         }else{
	            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET dni = ?,nombre = ?, direccion = ? WHERE id = ?");
	            consulta.setString(1, estudiante.getDni());
	            consulta.setString(2, estudiante.getNombre());
	            consulta.setString(3, estudiante.getDireccion());
	            consulta.setString(4, estudiante.getDni());
	         }
	         consulta.executeUpdate();
	      }catch(SQLException ex){
	         throw new SQLException(ex);
	      }
	   }
	
	public Alumno getAlumno(Connection conexion, int id_Alumno) throws SQLException {
		Alumno estudiante = null;
	      try{
	         PreparedStatement consulta = conexion.prepareStatement("SELECT dni,nombre,direccion "
	                 + " FROM " + this.tabla + " WHERE id_Alumno = ?" );
	         consulta.setInt(1, id_Alumno);
	         ResultSet resultado = consulta.executeQuery();
	         while(resultado.next()){
	            estudiante = new Alumno(resultado.getString("dni"), resultado.getString("nombre"), 
	                    resultado.getString("direccion"), id_Alumno);
	         }
	      }catch(SQLException ex){
	         throw new SQLException(ex);
	      }
	      System.out.println(estudiante);
	      return estudiante;
	   }
	
	public List<Alumno> getAllAlumnos(Connection conexion) throws SQLException{
	      List<Alumno> alumnos = new ArrayList<>();
	      try{
	         PreparedStatement consulta = conexion.prepareStatement("SELECT dni,nombre,direccion,id_Alumno "
	                 + " FROM " + this.tabla);
	         ResultSet resultado = consulta.executeQuery();
	         while(resultado.next()){
	        	 alumnos.add(new Alumno(resultado.getString("dni"), resultado.getString("nombre"), 
		                    resultado.getString("direccion"), resultado.getInt("id_Alumno")));
	         }
	      }catch(SQLException ex){
	         throw new SQLException(ex);
	      }
	      for(Alumno a:alumnos) {
	    	  System.out.println(a.getDni()+" "+a.getNombre());
	      }
	      return alumnos;
	   }
	
}
