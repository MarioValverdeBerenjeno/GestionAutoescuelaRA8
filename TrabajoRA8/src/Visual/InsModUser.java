package Visual;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Modelos.Estudiantes;
import Modelos.Instructor;
import Modelos.Usuario;
import Servicios.Conexion;
import Servicios.EstudianteService;
import Servicios.InstructorService;
import Servicios.UsuarioService;

@SuppressWarnings("serial")
public class InsModUser extends JFrame {
	private JLabel lblNombre, lblPassword, lblRol, lblDNI, lblNombre2, lblDireccion;
	private JButton btnConfirmar, btnVolver;
	private JTextField textFieldNombre, passwordField, textFieldDNI, textFieldDireccion, textFieldNombre2;
	private JComboBox<String> comboBoxRol;
	static int IdUserModi;
	// Instancia
	Manejador ma = new Manejador();
	ManejadorItem mi = new ManejadorItem();
	UsuarioService us = new UsuarioService();
	EstudianteService es = new EstudianteService();
	InstructorService is = new InstructorService();

	public InsModUser() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(pruebas.class.getResource("/Visual/imagenes/admin.jpg")));
		setBounds(100, 100, 331, 270);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// etiqueta nombre
		lblNombre = new JLabel("Username:");
		lblNombre.setBounds(10, 13, 70, 13);
		getContentPane().add(lblNombre);
		// etiqueta contrasenya
		lblPassword = new JLabel("Contrasenya:");
		lblPassword.setBounds(10, 75, 77, 13);
		getContentPane().add(lblPassword);
		// etiqueta rol
		lblRol = new JLabel("Rol:");
		lblRol.setBounds(10, 43, 45, 13);
		getContentPane().add(lblRol);
		// boton confirmar
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(45, 180, 96, 21);
		btnConfirmar.addActionListener(ma);
		getContentPane().add(btnConfirmar);
		// field nombre
		textFieldNombre = new JTextField(45);
		textFieldNombre.setBounds(99, 10, 106, 19);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		// combobox rol
		comboBoxRol = new JComboBox<String>();
		comboBoxRol.setBounds(99, 39, 115, 21);
		getContentPane().add(comboBoxRol);
		comboBoxRol.addItem("ADMIN");
		comboBoxRol.addItem("INSTRUCTOR");
		comboBoxRol.addItem("ALUMNO");
		comboBoxRol.addItemListener(mi);
		// password field
		passwordField = new JTextField(45);
		passwordField.setBounds(97, 72, 96, 19);
		getContentPane().add(passwordField);
		// boton volver
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(179, 180, 96, 21);
		btnVolver.addActionListener(ma);
		getContentPane().add(btnVolver);

		// etiqueta dni
		lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(10, 106, 77, 13);
		getContentPane().add(lblDNI);
		lblDNI.setVisible(false);

		// etiqueta nombre
		lblNombre2 = new JLabel("Nombre:");
		lblNombre2.setBounds(10, 129, 77, 13);
		getContentPane().add(lblNombre2);
		lblNombre2.setVisible(false);

		// etiqueta direccion
		lblDireccion = new JLabel("DIRECCION:");
		lblDireccion.setBounds(10, 153, 77, 13);
		getContentPane().add(lblDireccion);
		lblDireccion.setVisible(false);

		// field dni
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(99, 101, 96, 19);
		getContentPane().add(textFieldDNI);
		textFieldDNI.setVisible(false);

		// field nombre
		textFieldNombre2 = new JTextField();
		textFieldNombre2.setBounds(99, 125, 96, 19);
		getContentPane().add(textFieldNombre2);
		textFieldNombre2.setVisible(false);

		// field direccion
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(99, 149, 96, 19);
		getContentPane().add(textFieldDireccion);
		textFieldDireccion.setVisible(false);

		// Rellenar datos usuario
		if (IdUserModi != 0) {
			Usuario usu;
			try {
				usu = us.getUsuario(Conexion.obtener(), IdUserModi);
				textFieldNombre.setText(usu.getNombre());
				passwordField.setText(usu.getPassword());
				comboBoxRol.setEnabled(false);
				if (usu.getRol().equalsIgnoreCase("ADMIN")) {
					comboBoxRol.setSelectedIndex(0);

					deshabilitarRellenar();
				} else if (usu.getRol().equalsIgnoreCase("ALUMNO")) {
					comboBoxRol.setSelectedIndex(2);
					Estudiantes estu = es.getAlumno(Conexion.obtener(), IdUserModi);
					textFieldDNI.setText(estu.getDni());
					textFieldNombre2.setText(estu.getNombre());
					textFieldDireccion.setText(estu.getDireccion());
					habilitarRellenar();
				} else if (usu.getRol().equalsIgnoreCase("INSTRUCTOR")) {
					comboBoxRol.setSelectedIndex(1);
					Instructor instru = is.getInstructor(Conexion.obtener(), IdUserModi);
					textFieldDNI.setText(instru.getDni());
					textFieldNombre2.setText(instru.getNombre());
					textFieldDireccion.setText(instru.getDireccion());
					habilitarRellenar();
				}
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(null, "Error", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		}
		setVisible(true);
	}

	public class ManejadorItem implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			String rol = (String) comboBoxRol.getSelectedItem();
			if (rol.equalsIgnoreCase("ALUMNO") || rol.equalsIgnoreCase("INSTRUCTOR")) {
				habilitarRellenar();
			} else if (rol.equalsIgnoreCase("ADMIN")) {
				deshabilitarRellenar();
			}
		}

	}

	public class Manejador implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o == btnVolver) {
				IdUserModi = 0;
				refrescar();
			} else if (o == btnConfirmar) {

				String rol = (String) comboBoxRol.getSelectedItem();
				String nombre = textFieldNombre.getText();
				String password = passwordField.getText();

				// Actualizar usuario
				if (IdUserModi != 0) {
					try {
						us.save(Conexion.obtener(), new Usuario(IdUserModi, nombre, password, rol));
						if (!rol.equalsIgnoreCase("ADMIN")) {
							String dni = textFieldDNI.getText();
							String nombre2 = textFieldNombre2.getText();
							String direccion = textFieldDireccion.getText();
							if (rol.equalsIgnoreCase("INSTRUCTOR")) {

								is.saveUpdate(Conexion.obtener(), new Instructor(dni, nombre2, direccion, IdUserModi));
							} else if (rol.equalsIgnoreCase("ALUMNO")) {

								es.saveUpdate(Conexion.obtener(),
										new Estudiantes(dni, nombre2, direccion, IdUserModi, true));

							}
						}
						JOptionPane.showMessageDialog(InsModUser.this, "El usuario se ha Modificado correctamente",
								"Informacion", JOptionPane.INFORMATION_MESSAGE);
						IdUserModi = 0;
						refrescar();
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(InsModUser.this, "No se ha podido MODIFICAR el usuario", "ERROR",
								JOptionPane.ERROR_MESSAGE);

					}

				} else {

					// Insertar usuario
					try {
						us.save(Conexion.obtener(), new Usuario(nombre, password, rol));
						if (!rol.equalsIgnoreCase("ADMIN")) {
							String dni = textFieldDNI.getText();
							String nombre2 = textFieldNombre2.getText();
							String direccion = textFieldDireccion.getText();
							if (rol.equalsIgnoreCase("INSTRUCTOR")) {

								is.saveNewInstructor(Conexion.obtener(), new Instructor(dni, nombre2, direccion,
										us.getUsuarioNombre(Conexion.obtener(), nombre).getId()));
							} else if (rol.equalsIgnoreCase("ALUMNO")) {

								es.saveNewAlumno(Conexion.obtener(), new Estudiantes(dni, nombre2, direccion,
										us.getUsuarioNombre(Conexion.obtener(), nombre).getId(), true));
							}
						}

						IdUserModi = 0;
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
	}

	public void refrescar() {
		dispose();
		new CrudUsuarios();
	}

	public void deshabilitarRellenar() {
		lblDNI.setVisible(false);
		lblDireccion.setVisible(false);
		lblNombre2.setVisible(false);
		textFieldDNI.setVisible(false);
		textFieldDireccion.setVisible(false);
		textFieldNombre2.setVisible(false);
	}

	public void habilitarRellenar() {
		lblDNI.setVisible(true);
		lblDireccion.setVisible(true);
		lblNombre2.setVisible(true);
		textFieldDNI.setVisible(true);
		textFieldDireccion.setVisible(true);
		textFieldNombre2.setVisible(true);

	}

}
