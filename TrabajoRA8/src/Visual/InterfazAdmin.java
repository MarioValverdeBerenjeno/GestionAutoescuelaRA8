package Visual;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class InterfazAdmin extends JFrame {
	private JLabel labelFoto, labelSaludo, labelHacer, labelClave, labelNuevaClave,lblEligeAlEstudiante;
	@SuppressWarnings("rawtypes")
	private JComboBox comboOpciones, comboClaveEstudiantes;
	private JPanel panelOpciones, panelEstudiantes, panelClave, panelBaja, panelAverias;
	private JButton activarEstudiante, desactivarEstudiante, volver, darbaja, confirmar,reestablecer;
	private JTextField reestablecerclave, reestablecernueva;
	//tabla
	DefaultTableModel comboAveria, comboBajaEstudiante, comboADEstudiante;
	private static JTable tablaAveria, tablaBaja, tablaAD;
	private JScrollPane scrollAverias, scrollBaja, scrollActDes;
	//arrays
	private String[] opcionesAdministrador = { "Selecciona...","Averias", "Gestionar estudiantes", "Reestablecer clave",
	"Dar de baja" }, columnas;
	ManejadorEventos me=new ManejadorEventos();
	ManejadorAction ma=new ManejadorAction();
	public InterfazAdmin() {
		super("Interfaz administrador");
		setBounds(100, 100, 600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		//icono
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazAdmin.class.getResource("/Visual/imagenes/admin.jpg")));
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
		columnas = new String[] { "id_Parte", "datos_Averia", "id_Vehiculo_Averiado", "dni_Instructor_Informante" };
		comboAveria = new DefaultTableModel(columnas, 0);
		setTablaAveria(new JTable(comboAveria));
		getTablaAveria().setPreferredScrollableViewportSize(new Dimension(250, 100));
		getTablaAveria().getTableHeader().setReorderingAllowed(false);

		// scrollpanel
		scrollAverias = new JScrollPane(getTablaAveria());
		scrollAverias.setBounds(10, 10, 413, 401);
		panelAverias.add(scrollAverias);

		// boton arreglar
		confirmar = new JButton("Confirmar arreglo");
		confirmar.setBounds(140, 421, 140, 21);
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
		columnas = new String[] { "DNI", "Nombre", "Direccion", "ID_Alumno" };
		comboBajaEstudiante = new DefaultTableModel(columnas, 0);
		setTablaBaja(new JTable(comboBajaEstudiante));
		getTablaBaja().setPreferredScrollableViewportSize(new Dimension(250, 100));
		getTablaBaja().getTableHeader().setReorderingAllowed(false);
		// scrollpanel
		scrollBaja = new JScrollPane(getTablaBaja());
		scrollBaja.setBounds(10, 10, 413, 401);
		panelBaja.add(scrollBaja);
		// boton confirmar
		darbaja = new JButton("Dar de baja");
		darbaja.setBounds(140, 421, 140, 21);
		panelBaja.add(darbaja);
		add(panelBaja);
		panelBaja.setVisible(false);
	}

	private void creaActivarDesactivar() {
		panelEstudiantes = new JPanel();
		panelEstudiantes.setLayout(null);
		panelEstudiantes.setBounds(153, 0, 433, 463);
		// JTABLE
		// Crear el JTable
		columnas = new String[] { "DNI", "Nombre", "Direccion", "ID_Alumno" };
		comboADEstudiante = new DefaultTableModel(columnas, 0);
		setTablaAD(new JTable(comboADEstudiante));
		getTablaAD().setPreferredScrollableViewportSize(new Dimension(250, 100));
		getTablaAD().getTableHeader().setReorderingAllowed(false);
		// scrollpanel
		scrollActDes = new JScrollPane(getTablaAD());
		scrollActDes.setBounds(10, 10, 413, 401);
		panelEstudiantes.add(scrollActDes);
		// boton activar
		activarEstudiante = new JButton("Activar");
		activarEstudiante.setBounds(41, 421, 102, 21);
		panelEstudiantes.add(activarEstudiante);
		// boton desactivar
		desactivarEstudiante = new JButton("Desactivar");
		desactivarEstudiante.setBounds(280, 421, 102, 21);
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
		labelHacer = new JLabel("¿Qué deseas hacer?");
		labelHacer.setBounds(15, 144, 120, 41);
		panelOpciones.add(labelHacer);
		// combo box-
		comboOpciones = new JComboBox(opcionesAdministrador);
		comboOpciones.setBounds(10, 182, 130, 21);
		comboOpciones.addItemListener(me);
		panelOpciones.add(comboOpciones);
		// volver
		volver = new JButton("Volver");
		volver.setEnabled(false);
		volver.setBounds(20, 389, 113, 51);
		volver.addActionListener(ma);
		panelOpciones.add(volver);
		add(panelOpciones);
		panelOpciones.setVisible(true);
	}

	@SuppressWarnings("rawtypes")
	private void creaClave() {
		panelClave = new JPanel();
		panelClave.setLayout(null);
		panelClave.setBounds(153, 0, 433, 463);
		//etiqueta
		lblEligeAlEstudiante = new JLabel("Elige al estudiante:");
		lblEligeAlEstudiante.setBounds(59, 94, 110, 13);
		panelClave.add(lblEligeAlEstudiante);
		// seleccionar estudiante ??
		comboClaveEstudiantes = new JComboBox();
		comboClaveEstudiantes.setBounds(195, 90, 168, 21);
		panelClave.add(comboClaveEstudiantes);
		// etiqueta
		labelClave = new JLabel("Introduce la nueva clave:");
		labelClave.setBounds(59, 41, 145, 13);
		panelClave.add(labelClave);
		// textfield clave
		reestablecerclave = new JTextField(16);
		reestablecerclave.setBounds(220, 38, 153, 19);
		panelClave.add(reestablecerclave);
		// etiqueta
		labelNuevaClave = new JLabel("Introducela de nuevo:");
		labelNuevaClave.setBounds(59, 64, 150, 13);
		panelClave.add(labelNuevaClave);
		// textfield nueva clave
		reestablecernueva = new JTextField(16);
		reestablecernueva.setBounds(220, 61, 153, 19);
		panelClave.add(reestablecernueva);
		add(panelClave);
		//btn reestablecer
		reestablecer=new JButton("Reestablecer");
		reestablecer.setBounds(122, 405, 163, 35);
		panelClave.add(reestablecer);
		panelClave.setVisible(false);
	}
	
	//"Averias", "Gestionar estudiantes", "Reestablecer clave","Dar de baja"
	//Manejador de eventos
	private class ManejadorEventos implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
            
            String item = (String) e.getItem();
            principioManejador();
            if(	item.equalsIgnoreCase("Selecciona...")) {
            	volverOpciones();
            }else if(item.equalsIgnoreCase("Gestionar estudiantes")) {
            	panelEstudiantes.setVisible(true);
            }else if(item.equalsIgnoreCase("Reestablecer clave")) {
            	panelClave.setVisible(true);
            }else if(item.equalsIgnoreCase("Dar de baja")) {
            	panelBaja.setVisible(true);
            }else if(item.equalsIgnoreCase("Averias")) {
            	panelAverias.setVisible(true);
            }
		}
	}
	//activarEstudiante, desactivarEstudiante, volver, darbaja, confirmar,reestablecer;
	//Botones
	private class ManejadorAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o=e.getSource();
			if(o==volver) {
				volverOpciones();
			}
		}
	}
	//getter-setters JTable

	public static JTable getTablaAveria() {
		return tablaAveria;
	}

	public void principioManejador() {
		// TODO Auto-generated method stub
		comboOpciones.setEnabled(false);
    	volver.setEnabled(true);        	
        // Ocultar todos los paneles antes de mostrar el panel correspondiente
        panelEstudiantes.setVisible(false);
        panelClave.setVisible(false);
        panelBaja.setVisible(false);
        panelAverias.setVisible(false);
	}

	public void volverOpciones() {
    	comboOpciones.setEnabled(true);
		volver.setEnabled(false);
		panelEstudiantes.setVisible(false);
		panelClave.setVisible(false);
		panelBaja.setVisible(false);
		panelAverias.setVisible(false);
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