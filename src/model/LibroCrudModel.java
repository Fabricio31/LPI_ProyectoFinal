package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.LibroCrud;
import util.MySqlDBConexion;

public class LibroCrudModel {
	
	public int insertaLibro(LibroCrud l) {

		int salida = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {

			// 1 Conectar a la base de datos
			con = MySqlDBConexion.getConexion();

			// 2 Se prepara el SQL
			String sql = "insert into libro values(null,?,?,?,?)";

			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, l.getTitulo());
			pstm.setString(2, l.getAnio());
			pstm.setString(3, l.getCategoria());
			pstm.setString(4, l.getSerie());
			// 3 envia el sql y se recibe la cantidad de registrados

			salida = pstm.executeUpdate();

			System.out.println("SQL-->" + pstm);
			System.out.println("insertados : " + salida);



		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (Exception e2) {
			}
		}

		return salida;

	}
	
	public List<LibroCrud> listarLibro(){
		ArrayList<LibroCrud> data = new ArrayList<LibroCrud>();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs= null;
		try {
			con= MySqlDBConexion.getConexion();
			String sql="select * from libro";
			pstm= con.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			LibroCrud l=null;
			while(rs.next()) {
			l=new LibroCrud();
			
			l.setIdLibro(rs.getInt(1));
			l.setTitulo(rs.getString(2));
			l.setAnio(rs.getString(3));
			l.setCategoria(rs.getString(4));
			l.setSerie(rs.getString(5));
			
			data.add(l);
			
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(null!=pstm) pstm.close();
			if(null!=con)con.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return data;
	}	
	
	public int actualizar(LibroCrud l) {
		int resultado=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql="update libro set titulo = ?, anio= ?, categoria=?,serie=?  where idLibro = ?";
			
			pstm=con.prepareStatement(sql);
			pstm.setString(1,l.getTitulo());
			pstm.setString(2, l.getAnio());
			pstm.setString(3,l.getCategoria());
			pstm.setString(4, l.getSerie());
			pstm.setInt(5, l.getIdLibro());
			
			resultado=pstm.executeUpdate();
			
			System.out.println("SQL-->" + pstm);

			System.out.println("actualizados : " + resultado);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(null!=pstm)pstm.close();
			if(null!=con)con.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return resultado;
	}
	
	public int eliminar(int idLibro) {
		int resultado=-1;
		Connection con=null;
		PreparedStatement pstm=null;

		try {
			con=MySqlDBConexion.getConexion();
			String sql="delete from libro where idLibro=?";
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, idLibro);
			resultado=pstm.executeUpdate();
			System.out.println("eliminados :  " + resultado);
			
		}catch (SQLException e) {
			if (e.getErrorCode()==1451) {
				resultado=-2;
			}else {
			e.printStackTrace();
			}
		
		}finally {
			try {
				if(null!=con)con.close();
				if(null!=pstm)pstm.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return resultado;
	}
	}
	
	
