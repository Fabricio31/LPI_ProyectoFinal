package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.AutorCrud;
import util.MySqlDBConexion;

public class AutorCrudModel {
		
	public List<AutorCrud> listarAutor(){
		ArrayList<AutorCrud> data=new ArrayList<AutorCrud>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		
		try {
			con = MySqlDBConexion.getConexion();
			String sql="select * from autor";
			pstm=con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			rs=pstm.executeQuery();
			
			AutorCrud a=null;
			while(rs.next()) {
				a=new AutorCrud();
				a.setIdAutor(rs.getInt("idAutor"));
				a.setNombres(rs.getString("nombres"));
				a.setApellidos(rs.getString("apellidos"));
				a.setFechanacimiento(rs.getDate("fechaNacimiento"));
				a.setFecharegistro(rs.getString("fechaRegistro"));
				a.setNacionalidad(rs.getString("nacionalidad"));
				a.setGrado(rs.getString("grado"));
				data.add(a);
				
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	
	
	public int InsertarAutor(AutorCrud a) {
		int resultado=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql="insert into autor values(null,?,?,?,curtime(),?,?)";
			
			pstm=con.prepareStatement(sql);
			pstm.setString(1,a.getNombres());
			pstm.setString(2, a.getApellidos());
			pstm.setDate(3, a.getFechanacimiento());
			pstm.setString(4, a.getNacionalidad());
			pstm.setString(5, a.getGrado());
			
			resultado=pstm.executeUpdate();
			System.out.println("SQL-->" + pstm);

			System.out.println("insertados : " + resultado);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {

				if (pstm != null)

					pstm.close();

				if (con != null)

					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	public int ActualizarAutor(AutorCrud a) {
		int resultado=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql="update autor set nombres=?,apellidos=?,fechaNacimiento=?,nacionalidad=?,grado=? where idAutor=?";
			
			pstm=con.prepareStatement(sql);
			pstm.setString(1,a.getNombres());
			pstm.setString(2, a.getApellidos());
			pstm.setDate(3, a.getFechanacimiento());
			pstm.setString(4, a.getNacionalidad());
			pstm.setString(5, a.getGrado());
			pstm.setInt(6,a.getIdAutor());
			
			resultado=pstm.executeUpdate();
			System.out.println("SQL-->" + pstm);

			System.out.println("actualizados : " + resultado);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {

				if (pstm != null)

					pstm.close();

				if (con != null)

					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	public int eliminar(int idAutor) {
		int resultado=-1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql ="delete from autor where idAutor=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idAutor);
			
			resultado = pstm.executeUpdate();
			System.out.println("eliminados :  " + resultado);
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
		return resultado;
		
	}
}
