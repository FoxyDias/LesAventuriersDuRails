package metier;

import java.awt.Color;

import java.util.ArrayList;

public class Joueur {

    private static int nbJoueur;
    private int numJoueur;

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
        Joueur.nbJoueur++;
        this.numJoueur = nbJoueur;
        
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
        /* nbPoints = 0 pour éviter la duplication de points */
        this.nbPoints = 0;

        /* Calcul des points en fonction des paramètres de la mappe et de la taille de l'arête */
        for (Arete a : this.lstArete){
            this.nbPoints+= metier.getPointsTailleAretes(a.getWagon());
        }
        return this.nbPoints;
    }

    /**
     * Renvoie l'arrayList des arêtes occupées dont les points sont déjà comptabiliser
     * @return
     */
    public ArrayList<Arete> getAlCheminsPtsCpts(){
        return this.alCheminsPtsCpts;
    }

    /**
     * Renvoi le chemin le plus long
     * @return int routeLaPlusLongue
     */
    public int getRouteLaPlusLongue(){
        return this.routeLaPlusLongue;
    }

    public void chercheCheminLeplusPlong()
    {
        for( Arete a : this.lstArete ) {
            this.chercheCheminLeplusPlongRec(0, a.getNoeudDep() , new ArrayList<Arete>());
        }
    }

    private void chercheCheminLeplusPlongRec(int tailleChemin, Noeud noeud,  ArrayList<Arete> lstDejaParcouru )
    {

        for(Arete arete : noeud.getArrayArete())
        {
            if(this.lstArete.contains(arete))
            {
                if(!(lstDejaParcouru.contains(arete)))
                {
                    lstDejaParcouru.add(arete);
                    tailleChemin += arete.getWagon();

                    if(arete.getNoeudDep() == noeud)
                        this.chercheCheminLeplusPlongRec(tailleChemin, arete.getNoeudArr(), lstDejaParcouru);
                    else if(arete.getNoeudArr() == noeud)
                        this.chercheCheminLeplusPlongRec(tailleChemin, arete.getNoeudDep(), lstDejaParcouru);
                }
            }
        }

        if(tailleChemin > this.routeLaPlusLongue)
        {
            this.routeLaPlusLongue = tailleChemin;
        }

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
        return this.lstArete;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public String estPlusLong()
    {
        //if()
        return "Oui";
        //else()
        //return "Non";

    }

    public void ajouterCarteWagon(CarteWagon cWagon){this.mainWagon.add(cWagon);}
    public void retirerCarteWagon(int indexcWagon){this.mainWagon.remove(indexcWagon);}
    public ArrayList<CarteWagon> getMainWagon() {return this.mainWagon;}
    
    public void ajouterCarteObjectif(CarteObjectif cObjectif){this.mainObjectif.add(cObjectif);}
    public ArrayList<CarteObjectif> getMainObjectif() {return this.mainObjectif;}

    public int getNbPoints() {
        return this.nbPoints;
    }

    public void ajouterArete(Arete a){
        //this.rajouterPoint(a.getWagon());
        this.lstArete.add(a);
    }

    public static void setNbJoueur(int nbJoueur) {
        Joueur.nbJoueur = nbJoueur;
    }

    public static int getNbJoueur() {
        return nbJoueur;
    }

    public int getNumJoueur() {
        return numJoueur;
    }

    public String toString(){
        return "Joueur" + this.numJoueur;
    }

    public int completeCarteObjectif()
    {
       int nbPoint = 0;
       for(CarteObjectif co : this.mainObjectif)
       {
            if(co.isAccomplie()){
                nbPoint += co.getNbPoints();
                continue;
            }
            
            Noeud n1 = co.getNoeudArr();

            ArrayList<Arete> lstAreteValide = new ArrayList<Arete>();
            ArrayList<Arete> lstAreteMorte  = new ArrayList<Arete>();
            
            for(Arete a : this.lstArete)
            {
                if(a.getNoeudArr() == n1 || a.getNoeudDep() == n1)
                    lstAreteValide.add(a);
            }

            while(lstAreteValide.size()>0)
            {

                //ArrayList<Arete> lstAreteTmp = new ArrayList<Arete>();
                
                Arete a = lstAreteValide.get(0);
                lstAreteMorte.add(a);
                lstAreteValide.remove(0);

                Noeud n2 = a.getNoeudArr();


                if(n2 == n1)
                {
                    n2 = a.getNoeudDep();
                    n1 = a.getNoeudArr();
                }
                else{
                    n1 = a.getNoeudDep();
                }

                if(n2 == co.getNoeudDep())
                {
                    System.out.println("Objectif valide");
                    nbPoint += co.getNbPoints();
                    co.setAccomplie(true);
                    //this.rajouterPoint(co.getNbPoints());
                    //nbPoint = co.getNbPoints();

                    return co.getNbPoints();
                }

                for(Arete areteAjoue : this.lstArete)
                {
                    if((areteAjoue.getNoeudArr() == n2 || areteAjoue.getNoeudDep() == n2 ||
                    areteAjoue.getNoeudArr() == n1 || areteAjoue.getNoeudDep() == n1 ) && 
                            !lstAreteMorte.contains(areteAjoue) && !lstAreteValide.contains(areteAjoue))
                    {
                        lstAreteValide.add(areteAjoue);
                    }
                }
            }

       }
       return nbPoint;            
    }  

    public int getMalusCarteObjectif()
    {
        int nbMalus =0;
        for(CarteObjectif co : this.mainObjectif)
            if(!co.isAccomplie())
                nbMalus+=co.getNbPoints();

        return nbMalus;
    }

    public boolean isJoueurFinal(){return this.nbWagons <= 2;}

    public void retirerCarteObjectif(int indexcObjectif){this.mainObjectif.remove(indexcObjectif);}

    public int getNbPointsTotal() { return this.getNbPointsChemin() + this.completeCarteObjectif() - this.getMalusCarteObjectif(); }
}
