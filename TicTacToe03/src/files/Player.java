package files;

public class Player {

    private String name; // The name of the player
    private String mark; // The mark (e.g., "X" or "O") associated with the player

    // Constructor for the Player class
    public Player(String name, String mark) {
        this.name = name; // Initialize the player's name
        this.mark = mark; // Initialize the player's mark
    }

    // Getter method to retrieve the name of the player
    public String getName() {
        return name; // Return the player's name
    }

    // Getter method to retrieve the mark associated with the player
    public String getMark() {
        return mark; // Return the player's mark
    }

    // Method for the player to make a move on the game board
    public int makeMove(GameBoard board) {
        int move;
        do {
            move = (int)(Math.random() * 9); // Generate a random move between 0 and 8
        } while (board.play(mark, move) == false); // Keep generating a random move until a valid move is made
        return move; // Return the move made by the player
    }
}