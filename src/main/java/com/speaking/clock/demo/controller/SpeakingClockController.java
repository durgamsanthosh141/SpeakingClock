package com.speaking.clock.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.speaking.clock.demo.exception.InvalidInputException;
import com.speaking.clock.demo.service.SpeakingClockService;

@RestController
public class SpeakingClockController {

	@Autowired
	private SpeakingClockService speakingClockService;

	@GetMapping("/getCurrentTimeInWords")
	public ResponseEntity<String> getCurrentTimeInWords() {
		String currentTime = speakingClockService.getCurrentTimeInWords();
		return new ResponseEntity<String>(currentTime, HttpStatus.OK);

	}

	@GetMapping("/getMidDayOrNight/{time}")
	public ResponseEntity<String> getMidDayOrNight(@PathVariable("time") String time) {
		try {
			String day = speakingClockService.getMidDayOrNight(time);
			return new ResponseEntity<String>(day, HttpStatus.OK);
		} catch (InvalidInputException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
}
