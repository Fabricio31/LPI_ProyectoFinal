package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Sala;
import util.MySqlDBConexion;

public class ConsultaSalaModel {

	public List<Sala> listaxPiso(int pisoIni, int pisoFin) {
		ArrayList<Sala> lista = new ArrayList<Sala>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "select * from sala where piso between ? and ?	";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pisoIni);
			pstm.setInt(2, pisoFin);
			System.out.println("SQL -->" + pstm);

			rs = pstm.executeQuery();
			Sala obj = null;
			while (rs.next()) {
				obj = new Sala();
				obj.setSala(rs.getInt(1));
				obj.setNumero(rs.getString(2));
				obj.setPiso(rs.getInt(3));
				obj.setCapacidad(rs.getString(4));
				obj.setRecursos(rs.getString(5));
				obj.setEstado(rs.getByte(6));
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
		public List<Sala> listarSala(){
			ArrayList<Sala> lista = new ArrayList<Sala>();
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				
				conn = MySqlDBConexion.getConexion();
				String sql = "select * from sala";
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
					
				Sala objSala = null;
				while(rs.next()) {
					
					objSala = new Sala();
					objSala.setSala(rs.getInt(1));
					objSala.setNumero(rs.getString(2));
					objSala.setPiso(rs.getInt(3));
					objSala.setCapacidad(rs.getString(4));
					objSala.setRecursos(rs.getString(5));
					objSala.setEstado(rs.getByte(6));
					
					lista.add(objSala);
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
