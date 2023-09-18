package com.speaking.clock.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.speaking.clock.demo.exception.InvalidInputException;

@Service
public class SpeakingClockService {

	private static final String[] HOUR = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve" };

	private static final String[] TEENS = { "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
			"seventeen", "eighteen", "nineteen" };

	private static final String[] TENS = { "", "", "twenty", "thirty", "forty", "fifty" };

	public String getCurrentTimeInWords() {

		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String formattedDate = dateFormat.format(date);

		String[] currentTime = formattedDate.split(":");
		int hour, minute;

		hour = Integer.parseInt(currentTime[0]);
		minute = Integer.parseInt(currentTime[1]);

		if (hour != 12)
			hour = hour % 12;

		String ans = null;

		if (minute == 0) {
			ans = "It's " + HOUR[hour] + " o'clock";
		} else if (minute == 30) {
			ans = "It's " + HOUR[hour] + "thirty";
		} else if (minute <= 10) {
			ans = "It's " + HOUR[hour] + " " + HOUR[minute];
		} else if (minute < 20) {
			ans = "It's " + HOUR[hour] + " " + TEENS[minute - 11];
		} else {
			int tensDigit = minute / 10;
			int onesDigit = minute % 10;
			if (onesDigit != 0)
				ans = "It's " + HOUR[hour] + " " + TENS[tensDigit] + " " + HOUR[onesDigit];
			else
				ans = "It's " + HOUR[hour] + " " + TENS[tensDigit];

		}

		return ans;
	}

	public String getMidDayOrNight(String inputTime) {

		try {
			String[] time = inputTime.split(":");
			int hours = Integer.parseInt(time[0]);
			int minutes = Integer.parseInt(time[1]);

			if (hours > 23)
				throw new InvalidInputException("Invalid hour, shouldn't be greater than 23");

			if (minutes > 59)
				throw new InvalidInputException("Invalid minutes, shouldn't be greater than 59");

			if (hours == 0 && minutes == 0) {
				return "It's Midnight";
			}

			if (hours == 12 && minutes == 0) {
				return "It's Midday";
			}
			throw new InvalidInputException(
					"Invalid input, Please enter either '12:00' for Midday or '00:00' for Midnight");

		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new InvalidInputException("Invalid input format, Please use the format 'HH:MM'");
		}

	}

}
