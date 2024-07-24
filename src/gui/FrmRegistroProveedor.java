package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidad.Proveedor;
import model.ProveedorModel;
import util.Validaciones;

//import util.Validaciones;

public class FrmRegistroProveedor extends JInternalFrame implements  ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JTextField txtNombre;
	//private JTextField txtidProveedor;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtPais;
	private JButton btnRegistar;
	//PROBANDO CON GUI
	/*
	private JTextField txtNombre2;
	private JTextField txtApellido;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	 */
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroProveedor frame = new FrmRegistroProveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmRegistroProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de proveedor");
		setBounds(100, 100, 542, 445);
		getContentPane().setLayout(null);
		//TITULO
		JLabel lblRegistroProveedor = new JLabel("RegistroProveedor");
		lblRegistroProveedor.setFont(new Font("Tahoma", Font.BOLD,16));
		lblRegistroProveedor.setBounds(165, 45, 199, 16);
		getContentPane().add(lblRegistroProveedor);
		
		/*JLabel lblidProveedor = new JLabel("idProveedor");
		lblidProveedor.setBounds(48, 108, 56, 16);
		getContentPane().add(lblidProveedor);*/
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(48, 89, 56, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(48, 131, 56, 16);
		getContentPane().add(lblApellido);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(48, 176, 56,16);
		getContentPane().add(lblDNI);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(48, 211, 56,16);
		getContentPane().add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(48, 256, 56,16);
		getContentPane().add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(48, 291, 56,16);
		getContentPane().add(lblCorreo);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(48, 332, 56,16);
		getContentPane().add(lblPais);
		//ERROR
		btnRegistar = new JButton("Registar");
		btnRegistar.addActionListener(this);
		btnRegistar.setBounds(393, 203, 121, 32);
		getContentPane().add(btnRegistar);
		//TEXTFIELD
		txtNombre = new JTextField();
		txtNombre.setBounds(248, 86, 116, 22);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(248, 128, 116, 22);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(248, 173, 116, 22);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(248, 208, 116, 22);
		getContentPane().add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(248, 253, 116, 22);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(248, 288, 116, 22);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtPais = new JTextField();
		txtPais.setBounds(248, 326, 116, 22);
		getContentPane().add(txtPais);
		txtPais.setColumns(10);
		
		
		}//FIN FRAME
	
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnRegistar) {
				do_btnRegistar_actionPerformed(e);
			}
		}//FIN 
		
		protected void do_btnRegistar_actionPerformed(ActionEvent e) {
			String Nombre = txtNombre.getText().trim();
			String Apellido = txtApellido.getText().trim();
			String DNI = txtDNI.getText().trim();
			String Direccion = txtDireccion.getText().trim();
			String Telefono = txtTelefono.getText().trim();
			String Correo = txtCorreo.getText().trim();
			String Pais = txtPais.getText().trim();
			if(Nombre.matches("[a-zA-ZáéíóúñüÁÉÍÓÚÑÜ\\s]{2,20}")==false){ 
			 mensaje("Nombre es de 2 a 20 caracteres");
			}else if (Apellido.matches("[a-zA-ZáéíóúñüÁÉÍÓÚÑÜ\\\\s]{2,20}")==false) {
			mensaje("Apellido es de 2 a 20 caracteres");
			}else if(DNI.matches("[0-9]{8}")==false) {
				mensaje("El DNI solo tiene 8 digitos");
			}else if(!Direccion.matches(Validaciones.DIRECCION)) {
				mensaje("La dirrecion solo tiene 200 caracteres");
			}else if(Telefono.matches("[9][0-9]{8}")==false) {
				mensaje("El telefono tiene 9 digitos y empieza con 9");
			}else if(Correo.matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")==false) {
					mensaje("El correo solo tiene 30 caracteres como maximo");
			}else if(Pais.matches("[a-zA-ZáéíóúñüÁÉÍÓÚÑÜ\\s]{2,15}")==false) {
				mensaje("El pais solo tiene 15 caracteres");
			}else {
				Proveedor obj = new Proveedor();
				obj.setNombre(Nombre);
				obj.setApellido(Apellido);
				obj.setDni(DNI);
				obj.setDireccion(Direccion);
				obj.setTelefono(Telefono);
				obj.setCorreo(Correo);
				obj.setPais(Pais);
				
				ProveedorModel model = new ProveedorModel();
				int s = model.insertaProveedor(obj);
				
				if(s>0){
					mensaje("Registro exitoso");
					}else {
					mensaje("Registro erroneo");
					}
				
			}//FIN ELSE
			
		}//BTNREGISTRAR
		void mensaje(String m) {
			JOptionPane.showMessageDialog(this, m);

 }
}