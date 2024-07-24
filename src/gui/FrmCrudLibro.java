package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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

import entidad.LibroCrud;
import model.LibroCrudModel;
import util.Excel;
import util.Validaciones;

public class FrmCrudLibro extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
	private JTextField txtAño;
	private JTextField txtCategoria;
	private JTextField txtSerie;
	private JTable table;
	 int idSeleccionado=-1;
	 
	 Excel obj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudLibro frame = new FrmCrudLibro();
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
	public FrmCrudLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento Libro");
		setBounds(100, 100, 890, 502);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mantenimiento Libro");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(337, 21, 199, 23);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(35, 112, 64, 14);
		getContentPane().add(lblTitulo);
		
		JLabel lblAño = new JLabel("A\u00F1o");
		lblAño.setBounds(35, 169, 64, 14);
		getContentPane().add(lblAño);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(35, 224, 89, 14);
		getContentPane().add(lblCategoria);
		
		JLabel lblSerie = new JLabel("Serie");
		lblSerie.setBounds(35, 279, 89, 14);
		getContentPane().add(lblSerie);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(104, 109, 136, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtAño = new JTextField();
		txtAño.setBounds(104, 166, 136, 20);
		getContentPane().add(txtAño);
		txtAño.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnRegistrar_actionPerformed(e);
			}
		});
		btnRegistrar.setBounds(10, 356, 89, 47);
		getContentPane().add(btnRegistrar);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(104, 221, 136, 20);
		getContentPane().add(txtCategoria);
		txtCategoria.setColumns(10);
		
		txtSerie = new JTextField();
		txtSerie.setBounds(104, 276, 136, 20);
		getContentPane().add(txtSerie);
		txtSerie.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnActualizar_actionPerformed(e);
			}
		});
		btnActualizar.setBounds(119, 356, 89, 47);
		getContentPane().add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnEliminar_actionPerformed(e);
			}
		});
		btnEliminar.setBounds(228, 356, 89, 47);
		getContentPane().add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 83, 523, 378);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.setBounds(123, 411, 56, 50);
		getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_table_mouseClicked(e);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Titulo", "A\u00F1o", "Categoria", "Serie"
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
		lista();
		
		

	}
	
	
	public void lista() 
	{
		LibroCrudModel m=new LibroCrudModel();
		List<LibroCrud> lista = m.listarLibro();
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		
		dtm.setRowCount(0);
		
		for(LibroCrud x : lista) 
		{
			Object[] f = {x.getIdLibro(),x.getTitulo(),x.getAnio(),x.getCategoria(),x.getSerie()};//AR
			dtm.addRow(f);
		}
	}
	
	public void inserta() {

		String titulo = txtTitulo.getText().trim(); 
		String año=txtAño.getText().trim();
		String categoria=txtCategoria.getText().trim();
		String serie=txtSerie.getText().trim();
		
		
		if(!titulo.matches(Validaciones.NOMBRE)){
			mensaje("Titulo es de 2 a 20 caracteres");
		}
		if(!año.matches(Validaciones.ANNO)) {
			mensaje("Año es de 4 digitos");
		}
		if(!categoria.matches(Validaciones.TEXTO)) {
			mensaje("Formato de Categoria Invalido");
		}
		if(!serie.matches(Validaciones.SERIE)) {
			mensaje("Solo se permite texto");
		}else {
		
		LibroCrud obj=new LibroCrud();
		obj.setIdLibro(idSeleccionado);
		obj.setTitulo(titulo);
		obj.setAnio(año);
		obj.setCategoria(categoria);
		obj.setSerie(serie);
		
		LibroCrudModel m=new LibroCrudModel();
		int s=m.insertaLibro(obj);
		if(s>0) {
			mensaje("Se inserto correctamente");
			limpiarCajasTexto();
			lista();
		}else {
			mensaje("Registro erroneo");
		}
		}
	} 
	public void actualiza() {
		if(idSeleccionado ==-1) {
			mensaje("Selecciona un Proveedor");
		}else {
			String titulo = txtTitulo.getText().trim(); 
			String año=txtAño.getText().trim();
			String categoria=txtCategoria.getText().trim();
			String serie=txtSerie.getText().trim();
			
			
			if(!titulo.matches(Validaciones.NOMBRE)){
				mensaje("Titulo es de 2 a 20 caracteres");
			}
			if(!año.matches(Validaciones.ANNO)) {
				mensaje("Año es de 4 digitos");
			}
			if(!categoria.matches(Validaciones.TEXTO)) {
				mensaje("Formato de Categoria Invalido");
			}
			if(!serie.matches(Validaciones.SERIE)) {
				mensaje("Solo se permite texto");
			}else {
			
			LibroCrud obj=new LibroCrud();
			obj.setIdLibro(idSeleccionado);
			obj.setTitulo(titulo);
			obj.setAnio(año);
			obj.setCategoria(categoria);
			obj.setSerie(serie);
			
			LibroCrudModel m=new LibroCrudModel();
			int s=m.actualizar(obj);
			if(s>0) {
				mensaje("Se actualizo correctamente");
				limpiarCajasTexto();
				lista();
			}else {
				mensaje("Registro erroneo");
			}
			}
			}	
	}
	
	public void busca() {
		int fila = table.getSelectedRow();
		idSeleccionado = (int) table.getValueAt(fila,0);
		String titulo=(String)table.getValueAt(fila, 1);
		String año=(String)table.getValueAt(fila, 2);
		String categoria=(String)table.getValueAt(fila, 3);
		String serie=(String)table.getValueAt(fila, 4);

		txtTitulo.setText(titulo);
		txtAño.setText(año);
		txtCategoria.setText(categoria);
		txtSerie.setText(serie);
	
	}
	
	public void eliminar() {
		if(idSeleccionado ==-1) {
			mensaje("Selecciona un registro");
		}else {
			LibroCrudModel m=new LibroCrudModel();
			int s=m.eliminar(idSeleccionado);
			if(s>0) {
				mensaje("Se elimino el registro");
				lista();//Agregado
				limpiarCajasTexto();//Agregado
				idSeleccionado =-1;//Agregado
			}else if(s==-2){
				mensaje("El libro ya esta relacionado");
			}else {
				mensaje("Error al eliminar registro");
			}
		}
	}
	
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	
	
	
	void limpiarCajasTexto() {
		txtTitulo.setText("");
		txtAño.setText("");
		txtCategoria.setText("");
		txtSerie.setText("");
		txtTitulo.requestFocus();
		
}
	protected void do_btnActualizar_actionPerformed(ActionEvent e) {
	actualiza();
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		eliminar();
	}
	protected void do_table_mouseClicked(MouseEvent e) {
		busca();
	}
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		inserta();
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		obj=new Excel();
		try {
			Excel.crearArchivoExcel(table);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
	}
}
