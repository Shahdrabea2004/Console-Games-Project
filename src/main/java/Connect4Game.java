import java.util.Scanner;

public class Connect4Game {

    private static final int ROWS = 6;
    private static final int COLS = 7;

    private static char[][] board = new char[ROWS][COLS];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printBoard();
            System.out.print("Player " + currentPlayer + " choose column (0-6): ");
            int col = scanner.nextInt();

            if (isValidMove(col) && placeDiscInColumn(col)) {

                if (checkWinner()) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    running = false;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("Draw!");
                    running = false;
                } else {
                    switchPlayer();
                }

            } else {
                System.out.println("Invalid move!");
            }
        }
        scanner.close();
    }

    private static void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print("|" + cell);
            }
            System.out.println("|");
        }
        System.out.println("---------------------");
    }

    /**
     * Initializes the game board.
     *
     * TODO:
     * - Fill board with empty spaces
     */
    private static void initializeBoard() {
        // TODO
    }

    /**
     * Validates whether the selected column is playable.
     */
    private static boolean isValidMove(int col) {
        // TODO
        return false;
    }

    /**
     * Places the current player's disc into the selected column.
     *
     * TODO:
     * - Start from bottom row
     * - Place disc in first empty cell
     *
     * @param col selected column
     * @return true if disc was placed successfully
     */
    private static boolean placeDiscInColumn(int col) {
        // TODO
        return false;
    }

    /**
     * Checks if current player won.
     */
    private static boolean checkWinner() {
        // TODO
        return false;
    }

    /**
     * Checks if board is full.
     */
    private static boolean isBoardFull() {
        // TODO
        return false;
    }

    /**
     * Switches player turn.
     */
    private static void switchPlayer() {
        // TODO
    }
}