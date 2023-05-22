package Visual;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Modelos.Instructor;
import Servicios.Conexion;
import Servicios.InstructorService;

public class InsInstructor extends JFrame {
	private JTextField txtNombre, txtDni, txtDireccion, txtId;
	private JLabel lblNombre, lblDni, lblDireccion, lblId;
	private InstructorService is = new InstructorService();
	private JButton btnAceptar, btnCancelar;
	private ManejadorBotones mb = new ManejadorBotones();

	public InsInstructor() {
		super("Insertar instructor");
		initialize();
	}

	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(pruebas.class.getResource("/Visual/imagenes/admin.jpg")));
		setBounds(100, 100, 331, 231);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		txtNombre = new JTextField(45);
		txtNombre.setBounds(140, 21, 133, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(10, 23, 73, 13);
		getContentPane().add(lblNombre);

		txtDni = new JTextField(45);
		txtDni.setBounds(140, 53, 133, 19);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);

		lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDni.setBounds(10, 55, 73, 13);
		getContentPane().add(lblDni);

		txtDireccion = new JTextField(45);
		txtDireccion.setBounds(140, 82, 133, 19);
		getContentPane().add(txtDireccion);
		txtDireccion.setColumns(10);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccion.setBounds(10, 85, 73, 13);
		getContentPane().add(lblDireccion);

		txtId = new JTextField(45);
		txtId.setBounds(140, 111, 133, 19);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblId.setBounds(10, 113, 73, 13);
		getContentPane().add(lblId);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 149, 119, 35);
		btnAceptar.addActionListener(mb);
		getContentPane().add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(188, 149, 119, 35);
		btnCancelar.addActionListener(mb);
		getContentPane().add(btnCancelar);

		setVisible(true);

	}

	private class ManejadorBotones implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o.equals(btnAceptar)) {
				insertar();
				new CrudInstructor();
				dispose();
			} else if (o.equals(btnCancelar)) {
				new CrudInstructor();
				dispose();
			}

		}

		private void insertar() {
			if (!txtNombre.getText().equals("") && !txtDni.getText().equals("") && !txtDireccion.getText().equals("")
					&& !txtId.getText().equals("")) {
				if (txtDni.getText().matches("[0-9]{8}[A-Z]")) {
					try {
						is.saveNewInstructor(Conexion.obtener(), new Instructor(txtDni.getText(), txtNombre.getText(), 
								txtDireccion.getText(), Integer.parseInt(txtId.getText())));
					} catch (ClassNotFoundException | SQLException e) {
						JOptionPane.showMessageDialog(null, "Error al crear el instructor", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(null, "DNI introducido no valido", "Error",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "No puedes dejar campos sin rellenar", "Error",
						JOptionPane.ERROR_MESSAGE);
		}

	}

}
