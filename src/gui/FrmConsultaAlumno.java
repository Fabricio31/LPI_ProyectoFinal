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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import model.ConsultaAlumnoModel;
import util.Excel;

public class FrmConsultaAlumno extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIni;
	private JTextField txtFin;
	private JTable table;
	private JButton btnFiltrar;
	Excel obj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaAlumno frame = new FrmConsultaAlumno();
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
	public FrmConsultaAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Alumno");
		setBounds(100, 100, 877, 553);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consulta Alumno por Fechas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(260, 41, 365, 34);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDesde = new JLabel("Fecha Nacimiento (Desde)");
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDesde.setBounds(41, 131, 172, 23);
		getContentPane().add(lblDesde);
		
		txtIni = new JTextField();
		txtIni.setBounds(248, 134, 96, 19);
		getContentPane().add(txtIni);
		txtIni.setColumns(10);
		
		JLabel lblHasta = new JLabel("(Hasta)");
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHasta.setBounds(409, 134, 111, 17);
		getContentPane().add(lblHasta);
		
		txtFin = new JTextField();
		txtFin.setBounds(505, 134, 96, 19);
		getContentPane().add(txtFin);
		txtFin.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.setBounds(762, 104, 56, 50);
		getContentPane().add(btnNewButton);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(643, 133, 85, 21);
		getContentPane().add(btnFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 176, 793, 313);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id Alumno", "Nombres", "Apellidos", "DNI", "Correo", "Fecha Nacimiento", "Fecha Registro"
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


	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnFiltrar) {
			do_btnFiltrar_actionPerformed(arg0);
		}
	}
	protected void do_btnFiltrar_actionPerformed(ActionEvent arg0) {
		//st
		String fecIni =txtIni.getText();
		String fecFin =txtFin.getText();
		
		ConsultaAlumnoModel m = new ConsultaAlumnoModel();
		List<Alumno>lst =m.consultaDirectorxFechas(fecIni, fecFin);
		DefaultTableModel dtm =(DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		for(Alumno x:lst) 
		{
			Object[] f = {x.getIdAlumno(),x.getNombres(),x.getApellidos(),x.getDNI(),x.getCorreo(),x.getFechaNacimiento(),x.getFechaRegistro()};
			dtm.addRow(f);
		}
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
