package Visual;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Modelos.Estudiantes;
import Modelos.Usuario;
import Servicios.Conexion;
import Servicios.EstudianteService;
import Servicios.UsuarioService;

public class MenuPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	public static String nombreUsuario;
	private JTextField textoNombreUsuarioIS, textoNombreUsuarioRU, textoNombreRU, textoDNIRU, textoDireccionRU;
	private JPasswordField textoContrasenyaIS, textoContrasenyaRU, textoRepetirCRU;
	private JLabel lbTextoNombreUsuarioIS, lbTextoContrasenyaIS, lbTextoNombreUsuarioRU, lbTextoContrasenyaRU,
			lbTextoRepetirCRU, lbTextoNombreRU, lbTextoDNIRU, lbTextoDireccionRU;
	private JPanel panelOpcion, panelIS, panelRU;
	private JRadioButton rbRegistrar, rbIniciarS;
	private JButton btnRegistrar, btnIS, btnContrasenyaVisibleIS, btnContrasenyaVisibleRU;
	private UsuarioService usuarioService = new UsuarioService();
	private EstudianteService estudianteService = new EstudianteService();
	private Usuario u;
	private Estudiantes estudiante;
	private int id_estudiante;
	private boolean esVisibleIS = true, esVisibleRU = true;

	public MenuPrincipal() {
		super("Iniciar sesion");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(450, 250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// JPasword
		textoContrasenyaIS = new JPasswordField();
		textoContrasenyaIS.setBounds(150, 100, 100, 20);
		textoRepetirCRU = new JPasswordField();
		textoRepetirCRU.setBounds(200, 75, 100, 20);
		textoContrasenyaRU = new JPasswordField();
		textoContrasenyaRU.setBounds(200, 50, 100, 20);

		// JTextField
		textoNombreUsuarioIS = new JTextField();
		textoNombreUsuarioIS.setBounds(150, 75, 100, 20);
		textoNombreUsuarioRU = new JTextField();
		textoNombreUsuarioRU.setBounds(200, 25, 100, 20);
		textoDireccionRU = new JTextField();
		textoDireccionRU.setBounds(200, 100, 100, 20);
		textoDNIRU = new JTextField();
		textoDNIRU.setBounds(200, 125, 100, 20);
		textoNombreRU = new JTextField();
		textoNombreRU.setBounds(200, 0, 100, 20);

		// JLabel
		lbTextoNombreUsuarioIS = new JLabel("Nombre de usuario: ");
		lbTextoNombreUsuarioIS.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoNombreUsuarioIS.setBounds(30, 75, 120, 20);
		lbTextoContrasenyaIS = new JLabel("Contrasenya: ");
		lbTextoContrasenyaIS.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoContrasenyaIS.setBounds(50, 100, 100, 20);
		lbTextoRepetirCRU = new JLabel();
		lbTextoRepetirCRU.setText("Repite contrasenya:");
		lbTextoRepetirCRU.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoRepetirCRU.setBounds(50, 75, 130, 20);
		lbTextoContrasenyaRU = new JLabel();
		lbTextoContrasenyaRU.setText("Contrasenya:");
		lbTextoContrasenyaRU.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoContrasenyaRU.setBounds(50, 50, 130, 20);
		lbTextoDireccionRU = new JLabel();
		lbTextoDireccionRU.setText("Direccion:");
		lbTextoDireccionRU.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoDireccionRU.setBounds(50, 100, 130, 20);
		lbTextoNombreRU = new JLabel();
		lbTextoNombreRU.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTextoNombreRU.setText("Nombre completo: ");
		lbTextoNombreRU.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoNombreRU.setBounds(50, 0, 130, 20);
		lbTextoDNIRU = new JLabel();
		lbTextoDNIRU.setText("DNI:");
		lbTextoDNIRU.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoDNIRU.setBounds(50, 125, 130, 20);
		lbTextoNombreUsuarioRU = new JLabel();
		lbTextoNombreUsuarioRU.setText("Nombre de usuario:");
		lbTextoNombreUsuarioRU.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoNombreUsuarioRU.setBounds(50, 25, 130, 20);

		// JButton
		btnRegistrar = new JButton("Confirmar");
		btnRegistrar.setBounds(150, 150, 115, 30);
		btnIS = new JButton("Iniciar sesion");
		btnIS.setBounds(310, 140, 115, 30);
		btnContrasenyaVisibleIS = new JButton();
		btnContrasenyaVisibleIS.setBounds(300, 100, 25, 25);
		btnContrasenyaVisibleRU = new JButton();
		btnContrasenyaVisibleRU.setBounds(310, 50, 25, 25);

		// JRadioButton
		rbIniciarS = new JRadioButton("Iniciar sesion", true);
		rbIniciarS.setBounds(165, 5, 105, 23);
		rbRegistrar = new JRadioButton("Registrar nueva cuenta", false);
		rbRegistrar.setBounds(270, 5, 160, 23);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbIniciarS);
		bg.add(rbRegistrar);

		// JPanel
		panelIS = new JPanel();
		panelIS.setLayout(null);
		panelIS.add(textoContrasenyaIS);
		panelIS.add(textoNombreUsuarioIS);
		panelIS.add(lbTextoContrasenyaIS);
		panelIS.add(lbTextoNombreUsuarioIS);
		panelIS.add(btnIS);
		panelIS.add(btnContrasenyaVisibleIS);
		panelIS.setBounds(0, 30, 440, 190);
		panelOpcion = new JPanel();
		panelOpcion.setLayout(null);
		panelOpcion.add(rbIniciarS);
		panelOpcion.add(rbRegistrar);
		panelOpcion.setBounds(0, 0, 440, 30);
		panelRU = new JPanel();
		panelRU.setLayout(null);
		panelRU.setBounds(0, 30, 440, 190);
		panelRU.add(lbTextoRepetirCRU);
		panelRU.add(lbTextoContrasenyaRU);
		panelRU.add(lbTextoDireccionRU);
		panelRU.add(lbTextoDNIRU);
		panelRU.add(lbTextoNombreRU);
		panelRU.add(lbTextoNombreUsuarioRU);
		panelRU.add(textoContrasenyaRU);
		panelRU.add(textoDireccionRU);
		panelRU.add(textoDNIRU);
		panelRU.add(textoNombreRU);
		panelRU.add(textoNombreUsuarioRU);
		panelRU.add(textoRepetirCRU);
		panelRU.add(btnRegistrar);
		panelRU.add(btnContrasenyaVisibleRU);
		panelRU.setVisible(false);

		getContentPane().add(panelOpcion);
		getContentPane().add(panelIS);
		getContentPane().add(panelRU);

		ManejadorRB mRB = new ManejadorRB();
		rbIniciarS.addItemListener(mRB);
		rbRegistrar.addItemListener(mRB);
		ManejadorBtn mBtn = new ManejadorBtn();
		btnIS.addActionListener(mBtn);
		btnRegistrar.addActionListener(mBtn);
		btnContrasenyaVisibleIS.addActionListener(mBtn);
		btnContrasenyaVisibleRU.addActionListener(mBtn);

		setVisible(true);
	}

	public class ManejadorBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnRegistrar)) {
				u = new Usuario(textoNombreUsuarioRU.getText(), String.valueOf(textoContrasenyaRU.getPassword()));
				try {
					if (usuarioService.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioRU.getText()) == null) {
						if (textoContrasenyaRU.getPassword().equals(textoRepetirCRU.getPassword())) {
							usuarioService.save(Conexion.obtener(), u);
							estudiante = new Estudiantes(textoDNIRU.getText(), textoNombreRU.getText(),
									textoDireccionRU.getText(),
									usuarioService.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioRU.getText())
											.getId());
							estudianteService.saveNewAlumno(Conexion.obtener(), estudiante);
							InterfazAlumno ia = new InterfazAlumno(textoNombreUsuarioRU.getText());
							ia.setVisible(true);
							MenuPrincipal.this.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Las contrasenyas no coinciden", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else
						JOptionPane.showMessageDialog(null, "Ya existe ese nombre de usuario", "Error",
								JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource().equals(btnIS)) {

				try {
					if (usuarioService.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioIS.getText()) != null) {
						if (usuarioService.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioIS.getText())
								.getPassword().equals(String.valueOf(textoContrasenyaIS.getPassword()))) {
							if (usuarioService.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioIS.getText())
									.getRol().equalsIgnoreCase("admin")) {
								new MenuAdmin();
								MenuPrincipal.this.dispose();
							} else if (usuarioService
									.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioIS.getText()).getRol()
									.equalsIgnoreCase("instructor")) {
								InterfazInstructor ii = new InterfazInstructor(textoNombreUsuarioIS.getText());
								ii.setVisible(true);
								MenuPrincipal.this.dispose();
							} else if (usuarioService
									.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioIS.getText()).getRol()
									.equalsIgnoreCase("alumno")) {
								PreparedStatement consulta = Conexion.obtener()
										.prepareStatement("SELECT idUsuario FROM usuario WHERE nombre = ?");
								consulta.setString(1, textoNombreUsuarioIS.getText());
								ResultSet resultado = consulta.executeQuery();
								while (resultado.next()) {
									id_estudiante = resultado.getInt("idUsuario");
								}
								if (estudianteService.getAlumno(Conexion.obtener(), id_estudiante).isActivado()) {
									InterfazAlumno ia = new InterfazAlumno(textoNombreUsuarioIS.getText());
									ia.setVisible(true);
									MenuPrincipal.this.dispose();
								} else {
									JOptionPane.showMessageDialog(null,
											"Su cuenta no se encuentra activada en estos momentos", "Informacion",
											JOptionPane.INFORMATION_MESSAGE);
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Contrasenya incorrecta", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "No se encuentra la cuenta", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource().equals(btnContrasenyaVisibleIS)) {
				verContrasenyaIS();
			} else if (e.getSource().equals(btnContrasenyaVisibleRU)) {
				verContrasenyaRU();
			}
		}
	}

	private void verContrasenyaIS() {
		if (esVisibleIS) {
			textoContrasenyaIS.setEchoChar((char) 0);
			esVisibleIS = false;
		} else {
			textoContrasenyaIS.setEchoChar('*');
			esVisibleIS = true;
		}
	}

	private void verContrasenyaRU() {
		if (esVisibleRU) {
			textoContrasenyaRU.setEchoChar((char) 0);
			textoRepetirCRU.setEchoChar((char) 0);
			esVisibleRU = false;
		} else {
			textoContrasenyaRU.setEchoChar('*');
			textoRepetirCRU.setEchoChar('*');
			esVisibleRU = true;
		}
	}

	public class ManejadorRB implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JRadioButton rb = (JRadioButton) e.getSource();
			String opcion = rb.getText();
			gestionPaneles(opcion);
		}

		private void gestionPaneles(String opcion) {
			panelIS.setVisible(false);
			panelRU.setVisible(false);

			if (opcion.equalsIgnoreCase("iniciar sesion"))
				panelIS.setVisible(true);

			if (opcion.equalsIgnoreCase("registrar nueva cuenta"))
				panelRU.setVisible(true);
		}
	}

}