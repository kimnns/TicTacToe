package files;

public class GameTile {

    // The 'owner' variable represents the player who owns the tile
    protected String owner;

    // Constructor for the GameTile class
    public GameTile() {
        // Initialize the 'owner' variable to null, indicating that no player owns the tile
        owner = null;
    }

    // Getter method to retrieve the owner of the tile
    public String getOwner() {
        return owner;
    }

    // Setter method to set the owner of the tile
    public void setOwner(String player) {
        owner = player;
    }

    // Method to check if the tile is owned by any player
    public boolean owned() {
        // If the 'owner' variable is not null, it means the tile is owned by a player
        return owner != null;
    }
}