<div align="center">

# 🎮 Console Games Project

### *Four classic games built in Java — fully playable in your terminal.*

[![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Build-Maven-red?style=flat-square&logo=apachemaven)](https://maven.apache.org/)
[![Games](https://img.shields.io/badge/Games-4-blue?style=flat-square)]()
[![Type](https://img.shields.io/badge/Type-Console%20Application-green?style=flat-square)]()

</div>

---

## 📌 Overview

A collection of **4 console-based Java games** in a single Maven project. Each game is a self-contained class with its own `main()` entry point, built entirely with core Java — no external libraries, no frameworks. The project demonstrates fundamental programming concepts: 2D arrays, static state management, input validation loops, randomization, and win-condition algorithms.

| Game | File | Players | Board |
|---|---|---|---|
| 🐍 Snakes & Ladders | `SnakesAndLaddersGame.java` | 2 | Linear 1–100 |
| ❌⭕ Tic Tac Toe | `TicTacToeGame.java` | 2 or vs CPU | 3×3 grid |
| 🧠 Memory Match | `MemoryMatchGame.java` | 2 | 4×4 grid |
| 🔴🔵 Connect 4 | `Connect4Game.java` | 2 | 6×7 grid |

---

## 🏗️ Project Structure

```
Console-Games-Project/
│
├── pom.xml                          # Maven build config (Java 17)
│
└── src/main/java/
    ├── SnakesAndLaddersGame.java    # Game 1
    ├── TicTacToeGame.java           # Game 2
    ├── MemoryMatchGame.java         # Game 3
    └── Connect4Game.java            # Game 4
```

All four games share the same design pattern:
- **Static fields** hold game state
- **Static methods** implement each logical unit
- `main()` calls `initializeX()` then `playGame()`
- Input read via `java.util.Scanner`

---

## ⚙️ Backend Deep Dive

---

### 🐍 Game 1 — Snakes & Ladders (`SnakesAndLaddersGame.java`)

#### State

```java
static int  player1Position = 0;
static int  player2Position = 0;
static boolean playerOneTurn  = true;
static final int WIN_POSITION = 100;
static final Map<Integer, Integer> snakesAndLadders = new HashMap<>();
```

#### Snake & Ladder Map (hardcoded in `static {}` block)

| From | To | Type |
|---|---|---|
| 3 | 22 | 🪜 Ladder |
| 5 | 8 | 🪜 Ladder |
| 11 | 26 | 🪜 Ladder |
| 20 | 29 | 🪜 Ladder |
| 27 | 1 | 🐍 Snake |
| 21 | 9 | 🐍 Snake |
| 17 | 4 | 🐍 Snake |
| 19 | 7 | 🐍 Snake |

Detected at runtime by comparing new vs final position:
```java
if (finalPosition > newPosition) → "Ladder! Climb up..."
else                             → "Snake! Slide down..."
```

#### Core Logic — `movePlayer(int diceValue)`

```
currentPosition = active player's position
newPosition     = currentPosition + diceValue

if newPosition > 100 → skip (cannot overshoot)
if newPosition in snakesAndLadders map → apply teleport
update player1Position or player2Position
```

#### `rollDice()`
```java
new Random().nextInt(6) + 1   // produces 1–6 inclusive
```

#### `checkWinner()`
```java
return (player1Position == 100 || player2Position == 100);
```

Win is checked **after** every move. First to land exactly on 100 wins. Overshooting (newPosition > 100) does nothing — turn is wasted.

#### Method Map

| Method | Role |
|---|---|
| `initializeGame()` | Reset both positions to 0, set Player 1 first |
| `rollDice()` | Return random int 1–6 |
| `movePlayer(dice)` | Apply move + snake/ladder logic |
| `printPositions()` | Print both positions |
| `checkWinner()` | Return true if either player is at 100 |
| `switchTurn()` | Toggle `playerOneTurn` |

---

### ❌⭕ Game 2 — Tic Tac Toe (`TicTacToeGame.java`)

#### State

```java
static char[][] board          = new char[3][3];
static char     currentPlayer  = 'X';
static boolean  playWithComputer = false;
static String[] prizes = {"Pen","Candy","Sticker","Keychain","Eraser"};
```

#### Modes

Chosen at startup:
```
"Do you want to play against computer? (Y/N)"
```
- `Y` → `playWithComputer = true` — `'O'`'s turn is handled by `computerMove()`
- `N` → both players input manually

#### `isValidMove(int row, int col)`
```java
return (row >= 0 && row <= 2)
    && (col >= 0 && col <= 2)
    && (board[row][col] == ' ');
```
Bounds + occupancy check in one expression.

#### `checkWinner()` — Win Detection Algorithm

Checks all 8 possible winning lines:

```java
// 3 columns (vertical)
board[0][i] == board[1][i] == board[2][i]   (for i = 0,1,2)

// 3 rows (horizontal)
board[i][0] == board[i][1] == board[i][2]   (for i = 0,1,2)

// 2 diagonals
board[0][0] == board[1][1] == board[2][2]
board[0][2] == board[1][1] == board[0][0]   ← ⚠️ bug: checks [0][0] not [2][0]
```

> ⚠️ **Known Bug:** The anti-diagonal check compares `board[0][2]`, `board[1][1]`, `board[0][0]` instead of `board[0][2]`, `board[1][1]`, `board[2][0]`. Anti-diagonal wins are not detected correctly.

#### `isBoardFull()`
Scans all 9 cells for anything that isn't `'X'` or `'O'`. Returns `true` only if no empty cells remain **and** no winner (called after `checkWinner()` already returned false).

#### `computerMove()` — Random AI
```java
do {
    row = random.nextInt(3);
    col = random.nextInt(3);
} while (!isValidMove(row, col));
board[row][col] = currentPlayer;
```
Pure random selection — loops until a valid empty cell is found. No strategy.

#### `givePrize()` — Winner Reward
```java
prizes[new Random().nextInt(prizes.length)]
// → "Congratulations Player X! You won a Candy!"
```

#### Method Map

| Method | Role |
|---|---|
| `initializeBoard()` | Fill 3×3 with `' '` |
| `playGame()` | Main loop: prompt → validate → place → check |
| `isValidMove(r,c)` | Bounds + empty cell check |
| `checkWinner()` | 8-line win scan |
| `isBoardFull()` | Draw detection |
| `switchPlayer()` | Toggle `'X'` ↔ `'O'` |
| `computerMove()` | Random valid move for CPU |
| `givePrize()` | Pick random prize string |
| `printBoard()` | Render 3×3 grid |

---

### 🧠 Game 3 — Memory Match (`MemoryMatchGame.java`)

#### State

```java
static final int SIZE = 4;                    // 4×4 board = 16 cells = 8 pairs
static char[][]    board    = new char[4][4]; // card values (A–H, 2 each)
static boolean[][] revealed = new boolean[4][4]; // visibility mask
static int  player1Score = 0;
static int  player2Score = 0;
static boolean playerOneTurn = true;
```

Two parallel arrays: `board` holds actual values (hidden), `revealed` controls what the player sees.

#### `shuffleBoard()` — Fisher-Yates Shuffle

```java
char[] cards = {'A','A','B','B','C','C','D','D','E','E','F','F','G','G','H','H'};

// Fisher-Yates in-place shuffle
for (int i = cards.length - 1; i >= 0; i--) {
    int j = random.nextInt(i + 1);
    // swap cards[i] and cards[j]
}

// fill board row by row
board[i][j] = cards[index++];
```

8 letter pairs (A–H), each appearing exactly twice, placed in uniformly random order.

#### `printBoard()` — Visibility Mask

```java
revealed[r][c] ? board[r][c] : '*'
```

Matched cards show their letter. Unmatched cards show `*`. Players never see the board values directly — they guess positions.

#### `isValidMove(r1, c1, r2, c2)` — 3-Rule Validation

```java
// Rule 1: both cells within 0–3 bounds
// Rule 2: two different cells selected
if (r1 == r2 && c1 == c2) return false;
// Rule 3: neither cell already matched/revealed
if (revealed[r1][c1] || revealed[r2][c2]) return false;
```

#### `processMove(r1, c1, r2, c2)` — Core Turn Logic

```
reveal both cards temporarily
print board (player sees both cards)

if board[r1][c1] != board[r2][c2]:
    → "No match!"
    → hide both cards again (revealed = false)
    → switchTurn()
else:
    → "Match!"
    → cards stay revealed (already set to true)
    → active player scores++
    → (turn stays with same player — they go again)

print both scores
```

Match = keep turn + score point. No match = hide + switch. This is the standard Concentration game rule.

#### `checkWinner()` — End Condition

Full scan of `revealed[][]` — if **any** cell is still `false`, game continues. When all 16 cells are revealed:

```java
if (p1Score > p2Score) → "Player one Win!"
if (p1Score < p2Score) → "Player Two Win!"
if (p1Score == p2Score) → "It's a Tie!"
```

#### Method Map

| Method | Role |
|---|---|
| `initializeBoardRevealed()` | Set all `revealed[i][j] = false` |
| `shuffleBoard()` | Fisher-Yates shuffle → fill board |
| `playGame()` | Main loop: prompt two cells → validate → process |
| `isValidMove(r1,c1,r2,c2)` | Bounds + distinct + unrevealed check |
| `processMove(r1,c1,r2,c2)` | Reveal, compare, score, conditionally hide |
| `checkWinner()` | All-revealed scan + score compare |
| `switchTurn()` | Toggle `playerOneTurn` |
| `printBoard()` | Render masked 4×4 grid |

---

### 🔴🔵 Game 4 — Connect 4 (`Connect4Game.java`)

#### State

```java
static final int ROWS = 6;
static final int COLS = 7;
static char[][] board         = new char[6][7];
static char     currentPlayer = 'R';   // 'R' = Red, 'B' = Blue
```

#### `placeDiscInColumn(int col)` — Gravity Simulation

```java
for (int r = ROWS - 1; r >= 0; r--) {  // scan bottom → top
    if (board[r][col] == ' ') {
        board[r][col] = currentPlayer;
        return true;
    }
}
return false;
```

Iterates from the bottom row upward and places the disc in the first empty cell. Correctly simulates gravity — discs always fall to the lowest available row.

#### `isValidMove(int col)` — Column Check

```java
return (col >= 0 && col < COLS && board[0][col] == ' ');
```

A column is valid if it's in range **and** its top cell is empty (meaning the column is not full).

#### `checkWinner()` — 4-Direction Win Scan

Checks all four possible connect-4 orientations for `currentPlayer`:

```java
// 1. Vertical (columns) — scan top to bottom
for r in 0..(ROWS-4):  board[r][c] == board[r+1][c] == board[r+2][c] == board[r+3][c]

// 2. Horizontal (rows) — scan left to right
for c in 0..(COLS-4):  board[r][c] == board[r][c+1] == board[r][c+2] == board[r][c+3]

// 3. Diagonal ↘ (top-left to bottom-right)
for r in 0..(ROWS-4), c in 0..(COLS-4):
    board[r][c] == board[r+1][c+1] == board[r+2][c+2] == board[r+3][c+3]

// 4. Diagonal ↗ (bottom-left to top-right)
for r in (ROWS-4)..ROWS, c in 0..(COLS-4):
    board[r][c] == board[r-1][c+1] == board[r-2][c+2] == board[r-3][c+3]
```

All 4 directions fully covered. Win is checked only for `currentPlayer` after each move — no need to scan for both players simultaneously.

#### `isBoardFull()` — Efficient Draw Detection

```java
for (int j = 0; j < COLS; j++) {
    if (board[0][j] == ' ') return false;
}
return true;
```

Only checks the **top row** — if any top cell is empty, at least one column still has space. No need to scan all 42 cells.

#### `switchPlayer()`
```java
currentPlayer = (currentPlayer == 'R' ? 'B' : 'R');
```

#### Method Map

| Method | Role |
|---|---|
| `initializeBoard()` | Fill 6×7 with `' '` |
| `playGame()` | Main loop: prompt column → validate → place → check |
| `isValidMove(col)` | Range + top-cell-empty check |
| `placeDiscInColumn(col)` | Bottom-up gravity scan + placement |
| `checkWinner()` | 4-direction 4-in-a-row scan |
| `isBoardFull()` | Top-row empty check (draw detection) |
| `switchPlayer()` | Toggle `'R'` ↔ `'B'` |
| `printBoard()` | Render 6×7 grid with column index header |

---

## 📊 Algorithm Complexity Summary

| Game | Board | Win Check | Draw Check |
|---|---|---|---|
| Snakes & Ladders | Linear (HashMap) | O(1) — position == 100 | N/A |
| Tic Tac Toe | 3×3 = 9 cells | O(1) — fixed 8 lines | O(n²) — full scan |
| Memory Match | 4×4 = 16 cells | O(n²) — full revealed scan | Same as win check |
| Connect 4 | 6×7 = 42 cells | O(n×m) — 4-direction scan | O(cols) — top row only |

---

## 🔄 Game Loop Pattern (shared across all 4 games)

All games follow the exact same structure:

```
main()
  ├── initializeX()       ← reset state
  └── playGame()
        └── while (running):
              ├── printBoard()
              ├── get input (Scanner)
              ├── validate input
              ├── apply move
              ├── checkWinner() → if true: announce + stop
              ├── isBoardFull() → if true: announce draw + stop
              └── switchPlayer() / switchTurn()
```

---

## 🚀 Setup & Run

### Prerequisites
- JDK 17+
- Maven 3.x (optional — can also compile manually)

### Option A — Maven

```bash
git clone https://github.com/your-username/console-games.git
cd console-games

mvn compile

# Run a specific game (replace ClassName as needed)
mvn exec:java -Dexec.mainClass="TicTacToeGame"
mvn exec:java -Dexec.mainClass="Connect4Game"
mvn exec:java -Dexec.mainClass="MemoryMatchGame"
mvn exec:java -Dexec.mainClass="SnakesAndLaddersGame"
```

### Option B — Manual Compile

```bash
cd src/main/java

javac TicTacToeGame.java       && java TicTacToeGame
javac Connect4Game.java        && java Connect4Game
javac MemoryMatchGame.java     && java MemoryMatchGame
javac SnakesAndLaddersGame.java && java SnakesAndLaddersGame
```

---

## 🔒 Known Issues & Improvements

| Issue | Game | Description | Fix |
|---|---|---|---|
| Anti-diagonal bug | Tic Tac Toe | `checkWinner()` checks `board[0][0]` instead of `board[2][0]` for the ↗ diagonal | Change to `board[0][2] == board[1][1] && board[1][1] == board[2][0]` |
| Random AI only | Tic Tac Toe | Computer picks random empty cell — no strategy | Implement Minimax for unbeatable AI |
| No input type guard | All games | `scanner.nextInt()` throws `InputMismatchException` on non-integer input | Wrap with `hasNextInt()` check or try/catch |
| No overshooting win | Snakes & Ladders | Roll beyond 100 is silently skipped, turn wasted | Inform player their roll was wasted |
| No re-run option | All games | Game exits after one match | Wrap `main()` in "play again?" loop |
| Score not shown mid-game | Memory Match | Scores only printed after each move, not at turn start | Print scores in the turn banner |

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Build Tool | Apache Maven 3.x |
| I/O | `java.util.Scanner` |
| Randomization | `java.util.Random` |
| Data Structures | `char[][]`, `boolean[][]`, `HashMap<Integer,Integer>` |
| Design Pattern | Procedural / Static method pipeline |
| Dependencies | None (pure Java SE) |

---

## 🚧 Possible Enhancements

- 🤖 **Minimax AI** for Tic Tac Toe — make the CPU unbeatable
- 🔁 **Play Again** loop after each game ends
- 🏆 **Score tracker** across multiple rounds
- 🌐 **Unified launcher** — one `main()` that lets you pick which game to play
- 🖥️ **JavaFX GUI** — convert board rendering to a visual interface
- 🌍 **Networked multiplayer** — play Connect 4 or Tic Tac Toe over a socket

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

<div align="center">
  <em>4 games. 4 files. Zero dependencies. Pure Java. 🎮</em>
</div>
