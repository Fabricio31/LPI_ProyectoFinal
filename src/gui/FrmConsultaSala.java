package gui;

import java.awt.Color;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import entidad.Sala;
import model.ConsultaSalaModel;
import util.Excel;
import util.Validaciones;

public class FrmConsultaSala extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JTable table;
	private JButton btnFiltrar;
	Excel obj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
			FlatDarkLaf.setup();
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaSala frame = new FrmConsultaSala();
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
	public FrmConsultaSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Sala ");
		setBounds(100, 100, 665, 393);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consulta de Sala por piso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(30, 30, 602, 36);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPisoDesde = new JLabel("Piso   (Desde)");
		lblPisoDesde.setHorizontalAlignment(SwingConstants.CENTER);
		lblPisoDesde.setBounds(10, 93, 113, 25);
		getContentPane().add(lblPisoDesde);
		
		txtInicio = new JTextField();
		txtInicio.setBounds(133, 95, 96, 19);
		getContentPane().add(txtInicio);
		txtInicio.setColumns(10);
		
		txtFin = new JTextField();
		txtFin.setBounds(319, 95, 96, 19);
		getContentPane().add(txtFin);
		txtFin.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("(Hasta)");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(264, 99, 45, 13);
		getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 146, 587, 177);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"idSala", "numero", "piso", "capacidad", "recursos", "estado"
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
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(439, 95, 85, 21);
		getContentPane().add(btnFiltrar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.setBounds(550, 68, 56, 50);
		getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.ORANGE);
		separator.setBounds(46, 115, 85, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.ORANGE);
		separator_1.setBounds(307, 115, 70, 2);
		getContentPane().add(separator_1);


	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			do_btnFiltrar_actionPerformed(e);
		}
	}
	protected void do_btnFiltrar_actionPerformed(ActionEvent e) {
		//btnFiltrar
		String pisoIni = txtInicio.getText();
		String pisoFin = txtFin.getText();
		
		if(!pisoIni.matches(Validaciones.PISO)) {
			mensaje("El piso tiene como valor minimo 1 y maximo 6");
			return;	
		}
		
		
		if(!pisoFin.matches(Validaciones.PISO)) {
			mensaje("El piso tiene como valor minimo 1 y maximo 6");
			return;	
		}
		
		int pisoInii = Integer.parseInt(pisoIni);
		int pisoFinn = Integer.parseInt(pisoFin);
		
		if(pisoInii>pisoFinn) {
			mensaje("El piso final debe ser mayor al piso inicial");
			return;	
		}
		ConsultaSalaModel m = new ConsultaSalaModel();
		List<Sala> lista = m.listaxPiso(pisoInii, pisoFinn);
		
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0); //togglebreakpoint
		for (Sala x : lista) {
			Object[]f = {x.getSala(),x.getNumero(),x.getPiso(),x.getCapacidad(),x.getRecursos(),x.getEstado()};
			dtm.addRow(f);
		}
	}
	//MENSAJE
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
