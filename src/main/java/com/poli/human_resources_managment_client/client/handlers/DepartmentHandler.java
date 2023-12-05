package com.poli.human_resources_managment_client.client.handlers;

import java.util.Scanner;

/**
 * Handler class for department-related operations. This class provides methods to
 * handle different commands related to department operations such as getting, inserting,
 * and updating department data.
 */
public class DepartmentHandler {

	/**
	 * Scanner used to read user input from the console.
	 */
	private Scanner scanner;

	/**
	 * Handler class for department-related operations. This class provides methods to
	 * handle different commands related to department operations such as getting, inserting,
	 * and updating department data.
	 */
	public DepartmentHandler(Scanner scanner) {
		this.scanner = scanner;
	}


    /**
     * Handles the user command for department-related operations. Reads the operation
     * type from the user and calls the appropriate method based on the command.
     * 
     * @return A string command for server processing.
     */
	public String handleCommand() {
		System.out.print("Enter operation for Department (Get, Insert or Update): ");
		String operation = scanner.nextLine().toUpperCase();

		switch (operation) {
		case "GET":
			return handleGet();
		case "INSERT":
			return handleInsert();
		case "UPDATE":
			return handleUpdate();
		default:
			return "Invalid operation for Department";
		}
	}


    /**
     * Handles the 'get' operation for a department. Requests the user to enter the department
     * ID and constructs a command to retrieve department data.
     * 
     * @return A string command to get department data by ID.
     */
	private String handleGet() {
		System.out.print("Enter Department ID: ");
		return "DEPARTMENT_GET_BY_ID " + scanner.nextLine();
	}

    /**
     * Handles the 'insert' operation for a department. Requests the user to enter department
     * name and location ID and constructs a command to insert new department data.
     * 
     * @return A string command to insert a new department.
     */
	private String handleInsert() {
		System.out.print("Enter Department Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Location ID for Department: ");
		String locationId = scanner.nextLine();

		return "DEPARTMENT_INSERT " + name + " " + locationId;
	}

    /**
     * Handles the 'update' operation for a department. Requests the user to enter department
     * ID, new department name, and new location ID, then constructs a command to update
     * department data.
     * 
     * @return A string command to update an existing department.
     */
	private String handleUpdate() {
		System.out.print("Enter Department ID: ");
		String id = scanner.nextLine();

		System.out.print("Enter New Department Name: ");
		String newName = scanner.nextLine();

		System.out.print("Enter New Location ID for Department: ");
		String newLocationId = scanner.nextLine();

		return "DEPARTMENT_UPDATE " + id + " " + newName + " " + newLocationId;
	}

}
