package Visual;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuAdmin extends JFrame {
	private JButton btnCrudVehiculos,btnCrudUsuarios,btnCrudInstructores,btnInterfaz,btnVolver, btnGrafica;
	ManejadorA ma=new ManejadorA();
	public MenuAdmin() {
		super("Menu inicial ADMIN");
		setBounds(100, 100, 310, 357);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//botones
			//acceder crud vehiculos
		btnCrudVehiculos = new JButton("Crud vehiculos");
		btnCrudVehiculos.setBounds(80, 69, 126, 38);
		btnCrudVehiculos.addActionListener(ma);
		getContentPane().add(btnCrudVehiculos);
		
		btnCrudUsuarios = new JButton("Crud usuarios");
		btnCrudUsuarios.setBounds(80, 117, 126, 38);
		btnCrudUsuarios.addActionListener(ma);
		getContentPane().add(btnCrudUsuarios);
		
		btnCrudInstructores = new JButton("Crud instructores");
		btnCrudInstructores.setBounds(80, 165, 126, 38);
		btnCrudInstructores.addActionListener(ma);
		getContentPane().add(btnCrudInstructores);
		
		btnInterfaz = new JButton("Interfaz principal");
		btnInterfaz.setBounds(80, 213, 126, 38);
		btnInterfaz.addActionListener(ma);
		getContentPane().add(btnInterfaz);
		
		btnVolver = new JButton("Deslogearse");
		btnVolver.setBounds(80, 261, 126, 38);
		btnVolver.addActionListener(ma);
		getContentPane().add(btnVolver);
		
		btnGrafica = new JButton("Grafica");
		btnGrafica.setBounds(80, 21, 126, 38);
		getContentPane().add(btnGrafica);
		btnGrafica.addActionListener(ma);
		
		
		setVisible(true);
	}
	private class ManejadorA implements ActionListener{

		// btnCrudVehiculos,btnCrudUsuarios,btnCrudInstructores,btnInterfaz,
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o=e.getSource();
			
			if(o==btnCrudVehiculos) {
				new CrudVehiculo();
				dispose();
			}else if(o==btnCrudUsuarios){
				new CrudUsuarios();
				dispose();
			}else if(o==btnCrudInstructores) {
				new CrudInstructor();
				dispose();
			}else if(o==btnInterfaz) {
				new InterfazAdmin();
				dispose();
			}else if(o==btnVolver) {
				new MenuPrincipal();
				dispose();
			}else if(o==btnGrafica) {
				new Grafica();
				dispose();
			}
			
		}
		
	}
}
