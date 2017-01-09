package com.lonsec;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lonsec.service.RequestProcessor;

/**
 * @author Aravind
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class App implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	@Autowired
	private RequestProcessor processor;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		logger.info("Completed");
	}

	@Override
	public void run(String... arg0) throws Exception {
		displayOptions();
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
				System.out.println("Option 1: Please input the File Path/Folder Path:");
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
			System.out.println("Invalid Option Entered. Please enter a valid Number.");
		}
	}
}