package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import entidad.Usuario;
import model.ConsultaUsuarioModel;
import util.Excel;
import util.Validaciones;

public class FrmConsultaUsuario extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JLabel lblDni;
	private JTextField txtDni;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnConsultar;
	Excel obj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaUsuario frame = new FrmConsultaUsuario();
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
	public FrmConsultaUsuario() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Usuario");
		setBounds(100, 100, 782, 437);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Consultar Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(282, 27, 201, 25);
		getContentPane().add(lblNewLabel);
		
		lblDni = new JLabel("Dni :");
		lblDni.setBounds(10, 87, 46, 14);
		getContentPane().add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(39, 84, 130, 20);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 746, 276);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.setBounds(317, 59, 56, 50);
		getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 10));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "Login", "Password", "Correo", "Fecha de Nacimiento", "Direccion"
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
		table.getColumnModel().getColumn(0).setPreferredWidth(39);
		table.getColumnModel().getColumn(5).setPreferredWidth(103);
		table.getColumnModel().getColumn(6).setPreferredWidth(125);
		table.getColumnModel().getColumn(7).setPreferredWidth(97);
		scrollPane.setViewportView(table);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(205, 83, 89, 23);
		getContentPane().add(btnConsultar);


	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(e);
		}
	}
	protected void actionPerformedBtnConsultar(ActionEvent e) {
		
		String dni=txtDni.getText().trim();
		
		if(!dni.matches(Validaciones.DNI)){
			mensaje("El Dni tiene 8 digitos");
		}else {
			ConsultaUsuarioModel m=new ConsultaUsuarioModel();
			List<Usuario> lista=m.listaxDni(dni);
			if(lista.size()==0) {
				mensaje("No hay datos del Usuario");
			}
			DefaultTableModel dtm=(DefaultTableModel)table.getModel();
			dtm.setRowCount(0); //togglebreakpoint
			for (Usuario x : lista) {
				Object[]f = {x.getIdUsuario(),x.getNombre(),x.getApellido(),x.getLogin(),x.getPassword(),x.getCorreo(),x.getFecnac(),x.getDireccion()};
				dtm.addRow(f);
			}
		}
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		try{
			obj=new Excel();
		Excel.crearArchivoExcel(table);
		}catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
