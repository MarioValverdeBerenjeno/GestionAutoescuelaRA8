package Servicios;

import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		Conexion c=new Conexion();
		try {
			c.obtener();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}