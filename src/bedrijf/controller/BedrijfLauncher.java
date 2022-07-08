package bedrijf.controller;


import bedrijf.database.AfdelingDAO;
import bedrijf.database.DBaccess;
import bedrijf.database.WerknemerDAO;
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
        DBaccess dBaccess = new DBaccess("Bedrijf", "userBedrijf", "userBedrijfPW");
        AfdelingDAO afdelingDAO = new AfdelingDAO(dBaccess);

        dBaccess.openConnection();

        WerknemerDAO werknemerDAO = new WerknemerDAO(dBaccess);
        werknemerDAO.slaWerknemerOp(new Werknemer(
                "Lodewijk",
                "Zaandam",
                new Afdeling("Support", "Amsterdam"),
                2500));

        dBaccess.closeConnection();

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
