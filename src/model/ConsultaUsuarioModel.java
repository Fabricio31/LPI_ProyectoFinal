package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Usuario;
import util.MySqlDBConexion;

public class ConsultaUsuarioModel {

	public List<Usuario> listaxDni(String dni) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "select * from usuario where dni = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, dni);
			System.out.println("SQL -->" + pstm);

			rs = pstm.executeQuery();
			Usuario obj = null;
			while (rs.next()) {
				obj = new Usuario();
				obj.setIdUsuario(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getString(4));
				obj.setLogin(rs.getString(5));
				obj.setPassword(rs.getString(6));
				obj.setCorreo(rs.getString(7));
				obj.setFecnac(rs.getString(8));
				obj.setDireccion(rs.getString(9));
				lista.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstm != null)pstm.close();
				if(conn != null)conn.close();
			} catch (Exception e2) {}
		}
		return lista;
	}
	//Metodo Listar Sala solo btn
		public List<Usuario> listarUsuario(){
			ArrayList<Usuario> lista = new ArrayList<Usuario>();
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				
				conn = MySqlDBConexion.getConexion();
				String sql = "select * from usuario";
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
					
				Usuario obj = null;
				while(rs.next()) {
					
					obj = new Usuario();
					obj.setIdUsuario(rs.getInt(1));
					obj.setNombre(rs.getString(2));
					obj.setApellido(rs.getString(3));
					obj.setDni(rs.getString(4));
					obj.setLogin(rs.getString(5));
					obj.setPassword(rs.getString(6));
					obj.setCorreo(rs.getString(7));
					obj.setFecnac(rs.getString(8));
					obj.setDireccion(rs.getString(9));
					lista.add(obj);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null)rs.close();
					if(pstm != null)pstm.close();
					if(conn != null)conn.close();
				} catch (Exception e2) {}
			}
			return lista;
		}
		
}
