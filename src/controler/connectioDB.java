package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectioDB {
	 public static Connection connectioDB(String usuario, String password){
	        Connection con = null;
	        String url = "jdbc:sqlserver://localhost:1433;DatabaseName = events_daisy";
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(url, usuario, password);
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	            System.out.println("Driver de SQL Server no encontrado");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            System.out.println("Usuario o contraseña de base de datos erronea");
	        }
	        return con;
	    

	
}

}
