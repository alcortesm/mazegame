// An Icon is a visual representation of a "game object".
//
// In the TUI version of the game the icon will be rendered
// as a char.
//
// The TUI version of the game assumes the player is using a
// monospace font for her console. To be able to render the game
// properly, this TuiIcons must be rendered as single width
// characters.
//
// As Java chars are UTF-16 encoded representations of UTF graphemes,
// there are several reasons why we cannot use a simple Java char as
// a TextIcon:
//
// 1. Not all platforms supports UTF. As we want to make our code
// portable we must use ASCII characters instead, as ASCII is the
// most widely supported charset.
//
// 2. Even if the platform supported UTF, there are some UTF
// graphemes (fullwidth characters) that are often rendered in
// monospace fonts as twice the wide as ordinary chars. This will
// break our initial assumption about the constant width of TuiIcons
// renderizations.
//
// 3. Of all the ASCII characters, we should only use printable
// characters, otherwise the TUI version of the game will be very
// confusing.

package mazegame.server;

public class Icon {

    private char c;

    public static final Icon EXTERNAL_WALL = new Icon('#');

    // ASCII printable characters are from 32 to 126 (both included)
    private static boolean isAsciiPrintable(char c) {
        return (c >= 32) && (c <= 126);
    }

    public Icon(char c) {
        if (! isAsciiPrintable(c)) {
            throw new IllegalArgumentException(
                    "Non ASCII printable icon");
        }
        this.c = c;
    }

    public char getChar() {
        return c;
    }

    public String toString() {
        return "Icon['" + c + "']";
    }
}
