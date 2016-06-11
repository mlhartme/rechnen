package net.oneandone.rechnen;

import net.oneandone.sushi.cli.Console;
import net.oneandone.sushi.fs.World;
import net.oneandone.sushi.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rechnen {
    public static final String ANSI_ERASE_END = "\u001B[0J";
    public static final String ANSI_ERASE_ALL = "\u001B[2J";
    public static final String ANSI_DOWN = "\u001B[1B";
    public static final String ANSI_HOME = "\u001B[1;1H";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        String wahl;
        String name;
        Console console;

        console = Console.create(new World());
        name = console.readline("Hallo, wie ist dein Name? ");
        while (true) {
            console.info.println(ANSI_ERASE_ALL + ANSI_HOME + "Hallo " + name + ", laß uns rechnen!");
            console.info.println();
            console.info.println("7er und 8er    -> 1");
            console.info.println("alles          -> 2");
            console.info.println("nichts mehr    -> 0");
            console.info.println();

            wahl = console.readline("Was möchtest du üben? ");
            switch (wahl) {
                case "0":
                    return;
                case "1":
                    rechnen(console, Aufgabe.siebenUndAcht());
                    break;
                case "2":
                    rechnen(console, Aufgabe.kleinesEinMalEins());
                    break;
                default:
                    break;
            }
        }
    }

    private static void rechnen(Console console, List<Aufgabe> alles) {
        Random random;
        String ergebnis;
        Aufgabe aufgabe;
        int i;
        int richtig;
        StringBuilder done;
        List<String> history;
        long start;
        int end;

        history = new ArrayList<>();
        done = new StringBuilder();
        random = new Random();
        richtig = 0;
        start = System.currentTimeMillis();
        end = Math.min(20, alles.size());
        while (true) {
            console.info.print(ANSI_HOME + ANSI_DOWN + ANSI_ERASE_END);
            console.info.println(done + dots(end - richtig));
            for (String h : history) {
                console.info.println(h);
            }
            if (richtig == end) {
                break;
            }
            i = Math.abs(random.nextInt()) % alles.size();
            aufgabe = alles.get(i);
            ergebnis = console.readline(aufgabe.frage).trim();
            if (ergebnis.equals(aufgabe.antwort)) {
                alles.remove(i);
                richtig++;
                done.append(ANSI_GREEN + "+" + ANSI_RESET);
                history.add(aufgabe.frage + ANSI_GREEN + ergebnis + ANSI_RESET);
            } else {
                done.append(ANSI_RED + "-" + ANSI_RESET);
                history.add(aufgabe.frage + ANSI_RED + ergebnis + ANSI_RESET);
            }
            if (history.size() > 15) {
                history.remove(0);
            }
        }
        console.info.println();
        console.info.println("Du hast " + richtig + " richtige Aufgaben in "
                + ((System.currentTimeMillis() - start) / 1000) + " Sekunden gerechnet :)");

        console.info.println();
        console.readline("Bitte drücke Return: ");

    }
    private static String dots(int count) {
        return Strings.times('_', count);
    }

}