import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MemoryMatchGame {

    private static final int SIZE = 4;

    private static char[][] board = new char[SIZE][SIZE];
    private static boolean[][] revealed = new boolean[SIZE][SIZE];

    private static int player1Score = 0;
    private static int player2Score = 0;

    private static boolean playerOneTurn = true;

    public static void main(String[] args) {
        initializeBoardRevealed();
        shuffleBoard();

        playGame();
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println(playerOneTurn ? "Player 1 turn" : "Player 2 turn");
            printBoard();

            System.out.print("Enter first card (row col): ");
            int r1 = scanner.nextInt();
            int c1 = scanner.nextInt();

            System.out.print("Enter second card (row col): ");
            int r2 = scanner.nextInt();
            int c2 = scanner.nextInt();

            if (isValidMove(r1, c1, r2, c2)) {

                processMove(r1, c1, r2, c2);

                if (checkWinner()) {
                    running = false;
                }

            } else {
                System.out.println("Invalid move!");
            }
        }
        scanner.close();
    }

    /**
     * Initializes board and reveal state.
     * <p>
     * TODO:
     * - Prepare empty board
     * - Reset revealed array
     */
    private static void initializeBoardRevealed() {
        // TODO
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                revealed[i][j] = false;
            }
        }

    }

    /**
     * Shuffles card values.
     * <p>
     * TODO:
     * - Pair characters
     * - Randomize placement
     */
    private static void shuffleBoard() {
        // TODO
        char[] cards = {
                'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D',
                'E', 'E', 'F', 'F', 'G', 'G', 'H', 'H'
        };
        Random random = new Random();
        for (int i = cards.length - 1; i >= 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
        int index = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = cards[index++];
            }
        }

    }

    private static void printBoard() {
        System.out.print("   ");
        for (int c = 0; c < SIZE; c++) System.out.print(c + " ");
        System.out.println();
        for (int r = 0; r < SIZE; r++) {
            System.out.print(r + " ");
            for (int c = 0; c < SIZE; c++) {
                System.out.print("|" + (revealed[r][c] ? board[r][c] : '*'));
            }
            System.out.println("|");
        }
    }

    /**
     * Validates player move.
     * <p>
     * TODO:
     * - Bounds
     * - Different cells
     * - Not already revealed
     */
    private static boolean isValidMove(int r1, int c1, int r2, int c2) {
        // TODO
        if (!(r1 >= 0 && r1 <= 3) || !(c1 >= 0 && c1 <= 3) || !(r2 >= 0 && r2 <= 3) || !(c2 >= 0 && c2 <= 3)) {
            return false;
        }
        if (r1 == r2 && c1 == c2) {
            return false;
        }
        if (revealed[r1][c1] || revealed[r2][c2]) {
            return false;
        }
        return true;
    }

    /**
     * Processes selected cards.
     * <p>
     * TODO:
     * - Reveal cards
     * - Check match
     * - Update score
     */
    private static void processMove(int r1, int c1, int r2, int c2) {
        // TODO
        revealed[r1][c1] = true;
        revealed[r2][c2] = true;
        printBoard();
        if (board[r1][c1] != board[r2][c2]) {
            System.out.println("No match!");
            revealed[r1][c1] = false;
            revealed[r2][c2] = false;
            switchTurn();


        } else {
            System.out.println("Match!");
            if (playerOneTurn) {
                player1Score++;
            } else {
                player2Score++;
            }
        }

        System.out.println("-player one Score: " + player1Score);
        System.out.println("-player two Score: " + player2Score);

    }

    /**
     * Checks game end condition.
     * <p>
     * TODO:
     * - All pairs revealed
     */
    private static boolean checkWinner() {
        // TODO
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (revealed[i][j] == false)
                    return false;
            }
        }
        if (player1Score > player2Score) {
            printBoard();
            System.out.println("Player one Win!");
            return true;
        } else if (player1Score < player2Score) {
            printBoard();
            System.out.println("Player Two Win!");
            return true;
        } else if (player1Score == player2Score) {
            printBoard();
            System.out.println("It's a Tie!");
            return true;
        }
        return false;
    }

    /**
     * Switches turn between players.
     * <p>
     * TODO:
     * - Toggle turn
     */
    private static void switchTurn() {
        // TODO
        playerOneTurn = !playerOneTurn;
    }
}