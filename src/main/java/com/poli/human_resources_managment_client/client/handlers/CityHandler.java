package com.poli.human_resources_managment_client.client.handlers;

import java.util.Scanner;

/**
 * Handler class for city-related operations. This class provides methods to
 * handle different commands related to city operations like getting, inserting,
 * and updating city data.
 */
public class CityHandler {

	/**
	 * Scanner used to read user input from the console.
	 */
	private Scanner scanner;

	/**
	 * Constructor for CityHandler.
	 * 
	 * @param scanner Scanner object for reading user input.
	 */
	public CityHandler(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Handles the user command for city-related operations. Reads the operation
	 * type from the user and calls the appropriate method based on the command.
	 * 
	 * @return A string command for server processing.
	 */
	public String handleCommand() {
		System.out.print("Enter operation for City (Get, Insert or Update): ");
		String operation = scanner.nextLine().toUpperCase();

		switch (operation) {
		case "GET":
			return handleGet();
		case "INSERT":
			return handleInsert();
		case "UPDATE":
			return handleUpdate();
		default:
			return "Invalid operation for City";
		}
	}

	/**
	 * Handles the 'get' operation for a city. Requests the user to enter the city
	 * ID and constructs a command to retrieve city data.
	 * 
	 * @return A string command to get city data by ID.
	 */
	private String handleGet() {
		System.out.print("Enter City ID: ");
		return "CITY_GET_BY_ID " + scanner.nextLine();
	}

	/**
	 * Handles the 'insert' operation for a city. Requests the user to enter city
	 * name and country ID and constructs a command to insert new city data.
	 * 
	 * @return A string command to insert a new city.
	 */
	private String handleInsert() {
		System.out.print("Enter City Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Country ID for City: ");
		String countryId = scanner.nextLine();

		return "CITY_INSERT " + name + " " + countryId;
	}

	/**
	 * Handles the 'update' operation for a city. Requests the user to enter city
	 * ID, new city name, and new country ID, then constructs a command to update
	 * city data.
	 * 
	 * @return A string command to update an existing city.
	 */
	private String handleUpdate() {
		System.out.print("Enter City ID: ");
		String id = scanner.nextLine();

		System.out.print("Enter New City Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter New Country ID for City: ");
		String countryId = scanner.nextLine();

		return "CITY_UPDATE " + id + " " + name + " " + countryId;
	}
}
