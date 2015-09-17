package mazegame.server;

public class ClientView {

    public ClientView() {}

    public boolean isGameOver() {
        return false;
    }

    public boolean isHeroAlive() {
        return true;
    }

    public String lastMsgResult() {
        return "TODO";
    }

    public Icon[][] getTopView() {
        Icon[][] topView = new Icon[3][3];
        topView[0][0] = Icon.WALL;
        topView[0][1] = Icon.WALL;
        topView[0][2] = Icon.WALL;
        topView[1][0] = Icon.WALL;
        topView[1][1] = Icon.WALL;
        topView[1][2] = Icon.WALL;
        topView[2][0] = Icon.WALL;
        topView[2][1] = Icon.WALL;
        topView[2][2] = Icon.WALL;
        return topView;
    }


}
