package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.AutorCrud;
import util.MySqlDBConexion;

public class ConsultaAutorModel {

	public List<AutorCrud> listaxgrado(String grado) {
		ArrayList<AutorCrud> lista = new ArrayList<AutorCrud>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "select * from autor where grado = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, grado);
			System.out.println("SQL -->" + pstm);

			rs = pstm.executeQuery();
			AutorCrud obj = null;
			while (rs.next()) {
				obj = new AutorCrud();
				obj.setIdAutor(rs.getInt(1));
				obj.setNombres(rs.getString(2));
				obj.setApellidos(rs.getString(3));
				obj.setFechanacimiento(rs.getDate(4));
				obj.setFecharegistro(rs.getString(5));
				obj.setNacionalidad(rs.getString(6));
				obj.setGrado(rs.getString(7));
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
		public List<AutorCrud> listarLibro(){
			ArrayList<AutorCrud> lista = new ArrayList<AutorCrud>();
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				conn = MySqlDBConexion.getConexion();

				String sql = "select * from autor";
				pstm = conn.prepareStatement(sql);
				System.out.println("SQL -->" + pstm);

				rs = pstm.executeQuery();
				AutorCrud obj = null;
				while (rs.next()) {
					obj = new AutorCrud();
					obj.setIdAutor(rs.getInt(1));
					obj.setNombres(rs.getString(2));
					obj.setApellidos(rs.getString(3));
					obj.setFechanacimiento(rs.getDate(4));
					obj.setFecharegistro(rs.getString(5));
					obj.setNacionalidad(rs.getString(6));
					obj.setGrado(rs.getString(7));
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
