package organisep.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class ReservationDao {
	public ArrayList<String> getSalles(String d) {
		Connection con = null;
 		Statement statement = null;
 		ResultSet resultSet = null;
 		ArrayList<String> salles = new ArrayList<String>();
		
		try {
 			con = BDConnexion.createConnection();
 			statement = con.createStatement();
 			resultSet = statement.executeQuery("select * from salle");
 
 			while(resultSet.next()) { 
 				salles.add(resultSet.getString("salle_nom"));
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
		return salles;
	}
}
