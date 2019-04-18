package dev.collegues.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	// la méthode handleConflict est exécutée lorsqu'un contrôleur émet une
	// exception présente
	// dans la liste définie par l'annotation @ExceptionHandler
	@ExceptionHandler(value = { RetourException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Collegue non trouvé";
		return ResponseEntity.status(404).body(bodyOfResponse);
	}

	@ExceptionHandler(value = { CollegueInvalideException.class })
	protected ResponseEntity<Object> handleConflict2(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Collegue invalide : " + CollegueInvalideException.msg;
		return ResponseEntity.status(404).body(bodyOfResponse);
	}
}