package bedrijf.controller;


import bedrijf.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Het reilen en zeilen van mijn bedrijf.
 */
public class BedrijfLauncher {

    public static void main(String[] args) {
        ArrayList<Afdeling> afdelingen = leesAfdelingenIn();
        ArrayList<Persoon> personen = leesPersonenIn(afdelingen);

        Collections.sort(afdelingen);
        Collections.sort(personen);

        File uitvoerBestand = new File("resources/personenPerAfdeling.txt");
        try (PrintWriter printWriter = new PrintWriter(uitvoerBestand)) {
            for (Afdeling afdeling : afdelingen) {
                printWriter.printf("Afdeling: %s\n", afdeling.getAfdelingsNaam());
                for (Persoon persoon : personen) {
                    if (afdeling == persoon.getAfdeling()) {
                        printWriter.println("-- " + persoon);
                    }
                }
                printWriter.println();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Het is niet gelukt het uitvoerbestand te maken/openen");
        }
    }

    private static ArrayList<Persoon> leesPersonenIn(ArrayList<Afdeling> afdelingen) {
        ArrayList<Persoon> personen = new ArrayList<>();

        File personenBestand = new File("resources/Personen.csv");
        try (Scanner personenScanner = new Scanner(personenBestand)) {
            while (personenScanner.hasNextLine()) {
                String[] persoonInfo = personenScanner.nextLine().split(",");

                String typePersoon = persoonInfo[0];
                String naam = persoonInfo[1];
                String woonplaats = persoonInfo[2];
                int afdelingIndex = Integer.parseInt(persoonInfo[3]);
                Afdeling afdeling = afdelingen.get(afdelingIndex);
                double ietsMetGeld = Double.parseDouble(persoonInfo[4]);

                switch (typePersoon) {
                    case "Werknemer":
                        personen.add(new Werknemer(naam, woonplaats, afdeling, ietsMetGeld));
                        break;
                    case "Zzper":
                        personen.add(new Zzper(naam, woonplaats, afdeling, ietsMetGeld));
                        break;
                    case "Vrijwilliger":
                        personen.add(new Vrijwilliger(naam, woonplaats, afdeling));
                        break;
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Het lukt niet om het personen bestand te openen.");
        }

        return personen;
    }


    public static void toonJaarInkomen(Persoon persoon) {
        System.out.printf("%s verdient %.2f per jaar\n", persoon.getNaam(), persoon.berekenJaarInkomen());
    }

    public static ArrayList<Afdeling> leesAfdelingenIn() {
        ArrayList<Afdeling> afdelingen = new ArrayList<>();

        File afdelingenBestand = new File("resources/Afdelingen.txt");
        try (Scanner afdelingenScanner = new Scanner(afdelingenBestand)) {
            while (afdelingenScanner.hasNextLine()) {
                String afdelingsNaam = afdelingenScanner.nextLine();
                String afdelingsPlaats = afdelingenScanner.nextLine();
                afdelingen.add(new Afdeling(afdelingsNaam, afdelingsPlaats));
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Het afdelingen bestand kon niet geopend worden");
        }

        return afdelingen;
    }
}
