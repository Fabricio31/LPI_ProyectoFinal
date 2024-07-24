package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Proveedor;
import entidad.ProveedorCrud;
import util.MySqlDBConexion;

public class ConsultaProveedorModel {
	//XFechas
	public List<ProveedorCrud> listarxnombre(String nombre)
	{
		ArrayList<ProveedorCrud> data = new ArrayList<ProveedorCrud>();
		Connection coon = null;
		PreparedStatement pstm= null;
		ResultSet rs = null;
		try {
			coon = MySqlDBConexion.getConexion();
			String sql = "select * from proveedor where nombres = ?";
			pstm = coon.prepareStatement(sql);
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();
			
			ProveedorCrud obj = null;
			while(rs.next()) {
				obj = new ProveedorCrud();
				obj.setIdProveedor(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDNI(rs.getString(4));
				obj.setDireccion(rs.getString(5));
				obj.setTelefono(rs.getString(6));
				obj.setCorreo(rs.getString(7));
				obj.setPais(rs.getString(8));
				
				data.add(obj);
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

	public List<Proveedor> listarProveedor(){
		ArrayList<Proveedor> data = new ArrayList<Proveedor>();
		Connection coon = null;
		PreparedStatement pstm= null;
		ResultSet rs = null;
		try {
			coon = MySqlDBConexion.getConexion();
			String sql = "select * from proveedor";
			pstm = coon.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Proveedor obj = null;
			while(rs.next()) {
				obj = new Proveedor();
				obj.setIdProveedor(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getString(4));
				obj.setDireccion(rs.getString(5));
				obj.setTelefono(rs.getString(6));
				obj.setCorreo(rs.getString(7));
				obj.setPais(rs.getString(8));
				
				data.add(obj);
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
	
	}
