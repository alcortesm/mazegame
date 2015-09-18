package mazegame.client;

import org.junit.Test;
import static org.junit.Assert.*;

public class LocalizerTest {

    @Test
    public void shouldWorkForAllLanguages() {
        Language[] langs = Language.values();
        MsgToUsr[] msg = MsgToUsr.values();
        Localizer localizer;
        try {
            for (int l=0; l<langs.length; l++) {
                localizer = new Localizer(langs[l]);
            }
        } catch (Exception e) {
            fail("Exception thrown: " + e.toString());
        }
    }

    @Test(expected=NullPointerException.class)
    public void shouldThrowOnNull() {
        new Localizer(null);
    }
}
