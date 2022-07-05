package bedrijf.model;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Personeelslid van ons bedrijf
 */
public class Werknemer extends Persoon {
    public static final int DEFAULT_MAAND_SALARIS = 0;

    public static final int MAANDEN_PER_JAAR = 12;
    private static final double GRENSWAARDE_BONUS = 4500.00;

    private double maandSalaris;

    public Werknemer(String naam, String woonplaats, Afdeling afdeling, double maandSalaris) {
        super(naam, woonplaats, afdeling);
        this.setMaandSalaris(maandSalaris);
    }

    public Werknemer(String naam) {
        super(naam);
        this.setMaandSalaris(DEFAULT_MAAND_SALARIS);
    }

    public Werknemer() {
        super();
        this.setMaandSalaris(DEFAULT_MAAND_SALARIS);
    }

    @Override
    public double berekenJaarInkomen() {
        double jaarsalaris = MAANDEN_PER_JAAR * maandSalaris;

        if (heeftRechtOpBonus()) {
            jaarsalaris += maandSalaris;
        }

        return jaarsalaris;
    }

    public boolean heeftRechtOpBonus() {
        return maandSalaris >= GRENSWAARDE_BONUS;
    }

    @Override
    public String toString() {
        return String.format("%s en is een werknemer %s recht op een bonus",
                super.toString(),
                heeftRechtOpBonus() ? "met" : "zonder");
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

}
