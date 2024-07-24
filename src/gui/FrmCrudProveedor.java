package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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

import entidad.ProveedorCrud;
import model.ProveedorCrudModel;
import util.Excel;
import util.Validaciones;

public class FrmCrudProveedor extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtDNI;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtPais;
	private JTable table;
	int idSeleccionado = -1;
	 
	Excel obj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudProveedor frame = new FrmCrudProveedor();
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
	public FrmCrudProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setTitle("Actualiza Proveedor");
		setBounds(100, 100, 890, 502);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Mantenimento Proveedor");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(287, 11, 296, 28);
		getContentPane().add(lblNewLabel);

		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(40, 86, 69, 14);
		getContentPane().add(lblNombres);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(40, 123, 69, 14);
		getContentPane().add(lblApellidos);

		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(40, 165, 57, 14);
		getContentPane().add(lblDni);

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(40, 208, 69, 14);
		getContentPane().add(lblDireccion);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(40, 247, 69, 14);
		getContentPane().add(lblTelefono);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(40, 290, 46, 14);
		getContentPane().add(lblCorreo);

		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(40, 327, 46, 14);
		getContentPane().add(lblPais);

		txtNombres = new JTextField();
		txtNombres.setColumns(10);
		txtNombres.setBounds(119, 83, 199, 20);
		getContentPane().add(txtNombres);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(119, 120, 199, 20);
		getContentPane().add(txtApellidos);

		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(119, 162, 199, 20);
		getContentPane().add(txtDNI);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(119, 205, 199, 20);
		getContentPane().add(txtDireccion);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(119, 244, 199, 20);
		getContentPane().add(txtTelefono);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(119, 287, 199, 20);
		getContentPane().add(txtCorreo);

		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(119, 324, 199, 20);
		getContentPane().add(txtPais);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnRegistrar_actionPerformed(e);
			}
		});
		btnRegistrar.setBounds(20, 352, 89, 47);
		getContentPane().add(btnRegistrar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnEliminar_actionPerformed(e);
			}
		});
		btnEliminar.setBounds(218, 355, 89, 47);
		getContentPane().add(btnEliminar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnActualizar_actionPerformed(e);
			}
		});
		btnActualizar.setBounds(119, 352, 89, 47);
		getContentPane().add(btnActualizar);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.setBounds(140, 410, 56, 50);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(330, 94, 534, 367);
		getContentPane().add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_table_mouseClicked(e);
			}
		});
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombres", "Apellidos", "DNI", "Direcci\u00F3n", "Telefono", "Correo", "Pais" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		lista();

	}

	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		insertar();
	}

	public void lista() {
		ProveedorCrudModel m = new ProveedorCrudModel();
		List<ProveedorCrud> lista = m.listaProveedorCrud();
		

		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

		dtm.setRowCount(0);

		for (ProveedorCrud x : lista) {
			Object[] f = { x.getIdProveedor(), x.getNombre(), x.getApellido(), x.getDNI(), x.getDireccion(),
					x.getTelefono(), x.getCorreo(), x.getPais() };// AR
			dtm.addRow(f);
		}
	}

	public void insertar() {
		String nombre = txtNombres.getText().trim();
		String apellidos = txtApellidos.getText().trim();
		String dni = txtDNI.getText().trim();
		String direccion = txtDireccion.getText().trim();
		String telefono = txtTelefono.getText().trim();
		String correo = txtCorreo.getText().trim();
		String pais = txtPais.getText().trim();

		if (!nombre.matches(Validaciones.NOMBRE)) {
			mensaje("Nombre es de 2 a 20 caracteres");
		}
		if (!apellidos.matches(Validaciones.APELLIDO)) {
			mensaje("Apellidos es de 2 a 20 caracteres");
		}
		if (!dni.matches(Validaciones.DNI)) {
			mensaje("El DNI tiene solo 8 digitos");
		}
		if (!direccion.matches(Validaciones.DIRECCION)) {
			mensaje("La direccion debe ser de 10 a 200 caracteres");
		}
		if (!telefono.matches(Validaciones.TELEFONO)) {
			mensaje("El Telefono debe ser de 9 numeros ");
		}
		if (!correo.matches(Validaciones.CORREO)) {
			mensaje("Formato de Correo Invalido");
		}
		if (!pais.matches(Validaciones.TEXTO)) {
			mensaje("Solo texto de entre 2 a 20 caracteres");
		} else {

			ProveedorCrud obj = new ProveedorCrud();
			obj.setNombre(nombre);
			obj.setApellido(apellidos);
			obj.setDNI(dni);
			obj.setDireccion(direccion);
			obj.setTelefono(telefono);
			obj.setCorreo(correo);
			obj.setPais(pais);

			ProveedorCrudModel m = new ProveedorCrudModel();
			int s = m.insertaProveedor(obj);
			if (s > 0) {
				mensaje("Se inserto correctamente");
				limpiarCajasTexto();
				lista();
			} else {
				mensaje("Registro erroneo");
			}
		}
	}

	public void actualiza() {
		if (idSeleccionado == -1) {
			mensaje("Selecciona un Proveedor");
		} else {
			String nombre = txtNombres.getText().trim();
			String apellidos = txtApellidos.getText().trim();
			String dni = txtDNI.getText().trim();
			String direccion = txtDireccion.getText().trim();
			String telefono = txtTelefono.getText().trim();
			String correo = txtCorreo.getText().trim();
			String pais = txtPais.getText().trim();

			if (!nombre.matches(Validaciones.NOMBRE)) {
				mensaje("Nombre es de 2 a 20 caracteres");
			}
			if (!apellidos.matches(Validaciones.APELLIDO)) {
				mensaje("Apellidos es de 2 a 20 caracteres");
			}
			if (!dni.matches(Validaciones.DNI)) {
				mensaje("El DNI tiene solo 8 digitos");
			}
			if (!direccion.matches(Validaciones.DIRECCION)) {
				mensaje("La direccion debe ser de 10 a 200 caracteres");
			}
			if (!telefono.matches(Validaciones.TELEFONO)) {
				mensaje("El Telefono debe ser de 9 numeros ");
			}
			if (!correo.matches(Validaciones.CORREO)) {
				mensaje("Formato de Correo Invalido");
			}
			if (!pais.matches(Validaciones.TEXTO)) {
				mensaje("Solo texto de entre 2 a 20 caracteres");
			}
			ProveedorCrud obj = new ProveedorCrud();
			obj.setIdProveedor(idSeleccionado);
			obj.setNombre(nombre);
			obj.setApellido(apellidos);
			obj.setDNI(dni);
			obj.setDireccion(direccion);
			obj.setTelefono(telefono);
			obj.setCorreo(correo);
			obj.setPais(pais);

			ProveedorCrudModel m = new ProveedorCrudModel();
			int s = m.actualizaProveedorCrud(obj);
			if (s > 0) {
				mensaje("Se actualizo correctamente");
				limpiarCajasTexto();
				lista();
			} else {
				mensaje("Registro erroneo");
			}
		}
	}

	protected void do_table_mouseClicked(MouseEvent e) {
		busca();
	}

	public void eliminar() {
		if (idSeleccionado == -1) {
			mensaje("Selecciona un registro");
		} else {
			ProveedorCrudModel m = new ProveedorCrudModel();
			int s = m.eliminaSalaCrud(idSeleccionado);
			if (s > 0) {
				mensaje("Se elimino el registro");
				lista();// Agregado
				limpiarCajasTexto();// Agregado
				idSeleccionado = -1;// Agregado
			} else {
				mensaje("Error al eliminar registro");
			}
		}
	}

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	public void busca() {
		int fila = table.getSelectedRow();
		idSeleccionado = (Integer) table.getValueAt(fila, 0);
		String nombre = (String) table.getValueAt(fila, 1);
		String apellidos = (String) table.getValueAt(fila, 2);
		String dni = (String) table.getValueAt(fila, 3);
		String direccion = (String) table.getValueAt(fila, 4);
		String telefono = (String) table.getValueAt(fila, 5);
		String correo = (String) table.getValueAt(fila, 6);
		String pais = (String) table.getValueAt(fila, 7);

		txtNombres.setText(nombre);
		txtApellidos.setText(apellidos);
		txtDNI.setText(dni);
		txtDireccion.setText(direccion);
		txtTelefono.setText(telefono);
		txtCorreo.setText(correo);
		txtPais.setText(pais);

	}

	// LIMPIAR
	void limpiarCajasTexto() {
		txtNombres.setText("");
		txtApellidos.setText("");
		txtDNI.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		txtPais.setText("");
	}

	protected void do_btnActualizar_actionPerformed(ActionEvent e) {
		actualiza();
	}

	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		eliminar();
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		
		try {
			obj=new Excel();
			Excel.crearArchivoExcel(table);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
