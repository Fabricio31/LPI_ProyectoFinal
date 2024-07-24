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

import entidad.ProveedorCrud;
import model.ConsultaProveedorModel;
import util.Excel;
import util.Validaciones;

public class FrmConsultaProveedor extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaProveedor frame = new FrmConsultaProveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel lblNewLabel;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnConsultar;
	Excel obj;
	/**
	 * Create the frame.
	 */
	public FrmConsultaProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Proveedor");
		setBounds(100, 100, 782, 437);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Consultar Proveedor");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(274, 27, 217, 25);
		getContentPane().add(lblNewLabel);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(10, 87, 58, 14);
		getContentPane().add(lblNombre);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.setBounds(325, 59, 56, 50);
		getContentPane().add(btnNewButton);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(86, 84, 130, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 746, 276);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 10));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID", "Nombres", "Apellidos", "Dni", "Direccion", "Telefono", "Correo", "Pais"
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
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnConsultar_actionPerformed(e);
			}
		});
		btnConsultar.setBounds(226, 83, 89, 23);
		getContentPane().add(btnConsultar);


	}

	protected void do_btnConsultar_actionPerformed(ActionEvent e) {
String nombre=txtNombre.getText().trim();
		
		if(!nombre.matches(Validaciones.TEXTO)){
			mensaje("Solo Texto");
		}else {
			ConsultaProveedorModel m=new ConsultaProveedorModel();
			List<ProveedorCrud> lista=m.listarxnombre(nombre);
			if(lista.size()==0) {
				mensaje("No hay datos del Proveedor");
			}
			DefaultTableModel dtm=(DefaultTableModel)table.getModel();
			dtm.setRowCount(0); //togglebreakpoint
			for (ProveedorCrud x : lista) {
				Object[]f = {x.getIdProveedor(),x.getNombre(),x.getApellido(),x.getDNI(),x.getDireccion(),x.getTelefono(),x.getCorreo(),x.getPais()};
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
