package dev.collegues.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.collegues.entite.Collegue;
import dev.collegues.service.CollegueService;

public class CollegueControllerTest {

	CollegueService mockedServ = Mockito.mock(CollegueService.class);
	private static final Logger LOG = LoggerFactory.getLogger(CollegueService.class);

	private CollegueService collegueService = new CollegueService();
	private CollegueController collegueController = new CollegueController();
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
	}

	public void testRecupMatricules() {
		Mockito.when(mockedServ.rechercherParNom("fefuie")).thenReturn(Arrays.asList(collegue));

		List<String> recupMatricules = collegueController.recupMatricules("fefuie");

		Assert.assertTrue(recupMatricules.contains("ss"));

		Mockito.verify(mockedServ).rechercherParNom("fefuie");
	}

}
