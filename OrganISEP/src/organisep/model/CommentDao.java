package organisep.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

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
	
	public ArrayList<CommentBean> getComment(int idEvent) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
 		ResultSet rs = null;
 		ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
 
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "SELECT * FROM commentaires WHERE evenement_id = ?";
 			preparedStatement = con.prepareStatement(selectSQL);
 			preparedStatement.setInt(1, idEvent);
 			rs = preparedStatement.executeQuery();
 
 			while(rs.next()) { 
 				int idCreat = rs.getInt("utilisateur_id");
 				
 				EventDao eventDao = new EventDao();
 				String contentComment = rs.getString("commentaire_contenu");
 				Date dateComment = rs.getDate("commentaire_date");	
 				Boolean readComment = rs.getBoolean("commentaire_lu");
 				String nameCreat = eventDao.getCreateur(idCreat, "nom");
 				int eventComment = rs.getInt("evenement_id");
 				
 				CommentBean comment = new CommentBean(contentComment, dateComment, nameCreat, readComment, eventComment);
 				comments.add(comment);
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
		return comments;
	}
	
	public void readComment(String event, int idCreat) throws ParseException, java.text.ParseException  {
		EventDao eventDao = new EventDao();
		
		int eventId = eventDao.getEvent(event);
		
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
