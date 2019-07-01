package panel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbsal {
	static Connection con;
	private Dbsal() throws Exception {
	      String url="jdbc:oracle:thin:@localhost:1521:orcl2";
	      String user="scott";
	      String pass="123456";
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	      con=DriverManager.getConnection(url, user, pass);
	   }
	public static Connection getConnection() throws Exception {
		if (con==null) {
			new Dbsal();
		}
		return con;
	}
}
