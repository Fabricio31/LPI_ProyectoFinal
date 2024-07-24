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

import entidad.AutorCrud;
import model.ConsultaAutorModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GeneradorReporte;

public class FrmReporteAutor extends JInternalFrame {

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
					FrmReporteAutor frame = new FrmReporteAutor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel lblReporteAutor;
	private JButton btnListar;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public FrmReporteAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte Proveedor");
		setBounds(100, 100, 736, 397);
		getContentPane().setLayout(null);
		
		lblReporteAutor = new JLabel("Reporte Autor");
		lblReporteAutor.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReporteAutor.setBounds(284, 11, 151, 25);
		getContentPane().add(lblReporteAutor);
		
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
		getContentPane().add(panel);	}

	protected void do_btnListar_actionPerformed(ActionEvent e) {
		ConsultaAutorModel m = new ConsultaAutorModel();

		List<AutorCrud> lista = m.listarLibro();
		//
		// 1 La data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

		// 2 El diseño del reporte
		String file = "reporteAutor.jasper";

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
