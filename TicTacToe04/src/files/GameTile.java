package files;

public class GameTile {

    protected String owner;

    public GameTile() {
        owner = null; // Initially, the tile has no owner
    }

    public String getOwner() {
        return owner; // Returns the current owner of the tile
    }

    public void setOwner(String player) {
        owner = player; // Sets the owner of the tile to the specified player
    }

    public boolean owned() {
        return owner != null; // Returns true if the tile has an owner, false otherwise
    }
}