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

import entidad.AutorCrud;
import model.ConsultaAutorModel;
import util.Excel;
import util.Validaciones;

public class FrmConsultaAutor extends JInternalFrame {

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
					FrmConsultaAutor frame = new FrmConsultaAutor();
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
	public FrmConsultaAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Autor");
		setBounds(100, 100, 782, 437);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Consultar Autor");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(295, 27, 176, 25);
		getContentPane().add(lblNewLabel);
		
		lblNombre = new JLabel("Grado :");
		lblNombre.setBounds(10, 87, 66, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(86, 84, 130, 20);
		getContentPane().add(txtNombre);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.setBounds(335, 59, 56, 50);
		getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 746, 276);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setShowHorizontalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombres", "Apellidos", "Fecha de Nacimiento", "Fecha de Creacion", "Nacionalidad", "Grado"
			}
		) {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}});
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(3).setPreferredWidth(114);
		table.getColumnModel().getColumn(4).setPreferredWidth(102);
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
			mensaje("Solo texto");
		}else {
			ConsultaAutorModel m=new ConsultaAutorModel();
			List<AutorCrud> lista=m.listaxgrado(nombre);
			if(lista.size()==0) {
				mensaje("No hay datos del Autor");
			}
			DefaultTableModel dtm=(DefaultTableModel)table.getModel();
			dtm.setRowCount(0); 
			for(AutorCrud x : lista) 
			{
				Object[] f = {x.getIdAutor(),x.getNombres(),x.getApellidos(),x.getFechanacimiento(),x.getFecharegistro(),x.getNacionalidad(),x.getGrado()};//AR
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
