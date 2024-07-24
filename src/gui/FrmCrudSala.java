package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entidad.SalaCrud;
import model.SalaCrudModel;
import util.Validaciones;

public class FrmCrudSala extends JInternalFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNumero;
	private JTextField txtCapacidad;
	private JTextField txtRecursos;
	private JTable table;
	// Agregar
	int idSeleccionado = -1;
	private JButton btnRegistar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	// Agregar cbo ,añadir tipo de dato
	private JComboBox<String> cboPiso;
	private JComboBox<String> cboEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudSala frame = new FrmCrudSala();
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
	public FrmCrudSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Actualiza Sala");
		setBounds(100, 100, 805, 733);
		getContentPane().setLayout(null);

		JLabel lblMantenimientoSala = new JLabel("Mantenimiento Sala\r\n");
		lblMantenimientoSala.setFont(new Font("Arial", Font.BOLD, 23));
		lblMantenimientoSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoSala.setBounds(254, 13, 298, 91);
		getContentPane().add(lblMantenimientoSala);

		JLabel lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setBackground(Color.WHITE);
		lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumero.setBounds(72, 110, 96, 16);
		getContentPane().add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setBounds(220, 107, 158, 22);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCapacidad.setBounds(99, 200, 87, 16);
		getContentPane().add(lblCapacidad);

		txtCapacidad = new JTextField();
		txtCapacidad.setBounds(220, 197, 158, 22);
		getContentPane().add(txtCapacidad);
		txtCapacidad.setColumns(10);

		JLabel lblRecursos = new JLabel("Recursos");
		lblRecursos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRecursos.setBounds(130, 252, 56, 16);
		getContentPane().add(lblRecursos);

		txtRecursos = new JTextField();
		txtRecursos.setBounds(220, 249, 158, 22);
		getContentPane().add(txtRecursos);
		txtRecursos.setColumns(10);

		btnRegistar = new JButton("Registar");
		btnRegistar.addActionListener(this);
		btnRegistar.setBounds(556, 131, 118, 40);
		getContentPane().add(btnRegistar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(556, 206, 118, 44);
		getContentPane().add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(556, 274, 118, 40);
		getContentPane().add(btnEliminar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(FrmCrudAlumno.class.getResource("/util/xlsx.png")));
		btnNewButton.setBounds(588, 352, 56, 50);
		getContentPane().add(btnNewButton);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 439, 696, 220);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "idSala", "Número", "Piso", "Capacidad", "Recursos", "Estado" }) {/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
				}});
		scrollPane.setViewportView(table);
		// Tabla
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		header.setResizingAllowed(false);
		// Llamar a lista
		lista();

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPiso.setBounds(112, 158, 56, 16);
		getContentPane().add(lblPiso);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(112, 298, 56, 16);
		getContentPane().add(lblEstado);

		// JComboBox cboPiso = new JComboBox();
		cboPiso = new JComboBox<String>();
		cboPiso.setModel(
				new DefaultComboBoxModel(new String[] { "[Seleccione un piso]", "1", "2", "3", "4", "5", "6" }));
		cboPiso.setBounds(220, 155, 158, 22);
		getContentPane().add(cboPiso);

		// JComboBox cboEstado = new JComboBox();
		cboEstado = new JComboBox<String>();
		cboEstado
				.setModel(new DefaultComboBoxModel(new String[] { "[Seleccione un estado]", "Ocupado", "Disponible" }));
		cboEstado.setBounds(220, 295, 158, 22);
		getContentPane().add(cboEstado);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEliminar) {
			handle_btnEliminar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			handle_btnActualizar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnRegistar) {
			handle_btnRegistar_actionPerformed(arg0);
		}
	}

	protected void handle_btnRegistar_actionPerformed(ActionEvent arg0) {
		// BTN REGISTRAR
		inserta();

	}

	protected void handle_btnActualizar_actionPerformed(ActionEvent arg0) {
		// Programar boton ACTUALIZAR
		actualiza();
	}

	protected void handle_btnEliminar_actionPerformed(ActionEvent arg0) {
		// BTN ELIMINAR
		elimina();
	}

	// Agregando MouseClicked
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			handle_table_mouseClicked(arg0);
		}
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	protected void handle_table_mouseClicked(MouseEvent arg0) {
		// BUSCA
		busca();

	}

	// Creando los 5 Metodos
	// LISTA CORRECTO
	public void lista() {
		SalaCrudModel m = new SalaCrudModel();
		List<SalaCrud> lista = m.listaSalaCrud();
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

		dtm.setRowCount(0);

		for (SalaCrud x : lista) {
			Object[] f = { x.getSala(), x.getNumero(), x.getPiso(), x.getCapacidad(), x.getRecursos(),
					getEstado(x.getEstado()) };// AR
			dtm.addRow(f);
		}
	}

	// Metodo
	public String getEstado(int est) {
		if (est == 1) {
			return "Ocupado";
		} else if (est == 2) {
			return "Disponible";
		} else {
			return "Ninguno";
		}
	}

	// INSERTA ERROR
	public void inserta() {
		String num = txtNumero.getText();
		// int pospis = cboPiso.getSelectedIndex();
		String pis = cboPiso.getSelectedItem().toString();
		String cap = txtCapacidad.getText();
		String rec = txtRecursos.getText();
		int posest = cboEstado.getSelectedIndex();
		// String est= cboEstado.getSelectedItem().toString();

		if (!num.matches(Validaciones.TEXTOyNUM)) {
			mensaje("El número tiene como formato  número y texto");
		} else if (cboPiso.getSelectedIndex() == 0) {
			mensaje("Seleccione un piso");
		} else if (!cap.matches(Validaciones.TEXTOyNUM)) {
			mensaje("Capacidad tiene como formato número y texto");
			return;
		} else if (!rec.matches(Validaciones.TEXTO)) {
			mensaje("Recursos tiene como formato texto");
			return;
		} else if (cboEstado.getSelectedIndex() == 0) {
			mensaje("Selecciona estado de la sala");// HASTA ACA FUNCIONA VALIDACIONES PERO NO BTN
		} else {
			SalaCrud obj = new SalaCrud();
			obj.setNumero(num);
			obj.setPiso(Integer.parseInt(pis));
			obj.setCapacidad(cap);
			obj.setRecursos(rec);
			// obj.setEstado(est);
			obj.setEstado((byte) posest);
			SalaCrudModel m = new SalaCrudModel();

			int s = m.insertaSala(obj);
			if (s > 0) {
				mensaje("Se inserto correctamente");
				limpiarCajasTexto();
				lista();
			} else {
				mensaje("Registro erroneo");
			}
		}
	}

	// ACTUALIZA CORRECTO
	public void actualiza() {
		if (idSeleccionado == -1) {
			mensaje("Selecciona una sala");
		} else {
			String num = txtNumero.getText();
			int pospis = cboPiso.getSelectedIndex();
			String pis = cboPiso.getSelectedItem().toString();
			String cap = txtCapacidad.getText();
			String rec = txtRecursos.getText();
			int posest = cboEstado.getSelectedIndex();
			// String est =cboEstado.getSelectedItem().toString();
			if (!num.matches(Validaciones.TEXTOyNUM)) {
				mensaje("El numero es de 2 a 20 caracteres Alfanumerucos");
				return;
			}
			if (pospis == 0) {
				mensaje("Selecciona un piso");
				return;
			}
			if (!cap.matches(Validaciones.TEXTOyNUM)) {
				mensaje("La capacidad de sala es de 2 a 20 caracteres");
				return;
			}
			if (!rec.matches(Validaciones.TEXTOyNUM)) {
				mensaje("Los recursos de la sala tienen de 2 a 20 caracteres");
				return;
			}
			if (posest == 0) {
				mensaje("Seleccione estado de la sala");
				return;
			}
			SalaCrud obj = new SalaCrud();
			obj.setSala(idSeleccionado);
			obj.setNumero(num);
			obj.setPiso(Integer.parseInt(pis));
			obj.setCapacidad(cap);
			obj.setRecursos(rec);
			// obj.setEstado((posest)); //?
			obj.setEstado((byte) posest);

			SalaCrudModel m = new SalaCrudModel();
			int s = m.actualizaSalaCrud(obj);

			if (s > 0) {
				mensaje("Actualizacion exitosa");
				limpiarCajasTexto();
				lista();
			} else {
				mensaje("Actualizacion erronea");
			}
		}
	}

	// ELIMINA FALTA CBO
	public void elimina() {
		if (idSeleccionado == -1) {
			mensaje("Selecciona un registro");
		} else {
			SalaCrudModel model = new SalaCrudModel();
			int s = model.eliminaSalaCrud(idSeleccionado);
			if (s > 0) {
				mensaje("Se elimino el registro");
				lista();// Agregado
				limpiarCajasTexto();// Agregado
				idSeleccionado = -1;// Agregado
			} else {
				mensaje("Error al eliminar registro");
			}
		}
	}

	// BUSCA FALTA CBO
	public void busca() {
		int fila = table.getSelectedRow();
		idSeleccionado = (Integer) table.getValueAt(fila, 0);
		String num = (String) table.getValueAt(fila, 1);
		int pis = (Integer) table.getValueAt(fila, 2);
		String cap = (String) table.getValueAt(fila, 3);
		String rec = (String) table.getValueAt(fila, 4);
		String est = (String) table.getValueAt(fila, 5);

		if (est.equals("Ocupado")) {
			cboEstado.setSelectedIndex(1);
		} else if (est.equals("Libre")) {
			cboEstado.setSelectedIndex(2);
		} else {
			cboEstado.setSelectedIndex(0);
		}

		txtNumero.setText(num);
		cboPiso.setSelectedIndex(pis);
		txtCapacidad.setText(cap);
		txtRecursos.setText(rec);
		// cboEstado.setSelectedIndex(est);

	}

	// MENSAJE
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	// LIMPIAR
	void limpiarCajasTexto() {
		txtNumero.setText("");
		txtCapacidad.setText("");
		txtRecursos.setText("");
		txtNumero.requestFocus();
		// cboPiso.setToolTipText("");//Probando
		// cboEstado.setToolTipText("");//Probando
		cboPiso.setSelectedIndex(0);
		cboEstado.setSelectedIndex(0);

	}
	// Mensaje de prueba 15-06-21

}
