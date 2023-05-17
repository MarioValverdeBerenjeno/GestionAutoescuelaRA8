package Servicios;

import java.sql.SQLException;

import Visual.InterfazAdmin;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AlumnosService s=new AlumnosService();
//		//Buscar alumno ID
//		Alumno e=s.getAlumno(Conexion.obtener(), 1);
//		//Tener toda la lista de usuarios
//		s.getAllAlumnos(Conexion.obtener());
//		
//		
//		
		//Crear usuario
//		UsuarioService usu=new UsuarioService();
//		usu.save(Conexion.obtener(),new Usuario("PepeUser2","pass"));
		
		//Crear Alumno
//		Alumno alumnoA=new Alumno("12345679A","Pepe2","Cadiz",usu.getUsuarioNombre(Conexion.obtener(), "PepeUser2").getId());
//		s.saveNewAlumno(Conexion.obtener(), alumnoA);
		new InterfazAdmin();	

		//Update Alumno
//		Alumno a=new Alumno(e.getDni(),"ANDRES",e.getDireccion(),e.getId_Alumno());
//		s.saveUpdate(Conexion.obtener(), a);
		
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