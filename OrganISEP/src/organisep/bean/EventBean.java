package organisep.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventBean {
	private ArrayList<String> salles;
	private Date date;
	private String titre;
	private String creat;
	private String image;
	private String imCreat;
	private String description;
	
	private int validation;
	private int statut;
	private int etat;
		
	public EventBean(String titreEvent, Date dateEvent, String imEvent, ArrayList<String> sallesEvent, String creatEvent, String imCreatEvent, int valEvent, int statutEvent, String descriptionEvent) {
		salles = sallesEvent;
		date = dateEvent;
		titre = titreEvent;
		creat = creatEvent;
		image = imEvent;
		imCreat = imCreatEvent;
		description = descriptionEvent;
		
		validation = valEvent;
		statut = statutEvent;
		
		Date auj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (date.compareTo(auj) > 0) {
            etat = 1;
        }
		else {
			etat = 0;
		}
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
