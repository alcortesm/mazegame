package mazegame;

import mazegame.client.Language;
import mazegame.util.Array;
import mazegame.server.ServerSpec;
import mazegame.server.ServerSpecTest;
import mazegame.server.ServerSpecEmpty;

class CLOptions {

    private Language language;
    private ServerSpec serverSpec;
    private int rows;
    private int cols;

    // enum for parsing the main args
    private enum Opts {
        LANG("-l"), // followed by ENGLISH or SPANISH
        SPEC("-s"), // followed by TEST or EMPTY
        ROWS("-r"), // followed by a number
        COLS("-c"); // followed by a number

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
        language = Language.ENGLISH;
        serverSpec = new ServerSpecTest();
        rows = 10;
        cols = 20;
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
        args = extractAndSetLanguage(args);
        args = extractAndSetServerSpec(args);
        if (args.length != 0) {
            throw new IllegalArgumentException(
                    "Unrecognized argument: " + args[0]);
        }
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
                serverSpec = new ServerSpecTest();
                break;
            case "EMPTY":
                serverSpec = new ServerSpecEmpty(rows, cols);
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

    public Language getLanguage() { return language; }
    public ServerSpec getServerSpec() { return serverSpec; }
}
