package bedrijf.model;

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
    private static final double GRENSWAARDE_BONUS = 4500.00;

    private static int aantalPersonen = 0;

    private int personeelsNummer;
    private String naam;
    private String woonplaats;
    private double maandSalaris;
    private Afdeling afdeling;

    public Persoon(String naam, String woonplaats, double maandSalaris, Afdeling afdeling) {
        this.naam = naam;
        this.woonplaats = woonplaats;
        this.setMaandSalaris(maandSalaris);
        this.afdeling = afdeling;
        this.personeelsNummer = ++aantalPersonen;
    }

    public Persoon(String naam) {
        this(naam, DEFAULT_WOONPLAATS, DEFAULT_MAAND_SALARIS, new Afdeling());
    }

    public Persoon() {
        this(DEFAULT_NAAM);
    }

    public double berekenJaarInkomen() {
        return MAANDEN_PER_JAAR * maandSalaris;
    }

    public boolean heeftRechtOpBonus() {
        return maandSalaris >= GRENSWAARDE_BONUS;
    }

    public static int getAantalPersonen() {
        return aantalPersonen;
    }

    public int getPersoneelsNummer() {
        return personeelsNummer;
    }

    public void setPersoneelsNummer(int personeelsNummer) {
        this.personeelsNummer = personeelsNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public double getMaandSalaris() {
        return maandSalaris;
    }

    public void setMaandSalaris(double maandSalaris) {
        if (maandSalaris < 0) { // Maandsalaris mag niet negatief zijn
            System.out.println(maandSalaris + " is geen geldig maandsalaris, het maandsalaris wordt op 0 gezet.");
            maandSalaris = DEFAULT_MAAND_SALARIS;
        }
        this.maandSalaris = maandSalaris;
    }

    public Afdeling getAfdeling() {
        return afdeling;
    }
}
