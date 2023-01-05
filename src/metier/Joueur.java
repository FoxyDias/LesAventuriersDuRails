package metier;

import java.util.ArrayList;

public class Joueur {

    private int nbPoints;
    private int nbWagons;

    private ArrayList<CarteWagon> mainWagon;
    private ArrayList<CarteObjectif> mainObjectif;

    private int routeLaPlusLong;

    public Joueur(int w)
    {
        this.nbWagons       = w;

        this.mainWagon      = new ArrayList<CarteWagon>();
        this.mainObjectif   = new ArrayList<CarteObjectif>();

        this.nbPoints       = 0;
        this.routeLaPlusLong= 0;
    }

    public int getNbPoints() {return this.nbPoints;}

    /**
     * Rajoute un nombre de point pour le joueur, cette fonction sera utilisé en fin de partie
     * @param nbPoint
     */
    public void rajouterPoint(int nbPoint)  {this.nbPoints += nbPoint;}

    /**
     * Enlever un nombre de point pour le joueur, cette fonction sera utilisé en fin de partie
     * @param nbPoint
     */
    public void EnleverPoint(int nbPoint)   {this.nbPoints -= nbPoint;}


    public int getNbWagons() {return this.nbWagons;}
    public int getRouteLaPlusLong() {return this.routeLaPlusLong;}
    
    /**
     * Prend en paramettre le nombre de wagon à placer
     * @param nbWagonAPlacer
     * @return Si l'action est effectuer alors il retour true sinon il retournera false
     */
    public boolean placerWagon(int nbWagonAPlacer)
    {
        if(nbWagonAPlacer<this.nbWagons)
        {
            //TODO: mettre le lien entre le nombre de pion à placer et nbPoint gagner
            this.nbWagons -= nbWagonAPlacer;

            if(this.routeLaPlusLong<nbWagonAPlacer){this.routeLaPlusLong = nbWagonAPlacer;}

            return true;
        }

        return false;
    }

    public void ajouterCarteWagon(CarteWagon cWagon){this.mainWagon.add(cWagon);}
    public void retirerCarteWagon(int indexcWagon){this.mainObjectif.remove(indexcWagon);}
    
    public void ajouterCarteObjectif(CarteObjectif cObjectif){this.mainObjectif.add(cObjectif);}

    public boolean isJoueurFinal(){return nbWagons <= 2;}
}
