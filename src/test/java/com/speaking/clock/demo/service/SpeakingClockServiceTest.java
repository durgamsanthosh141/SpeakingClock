package com.speaking.clock.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.speaking.clock.demo.exception.InvalidInputException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class SpeakingClockServiceTest {

	@InjectMocks
	private SpeakingClockService speakingClockService;

	@Test
	public void testGetMidDayOrNight_Midnight() {
		String result = speakingClockService.getMidDayOrNight("00:00");
		assertEquals("It's Midnight", result);
	}

	@Test
	public void testGetMidDayOrNight_Midday() {
		String result = speakingClockService.getMidDayOrNight("12:00");
		assertEquals("It's Midday", result);
	}

	@Test
	public void testGetMidDayOrNight_InvaliTime() {
		InvalidInputException ex = assertThrows(InvalidInputException.class, () -> {
			speakingClockService.getMidDayOrNight("15:00");
		});
		assertEquals("Invalid input, Please enter either '12:00' for Midday or '00:00' for Midnight", ex.getMessage());

	}

	@Test
	public void testGetMidDayOrNight_InvalidFormat() {
		InvalidInputException ex = assertThrows(InvalidInputException.class, () -> {
			speakingClockService.getMidDayOrNight("22-00");
		});
		assertEquals("Invalid input format, Please use the format 'HH:MM'", ex.getMessage());
	}

	@Test
	public void testGetMidDayOrNight_InvalidHour() {
		InvalidInputException ex = assertThrows(InvalidInputException.class, () -> {
			speakingClockService.getMidDayOrNight("26:00");
		});
		assertEquals("Invalid hour, shouldn't be greater than 23", ex.getMessage());

	}

	@Test
	public void testGetMidDayOrNight_InvalidMinute() {
		InvalidInputException ex = assertThrows(InvalidInputException.class, () -> {
			speakingClockService.getMidDayOrNight("12:60");
		});
		assertEquals("Invalid minutes, shouldn't be greater than 59", ex.getMessage());

	}

}
