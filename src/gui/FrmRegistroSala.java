package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidad.Sala;
import model.SalaModel;
import util.Validaciones;

public class FrmRegistroSala extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCapacidad;
	private JTextField txtRecursos;
	private JTextField txtnumero;
	private JButton btnRegistrar;
	private JComboBox<String> cboEstado;
	private JComboBox<String> cboPiso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroSala frame = new FrmRegistroSala();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmRegistroSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Sala");
		setBounds(100, 100, 450, 315);
		getContentPane().setLayout(null);

		JLabel lblIngresarsala = new JLabel("Ingreso de Sala");
		lblIngresarsala.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIngresarsala.setBounds(146, 11, 141, 22);
		getContentPane().add(lblIngresarsala);

		JLabel lblnumero = new JLabel("N\u00FAmero");
		lblnumero.setBounds(93, 65, 46, 14);
		getContentPane().add(lblnumero);

		txtnumero = new JTextField();
		txtnumero.setBounds(176, 62, 117, 20);
		getContentPane().add(txtnumero);
		txtnumero.setColumns(10);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(93, 96, 46, 14);
		getContentPane().add(lblPiso);

		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(93, 124, 60, 14);
		getContentPane().add(lblCapacidad);

		txtCapacidad = new JTextField();
		txtCapacidad.setColumns(10);
		txtCapacidad.setBounds(176, 121, 175, 20);
		getContentPane().add(txtCapacidad);

		JLabel lblRecursos = new JLabel("Recursos");
		lblRecursos.setBounds(93, 152, 60, 14);
		getContentPane().add(lblRecursos);

		txtRecursos = new JTextField();
		txtRecursos.setColumns(10);
		txtRecursos.setBounds(176, 149, 175, 20);
		getContentPane().add(txtRecursos);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(93, 180, 60, 14);
		getContentPane().add(lblEstado);

		cboPiso = new JComboBox<String>();
		cboPiso.setModel(new DefaultComboBoxModel(new String[] { "[ Seleccione ]", "1", "2", "3", "4", "5", "6" }));
		cboPiso.setBounds(176, 93, 117, 22);
		getContentPane().add(cboPiso);

		cboEstado = new JComboBox<String>();
		cboEstado.setModel(new DefaultComboBoxModel(new String[] { "[ Seleccione ]", "Activo", "Inactivo" }));
		cboEstado.setBounds(176, 180, 117, 22);
		getContentPane().add(cboEstado);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(172, 227, 89, 23);
		getContentPane().add(btnRegistrar);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			handle_btnRegistrar_actionPerformed(e);
		}
	}

	protected void handle_btnRegistrar_actionPerformed(ActionEvent e) {
		String num = txtnumero.getText();
		int pospiso = cboPiso.getSelectedIndex();
		String piso = cboPiso.getSelectedItem().toString();
		String cap = txtCapacidad.getText();
		String rec = txtRecursos.getText();
		int posest = cboEstado.getSelectedIndex();
		@SuppressWarnings("unused")
		String est = cboEstado.getSelectedItem().toString();

		if (!num.matches(Validaciones.TEXTOyNUM)) {
			mensaje("Formato de Numero Invalido");
		} else if (pospiso == 0) {
			mensaje("Seleccione piso");
		} else if (!cap.matches(Validaciones.TEXTO)) {
			mensaje("Formato de Capacidad Invalido");
		} else if (!rec.matches(Validaciones.TEXTO)) {
			mensaje("Formato de Recursos Invalido");
		} else if (posest == 0) {
			mensaje("Seleccione Estado");
		}else {
			Sala objS=new Sala();
			objS.setNumero(num);
			objS.setPiso(Integer.parseInt(piso));
			objS.setCapacidad(cap);
			objS.setRecursos(rec);
			objS.setEstado((byte)posest);
			
			SalaModel model=new SalaModel();
			int salida=model.insertaSala(objS);
			if(salida> 0) {
				mensaje("Registro de Sala Exitoso");
				txtnumero.setText("");
				txtCapacidad.setText("");
				txtRecursos.setText("");
				cboEstado.setSelectedIndex(0);
				cboPiso.setSelectedIndex(0);
			}else {
				mensaje("Hubo un error al registrar Sala");
			}
			
		}

	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
}
