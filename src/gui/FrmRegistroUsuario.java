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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Usuario;
import model.UsuarioModel;
import util.Validaciones;

public class FrmRegistroUsuario extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblRegistro;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JLabel lblApellido;
	private JTextField txtDNI;
	private JLabel lblDni;
	private JTextField txtLogin;
	private JLabel lblLogin;
	private JLabel lblPassword;
	private JTextField txtCorreo;
	private JLabel lblCorreo;
	private JTextField txtFNAC;
	private JLabel lblFNAC;
	private JTextField txtDireccion;
	private JLabel lblDireccion;
	private JButton btnRegistro;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroUsuario frame = new FrmRegistroUsuario();
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
	public FrmRegistroUsuario() {
		//getContentPane().setBackground(UIManager.getColor("Button.background"));
		//getContentPane().setForeground(SystemColor.desktop);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Registro de usuario");
		setBounds(100, 100, 445, 414);
		getContentPane().setLayout(null);

		lblRegistro = new JLabel("Registro de Usuario");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRegistro.setBounds(42, 11, 361, 26);
		getContentPane().add(lblRegistro);

		lblNombre = new JLabel("Nombre :");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNombre.setBounds(102, 56, 63, 20);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(197, 58, 165, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(197, 86, 165, 20);
		getContentPane().add(txtApellido);

		lblApellido = new JLabel("Apellido :");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblApellido.setBounds(102, 86, 63, 17);
		getContentPane().add(lblApellido);

		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(197, 117, 165, 20);
		getContentPane().add(txtDNI);

		lblDni = new JLabel("Dni :");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDni.setBounds(133, 116, 32, 19);
		getContentPane().add(lblDni);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(197, 148, 165, 20);
		getContentPane().add(txtLogin);

		lblLogin = new JLabel("Login :");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLogin.setBounds(121, 148, 44, 17);
		getContentPane().add(lblLogin);

		lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPassword.setBounds(97, 179, 68, 17);
		getContentPane().add(lblPassword);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(197, 210, 165, 20);
		getContentPane().add(txtCorreo);

		lblCorreo = new JLabel("Correo :");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCorreo.setBounds(112, 210, 53, 23);
		getContentPane().add(lblCorreo);

		txtFNAC = new JTextField();
		txtFNAC.setColumns(10);
		txtFNAC.setBounds(197, 241, 165, 20);
		getContentPane().add(txtFNAC);

		lblFNAC = new JLabel("Fecha de Nacimiento :");
		lblFNAC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFNAC.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFNAC.setBounds(21, 244, 155, 17);
		getContentPane().add(lblFNAC);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(197, 271, 165, 20);
		getContentPane().add(txtDireccion);

		lblDireccion = new JLabel("Direccion :");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDireccion.setBounds(95, 272, 70, 17);
		getContentPane().add(lblDireccion);

		btnRegistro = new JButton("Registrar");
		btnRegistro.addActionListener(this);
		btnRegistro.setBounds(174, 321, 89, 23);
		getContentPane().add(btnRegistro);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(197, 179, 165, 20);
		getContentPane().add(txtpassword);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistro) {
			actionPerformedBtnNewButton(e);
		}
	}

	protected void actionPerformedBtnNewButton(ActionEvent e) {

		String nom = txtNombre.getText();
		String ape = txtApellido.getText();
		String dni = txtDNI.getText();
		String log = txtLogin.getText();
		String contra = String.valueOf(txtpassword.getPassword());
		String correo = txtCorreo.getText();
		String fecnac = txtFNAC.getText();
		String dire = txtDireccion.getText();

		if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("Nombre Invalido");
		} else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("Apellido Invalido ");
		} else if (!dni.matches(Validaciones.DNI)) {
			mensaje("DNI Invalido");
		}else if(!log.matches(Validaciones.TEXTOyNUM)) {
			mensaje("Login Invalido");
		}else if(!contra.matches(Validaciones.CONTRASEÑA)) {
			mensaje("Contraseña Invalida");
		}else if(!correo.matches(Validaciones.CORREO)) {
			mensaje("Correo Invalido");
		}else if(!fecnac.matches(Validaciones.FECHA)) {
			mensaje("Fecha de Nacimiento Invalida");
		}else {
			Usuario ObjU=new Usuario();
			ObjU.setNombre(nom);
			ObjU.setApellido(ape);
			ObjU.setDni(dni);
			ObjU.setLogin(log);
			ObjU.setPassword(contra);
			ObjU.setCorreo(correo);
			ObjU.setFecnac(fecnac);
			ObjU.setDireccion(dire);
			
			UsuarioModel model=new UsuarioModel();
			int salida=model.insertaUsuario(ObjU);
			if(salida > 0) {
				mensaje("Registro de Sala Exitoso");
			}else {
				mensaje("Hubo un error al registrar Sala");
			}
			
			
			
		}
	}

	void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
}
