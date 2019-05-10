package dev.collegues.dto;

public class InfosAuthentification {

	private String email;
	private String motDePasse;

	/**
	 * @return the adresseMail
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param adresseMail the adresseMail to set
	 */
	public void setEmail(String adresseMail) {
		this.email = adresseMail;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

}
