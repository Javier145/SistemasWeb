package mx.uv.sw80;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion{
    private static String url = "jdbc:mysql://127.0.0.1:3306/ejemplo_simple";
    private static String driverName = "com.mysql.cj.cjdbc.Driver";
    private static String user = "root";
    private static String password = "";
    private static Connection con = null;

    public static Connection getConnection(){
        try{
            Class.forName(driverName);
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Sucess connection!");
        }catch(SQLException e){
            System.out.println("Failed to creat data base!");

        }catch(ClassNotFoundException e){
            System.out.println("Driver not found!");
        }
        return con;

    }
}