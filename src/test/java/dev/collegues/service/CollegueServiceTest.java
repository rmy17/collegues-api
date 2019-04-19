package dev.collegues.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.exception.CollegueNonTrouveException;
import dev.collegues.repository.CollegueRepository;

public class CollegueServiceTest {

	CollegueRepository mockedRepo = Mockito.mock(CollegueRepository.class);
	private static final Logger LOG = LoggerFactory.getLogger(CollegueService.class);

	private CollegueService collegueService = new CollegueService();

	private Collegue collegue = new Collegue();

	@Before
	public void init() {
		// collegueService = new CollegueService();
		collegue.setPrenoms("Jean");
		collegue.setNom("Dupond");
		collegue.setMatricule("1");
		collegue.setEmail("blabla@email.com");
		collegue.setDateDeNaissance(LocalDate.of(1993, 11, 20));
		collegue.setPhotoUrl("https://pixabay.com/fr/photos/notre-dame-l-architecture-eglise-3672868/");
		collegueService.setCollegueRepository(mockedRepo);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_prenom_inferieur_2_caracteres() {
		LOG.info("Losrque que je sauvegarde un collegue avec un prenom inférieur à 2 caractères");
		LOG.info("Alors une exception Collegue est lancée");

		collegue.setPrenoms("L");
		collegueService.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_nom_inferieur_2_caracteres() {
		LOG.info("Losrque que je sauvegarde un collegue avec un nom inférieur à 2 caractères");
		LOG.info("Alors une exception Collegue est lancée");

		collegue.setNom("D");
		collegueService.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_email_inferieur_3_caracteres() {
		LOG.info("Losrque que je sauvegarde un collegue avec un email inférieur à 3 caractères");
		LOG.info("Alors une exception Collegue est lancée");
		collegue.setEmail("bl@email.com");
		collegueService.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_email_contient_pas_arobase() {
		LOG.info("Losrque que je sauvegarde un collegue avec un email sans arobase");
		LOG.info("Alors une exception Collegue est lancée");
		collegue.setEmail("blabla.email.com");
		collegueService.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_photoUrl_commence_par_http() {
		LOG.info("Losrque que je sauvegarde un collegue avec un email inférieur à 3 caractères");
		LOG.info("Alors une exception Collegue est lancée");
		collegue.setPhotoUrl("//pixabay.com/fr/photos/notre-dame-l-architecture-eglise-3672868/");
		collegueService.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_age_inferieur_a_18() {
		LOG.info("Losrque que je sauvegarde un collegue avec un age inférieur à 18 ans");
		LOG.info("Alors une exception Collegue est lancée");
		collegue.setDateDeNaissance(LocalDate.of(2017, 11, 20));
		collegueService.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void modifierEmail_email_contient_pas_arobase() {
		LOG.info("Losrque que je sauvegarde un collegue avec un email sans aroase");
		LOG.info("Alors une exception Collegue est lancée");
		collegue.setEmail("blabla.email.com");
		Mockito.when(mockedRepo.findById(collegue.getMatricule())).thenReturn(Optional.of(collegue));
		collegueService.modifierEmail(collegue.getMatricule(), collegue.getEmail());
	}

	@Test(expected = CollegueInvalideException.class)
	public void modifierEmail_email_inferieur_3_caracteres() {
		LOG.info("Losrque que je sauvegarde un collegue avec un email inférieur à 3 caractères");
		LOG.info("Alors une exception Collegue est lancée");
		collegue.setEmail("bl@email.com");
		Mockito.when(mockedRepo.findById(collegue.getMatricule())).thenReturn(Optional.of(collegue));
		collegueService.modifierEmail(collegue.getMatricule(), collegue.getEmail());
	}

	@Test(expected = CollegueInvalideException.class)
	public void modifierPhotoUrl_url_sans_http() {
		LOG.info("Losrque que je sauvegarde un collegue avec un email inférieur à 3 caractères");
		LOG.info("Alors une exception Collegue est lancée");
		collegue.setPhotoUrl("efefhefuief");
		Mockito.when(mockedRepo.findById(collegue.getMatricule())).thenReturn(Optional.of(collegue));
		collegueService.modifierPhotoUrl(collegue.getMatricule(), collegue.getPhotoUrl());
	}

	@Test(expected = CollegueNonTrouveException.class)
	public void modifierPhotoUrl_mauvais_matricule() {
		LOG.info("Losrque que je sauvegarde un collegue avec un mauvais matricule");
		LOG.info("Alors une exception Collegue non trouvé est lancée");
		Mockito.when(mockedRepo.findById("grghrghreg")).thenReturn(Optional.of(collegue));
	}

}
