package inicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entidad.Usuario;
import model.UsuarioModel;
import util.DatosGlobales;


@SuppressWarnings("serial")
public class FrmLogin extends JDialog implements ActionListener{
	
	public JLabel lblLogin, lblClave;
	public JTextField txtLogin;
	public JPasswordField txtClave;
	public JButton btnEnviar, btnLimpiar;
	private UsuarioModel model = new UsuarioModel();
	public FrmPrincipal frm;
	
	
	public FrmLogin(FrmPrincipal frm) {
			this.frm = frm;
			
			this.setLayout(null);
			this.setSize(340, 180);
			this.setLocationRelativeTo(null);
		    this.setTitle("Sistema de gestión de biblioteca");
		    
			lblLogin = new JLabel("Login");
			lblLogin.setBounds(50,15,100,25);
			add(lblLogin);
			
			txtLogin = new JTextField("luis");
			txtLogin.setBounds(160,15,100,25);
			txtLogin.addActionListener(this);
			add(txtLogin);
			
			lblClave = new JLabel("Clave");
			lblClave.setBounds(50,40,100,25);
			add(lblClave);
			
			txtClave = new JPasswordField("luis");
			txtClave.addActionListener(this);
			txtClave.setBounds(160,40,100,25);
			add(txtClave);
			
			btnEnviar = new JButton("Enviar");
			btnEnviar.addActionListener(this);
			btnEnviar.setBounds(50,75,100,25);
			add(btnEnviar);
			
			btnLimpiar = new JButton("Limpiar");
			btnLimpiar.addActionListener(this);
			btnLimpiar.setBounds(160,75,100,25);
			add(btnLimpiar);
			
			
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnEnviar || e.getSource()== txtLogin || e.getSource() == txtClave){
				
				String login = txtLogin.getText().trim();
				String clave = new String(txtClave.getPassword());
				
				Usuario bean =  model.valida(login, clave);
				
				
				if(bean!= null){
					DatosGlobales.ID_USUARIO = bean.getIdUsuario();
					frm.setVisible(true);
					this.setVisible(false);
					frm.muestraOpciones();
				}else{
					JOptionPane.showMessageDialog(this, "Usuario no valido");
				}
				
			}
			if(e.getSource() == btnLimpiar){
				txtLogin.setText("");
				txtClave.setText("");
				txtLogin.requestFocus();
			}
		
	}
	

	public void windowDeactivated(WindowEvent e) {}

	

}





