package dev.collegues.service;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.collegues.entite.Collegue;
import dev.collegues.repository.UtilisateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UtilisateurRepository utilisateurRepository;

	public UserDetailsServiceImpl(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

	// cette méthode va permettre à Spring Security d'avoir accès
	// aux informations d'un utilisateur (mot de passe, roles) à partir
	// d'un matricule
	//
	// L'interface UserDetails détaille le contrat attendu par Spring Security.
	@Override
	public UserDetails loadUserByUsername(String matricule) throws UsernameNotFoundException {

		// Recherche d'utilisateur par nom utilisateur
		Collegue CollegueTrouve = this.utilisateurRepository.findByMatricule(matricule)
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

		// Création d'un objet User (implémentant UserDetails)
		return new User(CollegueTrouve.getEmail(), CollegueTrouve.getMotDePasse(),
				CollegueTrouve.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}
}
