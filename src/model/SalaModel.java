package model;

import java.sql.Connection;
import java.sql.PreparedStatement;


import entidad.Sala;

import util.MySqlDBConexion;

public class SalaModel {
	public int insertaSala(Sala s) {

		int salida = -1;



		Connection con = null;

		PreparedStatement pstm = null;

		try {


			con = MySqlDBConexion.getConexion();

			String sql = "insert into sala values(null,?,?,?,?,?)";

			pstm = con.prepareStatement(sql);

			pstm.setString(1, s.getNumero());

			pstm.setInt(2, s.getPiso());

			pstm.setString(3, s.getCapacidad());

			pstm.setString(4, s.getRecursos());

			pstm.setByte(5, s.getEstado());

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

	}
}
