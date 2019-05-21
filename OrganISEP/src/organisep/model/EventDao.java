package organisep.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

<<<<<<< HEAD
=======
import com.google.protobuf.TextFormat.ParseException;

>>>>>>> refs/remotes/origin/master
import organisep.bean.CommentBean;
import organisep.bean.EventBean;
import organisep.model.BDConnexion;

public class EventDao {
	public ArrayList<EventBean> getEvents(ArrayList<EventBean> events) {
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
 				Time timeEvent = resultSet.getTime("evenement_time");
 				String imEvent = resultSet.getString("evenement_image");
 				String descriptionEvent = resultSet.getString("evenement_description");
 				ArrayList<String> salles = getSalle(idEvent);
 				ArrayList<CommentBean> commentsEvent = getComment(idEvent);
 				String creat = getCreateur(idCreat, "nom");
 				String imCreat = getCreateur(idCreat, "image");
 				
 				int valEvent = resultSet.getInt("evenement_validation");
 				int statutEvent = resultSet.getInt("evenement_statut");
 				
 				
<<<<<<< HEAD
 				EventBean event = new EventBean(titreEvent, dateEvent, imEvent, salles, creat, imCreat, valEvent, statutEvent, descriptionEvent, commentsEvent);
 				events.addEvent(event);
=======
 				EventBean event = new EventBean(titreEvent, dateEvent, timeEvent, imEvent, salles, creat, imCreat, valEvent, statutEvent, descriptionEvent, commentsEvent);
 				events.add(event);
>>>>>>> refs/remotes/origin/master
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
	
	public ArrayList<String> getListSalles(String d) {
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
 				int idSalle = resultSet.getInt("id_salle");
 				salles.add(Integer.toString(idSalle));
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
		return salles;
	}
	
	public int createEvent(EventBean e, int idCreat) throws ParseException, java.text.ParseException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String d = e.getDate();
		Date dat = new java.sql.Date(sdf.parse(d).getTime());
		String Test = dat.toString();
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "INSERT INTO evenements (evenement_titre, evenement_participant, evenement_date, evenement_time, evenement_budget, evenement_lien, evenement_description, evenement_validation, evenement_statut, evenement_image, utilisateur_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 			preparedStatement = con.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
 			preparedStatement.setString(1, e.getTitre());
 			preparedStatement.setInt(2, e.getParticipants());
 			preparedStatement.setDate(3, (java.sql.Date) dat);
 			preparedStatement.setString(4, e.getTime());
 			preparedStatement.setInt(5, e.getBudget());
 			preparedStatement.setString(6, e.getLien());
 			preparedStatement.setString(7, e.getDescription());
 			preparedStatement.setInt(8, e.getValidation());
 			preparedStatement.setInt(9, e.getStatut());
 			preparedStatement.setString(10, e.getImage());
 			preparedStatement.setInt(11, idCreat);
 			
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
	
	public void reservSalle(int idSalle, int idEvent) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "INSERT INTO reservation_salle (salle_id, evenement_id) VALUES (?, ?)";
 			preparedStatement = con.prepareStatement(selectSQL);
 			preparedStatement.setInt(1, idSalle);
 			preparedStatement.setInt(2, idEvent);
 		
 			
 			preparedStatement.executeUpdate();
 		}
 		catch(SQLException ex) {
 			ex.printStackTrace();
 		}
	}
	
	public void reservRess(String ress, int idEvent) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "INSERT INTO ressources (evenement_id, ressource_nom) VALUES (?, ?)";
 			preparedStatement = con.prepareStatement(selectSQL);
 			preparedStatement.setInt(1, idEvent);
 			preparedStatement.setString(2, ress);
 		
 			
 			preparedStatement.executeUpdate();
 		}
 		catch(SQLException ex) {
 			ex.printStackTrace();
 		}
	}
}