package organisep.model;

import java.security.MessageDigest;
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
 		String mdp = MD5(loginBean.getMdp());
 
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
	
	public String MD5(String mdp) {
		   try {
		        MessageDigest md5 = MessageDigest.getInstance("MD5");
		        byte[] mdpEncrypt = md5.digest(mdp.getBytes());
		        StringBuffer mdpEncryptString = new StringBuffer();
		        for (int i = 0; i < mdpEncrypt.length; ++i) {
		        	mdpEncryptString.append(Integer.toHexString((mdpEncrypt[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return mdpEncryptString.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
 }