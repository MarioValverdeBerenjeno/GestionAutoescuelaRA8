package Visual;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Modelos.Estudiantes;
import Modelos.ParteAveria;
import Modelos.Usuario;
import Servicios.AveriaService;
import Servicios.Conexion;
import Servicios.EstudianteService;
import Servicios.InstructorService;
import Servicios.UsuarioService;

@SuppressWarnings("serial")
public class InterfazAdmin extends JFrame {
	private JLabel labelFoto, labelSaludo, labelHacer, labelClave, labelNuevaClave, lblEligeAlEstudiante;
	@SuppressWarnings("rawtypes")
	private JComboBox comboOpciones;
	private JComboBox<Usuario> comboClaveEstudiantes;
	private JPanel panelOpciones, panelEstudiantes, panelClave, panelBaja, panelAverias;
	private JButton activarEstudiante, desactivarEstudiante, volver, darbaja, confirmar, reestablecer;
	private JPasswordField reestablecerclave, reestablecernueva;
	// tabla
	DefaultTableModel comboAveria, comboBajaEstudiante, comboADEstudiante;
	private static JTable tablaAveria, tablaBaja, tablaAD;
	private JScrollPane scrollAverias, scrollBaja, scrollActDes;
	// arrays list
	private String[] opcionesAdministrador = { "Selecciona...", "Averias", "Gestionar estudiantes",
			"Reestablecer clave", "Dar de baja" }, columnas;
	private List<Estudiantes> ListaAlumnos;
	private List<Usuario> ListUsuario, ListaUsuarios, ListUsu;
	private List<ParteAveria> ListaAverias;
//	private Collection<Usuario> ListaUsuarios,ListUsu;
	DefaultComboBoxModel<Usuario> modeloClave = new DefaultComboBoxModel<>();
	// manejadores
	ManejadorEventos me = new ManejadorEventos();
	ManejadorAction ma = new ManejadorAction();
	// Instancias
	EstudianteService es = new EstudianteService();
	UsuarioService us = new UsuarioService();
	InstructorService is = new InstructorService();
	AveriaService avs = new AveriaService();

	// Comparator

	public InterfazAdmin() {
		super("Interfaz administrador");
		setBounds(100, 100, 600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		// icono
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(InterfazAdmin.class.getResource("/Visual/imagenes/admin.jpg")));
		// panel opciones
		creaOpciones();
		// panel activar/desactivar estudiantes
		creaActivarDesactivar();
		// panel reestablecer clave
		creaClave();
		// panel dar de baja
		creaDarBaja();
		// panel averías (ver averías y confirmar arreglos)
		creaAverias();

		setVisible(true);
	}

