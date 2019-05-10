package dev.collegues.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.collegues.entite.Note;
import dev.collegues.entite.NoteSansCollegue;
import dev.collegues.service.NoteService;

@RestController
@RequestMapping("/collegues")
public class NoteController {

	@Autowired
	private NoteService noteServ;

	// GET /collegues?matricule=xxxx
	@GetMapping("/{matricule}/notes")
	public List<Note> recupNote(@PathVariable String matricule) {
		return noteServ.recupNotes(matricule);
	}

	@PostMapping("/{matricule}/notes")
	public ResponseEntity<Object> ajouterUneNote(@PathVariable String matricule,
			@RequestBody NoteSansCollegue noteSansCollegue) {
		return ResponseEntity.status(HttpStatus.OK).body(noteServ.addUneNote(matricule, noteSansCollegue.getText()));
	}

}
