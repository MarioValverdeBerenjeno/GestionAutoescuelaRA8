package Visual;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Modelos.Usuario;
import Servicios.Conexion;
import Servicios.EstudianteService;
import Servicios.InstructorService;
import Servicios.UsuarioService;

@SuppressWarnings("serial")
public class InsModUser extends JFrame {
	private JLabel lblNombre, lblPassword, lblRol;
	private JButton btnConfirmar, btnVolver;
	private JTextField textFieldNombre, passwordField;
	private JComboBox<String> comboBoxRol;
	// Instancia
	Manejador ma = new Manejador();
	UsuarioService us = new UsuarioService();
	EstudianteService es = new EstudianteService();
	InstructorService is = new InstructorService();

	public InsModUser() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(pruebas.class.getResource("/Visual/imagenes/admin.jpg")));
		setBounds(100, 100, 331, 231);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// etiqueta nombre
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(53, 36, 56, 13);
		getContentPane().add(lblNombre);
		// etiqueta contrasenya
		lblPassword = new JLabel("Contrasenya:");
		lblPassword.setBounds(53, 98, 77, 13);
		getContentPane().add(lblPassword);
		// etiqueta rol
		lblRol = new JLabel("Rol:");
		lblRol.setBounds(53, 66, 45, 13);
		getContentPane().add(lblRol);
		// boton confirmar
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(53, 140, 96, 21);
		btnConfirmar.addActionListener(ma);
		getContentPane().add(btnConfirmar);
		// field nombre
		textFieldNombre = new JTextField(45);
		textFieldNombre.setBounds(142, 33, 96, 19);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		// combobox rol
		comboBoxRol = new JComboBox<String>();
		comboBoxRol.setBounds(142, 62, 96, 21);
		getContentPane().add(comboBoxRol);
		comboBoxRol.addItem("ADMIN");
		comboBoxRol.addItem("INSTRUCTOR");
		comboBoxRol.addItem("ALUMNO");
		// password field
		passwordField = new JTextField(45);
		passwordField.setBounds(140, 95, 96, 19);
		getContentPane().add(passwordField);
		// boton volver
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(187, 140, 96, 21);
		btnVolver.addActionListener(ma);
		getContentPane().add(btnVolver);

		setVisible(true);
	}

	public class Manejador implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o == btnVolver) {
				new CrudUsuarios();
				dispose();
			} else if (o == btnConfirmar) {

				String nombre = textFieldNombre.getText();
				String rol = (String) comboBoxRol.getSelectedItem();
				String password = passwordField.getText();

				// Insertar usuario
				try {
					us.saveUsuario(Conexion.obtener(), new Usuario(nombre, password, rol));
					if (rol.equalsIgnoreCase("INSTRUCTOR")) {
//					is.saveNewInstructor(Conexion.obtener(), new Instructor(null,null,null,null));
					} else if (rol.equalsIgnoreCase("ALUMNO")) {

					}

				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(InsModUser.this, "No se ha podido insertar el usuario", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				JOptionPane.showMessageDialog(InsModUser.this, "El usuario se ha insertado correctamente",
						"Informacion", JOptionPane.INFORMATION_MESSAGE);
				refrescar();
			}
		}
	}

	public void refrescar() {
		dispose();
		new CrudUsuarios();
	}

}