	private void creaAverias() {
		panelAverias = new JPanel();
		panelAverias.setLayout(null);
		panelAverias.setBounds(153, 0, 433, 463);
		// JTABLE
		// Crear el JTable
		columnas = new String[] { "id_Parte", "id_Vehiculo_Averiado", "datos_Averia", "dni_Instructor_Informante" };
		comboAveria = new DefaultTableModel(columnas, 0);
		setTablaAveria(new JTable(comboAveria));
		getTablaAveria().setPreferredScrollableViewportSize(new Dimension(250, 100));
		getTablaAveria().getTableHeader().setReorderingAllowed(false);
		// Crear el ordenador de filas
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(comboAveria);
		tablaAveria.setRowSorter(sorter);
		// Ordenar por la columna "ID" de forma ascendente
		sorter.sort();
		// scrollpanel
		scrollAverias = new JScrollPane(getTablaAveria());
		scrollAverias.setBounds(10, 10, 413, 401);
		panelAverias.add(scrollAverias);
		try {

			ListaAverias = avs.getAllAverias(Conexion.obtener());
			for (ParteAveria a : ListaAverias) {
				String[] data = { String.valueOf(a.getIdParte()), String.valueOf(a.getIdVehiculoAveriado()),
						a.getDatosAveria(), a.getDniInstructor() };
				comboAveria.addRow(data);
			}
		} catch (java.lang.NullPointerException e) {

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// boton arreglar
		confirmar = new JButton("Confirmar arreglo");
		confirmar.setBounds(140, 421, 140, 21);
		confirmar.addActionListener(ma);
		panelAverias.add(confirmar);
		add(panelAverias);
		panelAverias.setVisible(false);
	}

	private void creaDarBaja() {
		panelBaja = new JPanel();
		panelBaja.setLayout(null);
		panelBaja.setBounds(153, 0, 433, 463);
		// JTABLE
		// Crear el JTable
		columnas = new String[] { "ID", "Nombre", "Role", };
		comboBajaEstudiante = new DefaultTableModel(columnas, 0);
		setTablaBaja(new JTable(comboBajaEstudiante));
		getTablaBaja().setPreferredScrollableViewportSize(new Dimension(250, 100));
		getTablaBaja().getTableHeader().setReorderingAllowed(false);
		// Crear el ordenador de filas
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(comboBajaEstudiante);
		tablaBaja.setRowSorter(sorter);
		// Ordenar por la columna "ID" de forma ascendente
		sorter.sort();
		// scrollpanel
		scrollBaja = new JScrollPane(getTablaBaja());
		scrollBaja.setBounds(10, 10, 413, 401);
		panelBaja.add(scrollBaja);
		// boton confirmar
		darbaja = new JButton("Dar de baja");
		darbaja.setBounds(140, 421, 140, 21);
		darbaja.addActionListener(ma);
		panelBaja.add(darbaja);
		// Rellenar tabla alumno

		try {

			ListUsuario = us.getAllUsuarios(Conexion.obtener());
			for (Usuario a : ListUsuario) {
				if (!a.getRol().equalsIgnoreCase("ADMIN")) {
					String[] data = { String.valueOf(a.getId()), a.getNombre(), a.getRol() };
					comboBajaEstudiante.addRow(data);
				}
			}
		} catch (java.lang.NullPointerException e) {

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		add(panelBaja);
		panelBaja.setVisible(false);
	}

	private void creaActivarDesactivar() {
		panelEstudiantes = new JPanel();
		panelEstudiantes.setLayout(null);
		panelEstudiantes.setBounds(153, 0, 433, 463);
		// JTABLE
		// Crear el JTable
		columnas = new String[] { "ID", "DNI", "Nombre", "Direccion", "Activado" };
		comboADEstudiante = new DefaultTableModel(columnas, 0);
		setTablaAD(new JTable(comboADEstudiante));
		getTablaAD().setPreferredScrollableViewportSize(new Dimension(250, 100));
		getTablaAD().getTableHeader().setReorderingAllowed(false);
		// Crear el ordenador de filas
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(comboADEstudiante);
		tablaAD.setRowSorter(sorter);
		// ordenar
		sorter.sort();
		// scrollpanel
		scrollActDes = new JScrollPane(getTablaAD());
		scrollActDes.setBounds(10, 10, 413, 401);
		panelEstudiantes.add(scrollActDes);
		// Rellenar tabla alumno
		try {
			ListaAlumnos = es.getAllAlumnos(Conexion.obtener());
			for (Estudiantes a : ListaAlumnos) {
				String Activado;
				if (a.isActivado())
					Activado = "SI";
				else
					Activado = "NO";
				String[] data = { String.valueOf(a.getId_Alumno()), a.getDni(), a.getNombre(), a.getDireccion(),
						Activado };
				comboADEstudiante.addRow(data);
			}
		} catch (java.lang.NullPointerException e) {

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// boton activar
		activarEstudiante = new JButton("Activar");
		activarEstudiante.setBounds(41, 421, 102, 21);
		activarEstudiante.addActionListener(ma);
		panelEstudiantes.add(activarEstudiante);
		// boton desactivar
		desactivarEstudiante = new JButton("Desactivar");
		desactivarEstudiante.setBounds(280, 421, 102, 21);
		desactivarEstudiante.addActionListener(ma);
		panelEstudiantes.add(desactivarEstudiante);
		add(panelEstudiantes);
		panelEstudiantes.setVisible(false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void creaOpciones() {
		// panel
		panelOpciones = new JPanel();
		panelOpciones.setLayout(null);
		panelOpciones.setBounds(0, 0, 155, 463);
		// foto
		labelFoto = new JLabel();
		labelFoto.setBounds(-60, -23, 209, 142);
		panelOpciones.add(labelFoto);
		labelFoto.setIcon(new ImageIcon(InterfazAdmin.class.getResource("/Visual/imagenes/admin.jpg")));
		// bienvenida, texto
		labelSaludo = new JLabel("Bienvenido administrador");
		labelSaludo.setBounds(5, 130, 150, 27);
		panelOpciones.add(labelSaludo);
		// texto que quieres hacer
		labelHacer = new JLabel("¿Que deseas hacer?");
		labelHacer.setBounds(15, 144, 120, 41);
		panelOpciones.add(labelHacer);
		// combo box-
		comboOpciones = new JComboBox(opcionesAdministrador);
		comboOpciones.setBounds(10, 182, 130, 21);
		comboOpciones.addItemListener(me);
		panelOpciones.add(comboOpciones);
		// volver
		volver = new JButton("Volver");
		volver.setBounds(20, 389, 113, 51);
		volver.addActionListener(ma);
		panelOpciones.add(volver);
		add(panelOpciones);
		panelOpciones.setVisible(true);
	}

	private void creaClave() {
		panelClave = new JPanel();
		panelClave.setLayout(null);
		panelClave.setBounds(153, 0, 433, 463);
		// etiqueta
		lblEligeAlEstudiante = new JLabel("Elige al estudiante:");
		lblEligeAlEstudiante.setBounds(59, 94, 110, 13);
		panelClave.add(lblEligeAlEstudiante);
		// añadir estudiantes a combo box
		UsuarioService usuServi = new UsuarioService();
		try {
			ListaUsuarios = usuServi.getAllUsuarios(Conexion.obtener());
			ListUsu = new ArrayList<>();
			for (Usuario usu : ListaUsuarios) {
				if (!usu.getRol().equalsIgnoreCase("ADMIN")) {
					ListUsu.add(usu);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// seleccionar estudiante ??
		modeloClave = new DefaultComboBoxModel<>();
//
		modeloClave.addAll(ListUsu);
		comboClaveEstudiantes = new JComboBox<Usuario>(modeloClave);
		comboClaveEstudiantes.setMaximumRowCount(10);
		comboClaveEstudiantes.setBounds(190, 90, 220, 21);
		panelClave.add(comboClaveEstudiantes);
		// etiqueta
		labelClave = new JLabel("Introduce la nueva clave:");
		labelClave.setBounds(59, 41, 145, 13);
		panelClave.add(labelClave);
		// textfield clave
		reestablecerclave = new JPasswordField(16);
		reestablecerclave.setBounds(220, 38, 153, 19);
		panelClave.add(reestablecerclave);
		// etiqueta
		labelNuevaClave = new JLabel("Introducela de nuevo:");
		labelNuevaClave.setBounds(59, 64, 150, 13);
		panelClave.add(labelNuevaClave);
		// textfield nueva clave
		reestablecernueva = new JPasswordField(16);
		reestablecernueva.setBounds(220, 61, 153, 19);
		panelClave.add(reestablecernueva);
		add(panelClave);
		// btn reestablecer
		reestablecer = new JButton("Reestablecer");
		reestablecer.setBounds(122, 405, 163, 35);
		reestablecer.addActionListener(ma);
		panelClave.add(reestablecer);
		panelClave.setVisible(false);
	}

	// "Averias", "Gestionar estudiantes", "Reestablecer clave","Dar de baja"
	// Manejador de eventos
	private class ManejadorEventos implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			String item = (String) e.getItem();
			principioManejador(item);
		}
	}

	// activarEstudiante, desactivarEstudiante, volver, darbaja,
	// confirmar,reestablecer;
	// Botones
	private class ManejadorAction implements ActionListener {
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			// botones
			if (o == volver) {
				volverOpciones();
			} else if (o == reestablecer) {
				try {
					Usuario selectedUser = (Usuario) comboClaveEstudiantes.getSelectedItem();
					int id = selectedUser.getId();
					System.out.println(id);

					// Comprobar contraseña iguales
					if (reestablecernueva.getText().equals(reestablecerclave.getText())) {
						Usuario modiUsuario = us.getUsuario(Conexion.obtener(), id);
						modiUsuario.setPassword(reestablecernueva.getText());
						us.save(Conexion.obtener(), modiUsuario);
						System.out.println("IGUALES");
					} else
						JOptionPane.showMessageDialog(null, "La contraseña no coincide", "ERROR PASSWORD",
								JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}

			// Boton dar de baja
			try {
				if (o == darbaja && obtenerFilasBaja()) {

//				int idUsuario = Integer.parseInt((getTablaBaja().getValueAt(getTablaBaja().getSelectedRow(), 0)).toString()) ;
//					String Role = (getTablaBaja().getValueAt(getTablaBaja().getSelectedRow(), 0)).toString();

					if (JOptionPane.showConfirmDialog(null, "¿Seguro que quieres dar de baja?", "WARNING",
							JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						// si option

						int[] selectedRows = getTablaBaja().getSelectedRows();

						for (int row : selectedRows) {
							// Recorre las columnas de la fila actual
							int todasId = Integer.parseInt(getTablaBaja().getValueAt(row, 0).toString());
							
							String rol = getTablaBaja().getValueAt(row, 2).toString();
//							System.out.println("Valor de la celda (" + row + ", " + 0 + "): " + todasId);
							if (rol.equalsIgnoreCase("ALUMNO")) {
								es.removeId(Conexion.obtener(), todasId);
								us.removeId(Conexion.obtener(), todasId);
								JOptionPane.showMessageDialog(null, "Usuarios dados de baja correctamente","BAJA",JOptionPane.INFORMATION_MESSAGE);
							} else if (rol.equalsIgnoreCase("INSTRUCTOR")) {
								//COmrpobar que no exista en una averia y si es asi pues no poder borrar al instructor
								if(avs.getAveriaDNI_Instructor(Conexion.obtener(), is.getInstructor(Conexion.obtener(), todasId).getDni())==null) {
									is.removeId(Conexion.obtener(),todasId);
									us.removeId(Conexion.obtener(), todasId);
									JOptionPane.showMessageDialog(null, "Usuarios dados de baja correctamente","BAJA",JOptionPane.INFORMATION_MESSAGE);
								}else
									JOptionPane.showMessageDialog(null, "No se puede dar de baja correctamente"
											+ "ya que el instructor esta relacionado con uno o varios partes"
											+ "de averia.","ERROR",JOptionPane.INFORMATION_MESSAGE);

									
							}
							
//							 getTablaBaja().getModel()).fireTableDataChanged();
						}
						refrescar();
					}
				}

				// Boton CONFIRMAR
				if (o == confirmar && obtenerFilasAveria()) {
					int idAveria = Integer
							.parseInt((getTablaAveria().getValueAt(getTablaAveria().getSelectedRow(), 0)).toString());
					obtenerFilasAD();
					if (JOptionPane.showConfirmDialog(null, "¿Seguro que quieres CONFIRMAR el arrelgo?", "WARNING",
							JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						// si option
						avs.removeId(Conexion.obtener(), idAveria);
						//refrescar
						JOptionPane.showMessageDialog(null, "Usuarios dados de baja correctamente","BAJA",JOptionPane.INFORMATION_MESSAGE);
						refrescar();
					}
				}

				// BOTONES ACTIVAR DESACTIVAR
				try {
				if (o == desactivarEstudiante && obtenerFilasAD()) {
					int idUsuario = Integer
							.parseInt((getTablaAD().getValueAt(getTablaAD().getSelectedRow(), 0)).toString());
					obtenerFilasAD();

					es.desactivarEstudiante(Conexion.obtener(), es.getAlumno(Conexion.obtener(), idUsuario));
					JOptionPane.showMessageDialog(null, "Usuarios desactivados","DESACTIVAR",JOptionPane.INFORMATION_MESSAGE);
					refrescar();
				} else if (o == activarEstudiante && obtenerFilasAD()) {
					int idUsuario = Integer
							.parseInt((getTablaAD().getValueAt(getTablaAD().getSelectedRow(), 0)).toString());

					es.activarEstudiante(Conexion.obtener(), es.getAlumno(Conexion.obtener(), idUsuario));
					JOptionPane.showMessageDialog(null, "Usuarios activados","ACTIVAR",JOptionPane.INFORMATION_MESSAGE);
					refrescar();
					
				}
				}catch(SQLException x) {
					JOptionPane.showMessageDialog(null, "No es posible realizar esa accion","Error",JOptionPane.ERROR_MESSAGE);
				}

			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	public boolean obtenerFilasBaja() {
		if (getTablaBaja().getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	public void refrescar() {
		InterfazAdmin.this.dispose();
		new InterfazAdmin();		
	}

	public boolean obtenerFilasAD() {
		if (getTablaAD().getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	public boolean obtenerFilasAveria() {
		if (getTablaAveria().getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	public void principioManejador(String item) {
		// Ocultar todos los paneles antes de mostrar el panel correspondiente
		panelEstudiantes.setVisible(false);
		panelClave.setVisible(false);
		panelBaja.setVisible(false);
		panelAverias.setVisible(false);

		if (item.equalsIgnoreCase("Gestionar estudiantes")) {
			panelEstudiantes.setVisible(true);
		} else if (item.equalsIgnoreCase("Reestablecer clave")) {
			panelClave.setVisible(true);
		} else if (item.equalsIgnoreCase("Dar de baja")) {
			panelBaja.setVisible(true);
		} else if (item.equalsIgnoreCase("Averias")) {
			panelAverias.setVisible(true);
		}
	}

	public void volverOpciones() {
		// Cerrar interfaz admin
		InterfazAdmin.this.dispose();
		// Abrir menu principal
		new MenuPrincipal();
	}
	// getter-setters JTable

	public static JTable getTablaAveria() {
		return tablaAveria;
	}

	public void setTablaAveria(JTable tablaJuegos) {
		InterfazAdmin.tablaAveria = tablaJuegos;
	}

	public static JTable getTablaBaja() {
		return tablaBaja;
	}

	public void setTablaBaja(JTable tablaBaja) {
		InterfazAdmin.tablaBaja = tablaBaja;
	}

	public static JTable getTablaAD() {
		return tablaAD;
	}

	public void setTablaAD(JTable tablaAD) {
		InterfazAdmin.tablaAD = tablaAD;
	}
}
