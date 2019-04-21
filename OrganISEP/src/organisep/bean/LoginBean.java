package organisep.bean;

public class LoginBean {
	private String email;
	private String mdp;
	private String nom;
	private int statut;
	private String image;
	
	public LoginBean(String email, String mdp) {
		this.email = email;
		this.mdp = mdp;
	}
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getMdp() { return mdp; }
	public void setMdp(String mdp) { this.mdp = mdp; }
	
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	
	public int getStatut() { return statut; }
	public void setStatut(int statut) { this.statut = statut; }
	
	public String getImage() { return image; }
	public void setImage(String image) { this.image = image; }
}
