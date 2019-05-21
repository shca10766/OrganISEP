package organisep.bean;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventBean {
	private ArrayList<String> salles;
	private ArrayList<CommentBean> comments;
<<<<<<< HEAD
	private Date date;
=======
	private String date;
	private String time;
>>>>>>> refs/remotes/origin/master
	private String titre;
	private String creat;
	private String image;
	private String imCreat;
	private String description;
	
	private int participants;
	private int budget;
	private String lien;
	private ArrayList<String> ressources;
	private String description;
	
	private int validation;
	private int statut;

	private int etat;
		
<<<<<<< HEAD
	public EventBean(String titreEvent, Date dateEvent, String imEvent, ArrayList<String> sallesEvent, String creatEvent, String imCreatEvent, int valEvent, int statutEvent, String descriptionEvent, ArrayList<CommentBean> commentsEvent) {
=======
	public EventBean(String titreEvent, Date dateEvent, Time timeEvent, String imEvent, ArrayList<String> sallesEvent, String creatEvent, String imCreatEvent, int valEvent, int statutEvent, String descriptionEvent, ArrayList<CommentBean> commentsEvent) {
>>>>>>> refs/remotes/origin/master
		salles = sallesEvent;
		comments = commentsEvent;
<<<<<<< HEAD
		date = dateEvent;
=======
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat tf = new SimpleDateFormat("HH:mm");
	    date = df.format(dateEvent);
		time = tf.format(timeEvent);
>>>>>>> refs/remotes/origin/master
		titre = titreEvent;
		creat = creatEvent;
		image = imEvent;
		imCreat = imCreatEvent;
		description = descriptionEvent;
		
		validation = valEvent;
		statut = statutEvent;
		
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

<<<<<<< HEAD
	
	public ArrayList<CommentBean> getComments() {
		return comments;
	}

	public void setComments(ArrayList<CommentBean> comments) {
		this.comments = comments;
	}

	public Date getDate() {
=======
	public String getDate() {
>>>>>>> refs/remotes/origin/master
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

<<<<<<< HEAD
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
=======
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
}
>>>>>>> refs/remotes/origin/master
