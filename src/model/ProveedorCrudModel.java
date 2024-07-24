
 package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.ProveedorCrud;
import util.MySqlDBConexion;



public class ProveedorCrudModel {
	public int insertaProveedor(ProveedorCrud p) {

		int salida = -1;
		
		Connection con = null;

		PreparedStatement pstm = null;

		try {
			//con = MySqlDBConexion.getConexion();

			con = MySqlDBConexion.getConexion();

			String sql = "insert into proveedor values(null,?,?,?,?,?,?,?)";

			pstm = con.prepareStatement(sql);

			pstm.setString(1, p.getNombre());

			pstm.setString(2, p.getApellido());

			pstm.setString(3, p.getDNI());

			pstm.setString(4, p.getDireccion());

			pstm.setString(5, p.getTelefono());
			
			pstm.setString(6, p.getCorreo());
			
			pstm.setString(7, p.getPais());

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
				e2.printStackTrace();
			}

		}

		return salida;
	
	}//FINAL INSERTA
	public List<ProveedorCrud> listaProveedorCrud()  { //Este metodo tiene el select y nos devuelve arraylist de campeonato
		ArrayList<ProveedorCrud> data = new ArrayList<ProveedorCrud>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			String sql ="select * from proveedor";
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			
			//En rs se trae los datos de la BD segun el SQL
			rs = pstm.executeQuery();
			
			//Se pasa la data del rs al ArrayList(data)
			ProveedorCrud p = null;
			while(rs.next()){
				p=new ProveedorCrud();
				// Se colocan los campos de la base de datos
				p.setIdProveedor(rs.getInt("idProveedor"));
				p.setNombre(rs.getString("nombres"));
				p.setApellido(rs.getString("apellidos"));
				p.setDNI(rs.getString("dni"));
				p.setDireccion(rs.getString("direccion"));
				p.setTelefono(rs.getString("telefono"));
				p.setCorreo(rs.getString("correo"));
				p.setPais(rs.getString("pais"));
				
				data.add(p);
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
	
	public int actualizaProveedorCrud(ProveedorCrud p) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "update proveedor set nombres=? , apellidos=? , dni=?, direccion=?,telefono=?,correo=? ,pais=? where idProveedor=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, p.getNombre());
			pstm.setString(2, p.getApellido());
			pstm.setString(3, p.getDNI());
			pstm.setString(4, p.getDireccion());
			pstm.setString(5, p.getTelefono());
			pstm.setString(6, p.getCorreo());
			pstm.setString(7, p.getPais());
			pstm.setInt(8, p.getIdProveedor());

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
	
	public int eliminaSalaCrud(int idProveedor) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql ="delete from proveedor where idProveedor=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idProveedor);
			
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
