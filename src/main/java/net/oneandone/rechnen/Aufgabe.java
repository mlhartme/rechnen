package net.oneandone.rechnen;

import java.util.ArrayList;
import java.util.List;

public class Aufgabe {
    public static List<Aufgabe> kleinesEinMalEins() {
        List<Aufgabe> result;

        result = new ArrayList<>();
        for (int x = 2; x <= 9; x++) {
            for (int y = x + 1; y <= 9; y++) {
                result.add(Aufgabe.mal(x, y));
            }
        }
        return result;
    }

    public static List<Aufgabe> siebenUndAcht() {
        List<Aufgabe> result;

        result = new ArrayList<>();
        for (int x = 2; x <= 9; x++) {
            for (int y = 7; y <= 8; y++) {
                result.add(Aufgabe.mal(x, y));
            }
        }
        return result;
    }

    public static Aufgabe mal(int x, int y) {
        return new Aufgabe(x + " * " + y + " = ", Integer.toString(x*y));
    }

    //--


    public final String frage;
    public final String antwort;

    public Aufgabe(String frage, String antwort) {
        this.frage = frage;
        this.antwort = antwort;
    }
}
