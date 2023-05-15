package Visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import Servicios.Conexion;

/*Instructores (Modificar perfil)
Instructores (Aceptar la clase solicitada)
Instructores (Ver clases que tienen cada dia)
Instructores (Evaluar la clase de cada estudiante)
Instructores (Ver evaluacion de los distintos estudiantes)
Instructores (Dar parte de averias de un vehiculo)*/

public class InterfazInstructor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cbOpciones;
	private JList<String> jlClases;
	private JScrollPane scrListaClases, scrTablaClase, scrTextoAveria, scrTablaEvaluaciones;
	private Icon imgPerfil;
	private JPanel panelOpciones, panelVerPerfil, panelModPerfil, panelVerClases, panelAceptarClases, panelParteAveria,
			panelEvaluaciones;
	private JLabel lbImg, lbTexto, lbBienvenido, lbTextoDNIPerfil, lbTextoDireccionPerfil, lbTextoContrasenya,
			lbTextoNombre, lbTextoNombrePerfil, lbTextoDireccion, lbTextoDNIPA, lbTextoInfoPA, lbTextoIDVehiculo,
			lbTextoNombreUsuario, lbTextoNombreUsuarioPerfil;
	private JTextField textoNombrePerfil, textoDNIPerfil, textoDireccion, textoCambiarNombre, textoCambiarNombreUsuario,
			textoCambiarContrasenya, textoCambiarDireccion, textoDNIPA, textoIDVehiculo, textoNombreUsuarioPerfil;
	private JTextArea textoInfoPA;
	private JButton btnConfirmar, btnAceptar;
	private DefaultTableModel modeloTablaClases, modeloTablaEvaluaciones;
	private JTable tablaClases, tablaEvaluaciones;
	private Object[][] dataTablaClases = { { null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null } },
			dataTablaEvaluaciones = { null, null, null, null };
	private String[] cabeceraTablaClases = { "Clase", "Dia", "Hora" },
			solicitudesClases = { "opcion1", "opcion2", "opcion3" }, listaOpciones = { "Ver perfil", "Modificar Perfil",
					"Ver clases", "Aceptar Clases", "Partes de averia", "Ver evaluaciones" },
			cabeceraTablaEvaluaciones = { "Alumno", "1º Eva", "2º Eva", "3º Eva" };
	private List<String> listaSolicitudesClases = new ArrayList<>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InterfazInstructor() {
		super("Interfaz Instructor");
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 500);
		getContentPane().setLayout(null);
		getContentPane();
		setLocationRelativeTo(null);
		
		for (String s : solicitudesClases) {
			listaSolicitudesClases.add(s);
		}

		modeloTablaClases = new DefaultTableModel(dataTablaClases, cabeceraTablaClases) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modeloTablaEvaluaciones = new DefaultTableModel(dataTablaEvaluaciones, cabeceraTablaEvaluaciones) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// JTables
		tablaClases = new JTable(modeloTablaClases);
		tablaClases.setRowHeight(40);
		tablaClases.setLocation(40, 30);
		tablaClases.setSize(350, 282);
		tablaEvaluaciones = new JTable(modeloTablaEvaluaciones);
		tablaEvaluaciones.setRowHeight(40);
		tablaEvaluaciones.setLocation(40, 30);
		tablaEvaluaciones.setSize(290, 290);

		// JButtons
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(150, 350, 100, 45);
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(150, 350, 100, 45);

		// JTextFields
		textoNombrePerfil = new JTextField();
		textoNombrePerfil.setBounds(190, 125, 100, 20);
		textoNombrePerfil.setEditable(false);
		textoDireccion = new JTextField();
		textoDireccion.setBounds(190, 275, 100, 20);
		textoDireccion.setEditable(false);
		textoDNIPerfil = new JTextField();
		textoDNIPerfil.setBounds(190, 200, 100, 20);
		textoDNIPerfil.setEditable(false);
		textoCambiarContrasenya = new JTextField();
		textoCambiarContrasenya.setBounds(190, 200, 100, 20);
		textoCambiarDireccion = new JTextField();
		textoCambiarDireccion.setBounds(190, 275, 100, 20);
		textoCambiarNombre = new JTextField();
		textoCambiarNombre.setBounds(190, 125, 100, 20);
		textoCambiarNombreUsuario = new JTextField();
		textoCambiarNombreUsuario.setBounds(190, 75, 100, 20);
		textoNombreUsuarioPerfil = new JTextField();
		textoNombreUsuarioPerfil.setEditable(false);
		textoNombreUsuarioPerfil.setBounds(190, 50, 100, 20);

		// JTextArea
		textoInfoPA = new JTextArea();
		textoInfoPA.setBounds(190, 200, 150, 100);
		textoIDVehiculo = new JTextField();
		textoIDVehiculo.setBounds(190, 150, 100, 20);
		textoDNIPA = new JTextField();
		textoDNIPA.setBounds(190, 100, 100, 20);

		// JPanels
		panelVerPerfil = new JPanel();
		panelVerPerfil.setBounds(150, 0, 435, 460);
		panelOpciones = new JPanel();
		panelOpciones.setBounds(0, 0, 150, 460);
		panelModPerfil = new JPanel();
		panelModPerfil.setBounds(150, 0, 435, 460);
		panelVerClases = new JPanel();
		panelVerClases.setBounds(150, 0, 435, 460);
		panelAceptarClases = new JPanel();
		panelAceptarClases.setBounds(150, 0, 435, 460);
		panelParteAveria = new JPanel();
		panelParteAveria.setBounds(150, 0, 435, 460);
		panelEvaluaciones = new JPanel();
		panelEvaluaciones.setBounds(150, 0, 435, 460);

		// JComboBox
		cbOpciones = new JComboBox(listaOpciones);
		cbOpciones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbOpciones.setBounds(0, 205, 150, 50);

		// JList
		jlClases = new JList();
		jlClases.setListData(solicitudesClases);
		jlClases.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		jlClases.setVisibleRowCount(5);
		jlClases.setSelectedIndex(0);
		jlClases.setFixedCellWidth(100);
		jlClases.setFixedCellHeight(15);

		// JScrollPane
		scrListaClases = new JScrollPane(jlClases);
		scrListaClases.setBounds(150, 30, 150, 200);
		scrTablaClase = new JScrollPane(tablaClases);
		scrTablaClase.setBounds(10, 0, 425, 450);
		scrTextoAveria = new JScrollPane(textoInfoPA);
		scrTextoAveria.setBounds(190, 200, 200, 200);
		scrTablaEvaluaciones = new JScrollPane(tablaEvaluaciones);
		scrTablaEvaluaciones.setBounds(0, 0, 300, 300);

		// JLabel
		lbImg = new JLabel();
		lbImg.setLocation(-50, 0);
		lbImg.setSize(200, 169);
		lbImg.setIcon(new ImageIcon(InterfazInstructor.class.getResource("/Visual/imagenes/profesor.jpg")));
		lbTextoDNIPA = new JLabel();
		lbTextoDNIPA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoDNIPA.setText("DNI instructor: ");
		lbTextoDNIPA.setBounds(40, 100, 150, 20);
		lbTextoIDVehiculo = new JLabel();
		lbTextoIDVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoIDVehiculo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoIDVehiculo.setText("ID Vehiculo: ");
		lbTextoIDVehiculo.setBounds(40, 150, 150, 20);
		lbTextoInfoPA = new JLabel();
		lbTextoInfoPA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoInfoPA.setText("Datos averia: ");
		lbTextoInfoPA.setBounds(40, 200, 150, 20);
		lbTextoNombreUsuarioPerfil = new JLabel("Nombre de usuario: ");
		lbTextoNombreUsuarioPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoNombreUsuarioPerfil.setBounds(15, 50, 200, 20);

		getContentPane().add(panelVerPerfil);
		panelVerPerfil.setLayout(null);
		panelVerPerfil.add(textoNombrePerfil);
		panelVerPerfil.add(textoDNIPerfil);
		panelVerPerfil.add(textoDireccion);
		panelVerPerfil.add(textoNombreUsuarioPerfil);
		panelVerPerfil.add(lbTextoNombreUsuarioPerfil);

		lbTextoNombrePerfil = new JLabel("Nombre: ");
		lbTextoNombrePerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoNombrePerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoNombrePerfil.setBounds(80, 125, 100, 20);
		panelVerPerfil.add(lbTextoNombrePerfil);

		lbTextoDNIPerfil = new JLabel("DNI: ");
		lbTextoDNIPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoDNIPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoDNIPerfil.setBounds(80, 200, 100, 20);
		panelVerPerfil.add(lbTextoDNIPerfil);

		lbTextoDireccionPerfil = new JLabel("Direccion: ");
		lbTextoDireccionPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoDireccionPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoDireccionPerfil.setBounds(80, 275, 100, 20);
		panelVerPerfil.add(lbTextoDireccionPerfil);

		getContentPane().add(panelOpciones);
		panelOpciones.setLayout(null);
		panelOpciones.add(lbImg);
		panelOpciones.add(cbOpciones);

		getContentPane().add(panelVerClases);
		panelVerClases.setLayout(null);
		panelVerClases.add(scrTablaClase);

		getContentPane().add(panelAceptarClases);
		panelAceptarClases.setLayout(null);
		panelAceptarClases.add(scrListaClases);
		panelAceptarClases.add(btnAceptar);

		getContentPane().add(panelModPerfil);
		panelModPerfil.setLayout(null);
		panelModPerfil.add(btnConfirmar);
		panelModPerfil.add(textoCambiarContrasenya);
		panelModPerfil.add(textoCambiarDireccion);
		panelModPerfil.add(textoCambiarNombre);
		panelModPerfil.add(textoCambiarNombreUsuario);

		getContentPane().add(panelParteAveria);
		panelParteAveria.setLayout(null);
		panelParteAveria.add(textoDNIPA);
		panelParteAveria.add(textoIDVehiculo);
		panelParteAveria.add(scrTextoAveria);
		panelParteAveria.add(lbTextoIDVehiculo);
		panelParteAveria.add(lbTextoInfoPA);
		panelParteAveria.add(lbTextoDNIPA);

		getContentPane().add(panelEvaluaciones);
		panelEvaluaciones.setLayout(null);
		panelEvaluaciones.add(scrTablaEvaluaciones);

		lbTextoNombre = new JLabel("Cambiar Nombre: ");
		lbTextoNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoNombre.setBounds(50, 125, 120, 20);
		panelModPerfil.add(lbTextoNombre);

		lbTextoContrasenya = new JLabel("Cambiar Contrasenya: ");
		lbTextoContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoContrasenya.setBounds(50, 200, 140, 20);
		panelModPerfil.add(lbTextoContrasenya);

		lbTextoDireccion = new JLabel("Cambiar Direccion: ");
		lbTextoDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoDireccion.setBounds(50, 275, 140, 20);
		panelModPerfil.add(lbTextoDireccion);

		lbTextoNombreUsuario = new JLabel("Cambiar nombre de usuario: ");
		lbTextoNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoNombreUsuario.setBounds(20, 75, 170, 20);
		panelModPerfil.add(lbTextoNombreUsuario);

		lbTexto = new JLabel("Que desea hacer?");
		lbTexto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lbTexto.setBounds(0, 185, 150, 20);
		panelOpciones.add(lbTexto);

		lbBienvenido = new JLabel("Bienvenido");
		lbBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lbBienvenido.setBounds(0, 170, 150, 20);
		panelOpciones.add(lbBienvenido);

		ManejadorCB mCB = new ManejadorCB();
		cbOpciones.addActionListener(mCB);

		ManejadorBtn mBtn = new ManejadorBtn();
		btnAceptar.addActionListener(mBtn);
		btnConfirmar.addActionListener(mBtn);

		iniciarPerfil();
		
		setVisible(true);
		panelAceptarClases.setVisible(false);
		panelModPerfil.setVisible(false);
		panelVerClases.setVisible(false);
		panelEvaluaciones.setVisible(false);
		panelParteAveria.setVisible(false);
	}
	
	private void iniciarPerfil() {
		String consulta = "SELECT u.nombre, i.nombre, i.dni, i.direccion FROM usuario u, instructor i WHERE u.nombre LIKE '" + MenuPrincipal.nombreUsuario + "'";
		textoNombreUsuarioPerfil.setText("");
		textoNombrePerfil.setText("Datos");
		textoDNIPerfil.setText("datos");
		textoDireccion.setText("");
	}

	// Manejador para los JComboBox
	public class ManejadorCB implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String opcion = (String) cbOpciones.getSelectedItem();
			gestionPaneles(opcion);
		}

		// Metodo que gestiona el visionado de los paneles del JFrame
		private void gestionPaneles(String opcion) {
			if (opcion.equalsIgnoreCase("modificar perfil")) {
				panelVerPerfil.setVisible(false);
				panelAceptarClases.setVisible(false);
				panelModPerfil.setVisible(true);
				panelEvaluaciones.setVisible(false);
				panelParteAveria.setVisible(false);
				panelVerClases.setVisible(false);
			} else if (opcion.equalsIgnoreCase("ver perfil")) {
				panelVerPerfil.setVisible(true);
				panelAceptarClases.setVisible(false);
				panelModPerfil.setVisible(false);
				panelEvaluaciones.setVisible(false);
				panelParteAveria.setVisible(false);
				panelVerClases.setVisible(false);
			} else if (opcion.equalsIgnoreCase("ver clases")) {
				panelVerPerfil.setVisible(false);
				panelAceptarClases.setVisible(false);
				panelModPerfil.setVisible(false);
				panelEvaluaciones.setVisible(false);
				panelParteAveria.setVisible(false);
				panelVerClases.setVisible(true);
			} else if (opcion.equalsIgnoreCase("aceptar clases")) {
				panelVerPerfil.setVisible(false);
				panelAceptarClases.setVisible(true);
				panelModPerfil.setVisible(false);
				panelEvaluaciones.setVisible(false);
				panelParteAveria.setVisible(false);
				panelVerClases.setVisible(false);
			} else if (opcion.equalsIgnoreCase("Partes de averia")) {
				panelVerPerfil.setVisible(false);
				panelAceptarClases.setVisible(false);
				panelModPerfil.setVisible(false);
				panelEvaluaciones.setVisible(false);
				panelParteAveria.setVisible(true);
				panelVerClases.setVisible(false);
			} else if (opcion.equalsIgnoreCase("ver evaluaciones")) {
				panelVerPerfil.setVisible(false);
				panelAceptarClases.setVisible(false);
				panelModPerfil.setVisible(false);
				panelEvaluaciones.setVisible(true);
				panelParteAveria.setVisible(false);
				panelVerClases.setVisible(false);
			}
		}
	}

	// Manejador para los botones
	public class ManejadorBtn implements ActionListener {
		List<String> listaClases = jlClases.getSelectedValuesList();
		String nombre, contrasenya, direccion, nombreUsuario;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnAceptar)) {
				for (String s : listaClases) {
					listaSolicitudesClases.remove(s);
				}
				for (int i = 0; i < listaSolicitudesClases.size(); i++) {
					solicitudesClases[i] = listaSolicitudesClases.get(i);
				}
				JOptionPane.showMessageDialog(null, "Las clases han sido aceptadas correctamente");
				// Mandar datos a la base de datos
			} else if (e.getSource().equals(btnConfirmar)) {
				nombreUsuario = textoCambiarNombreUsuario.getText();
				nombre = textoCambiarNombre.getText();
				contrasenya = textoCambiarContrasenya.getText();
				direccion = textoCambiarDireccion.getText();
				if (nombre.isEmpty() || direccion.isEmpty() || contrasenya.isEmpty() || nombreUsuario.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Faltan datos");
				} else {
					textoCambiarNombreUsuario.setText("");
					textoCambiarNombre.setText("");
					textoCambiarContrasenya.setText("");
					textoCambiarDireccion.setText("");
					// Mandar datos a la base de datos
				}
			}
		}
	}
}