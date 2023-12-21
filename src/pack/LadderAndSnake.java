
//Ashot

package pack;
import java.util.Arrays;
import java.util.Random;

public class LadderAndSnake {
    private static final int BOARD_SIZE = 10;
    private static final int WINNING_SQUARE = 100;
    private static final int MAX_DICE_VALUE = 6;

    private int[][] board;
    private int numberOfPlayers;
    private int[] playerPositions;
    private Random random;

    public LadderAndSnake(int numberOfPlayers) {

        this.numberOfPlayers = numberOfPlayers;
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        this.playerPositions = new int[numberOfPlayers];
        this.random = new Random();

        initializeBoard();
        initializePlayers();
    }

    private void initializeBoard() {
        board[3][5] = 8;  // Ladder from square 36 to square 44
        board[7][8] = -60; // Snake from square 79 to square 19
        
    }

    private void initializePlayers() {
        // Initialize player positions at the start of the board
        Arrays.fill(playerPositions, 0);
    }

    private int flipDice() {
        // Return a random value between 1 and 6
        return random.nextInt(MAX_DICE_VALUE) + 1;
    }

    private int[] determinePlayerOrder() {
        // Determine the order of playing turns based on the dice throw
        int[] diceValues = new int[numberOfPlayers];

        System.out.println("Now deciding which player will start playing:");
        for (int i = 0; i < numberOfPlayers; i++) {
            diceValues[i] = flipDice();
            System.out.printf("Player %d got a dice value of %d%n", i + 1, diceValues[i]);
        }

        int[] playerOrder = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            int maxIndex = getMaxIndex(diceValues);
            playerOrder[i] = maxIndex + 1;
            diceValues[maxIndex] = -1; // Mark the maximum value as used
        }

        return playerOrder;
    }

    private int getMaxIndex(int[] array) {
        // Return the index of the maximum value in the array
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void play() {
        int[] playerOrder = determinePlayerOrder();

        System.out.println("Reached final decision on the order of playing:");

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.printf("Player %d ", playerOrder[i]);
        }

        System.out.println("\n");

        int currentPlayerIndex = 0;

        while (true) {
            int diceValue = flipDice();
            int currentPlayer = playerOrder[currentPlayerIndex];

            System.out.printf("Player %d got a dice value of %d; ", currentPlayer, diceValue);

            try {
                playerPositions[currentPlayer - 1] += diceValue;

                if (playerPositions[currentPlayer - 1] > WINNING_SQUARE) {
                    // Player exceeded the winning square, move backward
                    int backwardMoves = playerPositions[currentPlayer - 1] - WINNING_SQUARE;
                    playerPositions[currentPlayer - 1] = WINNING_SQUARE - backwardMoves;

                    System.out.printf("exceeded the maximum possible moves, moving backward to %d%n",
                            playerPositions[currentPlayer - 1]);
                } else {
                    System.out.printf("now in square %d%n", playerPositions[currentPlayer - 1]);

                    int newPosition = playerPositions[currentPlayer - 1];

                    // Check for ladder or snake
                    int move = board[newPosition / BOARD_SIZE][newPosition % BOARD_SIZE];
                    if (move != 0) {
                        playerPositions[currentPlayer - 1] += move;

                        System.out.printf("Moved %d %s to square %d%n", Math.abs(move),
                                (move > 0) ? "up" : "down", playerPositions[currentPlayer - 1]);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Termination of the game.");
                break;
            }
     
        

            // Check if the current player won
            if (playerPositions[currentPlayer - 1] == WINNING_SQUARE) {
                System.out.printf("Player %d has won! Game over.%n", currentPlayer);
                System.out.println("Thank you for playing the Ladder and Snake Game, Ashot! Goodbye!");
                break;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % numberOfPlayers;
           

        }
    }


}