package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

/**
 * A pair of commands which are used to take yes/no decisions.
 *
 */
public enum YesNo {
	Y("Yes"),
	N("No");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	YesNo(String description) {
		this.description = description;
	}

	/**
	 * Describes the action
	 */
	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	/**
	 * Prints out both possible actions
	 */
	public static void printYesNo() {
		for (YesNo yn : YesNo.values()) {
			LOGGER.info(yn.getDescription());
		}
	}

	/**
	 * Gets a yes/no response based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 *
	 *
	 * @return boolean - true for Y: yes and false for N: no
	 */
	public static boolean getYesNo(Utils utils) {
		YesNo yn = null;
		do {
			try {
				yn = YesNo.valueOf(utils.getString().stripLeading().substring(0, 1).toUpperCase());
			} catch (IllegalArgumentException | IndexOutOfBoundsException e) {
				LOGGER.error("Invalid selection please try again");
			}
		} while (yn == null);
		return (yn == Y);
	}

}
