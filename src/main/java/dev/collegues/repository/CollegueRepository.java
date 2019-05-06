package dev.collegues.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.collegues.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String> {

	Collegue findByMatricule(String matricule);

	List<Collegue> findByNom(String nom);

	List<Collegue> findByEmail(String email);

}
