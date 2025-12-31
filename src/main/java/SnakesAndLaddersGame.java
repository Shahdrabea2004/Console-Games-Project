import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;

public class SnakesAndLaddersGame {

    private static final int WIN_POSITION = 100;

    private static int player1Position = 0;
    private static int player2Position = 0;

    private static boolean playerOneTurn = true;

    /* Snakes and ladders mapping */
    private static final Map<Integer, Integer> snakesAndLadders = new HashMap<>();

    static {
        // Ladders
        snakesAndLadders.put(3, 22);
        snakesAndLadders.put(5, 8);
        snakesAndLadders.put(11, 26);
        snakesAndLadders.put(20, 29);

        // Snakes
        snakesAndLadders.put(27, 1);
        snakesAndLadders.put(21, 9);
        snakesAndLadders.put(17, 4);
        snakesAndLadders.put(19, 7);
    }

    public static void main(String[] args) {
        initializeGame();
        playGame();
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(playerOneTurn ? "\nPlayer 1 turn" : "\nPlayer 2 turn");
            System.out.println("Press ENTER to roll the dice...");
            scanner.nextLine();

            int diceValue = rollDice();
            System.out.println("Dice rolled: " + diceValue);

            movePlayer(diceValue);
            printPositions();

            if (checkWinner()) {
                System.out.println(playerOneTurn ? "Player 1 wins!" : "Player 2 wins!");
                running = false;
            } else {
                switchTurn();
            }
        }
        scanner.close();
    }

    /**
     * Initializes game state.
     *
     * TODO:
     * - Reset player positions
     * - Set starting turn
     */
    private static void initializeGame() {
        // TODO
        player1Position=0;
        player2Position=0;
        playerOneTurn=true;
    }

    /**
     * Simulates rolling a dice.
     *
     * TODO:
     * - Generate random number between 1 and 6
     */
    private static int rollDice() {
        // TODO
        Random random =new Random();
        int randomDice=random.nextInt(6)+1;
        return randomDice;
    }

    /**
     * Moves the current player and handles snakes and ladders.
     *
     * @param diceValue value rolled
     */
    private static void movePlayer(int diceValue) {

        int currentPosition = playerOneTurn ? player1Position : player2Position;
        int newPosition = currentPosition + diceValue;

        if (newPosition > WIN_POSITION) {
            return; // cannot move beyond winning position
        }

        // Handle snakes or ladders
        if (snakesAndLadders.containsKey(newPosition)) {
            int finalPosition = snakesAndLadders.get(newPosition);

            if (finalPosition > newPosition) {
                System.out.println("Ladder! Climb up from " + newPosition + " to " + finalPosition);
            } else {
                System.out.println("Snake! Slide down from " + newPosition + " to " + finalPosition);
            }

            newPosition = finalPosition;
        }

        if (playerOneTurn) {
            player1Position = newPosition;
        } else {
            player2Position = newPosition;
        }
    }

    /**
     * Checks if current player has won.
     *
     * TODO:
     * - Check if position reached WIN_POSITION
     */
    private static boolean checkWinner() {
        // TODO
        boolean check=(player1Position==100||player2Position==100)?true:false;
        return check;
    }

    /**
     * Switches turn between players.
     *
     * TODO:
     * - Toggle playerOneTurn
     */
    private static void switchTurn() {
        // TODO
        if(playerOneTurn == true){
            playerOneTurn=false;
        }
        else{
            playerOneTurn=true;
        }

    }

    /**
     * Prints current positions.
     */
    private static void printPositions() {
        System.out.println("Player 1 position: " + player1Position);
        System.out.println("Player 2 position: " + player2Position);
    }
}