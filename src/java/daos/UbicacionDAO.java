/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entidades.Ubicacion;
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
public class UbicacionDAO {
    
     public List<Ubicacion> listarUbicaciones() {
        List<Ubicacion> ubicacionList = new ArrayList<Ubicacion>();

        try {
            Connection cn = Dao.getConnection();

            String query = "SELECT * FROM ubicacion";

            PreparedStatement ps = cn.prepareCall(query);

            ResultSet rs = ps.executeQuery();

            Ubicacion u;

            while (rs.next()) {
                u = new Ubicacion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                ubicacionList.add(u);
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return ubicacionList;
    }

}
