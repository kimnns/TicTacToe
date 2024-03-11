package files;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class playTicTacToe {
    public static void main(String args[]) throws Exception {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        GameBoard game = new GameBoard(); // makes a blank gameboard
        String player1, player2;
        int turn = 0;
        int index;
        boolean errorFlag;
        boolean gameOver = false;
        System.out.print("Please Enter Player 1's Name: ");
        player1 = keyboard.readLine();
        System.out.println(player1 + ", you are X's");
        System.out.print("Please Enter Player 2's Name: ");
        player2 = keyboard.readLine();
        System.out.println(player2 + ", you are O's");
        System.out.println("Press ENTER to continue");
        keyboard.readLine();
        System.out.println("\n\n");
        game.drawBoard();
        System.out.println("\n\n");
        while (!gameOver) {
            errorFlag = false;
            String currentPlayer = turn % 2 == 0 ? "X" : "O";
            String currentPlayerName = turn % 2 == 0 ? player1 : player2;
            do {
                System.out.print(currentPlayerName + ", enter your choice (0-8): ");
                index = Integer.parseInt(keyboard.readLine()); // Assumes a valid number is entered
                errorFlag = game.play(currentPlayer, index);
                if (errorFlag == false) {
                    System.out.println("That square is already taken or invalid.  Try again");
                }
            } while (errorFlag == false); // makes sure player enters a square not yet used
            System.out.println("\n\n");
            game.drawBoard();
            System.out.println("\n\n");
            if (game.checkWin(currentPlayer)) {
                System.out.println(currentPlayerName + ", YOU WIN!!");
                gameOver = true;
            }
            turn++;
            if (turn == 9 && !gameOver) {
                gameOver = true;
                System.out.println("DRAW");
            }
        } // end while
    } // end main
} // end class

