package metier;

import java.awt.*;
import java.util.ArrayList;

import ihm.sectionJeu.PanelHautJeu;

public class Joueur {

    private Color couleur;

    private int nbPoints;
    private int nbWagons;

    private ArrayList<CarteWagon> mainWagon;
    private ArrayList<CarteObjectif> mainObjectif;

    private ArrayList<Arete> lstArete;
    
    private PanelHautJeu panelHautJeu;

    private int routeLaPlusLongue;

    public Joueur(int w, Color c)
    {
        this.couleur  = c;
        this.nbWagons = w;

        this.mainWagon       = new ArrayList<CarteWagon>();
        this.mainObjectif    = new ArrayList<CarteObjectif>();
        this.lstArete        = new ArrayList<Arete>();

        this.nbPoints        = this.getNbPointsChemin();
        this.routeLaPlusLongue = 0;
    }

    /**
     * Renvoi le nombre de points des chemins du joueur
     */
    public int getNbPointsChemin() {
        //Les points d'un chemin sont calculés par rapport aux nombres de wagons nécessaires pour le parcourir
        for (Arete a : this.lstArete)
                this.nbPoints += a.getWagon();
        
        return this.nbPoints;
    }

    /**
     * Renvoi le chemin le plus long
     * @return int routeLaPlusLongue
     */
    public int getCheminLePlusLong(){
        for(Arete a : lstArete){
            if(a.getWagon() > routeLaPlusLongue){
                routeLaPlusLongue = a.getWagon();
            }
        }
        return routeLaPlusLongue;
    }

    /**
     * Renvoi la route la plus longue entre 2 noeuds
     * @return
     */
    public int getRouteLaPlusLongue(){
        return routeLaPlusLongue;
    }

    /**
     * Rajoute un nombre de points pour le joueur, cette fonction sera utilisé en fin de partie
     * @param nbPoint
     */
    public void rajouterPoint(int nbPoint)  {this.nbPoints += nbPoint;}

    /**
     * Enlever un nombre de points pour le joueur, cette fonction sera utilisé en fin de partie
     * @param nbPoint
     */
    public void enleverPoint(int nbPoint)   {this.nbPoints -= nbPoint;}


    public int getNbWagons         () {return this.nbWagons;}
    
    /**
     * Prend en paramettre le nombre de wagon à placer
     * @param nbWagonAPlacer
     * @return Si l'action est effectuer alors il retour true sinon il retournera false
     */
    public boolean placerWagon(int nbWagonAPlacer)
    {
        if(nbWagonAPlacer<this.nbWagons)
        {
            //TODO: mettre le lien entre le nombre de pion à placer et nbPoint gagné
            this.nbWagons -= nbWagonAPlacer;

            if(this.routeLaPlusLongue<nbWagonAPlacer){
                this.routeLaPlusLongue = nbWagonAPlacer;
            }
            return true;
        }

        return false;
    }

    public int getNbCarteWagon()
    {
        return this.mainWagon.size();
    }

    public int getNbCarteObjectif()
    {
        return this.mainObjectif.size();
    }

    public ArrayList<Arete> getLstArete() {
        return lstArete;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public void ajouterCarteWagon(CarteWagon cWagon){this.mainWagon.add(cWagon);}
    public void retirerCarteWagon(int indexcWagon){this.mainObjectif.remove(indexcWagon);}
    public ArrayList<CarteWagon> getMainWagon() {return this.mainWagon;}
    
    public void ajouterCarteObjectif(CarteObjectif cObjectif){this.mainObjectif.add(cObjectif);}
    public ArrayList<CarteObjectif> getMainObjectif() {return this.mainObjectif;}

    public void ajouterArete(Arete a){this.lstArete.add(a);}

    public boolean isJoueurFinal(){return nbWagons <= 2;}

    public void retirerCarteObjectif(int indexcObjectif){this.mainObjectif.remove(indexcObjectif);}
}
