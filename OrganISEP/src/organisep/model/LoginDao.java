package organisep.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import organisep.bean.LoginBean;
import organisep.model.BDConnexion;

public class LoginDao {
	public String authenticateUser(LoginBean loginBean) {
 		String mail = loginBean.getEmail();
 		String mdp = loginBean.getMdp();
 
 		Connection con = null;
 		Statement statement = null;
 		ResultSet resultSet = null;
 
 		String emailBD = "";
 		String mdpBD = "";
 
 		try {
 			con = BDConnexion.createConnection();
 			statement = con.createStatement();
 			resultSet = statement.executeQuery("select utilisateur_email,utilisateur_mot_de_passe from utilisateurs");
 
 			while(resultSet.next()) { 
 				emailBD = resultSet.getString("utilisateur_email");
 				mdpBD = resultSet.getString("utilisateur_mot_de_passe");
 
 				if(mail.equals(emailBD) && mdp.equals(mdpBD)) {
 					return "SUCCESS"; 
 				}
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
 		return "Combinaison email et mot de passe invalide.";
	}
	
	public void sessionUser(LoginBean loginBean) {
		String mail = loginBean.getEmail();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
 		ResultSet resultSet = null;
 		
 		try {
 			con = BDConnexion.createConnection();
 			preparedStatement = con.prepareStatement("SELECT * FROM utilisateurs WHERE utilisateur_email = ?");
 			preparedStatement.setString(1, mail);
 			resultSet = preparedStatement.executeQuery();
 			
 			while (resultSet.next()) {
 				loginBean.setNom(resultSet.getString("utilisateur_nom"));
 				loginBean.setStatut(resultSet.getInt("utilisateur_statut"));
 				loginBean.setImage(resultSet.getString("utilisateur_image"));
 				loginBean.setId(resultSet.getInt("utilisateur_id"));
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
	}
 }