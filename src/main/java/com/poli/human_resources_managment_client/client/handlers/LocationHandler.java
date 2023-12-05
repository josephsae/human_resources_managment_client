package com.poli.human_resources_managment_client.client.handlers;

import java.util.Scanner;

/**
 * Handler class for location-related operations. This class provides methods to
 * handle different commands related to location operations such as getting,
 * inserting, and updating location data.
 */
public class LocationHandler {

	/**
	 * Scanner used to read user input from the console.
	 */
	private Scanner scanner;

	/**
	 * Constructor for LocationHandler.
	 * 
	 * @param scanner Scanner object for reading user input.
	 */
	public LocationHandler(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Handles the user command for location-related operations. Reads the operation
	 * type from the user and calls the appropriate method based on the command.
	 * 
	 * @return A string command for server processing.
	 */
	public String handleCommand() {
		System.out.print("Enter operation for Location (Get, Insert or Update): ");
		String operation = scanner.nextLine().toUpperCase();

		switch (operation) {
		case "GET":
			return handleGet();
		case "INSERT":
			return handleInsert();
		case "UPDATE":
			return handleUpdate();
		default:
			return "Invalid operation for Location";
		}
	}

	/**
	 * Handles the 'get' operation for a location. Requests the user to enter the
	 * location ID and constructs a command to retrieve location data.
	 * 
	 * @return A string command to get location data by ID.
	 */
	private String handleGet() {
		System.out.print("Enter Location ID: ");
		return "LOCATION_GET_BY_ID " + scanner.nextLine();
	}

	/**
	 * Handles the 'insert' operation for a location. Requests the user to enter
	 * location address and city ID, and constructs a command to insert new location
	 * data.
	 * 
	 * @return A string command to insert a new location.
	 */
	private String handleInsert() {
		System.out.print("Enter Location Address: ");
		String address = scanner.nextLine();

		System.out.print("Enter City ID for Location: ");
		String cityId = scanner.nextLine();

		return "LOCATION_INSERT \"" + address + "\" " + cityId;
	}

	/**
	 * Handles the 'update' operation for a location. Requests the user to enter
	 * location ID, new location address, and new city ID, then constructs a command
	 * to update location data.
	 * 
	 * @return A string command to update an existing location.
	 */
	private String handleUpdate() {
		System.out.print("Enter Location ID: ");
		String id = scanner.nextLine();

		System.out.print("Enter New Location Address: ");
		String newAddress = scanner.nextLine();

		System.out.print("Enter New City ID for Location: ");
		String newCityId = scanner.nextLine();

		return "LOCATION_UPDATE " + id + " \"" + newAddress + "\" " + newCityId;
	}
}
