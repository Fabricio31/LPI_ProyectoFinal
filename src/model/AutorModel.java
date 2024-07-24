package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Autor;
import util.MySqlDBConexion;

public class AutorModel {
		
	public int InsertarAutor(Autor a) {
		int resultado=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql="insert into autor values(null,?,?,?,curtime(),?,?)";
			
			pstm=con.prepareStatement(sql);
			pstm.setString(1,a.getNombres());
			pstm.setString(2, a.getApellidos());
			pstm.setDate(3, a.getFechaNacimiento());
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
	
}
