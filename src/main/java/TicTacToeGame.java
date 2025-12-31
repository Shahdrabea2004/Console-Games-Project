import java.util.Scanner;

public class TicTacToeGame {

    private static char[][] board = new char[3][3];
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
            System.out.println("Player " + currentPlayer + " enter row and column (0-2):");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;

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
            System.out.println(row[0] + "|" + row[1] + "|" + row[2]);
            System.out.println("-----");
        }
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
     * Checks whether a move is valid.
     */
    private static boolean isValidMove(int row, int col) {
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