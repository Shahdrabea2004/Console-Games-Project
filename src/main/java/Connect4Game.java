import java.util.Scanner;

public class Connect4Game {

    private static final int ROWS = 6;
    private static final int COLS = 7;

    private static char[][] board = new char[ROWS][COLS];
    private static char currentPlayer = 'R';

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
        System.out.println(" 0 1 2 3 4 5 6");
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
     * <p>
     * TODO:
     * - Fill board with empty spaces
     */
    private static void initializeBoard() {
        // TODO
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Validates whether the selected column is playable.
     */
    private static boolean isValidMove(int col) {
        // TODO
        return (col >= 0 && col < COLS && board[0][col]==' ');
    }

    /**
     * Places the current player's disc into the selected column.
     * <p>
     * TODO:
     * - Start from bottom row
     * - Place disc in first empty cell
     *
     * @param col selected column
     * @return true if disc was placed successfully
     */
    private static boolean placeDiscInColumn(int col) {
        // TODO
        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][col] == ' ') {
                board[r][col] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if current player won.
     */
    private static boolean checkWinner() {
        // TODO
        //Columns
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS - 3; r++) {
                if (board[r][c] == currentPlayer &&
                        board[r + 1][c] == currentPlayer &&
                        board[r + 2][c] == currentPlayer &&
                        board[r + 3][c] == currentPlayer) return true;
            }
        }
        //Rows
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS - 3; c++) {
                if (board[r][c] == currentPlayer &&
                        board[r][c + 1] == currentPlayer &&
                        board[r][c + 2] == currentPlayer &&
                        board[r][c + 3] == currentPlayer) return true;
            }
        }

        //Diagonal Right
        for (int r = 0; r < ROWS - 3; r++) {
            for (int c = 0; c < COLS - 3; c++) {
                if (board[r][c] == currentPlayer &&
                        board[r + 1][c + 1] == currentPlayer &&
                        board[r + 2][c + 2] == currentPlayer &&
                        board[r + 3][c + 3] == currentPlayer) return true;
            }
        }

        //Diagonal Left
        for (int r = ROWS-4 ; r < ROWS; r++) {
            for (int c = 0; c < COLS - 3; c++) {
                if (board[r][c] == currentPlayer &&
                        board[r - 1][c + 1] == currentPlayer &&
                        board[r - 2][c + 2] == currentPlayer &&
                        board[r - 3][c + 3] == currentPlayer) return true;
            }
        }


        return false;
    }

    /**
     * Checks if board is full.
     */
    private static boolean isBoardFull() {
        // TODO
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] == ' ') return false;
        }

        return true;
    }

    /**
     * Switches player turn.
     */
    private static void switchPlayer() {
        // TODO
        currentPlayer = (currentPlayer == 'R' ? 'B' : 'R');
    }
}