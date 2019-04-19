package organisep.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import organisep.bean.LoginBean;
import organisep.model.BDConnexion;

public class LoginDao {
	public String authenticateUser(LoginBean loginBean) {
 		String mail = loginBean.getEmail(); //Keeping user entered values in temporary variables.
 		String mdp = loginBean.getMdp();
 
 		Connection con = null;
 		Statement statement = null;
 		ResultSet resultSet = null;
 
 		String emailBD = "";
 		String mdpBD = "";
 
 		try {
 			con = BDConnexion.createConnection(); //establishing connection
 			statement = con.createStatement(); //Statement is used to write queries. Read more about it.
 			resultSet = statement.executeQuery("select utilisateur_email,utilisateur_mot_de_passe from utilisateurs");
 
 			while(resultSet.next()) { // Until next row is present otherwise it return false
 				emailBD = resultSet.getString("utilisateur_email"); //fetch the values present in database
 				mdpBD = resultSet.getString("utilisateur_mot_de_passe");
 
 				if(mail.equals(emailBD) && mdp.equals(mdpBD)) {
 					return "SUCCESS"; ////If the user entered values are already present in database, which means user has already registered so I will return SUCCESS message.
 				}
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
 		return "Combinaison email et mot de passe invalide."; // Just returning appropriate message otherwise
	}
 }
