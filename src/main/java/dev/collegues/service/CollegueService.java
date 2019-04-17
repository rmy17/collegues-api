package dev.collegues.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dev.collegues.entite.Collegue;

public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {
		// TODO alimenter data avec des données fictives
		// Pour générer un matricule : `UUID.randomUUID().toString()`

		Collegue collegue0 = new Collegue();
		collegue0.setNom("Dupond");
		collegue0.setPrenoms("Jean");
		collegue0.setMatricule(UUID.randomUUID().toString());
		collegue0.setEmail("blabla@email.com");
		collegue0.setDateDeNaissance("1900-11-25");
		collegue0.setPhotoUrl("https://pixabay.com/fr/photos/notre-dame-l-architecture-eglise-3672868/");
		data.put(collegue0.getNom(), collegue0);
		Collegue collegue1 = new Collegue();
		collegue1.setNom("Cartier");
		collegue1.setPrenoms("Jacque");
		collegue1.setMatricule(UUID.randomUUID().toString());
		collegue1.setEmail("fefklfbn@email.com");
		collegue1.setDateDeNaissance("1874-08-14");
		collegue1.setPhotoUrl("https://pixabay.com/fr/photos/notre-dame-l-architecture-eglise-3672868/");
		data.put(collegue1.getNom(), collegue1);
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		List<Collegue> collegues = new ArrayList<>();
		collegues.add(data.get(nomRecherche));
		return collegues;
	}
}
