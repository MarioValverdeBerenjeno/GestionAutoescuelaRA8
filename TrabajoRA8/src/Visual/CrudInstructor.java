package Visual;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Modelos.Instructor;
import Modelos.Vehiculo;
import Servicios.Conexion;
import Servicios.InstructorService;

public class CrudInstructor extends JFrame {
	private JTable tableInstructor;
	private JButton btnBorrar, btnModificar, btnInsertar, btnAtras;
	private String[] columnas;
	private JScrollPane scrollInstructor;
	DefaultTableModel modelInstructor;
	private List<Instructor> ListaInstructores;
	InstructorService is = new InstructorService();
	ManejadorBotones mb = new ManejadorBotones();

	public CrudInstructor() {
		super("Administrar instructores");
		setBounds(100, 100, 600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		getContentPane().add(panel);
		panel.setLayout(null);

		// Borrar
		btnBorrar = new JButton("BORRAR");
		btnBorrar.setBounds(458, 328, 110, 47);
		btnBorrar.addActionListener(mb);
		getContentPane().add(btnBorrar);
		// Modificar
		btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(458, 271, 110, 47);
		btnModificar.addActionListener(mb);
		getContentPane().add(btnModificar);
		// Insertar
		btnInsertar = new JButton("INSERTAR");
		btnInsertar.setBounds(458, 214, 110, 47);
		btnInsertar.addActionListener(mb);
		getContentPane().add(btnInsertar);
		// Atras
		btnAtras = new JButton("ATRAS");
		btnAtras.setBounds(458, 385, 110, 47);
		btnAtras.addActionListener(mb);
		getContentPane().add(btnAtras);
		// JTable
		columnas = new String[] { "DNI", "NOMBRE", "DIRECCION", "ID_INSTRUCTOR" };
		modelInstructor = new DefaultTableModel(columnas, 0);
		setTableInstructor(new JTable(modelInstructor));
		getTableInstructor().setPreferredScrollableViewportSize(new Dimension(250, 100));
		getTableInstructor().getTableHeader().setReorderingAllowed(false);
		getTableInstructor().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Crear el ordenador de filas
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelInstructor);
		for (int i = 0; i < modelInstructor.getColumnCount(); i++) {
			sorter.setSortable(i, false); // Deshabilitar la ordenacion de columnas
		}
		getTableInstructor().setRowSorter(sorter);
		// scrollpanel
		scrollInstructor = new JScrollPane(getTableInstructor());
		scrollInstructor.setBounds(10, 10, 413, 401);
		add(scrollInstructor);

		try {
			ListaInstructores = is.getAllInstructores(Conexion.obtener());
			for (Instructor a : ListaInstructores) {
				String[] data = { a.getDni(), a.getNombre(), a.getDireccion(),
						String.valueOf(a.getId_Instructor()) };
				modelInstructor.addRow(data);
			}
		} catch (java.lang.NullPointerException e) {

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}

	private class ManejadorBotones implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			if (o.equals(btnInsertar)) {

			} else if (o.equals(btnModificar)) {

			} else if (o.equals(btnBorrar)) {
				int id_instructor = Integer.parseInt(
						(getTableInstructor().getValueAt(getTableInstructor().getSelectedRow(), 3)).toString());
				try {
					if (JOptionPane.showConfirmDialog(null, "¿Seguro que quieres BORRAR al instructor?", "WARNING",
							JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						// si option
						is.removeId(Conexion.obtener(), id_instructor);
						// refrescar
						JOptionPane.showMessageDialog(null, "Instructor/es borrado/s correctamente ", "Borrados",
								JOptionPane.INFORMATION_MESSAGE);
						refrescar();
					}
				} catch (Exception z) {
					JOptionPane.showMessageDialog(null, "No es posible realizar esa accion", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else if (o.equals(btnAtras)) {
				new MenuAdmin();
				dispose();
			}

		}

	}

	public boolean obtenerFilas() {
		if (getTableInstructor().getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	public JTable getTableInstructor() {
		return tableInstructor;
	}

	public void setTableInstructor(JTable tableInstructor) {
		this.tableInstructor = tableInstructor;
	}

	public void refrescar() {
		new CrudInstructor();
		dispose();
	}
}
