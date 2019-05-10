package dev.collegues.repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.collegues.entite.Collegue;
import dev.collegues.service.CollegueService;

@Component
public class StartupDataInit {

	@Autowired
	CollegueService collegueService;

	// TODO
	@Autowired
	private PasswordEncoder passwordEncoder;

	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		Collegue collegue0 = new Collegue();
		collegue0.setMatricule("1");
		collegue0.setNom("Dupond");
		collegue0.setPrenoms("Jean");
		collegue0.setEmail("blabla@email.com");
		collegue0.setDateDeNaissance(LocalDate.of(1993, 11, 20));
		collegue0.setPhotoUrl("https://cdn.pixabay.com/photo/2014/04/03/10/32/businessman-310819__340.png");
		collegue0.setMotDePasse(passwordEncoder.encode("pass1"));
		collegue0.setRoles(Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
		collegueService.ajouterUnCollegue(collegue0);
		Collegue collegue1 = new Collegue();
		collegue1.setNom("Dupond");
		collegue1.setPrenoms("Jacque");
		collegue1.setMatricule(UUID.randomUUID().toString());
		collegue1.setEmail("fefklfbn@email.com");
		collegue1.setDateDeNaissance(LocalDate.of(1874, 8, 14));
		collegue1.setPhotoUrl("https://cdn.pixabay.com/photo/2014/04/03/10/32/businessman-310819__340.png");
		collegue1.setMotDePasse(passwordEncoder.encode("pass2"));
		collegue1.setRoles(Arrays.asList("ROLE_USER"));
		collegueService.ajouterUnCollegue(collegue1);

	}
}