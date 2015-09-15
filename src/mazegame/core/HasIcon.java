// The HasIcon interface has the methods to access the
// visual representation of an object in he maze.

package mazegame.core;

interface HasIcon {
    // Returns the char representation of the icon of the
    // object, for the TUI. This will be usueful to show
    // the maze map in the TUI.
    char getIconChar();
}
