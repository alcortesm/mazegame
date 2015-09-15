package mazegame.server;

import mazegame.core.Maze;

public class ClientView {

    private Icon[][] photo;
    private boolean  isFinished;
    private String   lastCommandResultDescription;

    public int getNumRows() {
        return photo.length;
    }

    public int getNumColumns() {
        return photo[0].length;
    }

    public Icon getIconAt(int row, int column) {
        return photo[row][column];
    }

    public boolean isFinished() {
        return isFinished;
    }

    public String lastCommandResultDescription() {
        return lastCommandResultDescription;
    }

    ClientView(Maze maze) {
    }
}
