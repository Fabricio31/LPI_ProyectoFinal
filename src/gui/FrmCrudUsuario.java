package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entidad.Usuario;
import model.UsuarioModel;
import util.Excel;

public class FrmCrudUsuario extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblMantenimiento;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblUsuario;
	private JLabel lblContraseña;
	private JLabel lblDNI;
	private JLabel lblFechNac;
	private JLabel lblCorreo;
	private JLabel lblDireccion;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtContraseña;
	private JTextField txtDNI;
	private JTextField txtCorreo;
	private JTextField txtDireccion;
	private JTextField txtFechNac;
	private JScrollPane scp;
	private JTable table;
	
	int idUsuarioSeleccionado = -1;
	
	Excel obj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
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
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Actualiza Usuario");
		setBounds(100, 100, 988, 529);
		getContentPane().setLayout(null);
		
		lblMantenimiento = new JLabel("MANTENIMIENTO DE USARIO");
		lblMantenimiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimiento.setBounds(10, 27, 795, 35);
		getContentPane().add(lblMantenimiento);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(76, 89, 46, 14);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("Apellido :");
		lblApellido.setBounds(76, 116, 46, 14);
		getContentPane().add(lblApellido);
		
		lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(76, 152, 46, 14);
		getContentPane().add(lblUsuario);
		
		lblContraseña = new JLabel("Contrase\u00F1a :");
		lblContraseña.setBounds(76, 186, 46, 14);
		getContentPane().add(lblContraseña);
		
		lblDNI = new JLabel("DNI :");
		lblDNI.setBounds(435, 89, 46, 14);
		getContentPane().add(lblDNI);
		
		lblFechNac = new JLabel("Fecha Nacimiento :");
		lblFechNac.setBounds(435, 186, 46, 14);
		getContentPane().add(lblFechNac);
		
		lblCorreo = new JLabel("Correo :");
		lblCorreo.setBounds(435, 116, 46, 14);
		getContentPane().add(lblCorreo);
		
		lblDireccion = new JLabel("Direccion :");
		lblDireccion.setBounds(435, 152, 46, 14);
		getContentPane().add(lblDireccion);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(76, 226, 89, 23);
		getContentPane().add(btnRegistrar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(339, 226, 89, 23);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(588, 226, 89, 23);
		getContentPane().add(btnActualizar);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(130, 86, 136, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(132, 113, 136, 20);
		getContentPane().add(txtApellido);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(132, 149, 136, 20);
		getContentPane().add(txtUsuario);
		
		txtContraseña = new JTextField();
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(132, 183, 136, 20);
		getContentPane().add(txtContraseña);
		
		txtDNI = new JTextField();
		txtDNI.addKeyListener(this);
		txtDNI.setColumns(10);
		txtDNI.setBounds(486, 86, 136, 20);
		getContentPane().add(txtDNI);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(486, 113, 136, 20);
		getContentPane().add(txtCorreo);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(486, 149, 136, 20);
		getContentPane().add(txtDireccion);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.setBounds(816, 200, 56, 50);
		getContentPane().add(btnNewButton);
		
		txtFechNac = new JTextField();
		txtFechNac.setColumns(10);
		txtFechNac.setBounds(496, 183, 136, 20);
		getContentPane().add(txtFechNac);
		
		scp = new JScrollPane();
		scp.setBounds(10, 261, 952, 227);
		getContentPane().add(scp);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "DNI", "Usuario", "Contrase\u00F1a", "Correo", "Direccion", "Fecha de Nacimiento"
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
		table.getColumnModel().getColumn(0).setPreferredWidth(42);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		table.getColumnModel().getColumn(2).setPreferredWidth(96);
		table.getColumnModel().getColumn(6).setPreferredWidth(78);
		table.getColumnModel().getColumn(7).setPreferredWidth(87);
		table.getColumnModel().getColumn(8).setPreferredWidth(123);
		scp.setViewportView(table);
		listar();


	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
	}
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		
		insertar();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		eliminar();
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizar();
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedTable(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTable(MouseEvent e) {
		buscar();
	}
	public void insertar()
	{
		String nom = txtNombre.getText().trim();
        String ape = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim();
        String login = txtUsuario.getText().trim();
        String contra = txtContraseña.getText().trim();
        String correo = txtCorreo.getText().trim();
        String fech = txtFechNac.getText().trim();
        String dir = txtDireccion.getText().trim();

        if(!nom.matches(util.Validaciones.NOMBRE)){
            mensaje("Error en Nombre: Entre  3 a 25 Caracteres");
            return;
        }else if(!ape.matches(util.Validaciones.APELLIDO)){
            mensaje("Error en Apellido: Entre  5 a 25 Caracteres");
            return;
        }else if(!dni.matches(util.Validaciones.DNI)){
            mensaje("Error en Dni: 8 Dígitos");
            return;
        }else if(!login.matches(util.Validaciones.USUARIO)){
            mensaje("Error en Usuario: Entre  2 a 15 Caracteres");
            return;
        }else if(!contra.matches(util.Validaciones.CONTRASEÑA)){
            mensaje("Error en Contraseña: Entre  2 a 10 Caracteres");
            return;
        }else if(!correo.matches(util.Validaciones.CORREO)){
            mensaje("Error en Correo");
            return;
        }else if(!fech.matches(util.Validaciones.FECHA)){
            mensaje("Error en Fecha: Formato de fecha de registro es año, mes, día");
            return;
        }else if(!dir.matches(util.Validaciones.DIRECCION)){
            mensaje("Error en Direccion: Solo texto");
            return;
        }
        Usuario obj =  new Usuario();
        obj.setIdUsuario(idUsuarioSeleccionado);
        obj.setNombre(nom);
        obj.setApellido(ape);
        obj.setDni(dni);
        obj.setLogin(login);
        obj.setPassword(contra);
        obj.setCorreo(correo);
        obj.setFecnac(fech);
        obj.setDireccion(dir);

        UsuarioModel c = new UsuarioModel();
        int r = c.insertaUsuario(obj);
        if (r > 0) {
            listar();
            limpiarTxt();
            idUsuarioSeleccionado = -1;
            mensaje("Se ingresó correctamente");
        }else {
            mensaje("Error al ingresar");
        }
		
	}
	public void eliminar()
	{
		if(idUsuarioSeleccionado == -1) {
            mensaje("Seleccione un Usuario");
        }else {
            UsuarioModel c = new UsuarioModel();
            int r = c.eliminaUsuario(idUsuarioSeleccionado);
            if (r > 0) {
                mensaje("Se eliminó correctamente");
                listar();
                limpiarTxt();
                idUsuarioSeleccionado = -1;
            }else {
                mensaje("Error al eliminar");
            }
        }
	}
	public void actualizar()
	{
		if (idUsuarioSeleccionado == -1) {
            mensaje("Seleccione un usuario");
        } else {
			String nom    = txtNombre.getText();
			String ape    = txtApellido.getText();
			String dni    = txtDNI.getText();
			String login  = txtUsuario.getText();
			String contra = txtContraseña.getText();
			String correo = txtCorreo.getText();
			String fech   = txtFechNac.getText();
			String dir    = txtDireccion.getText();

			if(!nom.matches(util.Validaciones.NOMBRE)){
				mensaje("Error en Nombre: [Entre  3 a 25 Caracteres]");
				return;
			}else if(!ape.matches(util.Validaciones.APELLIDO)){
				mensaje("Error en Apellido: [Entre  5 a 25 Caracteres]");
				return;
			}else if(!dni.matches(util.Validaciones.DNI)){
				mensaje("Error en Dni: [8 Dígitos]");
				return;
			}else if(!login.matches(util.Validaciones.USUARIO)){
				mensaje("Error en Usuario: [Entre  2 a 15 Caracteres]");
				return;
			}else if(!contra.matches(util.Validaciones.CONTRASEÑA)){
				mensaje("Error en Contraseña: [Entre  2 a 10 Caracteres]");
				return;
			}else if(!correo.matches(util.Validaciones.CORREO)){
				mensaje("Error en Correo");
				return;
			}else if(!fech.matches(util.Validaciones.FECHA)){
				mensaje("Error en Fecha: [Formato de fecha de registro es año, mes, día]");
				return;	
			}else if(!dir.matches(util.Validaciones.DIRECCION)){
				mensaje("Error en Direccion: [Solo texto]");
				return;
			}

			Usuario obj =  new Usuario();
			obj.setIdUsuario(idUsuarioSeleccionado);
			obj.setNombre(nom);
			obj.setApellido(ape);
			obj.setDni(dni);
			obj.setLogin(login);
			obj.setPassword(contra);
			obj.setCorreo(correo);
			obj.setFecnac(fech);
			obj.setDireccion(dir);


			UsuarioModel c = new UsuarioModel();
			
			int r = c.actualizaUsuario(obj);
			
			if (r > 0) {
				
				mensaje("Se actualizó correctamente");
				
				idUsuarioSeleccionado = -1;
				
				listar();
				
				limpiarTxt();
			}else {
				mensaje("Error al actualizar");
			}
			
		}
	}
	public void buscar()
	{
		int fila = table.getSelectedRow();

        idUsuarioSeleccionado = (Integer)table.getValueAt(fila, 0);
        String nom     = (String)table.getValueAt(fila, 1);
        String ape     = (String)table.getValueAt(fila, 2);
        String dni     = (String)table.getValueAt(fila, 3);
        String login   = (String)table.getValueAt(fila, 4);
        String contra  = (String)table.getValueAt(fila, 5);
        String correo  = (String)table.getValueAt(fila, 6);
        String fech    = (String)table.getValueAt(fila, 7);
        String dir     = (String)table.getValueAt(fila, 8);

        txtNombre.setText(nom);
        txtApellido.setText(ape);
        txtDNI.setText(dni);
        txtUsuario.setText(login);
        txtContraseña.setText(contra);
        txtCorreo.setText(correo);
        txtFechNac.setText(fech);
        txtDireccion.setText(dir);

	}
	public void listar()
	{
		UsuarioModel c = new UsuarioModel();
        List<Usuario> lista = c.listaUsuario();

        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);

        for (Usuario ru : lista) {
            Object[] f = {ru.getIdUsuario(),
                          ru.getNombre(),
                          ru.getApellido(),
                          ru.getDni(),
                          ru.getLogin(),
                          ru.getPassword(),
                          ru.getCorreo(),
                          ru.getFecnac(),
                          ru.getDireccion()};
            dtm.addRow(f);
        }
	}
	
	public void mensaje(String ms){
        JOptionPane.showMessageDialog(this, ms);
    }
	
	void limpiarTxt() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDNI.setText("");
        txtUsuario.setText("");
        txtContraseña.setText("");
        txtCorreo.setText("");
        txtFechNac.setText("");
        txtDireccion.setText("");

        txtNombre.requestFocus();
        txtApellido.requestFocus();
        txtDNI.requestFocus();
        txtUsuario.requestFocus();
        txtContraseña.requestFocus();
        txtCorreo.requestFocus();
        txtFechNac.requestFocus();
        txtDireccion.requestFocus();
    }
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtDNI) {
			keyTypedTxtDNI(e);
		}
	}
	//permite cierta cantidad de caracteres
	@SuppressWarnings("unused")
	protected void keyTypedTxtDNI(KeyEvent e) {
		char  car = e.getKeyChar();

        if( txtDNI.getText().length()> 7){
            getToolkit().beep();
            e.consume();
		
	}
}
	
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		try {
			obj=new Excel();
			Excel.crearArchivoExcel(table);
			
		}catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
