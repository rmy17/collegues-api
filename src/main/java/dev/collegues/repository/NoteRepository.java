package dev.collegues.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.collegues.entite.Note;

public interface NoteRepository extends JpaRepository<Note, String> {

	@Query("select n from Note n where n.collegue.matricule = :matricule")
	List<Note> findAllByMatricule(@Param("matricule") String matricule);

}
