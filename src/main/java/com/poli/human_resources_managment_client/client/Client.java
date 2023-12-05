package com.poli.human_resources_managment_client.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.poli.human_resources_managment_client.client.handlers.EmployeeHandler;
import com.poli.human_resources_managment_client.client.handlers.CountryHandler;
import com.poli.human_resources_managment_client.client.handlers.CityHandler;
import com.poli.human_resources_managment_client.client.handlers.DepartmentHandler;
import com.poli.human_resources_managment_client.client.handlers.JobHandler;
import com.poli.human_resources_managment_client.client.handlers.LocationHandler;
import com.poli.human_resources_managment_client.client.handlers.HistoricHandler;

/**
 * Client class that establishes a connection to the server and manages user
 * interactions. Allows the user to choose between different entities to perform
 * CRUD operations. Supports entities such as Employee, Country, City,
 * Department, Job, Location, and Historic.
 */
public class Client {

	/**
	 * The default server IP address. This is used when the server IP is not
	 * specified by the user.
	 */
	private static final String DEFAULT_SERVER_IP = "localhost";

	/**
	 * A BufferedReader for reading input from the console. This is used to read
	 * user input for server IP and port configurations.
	 */
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Socket for the client to establish a connection with the server. This socket
	 * is used for network communication with the server.
	 */
	private Socket socket;

	/**
	 * PrintWriter for sending data to the server. It is used to send text data over
	 * the socket to the server.
	 */
	private PrintWriter out;

	/**
	 * BufferedReader for receiving data from the server. It is used to read text
	 * data sent from the server over the socket.
	 */
	private BufferedReader in;

	/**
	 * Logger for logging information, warnings, and errors. It is a static final
	 * logger used throughout the Client class for logging purposes.
	 */
	private static final Logger logger = Logger.getLogger(Client.class.getName());

	/**
	 * The main method that runs when the program starts. Establishes the connection
	 * with the server and handles user interactions with different entities.
	 *
	 * @param args Command-line arguments, not used in this application.
	 */
	public static void main(String[] args) {
		Client client = new Client();
		String serverIp = getServerIp();
		int port = getServerPort();

		if (!client.startConnection(serverIp, port)) {
			return;
		}

		Scanner scanner = new Scanner(System.in);
		EmployeeHandler employeeHandler = new EmployeeHandler(scanner);
		CountryHandler countryHandler = new CountryHandler(scanner);
		CityHandler cityHandler = new CityHandler(scanner);
		DepartmentHandler departmentHandler = new DepartmentHandler(scanner);
		JobHandler jobHandler = new JobHandler(scanner);
		LocationHandler locationHandler = new LocationHandler(scanner);
		HistoricHandler historicHandler = new HistoricHandler(scanner);

		String response;
		String inputLine;
		while (true) {
			System.out.print("Enter entity (Employee, Country, City, Department, Job or Location.) or 'EXIT': ");
			String entity = scanner.nextLine().toUpperCase();

			switch (entity) {
			case "EMPLOYEE":
				inputLine = employeeHandler.handleCommand();
				break;
			case "COUNTRY":
				inputLine = countryHandler.handleCommand();
				break;
			case "CITY":
				inputLine = cityHandler.handleCommand();
				break;
			case "DEPARTMENT":
				inputLine = departmentHandler.handleCommand();
				break;
			case "JOB":
				inputLine = jobHandler.handleCommand();
				break;
			case "LOCATION":
				inputLine = locationHandler.handleCommand();
				break;
			case "HISTORIC":
				inputLine = historicHandler.handleCommand();
				break;
			case "EXIT":
				inputLine = "EXIT";
				break;
			default:
				System.out.println("Invalid entity");
				continue;
			}

			response = client.sendMessage(inputLine);
			if ("EXIT".equalsIgnoreCase(inputLine)) {
				break;
			}
			System.out.println("Server response: " + response);
		}
		;

		client.stopConnection();
	}

	/**
	 * Initiates a connection to the server using the provided IP address and port.
	 * Creates a socket for communication and establishes input and output streams.
	 *
	 * @param serverIp The IP address of the server.
	 * @param port     The port of the server.
	 * @return true if the connection is successfully established, false otherwise.
	 */
	public boolean startConnection(String serverIp, int port) {
		try {
			socket = new Socket(serverIp, port);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			return true;
		} catch (IOException e) {
			handleError("Error starting client connection", e);
			return false;
		}
	}

	/**
	 * Sends a message to the server and waits for a response. Uses the output
	 * stream to send the message and the input stream to receive the response.
	 *
	 * @param msg The message to send to the server.
	 * @return The response from the server.
	 */
	public String sendMessage(String msg) {
		out.println(msg);
		try {
			return in.readLine();
		} catch (IOException e) {
			handleError("Error sending message to server", e);
			return "Error: Unable to receive server response";
		}
	}

	/**
	 * Closes the connection with the server. Shuts down the input and output
	 * streams and the socket.
	 */
	public void stopConnection() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			handleError("Error closing client connection", e);
		}
	}

	/**
	 * Reads the server IP from the user input. Defaults to localhost if no input is
	 * provided.
	 * 
	 * @return The IP address of the server.
	 */
	private static String getServerIp() {
		System.out.print("Enter the server IP (leave blank for localhost): ");
		try {
			String serverIp = reader.readLine();
			return serverIp.isEmpty() ? DEFAULT_SERVER_IP : serverIp;
		} catch (IOException e) {
			handleError("Error reading server IP", e);
			return DEFAULT_SERVER_IP;
		}
	}

	/**
	 * Reads the server port from the user input.
	 * 
	 * @return The port number of the server.
	 */
	private static int getServerPort() {
		System.out.print("Enter the server port: ");
		while (true) {
			try {
				return Integer.parseInt(reader.readLine());
			} catch (IOException e) {
				handleError("Error reading server port", e);
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, "Please enter a valid integer number for the port.");
			}
		}
	}

	/**
	 * Logs an error message and the associated exception. This method is used to
	 * log severe level errors that occur within the Client application.
	 *
	 * @param message A descriptive message explaining the error context.
	 * @param e       The exception that triggered the error condition.
	 */
	public static void handleError(String message, Exception e) {
		logger.log(Level.SEVERE, message, e);
	}
}