package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modelos.ClaseConducir;
import Modelos.Instructor;
import Modelos.Vehiculo;

public class ClaseConducirService {
	private final String tabla = "claseconducir";
	
	//Update de Clase de conducir
	
	public void saveUpdate(Connection conexion, ClaseConducir clase_conducir) throws SQLException {
		try {
			PreparedStatement consulta;
//	         if(clase_conducir.getId_Clase() == 0){
//			consulta = conexion.prepareStatement(
//					"INSERT INTO " + this.tabla + "(id_Clase, fecha, hora) VALUES(?, ?, ?)");
//			consulta.setString(1, profesor.getDni());
//			consulta.setString(2, profesor.getNombre());
//			consulta.setString(3, profesor.getDireccion());
//			consulta.setInt(4, profesor.getId_Alumno());

//	         }else{
	            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET fecha = ?,hora = ? WHERE id_Clase = ?");
	            consulta.setDate(1, clase_conducir.getFecha());
	            consulta.setDate(2, clase_conducir.getHora());
	            consulta.setInt(3, clase_conducir.getId_Clase());
//	         }
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}
	
	// Crear nueva Clase de conducir
	
	public void saveNewClaseConducir(Connection conexion, ClaseConducir clase_conducir, Instructor instructor, Vehiculo vehiculo) throws SQLException, ClassNotFoundException {
		try {
			
			PreparedStatement consulta;

			consulta = conexion.prepareStatement(
					"INSERT INTO " + this.tabla + "() VALUES(?, ?, ?, ?, ?)");
			consulta.setDate(1, clase_conducir.getFecha());
            consulta.setDate(2, clase_conducir.getHora());
            consulta.setInt(3, clase_conducir.getId_Clase());
            consulta.setInt(4, instructor.getId_Instructor());
            consulta.setInt(5, vehiculo.getid_Vehiculo());

			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

}
