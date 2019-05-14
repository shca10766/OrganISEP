package organisep.bean;

import java.util.ArrayList;
import java.util.Date;

public class EventBean {
	private ArrayList<String> salles;
	private Date date;
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
		
	public EventBean(String titreEvent, Date dateEvent, String imEvent, ArrayList<String> sallesEvent, String creatEvent, String imCreatEvent, int valEvent, int statutEvent) {
		salles = sallesEvent;
		date = dateEvent;
		titre = titreEvent;
		creat = creatEvent;
		image = imEvent;
		imCreat = imCreatEvent;
		
		validation = valEvent;
		statut = statutEvent;
		
		Date auj = new Date();

		if (date.compareTo(auj) > 0) {
            etat = 1;
        }
		else {
			etat = 0;
		}
	}
	
	public EventBean(String titreEvent, Date dateEvent, String imEvent, 
					ArrayList<String> sallesEvent, String creatEvent, String imCreatEvent, int participantsEvent, 
					int budgetEvent, String lienEvent, ArrayList<String> ressourcesEvent, String descEvent) {
		
		salles = sallesEvent;
		date = dateEvent;
		titre = titreEvent;
		creat = creatEvent;
		image = imEvent;
		imCreat = imCreatEvent;
		
		validation = 0;
		statut = 1;
		
		participants = participantsEvent;
		budget = budgetEvent;
		lien = lienEvent;
		ressources = ressourcesEvent;
		description = descEvent;
	}

	public ArrayList<String> getSalles() {
		return salles;
	}

	public void setSalles(ArrayList<String> salles) {
		this.salles = salles;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
}
