package Visual;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Modelos.Vehiculo;
import Servicios.Conexion;
import Servicios.VehiculoService;

public class CrudVehiculo extends JFrame {
	private JButton btnBorrar, btnModificar, btnInsertar, btnModImg;
	private JLabel lblImagen;
	private String[] columnas;
	private JTable tablevehiculo;
	private JScrollPane scrollvehiculo;
	DefaultTableModel modelVehiculos;
	private List<Vehiculo> ListaVehiculos;
	VehiculoService vs = new VehiculoService();
	ManejadorA ma = new ManejadorA();
	ManejadorImagen mi=new ManejadorImagen();

	public CrudVehiculo() {
		super("Administrar vehiculo");
		setBounds(100, 100, 600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//Mostrar imagen
		lblImagen = new JLabel("");
		lblImagen.setBounds(431, 1, 145, 137);
		getContentPane().add(lblImagen);
		
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
		// modificar imagen
		btnModImg = new JButton("Cambiar imagen");
		btnModImg.setBounds(446, 232, 130, 21);
		btnModImg.addActionListener(ma);
		getContentPane().add(btnModImg);
		// Jtable
		// Crear el JTable
		columnas = new String[] { "ID_VEHICULO", "MODELO", "TIPO", "IMAGEN VEHICULO" };
		modelVehiculos = new DefaultTableModel(columnas, 0);
		setTablevehiculo(new JTable(modelVehiculos));
		getTablevehiculo().setPreferredScrollableViewportSize(new Dimension(250, 100));
		getTablevehiculo().getTableHeader().setReorderingAllowed(false);
		//actualizar imagen
		getTablevehiculo().getSelectionModel().addListSelectionListener(mi);
		// Crear el ordenador de filas
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelVehiculos);
		tablevehiculo.setRowSorter(sorter);
		// Ordenar por la columna "ID" de forma ascendente
		sorter.sort();
		// scrollpanel
		scrollvehiculo = new JScrollPane(getTablevehiculo());
		scrollvehiculo.setBounds(10, 10, 413, 401);
		add(scrollvehiculo);
		try {
			ListaVehiculos = vs.getAllVehiculos(Conexion.obtener());
			for (Vehiculo a : ListaVehiculos) {
				String[] data = { String.valueOf(a.getId_Vehiculo()), a.getModelo(), a.getTipo(),
						a.getImagenVehiculo() };
				modelVehiculos.addRow(data);
			}
		} catch (java.lang.NullPointerException e) {

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}

	// btnBorrar, btnModificar, btnInsertar, btnModImg;
	private class ManejadorA implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o==btnInsertar){
				new InsModVehiculo();
			}else if (obtenerFilas()) {
				if (o == btnModificar) {
					new InsModVehiculo();
				} else if (o == btnBorrar) {

				} else if (o == btnModImg) {

				}
			}
		}

	}

	public boolean obtenerFilas() {
		if (getTablevehiculo().getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	public class ManejadorImagen implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (getTablevehiculo().getValueAt(getTablevehiculo().getSelectedRow(), 3) != null) {
				Image img = new ImageIcon(getTablevehiculo().getValueAt(getTablevehiculo().getSelectedRow(), 3).toString())
						.getImage();
				Image newimg = img.getScaledInstance(150, 180, java.awt.Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(newimg);
				lblImagen.setIcon(imageIcon);
			}

		}
	}

	public JTable getTablevehiculo() {
		return tablevehiculo;
	}

	public void setTablevehiculo(JTable tablevehiculo) {
		this.tablevehiculo = tablevehiculo;
	}
}
