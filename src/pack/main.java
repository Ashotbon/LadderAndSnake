package pack;

import java.util.Scanner;


public class main {
	
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Welcome to the Ladder and Snake Game, Ashot!");
	        int numberOfPlayers = 0;
	        int attempts = 0;
	        final int MAX_ATTEMPTS = 4;

	        while (attempts < MAX_ATTEMPTS) {
	            try {
	                System.out.print("Enter the number of players (between 2 and 4): ");
	                numberOfPlayers = scanner.nextInt();

	                if (numberOfPlayers >= 2 && numberOfPlayers <= 4) {
	                    break;
	                } else {
	                    attempts++;
	                    System.out.println("Bad Attempt. Please enter a number between 2 and 4.");
	                }
	            } catch (Exception e) {
	                // Handle the exception (e.g., display an error message)
	                System.out.println("Invalid input. Please enter a valid number.");
	                // Clear the buffer to prevent an infinite loop due to the invalid input
	                scanner.nextLine();
	                attempts++;
	            }
	        }
	        if (attempts == MAX_ATTEMPTS) {
	            System.out.println("Exceeded maximum attempts. Program terminating.");
	        } else {
	            LadderAndSnake game = new LadderAndSnake(numberOfPlayers);

	            // Simulate the game
	            game.play();
	        }
	        
	        scanner.close();
	    }
	}