package files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class playTicTacToe {
    private static final double RANDOM_THRESHOLD = 0.2;
    private static final double LEARNING_RATE = 0.1;
    private static final double DISCOUNT_FACTOR = 0.9;

    public static void main(String args[]) throws Exception {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        GameBoard game = new GameBoard(); // Create a new game board
        String player1, player2;
        int turn = 0;
        int index;
        boolean errorFlag;
        boolean gameOver = false;
        double[] qValues = loadQValues(); // Load Q-values from a file

        System.out.print("Please Enter Your Name: ");
        player1 = keyboard.readLine();
        System.out.println(player1 + ", you are X's");
        player2 = "Computer";
        System.out.println(player2 + ", you are O's");
        System.out.println("Press ENTER to continue");
        keyboard.readLine();
        System.out.println("\n\n");
        game.drawBoard();
        System.out.println("\n\n");

        while (!gameOver) {
            String currentPlayer = turn % 2 == 0 ? "X" : "O";
            String currentPlayerName = turn % 2 == 0 ? player1 : player2;

            if (currentPlayer.equals("X")) {
                do {
                    System.out.print(currentPlayerName + ", enter your choice (0-8): ");
                    index = Integer.parseInt(keyboard.readLine());
                    errorFlag = game.play(currentPlayer, index);
                    if (errorFlag == false) {
                        System.out.println("That square is already taken or invalid. Try again");
                    }
                } while (errorFlag == false);
            } else {
                if (makeRandomMove()) {
                    do {
                        index = new Random().nextInt(9);
                        errorFlag = game.play(currentPlayer, index);
                    } while (errorFlag == false);
                } else {
                    index = makeSmartMove(game, qValues);
                    errorFlag = game.play(currentPlayer, index);
                }
                System.out.println(currentPlayerName + " played at " + index);
            }

            System.out.println("\n\n");
            game.drawBoard();
            System.out.println("\n\n");

            if (game.checkWin(currentPlayer)) {
                if (currentPlayer.equals("X")) {
                    System.out.println(currentPlayerName + ", YOU WIN!!");
                } else {
                    System.out.println(currentPlayerName + " WINS!!");
                }
                gameOver = true;
                if (!currentPlayer.equals("X")) {
                    updateQValue(game, qValues, index);
                }
            }

            turn++;

            if (turn == 9 && !gameOver) {
                gameOver = true;
                System.out.println("DRAW");
            }
        }

        storeQValues(qValues); // Store Q-values in a file
    }

    private static boolean makeRandomMove() {
        return Math.random() < RANDOM_THRESHOLD; // Determines if a random move should be made
    }

    private static int makeSmartMove(GameBoard game, double[] qValues) {
        int bestMove = -1;
        double bestQValue = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < 9; i++) {
            if (!game.board[i].owned()) {
                double qValue = qValues[i];
                if (qValue > bestQValue) {
                    bestQValue = qValue;
                    bestMove = i;
                }
            }
        }
        return bestMove; // Returns the best move based on Q-values
    }

    private static void updateQValue(GameBoard game, double[] qValues, int move) {
        double reward = 0;
        if (game.checkWin("O")) {
            reward = 1; // Assign a reward of 1 if the computer wins
        } else if (game.checkWin("X")) {
            reward = -1; // Assign a reward of -1 if the human wins
        }

        qValues[move] += LEARNING_RATE * (reward - qValues[move]); // Update the Q-value for the chosen move
    }

    private static void storeQValues(double[] qValues) {
        try (FileWriter writer = new FileWriter("qvalues.txt")) {
            for (double qValue : qValues) {
                writer.write(Double.toString(qValue)); // Write each Q-value to the file
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double[] loadQValues() {
        double[] qValues = new double[9];
        try (BufferedReader reader = new BufferedReader(new FileReader("qvalues.txt"))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                qValues[index++] = Double.parseDouble(line); // Load each Q-value from the file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return qValues; // Return the loaded Q-values
    }
}