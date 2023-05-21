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

import Modelos.Usuario;
import Servicios.Conexion;
import Servicios.EstudianteService;
import Servicios.InstructorService;
import Servicios.UsuarioService;

@SuppressWarnings("serial")
public class InsModUser extends JFrame {
	private JLabel lblNombre, lblPassword, lblRol, lblDNI, lblDireccion;
	private JButton btnConfirmar, btnVolver;
	private JTextField textFieldNombre, passwordField, textFieldDNI, textFieldDireccion;
	private JComboBox<String> comboBoxRol;
	// Instancia
	Manejador ma = new Manejador();
	ManejadorItem mi=new ManejadorItem();
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
		lblNombre.setBounds(10, 13, 56, 13);
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
		btnConfirmar.setBounds(45, 163, 96, 21);
		btnConfirmar.addActionListener(ma);
		getContentPane().add(btnConfirmar);
		// field nombre
		textFieldNombre = new JTextField(45);
		textFieldNombre.setBounds(99, 10, 96, 19);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		// combobox rol
		comboBoxRol = new JComboBox<String>();
		comboBoxRol.setBounds(99, 39, 96, 21);
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
		btnVolver.setBounds(179, 163, 96, 21);
		btnVolver.addActionListener(ma);
		getContentPane().add(btnVolver);

		// etiqueta dni
		lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(10, 106, 77, 13);
		getContentPane().add(lblDNI);
		lblDNI.setVisible(false);

		// etiqueta direccion
		lblDireccion = new JLabel("DIRECCION:");
		lblDireccion.setBounds(10, 129, 77, 13);
		getContentPane().add(lblDireccion);
		lblDireccion.setVisible(false);

		// field dni
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(99, 101, 96, 19);
		getContentPane().add(textFieldDNI);
		textFieldDNI.setVisible(false);

		// field direccion
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(99, 126, 96, 19);
		getContentPane().add(textFieldDireccion);
		textFieldDireccion.setVisible(false);

		setVisible(true);
	}
	public class ManejadorItem implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
            String rol = (String) comboBoxRol.getSelectedItem();
            if(rol.equalsIgnoreCase("ALUMNO")||rol.equalsIgnoreCase("INSTRUCTOR")) {
				habilitarRellenar();
			}else if(rol.equalsIgnoreCase("ADMIN")){
				deshabilitarRellenar();
			}
		}
		
	}
	public class Manejador implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o == btnVolver) {
				new CrudUsuarios();
				dispose();
			} else if (o == btnConfirmar) {
				String rol=(String) comboBoxRol.getSelectedItem();
				String nombre = textFieldNombre.getText();
				String password = passwordField.getText();

				// Insertar usuario
				try {
					us.save(Conexion.obtener(), new Usuario(nombre, password, rol));
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

	public void deshabilitarRellenar() {
		lblDNI.setVisible(false);
		lblDireccion.setVisible(false);
		textFieldDNI.setVisible(false);
		textFieldDireccion.setVisible(false);		
	}

	public void habilitarRellenar() {
		lblDNI.setVisible(true);
		lblDireccion.setVisible(true);
		textFieldDNI.setVisible(true);
		textFieldDireccion.setVisible(true);
	}

}
