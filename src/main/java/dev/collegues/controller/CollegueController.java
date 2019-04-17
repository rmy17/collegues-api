package dev.collegues.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.collegues.entite.Collegue;
import dev.collegues.service.CollegueService;

@RestController
@RequestMapping("/collegues")
public class CollegueController {

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

}
