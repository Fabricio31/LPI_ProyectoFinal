package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entidad.Proveedor;
import model.ConsultaProveedorModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GeneradorReporte;


public class FrmReporteProveedor extends JInternalFrame {

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
					FrmReporteProveedor frame = new FrmReporteProveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel lblReporteProveedor;
	private JButton btnListar;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public FrmReporteProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte Proveedor");
		setBounds(100, 100, 736, 397);
		getContentPane().setLayout(null);
		
		lblReporteProveedor = new JLabel("Reporte Proveedor");
		lblReporteProveedor.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReporteProveedor.setBounds(264, 11, 200, 25);
		getContentPane().add(lblReporteProveedor);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnListar_actionPerformed(e);
			}
		});
		btnListar.setBounds(312, 59, 89, 23);
		getContentPane().add(btnListar);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0,0));
		panel.setBounds(10, 97, 700, 259);
		getContentPane().add(panel);



	}

	protected void do_btnListar_actionPerformed(ActionEvent e) {
		ConsultaProveedorModel m = new ConsultaProveedorModel();

		List<Proveedor> lista = m.listarProveedor();
		//
		// 1 La data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

		// 2 El diseño del reporte
		String file = "reporteProveedor.jasper";

		// 3 Se genera el reporte
		JasperPrint jasperPrint = GeneradorReporte.genera(file, dataSource, null);

		// 4 Se muestra en el visor
		JRViewer jRViewer = new JRViewer(jasperPrint);

		// 5 Se añade el visor al panel
		panel.removeAll();
		panel.add(jRViewer);
		panel.repaint();
		panel.revalidate();
	}
}
