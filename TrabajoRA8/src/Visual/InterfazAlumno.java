package Visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class InterfazAlumno extends JFrame {
	// Principal
	private JPanel principal;
	private JLabel imgPerfil, lblBienvenida;
	private JComboBox opciones;
	private static String[] listaOpciones = { "Ver perfil", "Modificar perfil", "Solicitar clase",
			"Ver clases solicitadas", "Evaluaciones" };
	private JButton btnVolver;
	// Paneles
	private static JPanel verClases, evaluaciones, modificarPerfil, solicitarClase, verPerfil;
	// ModificarPerfil
	private JTextField fieldNombreUsuario, fieldNombre, fieldDNI, fieldDireccion;
	private JPasswordField fieldNPassword, fieldRPassword;
	private JLabel lblTituloModificarPerfil, lblnomUsuario, lblNombre, lblDNI, lblDireccion, lblNPassword, lblRPassword;
	private JButton btnConfirmarModPer;
	// SolicitarClase
	private JLabel lblTituloSolicitarClase;
	private JList listaClases;
	private JButton btnSolicitarClase;
	private JScrollPane scrollListaClases;
	// VerClases
	private JLabel lblTituloVerClases;
	private DefaultTableModel modeloTabla;
	private Object[][] data = { { null, null } };
	private String[] cabeceraTabla = { "Clase", "Estado" };
	private JScrollPane scrollTablaClases;
	private JTable tableClasesSolicitadas;
	// Evaluacion
	private JLabel lblTituloEvaluaciones;
	private DefaultTableModel modeloTablaEval;
	private Object[][] dataE = { { null } };
	private String[] cabeceraTablaE = { "Evaluacion" };
	private JScrollPane scrollTablaEvaluaciones;
	private JTable tableEvaluaciones;
	// Ver Perfil
	private JLabel lblTituloVerPerfil;
	private JLabel lblUsuarioP, lblNombreP, lblDniP, lblDireccionP;
	private JTextField textUsuarioP, textNombreP, textDniP, textDireccionP;

	public InterfazAlumno() {
		super("Interfaz Alumno");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 500);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		// Panel Principal

		principal = new JPanel();
		principal.setBounds(0, 0, 260, 463);
		getContentPane().add(principal);
		principal.setLayout(null);

		imgPerfil = new JLabel("New label");
		imgPerfil.setIcon(new ImageIcon(InterfazAlumno.class.getResource("/Visual/imagenes/estudiante.jpg")));
		imgPerfil.setBounds(0, 10, 257, 166);
		principal.add(imgPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolver.setBounds(10, 421, 124, 32);
		principal.add(btnVolver);

		ManejadorComboBox mcb = new ManejadorComboBox();

		opciones = new JComboBox(listaOpciones);
		opciones.setBounds(10, 224, 123, 30);
		opciones.addActionListener(mcb);
		principal.add(opciones);

		lblBienvenida = new JLabel("Bienvenido, Usuario");
		lblBienvenida.setBounds(10, 186, 235, 28);
		principal.add(lblBienvenida);
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 15));

		// Panel Ver perfil

		verPerfil = new JPanel();
		verPerfil.setBounds(260, 0, 326, 463);
		getContentPane().add(verPerfil);
		verPerfil.setLayout(null);

		lblTituloVerPerfil = new JLabel("Perfil");
		lblTituloVerPerfil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTituloVerPerfil.setBounds(140, 10, 43, 46);
		verPerfil.add(lblTituloVerPerfil);

		lblUsuarioP = new JLabel("Usuario");
		lblUsuarioP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsuarioP.setBounds(10, 86, 90, 29);
		verPerfil.add(lblUsuarioP);

		lblNombreP = new JLabel("Nombre");
		lblNombreP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreP.setBounds(10, 125, 90, 29);
		verPerfil.add(lblNombreP);

		lblDniP = new JLabel("DNI");
		lblDniP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDniP.setBounds(10, 164, 90, 29);
		verPerfil.add(lblDniP);

		lblDireccionP = new JLabel("Direcci\u00F3n");
		lblDireccionP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccionP.setBounds(10, 203, 90, 29);
		verPerfil.add(lblDireccionP);

		textUsuarioP = new JTextField();
		textUsuarioP.setEditable(false);
		textUsuarioP.setBounds(110, 92, 146, 19);
		verPerfil.add(textUsuarioP);
		textUsuarioP.setColumns(10);

		textNombreP = new JTextField();
		textNombreP.setEditable(false);
		textNombreP.setColumns(10);
		textNombreP.setBounds(110, 131, 146, 19);
		verPerfil.add(textNombreP);

		textDniP = new JTextField();
		textDniP.setEditable(false);
		textDniP.setColumns(10);
		textDniP.setBounds(110, 170, 146, 19);
		verPerfil.add(textDniP);

		textDireccionP = new JTextField();
		textDireccionP.setEditable(false);
		textDireccionP.setColumns(10);
		textDireccionP.setBounds(110, 209, 146, 19);
		verPerfil.add(textDireccionP);

		verPerfil.setVisible(true);

		// Panel Evaluaciones

		evaluaciones = new JPanel();
		evaluaciones.setBounds(260, 0, 326, 463);
		getContentPane().add(evaluaciones);
		evaluaciones.setLayout(null);

		lblTituloEvaluaciones = new JLabel("Evaluaciones");
		lblTituloEvaluaciones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTituloEvaluaciones.setBounds(128, 10, 98, 46);
		evaluaciones.add(lblTituloEvaluaciones);

		modeloTablaEval = new DefaultTableModel(dataE, cabeceraTablaE) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableClasesSolicitadas = new JTable(modeloTablaEval);

		scrollTablaEvaluaciones = new JScrollPane(tableEvaluaciones);
		scrollTablaEvaluaciones.setBounds(10, 66, 306, 384);
		evaluaciones.add(scrollTablaEvaluaciones);

		evaluaciones.setVisible(false);

		// Panel Ver clases

		verClases = new JPanel();
		verClases.setBounds(260, 0, 326, 463);
		getContentPane().add(verClases);
		verClases.setLayout(null);

		lblTituloVerClases = new JLabel("Ver clases");
		lblTituloVerClases.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTituloVerClases.setBounds(128, 10, 98, 46);
		verClases.add(lblTituloVerClases);

		modeloTabla = new DefaultTableModel(data, cabeceraTabla) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableClasesSolicitadas = new JTable(modeloTabla);

		scrollTablaClases = new JScrollPane(tableClasesSolicitadas);
		scrollTablaClases.setBounds(10, 66, 306, 384);
		verClases.add(scrollTablaClases);

		verClases.setVisible(false);

		// Panel Solicitar clase

		solicitarClase = new JPanel();
		solicitarClase.setBounds(260, 0, 326, 463);
		getContentPane().add(solicitarClase);
		solicitarClase.setLayout(null);

		lblTituloSolicitarClase = new JLabel("Solicitar clase");
		lblTituloSolicitarClase.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTituloSolicitarClase.setBounds(118, 10, 98, 46);
		solicitarClase.add(lblTituloSolicitarClase);

		scrollListaClases = new JScrollPane();
		scrollListaClases.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollListaClases.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollListaClases.setBounds(46, 65, 233, 307);
		solicitarClase.add(scrollListaClases);

		listaClases = new JList();
		scrollListaClases.setViewportView(listaClases);

		btnSolicitarClase = new JButton("Solicitar");
		btnSolicitarClase.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSolicitarClase.setBounds(107, 395, 109, 46);
		solicitarClase.add(btnSolicitarClase);

		solicitarClase.setVisible(false);

		// Panel Modificar perfil

		modificarPerfil = new JPanel();
		modificarPerfil.setBounds(260, 0, 326, 463);
		getContentPane().add(modificarPerfil);
		modificarPerfil.setLayout(null);

		lblTituloModificarPerfil = new JLabel("Modificar Perfil");
		lblTituloModificarPerfil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTituloModificarPerfil.setBounds(108, 10, 98, 46);
		modificarPerfil.add(lblTituloModificarPerfil);

		lblnomUsuario = new JLabel("Nombre usuario");
		lblnomUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblnomUsuario.setBounds(10, 66, 98, 27);
		modificarPerfil.add(lblnomUsuario);

		fieldNombreUsuario = new JTextField();
		fieldNombreUsuario.setBounds(108, 71, 163, 19);
		modificarPerfil.add(fieldNombreUsuario);
		fieldNombreUsuario.setColumns(10);

		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(10, 103, 98, 27);
		modificarPerfil.add(lblNombre);

		fieldNombre = new JTextField();
		fieldNombre.setBounds(108, 111, 163, 19);
		modificarPerfil.add(fieldNombre);
		fieldNombre.setColumns(10);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDNI.setBounds(10, 140, 98, 27);
		modificarPerfil.add(lblDNI);

		fieldDNI = new JTextField();
		fieldDNI.setBounds(108, 145, 163, 19);
		modificarPerfil.add(fieldDNI);
		fieldDNI.setColumns(10);

		fieldDireccion = new JTextField();
		fieldDireccion.setBounds(108, 182, 163, 19);
		modificarPerfil.add(fieldDireccion);
		fieldDireccion.setColumns(10);

		lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccion.setBounds(10, 177, 98, 27);
		modificarPerfil.add(lblDireccion);

		lblNPassword = new JLabel("Nueva contrase\u00F1a");
		lblNPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNPassword.setBounds(10, 251, 112, 27);
		modificarPerfil.add(lblNPassword);

		fieldNPassword = new JPasswordField();
		fieldNPassword.setBounds(10, 288, 196, 19);
		modificarPerfil.add(fieldNPassword);

		lblRPassword = new JLabel("Repetir contrase\u00F1a");
		lblRPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRPassword.setBounds(10, 317, 112, 27);
		modificarPerfil.add(lblRPassword);

		fieldRPassword = new JPasswordField();
		fieldRPassword.setBounds(10, 354, 196, 19);
		modificarPerfil.add(fieldRPassword);

		btnConfirmarModPer = new JButton("Confirmar");
		btnConfirmarModPer.setBounds(108, 416, 112, 37);
		modificarPerfil.add(btnConfirmarModPer);

		modificarPerfil.setVisible(false);

	}

	private class ManejadorComboBox implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String obj = (String) opciones.getSelectedItem();
			controlarVentanas(obj);

		}

		private static void controlarVentanas(String s) {
			verPerfil.setVisible(false);
			modificarPerfil.setVisible(false);
			solicitarClase.setVisible(false);
			verClases.setVisible(false);
			evaluaciones.setVisible(false);

			if (s.equals("Modificar perfil")) {
				modificarPerfil.setVisible(true);
			}
			if (s.equals("Solicitar clase")) {
				solicitarClase.setVisible(true);
			}
			if (s.equals("Ver clases solicitadas")) {
				verClases.setVisible(true);
			}
			if (s.equals("Evaluaciones")) {
				evaluaciones.setVisible(true);
			}
			if (s.equals("Ver perfil")) {
				verPerfil.setVisible(true);
			}
		}

	}

	public static void main(String[] args) {
		InterfazAlumno ia = new InterfazAlumno();
		ia.setVisible(true);
	}
}
