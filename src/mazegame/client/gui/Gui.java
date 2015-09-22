package mazegame.client.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Container;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import mazegame.client.Client;
import mazegame.server.ServerSpec;
import mazegame.server.Server;
import mazegame.server.ClientView;
import mazegame.server.Icon;

public class Gui implements Client {

    private Server server;
    private JFrame frame;
    private JPanel map;

    public Gui(ServerSpec serverSpec) {
        server = new Server(serverSpec);
    }

    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

    private void createAndShowGui() {
        frame = new JFrame("MazeGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fillMap(server.getClientView());
        frame.add(map, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private void fillMap(ClientView view) {
        Icon[][] icons = view.getTopView();
        int numRows = icons.length;
        int numCols = icons[0].length;
        map = new JPanel(
                new GridLayout(numRows, numCols));
        for (int r=0; r<numRows; r++) {
            for (int c=0; c<numCols; c++) {
                map.add(new JLabel(
                            iconToString(icons[r][c])));
            }
        }
    }

    private static String iconToString(Icon icon) {
        switch (icon) {
            case EMPTY:
                return " ";
            case WALL:
                return "#";
            case END:
                return "e";
            case HERO:
                return "@";
            case FOOTPRINT:
                return ".";
            default:
                throw new IllegalArgumentException(icon.toString());
        }
    }
}
