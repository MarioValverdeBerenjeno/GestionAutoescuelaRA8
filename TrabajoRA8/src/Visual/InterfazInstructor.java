package Visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Modelos.ClaseConducir;
import Modelos.Estudiantes;
import Modelos.Evaluacion;
import Modelos.Instructor;
import Modelos.ParteAveria;
import Modelos.Usuario;
import Servicios.AveriaService;
import Servicios.ClaseConducirService;
import Servicios.Conexion;
import Servicios.EstudianteService;
import Servicios.InstructorService;
import Servicios.UsuarioService;

/*Instructores (Modificar perfil)
Instructores (Aceptar la clase solicitada)
Instructores (Ver clases que tienen cada dia)
Instructores (Evaluar la clase de cada estudiante)
Instructores (Ver evaluacion de los distintos estudiantes)
Instructores (Dar parte de averias de un vehiculo)*/

public class InterfazInstructor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cbOpciones, cbIdClases;
	private JComboBox<Estudiantes> cbEstudiantes;
	private static JList<Evaluacion> jlClases;
	private JScrollPane scrListaClases, scrTablaClase, scrTextoAveria, scrTablaEvaluaciones;
	private Icon imgPerfil, imgRecargar;
	private JPanel panelOpciones, panelVerPerfil, panelModPerfil, panelVerClases, panelAceptarClases, panelParteAveria,
			panelVerEvaluaciones, panelEvaluar;
	private JLabel lbImg, lbTexto, lbBienvenido, lbTextoDNIPerfil, lbTextoDireccionPerfil, lbTextoContrasenya,
			lbTextoNombre, lbTextoNombrePerfil, lbTextoDireccion, lbTextoDNIPA, lbTextoInfoPA, lbTextoIDVehiculo,
			lbTextoNombreUsuario, lbTextoNombreUsuarioPerfil, lbTextoCambiarDNI, lbTextoRepetirContrasenya,
			lbModificarPerfil, lbVerPerfil, lbNota, lbEvaluar;
	private JTextField textoNombrePerfil, textoDNIPerfil, textoDireccion, textoCambiarNombre, textoCambiarNombreUsuario,
			textoCambiarDireccion, textoDNIPA, textoIDVehiculo, textoNombreUsuarioPerfil, textoCambiarDNI, textoNota;
	private JPasswordField textoCambiarContrasenya, textoRepetirContrasenya;
	private JTextArea textoInfoPA;
	private JButton btnConfirmar, btnAceptar, btnContrasenyaVisible, btnEnviar, btnConfirmarNota, btnRecargarPag;
	private DefaultTableModel modeloTablaClases, modeloTablaEvaluaciones;
	private DefaultComboBoxModel<String> modeloCBIdClases;
	private DefaultComboBoxModel<Estudiantes> modeloCBEstudiante;
	private JTable tablaClases, tablaEvaluaciones;
	private Object[][] dataTablaClases, dataTablaEvaluaciones;
	private String[] cabeceraTablaClases = { "Clase", "Dia", "Hora" },
			listaOpciones = { "Ver perfil", "Modificar Perfil", "Ver clases", "Aceptar Clases", "Partes de averia",
					"Ver evaluaciones", "Evaluar" },
			cabeceraTablaEvaluaciones = { "Estudiante", "Notas" }, arraySolicitudesClases;
	private List<String> dnisEstudiantes = new ArrayList<>(), ids_clases = new ArrayList<>(),
			ids_clases_alumno = new ArrayList<>();
	private List<Estudiantes> listaEstudiantes = new ArrayList<>(), listaEstudiantesProfesor = new ArrayList<>();
	private List<Evaluacion> listaEvaluaciones = new ArrayList<>();
	private static List<Evaluacion> solicitudesClases = new ArrayList<>();
	private int id_instructor, cont = 0;
	private String dniInstructor, nombreInstructor;
	private boolean esVisible = true;
	private InstructorService instructorService = new InstructorService();
	private UsuarioService usuarioService = new UsuarioService();
	private ClaseConducirService claseConducirService = new ClaseConducirService();
	private AveriaService averiaService = new AveriaService();
	private EstudianteService estudianteService = new EstudianteService();

	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public InterfazInstructor(String nombre) {
		super("Interfaz Instructor");
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 500);
		getContentPane().setLayout(null);
		getContentPane();
		setLocationRelativeTo(null);

		obtenerIDInstructor(nombre);
		obtenerDNIInstructor(nombre);
		obtenerSolicitudes();
		/*
		 * for (Evaluacion e : solicitudesClases) { arraySolicitudesClases[] = e; }
		 */

		nombreInstructor = nombre;

		modeloTablaEvaluaciones = new DefaultTableModel(dataTablaEvaluaciones, cabeceraTablaEvaluaciones) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		try {
			listaEstudiantes = estudianteService.getAllAlumnos(Conexion.obtener());
			for (Estudiantes estudiante : listaEstudiantes) {
				if (estudiante.getDni().equals(obtenerDNIEstudianteSolicitud(nombre).get(cont++))) {
					listaEstudiantesProfesor.add(estudiante);
					for (Evaluacion evaluacion : listaEvaluaciones) {
						String[] datos = { estudiante.getNombre(), String.valueOf(evaluacion.getEvaluacion()) };
						modeloTablaEvaluaciones.addRow(datos);
					}
				}
				obtenerIdClase(estudiante.getDni());
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		modeloCBEstudiante = new DefaultComboBoxModel<>();
		modeloCBEstudiante.addAll(listaEstudiantesProfesor);
		modeloCBIdClases = new DefaultComboBoxModel<>();

		dataTablaEvaluaciones = new Object[dimensionTablaClases()][listaEvaluaciones.size()];

		// Inicializacion de la tabla de las clases que tiene el instructor
		dataTablaClases = new Object[dimensionTablaClases()][3];
		verClases();

		modeloTablaClases = new DefaultTableModel(dataTablaClases, cabeceraTablaClases) {
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
		btnContrasenyaVisible = new JButton();
		btnContrasenyaVisible.setBounds(350, 300, 30, 30);
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(50, 350, 60, 20);
		btnConfirmarNota = new JButton("Confirmar");
		btnConfirmarNota.setBounds(150, 400, 100, 25);
		btnRecargarPag = new JButton();
		btnRecargarPag.setIcon(new ImageIcon(InterfazInstructor.class.getResource("/Visual/imagenes/recargar.PNG")));
		btnRecargarPag.setBounds(300, 350, 50, 45);

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
		textoCambiarDireccion = new JTextField();
		textoCambiarDireccion.setBounds(190, 225, 100, 20);
		textoCambiarNombre = new JTextField();
		textoCambiarNombre.setBounds(190, 125, 100, 20);
		textoCambiarNombreUsuario = new JTextField();
		textoCambiarNombreUsuario.setBounds(300, 100, 100, 20);
		textoCambiarDNI = new JTextField();
		textoCambiarDNI.setBounds(150, 200, 100, 20);
		textoNombreUsuarioPerfil = new JTextField();
		textoNombreUsuarioPerfil.setEditable(false);
		textoNombreUsuarioPerfil.setBounds(190, 50, 100, 20);
		textoNota = new JTextField();
		textoNota.setBounds(350, 100, 50, 20);

		// JPasswordField
		textoCambiarContrasenya = new JPasswordField();
		textoCambiarContrasenya.setBounds(190, 150, 100, 20);
		textoRepetirContrasenya = new JPasswordField();
		textoRepetirContrasenya.setBounds(240, 175, 100, 20);

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
		panelModPerfil.setVisible(false);
		panelVerClases = new JPanel();
		panelVerClases.setBounds(150, 0, 435, 460);
		panelVerClases.setVisible(false);
		panelAceptarClases = new JPanel();
		panelAceptarClases.setBounds(150, 0, 435, 460);
		panelAceptarClases.setVisible(false);
		panelParteAveria = new JPanel();
		panelParteAveria.setBounds(150, 0, 435, 460);
		panelParteAveria.setVisible(false);
		panelVerEvaluaciones = new JPanel();
		panelVerEvaluaciones.setBounds(150, 0, 435, 460);
		panelVerEvaluaciones.setVisible(false);
		panelEvaluar = new JPanel();
		panelEvaluar.setBounds(150, 0, 435, 460);
		panelEvaluar.setVisible(false);

		// JComboBox
		cbOpciones = new JComboBox(listaOpciones);
		cbOpciones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbOpciones.setBounds(0, 205, 150, 50);
		cbEstudiantes = new JComboBox<Estudiantes>(modeloCBEstudiante);
		cbEstudiantes.setBounds(20, 100, 125, 50);
		cbEstudiantes.setSelectedIndex(0);
		cbIdClases = new JComboBox(modeloCBIdClases);
		cbIdClases.setBounds(145, 100, 125, 50);

		// JList
		jlClases = new JList(solicitudesClases.toArray());
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
		lbTextoCambiarDNI = new JLabel("DNI:");
		lbTextoCambiarDNI.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoCambiarDNI.setLocation(50, 200);
		lbTextoCambiarDNI.setSize(120, 20);
		lbTextoRepetirContrasenya = new JLabel("Repite contrasenya:");
		lbTextoRepetirContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoRepetirContrasenya.setBounds(50, 175, 200, 20);
		lbModificarPerfil = new JLabel("Modificar perfil");
		lbModificarPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbModificarPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lbModificarPerfil.setBounds(150, 10, 150, 25);
		lbVerPerfil = new JLabel("Perfil");
		lbVerPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbVerPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lbVerPerfil.setBounds(150, 10, 150, 25);
		lbNota = new JLabel("Nota:");
		lbNota.setBounds(275, 100, 58, 20);
		lbEvaluar = new JLabel("Evaluar");
		lbEvaluar.setHorizontalAlignment(SwingConstants.CENTER);
		lbEvaluar.setBounds(150, 10, 150, 30);

		rellenarPerfil();
		rellenarCambiarPerfil();

		getContentPane().add(panelVerPerfil);
		panelVerPerfil.setLayout(null);
		panelVerPerfil.add(textoNombrePerfil);
		panelVerPerfil.add(textoDNIPerfil);
		panelVerPerfil.add(textoDireccion);
		panelVerPerfil.add(textoNombreUsuarioPerfil);
		panelVerPerfil.add(lbTextoNombreUsuarioPerfil);
		panelVerPerfil.add(lbVerPerfil);

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
		panelAceptarClases.add(btnRecargarPag);

		getContentPane().add(panelModPerfil);
		panelModPerfil.setLayout(null);
		panelModPerfil.add(btnConfirmar);
		panelModPerfil.add(btnContrasenyaVisible);
		panelModPerfil.add(textoCambiarContrasenya);
		panelModPerfil.add(textoCambiarDireccion);
		panelModPerfil.add(textoCambiarNombre);
		panelModPerfil.add(textoCambiarNombreUsuario);
		panelModPerfil.add(textoCambiarContrasenya);
		panelModPerfil.add(textoRepetirContrasenya);
		panelModPerfil.add(textoCambiarDNI);
		panelModPerfil.add(lbTextoRepetirContrasenya);
		panelModPerfil.add(lbTextoCambiarDNI);
		panelModPerfil.add(lbModificarPerfil);

		getContentPane().add(panelParteAveria);
		panelParteAveria.setLayout(null);
		panelParteAveria.add(textoDNIPA);
		panelParteAveria.add(textoIDVehiculo);
		panelParteAveria.add(scrTextoAveria);
		panelParteAveria.add(lbTextoIDVehiculo);
		panelParteAveria.add(lbTextoInfoPA);
		panelParteAveria.add(lbTextoDNIPA);
		panelParteAveria.add(btnEnviar);

		getContentPane().add(panelVerEvaluaciones);
		panelVerEvaluaciones.setLayout(null);
		panelVerEvaluaciones.add(scrTablaEvaluaciones);

		getContentPane().add(panelEvaluar);
		panelEvaluar.setLayout(null);
		panelEvaluar.add(btnConfirmarNota);
		panelEvaluar.add(textoNota);
		panelEvaluar.add(cbEstudiantes);
		panelEvaluar.add(lbNota);
		panelEvaluar.add(cbIdClases);
		panelEvaluar.add(lbEvaluar);

		lbTextoNombre = new JLabel("Nombre: ");
		lbTextoNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoNombre.setBounds(50, 125, 120, 20);
		panelModPerfil.add(lbTextoNombre);

		lbTextoContrasenya = new JLabel("Contrasenya: ");
		lbTextoContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoContrasenya.setBounds(50, 150, 140, 20);
		panelModPerfil.add(lbTextoContrasenya);

		lbTextoDireccion = new JLabel("Direccion: ");
		lbTextoDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoDireccion.setBounds(50, 225, 140, 20);
		panelModPerfil.add(lbTextoDireccion);

		lbTextoNombreUsuario = new JLabel("Nombre de usuario:");
		lbTextoNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTextoNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoNombreUsuario.setBounds(20, 100, 270, 20);
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

		ManejadorCBPaneles mCBP = new ManejadorCBPaneles();
		cbOpciones.addActionListener(mCBP);

		ManejadorCBEstudiantes mCBE = new ManejadorCBEstudiantes();
		cbEstudiantes.addActionListener(mCBE);

		ManejadorBtn mBtn = new ManejadorBtn();
		btnAceptar.addActionListener(mBtn);
		btnConfirmar.addActionListener(mBtn);
		btnContrasenyaVisible.addActionListener(mBtn);
		btnEnviar.addActionListener(mBtn);
		btnConfirmarNota.addActionListener(mBtn);
		btnRecargarPag.addActionListener(mBtn);

		setVisible(true);
	}

	// Manejador para el JComboBox de los estudiantes
	public class ManejadorCBEstudiantes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Estudiantes estudiante = (Estudiantes) cbEstudiantes.getSelectedItem();
			ids_clases_alumno.clear();
			rellenarListaClasesAlumno(estudiante);
			modeloCBIdClases.removeAllElements();
			modeloCBIdClases.addAll(ids_clases_alumno);
			cbIdClases = new JComboBox<>(modeloCBIdClases);
		}

	}

	// Manejador para el JComboBox de los paneles
	public class ManejadorCBPaneles implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String opcion = (String) cbOpciones.getSelectedItem();
			gestionPaneles(opcion);
		}

		// Metodo que gestiona el visionado de los paneles del JFrame
		private void gestionPaneles(String opcion) {
			panelVerPerfil.setVisible(false);
			panelAceptarClases.setVisible(false);
			panelModPerfil.setVisible(false);
			panelVerClases.setVisible(false);
			panelVerEvaluaciones.setVisible(false);
			panelParteAveria.setVisible(false);
			panelEvaluar.setVisible(false);

			if (opcion.equalsIgnoreCase("modificar perfil"))
				panelModPerfil.setVisible(true);

			if (opcion.equalsIgnoreCase("ver perfil"))
				panelVerPerfil.setVisible(true);

			if (opcion.equalsIgnoreCase("ver clases"))
				panelVerClases.setVisible(true);

			if (opcion.equalsIgnoreCase("aceptar clases"))
				panelAceptarClases.setVisible(true);

			if (opcion.equalsIgnoreCase("Partes de averia"))
				panelParteAveria.setVisible(true);

			if (opcion.equalsIgnoreCase("ver evaluaciones"))
				panelVerEvaluaciones.setVisible(true);

			if (opcion.equalsIgnoreCase("evaluar")) {
				panelEvaluar.setVisible(true);
			}
		}
	}

	// Manejador para los botones
	public class ManejadorBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnAceptar)) {
				quitarSolicitudes();
			} else if (e.getSource().equals(btnConfirmar)) {
				modificarPerfil();
			} else if (e.getSource().equals(btnContrasenyaVisible)) {
				verContrasenya();
			} else if (e.getSource().equals(btnEnviar)) {
				try {
					if (averiaService.getAveria(Conexion.obtener(),
							obtenerIDParte(Integer.parseInt(textoIDVehiculo.getText()))) == null) {
						averiaService.saveNewAveria(Conexion.obtener(),
								new ParteAveria(Integer.parseInt(textoIDVehiculo.getText()), textoInfoPA.getText(),
										textoDNIPA.getText()));
						JOptionPane.showMessageDialog(null, "Enviado correctamente", "Informacion",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "El vehiculo ya tiene un parte", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource().equals(btnConfirmarNota)) {
				String nota = textoNota.getText();
				if (nota.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo nota vacio", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					evaluar(Integer.parseInt((String) cbIdClases.getSelectedItem()));
					JOptionPane.showMessageDialog(null, "Se ha anyadido correctamente la nota", "Informacion",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (e.getSource().equals(btnRecargarPag)) {
				InterfazInstructor.this.dispose();
				new InterfazInstructor(nombreInstructor);
			}
		}

		private void evaluar(int id_clase) {
			PreparedStatement consulta;
			try {
				Estudiantes estudiante = (Estudiantes) cbEstudiantes.getSelectedItem();
				consulta = Conexion.obtener()
						.prepareStatement("UPDATE asistencia SET evaluacion = ? WHERE dni_alumno = ? AND id_clase = ?");
				consulta.setFloat(1, Float.parseFloat(textoNota.getText()));
				consulta.setString(2, estudiante.getDni());
				consulta.setInt(3, id_clase);
				consulta.executeUpdate();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void quitarSolicitudes() {
		List<Evaluacion> listaClases = jlClases.getSelectedValuesList();
		for (Evaluacion eva : listaClases) {
			if (eva != null) {
				try {
					PreparedStatement consulta = Conexion.obtener()
							.prepareStatement("DELETE FROM solicitud WHERE id_clase_conducir = ?");
					consulta.setInt(1, eva.getId_clase());
					consulta.executeUpdate();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}

				try {
					PreparedStatement consulta = Conexion.obtener()
							.prepareStatement("INSERT INTO asistencia (id_clase, dni_alumno) VALUES (?, ?)");
					consulta.setInt(1, eva.getId_clase());
					consulta.setString(2, eva.getDni_estudiante());
					consulta.executeUpdate();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				solicitudesClases.remove(eva);
			}
		}
		JOptionPane.showMessageDialog(null, "Las clases han sido aceptadas correctamente");
		obtenerSolicitudes();
	}

	// Este método es el que hace visible el texto del jPasswordField
	private void verContrasenya() {
		if (esVisible) {
			textoCambiarContrasenya.setEchoChar((char) 0);
			textoRepetirContrasenya.setEchoChar((char) 0);
			esVisible = false;
		} else {
			textoCambiarContrasenya.setEchoChar('*');
			textoRepetirContrasenya.setEchoChar('*');
			esVisible = true;
		}
	}

	private int dimensionTablaClases() {
		List<ClaseConducir> listaClasesProfesor = new ArrayList<>();
		try {
			for (int i = 0; i < claseConducirService.getAllClases(Conexion.obtener()).size(); i++) {
				if (claseConducirService.getAllClases(Conexion.obtener()).get(i).getDni_Instructor()
						.equals(dniInstructor)) {
					listaClasesProfesor.add(claseConducirService.getAllClases(Conexion.obtener()).get(i));
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return listaClasesProfesor.size();
	}

	private void verClases() {
		// Datos para la tabla de las clases del profesor
		try {
			dataTablaClases = new Object[claseConducirService.getAllClases(Conexion.obtener()).size()][3];
			for (int i = 0; i < claseConducirService.getAllClases(Conexion.obtener()).size(); i++) {
				if (claseConducirService.getAllClases(Conexion.obtener()).get(i).getDni_Instructor()
						.equals(dniInstructor)) {
					for (int j = 0; j < 3; j++) {
						if (j == 0) {
							dataTablaClases[i][j] = claseConducirService.getAllClases(Conexion.obtener()).get(i)
									.getId_Clase();
						} else if (j == 1) {
							dataTablaClases[i][j] = claseConducirService.getAllClases(Conexion.obtener()).get(i)
									.getFecha();
						} else if (j == 2) {
							dataTablaClases[i][j] = claseConducirService.getAllClases(Conexion.obtener()).get(i)
									.getHora();
						}
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// Este metodo rellena el panel cambiar perfil con los datos actuales del perfil
	private void rellenarCambiarPerfil() {
		try {
			textoCambiarDireccion
					.setText(instructorService.getInstructor(Conexion.obtener(), id_instructor).getDireccion());
			textoCambiarDNI.setText(instructorService.getInstructor(Conexion.obtener(), id_instructor).getDni());
			textoCambiarNombre.setText(instructorService.getInstructor(Conexion.obtener(), id_instructor).getNombre());
			textoCambiarNombreUsuario.setText(usuarioService.getUsuario(Conexion.obtener(), id_instructor).getNombre());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// Este metodo rellena el perfil con los datos del profesor conectado
	private void rellenarPerfil() {
		try {
			textoNombrePerfil.setText(instructorService.getInstructor(Conexion.obtener(), id_instructor).getNombre());
			textoNombreUsuarioPerfil.setText(usuarioService.getUsuario(Conexion.obtener(), id_instructor).getNombre());
			textoDNIPerfil.setText(instructorService.getInstructor(Conexion.obtener(), id_instructor).getDni());
			textoDireccion.setText(instructorService.getInstructor(Conexion.obtener(), id_instructor).getDireccion());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// Este metodo se encarga de habilitar la edicion del perfil
	private void modificarPerfil() {
		if (textoCambiarContrasenya.getPassword().length != 0) {
			if (String.valueOf(textoCambiarContrasenya.getPassword())
					.equals(String.valueOf(textoCambiarContrasenya.getPassword()))) {
				try {
					usuarioService.save(Conexion.obtener(),
							new Usuario(id_instructor, textoCambiarNombreUsuario.getText(),
									String.valueOf(textoCambiarContrasenya.getPassword()), "INSTRUCTOR"));
					instructorService.saveUpdate(Conexion.obtener(), new Instructor(textoCambiarDNI.getText(),
							textoCambiarNombre.getText(), textoCambiarDireccion.getText()));
					rellenarPerfil();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe rellenar el campo contraseña", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private int obtenerIDParte(int idVehiculo) {
		PreparedStatement consulta;
		try {
			consulta = Conexion.obtener()
					.prepareStatement("SELECT id_parte FROM parteaveria WHERE id_vehiculo_averiado = ?");
			consulta.setInt(1, idVehiculo);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				idVehiculo = resultado.getInt("id_parte");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return idVehiculo;
	}

	private int obtenerIDInstructor(String nombre) {
		try {
			PreparedStatement consulta = Conexion.obtener().prepareStatement(
					"SELECT i.id_Instructor FROM instructor i, usuario u WHERE u.nombre = ? AND i.id_instructor = u.idUsuario");
			consulta.setString(1, nombre);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				id_instructor = resultado.getInt("i.id_Instructor");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return id_instructor;
	}

	private String obtenerDNIInstructor(String nombre) {
		try {
			PreparedStatement consulta = Conexion.obtener().prepareStatement(
					"SELECT i.dni FROM instructor i, usuario u WHERE u.nombre = ? AND i.id_instructor = u.idUsuario");
			consulta.setString(1, nombre);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				dniInstructor = resultado.getString("i.dni");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return dniInstructor;
	}

	private List<String> obtenerDNIEstudianteSolicitud(String nombre) {
		try {
			PreparedStatement consulta = Conexion.obtener().prepareStatement(
					"SELECT s.dni_estudiante FROM solicitud s, instructor i WHERE i.dni = s.dni_instructor_clase AND i.nombre = ?");
			consulta.setString(1, nombre);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				dnisEstudiantes.add(resultado.getString("s.dni_estudiante"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return dnisEstudiantes;
	}

	private List<String> obtenerIdClase(String dniEstudiante) {
		PreparedStatement consulta;
		try {
			consulta = Conexion.obtener().prepareStatement("SELECT id_clase FROM asistencia WHERE dni_alumno = ?");
			consulta.setString(1, dniEstudiante);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				ids_clases.add(resultado.getString("id_clase"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return ids_clases;
	}

	private void rellenarListaClasesAlumno(Estudiantes estudiante) {
		try {
			PreparedStatement consulta = Conexion.obtener().prepareStatement(
					"SELECT a.id_clase FROM asistencia a, claseConducir c WHERE a.id_clase = c.id AND dni_alumno = ? AND c.dni_instructor = ?");
			consulta.setString(1, estudiante.getDni());
			consulta.setString(2, dniInstructor);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				ids_clases_alumno.add(String.valueOf(resultado.getInt("a.id_clase")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void obtenerSolicitudes() {
		PreparedStatement consulta;
		try {
			consulta = Conexion.obtener().prepareStatement(
					"SELECT id_clase_conducir, dni_estudiante FROM solicitud WHERE dni_instructor_clase = ?");
			consulta.setString(1, dniInstructor);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				solicitudesClases.add(
						new Evaluacion(resultado.getInt("id_clase_conducir"), resultado.getString("dni_estudiante")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*private void obtenerEvaluaciones(String id_clase) {
		PreparedStatement consulta;
		try {
			consulta = Conexion.obtener().prepareStatement("SELECT dni_alumno, evaluacion FROM asistencia WHERE id_clase = ?");
			consulta.setInt(1, obtenerIdClase(id_clase));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}*/

}