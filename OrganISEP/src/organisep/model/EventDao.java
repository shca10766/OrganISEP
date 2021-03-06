package organisep.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import organisep.bean.CommentBean;
import organisep.bean.EventBean;
import organisep.model.BDConnexion;

public class EventDao {
	public ArrayList<EventBean> getEvents(ArrayList<EventBean> events) {
		Connection con = null;
 		Statement statement = null;
 		ResultSet resultSet = null;
 		
 		CommentDao commentDao = new CommentDao();
		
		try {
 			con = BDConnexion.createConnection();
 			statement = con.createStatement();
 			resultSet = statement.executeQuery("select * from evenements ORDER BY evenement_date DESC");
 
 			while(resultSet.next()) { 
 				
 				int idEvent = resultSet.getInt("evenement_id");
 				int idCreat = resultSet.getInt("utilisateur_id");
 				
 				String titreEvent = resultSet.getString("evenement_titre");
 				Date dateEvent = resultSet.getDate("evenement_date");
 				Time timeEvent = resultSet.getTime("evenement_time");
 				String imEvent = resultSet.getString("evenement_image");
 				String descriptionEvent = resultSet.getString("evenement_description");
 				ArrayList<String> salles = getSalle(idEvent);
 				ArrayList<CommentBean> commentsEvent = commentDao.getComment(idEvent);
 				String creat = getCreateur(idCreat, "nom");
 				String imCreat = getCreateur(idCreat, "image");
 				
 				int participants = resultSet.getInt("evenement_participant"); 
 				int budget = resultSet.getInt("evenement_budget");
 				
 				int valEvent = resultSet.getInt("evenement_validation");
 				int statutEvent = resultSet.getInt("evenement_statut");
 				ArrayList<String> ressources = getRessources(idEvent);
 				
 				
 				EventBean event = new EventBean(idEvent, titreEvent, dateEvent, timeEvent, imEvent, salles, creat, imCreat, valEvent, 
 						statutEvent, descriptionEvent, commentsEvent, participants, budget, ressources);
 				events.add(event);
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
	
	public void sendHTMLEmail(EventBean event, String type) throws MessagingException {
		Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", "smtp.live.com");
	      props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("iseporgan@hotmail.com", "passOrgan");
		    }
		});

		try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("iseporgan@hotmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("iseporgan@hotmail.com")
            );
            if(type.equals("creat")) {
            	message.setSubject("Demande de validation d'�v�nement");
                message.setText(event.getCreat() + " a cr�� l'�v�nement " + event.getTitre() + " et requiert votre validation"
                        + "\n\n Merci d'aller sur l'application Organ'Isep pour ce faire!");
            }
            else if(type.equals("update")) {
            	message.setSubject("Modification d'un �v�nement");
                message.setText(event.getCreat() + " a modifier l'�v�nement " + event.getTitre() + " et requiert votre validation"
                        + "\n\n Merci d'aller sur l'application Organ'Isep pour ce faire!");
            }
            

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	public EventBean getEventInEvents(String eventTitle) {
		EventBean event;
		ArrayList<EventBean> listEvents = new ArrayList<EventBean>();
		listEvents = getEvents(listEvents);
		for(int i = 0 ; i<listEvents.size(); i++) {
			if (listEvents.get(i).getTitre().equals(eventTitle)){
				event = listEvents.get(i);
				return event;
			}
		}
		return null;
	}
	
	public EventBean getEventInEventsById(int eventId) {
		EventBean event;
		ArrayList<EventBean> listEvents = new ArrayList<EventBean>();
		listEvents = getEvents(listEvents);
		for(int i = 0 ; i<listEvents.size(); i++) {
			if (listEvents.get(i).getId() == eventId){
				event = listEvents.get(i);
				return event;
			}
		}
		return null;
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
	
	private ArrayList<String> getRessources(int idEvent) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
 		ResultSet rs = null;
 		ArrayList<String> ressources = new ArrayList<String>();
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "SELECT ressource_nom FROM ressources WHERE evenement_id = ?";
 			preparedStatement = con.prepareStatement(selectSQL);
 			preparedStatement.setInt(1, idEvent);
 			rs = preparedStatement.executeQuery();
 
 			while(rs.next()) { 
 				ressources.add(rs.getString("ressource_nom"));
 			}
 		}
 		catch(SQLException e) {
 			e.printStackTrace();
 		}
		return ressources;
	}
	
	public void updateValidationEvent(int id, int val) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = BDConnexion.createConnection();
			String selectSQL = "UPDATE evenements SET evenement_validation = ? WHERE evenement_id = ?";
			preparedStatement = con.prepareStatement(selectSQL);
			preparedStatement.setInt(1, val);
 			preparedStatement.setInt(2, id);
 		
 			
 			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int updateEvent(EventBean e) throws ParseException, java.text.ParseException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String d = e.getDate();
		Date dat = new java.sql.Date(sdf.parse(d).getTime());
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "UPDATE evenements SET evenement_titre = ?, evenement_participant = ?, evenement_date = ?, evenement_time = ?, evenement_budget = ?, evenement_description = ?, evenement_image = ? WHERE evenement_id = ?";
 			preparedStatement = con.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
 			preparedStatement.setString(1, e.getTitre());
 			preparedStatement.setInt(2, e.getParticipants());
 			preparedStatement.setDate(3, (java.sql.Date) dat);
 			preparedStatement.setString(4, e.getTime());
 			preparedStatement.setInt(5, e.getBudget());
 			preparedStatement.setString(6, e.getDescription());
 			preparedStatement.setString(7, e.getImage());
 			preparedStatement.setInt(8, e.getId());
 			
 			preparedStatement.executeUpdate();
 		}
 		catch(SQLException ex) {
 			ex.printStackTrace();
 		}
		return 0;
	}
	
	public void deleteSalle(int idEvent) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "DELETE FROM reservation_salle WHERE evenement_id = ?";
 			preparedStatement = con.prepareStatement(selectSQL);
 			preparedStatement.setInt(1, idEvent);
 		
 			
 			preparedStatement.executeUpdate();
 		}
 		catch(SQLException ex) {
 			ex.printStackTrace();
 		}
	}
	
	public void deleteRess(int idEvent) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
 			con = BDConnexion.createConnection();
 			String selectSQL = "DELETE FROM ressources WHERE evenement_id = ?";
 			preparedStatement = con.prepareStatement(selectSQL);
 			preparedStatement.setInt(1, idEvent);
 		
 			
 			preparedStatement.executeUpdate();
 		}
 		catch(SQLException ex) {
 			ex.printStackTrace();
 		}
	}
}
