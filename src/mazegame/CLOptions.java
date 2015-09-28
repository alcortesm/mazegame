// This class holds all the options available from the command line.
//
// It gets initialized with the application defaults and the process
// the command line array of strings to configure each option.

package mazegame;

import mazegame.client.Language;
import mazegame.util.Array;
import mazegame.server.ServerSpec;
import mazegame.server.ServerSpecTest;
import mazegame.server.ServerSpecEmpty;
import mazegame.server.ServerSpecPrim;
import mazegame.server.ServerSpecDepthFirst;

class CLOptions {

    static final Language DEFAULT_LANGUAGE = Language.ENGLISH;
    static final int DEFAULT_ROWS = 31;
    static final int DEFAULT_COLS = 41;
    static final int DEFAULT_TRAIL_CAPACITY = 5;
    static final ClientChoice DEFAULT_CLIENT_CHOICE = ClientChoice.GUI;

    private Language language;
    private ServerSpec serverSpec;
    private int rows;
    private int cols;
    private int trailCapacity;
    private ClientChoice clientChoice;

    // enum for parsing the main args
    private enum Opts {
        LANG("-l"), // followed by ENGLISH or SPANISH
        SPEC("-s"), // followed by TEST, EMPTY, PRIM, DEPTHFIRST
        ROWS("-r"), // followed by a number
        COLS("-c"), // followed by a number
        TRAIL("-t"), // followed by a number
        UI("-ui"); // followed by (ClientChoice) TUI or GUI

        private final String text;

        private Opts(final String text) {
            this.text = text;
        }

        public String toString() {
            return text;
        }
    }

    // the set of default options
    private CLOptions() {
        language = DEFAULT_LANGUAGE;
        rows = DEFAULT_ROWS;
        cols = DEFAULT_COLS;
        trailCapacity = DEFAULT_TRAIL_CAPACITY;
        clientChoice = DEFAULT_CLIENT_CHOICE;
    }

    protected CLOptions(String args[]) {
        this();
        if (args.length == 0) {
            return;
        }
        // going throw all the command line arguments
        // again and again is not efficient, but it is simple
        // enough for the students and it is a good chance
        // to build an extraction filter.
        args = extractAndSetClientChoice(args);
        args = extractAndSetLanguage(args);
        args = extractAndSetNumRows(args);
        args = extractAndSetNumCols(args);
        args = extractAndSetTrailCapacity(args);
        args = extractAndSetServerSpec(args);
        if (serverSpec == null) {
            serverSpec = new ServerSpecDepthFirst(
                    rows, cols, trailCapacity);
        }
        // serverSpec detection comes last to use the
        // appropiate rows, columns and trail capacity.
        if (args.length != 0) {
            throw new IllegalArgumentException(
                    "Unrecognized argument: " + args[0]);
        }
    }

    private String[] extractAndSetClientChoice(String[] args) {
        int i = Array.firstIndexOf(Opts.UI.toString(), args, 0);
        if (i == -1) {
            return args;
        }
        // if no more arguments, complain about a missing argument
        if (i+1 == args.length) {
            throw new IllegalArgumentException(
                    "Missing " + Opts.UI.toString() + " argument");
        }
        // check if the language is supported
        ClientChoice[] clientChoices = ClientChoice.values();
        boolean supported = false;
        for (int j=0; j<clientChoices.length; j++) {
            if (clientChoices[j].toString().equals(args[i+1])) {
                supported = true;
                this.clientChoice = clientChoices[j];
                break;
            }
        }
        if (! supported) {
            throw new IllegalArgumentException(
                    "Unrecognized client choice: " + args[i+1]);
        }
        // check for repeated command
       int repeated = Array.firstIndexOf(Opts.UI.toString(),
                args, i+2);
        if (repeated != -1) {
            throw new IllegalArgumentException(
                    "Repeated option " + Opts.UI.toString());
        }
        return Array.remove(args, i, 2);
    }

    private String[] extractAndSetLanguage(String[] args) {
        // find if there is an "-l" option
        int i = Array.firstIndexOf(Opts.LANG.toString(), args, 0);
        if (i == -1) {
            return args;
        }
        // if no more arguments, complain about a missing argument
        if (i+1 == args.length) {
            throw new IllegalArgumentException(
                    "Missing " + Opts.LANG.toString() + " argument");
        }
        // check if the language is supported
        Language[] langs = Language.values();
        boolean supported = false;
        for (int j=0; j<langs.length; j++) {
            if (langs[j].toString().equals(args[i+1])) {
                supported = true;
                this.language = langs[j];
                break;
            }
        }
        if (! supported) {
            throw new IllegalArgumentException(
                    "Unrecognized language: " + args[i+1]);
        }
        // check for repeated command
       int repeated = Array.firstIndexOf(Opts.LANG.toString(),
                args, i+2);
        if (repeated != -1) {
            throw new IllegalArgumentException("Repeated option " + Opts.LANG.toString());
        }
        return Array.remove(args, i, 2);
    }

