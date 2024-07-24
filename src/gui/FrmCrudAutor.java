package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entidad.AutorCrud;
import model.AutorCrudModel;
import util.Excel;
import util.Validaciones;

public class FrmCrudAutor extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtFechadeNacimiento;
	private JTextField txtNacionalidad;
	private JTextField txtGrado;
	private JTable table;
	int idSeleccionado=-1;
	Excel obj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudAutor frame = new FrmCrudAutor();
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
	public FrmCrudAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento Autor");
		setBounds(100, 100, 890, 502);
		getContentPane().setLayout(null);
		
		JLabel lblMantenimientoAutor = new JLabel("Mantenimiento Autor");
		lblMantenimientoAutor.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblMantenimientoAutor.setBounds(312, 31, 250, 28);
		getContentPane().add(lblMantenimientoAutor);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombres.setBounds(10, 148, 71, 14);
		getContentPane().add(lblNombres);
		
		txtNombres = new JTextField();
		txtNombres.setColumns(10);
		txtNombres.setBounds(91, 145, 199, 20);
		getContentPane().add(txtNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellidos.setBounds(10, 188, 71, 14);
		getContentPane().add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(91, 185, 199, 20);
		getContentPane().add(txtApellidos);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaDeNacimiento.setBounds(10, 226, 121, 14);
		getContentPane().add(lblFechaDeNacimiento);
		
		txtFechadeNacimiento = new JTextField();
		txtFechadeNacimiento.setColumns(10);
		txtFechadeNacimiento.setBounds(123, 223, 167, 20);
		getContentPane().add(txtFechadeNacimiento);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNacionalidad.setBounds(10, 267, 89, 14);
		getContentPane().add(lblNacionalidad);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setColumns(10);
		txtNacionalidad.setBounds(91, 264, 199, 20);
		getContentPane().add(txtNacionalidad);
		
		JLabel lblGrado = new JLabel("Grado");
		lblGrado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGrado.setBounds(10, 305, 58, 14);
		getContentPane().add(lblGrado);
		
		txtGrado = new JTextField();
		txtGrado.setColumns(10);
		txtGrado.setBounds(91, 302, 199, 20);
		getContentPane().add(txtGrado);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnRegistrar_actionPerformed(e);
			}
		});
		btnRegistrar.setBounds(10, 355, 89, 47);
		getContentPane().add(btnRegistrar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnActualizar_actionPerformed(e);
			}
		});
		btnActualizar.setBounds(109, 355, 89, 47);
		getContentPane().add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnEliminar_actionPerformed(e);
			}
		});
		btnEliminar.setBounds(208, 355, 89, 47);
		getContentPane().add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(312, 108, 552, 353);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.setBounds(123, 411, 56, 50);
		getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_table_mouseClicked(e);
			}
		});
		table.setShowHorizontalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombres", "Apellidos", "Fecha de Nacimiento", "Fecha de Creacion", "Nacionalidad", "Grado"
			}
		) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(3).setPreferredWidth(114);
		table.getColumnModel().getColumn(4).setPreferredWidth(102);
		scrollPane.setViewportView(table);
		lista();

	}
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		inserta();
	}
	protected void do_btnActualizar_actionPerformed(ActionEvent e) {
		actualiza();
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		eliminar();
	}
	
	public void lista() 
	{
		AutorCrudModel m=new AutorCrudModel();
		List<AutorCrud> lista = m.listarAutor();
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		
		dtm.setRowCount(0);
		
		for(AutorCrud x : lista) 
		{
			Object[] f = {x.getIdAutor(),x.getNombres(),x.getApellidos(),x.getFechanacimiento(),x.getFecharegistro(),x.getNacionalidad(),x.getGrado()};//AR
			dtm.addRow(f);
		}
	}
	
	public void inserta() {

		String nombre = txtNombres.getText().trim(); 
		String apellidos=txtApellidos.getText().trim();
		String fechaNacimiento=txtFechadeNacimiento.getText().trim();
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
		AutorCrud obj=new AutorCrud();
		obj.setIdAutor(idSeleccionado);
		obj.setNombres(nombre);
		obj.setApellidos(apellidos);
		obj.setFechanacimiento(Date.valueOf(fechaNacimiento));
		obj.setNacionalidad(nacionalidad);
		obj.setGrado(grado);
		
		AutorCrudModel m=new AutorCrudModel();
		int s=m.InsertarAutor(obj);
		if(s>0) {
			mensaje("Se inserto correctamente");
			limpiarCajasTexto();
			lista();
		}else {
			mensaje("Registro erroneo");
		}
		
	} 
	public void actualiza() {
		if(idSeleccionado ==-1) {
			mensaje("Selecciona un Proveedor");
		}else {
			String nombre = txtNombres.getText().trim(); 
			String apellidos=txtApellidos.getText().trim();
			String fechaNacimiento=txtFechadeNacimiento.getText().trim();
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
			}else {
			AutorCrud obj=new AutorCrud();
			obj.setIdAutor(idSeleccionado);
			obj.setNombres(nombre);
			obj.setApellidos(apellidos);
			obj.setFechanacimiento(Date.valueOf(fechaNacimiento));
			obj.setNacionalidad(nacionalidad);
			obj.setGrado(grado);
			
			AutorCrudModel m=new AutorCrudModel();
			int s=m.ActualizarAutor(obj);
			if(s>0) {
				mensaje("Se actualizo correctamente");
				limpiarCajasTexto();
				lista();
			}else {
				mensaje("Registro erroneo");
			}
			}
			}	
	}
	
	public void busca() {
		int fila = table.getSelectedRow();
		idSeleccionado = (int) table.getValueAt(fila,0);
		String nombre=(String)table.getValueAt(fila, 1);
		String apellidos=(String)table.getValueAt(fila, 2);
		Date fechaNac=(Date)table.getValueAt(fila, 3);
		String nacionalidad=(String)table.getValueAt(fila, 5);
		String grado=(String)table.getValueAt(fila, 6);

		txtNombres.setText(nombre);
		txtApellidos.setText(apellidos);
		txtFechadeNacimiento.setText(fechaNac.toString());
		txtNacionalidad.setText(nacionalidad);
		txtGrado.setText(grado);
	
	}
	
	public void eliminar() {
		if(idSeleccionado ==-1) {
			mensaje("Selecciona un registro");
		}else {
			 AutorCrudModel m=new AutorCrudModel();
			int s=m.eliminar(idSeleccionado);
			if(s>0) {
				mensaje("Se elimino el registro");
				lista();//Agregado
				limpiarCajasTexto();//Agregado
				idSeleccionado =-1;//Agregado
			}else {
				mensaje("Error al eliminar registro");
			}
		}
	}
	
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	
	
	
	void limpiarCajasTexto() {
		txtNombres.requestFocus();
		txtNombres.setText("");
		txtApellidos.setText("");
		txtFechadeNacimiento.setText("");
		txtNacionalidad.setText("");
		txtGrado.setText("");
		txtNombres.requestFocus();
		
}
	protected void do_table_mouseClicked(MouseEvent e) {
		busca();
	}
	
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		try {
			obj= new Excel();
			Excel.crearArchivoExcel(table);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
