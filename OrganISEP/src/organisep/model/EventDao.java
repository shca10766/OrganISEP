package organisep.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import organisep.bean.CommentBean;
import organisep.bean.EventBean;
import organisep.bean.EventsBean;
import organisep.model.BDConnexion;

public class EventDao {
	public EventsBean getEvents(EventsBean events) {
		Connection con = null;
 		Statement statement = null;
 		ResultSet resultSet = null;
		
		try {
 			con = BDConnexion.createConnection();
 			statement = con.createStatement();
 			resultSet = statement.executeQuery("select * from evenements");
 
 			while(resultSet.next()) { 
 				
 				int idEvent = resultSet.getInt("evenement_id");
 				int idCreat = resultSet.getInt("utilisateur_id");
 				
 				String titreEvent = resultSet.getString("evenement_titre");
 				Date dateEvent = resultSet.getDate("evenement_date");
 				String imEvent = resultSet.getString("evenement_image");
 				String descriptionEvent = resultSet.getString("evenement_description");
 				ArrayList<String> salles = getSalle(idEvent);
 				ArrayList<CommentBean> commentsEvent = getComment(idEvent);
 				String creat = getCreateur(idCreat, "nom");
 				String imCreat = getCreateur(idCreat, "image");
 				
 				int valEvent = resultSet.getInt("evenement_validation");
 				int statutEvent = resultSet.getInt("evenement_statut");
 				
 				
 				EventBean event = new EventBean(titreEvent, dateEvent, imEvent, salles, creat, imCreat, valEvent, statutEvent, descriptionEvent, commentsEvent);
 				events.addEvent(event);
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
		return events;
	}
	
	public ArrayList<String> getSalle(int idEvent) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
 		ResultSet rs = null;
 		ArrayList<String> salles = new ArrayList<String>();
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "SELECT salle_nom FROM reservation_salle, salle WHERE salle_id = id_salle AND evenement_id = ?";
 			preparedStatement = con.prepareStatement(selectSQL);
 			preparedStatement.setInt(1, idEvent);
 			rs = preparedStatement.executeQuery();
 
 			while(rs.next()) { 
 				salles.add(rs.getString("salle_nom")); 				
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
		return salles;
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
 				
 				String contentComment = rs.getString("commentaire_contenu");
 				Date dateComment = rs.getDate("commentaire_date");	
 				Boolean readComment = rs.getBoolean("commentaire_lu");
 				String nameCreat = getCreateur(idCreat, "nom");
 				
 				CommentBean comment = new CommentBean(contentComment, dateComment, nameCreat, readComment);
 				comments.add(comment);
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
		return comments;
	}
	
	public String getCreateur(int idCreat, String type) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
 		ResultSet rs = null;
 		String createur = null;
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "SELECT utilisateur_" + type + " FROM utilisateurs WHERE utilisateur_id = ?";
 			preparedStatement = con.prepareStatement(selectSQL);
 			preparedStatement.setInt(1, idCreat);
 			rs = preparedStatement.executeQuery();
 
 			while(rs.next()) { 
 				createur = rs.getString("utilisateur_" + type);		
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
		return createur;
	}
}
