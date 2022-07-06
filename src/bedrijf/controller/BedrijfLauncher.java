package bedrijf.controller;


import bedrijf.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Het reilen en zeilen van mijn bedrijf.
 */
public class BedrijfLauncher {

    public static void main(String[] args) {
        Afdeling[] afdelingen = {
                new Afdeling("Uitvoering", "Hilversum"),
                new Afdeling("Support", "Amsterdam"),
                new Afdeling("Management", "Almere"),
                new Afdeling("Documentatie", "Gouda")
        };

        ArrayList<Persoon> personen = new ArrayList<>();
        personen.add(new Werknemer("Mark", "Den Haag", afdelingen[2], 10000));
        personen.add(new Werknemer("Angelique", "Rotterdam", afdelingen[2],
                5000));
        personen.add(new Werknemer("Caroline", "Delft", afdelingen[1], 4000));
        personen.add(new Zzper("Klaas", "Diemen", afdelingen[3], 50.00));
        personen.add(new Zzper("Ronald", "Zaandam", afdelingen[0], 80.00));
        personen.add(new Zzper("Jannie", "Utrecht", afdelingen[0], 60.00));
        personen.add(new Zzper("Anne", "Zwolle", afdelingen[0], 40.00));
        personen.add(new Vrijwilliger("Ambi", "Amsterdam", afdelingen[0]));
        personen.add(new Vrijwilliger("Naledi", "Gaborone", afdelingen[1]));
        personen.add(new Vrijwilliger("Ceren", "Istanboel", afdelingen[2]));
        personen.add(new Vrijwilliger("Haining", "Shaoxing", afdelingen[3]));

        for (Persoon persoon : personen) {
            if (persoon instanceof Zzper) {
                ((Zzper) persoon).huurIn(320);
            } else if (persoon instanceof Vrijwilliger) {
                ((Oproepbaar) persoon).huurIn(160);
            }
        }

        Collections.sort(personen);

        for (Persoon persoon : personen) {
            System.out.println(persoon);
            toonJaarInkomen(persoon);
        }
    }

    public static void toonJaarInkomen(Persoon persoon) {
        System.out.printf("%s verdient %.2f per jaar\n", persoon.getNaam(), persoon.berekenJaarInkomen());
    }
}
