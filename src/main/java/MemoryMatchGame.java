import java.util.Scanner;

public class MemoryMatchGame {

    private static final int SIZE = 4;

    private static char[][] board = new char[SIZE][SIZE];
    private static boolean[][] revealed = new boolean[SIZE][SIZE];

    private static int player1Score = 0;
    private static int player2Score = 0;

    private static boolean playerOneTurn = true;

    public static void main(String[] args) {
        initializeBoard();
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
                } else {
                    switchTurn();
                }

            } else {
                System.out.println("Invalid move!");
            }
        }
        scanner.close();
    }

    /**
     * Initializes board and reveal state.
     *
     * TODO:
     * - Prepare empty board
     * - Reset revealed array
     */
    private static void initializeBoard() {
        // TODO
    }

    /**
     * Shuffles card values.
     *
     * TODO:
     * - Pair characters
     * - Randomize placement
     */
    private static void shuffleBoard() {
        // TODO
    }

    private static void printBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                System.out.print("|" + (revealed[r][c] ? board[r][c] : '*'));
            }
            System.out.println("|");
        }
    }

    /**
     * Validates player move.
     *
     * TODO:
     * - Bounds
     * - Different cells
     * - Not already revealed
     */
    private static boolean isValidMove(int r1, int c1, int r2, int c2) {
        // TODO
        return false;
    }

    /**
     * Processes selected cards.
     *
     * TODO:
     * - Reveal cards
     * - Check match
     * - Update score
     */
    private static void processMove(int r1, int c1, int r2, int c2) {
        // TODO
    }

    /**
     * Checks game end condition.
     *
     * TODO:
     * - All pairs revealed
     */
    private static boolean checkWinner() {
        // TODO
        return false;
    }

    /**
     * Switches turn between players.
     *
     * TODO:
     * - Toggle turn
     */
    private static void switchTurn() {
        // TODO
    }
}