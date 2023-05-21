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

public class InsModVehiculo extends JFrame {
	private JTextField textFieldModelo;
	private JLabel lblModelo, lblTipo, lblImagen;
	private JComboBox comboBoxTipo;
	private JButton btnSeleccionar, btnConfirmar;

	// Extension de la imagenes
	private String extension;
	private Path sourcer, destination;
	// Manejador Insertar imagen
	insertImg insIma = new insertImg();
	ManeInsert mi =new ManeInsert(); 

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

		textFieldModelo = new JTextField(45);
		textFieldModelo.setBounds(140, 26, 133, 19);
		getContentPane().add(textFieldModelo);
		textFieldModelo.setColumns(10);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(10, 29, 45, 13);
		getContentPane().add(lblModelo);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 63, 45, 13);
		getContentPane().add(lblTipo);

		JLabel lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(10, 99, 45, 13);
		getContentPane().add(lblImagen);

		comboBoxTipo = new JComboBox();
		comboBoxTipo.addItem("Coche");
		comboBoxTipo.addItem("Moto");
		comboBoxTipo.addItem("Camion");
		comboBoxTipo.setBounds(140, 59, 133, 21);
		getContentPane().add(comboBoxTipo);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(150, 95, 107, 21);
		btnSeleccionar.addActionListener(insIma);
		getContentPane().add(btnSeleccionar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(79, 143, 100, 21);
		btnConfirmar.addActionListener(mi);
		getContentPane().add(btnConfirmar);
		setVisible(true);

	}

	// Comprobar repetidos
//		public boolean getRepetido() throws FileNotFoundException, IOException, ClassNotFoundException {
//			boolean testeado = true;
//			if (games.exists()) {
//				File file = new File("ficheros/games");
//				FileReader fr = new FileReader(file);
//				if (fr.read() > 0) {
//					ObjectInputStream is = new ObjectInputStream(new FileInputStream(games));
//					PerfilJuego juegox = (PerfilJuego) is.readObject();
//					try {
//						while (juegox != null) {
//							if (juegox.getNombre().equals(jtNombre.getText()))
//								testeado = false;
//							juegox = (PerfilJuego) is.readObject();
//						}
//					} catch (Exception ex) {
//					}
//					is.close();
//					fr.close();
//					if (testeado == false)
//						JOptionPane.showMessageDialog(null, "Este nombre ya se encuentra en la base de datos", "",
//								JOptionPane.ERROR_MESSAGE);
//				}
//			}
//			return testeado;
//		}

	public class ManeInsert implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

//				if (getRepetido() == true && comprobarDatos() == true) {
			try {
				String modelo = textFieldModelo.getText();
//				String resEdad="Deportivo";
//				System.out.println("EL CTIPO ES: "+comboBoxTipo.getSelectedItem().toString());
				String tipoVehi = (String) comboBoxTipo.getSelectedItem();
				String dirImg = "imagen/vehiculos/" + textFieldModelo.getText() + extension;

				// Insertar vehiculo
				try {
					vs.saveNewVehiculo(Conexion.obtener(), new Vehiculo(dirImg,modelo,tipoVehi));
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(InsModVehiculo.this, "No se ha podido insertar", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
				JOptionPane.showMessageDialog(InsModVehiculo.this, "El juego se ha insertado correctamente...", "",
						JOptionPane.INFORMATION_MESSAGE);

				if (sourcer != null)

					Files.copy(sourcer, destination);

//				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
				JOptionPane.showMessageDialog(null, "Imagen añadida", "", JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

}
