package mazegame.server;

import mazegame.core.Maze;
import mazegame.core.Map;
import mazegame.core.Thing;
import mazegame.core.Place;
import mazegame.core.Tile;

public class ClientView {

    private Icon[][] photo;
    private boolean  isFinished;
    private String   lastCommandResultDescription;

    public int getNumRows() {
        return photo.length;
    }

    public int getNumCols() {
        return photo[0].length;
    }

    public Icon getIconAt(int row, int col) {
        return photo[row][col];
    }

    public boolean isFinished() {
        return isFinished;
    }

    public String lastCommandResultDescription() {
        return lastCommandResultDescription;
    }

    ClientView(Maze maze) {
        if (maze == null) {
            throw new NullPointerException("maze");
        }

        Map map = maze.getMap();
        Thing player = maze.getPlayer();
        Thing start = maze.getStart();
        Thing end = maze.getEnd();

        isFinished = player.sharePlaceWith(end);

        lastCommandResultDescription = "NOT IMPLEMENTED";

        photo = new Icon[map.getNumRows()][map.getNumCols()];
        for (int r=0; r<maze.getMap().getNumRows(); r++) {
            for (int c=0; c<maze.getMap().getNumCols(); c++) {
                Place here = new Place(r, c, map);
                if (player.isAt(here)) {
                    photo[r][c] = player.getIcon();
                    continue;
                }
                if (end.isAt(here)) {
                    photo[r][c] = end.getIcon();
                    continue;
                }
                if (start.isAt(here)) {
                    photo[r][c] = start.getIcon();
                    continue;
                }
                photo[r][c] = map.getTile(r, c).getIcon();
            }
        }
    }
}
