package bedrijf.model;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Personen binnen het bedrijf werken voor een afdeling.
 */
public class Afdeling implements Comparable<Afdeling> {
    private static final String DEFAULT_PLAATS = "Onbekend";
    private static final String DEFAULT_NAAM = "Onbekend";

    private String afdelingsNaam;
    private String afdelingsPlaats;

    public Afdeling(String afdelingsNaam, String afdelingsPlaats) {
        this.afdelingsNaam = afdelingsNaam;
        this.afdelingsPlaats = afdelingsPlaats;
    }

    public Afdeling() {
        this(DEFAULT_NAAM, DEFAULT_PLAATS);
    }

    @Override
    public String toString() {
        return String.format("afdeling %s te %s", this.afdelingsNaam, this.afdelingsPlaats);
    }

    @Override
    public int compareTo(Afdeling o) {
        return afdelingsNaam.compareTo(o.afdelingsNaam);
    }

    public String getAfdelingsNaam() {
        return afdelingsNaam;
    }

    public String getAfdelingsPlaats() {
        return afdelingsPlaats;
    }
}
