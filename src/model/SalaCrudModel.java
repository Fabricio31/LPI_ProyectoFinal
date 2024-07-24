
 package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entidad.SalaCrud;
import util.MySqlDBConexion;



public class SalaCrudModel {
	public int insertaSala(SalaCrud c) {
		int salida = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			//con = new MySqlDBConexion().getConexion();
			con = MySqlDBConexion.getConexion();
			String sql = "insert into sala values(null,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, c.getNumero());
			pstm.setInt(2, c.getPiso());
			pstm.setString(3, c.getCapacidad());
			pstm.setString(4, c.getRecursos());
			pstm.setByte(5, c.getEstado());
			salida = pstm.executeUpdate();
			System.out.println("SQL-->" + pstm);
			System.out.println("insertados : " + salida);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	
	}//FINAL INSERTA
	public List<SalaCrud> listaSalaCrud()  { //Este metodo tiene el select y nos devuelve arraylist de campeonato
		ArrayList<SalaCrud> data = new ArrayList<SalaCrud>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			String sql ="select * from sala";
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			
			//En rs se trae los datos de la BD segun el SQL
			rs = pstm.executeQuery();
			
			//Se pasa la data del rs al ArrayList(data)
			SalaCrud c = null;
			while(rs.next()){
				c = new SalaCrud();
				// Se colocan los campos de la base de datos
				c.setSala(rs.getInt("idSala"));
			 	c.setNumero(rs.getString("numero"));
			 	c.setPiso(rs.getInt("piso"));
			 	c.setCapacidad(rs.getString("capacidad"));
			 	c.setRecursos(rs.getString("recursos"));
			 	c.setEstado(rs.getByte("estado"));
				
				data.add(c);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	public int actualizaSalaCrud(SalaCrud c) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "update sala set numero=?, piso=?, capacidad=?, recursos=?, estado=? where idSala=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, c.getNumero());
			pstm.setInt(2, c.getPiso());
			pstm.setString(3, c.getCapacidad());
			pstm.setString(4, c.getRecursos());
			pstm.setByte(5, c.getEstado());
			pstm.setInt(6, c.getSala());

			System.out.println(pstm);
			actualizados = pstm.executeUpdate();
			System.out.println("actualizados :  " + actualizados);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actualizados;
	}	
	public int eliminaSalaCrud(int idSala) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql ="delete from sala where idSala=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idSala);
			
			eliminados = pstm.executeUpdate();
			System.out.println("eliminados :  " + eliminados);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return eliminados;
	}	
}//FINAL
