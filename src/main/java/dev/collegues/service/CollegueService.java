package dev.collegues.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.exception.RetourException;

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
		collegue0.setDateDeNaissance(LocalDate.of(1993, 11, 20));
		collegue0.setPhotoUrl("https://pixabay.com/fr/photos/notre-dame-l-architecture-eglise-3672868/");
		data.put(collegue0.getMatricule(), collegue0);
		Collegue collegue1 = new Collegue();
		collegue1.setNom("Dupond");
		collegue1.setPrenoms("Jacque");
		collegue1.setMatricule(UUID.randomUUID().toString());
		collegue1.setEmail("fefklfbn@email.com");
		collegue1.setDateDeNaissance(LocalDate.of(1874, 8, 14));
		collegue1.setPhotoUrl("https://pixabay.com/fr/photos/notre-dame-l-architecture-eglise-3672868/");
		data.put(collegue1.getMatricule(), collegue1);
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		return this.data.values().stream().filter(col -> col.getNom().equals(nomRecherche))
				.collect(Collectors.toList());
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws RetourException {
		return Optional.ofNullable(data.get(matriculeRecherche)).orElseThrow(RetourException::new);
	}

	public void ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {
		if (collegueAAjouter.getNom().length() < 2) {
			throw new CollegueInvalideException("Le nom inférieur à 2 caractères");
		}
		if (collegueAAjouter.getPrenoms().length() < 2) {
			throw new CollegueInvalideException("Prenom doit avoir plus de 2 caractères");
		}
		if (!collegueAAjouter.getEmail().contains("@")) {
			throw new CollegueInvalideException("L'email ne contient pas de @");
		}
		if (collegueAAjouter.getEmail().substring(0, collegueAAjouter.getEmail().indexOf("@")).length() < 3) {
			throw new CollegueInvalideException("L'email doit contenir plus de 3 caractères avnt le @ ");
		}
		if (!collegueAAjouter.getPhotoUrl().startsWith("http")) {
			throw new CollegueInvalideException("L'url doit commencer par 'http'");
		}
		if (LocalDate.now().getYear() - collegueAAjouter.getDateDeNaissance().getYear() < 18) {
			throw new CollegueInvalideException("Vous devez avoir plus de  18 ans'");
		} else {
			collegueAAjouter.setMatricule(UUID.randomUUID().toString());
			data.put(collegueAAjouter.getMatricule(), collegueAAjouter);
		}
	}

}
