package mazegame;

import org.junit.Test;
import static org.junit.Assert.*;

public class CLOptionsTest {

    private static String arrayToString(String[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i=0; i<a.length; i++) {
            sb.append("\"");
            sb.append(a[i]);
            sb.append("\"");
            if (i < a.length-1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static void tryNew(String[] args) {
        try {
            new CLOptions(args);
        } catch (Exception e) {
            fail("Exception thrown: " + e.toString() +
                    ". Args was: " +
                    arrayToString(args));
        }
    }

    @Test
    public void ctorMustWork() {
        String[] args;
        {
            args = new String[]{};
            tryNew(args);
        }
        {
            args = new String[]{"-l", "ENGLISH"};
            tryNew(args);
        }
        {
            args = new String[]{"-l", "SPANISH"};
            tryNew(args);
        }
    }

    @Test(expected=NullPointerException.class)
    public void ctorMustThrowOnNull() {
        new CLOptions(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void ctorMustThrowOnBadStrings() {
        String[] args;
        {
            args = new String[]{""};
            new CLOptions(args);
        }
        {
            args = new String[]{"foo"};
            new CLOptions(args);
        }
        {
            args = new String[]{"-l"};
            new CLOptions(args);
        }
        {
            args = new String[]{"-l", "FRENCH"};
            new CLOptions(args);
        }
        {
            args = new String[]{"-l", "ENGLISH", ""};
            new CLOptions(args);
        }
        {
            args = new String[]{"-l", "ENGLISH", "-l"};
            new CLOptions(args);
        }
        {
            args = new String[]{"-l", "ENGLISH", "-l", "SPANISH"};
            new CLOptions(args);
        }
        {
            args = new String[]{"-l", "ENGLISH", "foo"};
            new CLOptions(args);
        }
    }
}


