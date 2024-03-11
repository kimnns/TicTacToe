package files;

public class Player {

    private String name;
    private String mark;

    public Player(String name, String mark) {
        this.name = name; // Initialize the player's name
        this.mark = mark; // Initialize the player's mark (e.g., "X" or "O")
    }

    public String getName() {
        return name; // Returns the player's name
    }

    public String getMark() {
        return mark; // Returns the player's mark
    }

    public int makeMove(GameBoard board) {
        int move;
        do {
            move = (int)(Math.random() * 9); // Generate a random move between 0 and 8
        } while (board.play(mark, move) == false); // Keep generating moves until a valid move is made
        return move; // Return the valid move made by the player
    }
}