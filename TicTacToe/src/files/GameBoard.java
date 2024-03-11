package files;

public class GameBoard {

    GameTile[] board;

    public GameBoard(){
        board = new GameTile[9];
        for (int i = 0; i < 9; i++)
            board[i] = new GameTile();
    }

    public boolean play(String player, int tile){
        if (tile < 0 || tile > 8) {
            return false; // index out of bounds
        }
        if (!board[tile].owned()) {
            board[tile].setOwner(player);
            return true; // successful play
        }
        return false; // tile already owned
    }

    public boolean checkWin(String player){
        // check rows
        for (int i = 0; i < 9; i += 3) {
            if (board[i].getOwner() == player && board[i+1].getOwner() == player && board[i+2].getOwner() == player) {
                return true;
            }
        }
        // check columns
        for (int i = 0; i < 3; i++) {
            if (board[i].getOwner() == player && board[i+3].getOwner() == player && board[i+6].getOwner() == player) {
                return true;
            }
        }
        // check diagonals
        if (board[0].getOwner() == player && board[4].getOwner() == player && board[8].getOwner() == player) {
            return true;
        }
        if (board[2].getOwner() == player && board[4].getOwner() == player && board[6].getOwner() == player) {
            return true;
        }
        return false; // no win
    }

    public void drawBoard(){
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("-------------");
            }
            System.out.print("| " + board[i].getOwner() + " ");
            if (i % 3 == 2) {
                System.out.println("|");
            }
        }
        System.out.println("-------------");
    }
}
