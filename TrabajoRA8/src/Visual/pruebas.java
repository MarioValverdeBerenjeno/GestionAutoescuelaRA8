package Visual;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class pruebas {

	private JFrame frame;
	private JTextField textFieldNombre,passwordField;
	private JTextField textFieldDNI;
	private JTextField textFieldDireccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pruebas window = new pruebas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pruebas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(pruebas.class.getResource("/Visual/imagenes/admin.jpg")));
//		frame.setSize(773,604);
		frame.setBounds(100, 100, 331, 231);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 13, 56, 13);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblPassword = new JLabel("Contrasenya:");
		lblPassword.setBounds(10, 75, 77, 13);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(10, 43, 45, 13);
		frame.getContentPane().add(lblRol);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(45, 163, 96, 21);
		frame.getContentPane().add(btnConfirmar);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(99, 10, 96, 19);
		frame.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JComboBox comboBoxRol = new JComboBox();
		comboBoxRol.setBounds(99, 39, 96, 21);
		frame.getContentPane().add(comboBoxRol);
		
		passwordField = new JTextField();
		passwordField.setBounds(97, 72, 96, 19);
		frame.getContentPane().add(passwordField);
		ImageIcon icon = new ImageIcon(pruebas.class.getResource("/Visual/imagenes/ojopassword.png"));
		Image image = icon.getImage().getScaledInstance(24, 21, Image.SCALE_SMOOTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(179, 163, 96, 21);
		frame.getContentPane().add(btnVolver);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(10, 106, 77, 13);
		frame.getContentPane().add(lblDNI);
		
		JLabel lblDireccion = new JLabel("DIRECCION:");
		lblDireccion.setBounds(10, 129, 77, 13);
		frame.getContentPane().add(lblDireccion);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(99, 101, 96, 19);
		frame.getContentPane().add(textFieldDNI);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(99, 126, 96, 19);
		frame.getContentPane().add(textFieldDireccion);
	}
}
