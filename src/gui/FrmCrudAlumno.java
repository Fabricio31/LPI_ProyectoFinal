package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entidad.Alumno;
import model.AlumnoCrudModel;
import util.Excel;
import util.Validaciones;
import javax.swing.ImageIcon;

public class FrmCrudAlumno extends JInternalFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JTextField txtCorreo;
	private JTextField txtFechaNacimiento;
	private JTable table;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	int idSeleccionado = -1;
	Excel obj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudAlumno frame = new FrmCrudAlumno();
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
	public FrmCrudAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Actualiza Alumno");
		setBounds(100, 100, 722, 601);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mantenimiento Alumno");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(189, 26, 337, 21);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(35, 92, 56, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(35, 133, 56, 16);
		getContentPane().add(lblApellido);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(35, 179, 56, 16);
		getContentPane().add(lblDNI);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(35, 222, 56, 16);
		getContentPane().add(lblCorreo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(35, 272, 142, 16);
		getContentPane().add(lblFechaDeNacimiento);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(227, 89, 116, 22);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(227, 130, 116, 22);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(227, 176, 116, 22);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(227, 219, 116, 22);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(227, 269, 116, 22);
		getContentPane().add(txtFechaNacimiento);
		txtFechaNacimiento.setColumns(10);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(543, 88, 97, 25);
		getContentPane().add(btnRegistrar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(543, 148, 97, 25);
		getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(543, 206, 97, 25);
		getContentPane().add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 333, 682, 195);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"idAlumno","Nombre", "Apellidos", "DNI", "Correo", "Fecha de Nacimiento"
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
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setBounds(566, 255, 56, 50);
		getContentPane().add(btnNewButton);
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		header.setResizingAllowed(false);
		//Llamar a lista
		Listar();
 
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEliminar) {
			handle_btnEliminar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			handle_btnActualizar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnRegistrar) {
			handle_btnRegistrar_actionPerformed(arg0);
		}
	}
	protected void handle_btnRegistrar_actionPerformed(ActionEvent arg0) 
	{
		//btnRegistra
		Inserta();
	}
	protected void handle_btnActualizar_actionPerformed(ActionEvent arg0) 
	{
		//btnActualiza
		Actualiza();
	}
	
	protected void handle_btnEliminar_actionPerformed(ActionEvent arg0) 
	{
		//btnElimina
		Elimina();
	}
	//mouse
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			handle_table_mouseClicked(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void handle_table_mouseClicked(MouseEvent arg0) 
	{
		//BUSCA
		Busca();
		
	}

	
	//Creando los 5 metodos
	//LISTA
	public void Listar() 
	{
		AlumnoCrudModel m = new AlumnoCrudModel();
		List<Alumno> lista=m.listaAlumno();
		DefaultTableModel dtm =(DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		for(Alumno x : lista) 
		{
			Object[] f = {x.getIdAlumno(),x.getNombres(),x.getApellidos(),x.getDNI(),x.getCorreo(),x.getFechaNacimiento()};//x.getIdAlumno()
			dtm.addRow(f);
		}
	}
	//INSERTA 
	public void Inserta() 
	{
		
		String Nombre = txtNombre.getText();
		String Apellido = txtApellido.getText();
		String DNI = txtDNI.getText();
		String Correo = txtCorreo.getText();
		String fechaNac = txtFechaNacimiento.getText();
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
		AlumnoCrudModel model = new AlumnoCrudModel();
		int s = model.insertaAlumno(obj);
		if(s>0){
			mensaje("Registro exitoso");
			limpiarCajasTexto();
			Listar();
			}else {
			mensaje("Registro erroneo");
			}
		
		
	}
	//ACTUALIZA	
	public void Actualiza() 
	{
		 if(idSeleccionado ==-1) {
				mensaje("Selecciona un registro");
			}else {
				String Nombre = txtNombre.getText();
				String Apellido = txtApellido.getText();
				String DNI = txtDNI.getText();
				String Correo = txtCorreo.getText();
				String fechaNac = txtFechaNacimiento.getText();
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
					
				AlumnoCrudModel m = new AlumnoCrudModel();
					int s = m.insertaAlumno(obj);
					if(s > 0) {
						mensaje("Actualizacion exitosa");
						limpiarCajasTexto();
						Listar();
						}else {
							mensaje("Actualizacion erronea");
						}
				}
		
	
	}
	//ELIMINA
	public void Elimina() {
		if(idSeleccionado ==-1) {
			mensaje("Selecciona un registro");
		}else {
			AlumnoCrudModel model = new AlumnoCrudModel();
			int s =model.eliminaAlumnoCrud(idSeleccionado);
			if(s>0) {
				mensaje("Se elimino el registro");
				Listar();//Agregado
				limpiarCajasTexto();//Agregado
				idSeleccionado =-1;//Agregado
			}else {
				mensaje("Error al eliminar registro");
			}
		}
	}
	//BUSCA 
	public void Busca() {
	
		int fila = table.getSelectedRow();
		idSeleccionado=(Integer)table.getValueAt(fila, 0);
		String Nombre=(String)table.getValueAt(fila, 1);
		String Apellido=(String)table.getValueAt(fila, 2);
		String DNI=(String)table.getValueAt(fila, 3);
		String CORREO=(String)table.getValueAt(fila, 4);
		//probando
		Date fechaNac=(Date)table.getValueAt(fila, 5);
		
		txtNombre.setText(Nombre);
		txtApellido.setText(Apellido);
		txtDNI.setText(DNI);
		txtCorreo.setText(CORREO);
		txtFechaNacimiento.setText(String.valueOf(fechaNac));
		
	}
	
	//MENSAJE
	void mensaje(String m) 
	{
	JOptionPane.showMessageDialog(this, m);
	}
	//LIMPIAR	
	void limpiarCajasTexto() {
				txtNombre.setText("");
				txtApellido.setText("");
				txtCorreo.setText("");
				txtDNI.setText("");
				txtNombre.requestFocus();
				txtFechaNacimiento.setText("");
				
		}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		try {
		obj= new Excel();
		Excel.crearArchivoExcel(table);
		}catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
