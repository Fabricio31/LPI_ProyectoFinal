package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Proveedor;
import util.MySqlDBConexion;
public class ProveedorModel 
	{
	public int insertaProveedor(Proveedor obj){
		int salida = -1;
		Connection coon = null;
		PreparedStatement pstm = null;
		try{
			coon = MySqlDBConexion.getConexion();
			String sql = "insert into Proveedor values (null,?,?,?,?,?,?,?)";
			pstm=coon.prepareStatement(sql);
			pstm.setString(1,obj.getNombre());
			pstm.setString(2,obj.getApellido());
			pstm.setString(3,obj.getDni());
			pstm.setString(4,obj.getDireccion());
			pstm.setString(5,obj.getTelefono());
			pstm.setString(6,obj.getCorreo());
			pstm.setString(7,obj.getPais());
			salida = pstm.executeUpdate();}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				if(coon != null)pstm.close();
				if(coon!=null)coon.close();
			} catch (Exception e2) {
			}
		}
		
		return salida;
}
}