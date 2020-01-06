/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author XxkokoxXT
 */
public class UsuarioDAO {
    
    public List<Usuario> validarUsuario(String correo, String contraseña) {
        List<Usuario> usuarioslList = new ArrayList<Usuario>();

        try {
            Connection cn = Dao.getConnection();

            String query = "SELECT * FROM usuarios WHERE correoUsu = '" + correo + "' and passUsu ='" + contraseña + "'";

            PreparedStatement ps = cn.prepareCall(query);

            ResultSet rs = ps.executeQuery();

            Usuario u;

            while (rs.next()) {
                u = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                usuarioslList.add(u);
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return usuarioslList;
    }

    public int registrarUsuario(String nombre, String apellido, String correo, String contraseña) {
        int resultado = 0;

        String sql = "INSERT INTO usuarios (nomUsu, apeUsu, correoUsu, passUsu) "
                + "VALUES (?, ?, ?, ?)";
        
        Connection cn = Dao.getConnection();
        
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setString(4, contraseña);

            resultado = ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al intentar almacenar la información: " + e);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al intentar cerrar la conexión: " + ex.getMessage());
            }
        }

        return resultado;
    }

}
