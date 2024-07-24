package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.LibroCrud;
import util.MySqlDBConexion;

public class ConsultaLibroModel {

	public List<LibroCrud> listaxAño(String año) {
		ArrayList<LibroCrud> lista = new ArrayList<LibroCrud>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "select * from libro where anio = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, año);
			System.out.println("SQL -->" + pstm);

			rs = pstm.executeQuery();
			LibroCrud obj = null;
			while (rs.next()) {
				obj = new LibroCrud();
				obj.setIdLibro(rs.getInt(1));
				obj.setTitulo(rs.getString(2));
				obj.setAnio(rs.getString(3));
				obj.setCategoria(rs.getString(4));
				obj.setSerie(rs.getString(5));
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
		public List<LibroCrud> listarLibro(){
			ArrayList<LibroCrud> lista = new ArrayList<LibroCrud>();
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				
				conn = MySqlDBConexion.getConexion();
				String sql = "select * from libro";
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
					
				LibroCrud obj = null;
				while(rs.next()) {
					
					obj = new LibroCrud();
					obj.setIdLibro(rs.getInt(1));
					obj.setTitulo(rs.getString(2));
					obj.setAnio(rs.getString(3));
					obj.setCategoria(rs.getString(4));
					obj.setSerie(rs.getString(5));
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
