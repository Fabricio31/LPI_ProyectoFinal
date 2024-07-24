package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Alumno;
import util.MySqlDBConexion;

public class ConsultaAlumnoModel {
	//XFechas
	public List<Alumno> consultaDirectorxFechas(String fecIni, String fecFin)
	{
		ArrayList<Alumno> data = new ArrayList<Alumno>();
		Connection coon = null;
		PreparedStatement pstm= null;
		ResultSet rs = null;
		try {
			coon = MySqlDBConexion.getConexion();
			String sql = "select * from alumno where fechaNacimiento between ? and ?";
			pstm = coon.prepareStatement(sql);
			pstm.setDate(1, Date.valueOf(fecIni));
			pstm.setDate(2, Date.valueOf(fecFin));
			
			rs = pstm.executeQuery();
			
			Alumno objAlumno = null;
			while(rs.next()) {
				objAlumno = new Alumno();
				objAlumno.setIdAlumno(rs.getInt(1));
				objAlumno.setNombres(rs.getString(2));
				objAlumno.setApellidos(rs.getString(3));
				objAlumno.setDNI(rs.getString(4));
				objAlumno.setCorreo(rs.getString(5));
				objAlumno.setFechaNacimiento(rs.getDate(6));
				objAlumno.setFechaRegistro(rs.getDate(7));
				
				data.add(objAlumno);
			}
		}catch (Exception e) {
		e.printStackTrace();
		}finally {
			try {
				if (pstm != null)pstm.close();
				if (coon != null)coon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	//Listar
	public List<Alumno> listaAlumno(){
		ArrayList<Alumno> data = new ArrayList<Alumno>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; 
		try {
			
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from alumno";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
				
			
			Alumno objAlumno= null;
			while(rs.next()){
				objAlumno = new Alumno();
				objAlumno.setIdAlumno(rs.getInt(1));
				objAlumno.setNombres(rs.getString(2));
				objAlumno.setApellidos(rs.getString(3));
				objAlumno.setDNI(rs.getString(4));
				objAlumno.setCorreo(rs.getString(5));
				objAlumno.setFechaNacimiento(rs.getDate(6));
				objAlumno.setFechaRegistro(rs.getDate(7));
				
				data.add(objAlumno);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
}
