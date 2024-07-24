package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import entidad.Libro;
import model.LibroModel;
import util.Validaciones;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistroLibro extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
	private JTextField txtAnio;
	private JTextField txtCategoria;
	private JTextField txtSerie;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistroLibro frame = new FrmRegistroLibro();
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
	public FrmRegistroLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Libro");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro Libro");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 34);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Titulo:");
		lblNewLabel_1.setBounds(77, 59, 83, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(138, 56, 126, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("A\u00F1o:");
		lblNewLabel_1_1.setBounds(77, 87, 83, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		txtAnio = new JTextField();
		txtAnio.setColumns(10);
		txtAnio.setBounds(138, 84, 126, 20);
		getContentPane().add(txtAnio);
		
		JLabel lblNewLabel_1_2 = new JLabel("Categoria:");
		lblNewLabel_1_2.setBounds(77, 115, 83, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		txtCategoria = new JTextField();
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(138, 112, 126, 20);
		getContentPane().add(txtCategoria);
		
		JLabel lblNewLabel_1_3 = new JLabel("Serie:");
		lblNewLabel_1_3.setBounds(77, 143, 83, 14);
		getContentPane().add(lblNewLabel_1_3);
		
		txtSerie = new JTextField();
		txtSerie.setColumns(10);
		txtSerie.setBounds(138, 140, 126, 20);
		getContentPane().add(txtSerie);
		
		btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(106, 198, 89, 23);
		getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(240, 198, 89, 23);
		getContentPane().add(btnNewButton_1);


	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton_1) {
			actionPerformedBtnNewButton_1(e);
		}
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		
		String tit = txtTitulo.getText();
		String anio = txtAnio.getText();
		String cat = txtCategoria.getText();
		String ser = txtSerie.getText();
		
		if (!tit.matches(Validaciones.TEXTOyNUM)) {
			mensaje("Formato del Titulo incorrecto");
		}else if (!anio.matches(Validaciones.ANNO)) {
			mensaje("Formato del Año incorrecto");
		}else if (!cat.matches(Validaciones.TEXTO)) {
			mensaje("Formato del Categoria incorrecto");
		}else if (!ser.matches(Validaciones.SERIE)) {
			mensaje("Formato del Serie incorrecto");
		}else {
			Libro obj = new Libro();
			obj.setTitulo(tit);
			obj.setAnio(anio);
			obj.setCategoria(cat);
			obj.setSerie(ser);
			
			LibroModel model = new LibroModel();
			int salida = model.insertaLibro(obj);
			if (salida > 0) {
				mensaje("Registro de Libro fue exitoso");
			} else {
				mensaje("Registro de Libro Fallido");
			}
			
		}
	
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	protected void actionPerformedBtnNewButton_1(ActionEvent e) {
		
		txtAnio.setText("");
		txtCategoria.setText("");
		txtSerie.setText("");
		txtTitulo.setText("");
		
		JOptionPane.showMessageDialog(this, "Limpio");
		
	}
}
