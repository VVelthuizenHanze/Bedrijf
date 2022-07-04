package bedrijf.controller;


import bedrijf.model.Afdeling;
import bedrijf.model.Persoon;

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

        Persoon baas = new Persoon("Mark", "Den Haag", 10000, afdelingen[2]);
        Persoon medewerker = new Persoon("Caroline", "Delft", 4000, afdelingen[1]);
        Persoon assistent = new Persoon("Klaas");

        System.out.printf("Het aantal personen in het bedrijf is %d\n", Persoon.getAantalPersonen());

        System.out.printf("%s werkt in %s en woont in %s\n",
                baas.getNaam(), baas.getAfdeling().getAfdelingsPlaats(), baas.getWoonplaats());
        System.out.printf("%s werkt op de afdeling %s en verdient %.2f\n",
                medewerker.getNaam(), medewerker.getAfdeling().getAfdelingsNaam(), medewerker.getMaandSalaris());
        System.out.printf("%s werkt op de afdeling %s en woont in %s\n",
                assistent.getNaam(), assistent.getAfdeling().getAfdelingsNaam(), assistent.getWoonplaats());

    }
}
