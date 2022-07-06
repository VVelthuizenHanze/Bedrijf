package bedrijf.model;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Sommige mensen die extern werken voor ons bedrijf doen dit op vrijwillige basis (zonder uurtarief)
 */
public class Vrijwilliger extends Persoon implements Oproepbaar {
    private int urenGewerkt;

    public Vrijwilliger(String naam, String woonplaats, Afdeling afdeling) {
        super(naam, woonplaats, afdeling);
        this.urenGewerkt = 0;
    }

    @Override
    public void huurIn(int uren) {
        urenGewerkt += uren;
    }

    @Override
    public double berekenJaarInkomen() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s en is een vrijwilliger", super.toString());
    }
}
