package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Libro;
import util.MySqlDBConexion;

public class LibroModel {
	
	public int insertaLibro(Libro c) {

		int salida = -1;
		Connection con = null;

		PreparedStatement pstm = null;

		try {

			// 1 Conectar a la base de datos
			con = MySqlDBConexion.getConexion();

			// 2 Se prepara el SQL
			String sql = "insert into libro values(null,?,?,?,?)";

			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, c.getTitulo());
			pstm.setString(2, c.getAnio());
			pstm.setString(3, c.getCategoria());
			pstm.setString(4, c.getSerie());

			



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
	
}
