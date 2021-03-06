package dev.collegues.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.collegues.entite.Collegue;
import dev.collegues.entite.CollegueConnecte;
import dev.collegues.entite.ColleguePhoto;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.exception.CollegueNonTrouveException;
import dev.collegues.repository.CollegueRepository;
import dev.collegues.repository.UtilisateurRepository;

/**
 * @author rmy17
 *
 */
@Service
public class CollegueService {

	@Autowired
	CollegueRepository collegueRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	/**
	 * @param nomRecherche
	 * @return Liste de collegues
	 * @throws CollegueNonTrouveException
	 */
	public List<Collegue> rechercherParNom(String nomRecherche) throws CollegueNonTrouveException {
		if (!collegueRepository.findByNom(nomRecherche).isEmpty()) {
			return collegueRepository.findByNom(nomRecherche);
		} else {
			throw new CollegueNonTrouveException();
		}
	}

	/**
	 * @param matriculeRecherche
	 * @return Collegue
	 * @throws CollegueNonTrouveException
	 */
	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
		Optional<Collegue> optColl = collegueRepository.findById(matriculeRecherche);
		if (optColl.isPresent()) {
			return optColl.get();
		} else {
			throw new CollegueNonTrouveException();
		}
	}

	/**
	 * @param existEmail
	 * @return Boolean
	 */
	public boolean existEmail(String email) {
		if (!collegueRepository.findByEmail(email).isEmpty()) {
			return true;
		}
		return false;
	}

	public List<ColleguePhoto> recupCollePhoto() {
		return collegueRepository.findAll().stream()
				.map(col -> new ColleguePhoto(col.getMatricule(), col.getPhotoUrl())).collect(Collectors.toList());
	}

	/**
	 * @param collegueAAjouter
	 * @return Collegue
	 * @throws CollegueInvalideException
	 */
	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {
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
			throw new CollegueInvalideException("L'age doit êtresupérieur à 18 ans");
		} else {
			collegueAAjouter.setMatricule(UUID.randomUUID().toString());
			collegueRepository.save(collegueAAjouter);
			return collegueAAjouter;
		}
	}

	/**
	 * @param matricule
	 * @param email
	 * @return Collegue
	 * @throws CollegueInvalideException
	 */
	@Transactional
	public Collegue modifierEmail(String matricule, String email) {
		Collegue collegue1 = rechercherParMatricule(matricule);
		if (!email.contains("@")) {
			throw new CollegueInvalideException("L'email ne contient pas de @");
		}
		if (email.substring(0, email.indexOf("@")).length() < 3) {
			throw new CollegueInvalideException("L'email doit contenir plus de 3 caractères avant le @ ");
		}
		collegue1.setEmail(email);
		return collegue1;
	}

	// Transctional permet de save automatiquement
	/**
	 * @param matricule
	 * @param photoUrl
	 * @return Collegue
	 * @throws CollegueInvalideException
	 */
	@Transactional
	public Collegue modifierPhotoUrl(String matricule, String photoUrl) {
		Collegue collegue1 = rechercherParMatricule(matricule);
		if (!photoUrl.startsWith("http")) {
			throw new CollegueInvalideException("L'url doit commencer par 'http'");
		}
		collegue1.setPhotoUrl(photoUrl);
		return collegue1;
	}

	/**
	 * @return the collegueRepository
	 */
	public CollegueRepository getCollegueRepository() {
		return collegueRepository;
	}

	/**
	 * @param collegueRepository the collegueRepository to set
	 */
	public void setCollegueRepository(CollegueRepository collegueRepository) {
		this.collegueRepository = collegueRepository;
	}

	public CollegueConnecte recupCollegueActif(String email) {
		CollegueConnecte colConnect = new CollegueConnecte();
		Collegue CollegueTrouve = this.utilisateurRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
		colConnect.setEmail(CollegueTrouve.getEmail());
		colConnect.setNom(CollegueTrouve.getNom());
		colConnect.setPrenom(CollegueTrouve.getPrenoms());
		colConnect.setRoles(CollegueTrouve.getRoles());
		colConnect.setPhotoUrl(CollegueTrouve.getPhotoUrl());
		return colConnect;
	}

}
