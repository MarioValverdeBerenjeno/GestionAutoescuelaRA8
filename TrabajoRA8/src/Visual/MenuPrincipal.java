package Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	public static String nombreUsuario;
	private JTextField textoNombreUsuario, textoContrasenya, textoRepetirC;
	private JLabel lbTextoNombreUsuario, lbTextoContrasenya, lbTextoRepetirC;
	private JPanel panelIS;
	private JRadioButton rbRegistrar, rbIniciarS;

	public MenuPrincipal() {
		super("Iniciar sesion");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 500);

		// JTextField
		textoNombreUsuario = new JTextField();
		textoNombreUsuario.setBounds(260, 150, 100, 20);
		textoContrasenya = new JTextField();
		textoContrasenya.setBounds(260, 200, 100, 20);

		// JLabel
		lbTextoNombreUsuario = new JLabel("Nombre de usuario: ");
		lbTextoNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoNombreUsuario.setBounds(150, 150, 101, 20);
		lbTextoContrasenya = new JLabel("Contrasenya: ");
		lbTextoContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		lbTextoContrasenya.setBounds(150, 200, 100, 20);

		// JRadioButton
		rbIniciarS = new JRadioButton("Iniciar sesion", false);
		rbIniciarS.setBounds(276, 5, 91, 23);
		rbRegistrar = new JRadioButton("Registrar nueva cuenta", true);
		rbRegistrar.setBounds(372, 5, 141, 23);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbIniciarS);
		bg.add(rbRegistrar);

		panelIS = new JPanel();
		panelIS.setLayout(null);
		panelIS.add(textoContrasenya);
		panelIS.add(textoNombreUsuario);
		panelIS.add(lbTextoContrasenya);
		panelIS.add(lbTextoNombreUsuario);
		panelIS.add(rbIniciarS);
		panelIS.add(rbRegistrar);

		getContentPane().add(panelIS);

		setVisible(true);
	}

	public class ManejadorBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			nombreUsuario = textoNombreUsuario.getText();
			InterfazInstructor i = new InterfazInstructor();
			MenuPrincipal.this.dispose();
		}
	}

	public class ManejadorRB implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(rbIniciarS)) {
				textoRepetirC.setVisible(false);
			} else if (e.getSource().equals(rbRegistrar)) {
				textoRepetirC.setVisible(true);
			}
		}
	}

}