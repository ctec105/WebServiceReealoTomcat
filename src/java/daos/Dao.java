/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author XxkokoxXT
 */
public class Dao {

    private static String nombre_BD = "ReealoDB2019";
    private static String usuario = "cibertecx_SQLLogin_1";
    private static String pass = "6yhrj2t7no";
 
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                  
            con = DriverManager.getConnection("jdbc:sqlserver://ReealoDB2019.mssql.somee.com:1433;databasename=" + nombre_BD + ";" + "user=" + usuario + ";password=" + pass);
            
            System.out.println("Existosa Conexion a la Base de Datos");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al Conectar con la Base de Datos: " + e);
            //JOptionPane.showMessageDialog(null, "Error al Acceder a la Base de Datos");
            e.printStackTrace();
        }

        return con;
    }
    
    
    
    /**
     * CONEXIÓN LOCAL PARA PRUEBAS (COMENTA EL COD ANTERIOR) *
     */
    /*static Connection con;
    static String nombre_BD = "Reealo2019";
    static String usuario = "p1";
    static String pass = "sql";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://XXKOKOXXT-PC:1433;databasename=" + nombre_BD + ";" + "user=" + usuario + ";password=" + pass);
            System.out.println("Existosa Conexion a la Base de Datos");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al Conectar con la Base de Datos");
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Base de Datos");
            e.printStackTrace();
        }
        return con;
    }*/

}
