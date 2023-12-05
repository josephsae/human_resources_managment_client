package com.poli.human_resources_managment_client.client.handlers;

import java.util.Scanner;

/**
 * Handler class for historic-related operations. This class provides methods to
 * handle different commands related to historic operations like getting
 * historic data.
 */
public class HistoricHandler {

	/**
	 * Scanner used to read user input from the console.
	 */
	private Scanner scanner;

	/**
	 * Constructor for HistoricHandler.
	 * 
	 * @param scanner Scanner object for reading user input.
	 */
	public HistoricHandler(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Handles the user command for historic-related operations. Reads the operation
	 * type from the user and calls the appropriate method based on the command.
	 * 
	 * @return A string command for server processing.
	 */
	public String handleCommand() {
		System.out.print("Enter operation for Historic (Get): ");
		String operation = scanner.nextLine().toUpperCase();

		switch (operation) {
		case "GET":
			return handleGet();
		default:
			return "Invalid operation for Historic";
		}
	}

	/**
	 * Handles the 'get' operation for historic data. Requests the user to enter the
	 * historic ID and constructs a command to retrieve historic data.
	 * 
	 * @return A string command to get historic data by ID.
	 */
	private String handleGet() {
		System.out.print("Enter Historic ID: ");
		return "HISTORIC_GET_BY_ID " + scanner.nextLine();
	}
}
