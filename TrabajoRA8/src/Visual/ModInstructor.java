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

public class ModInstructor extends JFrame {
	private JTextField txtNombre, txtDni, txtDireccion;
	private JLabel lblNombre, lblDni, lblDireccion;
	private InstructorService is = new InstructorService();
	private JButton btnAceptar, btnCancelar;
	private static int id_instructor;
	private ManejadorBotones mb = new ManejadorBotones();

	public ModInstructor(int id) {
		super("Modificar instructor");
		initialize();
		id_instructor = id;

	}

	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(pruebas.class.getResource("/Visual/imagenes/admin.jpg")));
		setBounds(100, 100, 331, 231);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		txtNombre = new JTextField(45);
		txtNombre.setBounds(140, 35, 133, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(10, 37, 73, 13);
		getContentPane().add(lblNombre);

		txtDni = new JTextField(45);
		txtDni.setBounds(140, 64, 133, 19);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);

		lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDni.setBounds(10, 66, 73, 13);
		getContentPane().add(lblDni);

		txtDireccion = new JTextField(45);
		txtDireccion.setBounds(140, 94, 133, 19);
		getContentPane().add(txtDireccion);
		txtDireccion.setColumns(10);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccion.setBounds(10, 96, 73, 13);
		getContentPane().add(lblDireccion);

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
				insertar(id_instructor);
				new CrudInstructor();
				dispose();
			} else if (o.equals(btnCancelar)) {
				new CrudInstructor();
				dispose();
			}

		}

		private void insertar(int id) {
			if (!txtNombre.getText().equals("") && !txtDni.getText().equals("") && !txtDireccion.getText().equals("")) {
				if (txtDni.getText().matches("[0-9]{8}[A-Z]")) {
					try {
						is.saveUpdate(Conexion.obtener(),
								new Instructor(txtNombre.getText(), txtDni.getText(), txtDireccion.getText(), id));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error al modificar el instructor", "Error", JOptionPane.ERROR_MESSAGE);
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
