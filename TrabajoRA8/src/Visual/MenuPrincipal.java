package Visual;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	private JTextField textoNombreUsuarioIS, textoContrasenyaIS, textoNombreUsuarioRU, textoContrasenyaRU,
			textoRepetirCRU, textoNombreRU, textoDNIRU, textoDireccionRU;
	private JLabel lbTextoNombreUsuarioIS, lbTextoContrasenyaIS, lbTextoNombreUsuarioRU, lbTextoContrasenyaRU,
			lbTextoRepetirCRU, lbTextoNombreRU, lbTextoDNIRU, lbTextoDireccionRU;
	private JPanel panelOpcion, panelIS, panelRU;
	private JRadioButton rbRegistrar, rbIniciarS;
	private JButton btnRegistrar, btnIS;
	private UsuarioService usuarioService = new UsuarioService();
	private EstudianteService estudianteService;
	private Usuario u;
	private Estudiantes estudiante;

	public MenuPrincipal() {
		super("Iniciar sesion");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(450, 250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// JPasword
		textoContrasenyaIS = new JPasswordField();
		textoContrasenyaIS.setBounds(150, 100, 100, 20);

		// JTextField
		textoNombreUsuarioIS = new JTextField();
		textoNombreUsuarioIS.setBounds(150, 75, 100, 20);
		textoRepetirCRU = new JTextField();
		textoRepetirCRU.setBounds(200, 75, 100, 20);
		textoNombreUsuarioRU = new JTextField();
		textoNombreUsuarioRU.setBounds(200, 25, 100, 20);
		textoDireccionRU = new JTextField();
		textoDireccionRU.setBounds(200, 100, 100, 20);
		textoDNIRU = new JTextField();
		textoDNIRU.setBounds(200, 125, 100, 20);
		textoNombreRU = new JTextField();
		textoNombreRU.setBounds(200, 0, 100, 20);
		textoContrasenyaRU = new JTextField();
		textoContrasenyaRU.setBounds(200, 50, 100, 20);

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

		setVisible(true);
	}

	public class ManejadorBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnRegistrar)) {
				u = new Usuario(textoNombreUsuarioRU.getText(), textoContrasenyaRU.getText());
				try {
					if (usuarioService.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioRU.getText()) == null) {
						try {
							estudiante = new Estudiantes(textoDNIRU.getText(), textoNombreRU.getText(),
									textoDireccionRU.getText(), usuarioService
											.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioRU.getText()).getId());
						} catch (ClassNotFoundException | SQLException e2) {
							e2.printStackTrace();
						}
						try {
							usuarioService.save(Conexion.obtener(), u);
							estudianteService.saveNewAlumno(Conexion.obtener(), estudiante);
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
						new InterfazAlumno();
						MenuPrincipal.this.dispose();
					}else 
						JOptionPane.showMessageDialog(null, "Ya existe ese nombre de usuario", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource().equals(btnIS)) {

				try {
					if (usuarioService.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioIS.getText()) != null) {
						if (usuarioService.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioIS.getText())
								.getPassword() == textoContrasenyaIS.getText()) {
							if (usuarioService.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioIS.getText())
									.getRol().equalsIgnoreCase("admin")) {
								new InterfazAdmin();
								MenuPrincipal.this.dispose();
							} else if (usuarioService
									.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioIS.getText()).getRol()
									.equalsIgnoreCase("instructor")) {
								new InterfazInstructor();
								MenuPrincipal.this.dispose();
							} else if (usuarioService
									.getUsuarioNombre(Conexion.obtener(), textoNombreUsuarioIS.getText()).getRol()
									.equalsIgnoreCase("estudiante")) {
								/*
								 * if() { new InterfazAlumno(); MenuPrincipal.this.dispose(); } else {
								 * JOptionPane.showMessageDialog(null,
								 * "Su cuenta no se encuentra activada en estos momentos", "Informacion",
								 * JOptionPane.INFORMATION_MESSAGE); }
								 */
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
				MenuPrincipal.this.dispose();
			}
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