package files;

public class GameTile {


    protected String owner;


    public GameTile() {
        owner = null;
    }


    public String getOwner() {
        return owner;
    }


    public void setOwner(String player) {
        owner = player;
    }


    public boolean owned() {
        return owner != null;
    }

}
