package organisep.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class BDConnexion {
	public static Connection createConnection() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/organisep?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "root"; // MySQL username
		String password = "root"; // MySQL password

		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); // loading mysql driver
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, username, password); // attempting to connect to MySQL database
			System.out.println("Printing connection object " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}