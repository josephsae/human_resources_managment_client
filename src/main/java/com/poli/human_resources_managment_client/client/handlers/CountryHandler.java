package com.poli.human_resources_managment_client.client.handlers;

import java.util.Scanner;

/**
 * Handler class for country-related operations. This class provides methods to
 * handle different commands related to country operations like getting,
 * inserting, and updating country data.
 */
public class CountryHandler {

	/**
	 * Scanner used to read user input from the console.
	 */
	private Scanner scanner;

	/**
	 * Constructor for CountryHandler.
	 * 
	 * @param scanner A Scanner object for reading user input.
	 */
	public CountryHandler(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Processes user commands for country operations. Accepts commands for getting,
	 * inserting, or updating a country.
	 * 
	 * @return A string command for server-side processing.
	 */
	public String handleCommand() {
		System.out.print("Enter operation for Country (Get, Insert or Update): ");
		String operation = scanner.nextLine().toUpperCase();

		switch (operation) {
		case "GET":
			return handleGet();
		case "INSERT":
			return handleInsert();
		case "UPDATE":
			return handleUpdate();
		default:
			return "Invalid operation for Country";
		}
	}

	/**
	 * Handles the 'get' operation for a country. Requests the user to enter a
	 * country ID and returns a command string.
	 * 
	 * @return A command string to get a country by ID.
	 */
	private String handleGet() {
		System.out.print("Enter Country ID: ");
		return "COUNTRY_GET_BY_ID " + scanner.nextLine();
	}

	/**
	 * Handles the 'insert' operation for a country. Requests the user to enter a
	 * country name and returns a command string.
	 * 
	 * @return A command string to insert a new country.
	 */
	private String handleInsert() {
		System.out.print("Enter Country Name: ");
		String name = scanner.nextLine();
		return "COUNTRY_INSERT " + name;
	}

	/**
	 * Handles the 'update' operation for a country. Requests the user to enter a
	 * country ID and new name, and returns a command string.
	 * 
	 * @return A command string to update an existing country.
	 */
	private String handleUpdate() {
		System.out.print("Enter Country ID: ");
		String id = scanner.nextLine();

		System.out.print("Enter New Country Name: ");
		String newName = scanner.nextLine();

		return "COUNTRY_UPDATE " + id + " " + newName;
	}

}
