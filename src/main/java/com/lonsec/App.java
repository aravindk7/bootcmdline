package com.lonsec;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.lonsec.service.RequestProcessor;

/**
 * @author Aravind
 *
 */
@SpringBootApplication
public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	@Autowired
	private  RequestProcessor processor;
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(App.class, args);
		App app = new App();
		app.displayOptions();
		logger.info("Completed");
	}

	private void displayOptions() {
		System.out.println("Enter the Option (1/2/3): ");
		System.out.println("1 - To Process the input files");
		System.out.println("2 - To Generate Monthly Performance File");
		System.out.println("3 - To Exit");

		try (Scanner scanner = new Scanner(System.in)) {
			String option = scanner.nextLine();

			switch (option) {
			case "1":
				System.out.println("Option 1");
				String path = scanner.nextLine();
				processor.processInputFiles(path);
				displayOptions();
			case "2":
				System.out.println("Generating monthlyOutperformance.csv file..");
				processor.generateMonthlyReport();
			case "3":
				System.out.println("Exiting..");
				System.exit(0);
			default:
				System.out.println("Invalid Option Entered. Please enter a valid Number.");
				displayOptions();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid Option. Please input a ");
		}
	}
}