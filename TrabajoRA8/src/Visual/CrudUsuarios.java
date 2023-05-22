package Visual;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Modelos.Usuario;
import Servicios.Conexion;
import Servicios.EstudianteService;
import Servicios.InstructorService;
import Servicios.UsuarioService;

@SuppressWarnings("serial")
public class CrudUsuarios extends JFrame {
	// Instancias
	ManejadorA ma = new ManejadorA();
	UsuarioService us = new UsuarioService();
	InstructorService is = new InstructorService();
	EstudianteService es = new EstudianteService();
	//
	private JButton btnBorrar, btnModificar, btnInsertar, btnVolver;
	private String[] columnas;
	private DefaultTableModel modelUsuarios;
	private JTable tableusuarios;
	private JScrollPane scrollusuario;
	private List<Usuario> ListaUsuarios;

	@SuppressWarnings("serial")
	public CrudUsuarios() {
		super("Administrar usuarios");
		setTitle("CRUD USUARIO");
		setBounds(100, 100, 600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		// icono
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(InterfazAdmin.class.getResource("/Visual/imagenes/logo.png")));

		// botones
		// borrar
		btnBorrar = new JButton("BORRAR");
		btnBorrar.setBounds(458, 385, 110, 47);
		btnBorrar.addActionListener(ma);
		getContentPane().add(btnBorrar);
		// modificar
		btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(458, 328, 110, 47);
		btnModificar.addActionListener(ma);
		getContentPane().add(btnModificar);
		// insertar
		btnInsertar = new JButton("INSERTAR");
		btnInsertar.setBounds(458, 271, 110, 47);
		btnInsertar.addActionListener(ma);
		getContentPane().add(btnInsertar);
		// volver
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(149, 432, 126, 21);
		btnVolver.addActionListener(ma);
		getContentPane().add(btnVolver);
		// JTable
		// Crear el JTable
		columnas = new String[] { "IDUSUARIO", "ROL", "NOMBRE", "CONTRASENYA" };
		modelUsuarios = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Hacer todas las celdas no editables
			}
		};
		setTableusuarios(new JTable(modelUsuarios));
		getTableusuarios().setPreferredScrollableViewportSize(new Dimension(250, 100));
		getTableusuarios().getTableHeader().setReorderingAllowed(false);
		getTableusuarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Crear el ordenador de filas
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelUsuarios);
		for (int i = 0; i < modelUsuarios.getColumnCount(); i++) {
			sorter.setSortable(i, false); // Deshabilitar la ordenación de columnas
		}
		getTableusuarios().setRowSorter(sorter);

		// scrollpanel
		scrollusuario = new JScrollPane(getTableusuarios());
		scrollusuario.setBounds(10, 10, 413, 401);
		add(scrollusuario);

		try {
			ListaUsuarios = us.getAllUsuarios(Conexion.obtener());
			for (Usuario a : ListaUsuarios) {
				String[] data = { String.valueOf(a.getId()), a.getRol(), a.getNombre(), a.getPassword() };
				modelUsuarios.addRow(data);
			}
		}catch (SQLException | ClassNotFoundException | java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Error", "ERROR", JOptionPane.ERROR_MESSAGE);
		} 
		setVisible(true);
	}

	public JTable getTableusuarios() {
		return tableusuarios;
	}

	public void setTableusuarios(JTable tableusuarios) {
		this.tableusuarios = tableusuarios;
	}

	private class ManejadorA implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o == btnInsertar) {
				new InsModUser();
				dispose();

			} else if (o == btnVolver) {
				new MenuAdmin();
				dispose();
			} else if (obtenerFilas()) {
				if (o == btnModificar) {
					InsModUser.IdUserModi = Integer.parseInt(
							(getTableusuarios().getValueAt(getTableusuarios().getSelectedRow(), 0)).toString());
					dispose();
					new InsModUser();

				} else if (o == btnBorrar) {
					int idUser = Integer.parseInt(
							(getTableusuarios().getValueAt(getTableusuarios().getSelectedRow(), 0)).toString());
					try {
						if (JOptionPane.showConfirmDialog(null, "¿Seguro que quieres BORRAR el usuario?", "WARNING",
								JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							// si option
							if (us.getUsuario(Conexion.obtener(), idUser).getRol().equalsIgnoreCase("INSTRUCTOR")) {
								is.removeId(Conexion.obtener(), idUser);
							} else if (us.getUsuario(Conexion.obtener(), idUser).getRol().equalsIgnoreCase("ALUMNO")) {
								es.removeId(Conexion.obtener(), idUser);
							}
							us.removeId(Conexion.obtener(), idUser);
							// refrescar
							JOptionPane.showMessageDialog(null, "Usuario borrado correctamente ", "Borrado",
									JOptionPane.INFORMATION_MESSAGE);
							refrescar();
						}
					} catch (Exception z) {
						JOptionPane.showMessageDialog(null, "No es posible realizar esa accion", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

	}

	public boolean obtenerFilas() {
		if (getTableusuarios().getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	public void refrescar() {
		dispose();
		new CrudUsuarios();
	}
}
