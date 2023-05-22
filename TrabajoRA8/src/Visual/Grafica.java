package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Modelos.Evaluacion;
import Servicios.Conexion;

public class Grafica extends JFrame {
	private JPanel panelP;
	private JButton btnMostrarGrafica, btnAtras;
	private List<Evaluacion> listaEvaluaciones = new ArrayList<>();

	public Grafica() {
		super("Grafica evaluaciones");
		setBounds(100, 100, 588, 429);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		ManejadorBotones mb = new ManejadorBotones();

		panelP = new JPanel();
		panelP.setBounds(10, 10, 553, 323);
		getContentPane().add(panelP);
		panelP.setLayout(null);

		btnMostrarGrafica = new JButton("Mostrar Grafica");
		btnMostrarGrafica.setBounds(108, 354, 122, 40);
		btnMostrarGrafica.addActionListener(mb);
		getContentPane().add(btnMostrarGrafica);

		btnAtras = new JButton("Atras");
		btnAtras.setBounds(336, 354, 122, 40);
		btnAtras.addActionListener(mb);
		getContentPane().add(btnAtras);

		setVisible(true);

	}

	private class ManejadorBotones implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o.equals(btnMostrarGrafica)) {

				DefaultCategoryDataset datos = new DefaultCategoryDataset();
				obtenerEvaluaciones();
				for (Evaluacion ev : listaEvaluaciones) {
					datos.setValue(ev.getId_clase(), ev.getDni_estudiante(), String.valueOf(ev.getEvaluacion()));
				}

				JFreeChart grafico_barras = ChartFactory.createBarChart3D("Evaluaciones", "Evaluaciones por clase",
						"Calificacion", datos, PlotOrientation.VERTICAL, true, true, false);

				ChartPanel panel = new ChartPanel(grafico_barras);
				panel.setMouseWheelEnabled(true);
				panel.setPreferredSize(new Dimension(553, 323));

				panelP.setLayout(new BorderLayout());
				panelP.add(panel, BorderLayout.NORTH);

				pack();
				repaint();

			} else if (o.equals(btnAtras)) {
				new MenuAdmin();
				dispose();
			}

		}

		private void obtenerEvaluaciones() {
			try {
				PreparedStatement consulta = Conexion.obtener().prepareStatement("SELECT * FROM asistencia");
				ResultSet resultado = consulta.executeQuery();
				while (resultado.next()) {
					listaEvaluaciones.add(new Evaluacion(resultado.getString("dni_alumno"),
							resultado.getInt("id_clase"), resultado.getFloat("evaluacion")));
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}