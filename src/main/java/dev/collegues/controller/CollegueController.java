package dev.collegues.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.collegues.entite.Collegue;
import dev.collegues.service.CollegueService;

@RestController
@RequestMapping("/collegues")
public class CollegueController {

	@GetMapping
	public List<String> RecupMatricules(@RequestParam("nom") String nom) {
		List<String> matricules = new ArrayList<>();
		CollegueService colServ = new CollegueService();
		for (Collegue c : colServ.rechercherParNom(nom)) {
			matricules.add(c.getMatricule());
		}
		return matricules;
	}

}
