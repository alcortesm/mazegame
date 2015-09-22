package mazegame.client;

import mazegame.util.Array;

public class Localizer {

    // This is the translation database.
    private static final String GREETING_ENGLISH =
        System.lineSeparator() +
        "                  ###########################               " + System.lineSeparator() +
        "                  # Welcome to the MazeGame #               " + System.lineSeparator() +
        "                  ###########################               " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "            A tiny roguelike game for teaching Java.        " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "       (C) 2015 by Alberto Cortés (alcortesm@gmail.com)     " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "Press the ENTER key to begin...";

    private static final String GREETING_SPANISH =
        System.lineSeparator() +
        "                  #########################               " + System.lineSeparator() +
        "                  # Bienvenido a MazeGame #               " + System.lineSeparator() +
        "                  #########################               " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "     Un sencillo juego tipo `rogue` para enseñar Java.    " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "       (C) 2015 por Alberto Cortés (alcortesm@gmail.com)     " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "Pulse la tecla ENTER para comenzar...";

    private static final String CONGRATULATIONS_ENGLISH =
        System.lineSeparator() +
        "                        CONGRATULATIONS!                    " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "                           YOU WIN!                         " + System.lineSeparator() +
        System.lineSeparator();

    private static final String CONGRATULATIONS_SPANISH =
        System.lineSeparator() +
        "                        ¡FELICIDADES!                       " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "                          ¡GANASTE!                         " + System.lineSeparator() +
        System.lineSeparator();

    private static final String ASK_FOR_COMMAND_ENGLISH =
        System.lineSeparator() +
        "Write a command and press ENTER" + System.lineSeparator() +
        "('h' for help): ";

    private static final String ASK_FOR_COMMAND_SPANISH =
        System.lineSeparator() +
        "Escriba un comando y pulse ENTER" + System.lineSeparator() +
        "('h + ENTER' para acceder a la ayuda): ";

    private static final String HELP_ENGLISH =
        System.lineSeparator() +
        "Goal:" + System.lineSeparator() +
        "\tYour goal is to take the player from" + System.lineSeparator() +
        "\tthe starting point to the exit of the maze." + System.lineSeparator() +
        System.lineSeparator() +
        "Legend:" + System.lineSeparator() +
        "\t#   a wall" + System.lineSeparator() +
        "\t@   the hero" + System.lineSeparator() +
        "\te   the exit of the maze" + System.lineSeparator() +
        System.lineSeparator() +
        "Commands:" + System.lineSeparator() +
        "\th, help:   this help" + System.lineSeparator() +
        "\tn, north:  go north" + System.lineSeparator() +
        "\ts, south:  go south" + System.lineSeparator() +
        "\te, east:   go east" + System.lineSeparator() +
        "\tw, west:   go west" + System.lineSeparator() +
        "\tq, quit:   quit the game" + System.lineSeparator() +
        "\tx, exit:   alias for \"quit\"" + System.lineSeparator() +
        System.lineSeparator();

    private static final String HELP_SPANISH =
        System.lineSeparator() +
        "Objetivo del juego:" + System.lineSeparator() +
        "\tEl objetivo es mover al héroe dentro del" + System.lineSeparator() +
        "\tlaberinto hasta su salida." + System.lineSeparator() +
        System.lineSeparator() +
        "Leyenda:" + System.lineSeparator() +
        "\t#   una pared" + System.lineSeparator() +
        "\t@   el héroe" + System.lineSeparator() +
        "\te   la salida del laberinto" + System.lineSeparator() +
        System.lineSeparator() +
        "Comandos:" + System.lineSeparator() +
        "\th, help:   ayuda" + System.lineSeparator() +
        "\tn, north:  moverse hacia el norte" + System.lineSeparator() +
        "\ts, south:  moverse hacia el sur" + System.lineSeparator() +
        "\te, east:   moverse hacia el este" + System.lineSeparator() +
        "\tw, west:   moverse hacia el oeste" + System.lineSeparator() +
        "\tq, quit:   Salir del juego" + System.lineSeparator() +
        "\tx, exit:   un álias de \"quit\"" + System.lineSeparator() +
        System.lineSeparator();

    private static final String EXITING_ENGLISH = System.lineSeparator() + "Exiting..." + System.lineSeparator()
        + System.lineSeparator();

    private static final String EXITING_SPANISH = System.lineSeparator() + "Cerrando el juego..." + System.lineSeparator()
        + System.lineSeparator();

    private static final String UNKNOWN_COMMAND_ENGLISH = System.lineSeparator() + "Unknown command: \"";

    private static final String UNKNOWN_COMMAND_SPANISH = System.lineSeparator() + "Comando desconocido: \"";

    private static final String UNKNOWN_COMMAND_SUFIX_ENGLISH = "\"" + System.lineSeparator();

    private static final String UNKNOWN_COMMAND_SUFIX_SPANISH = "\"" + System.lineSeparator();

    private static final String MOVE_OK_ENGLISH = "OK";

    private static final String MOVE_OK_SPANISH = "OK";

    private static final String MOVE_KO_ENGLISH = "Cannot move there!";

    private static final String MOVE_KO_SPANISH = "¡No puedo moverme ahí!";

    private static final String[][] strings = {
        {
            GREETING_ENGLISH,
            CONGRATULATIONS_ENGLISH,
            ASK_FOR_COMMAND_ENGLISH,
            HELP_ENGLISH,
            EXITING_ENGLISH,
            UNKNOWN_COMMAND_ENGLISH,
            UNKNOWN_COMMAND_SUFIX_ENGLISH,
            MOVE_OK_ENGLISH,
            MOVE_KO_ENGLISH
        },
        {
            GREETING_SPANISH,
            CONGRATULATIONS_SPANISH,
            ASK_FOR_COMMAND_SPANISH,
            HELP_SPANISH,
            EXITING_SPANISH,
            UNKNOWN_COMMAND_SPANISH,
            UNKNOWN_COMMAND_SUFIX_SPANISH,
            MOVE_OK_SPANISH,
            MOVE_KO_SPANISH
        }
    };

    private Language lang;

    public Localizer(Language lang) {
        if (lang == null) {
            throw new NullPointerException("lang");
        }
        checkStringDB();
        // find the correct language in the enum Language
        Language[] langs = Language.values();
        boolean found = false;
        for (int i=0; i<langs.length; i++) {
            if (lang.equals(langs[i])) {
                this.lang = lang;
                found = true;
                break;
            }
        }
        if (! found) {
            throw new IllegalArgumentException(
                    "Unsupported language " + lang);
        }
    }

    // Ugly hack to check the integrity of the static string
    // database at runtime.
    private void checkStringDB() {
       if (strings == null) {
           throw new NullPointerException("strings");
       }
       if (Array.hasNull(strings) ||
               ! Array.isRect(strings)) {
           throw new IllegalStateException(
                   "The translation database is not a rectangular array");
       }
    }

    public String get(MsgToUsr msg) {
        return strings[lang.ordinal()][msg.ordinal()];
    }
}
