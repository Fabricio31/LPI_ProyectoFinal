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

import entidad.Autor;
import model.AutorModel;
import util.Validaciones;

public class FrmRegistroAutor extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtFechaNacimiento;
	private JTextField txtNacionalidad;
	private JTextField txtGrado;
	int idSeleccionado=-1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroAutor frame = new FrmRegistroAutor();
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
	public FrmRegistroAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Autor");
		setBounds(100, 100, 450, 325);
		getContentPane().setLayout(null);
		
		JLabel lblRegistrarAutor = new JLabel("Registrar Autor");
		lblRegistrarAutor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegistrarAutor.setBounds(153, 11, 127, 19);
		getContentPane().add(lblRegistrarAutor);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(38, 70, 62, 14);
		getContentPane().add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(38, 101, 66, 14);
		getContentPane().add(lblApellidos);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(38, 129, 129, 14);
		getContentPane().add(lblFechaNacimiento);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(38, 160, 87, 14);
		getContentPane().add(lblNacionalidad);
		
		JLabel lblGrado = new JLabel("Grado");
		lblGrado.setBounds(38, 191, 64, 14);
		getContentPane().add(lblGrado);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(153, 67, 160, 20);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(153, 95, 160, 20);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(153, 126, 160, 20);
		getContentPane().add(txtFechaNacimiento);
		txtFechaNacimiento.setColumns(10);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setBounds(153, 158, 160, 20);
		getContentPane().add(txtNacionalidad);
		txtNacionalidad.setColumns(10);
		
		txtGrado = new JTextField();
		txtGrado.setBounds(153, 188, 160, 20);
		getContentPane().add(txtGrado);
		txtGrado.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnRegistrar_actionPerformed(e);
			}
		});
		btnRegistrar.setBounds(172, 239, 89, 23);
		getContentPane().add(btnRegistrar);


	}
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		inserta();
	}
	
	public void inserta() {

		String nombre = txtNombres.getText().trim(); 
		String apellidos=txtApellidos.getText().trim();
		String fechaNacimiento=txtFechaNacimiento.getText().trim();
		String nacionalidad=txtNacionalidad.getText().trim();
		String grado=txtGrado.getText().trim();
		
		
		if(!nombre.matches(Validaciones.NOMBRE)){
			mensaje("Nombre es de 2 a 20 caracteres");
		}
		if(!apellidos.matches(Validaciones.APELLIDO)) {
			mensaje("Apellidos es de 2 a 20 caracteres");
		}
		if(!fechaNacimiento.matches(Validaciones.FECHA)) {
			mensaje("Formato de Fecha Invalido");
		}
		if(!nacionalidad.matches(Validaciones.TEXTO)) {
			mensaje("Solo se permite texto");
		}
		if(!grado.matches(Validaciones.TEXTO)) {
			mensaje("Solo se permite texto");
		}
		Autor obj=new Autor();
		obj.setIdAutor(idSeleccionado);
		obj.setNombres(nombre);
		obj.setApellidos(apellidos);
		obj.setFechaNacimiento(Date.valueOf(fechaNacimiento));
		obj.setNacionalidad(nacionalidad);
		obj.setGrado(grado);
		
		AutorModel m=new AutorModel();
		int s=m.InsertarAutor(obj);
		if(s>0) {
			mensaje("Se inserto correctamente");
			limpiarCajasTexto();
		}else {
			mensaje("Registro erroneo");
		}
		
	} 
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	
	
	
	void limpiarCajasTexto() {
		txtNombres.requestFocus();
		txtNombres.setText("");
		txtApellidos.setText("");
		txtFechaNacimiento.setText("");
		txtNacionalidad.setText("");
		txtGrado.setText("");
		txtNombres.requestFocus();
		
}
}
