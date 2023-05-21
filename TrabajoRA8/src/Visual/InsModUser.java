package Visual;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InsModUser extends JFrame{
	private JLabel lblNombre,lblPassword,lblRol;
	private JButton btnConfirmar,btnOjo;
	private JTextField textFieldNombre;
	private JPasswordField passwordField;
	private JComboBox comboBoxRol;
	

	public InsModUser() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(pruebas.class.getResource("/Visual/imagenes/admin.jpg")));
		setBounds(100, 100, 331, 231);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//etiqueta nombre
		 lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(53, 36, 45, 13);
		getContentPane().add(lblNombre);
		//etiqueta contrasenya
		 lblPassword = new JLabel("Contrasenya:");
		lblPassword.setBounds(53, 98, 77, 13);
		getContentPane().add(lblPassword);
		//etiqueta rol
		 lblRol = new JLabel("Rol:");
		lblRol.setBounds(53, 66, 45, 13);
		getContentPane().add(lblRol);
		//boton confirmar
		 btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(104, 138, 96, 21);
		getContentPane().add(btnConfirmar);
		//field nombre
		textFieldNombre = new JTextField(45);
		textFieldNombre.setBounds(126, 33, 96, 19);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		//combobox rol
		comboBoxRol = new JComboBox();
		comboBoxRol.setBounds(126, 62, 96, 21);
		getContentPane().add(comboBoxRol);
		comboBoxRol.addItem("ADMIN");
		comboBoxRol.addItem("INSTRUCTOR");
		comboBoxRol.addItem("ALUMNO");
		//password field
		passwordField = new JPasswordField();
		passwordField.setBounds(126, 95, 96, 19);
		getContentPane().add(passwordField);
		//boton mostrar/ocultar
		JButton btnOjo = new JButton("");
		ImageIcon icon = new ImageIcon(pruebas.class.getResource("/Visual/imagenes/ojopassword.png"));
		Image image = icon.getImage().getScaledInstance(24, 21, Image.SCALE_SMOOTH);
		btnOjo.setIcon(new ImageIcon(image));
		btnOjo.setBounds(232, 94, 24, 21);
		getContentPane().add(btnOjo);
		setVisible(true);
	}

}
