package dev.collegues.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.collegues.entite.Note;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.repository.NoteRepository;

/**
 * @author rmy17
 *
 */
@Service
public class NoteService {

	@Autowired
	private NoteRepository texteRepository;

	@Autowired
	private CollegueService colServ;

	public List<Note> recupNotes(String matricule) {
		return texteRepository.findAllByMatricule(matricule).stream().collect(Collectors.toList());
	}

	/**
	 * @param collegueAAjouter
	 * @return Collegue
	 * @throws CollegueInvalideException
	 */
	public Note ajouterUneNote(Note noteAAjouter) throws CollegueInvalideException {
		texteRepository.save(noteAAjouter);
		return noteAAjouter;
	}

	public Object addUneNote(String matricule, String text) {

		Note note = new Note();
		note.setDate(LocalDateTime.now());
		note.setText(text);
		note.setCollegue(colServ.rechercherParMatricule(matricule));
		texteRepository.save(note);
		return note;
	}

}
