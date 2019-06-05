package organisep.bean;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EventBean {
	private ArrayList<String> salles;
	private ArrayList<CommentBean> comments;
	private String date;
	private String time;
	private String titre;
	private String creat;
	private String image;
	private String imCreat;
	
	private int participants;
	private int budget;
	private String lien;
	private ArrayList<String> ressources;
	private String description;
	
	private int validation;
	private int statut;
	private int etat;
	private int id;
		
	public EventBean(int idEvent, String titreEvent, Date dateEvent, Time timeEvent, String imEvent, ArrayList<String> sallesEvent,
			String creatEvent, String imCreatEvent, int valEvent, int statutEvent, String descriptionEvent, 
			ArrayList<CommentBean> commentsEvent, int participantsEvent, int budgetEvent, ArrayList<String> ressourcesEvent) {
		setId(idEvent);
		salles = sallesEvent;
		comments = commentsEvent;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat tf = new SimpleDateFormat("HH:mm");
	    date = df.format(dateEvent);
		time = tf.format(timeEvent);
		titre = titreEvent;
		creat = creatEvent;
		image = imEvent;
		imCreat = imCreatEvent;
		description = descriptionEvent;
		
		validation = valEvent;
		statut = statutEvent;
		
		participants = participantsEvent;
		budget = budgetEvent;
		ressources = ressourcesEvent;
		
		Date auj = new Date();

		if (dateEvent.compareTo(auj) > 0) {
            		etat = 1;
        	}
		else {
			etat = 0;
		}
	}
	
	public EventBean(String titreEvent, String dateEvent, String timeEvent, String imEvent, 
					String creatEvent, int participantsEvent, 
					int budgetEvent, String lienEvent, ArrayList<String> ressourcesEvent, String descEvent) {
		
		date = dateEvent;
		time = timeEvent;
		titre = titreEvent;
		creat = creatEvent;
		image = imEvent;
		
		validation = 3;
		etat = 1;
		statut = 1;
		
		participants = participantsEvent;
		budget = budgetEvent;
		lien = lienEvent;
		ressources = ressourcesEvent;
		description = descEvent;
	}
	
	public void updateEvent(String nomEvent, String timeEvent, String descEvent, int nbr_participant, int budgetEvent,
			ArrayList<String> sallesEvent, ArrayList<String> ressourcesEvent, String dateEvent, String imEvent) {
		this.titre = nomEvent;
		this.time = timeEvent;
		this.description = descEvent;
		this.participants = nbr_participant;
		this.budget = budgetEvent;
		this.salles = sallesEvent;
		this.ressources = ressourcesEvent;
		this.date = dateEvent;
		
		if(this.image != "img/imgEvent/noImage.png" && imEvent != "") {
			this.image = imEvent;
		}
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ArrayList<String> getSalles() {
		return salles;
	}

	public void setSalles(ArrayList<String> salles) {
		this.salles = salles;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getCreat() {
		return creat;
	}

	public void setCreat(String creat) {
		this.creat = creat;
	}
	
	public String getImCreat() {
		return imCreat;
	}

	public void setImCreat(String imCreat) {
		this.imCreat = imCreat;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getValidation() {
		return validation;
	}

	public void setValidation(int validation) {
		this.validation = validation;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public ArrayList<String> getRessources() {
		return ressources;
	}

	public void setRessources(ArrayList<String> ressources) {
		this.ressources = ressources;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<CommentBean> getComments() {
		return comments;
	}

	public void setComments(ArrayList<CommentBean> comments) {
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