    private String[] extractAndSetServerSpec(String[] args) {
        int i = Array.firstIndexOf(Opts.SPEC.toString(), args, 0);
        if (i == -1) {
            return args;
        }
        // if no more arguments, complain about a missing argument
        if (i+1 == args.length) {
            throw new IllegalArgumentException(
                    "Missing " + Opts.SPEC.toString() + " argument");
        }
        // check if the spec is among the supported ones
        switch (args[i+1]) {
            case "TEST":
                serverSpec = new ServerSpecTest(trailCapacity);
                break;
            case "EMPTY":
                serverSpec =
                    new ServerSpecEmpty(rows, cols, trailCapacity);
                break;
            case "PRIM":
                serverSpec =
                    new ServerSpecPrim(rows, cols, trailCapacity);
                break;
            case "DEPTHFIRST":
                serverSpec =
                    new ServerSpecDepthFirst(rows, cols, trailCapacity);
                break;
            default:
            throw new IllegalArgumentException(
                    "Unrecognized spec: " + args[i+1]);
        }
        // check for repeated command
        int repeated = Array.firstIndexOf(Opts.SPEC.toString(),
                args, i+2);
        if (repeated != -1) {
            throw new IllegalArgumentException("Repeated option " + Opts.SPEC.toString());
        }
        return Array.remove(args, i, 2);
    }

    private String[] extractAndSetNumRows(String[] args) {
        int i = Array.firstIndexOf(Opts.ROWS.toString(), args, 0);
        if (i == -1) {
            return args;
        }
        // if no more arguments, complain about a missing argument
        if (i+1 == args.length) {
            throw new IllegalArgumentException(
                    "Missing " + Opts.ROWS.toString() + " argument");
        }
        // check if the number of rows is supported
        try {
            rows = Integer.parseInt(args[i+1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Unrecognized number format: " + args[i+1]);
        }
        if (rows < 0) {
            throw new IllegalArgumentException(
                    "Negative number of rows: " + args[i+1]);
        }
        // check for repeated command
        int repeated = Array.firstIndexOf(Opts.ROWS.toString(),
                args, i+2);
        if (repeated != -1) {
            throw new IllegalArgumentException("Repeated option " + Opts.ROWS.toString());
        }
        return Array.remove(args, i, 2);
    }

    private String[] extractAndSetNumCols(String[] args) {
        int i = Array.firstIndexOf(Opts.COLS.toString(), args, 0);
        if (i == -1) {
            return args;
        }
        // if no more arguments, complain about a missing argument
        if (i+1 == args.length) {
            throw new IllegalArgumentException(
                    "Missing " + Opts.COLS.toString() + " argument");
        }
        // check if the number of rows is supported
        try {
            cols = Integer.parseInt(args[i+1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Unrecognized number format: " + args[i+1]);
        }
        if (cols < 0) {
            throw new IllegalArgumentException(
                    "Negative number of columns: " + args[i+1]);
        }
        // check for repeated command
        int repeated = Array.firstIndexOf(Opts.COLS.toString(),
                args, i+2);
        if (repeated != -1) {
            throw new IllegalArgumentException("Repeated option " + Opts.COLS.toString());
        }
        return Array.remove(args, i, 2);
    }

    private String[] extractAndSetTrailCapacity(String[] args) {
        int i = Array.firstIndexOf(Opts.TRAIL.toString(), args, 0);
        if (i == -1) {
            return args;
        }
        // if no more arguments, complain about a missing argument
        if (i+1 == args.length) {
            throw new IllegalArgumentException(
                    "Missing " + Opts.TRAIL.toString() + " argument");
        }
        // check if the number of rows is supported
        try {
            trailCapacity = Integer.parseInt(args[i+1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Unrecognized number format: " + args[i+1]);
        }
        if (trailCapacity < 0) {
            throw new IllegalArgumentException(
                    "Negative number of trailCapacity: " + args[i+1]);
        }
        // check for repeated command
        int repeated = Array.firstIndexOf(Opts.TRAIL.toString(),
                args, i+2);
        if (repeated != -1) {
            throw new IllegalArgumentException("Repeated option " + Opts.TRAIL.toString());
        }
        return Array.remove(args, i, 2);
    }

    public Language getLanguage() { return language; }
    public ServerSpec getServerSpec() {
        return serverSpec;
    }
    public int getTrailCapacity() { return trailCapacity; }
    public ClientChoice getClientChoice() { return clientChoice; }
}
