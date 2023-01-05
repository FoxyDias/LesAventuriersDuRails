package metier;

public class Joueur {

    private int nbPoints;
    private int nbWagons;

    public Joueur(int w)
    {
        this.nbWagons = w;

        this.nbPoints = 0;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public int getNbWagons() {
        return nbWagons;
    }
}
