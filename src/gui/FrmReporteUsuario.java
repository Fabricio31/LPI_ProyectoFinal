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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entidad.Usuario;
import model.ConsultaUsuarioModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GeneradorReporte;

public class FrmReporteUsuario extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblReporteUsuario;
	private JButton btnNewButton;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteUsuario frame = new FrmReporteUsuario();
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
	public FrmReporteUsuario() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte Usuario");
		setBounds(100, 100, 736, 397);
		getContentPane().setLayout(null);
		
		lblReporteUsuario = new JLabel("Reporte Usuario");
		lblReporteUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReporteUsuario.setBounds(271, 11, 177, 25);
		getContentPane().add(lblReporteUsuario);
		
		btnNewButton = new JButton("Filtrar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(312, 59, 89, 23);
		getContentPane().add(btnNewButton);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0,0));
		panel.setBounds(10, 97, 700, 259);
		getContentPane().add(panel);


	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		ConsultaUsuarioModel m = new ConsultaUsuarioModel();
		List<Usuario> lista = m.listarUsuario();
		//
		// 1 La data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

		// 2 El diseño del reporte
		String file = "reportUsuario.jasper";

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
