import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

    private static char[][] board = new char[3][3];

    private static String[] prizes = {"Pen", "Candy", "Sticker", "Keychain", "Eraser"};
    private static char currentPlayer = 'X';

    private static boolean playWithComputer = false;

    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Do you want to play against computer? (Y/N): ");
        String choice = scanner.next();
        choice = choice.toLowerCase();
        playWithComputer = (choice.equals("y")) ? true : false;
        while (running) {
            printBoard();
            //play with computer
            if (playWithComputer && currentPlayer == 'O') {
                computerMove();
            }
            //play with 2 players
            else {
                System.out.println("Player " + currentPlayer + " enter row and column (0-2):");

                int row = scanner.nextInt();
                int col = scanner.nextInt();
                if (!isValidMove(row, col)) {
                    System.out.println("Invalid move!");
                    continue;
                }
                board[row][col] = currentPlayer;

            }

            if (checkWinner()) {
                printBoard();
                System.out.println(givePrize());
                running = false;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("Draw!");
                running = false;
            } else {
                switchPlayer();
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
     * <p>
     * TODO:
     * - Fill board with empty spaces
     */
    private static void initializeBoard() {
        // TODO
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Checks whether a move is valid.
     */
    private static boolean isValidMove(int row, int col) {
        // TODO
        return (row >= 0 && row <= 2) && (col >= 0 && col <= 2) && (board[row][col] == ' ');
    }

    /**
     * Checks if current player won.
     */
    private static boolean checkWinner() {
        // TODO
        for (int i = 0; i < 3; i++) {
            if ((board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) ||
                    (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])) {
                return true;
            }
        }
        // Diagonals
        if ((board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[0][0])) {
            return true;
        }
        return false;
    }

    /**
     * Randomly select a prize
     */
    private static String givePrize() {
        Random random = new Random();
        String prize = prizes[random.nextInt(prizes.length)];
        return ("Congratulations Player " + currentPlayer + "! You won a " + prize + "!");
    }

    /**
     * Checks if board is full.
     */
    private static boolean isBoardFull() {
        // TODO
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return !checkWinner();
    }

    /**
     * Switches player turn.
     */
    private static void switchPlayer() {
        // TODO
        currentPlayer = (currentPlayer == 'O') ? 'X' : 'O';
    }

    /**
     * Play with computer.
     */
    private static void computerMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));
        System.out.println("Computer chooses: " + row + " " + col);
        board[row][col] = currentPlayer;
    }
}