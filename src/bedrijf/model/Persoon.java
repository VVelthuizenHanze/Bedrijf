package bedrijf.model;

import java.time.LocalDate;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Algemene eigenschappen van alle personen betrokken bij mijn bedrijf
 */
public class Persoon {
    public static final String DEFAULT_WOONPLAATS = "Onbekend";
    public static final int DEFAULT_MAAND_SALARIS = 0;
    public static final String DEFAULT_NAAM = "Onbekend";
    public static final int MAANDEN_PER_JAAR = 12;
    public static int aantalPersonen = 0;

    public int personeelsNummer;
    public String naam;
    public String woonplaats;
    public double maandSalaris;

    public Persoon(String naam, String woonplaats, double maandSalaris) {
        this.naam = naam;
        this.woonplaats = woonplaats;
        this.maandSalaris = maandSalaris;
        this.personeelsNummer = ++aantalPersonen;
    }

    public Persoon(String naam) {
        this.naam = naam;
        this.woonplaats = DEFAULT_WOONPLAATS;
        this.maandSalaris = DEFAULT_MAAND_SALARIS;
        this.personeelsNummer = ++aantalPersonen;
    }

    public Persoon() {
        this.naam = DEFAULT_NAAM;
        this.woonplaats = DEFAULT_WOONPLAATS;
        this.maandSalaris = DEFAULT_MAAND_SALARIS;
        this.personeelsNummer = ++aantalPersonen;
    }

    public double berekenJaarInkomen() {
        return MAANDEN_PER_JAAR * maandSalaris;
    }
}
