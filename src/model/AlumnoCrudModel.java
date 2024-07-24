package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Alumno;
import util.MySqlDBConexion;

public class AlumnoCrudModel 
{
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
	}//final inserta
	//-------------------------------
	public List<Alumno> listaAlumno()  { //Este metodo tiene el select y nos devuelve arraylist de campeonato ENTIDAD
		ArrayList<Alumno> data = new ArrayList<Alumno>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			String sql ="select * from alumno";
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			
			//En rs se trae los datos de la BD segun el SQL
			rs = pstm.executeQuery();
			
			//Se pasa la data del rs al ArrayList(data)
			Alumno c = null;
			while(rs.next()){
				c = new Alumno();
				// Se colocan los campos de la base de datos
				c.setIdAlumno(rs.getInt("idAlumno"));
				c.setNombres(rs.getString("nombres"));
				c.setApellidos(rs.getString("apellidos"));
				c.setDNI(rs.getString("dni"));
				c.setCorreo(rs.getString("correo"));
				c.setFechaNacimiento(rs.getDate("fechaNacimiento"));
				//c.setFechaNacimiento(rs.getDate("fechaRegistro"));
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
	}//final de listar
	//-------------------------------
	public int actualizaAlumnoCrud(Alumno c) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "update alumno set nombre=?, apellido=?, dni=?, correo=?, fechaNacimiento=? where idAlumno=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1,c.getNombres());
		 	pstm.setString(2,c.getApellidos());
		 	pstm.setString(3,c.getDNI());
		 	pstm.setString(4,c.getCorreo());
		 	pstm.setDate(5,c.getFechaNacimiento());
		 	pstm.setInt(5, c.getIdAlumno());

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
	public int eliminaAlumnoCrud(int idAlumno) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql ="delete from alumno where idAlumno=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idAlumno);
			
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
}
