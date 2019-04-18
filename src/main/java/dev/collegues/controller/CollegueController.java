package dev.collegues.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.collegues.entite.Collegue;
import dev.collegues.exception.CollegueInvalideException;
import dev.collegues.service.CollegueService;

@RestController
@RequestMapping("/collegues")
public class CollegueController {
	private static final Logger LOG = LoggerFactory.getLogger(CollegueInvalideException.class);
	private CollegueService colServ = new CollegueService();

	@GetMapping
	public List<String> recupMatricules(@RequestParam("nom") String nom) {
		List<String> matricules = new ArrayList<>();

		for (Collegue c : colServ.rechercherParNom(nom)) {
			matricules.add(c.getMatricule());
		}
		return matricules;
	}

	@RequestMapping(path = "/{matricule}", method = RequestMethod.GET)
	@ResponseBody
	public Collegue recupInformation(@PathVariable String matricule) {

		return colServ.rechercherParMatricule(matricule);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Collegue collegue) {
		Collegue collegue1 = colServ.ajouterUnCollegue(collegue);
		return ResponseEntity.status(HttpStatus.OK).body(collegue1);
	}

}
