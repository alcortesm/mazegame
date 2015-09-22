package mazegame.client.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Container;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import mazegame.client.Client;
import mazegame.server.ServerSpec;
import mazegame.server.Server;
import mazegame.server.ClientView;
import mazegame.server.Icon;
import mazegame.server.Direction;

public class Gui extends JFrame implements Client, ActionListener {

    private Server server;
    private JPanel controls;
    private JPanel map;
    private JButton goNorth;
    private JButton goSouth;
    private JButton goEast;
    private JButton goWest;

    public Gui(ServerSpec serverSpec) {
        super("MazeGame");
        server = new Server(serverSpec);
        addControls();
        addMap();
    }

    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

    private void createAndShowGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateMap(server.getClientView());
        pack();
        setVisible(true);
    }

    private void addControls() {
        goNorth = new JButton("N");
        goSouth = new JButton("S");
        goEast = new JButton("E");
        goWest = new JButton("W");

        goNorth.addActionListener(this);
        goSouth.addActionListener(this);
        goEast.addActionListener(this);
        goWest.addActionListener(this);

        JPanel controls = new JPanel(new GridLayout(3, 3));
        controls.add(new JLabel());
        controls.add(goNorth);
        controls.add(new JLabel());
        controls.add(goWest);
        controls.add(new JLabel());
        controls.add(goEast);
        controls.add(new JLabel());
        controls.add(goSouth);
        controls.add(new JLabel());
        add(controls, BorderLayout.SOUTH);
    }

    // adds an initial empty map
    // it will get update later using
    // updateMap().
    private void addMap() {
        map = new JPanel();
        add(map, BorderLayout.CENTER);
    }

    private void updateMap(ClientView view) {
        Icon[][] icons = view.getTopView();
        int numRows = icons.length;
        int numCols = icons[0].length;
        map.removeAll();
        map.setLayout(new GridLayout(numRows, numCols));
        for (int r=0; r<numRows; r++) {
            for (int c=0; c<numCols; c++) {
                map.add(new JLabel(
                            iconToString(icons[r][c])));
            }
        }
        validate();
        repaint();
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

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (! (o instanceof JButton)) {
            throw new IllegalArgumentException(
                    "e source was not a JButton");
        }
        JButton b = (JButton) o;
        if (b == goNorth) {
            server.moveHero(Direction.NORTH);
        } else if (b == goSouth) {
            server.moveHero(Direction.SOUTH);
        } else if (b == goEast) {
            server.moveHero(Direction.EAST);
        } else if (b == goWest) {
            server.moveHero(Direction.WEST);
        } else {
            throw new UnsupportedOperationException(
                    "Unsupported button: " + b.getText());
        }
        updateMap(server.getClientView());
    }
}
