package Visual;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class pruebas {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

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
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 155, 463);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel foto = new JLabel("New label");
		foto.setBounds(-60, -23, 209, 142);
		panel.add(foto);
		foto.setIcon(new ImageIcon(pruebas.class.getResource("/Visual/imagenes/admin.jpg")));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(5, 182, 123, 21);
		panel.add(comboBox);
		
		JLabel saludo = new JLabel("Bienvenido, administrador");
		saludo.setBounds(5, 131, 131, 27);
		panel.add(saludo);
		
		JLabel opciones = new JLabel("¿Qué deseas hacer?");
		opciones.setBounds(15, 144, 103, 41);
		panel.add(opciones);
		
		JButton volver = new JButton("Volver");
		volver.setEnabled(false);
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		volver.setBounds(15, 389, 113, 51);
		panel.add(volver);
		
		JPanel paneles = new JPanel();
		paneles.setBounds(153, 0, 433, 463);
		frame.getContentPane().add(paneles);
		paneles.setLayout(null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(195, 90, 168, 21);
		paneles.add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("Introduce la nueva clave:");
		lblNewLabel.setBounds(59, 41, 126, 13);
		paneles.add(lblNewLabel);
		
		JLabel lblIntroduzcalaDeNuevo = new JLabel("Introduzcala de nuevo:");
		lblIntroduzcalaDeNuevo.setBounds(59, 64, 105, 13);
		paneles.add(lblIntroduzcalaDeNuevo);
		
		textField = new JTextField();
		textField.setBounds(195, 38, 153, 19);
		paneles.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(195, 61, 153, 19);
		paneles.add(textField_1);
		
		JButton btnNewButton = new JButton("Reestablecer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(122, 405, 163, 35);
		paneles.add(btnNewButton);
		
		JLabel lblEligeAlEstudiante = new JLabel("Elige al estudiante:");
		lblEligeAlEstudiante.setBounds(59, 94, 105, 13);
		paneles.add(lblEligeAlEstudiante);
	}
}
