package dev.collegues.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.collegues.entite.Collegue;

public interface UtilisateurRepository extends JpaRepository<Collegue, String> {

	Optional<Collegue> findByMatricule(String matricule);
}
