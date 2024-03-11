package files;

public class GameTile {

    protected String owner; // The owner of the game tile

    public GameTile() {
        owner = null; // Initialize the owner as null, indicating that no player owns the tile initially
    }

    public String getOwner() {
        return owner; // Return the current owner of the tile
    }

    public void setOwner(String player) {
        owner = player; // Set the owner of the tile to the specified player
    }

    public boolean owned() {
        return owner != null; // Check if the tile is owned by any player (owner is not null)
    }
}