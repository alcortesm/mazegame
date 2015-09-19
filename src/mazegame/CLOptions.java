package mazegame;

import mazegame.client.Language;
import mazegame.util.Array;

class CLOptions {

    private Language language;

    // enum for parsing the main args
    private enum Opts {
        LANG("-l");

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
                    "Missing -l argument");
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
            throw new IllegalArgumentException("Repeated option -l");
        }
        return removePair(args, i);
    }

    // remove the nth and the nth+1 element from args
    private String[] removePair(String[] args, int n) {
        int len = args.length;
        int newLen = len - 2;
        String[] r = new String[newLen];
        for (int i=0; i<n; i++) {
            r[i] = args[i];
        }
        for (int i=n+2; i<len; i++) {
            r[i-2] = args[i];
        }
        return r;
    }

    public Language getLanguage() {
        return language;
    }
}
