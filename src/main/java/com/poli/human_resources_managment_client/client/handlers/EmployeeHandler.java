package com.poli.human_resources_managment_client.client.handlers;

import java.util.Scanner;

/**
 * Handler class for employee-related operations. This class provides methods to
 * handle different commands related to employee operations such as getting,
 * inserting, updating, removing, and relocating employee data.
 */
public class EmployeeHandler {

	/**
	 * Scanner used to read user input from the console.
	 */
	private Scanner scanner;

	/**
	 * Constructor for EmployeeHandler.
	 * 
	 * @param scanner Scanner object for reading user input.
	 */
	public EmployeeHandler(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Handles the user command for employee-related operations. Reads the operation
	 * type from the user and calls the appropriate method based on the command.
	 * 
	 * @return A string command for server processing.
	 */
	public String handleCommand() {
		System.out.print("Enter operation for Employee (Get, Insert, Update, Remove or Relocate): ");
		String operation = scanner.nextLine().toUpperCase();

		switch (operation) {
			case "GET":
				return handleGet();
			case "INSERT":
				return handleInsert();
			case "UPDATE":
				return handleUpdate();
			case "REMOVE":
				return handleRemove();
			case "RELOCATE":
				return handleRelocate();
			default:
				return "Invalid operation for Employee";
		}
	}

	/**
	 * Handles the 'get' operation for an employee. Requests the user to enter the
	 * employee ID and constructs a command to retrieve employee data.
	 * 
	 * @return A string command to get employee data by ID.
	 */
	private String handleGet() {
		System.out.print("Enter Employee ID: ");
		return "EMPLOYEE_GET_BY_ID " + scanner.nextLine();
	}

	/**
	 * Handles the 'insert' operation for an employee. Constructs a command to
	 * insert new employee data based on user input.
	 * 
	 * @return A string command to insert a new employee.
	 */
	private String handleInsert() {
		return "EMPLOYEE_INSERT " + getEmployeeData(scanner, false);
	}

	/**
	 * Handles the 'update' operation for an employee. Constructs a command to
	 * update existing employee data based on user input.
	 * 
	 * @return A string command to update an existing employee.
	 */
	private String handleUpdate() {
		return "EMPLOYEE_UPDATE " + getEmployeeData(scanner, true);
	}

	/**
	 * Handles the 'remove' operation for an employee. Requests the user to enter
	 * the employee ID and constructs a command to remove the specified employee.
	 * 
	 * @return A string command to remove an employee.
	 */
	private String handleRemove() {
		System.out.print("Enter Employee ID: ");
		return "EMPLOYEE_REMOVE " + scanner.nextLine();
	}

	/**
	 * Handles the 'relocate' operation for an employee. Requests the user to enter
	 * relevant details for relocating the employee and constructs a command to
	 * execute the relocation.
	 * 
	 * @return A string command to relocate an employee.
	 */
	private String handleRelocate() {
		System.out.print("Enter Employee ID: ");
		String employeeId = scanner.nextLine();

		System.out.print("Enter City Name: ");
		String cityName = scanner.nextLine();

		System.out.print("Enter Location Address: ");
		String locationAddress = "\"" + scanner.nextLine() + "\"";

		System.out.print("Enter Department Name: ");
		String departmentName = scanner.nextLine();

		return "EMPLOYEE_RELOCATE " + employeeId + " " + cityName + " " + locationAddress + departmentName;
	}

	/**
	 * Constructs a string with employee data based on user input. This method is
	 * used for both inserting and updating employee data.
	 * 
	 * @param scanner  Scanner object for reading user input.
	 * @param isUpdate Boolean flag to determine if it's an update operation.
	 * @return A string containing employee data.
	 */
	private static String getEmployeeData(Scanner scanner, boolean isUpdate) {
		StringBuilder sb = new StringBuilder();

		if (isUpdate) {
			System.out.print("Enter Employee ID: ");
			sb.append(scanner.nextLine()).append(" ");
		}

		System.out.print("Enter First Name: ");
		sb.append(scanner.nextLine()).append(" ");

		System.out.print("Enter Second Name or 'null' if not applicable: ");
		String secondName = scanner.nextLine();
		sb.append("null".equalsIgnoreCase(secondName) ? "null" : secondName).append(" ");

		System.out.print("Enter Email: ");
		sb.append(scanner.nextLine()).append(" ");

		System.out.print("Enter Birth Date (YYYY-MM-DD): ");
		sb.append(scanner.nextLine()).append(" ");

		System.out.print("Enter Salary: ");
		sb.append(scanner.nextLine()).append(" ");

		System.out.print("Enter Commission or 'null' if not applicable: ");
		String commission = scanner.nextLine();
		sb.append("null".equalsIgnoreCase(commission) ? "null" : commission).append(" ");

		System.out.print("Enter Position ID: ");
		sb.append(scanner.nextLine()).append(" ");

		System.out.print("Enter Manager ID or 'null' if not applicable: ");
		String managerId = scanner.nextLine();
		sb.append("null".equalsIgnoreCase(managerId) ? "null" : managerId).append(" ");

		System.out.print("Enter Department ID: ");
		sb.append(scanner.nextLine());

		return sb.toString().trim();
	}
}
