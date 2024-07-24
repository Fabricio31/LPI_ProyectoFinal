package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import entidad.Sala;
import model.ConsultaSalaModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GeneradorReporte;

public class FrmReporteSala extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton btnFiltrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteSala frame = new FrmReporteSala();
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
	public FrmReporteSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte Sala");
		setBounds(100, 100, 862, 561);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Reporte Sala");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 37, 830, 40);
		getContentPane().add(lblNewLabel);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(388, 116, 85, 21);
		getContentPane().add(btnFiltrar);

		panel = new JPanel();
		panel.setLayout(new BorderLayout(0,0));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Reporte Sala", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 177, 830, 318);
		getContentPane().add(panel);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnFiltrar) {
			do_btnFiltrar_actionPerformed(arg0);
		}
	}

	protected void do_btnFiltrar_actionPerformed(ActionEvent arg0) {
		
		ConsultaSalaModel m = new ConsultaSalaModel();
		List<Sala> lista = m.listarSala();
		//
		// 1 La data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

		// 2 El diseño del reporte
		String file = "reporteSalaV2.jasper";

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
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
}
