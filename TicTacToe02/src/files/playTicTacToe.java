package files;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class playTicTacToe {
    public static void main(String args[]) throws Exception {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        GameBoard game = new GameBoard(); // Create a blank game board
        String player1, player2;
        int turn = 0;
        int index;
        boolean errorFlag;
        boolean gameOver = false;

        // Get player names
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
                // Human player's turn
                do {
                    System.out.print(currentPlayerName + ", enter your choice (0-8): ");
                    index = Integer.parseInt(keyboard.readLine()); // Assumes a valid number is entered
                    errorFlag = game.play(currentPlayer, index);
                    if (errorFlag == false) {
                        System.out.println("That square is already taken or invalid.  Try again");
                    }
                } while (errorFlag == false); // Ensure the player enters a valid and available square
            } else {
                // Computer player's turn
                do {
                    Random random = new Random();
                    index = random.nextInt(9);
                    errorFlag = game.play(currentPlayer, index);
                } while (errorFlag == false); // Ensure the computer enters a valid and available square
                System.out.println(currentPlayerName + " played at " + index);
            }

            System.out.println("\n\n");
            game.drawBoard();
            System.out.println("\n\n");

            if (game.checkWin(currentPlayer)) {
                // Check if the current player has won
                if (currentPlayer.equals("X")) {
                    System.out.println(currentPlayerName + ", YOU WIN!!");
                } else {
                    System.out.println(currentPlayerName + " WINS!!");
                }
                gameOver = true; // Game over, someone has won
            }

            turn++;

            if (turn == 9 && !gameOver) {
                // If all squares are filled and no winner, it's a draw
                gameOver = true;
                System.out.println("DRAW");
            }
        } // end while
    } // end main
} // end class