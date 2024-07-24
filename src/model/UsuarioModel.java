package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Opcion;
import entidad.Usuario;
import util.MySqlDBConexion;

public class UsuarioModel {

	public Usuario valida(String login, String clave) {
		Usuario bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from usuario where login=? and password =?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, clave);
			System.out.println(pstm);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				bean = new Usuario();
				bean.setIdUsuario(rs.getInt(1));
				bean.setNombre(rs.getString("nombre"));
				bean.setApellido(rs.getString("apellido"));
				bean.setDni(rs.getString("dni"));
				bean.setLogin(rs.getString("login"));
				bean.setPassword(rs.getString("password"));
				bean.setCorreo(rs.getString("correo"));
				bean.setFecnac(rs.getString("fechaNacimiento"));
				bean.setDireccion(rs.getString("direccion"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
			}
		}
		return bean;
	}

	public List<Opcion> obtieneOpciones(int idUsuario) {
		ArrayList<Opcion> data = new ArrayList<Opcion>();
		Opcion bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select p.idopcion, p.nombre  from opcion p inner join rol_has_opcion r on p.idopcion = r.idopcion inner join rol c on r.idrol = c.idrol inner join usuario_has_rol h on c.idrol = h.idrol where idusuario = ? order by 2;";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			System.out.println(pstm);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Opcion();
				bean.setIdOpcion(rs.getInt("idopcion"));
				bean.setNombre(rs.getString("nombre"));
				data.add(bean);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}

	public int insertaUsuario(Usuario c) {
		int salida = -1;

		Connection con = null;
		PreparedStatement pstm = null;
		try {
			// 1 Conectar a la base de datos
			con = MySqlDBConexion.getConexion();

			// 2 Se prepara el SQL
			String sql = "insert into usuario values(null,?,?,?,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, c.getNombre());
			pstm.setString(2, c.getApellido());
			pstm.setString(3, c.getDni());
			pstm.setString(4, c.getLogin());
			pstm.setString(5, c.getPassword());
			pstm.setString(6, c.getCorreo());
			pstm.setString(7, c.getFecnac());
			pstm.setString(8, c.getDireccion());

			// 3 envia el sql y se recibe la cantidad de registrados
			salida = pstm.executeUpdate();

			System.out.println("SQL-->" + pstm);
			System.out.println("insertados :  " + salida);

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

	public List<Usuario> listaUsuario() {
		ArrayList<Usuario> data = new ArrayList<Usuario>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; // Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "select * from usuario";
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);

			// En rs se trae los datos de la BD segun el SQL
			rs = pstm.executeQuery();

			// Se pasa la data del rs al ArrayList(data)
			Usuario c = null;
			while (rs.next()) {
				c = new Usuario();
				c.setIdUsuario(rs.getInt("idUsuario"));
				c.setNombre(rs.getString("nombre"));
				c.setApellido(rs.getString("apellido"));
				c.setDni(rs.getString("dni"));
				c.setLogin(rs.getString("login"));
				c.setPassword(rs.getString("password"));
				c.setCorreo(rs.getString("correo"));
				c.setFecnac(rs.getString("fechaNacimiento"));
				c.setDireccion(rs.getString("direccion"));
				data.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public int actualizaUsuario(Usuario c) {
        int actualizados = -1;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn= MySqlDBConexion.getConexion();
            String sql = "update usuario set nombre=?, apellido=?, dni=?, login=?, password=?, correo=?, fechaNacimiento=?, direccion=? where idUsuario=?"; 
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, c.getNombre());
            pstm.setString(2, c.getApellido());
            pstm.setString(3, c.getDni());
            pstm.setString(4, c.getLogin());
            pstm.setString(5, c.getPassword());
            pstm.setString(6, c.getCorreo());
            pstm.setString(7, c.getFecnac());
            pstm.setString(8, c.getDireccion());
            pstm.setInt   (9, c.getIdUsuario());
            actualizados = pstm.executeUpdate();
            System.out.println("actualizados :  " + actualizados);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstm!=null) pstm.close();
                if(conn!=null) conn.close();
            }
            catch(Exception e2) {

            }
        }
        return actualizados;
    }


	public int eliminaUsuario(int idUsuario) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "delete from usuario where idUsuario=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			eliminados = pstm.executeUpdate();
			System.out.println("eliminados :  " + eliminados);
			System.out.println("SQL-->" + pstm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eliminados;
	}
}
