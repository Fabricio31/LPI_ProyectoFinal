package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Alumno;
import model.AlumnoModel;
import util.Validaciones;

public class FrmRegistroAlumno extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtDNI;
	private JTextField txtCorreo;
	private JTextField txtfechaNacimiento;
	
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroAlumno frame = new FrmRegistroAlumno();
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
	public FrmRegistroAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Alumno");
		setBounds(100, 100, 713, 458);
		getContentPane().setLayout(null);
		
		JLabel lblRegistroAlumno = new JLabel("Registro Alumno");
		lblRegistroAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroAlumno.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRegistroAlumno.setBounds(261, 27, 186, 30);
		getContentPane().add(lblRegistroAlumno);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(28, 113, 88, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(28, 156, 69, 16);
		getContentPane().add(lblApellidos);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(28, 196, 78, 16);
		getContentPane().add(lblDNI);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(28, 237, 56, 16);
		getContentPane().add(lblCorreo);
		
		JLabel lblfechaNacimiento = new JLabel("Fecha de Nacimiento");
		lblfechaNacimiento.setBounds(28, 276, 136, 16);
		getContentPane().add(lblfechaNacimiento);
		
		
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(544, 206, 97, 25);
		getContentPane().add(btnRegistrar);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(239, 110, 116, 22);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(239, 153, 116, 22);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(239, 193, 116, 22);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(239, 234, 116, 22);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtfechaNacimiento = new JTextField();
		txtfechaNacimiento.setBounds(239, 273, 116, 22);
		getContentPane().add(txtfechaNacimiento);
		txtfechaNacimiento.setColumns(10);
		


	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnRegistrar) {
			handle_btnRegistrar_actionPerformed(arg0);
		}
	}
	protected void handle_btnRegistrar_actionPerformed(ActionEvent arg0) 
	{
		//btnRegistrar
		registar();
	}
	public void registar()
	{
		String Nombre = txtNombres.getText();
		String Apellido = txtApellidos.getText();
		String DNI = txtDNI.getText();
		String Correo = txtCorreo.getText();
		String fechaNac = txtfechaNacimiento.getText();
		if (!Nombre.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
			return;
		}
		if (!Apellido.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 2 a 20 caracteres");
			return;
		}
		if (!DNI.matches(Validaciones.DNI)) {
			mensaje("El DNI solo tiene 8 digitos");
			return;
		}
		if (!Correo.matches(Validaciones.CORREO)) {
			mensaje("El correo solo tiene 30 digitos");
			return;
		}
		if (!fechaNac.matches(Validaciones.FECHA)) {
			mensaje("La fecha tiene formato yyyy-MM-dd");
			return;
		}
		Alumno obj = new Alumno();
		obj.setNombres(Nombre);
		obj.setApellidos(Apellido);
		obj.setDNI(DNI);
		obj.setCorreo(Correo);
		obj.setFechaNacimiento(Date.valueOf(fechaNac));
		
		
		AlumnoModel model = new AlumnoModel();
		int s = model.insertaAlumno(obj);
		if(s>0){
			mensaje("Registro exitoso");
			}else {
			mensaje("Registro erroneo");
			}
			
			
	}//fin metodo
	
		//Mensaje
		void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);}
}
