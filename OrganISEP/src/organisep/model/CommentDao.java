package organisep.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.protobuf.TextFormat.ParseException;

import organisep.bean.CommentBean;

public class CommentDao {
	public int createComment(CommentBean b, int c) throws ParseException, java.text.ParseException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "INSERT INTO commentaires (commentaire_contenu, commentaire_lu, evenement_id, utilisateur_id) VALUES (?, ?, ?, ?)";
 			preparedStatement = con.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
 			preparedStatement.setString(1, b.getContent());
 			preparedStatement.setBoolean(2, b.getRead());
 			preparedStatement.setInt(3, b.getEvent());
 			preparedStatement.setInt(4, c);
 			
 			int affectedRows = preparedStatement.executeUpdate();

 	        if (affectedRows == 0) {
 	            throw new SQLException("Creating user failed, no rows affected.");
 	        }

 	        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
 	            if (generatedKeys.next()) {
 	                return generatedKeys.getInt(1);
 	            }
 	            else {
 	                throw new SQLException("Creating user failed, no ID obtained.");
 	            }
 	        }
 		}
 		catch(SQLException ex) {
 			ex.printStackTrace();
 		}
		return 0;
	}
	
	public int getEvent(String event) throws ParseException, java.text.ParseException  {
		Connection con = null;
		PreparedStatement preparedStatement = null;
 		ResultSet rs = null;
 		int eventId = 0;
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "SELECT evenement_id FROM evenements WHERE evenement_titre = ?";
 			preparedStatement = con.prepareStatement(selectSQL);
 			preparedStatement.setString(1, event);
 			rs = preparedStatement.executeQuery();
 
 			while(rs.next()) { 
 				eventId = rs.getInt("evenement_id");		
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
		return eventId;
	}
	
	public void readComment(String event, int idCreat) throws ParseException, java.text.ParseException  {
		int eventId = getEvent(event);
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "UPDATE commentaires SET commentaire_lu = ? WHERE evenement_id = ? AND utilisateur_id != ? ";
 			preparedStatement = con.prepareStatement(selectSQL);
 			preparedStatement.setBoolean(1, true);
 			preparedStatement.setInt(2, eventId);
 			preparedStatement.setInt(3, idCreat);
 			preparedStatement.executeUpdate();
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
	}
}
