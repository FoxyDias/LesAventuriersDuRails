package metier;

import java.awt.*;
import java.util.ArrayList;

public class Joueur {

    private Color couleur;
    private Metier metier;

    private int nbPoints;
    private int nbWagons;
    private int routeLaPlusLongue;

    private ArrayList<CarteWagon> mainWagon;
    private ArrayList<CarteObjectif> mainObjectif;
    private ArrayList<Arete> lstArete;
    private ArrayList<Arete> alCheminsPtsCpts;

    public Joueur(int wagons, Color coul, Metier metier)
    {
        this.couleur  = coul;
        this.nbWagons = wagons;
        this.metier   = metier;

        this.mainWagon       = new ArrayList<CarteWagon>();
        this.mainObjectif    = new ArrayList<CarteObjectif>();
        this.lstArete        = new ArrayList<Arete>();
        this.alCheminsPtsCpts= new ArrayList<Arete>();

        this.nbPoints          = 0;
        this.routeLaPlusLongue = 0;
    }

    /**
     * Renvoi le nombre de points des chemins du joueur
     */
    public int getNbPointsChemin() {
        //Les points d'une arête sont calculés par rapport aux nombres de wagons nécessaires pour la parcourire
        for (Arete a : this.lstArete){
            if( this.alCheminsPtsCpts.size() != 0){
                System.out.println("FLAG1");
                for(Arete b : this.alCheminsPtsCpts){
                    System.out.println("FLAG2");
                    System.out.println("LE A.EQUALSB C ICI : " + a.equals(b));
                    System.out.println("LE A ICI : " + a.toString());
                    System.out.println("LE B ICI : " + b.toString());
                    /* Si l'arête a déjà étée comptabilisée on augmente pas le nbPoints */
                    if(a.equals(b)){
                        System.out.println("FLAG3");
                        return nbPoints;
                    }   
                    else{
                        System.out.println("FLAG4");
                        /* Sinon on augmente le nbPoints et on l'ajoute dans l'arrayList des arêtes déjà comptabilisées */
                        this.nbPoints += metier.getPointsTailleAretes(a.getWagon());
                        this.alCheminsPtsCpts.add(a);
                    }
                }
            }else{
                System.out.println("FLAG5");
                /* Pour le premier cas si aucun joueurs n'a d'arêtes */
                this.nbPoints += this.metier.getPointsTailleAretes(a.getWagon());
                this.alCheminsPtsCpts.add(a);
            }
        }
        return this.nbPoints;
    }

    /**
     * Renvoie l'arrayList des arêtes occupées
     * @return
     */
    public ArrayList<Arete> getAlCheminsPtsCpts(){
        return this.alCheminsPtsCpts;
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
        return 0;
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

    public void removeNbWagons(int n )
    {
        this.nbWagons = this.nbWagons-n;
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
    public void retirerCarteWagon(int indexcWagon){this.mainWagon.remove(indexcWagon);}
    public ArrayList<CarteWagon> getMainWagon() {return this.mainWagon;}
    
    public void ajouterCarteObjectif(CarteObjectif cObjectif){this.mainObjectif.add(cObjectif);}
    public ArrayList<CarteObjectif> getMainObjectif() {return this.mainObjectif;}

    public int getNbPoints() {
        return nbPoints;
    }

    public void ajouterArete(Arete a){
        //this.rajouterPoint(a.getWagon());
        this.lstArete.add(a);
    }

    public String toString()
    {
        int i = 0;
        i++;
        return "Joueur " + i + "\n";
        
    }

    public boolean isJoueurFinal(){return nbWagons <= 2;}

    public void retirerCarteObjectif(int indexcObjectif){this.mainObjectif.remove(indexcObjectif);}
}
