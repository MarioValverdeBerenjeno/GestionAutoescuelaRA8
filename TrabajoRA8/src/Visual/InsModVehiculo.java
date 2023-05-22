package Visual;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Modelos.Vehiculo;
import Servicios.Conexion;
import Servicios.VehiculoService;

@SuppressWarnings("serial")
public class InsModVehiculo extends JFrame {
	private JTextField textFieldModelo;
	private JLabel lblModelo, lblTipo, lblImagen;
	private JComboBox<String> comboBoxTipo;
	private JButton btnSeleccionar, btnConfirmar, btnVolver;

	static int idModificar;

	// Extension de la imagenes
	private String extension;
	private Path sourcer, destination;
	// Manejador Insertar imagen
	insertImg insIma = new insertImg();
	Manejador ma = new Manejador();

	// Servicios
	VehiculoService vs = new VehiculoService();

	public InsModVehiculo() {
		initialize();
	}

	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(pruebas.class.getResource("/Visual/imagenes/admin.jpg")));
		setBounds(100, 100, 331, 231);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		// Field modelo
		textFieldModelo = new JTextField(45);
		textFieldModelo.setBounds(140, 26, 133, 19);
		getContentPane().add(textFieldModelo);
		textFieldModelo.setColumns(10);
		// label modelo
		lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(10, 29, 50, 13);
		getContentPane().add(lblModelo);
		// label tipo
		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 63, 45, 13);
		getContentPane().add(lblTipo);
		// label imagen
		lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(10, 99, 45, 13);
		getContentPane().add(lblImagen);
		// combobox tipo vehiculo
		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.addItem("Coche");
		comboBoxTipo.addItem("Moto");
		comboBoxTipo.addItem("Camion");
		comboBoxTipo.setBounds(140, 59, 133, 21);
		getContentPane().add(comboBoxTipo);
		// boton seleccionar
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(150, 95, 107, 21);
		btnSeleccionar.addActionListener(insIma);
		getContentPane().add(btnSeleccionar);
		// boton confirmar
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(53, 140, 96, 21);
		btnConfirmar.addActionListener(ma);
		getContentPane().add(btnConfirmar);
		// boton volver
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(187, 140, 96, 21);
		btnVolver.addActionListener(ma);
		getContentPane().add(btnVolver);

		// Rellenar si es Update de Vehiculo
		if (idModificar != 0) {
			try {
				Vehiculo vehi = vs.getVehiculo(Conexion.obtener(), idModificar);
				textFieldModelo.setText(vehi.getModelo());
				if (vehi.getTipo().equalsIgnoreCase("COCHE")) {
					comboBoxTipo.setSelectedIndex(0);
				} else if (vehi.getTipo().equalsIgnoreCase("MOTO")) {
					comboBoxTipo.setSelectedIndex(1);
				} else if (vehi.getTipo().equalsIgnoreCase("CAMION")) {
					comboBoxTipo.setSelectedIndex(2);
				}
				File imagenes = new File(vehi.getImagenVehiculo());
				destination = imagenes.toPath();
				comboBoxTipo.setSelectedIndex(0);

			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(null, "Error", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		setVisible(true);

	}

	public class Manejador implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			Object o = e.getSource();
			if (o == btnVolver) {
				idModificar = 0;
				dispose();
				new CrudVehiculo();

			} else if (o == btnConfirmar) {
				try {
					String modelo = textFieldModelo.getText();
					String tipoVehi = (String) comboBoxTipo.getSelectedItem();
					String dirImg = "imagen/vehiculos/" + textFieldModelo.getText() + extension;
					// Modificar si pulsa el boton
					if (idModificar != 0) {
						try {
							vs.saveUpdate(Conexion.obtener(), new Vehiculo(idModificar, dirImg, modelo, tipoVehi));

						} catch (ClassNotFoundException | SQLException e1) {
							JOptionPane.showMessageDialog(InsModVehiculo.this, "No se ha podido MODIFICAR", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(InsModVehiculo.this, "El vehiculo se ha MODIFICADO correctamente",
								"Informacion", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new CrudVehiculo();
						idModificar = 0;
					} else {
						// Insertar vehiculo
						try {
							vs.saveNewVehiculo(Conexion.obtener(), new Vehiculo(dirImg, modelo, tipoVehi));
						} catch (ClassNotFoundException | SQLException e1) {
							JOptionPane.showMessageDialog(InsModVehiculo.this, "No se ha podido insertar", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(InsModVehiculo.this, "El vehiculo se ha insertado correctamente",
								"Informacion", JOptionPane.INFORMATION_MESSAGE);
					}
					if (sourcer != null)
						Files.copy(sourcer, destination);

//				}

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(InsModVehiculo.this, "Error", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				refrescar();
			}
		}
	}

	public class insertImg implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter soloImg = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "jpeg");
			fileChooser.setFileFilter(soloImg);
			fileChooser.showSaveDialog(null);

			if (fileChooser.getSelectedFile() != null) {
				extension = fileChooser.getSelectedFile().toString()
						.substring(fileChooser.getSelectedFile().toString().lastIndexOf('.'));
				File imagenes = new File("imagen/vehiculos/" + textFieldModelo.getText().replace(" ", "") + extension);
				sourcer = fileChooser.getSelectedFile().getAbsoluteFile().toPath();
				destination = imagenes.toPath();
				JOptionPane.showMessageDialog(null, "Imagen añadida", "INFO", JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

	public void refrescar() {
		dispose();
		new CrudVehiculo();
	}

}
