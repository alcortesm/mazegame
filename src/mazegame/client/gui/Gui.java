// A simple GUI client.
//
// Students do not know how to use the canvas, so this GUI will only
// use standard widgets.
//
// Student know a pretty limited set of Layouts. For example
// GridBagLayout will be more interesting than a GridLayout.
//
// Keyboard events will be more interesting than using JButtons but
// keyboard events are covered by the course curriculum.

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
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Component;

import mazegame.client.Client;
import mazegame.server.ServerSpec;
import mazegame.server.Server;
import mazegame.server.ClientView;
import mazegame.server.Icon;
import mazegame.util.Direction;
import mazegame.util.Queue;
import mazegame.server.Update;

public class Gui extends JFrame implements Client, ActionListener {

    private static final Color BUTTON_BACKGROUND_COLOR = Color.CYAN;
    private static final Color BUTTON_FOREGROUND_COLOR = Color.BLACK;

    private static final Color WALL_COLOR = Color.BLACK;
    private static final Color SPACE_COLOR = Color.GRAY;
    private static final Color HERO_COLOR = Color.BLUE;
    private static final Color END_COLOR = Color.GREEN;
    private static final Color FOOTPRINT_COLOR = Color.LIGHT_GRAY;

    private Server server;
    private JPanel controls;
    private JPanel map;
    private JButton goNorth;
    private JButton goSouth;
    private JButton goEast;
    private JButton goWest;
    private JButton undo;
    private JLabel[][] mapTiles;

    private static final Dimension TILE_DIM = new Dimension(10, 10);

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
        ClientView view = server.getClientView();
        updateMap(view.getTopView());
        triggerGameOver(view.isGameOver());
        pack();
        setVisible(true);
    }

    private void addControls() {
        goNorth = new JButton("N");
        goNorth.setBackground(BUTTON_BACKGROUND_COLOR);
        goNorth.setForeground(BUTTON_FOREGROUND_COLOR);
        goSouth = new JButton("S");
        goSouth.setBackground(BUTTON_BACKGROUND_COLOR);
        goSouth.setForeground(BUTTON_FOREGROUND_COLOR);
        goEast = new JButton("E");
        goEast.setBackground(BUTTON_BACKGROUND_COLOR);
        goEast.setForeground(BUTTON_FOREGROUND_COLOR);
        goWest = new JButton("W");
        goWest.setBackground(BUTTON_BACKGROUND_COLOR);
        goWest.setForeground(BUTTON_FOREGROUND_COLOR);

        undo = new JButton("Back");
        undo.setBackground(BUTTON_BACKGROUND_COLOR);
        undo.setForeground(BUTTON_FOREGROUND_COLOR);
        undo.setAlignmentY(Component.CENTER_ALIGNMENT);
        undo.setAlignmentX(Component.CENTER_ALIGNMENT);

        goNorth.addActionListener(this);
        goSouth.addActionListener(this);
        goEast.addActionListener(this);
        goWest.addActionListener(this);
        undo.addActionListener(this);

        FlowLayout controlWrapperLayout =
            new FlowLayout(FlowLayout.CENTER);
        controlWrapperLayout.setHgap(40);
        JPanel controlWrapper = new JPanel(controlWrapperLayout);
        controlWrapper.add(undo);

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
        controlWrapper.add(controls);
        add(controlWrapper, BorderLayout.SOUTH);
    }

    // adds an initial empty map
    // it will get update later using
    // updateMap().
    private void addMap() {
        map = new JPanel();
        add(map, BorderLayout.CENTER);
    }

    private void updateMap(Icon[][] icons) {
        int numRows = icons.length;
        int numCols = icons[0].length;
        map.removeAll();
        map.setLayout(new GridLayout(numRows+2, numCols+2));
        mapTiles = new JLabel[numRows][numCols];
        // north wall
        for (int c=0; c<numCols+2; c++) {
            map.add(newLabel(Icon.WALL));
        }
        for (int r=0; r<numRows; r++) {
            // east wall
            map.add(newLabel(Icon.WALL));
            // map contents
            for (int c=0; c<numCols; c++) {
                mapTiles[r][c] = newLabel(icons[r][c]);
                map.add(mapTiles[r][c]);
            }
            // west wall
            map.add(newLabel(Icon.WALL));
        }
        // south wall
        for (int c=0; c<numCols+2; c++) {
            map.add(newLabel(Icon.WALL));
        }
        map.validate();
        map.repaint();
    }

    private static Color iconToColor(Icon icon) {
        switch (icon) {
            case EMPTY:
                return SPACE_COLOR;
            case WALL:
                return WALL_COLOR;
            case END:
                return END_COLOR;
            case HERO:
                return HERO_COLOR;
            case FOOTPRINT:
                return FOOTPRINT_COLOR;
            default:
                throw new IllegalArgumentException(icon.toString());
        }
    }

    private static JLabel newLabel(Icon icon) {
        JLabel label = new JLabel();
        label.setMinimumSize(TILE_DIM);
        label.setPreferredSize(TILE_DIM);
        label.setMaximumSize(TILE_DIM);
        label.setOpaque(true);
        label.setBackground(iconToColor(icon));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
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
        } else if (b == undo) {
            server.undo();
        } else {
            throw new UnsupportedOperationException(
                    "Unsupported button: " + b.getText());
        }
        Queue<Update> updates = server.getUpdates();
        showUpdates(updates);
        triggerGameOver(server.isGameOver());
    }

    private void showUpdates(Queue<Update> updates) {
        while(! updates.isEmpty()) {
            Update update = updates.dequeue();
            int row = update.getRow();
            int col = update.getCol();
            Icon icon = update.getIcon();
            JLabel label = mapTiles[row][col];
            label.setBackground(iconToColor(icon));
        }
    }

    private void triggerGameOver(boolean gameOver) {
        if (! gameOver) {
            return;
        }
        System.out.println("Game Over");
        dispatchEvent(new WindowEvent(
                    this, WindowEvent.WINDOW_CLOSING));
    }
}
