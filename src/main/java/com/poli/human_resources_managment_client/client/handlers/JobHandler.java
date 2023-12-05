package com.poli.human_resources_managment_client.client.handlers;

import java.util.Scanner;

/**
 * Handler class for job-related operations. This class provides methods to
 * handle different commands related to job operations like getting, inserting,
 * and updating job data.
 */
public class JobHandler {

	/**
	 * Scanner used to read user input from the console.
	 */
	private Scanner scanner;

	/**
	 * Constructor for JobHandler.
	 * 
	 * @param scanner Scanner object for reading user input.
	 */
	public JobHandler(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Handles the user command for job-related operations. Reads the operation type
	 * from the user and calls the appropriate method based on the command.
	 * 
	 * @return A string command for server processing.
	 */
	public String handleCommand() {
		System.out.print("Enter operation for Job (Get, Insert or Update): ");
		String operation = scanner.nextLine().toUpperCase();

		switch (operation) {
		case "GET":
			return handleGet();
		case "INSERT":
			return handleInsert();
		case "UPDATE":
			return handleUpdate();
		default:
			return "Invalid operation for Job";
		}
	}

	/**
	 * Handles the 'get' operation for a job. Requests the user to enter the job ID
	 * and constructs a command to retrieve job data.
	 * 
	 * @return A string command to get job data by ID.
	 */
	private String handleGet() {
		System.out.print("Enter Job ID: ");
		return "JOB_GET_BY_ID " + scanner.nextLine();
	}

	/**
	 * Handles the 'insert' operation for a job. Requests the user to enter job
	 * name, minimum salary, and maximum salary, and constructs a command to insert
	 * new job data.
	 * 
	 * @return A string command to insert a new job.
	 */
	private String handleInsert() {
		System.out.print("Enter Job Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Minimum Salary: ");
		String minSalary = scanner.nextLine();

		System.out.print("Enter Maximum Salary: ");
		String maxSalary = scanner.nextLine();

		return "JOB_INSERT " + name + " " + minSalary + " " + maxSalary;
	}

	/**
	 * Handles the 'update' operation for a job. Requests the user to enter job ID,
	 * new job name, new minimum salary, and new maximum salary, then constructs a
	 * command to update job data.
	 * 
	 * @return A string command to update an existing job.
	 */
	private String handleUpdate() {
		System.out.print("Enter Job ID: ");
		String id = scanner.nextLine();

		System.out.print("Enter New Job Name: ");
		String newName = scanner.nextLine();

		System.out.print("Enter New Minimum Salary: ");
		String newMinSalary = scanner.nextLine();

		System.out.print("Enter New Maximum Salary: ");
		String newMaxSalary = scanner.nextLine();

		return "JOB_UPDATE " + id + " " + newName + " " + newMinSalary + " " + newMaxSalary;
	}
}
