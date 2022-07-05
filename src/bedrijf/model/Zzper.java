package bedrijf.model;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Een extern ingehuurde kracht binnen ons bedrijf
 */
public class Zzper extends Persoon {
    private double uurtarief;
    private int urenGewerkt;

    public Zzper(String naam, String woonplaats, Afdeling afdeling, double uurtarief) {
        super(naam, woonplaats, afdeling);
        this.uurtarief = uurtarief;
        this.urenGewerkt = 0;
    }

    public void huurIn(int uren) {
        this.urenGewerkt += uren;
    }

    @Override
    public double berekenJaarInkomen() {
        return urenGewerkt * uurtarief;
    }

    @Override
    public String toString() {
        return String.format("%s en is een zzp-er met een uurtarief van %.2f", super.toString(), this.uurtarief);
    }
}
