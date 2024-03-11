package files;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class playTicTacToe {
    public static void main(String args[]) throws Exception {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        GameBoard game = new GameBoard();
        String player1, player2;
        int turn = 0;
        int index;
        boolean errorFlag;
        boolean gameOver = false;
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
                        System.out.println("That square is already taken or invalid.  Try again");
                    }
                } while (errorFlag == false);
            } else {
                index = makeSmartMove(game); // Call the makeSmartMove method to determine the computer's move
                errorFlag = game.play(currentPlayer, index);
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
            }
            turn++;
            if (turn == 9 && !gameOver) {
                gameOver = true;
                System.out.println("DRAW");
            }
        }
    }

    private static int makeSmartMove(GameBoard game) {
        // Check if the computer can win in the next move
        for (int i = 0; i < 9; i++) {
            if (!game.board[i].owned()) {
                game.board[i].setOwner("O");
                if (game.checkWin("O")) {
                    game.board[i].setOwner(null);
                    return i;
                }
                game.board[i].setOwner(null);
            }
        }

        // Check if the human can win in the next move and block it
        for (int i = 0; i < 9; i++) {
            if (!game.board[i].owned()) {
                game.board[i].setOwner("X");
                if (game.checkWin("X")) {
                    game.board[i].setOwner(null);
                    return i;
                }
                game.board[i].setOwner(null);
            }
        }

        // Choose a random available move
        Random random = new Random();
        int index;
        do {
            index = random.nextInt(9);
        } while (game.board[index].owned());
        return index;
    }
}