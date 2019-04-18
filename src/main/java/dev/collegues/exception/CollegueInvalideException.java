package dev.collegues.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollegueInvalideException extends RuntimeException {

	private static final Logger LOG = LoggerFactory.getLogger(CollegueInvalideException.class);
	static String msg;

	public CollegueInvalideException(String msg) {
		this.msg = msg;
		LOG.error(msg);
	}
}
