package Servicios;

import java.sql.SQLException;

import Visual.InterfazAdmin;
import Visual.InterfazInstructor;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		new InterfazAdmin();	
		AlumnosService s=new AlumnosService();
		s.getAlumno(Conexion.obtener(), 1);
		s.getAllAlumnos(Conexion.obtener());

		InterfazInstructor i = new InterfazInstructor();
		/*AlumnosService s=new AlumnosService();
		s.getEstudiante(Conexion.obtener(), 1);

		UsuarioService u=new UsuarioService();
		u.getUsuario(Conexion.obtener(), 1);*/
//		try {
//			c.obtener();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}