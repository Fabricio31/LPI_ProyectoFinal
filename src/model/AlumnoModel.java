package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Alumno;
import util.MySqlDBConexion;

public class AlumnoModel {
	public int insertaAlumno(Alumno obj){
		int salida = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			//con = new MySqlDBConexion().getConexion();
			con = MySqlDBConexion.getConexion();
			String sql = "insert into alumno values (null,?,?,?,?,?,curtime())";
			pstm = con.prepareStatement(sql);
			pstm.setString(1,obj.getNombres());
		 	pstm.setString(2,obj.getApellidos());
		 	pstm.setString(3,obj.getDNI());
		 	pstm.setString(4,obj.getCorreo());
		 	pstm.setDate(5,obj.getFechaNacimiento());
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
