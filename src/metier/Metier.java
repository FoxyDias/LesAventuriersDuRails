package metier;

import main.Controleur;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.jdom2.*;
import org.jdom2.input.*;   

public class Metier {

    private Controleur ctrl;

    private String nomImage;

    private ArrayList<Noeud> lstNoeud;
    private ArrayList<Arete> lstArete;

    private ArrayList<CarteObjectif> lstCarteObjectif;
    private ArrayList<CarteObjectif> lstPiocheObjectifs;

    private ArrayList<CarteWagon> lstCarteWagon;
    private ArrayList<CarteWagon> lstPiocheWagon;
    private ArrayList<CarteWagon> lstDefausseWagon;
    

    private String versoCarteObjectif;
    private String versoCarteWagon;

    private ArrayList<Color> lstCouleurJoueur;
    private ArrayList<Joueur> lstJoueur;


    private double witdhPanel;
    private double heightPanel;

    private int nbJoueurMax, nbJoueurPartie, nbJoueurMinDoubleArete , nbWagonDebutPartie ,nbWagonFinPartie , nbPointsPlusLongChemin ;
    private int[] pointsTaille;

    private int intJoueurActuel;

    private HashMap<Joueur, ArrayList<Noeud>> hsmJoueurNoeud;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;

        this.lstNoeud = new ArrayList<Noeud>();
        this.lstArete = new ArrayList<Arete>();

        this.lstCarteObjectif = new ArrayList<CarteObjectif>();
        this.lstPiocheObjectifs = new ArrayList<CarteObjectif>(); 

        this.lstCarteWagon = new ArrayList<CarteWagon>();
        this.lstDefausseWagon = new ArrayList<CarteWagon>();
        this.lstPiocheWagon = new ArrayList<CarteWagon>();

        this.lstCouleurJoueur = new ArrayList<Color>();
        this.lstJoueur = new ArrayList<Joueur>();

        this.intJoueurActuel = 0;

        this.hsmJoueurNoeud = new HashMap<Joueur,ArrayList<Noeud>>();
    }


    public void lancerPartie()
    {   
        initPioche();

        this.ctrl.changerPanel("Jeu");

        //tourChoixObjectif();
        tourActuel();
    }

    public void tourActuel()
    {
        String action;

        Joueur joueurActuel = this.lstJoueur.get(this.intJoueurActuel);

        boolean tourQuitter = true;
        while (tourQuitter)
        {
            System.out.println("Quelle action choisissez-vous parmit : Piocher | Possession | Carte  ");
            Scanner sc = new Scanner(System.in);
            action = sc.nextLine().toLowerCase();

            /*                          Choix Piocher                             */
            if (action.equals("piocher")) {
                tourQuitter = !(tourPiocher(joueurActuel));
            }

            /*-------------------------------------------------------------------*/

            /*                          Choix Possession                         */
            else if (action.equals("possession")) {
                tourQuitter = !(tourChoixArete(joueurActuel));
            }
            /*-------------------------------------------------------------------*/

            /*                             Choix Carte                           */
            else if (action.equals("carte")) {
                tourQuitter = !(tourCarte(joueurActuel));
            }
            /*-------------------------------------------------------------------*/
        }
        
    }

    /*
     * Joueur actuellement entrain de jouer
     */
    public Joueur getEstJoueurCourant()
    {
        return this.lstJoueur.get(this.intJoueurActuel);
    }

    /*
     * Gère le tour des joueurs à chaque fin de tours
     */
    public void avancerJoueur()
    {  
        if( this.intJoueurActuel >= this.nbJoueurPartie -1)
            this.intJoueurActuel = 0;
        else
            this.intJoueurActuel = (this.intJoueurActuel++);

        System.out.println(this.intJoueurActuel);
    }

    private void initPioche()
    {
        for(int i=0; i<5; i++)
        {
            piocherWagonRandom();
        }
        for(int i=0; i<3; i++)
        {
            piocherObjectifRandom();
        }

    }

    private boolean tourPiocher(Joueur joueurActuel)
    {
        String  choixWagons;
        Scanner sc = new Scanner(System.in);
        boolean droitMulti = true;

        for(int nbPioche=0; nbPioche<2; nbPioche++)
        {
            /* En cas de joker dès les 3 premières cartes */
            for(CarteWagon cw : this.lstPiocheWagon)
                if(cw.getCouleur().equals("Joker"))
                    droitMulti = false; 

            /* Choix entre les cartes */
            System.out.println("Choisissez ce que vous voulez piocher parmis : ");
            this.afficherPioche();
            choixWagons = sc.nextLine().toLowerCase();
            if(!droitMulti && choixWagons.equals("joker"))
            {
                System.out.println("Vous n'avez pas le droit de prendre un Joker");
                choixWagons = sc.nextLine().toLowerCase();
            }
            while(!choixWagons(joueurActuel, choixWagons) && !(choixWagons.equals("quitter")))
            {
                if(!droitMulti && choixWagons.equals("joker"))
                    System.out.println("Vous n'avez pas le droit de prendre un Joker");
                else
                    System.out.println("Erreur de choix");

                choixWagons = sc.nextLine();
            }
            if(choixWagons.equals("quitter"))
                return false;

            for(CarteWagon cw : this.lstPiocheWagon)
            {
                if(cw.getCouleur().equals("Joker"))
                {
                    droitMulti = false;
                }
            }
        }
        return true;
    }

    private void afficherPioche() {

        for( CarteWagon cw : this.lstPiocheWagon )
        {
            System.out.println(cw.getCouleur());
        }

    }

    private boolean tourChoixArete(Joueur joueurActuel)
    {
        System.out.println("Quelle arête voulez-vous ? ");
        
        afficherArete();

        Scanner sc = new Scanner(System.in);
        String choixArete = sc.nextLine().toLowerCase();

        Arete a = this.lstArete.get(Integer.parseInt(choixArete));

        if(joueurActuel.placerWagon(a.getWagon()) != a.getEstOccupe()){
            System.out.println("Ouais c ajté :)");
            return true;
        }
        
        if(joueurActuel.placerWagon(a.getWagon()) == a.getEstOccupe()){
            System.out.println("Arête déjà occupée");
            return true;
        }

        return false;
    }

    public void afficherArete(){
        int cpt = 0;
        for(Arete a : lstArete){
            System.out.println(cpt + " : " + a.getNoeudDep().getNom() + " --- " + a.getNoeudArr().getNom());
            cpt++;
        }
    }

    private boolean tourCarte(Joueur joueurActuel)
    {
        return true;
    }

    private boolean choixWagons(Joueur joueur, String choix){
        if (choix == null) return false;

        for(int i =0; i<this.lstPiocheWagon.size(); i++)
        {
            if (choix.equals(this.lstPiocheWagon.get(i).getCouleur().toLowerCase())){
                joueur.ajouterCarteWagon(this.lstPiocheWagon.get(i));
                this.lstDefausseWagon.add(this.lstPiocheWagon.get(i));
                this.lstPiocheWagon.remove(i);
                this.piocherWagonRandom();
                return true;
            }
        }
        return false;
    }

    private void piocherWagonRandom()
    {
        if(this.lstCarteWagon.size() == 0)
        {
            for(int i =0; i<this.lstCarteWagon.size(); i++)
            {
                this.lstCarteWagon.add(this.lstDefausseWagon.get(i));
                this.lstDefausseWagon.remove(i);
            }
        }

        int num = (int) (Math.random() * this.lstCarteWagon.size());
        this.lstPiocheWagon.add(this.lstCarteWagon.get(num));
        this.lstCarteWagon.remove(num);
    }

    private void piocherObjectifRandom()
    {

        int num = (int) (Math.random() * this.lstCarteObjectif.size());
        this.lstPiocheObjectifs.add(this.lstCarteObjectif.get(num));
        this.lstCarteObjectif.remove(num);
    }

    public void lireXml(String pathXml)
    {
        Document document;
        Element  racine;

        SAXBuilder sxb = new SAXBuilder();
        try {
            // On crée un nouveau document JDOM avec en argument le
            //fichier XML
            // Le parsing est terminé
            document = sxb.build(pathXml);
        } catch (Exception e)
        {

            System.out.println("Erreur");
            return;
        }

        this.lstNoeud = new ArrayList<Noeud>();
        this.lstArete = new ArrayList<Arete>();
        this.lstCarteObjectif = new ArrayList<CarteObjectif>();
        this.lstCarteWagon = new ArrayList<CarteWagon>();

        racine = document.getRootElement();

        System.out.println("Test1");

        // List listVilles = racine.getChildren("noeud");
        // Iterator i = listVilles.iterator();
        List<Element> lstNoeud = racine.getChildren ( "mappe" ).get(0).getChildren("noeud");
        List<Element> lstArete = racine.getChildren ( "mappe" ).get(0).getChildren("arete");
        List<Element> lstObjectif = racine.getChildren ( "mappe" ).get(0).getChildren("carteObjectif");
        List<Element> lstWagon = racine.getChildren ( "mappe" ).get(0).getChildren("carteWagon");
        List<Element> lstInformation = racine.getChildren ( "mappe" ).get(0).getChildren("details");
       
        List<Element> lstPoints = racine.getChildren ( "mappe" ).get(0).getChildren("points").get(0).getChildren("pointTaille");

        for(Element courant : lstNoeud) {

            String nomVille = courant.getAttributeValue("nom");
            int x = Integer.parseInt(courant.getChild("coordonees").getAttributeValue("x"));
            int y = Integer.parseInt(courant.getChild("coordonees").getAttributeValue("y"));
            int nomX = Integer.parseInt(courant.getChild("coordoneesNom").getAttributeValue("x"));
            int nomY = Integer.parseInt(courant.getChild("coordoneesNom").getAttributeValue("y"));

            //System.out.println("Noeud : " + nomVille + " x : " + x + " y : " + y + " nomX : "+ nomX + " nomY : "+ nomY);

            this.creerNoeud(nomVille, x, y, nomX, nomY);
        }

        for(Element w : lstWagon)
        {
            String c = w.getChild("couleur").getText(); // la couleur en toString
            String verso ="donnee/" + w.getChild("recto").getText(); //le recto
            String rgb;

            if(!(c.equals("Joker")))
                 rgb = stringToRGB(c);
            else
                rgb = c;

            for(int i =0; i<Integer.parseInt(w.getChild("nombre").getText()); i++ )
                this.creerCarteWagon(rgb, verso);

        }
        
        for(Element a : lstArete)
        {
            String nomVille1 = a.getChild("noeudArr").getText ();
            String nomVille2 = a.getChild("noeudDep").getText ();
            String couleur = a.getChild("couleur").getText();
            int nbW = Integer.parseInt(a.getChild("wagons").getText());

            //System.out.println("Arete : " + nomVille1 + " " + nomVille2 + " " + couleur + " " + nbW + " " + estDouble);
            Noeud n1 = this.lstNoeud.get(Integer.parseInt(nomVille1));
            Noeud n2 = this.lstNoeud.get(Integer.parseInt(nomVille2));
            this.creerArete(n1, n2, couleur,nbW);
        }

        for(Element d : lstInformation)
        {
            this.nbJoueurMinDoubleArete = Integer.parseInt(d.getChild("nbJoueurMinDoubleArete").getText());
            this.nbJoueurMax            = Integer.parseInt(d.getChild("nbJoueurMax").getText());
            this.nbWagonDebutPartie     = Integer.parseInt(d.getChild("nbWagonDebutPartie").getText());
            this.nbWagonFinPartie       = Integer.parseInt(d.getChild("nbWagonFinPartie").getText());
            this.nbPointsPlusLongChemin = Integer.parseInt(d.getChild("nbPointsPlusLongChemin").getText());
            this.witdhPanel             = Double.parseDouble(d.getChild("witdhPanel").getText());
            this.heightPanel            = Double.parseDouble(d.getChild("heightPanel").getText());
            this.nomImage               = d.getChild("image").getText();
            this.versoCarteWagon        = d.getChild("versoCarteWagon").getText();
            this.versoCarteObjectif     = d.getChild("versoCarteObjectif").getText();
        }

        for(Element o : lstObjectif)
        {
            Noeud n1 = this.lstNoeud.get(Integer.parseInt(o.getChild("noeudDep").getText()));
            Noeud n2 = this.lstNoeud.get(Integer.parseInt(o.getChild("noeudArr").getText()));
            int points = Integer.parseInt(o.getChild("points").getText());

            this.creerCarteObjectif(n1, n2, points);
        }

        int maxTaille = 0;
        int taille    = 0;
        int point     = 0;

        for(Element p  : lstPoints)
        {
            taille = Integer.parseInt(p.getChild("taille").getText());
            if(maxTaille<taille)
                maxTaille = taille;
        }

        this.pointsTaille = new int[maxTaille+1];

        for(Element p  : lstPoints)
        {
            taille = Integer.parseInt(p.getChild("taille").getText());
            point  = Integer.parseInt(p.getChild("points").getText());

            this.pointsTaille[taille] = point;
            this.pointsTaille[taille] = point;
        }

        Element lstCouleurJoueur = racine.getChildren ( "mappe" ).get(0).getChild("CouleurJoueurList");

        for(Element c : lstCouleurJoueur.getChildren("couleurJoueur"))
        {
            String coulSTR = c.getText();
            Color  coulRGB;

            /* Conversion des couleurs en RGB / Sring */
            coulSTR = stringToRGB(coulSTR);
            coulRGB = RGBtoColor (coulSTR);

            this.lstCouleurJoueur.add(coulRGB);
            this.lstJoueur.add(new Joueur(this.nbWagonDebutPartie, coulRGB));

        }

    }

    private Color RGBtoColor(String couleur) 
    {
        return new Color(Integer.parseInt(couleur.split(",")[0]), Integer.parseInt(couleur.split(",")[1]) , Integer.parseInt(couleur.split(",")[2]));
    }

    private String stringToRGB(String c) {
        int r = Integer.parseInt(c.split(",")[0].substring(3));
        int g = Integer.parseInt(c.split(",")[1].substring(2));
        int b = Integer.parseInt(c.split(",")[2].substring(2).replace("]", ""));

        return (r + "," + g +"," + b);
    }

    public void creerNoeud(String nom, int x, int y, int nomX , int nomY)
    {
        Noeud n = new Noeud( nom, x, y, nomX, nomY);
        lstNoeud.add( n );
    }

    public void creerNoeud(String nom, int x, int y )
    {
        Noeud n = new Noeud( nom, x, y, x-15, y+15 );
        lstNoeud.add( n );
    }

    //En partant sur la base que l'on utilise une liste déroulante pour la couleur ET pour les ville
    public void creerArete(Noeud n1 , Noeud n2 , String c, int nbW )
    {
        Arete a = new Arete( n1, n2, c ,nbW);
        for (Arete b : lstArete)
        {
            if ((b.getNoeudArr().equals(n1) && b.getNoeudDep().equals(n2))||
                    (b.getNoeudArr().equals(n2) && b.getNoeudDep().equals(n1)))
            {
                b.setEstDouble(true);
                a.setEstDouble(true);
                b.setAreteDouble(a);
                a.setAreteDouble(b);
            }
        }

        this.lstArete.add( a );
    }

    public void creerCarteObjectif( Noeud noeudDep, Noeud noeudArr, int nbW )
    {
        CarteObjectif co = new CarteObjectif( noeudDep, noeudArr, nbW );
        this.lstCarteObjectif.add( co );
    }

    public void  creerCarteWagon( String c, String s )
    {
        CarteWagon cw = new CarteWagon( c );
        if(!(s == null))
            cw.setRecto(s);

        this.lstCarteWagon.add( cw );
    }

    public ArrayList<Joueur> getLstJoueur() { return this.lstJoueur; }


    /*-------------------------------------------------------------------------*/
    /*                                Getters                                  */
    /*-------------------------------------------------------------------------*/
    public double getWidthPanel (){ return this.witdhPanel; }
    public double getHeightPanel(){ return this.heightPanel;}

    public int getNbJoueurPartie        () { return nbJoueurPartie;        }
    public int getNbJoueurMax           () { return nbJoueurMax;           }
    public int getNbJoueurMinDoubleArete() { return nbJoueurMinDoubleArete;}
    public int getNbWagonDebutPartie    () { return nbWagonDebutPartie;    }
    public int getNbWagonFinPartie      () { return nbWagonFinPartie;      } 
    public int getNbPointsPlusLongChemin() { return nbPointsPlusLongChemin;}
    public int[] getPointsTaille() { return pointsTaille; }
    /*-------------------------------------------------------------------------*/

    /*-------------------------------------------------------------------------*/
    /*                                Setters                                  */
    /*-------------------------------------------------------------------------*/
    public void setWidthPanel (double witdhPanel) {this.witdhPanel = witdhPanel;  }
    public void setHeightPanel(double heightPanel){this.heightPanel = heightPanel;}
    public void setNbJoueurPartie(int n          ){ this.nbJoueurPartie = n; }

    /*-------------------------------------------------------------------------*/

    

    public ArrayList<Color> getLstCouleurJoueur() {
        return lstCouleurJoueur;
    }

    public ArrayList<CarteObjectif> getListCarteObjectif()
    {
        return this.lstCarteObjectif;
    }

    public String getVersoCarteWagon(){
        return this.versoCarteWagon;
    }

    public String getVersoCarteObjectif() {
        return versoCarteObjectif;
    }

    public ArrayList<CarteWagon> getLstCarteWagon() {
        return lstCarteWagon;
    }

    

    public ArrayList<CarteObjectif> getLstCarteObjectif() {
        return lstCarteObjectif;
    }

    public ArrayList<Noeud> getLstNoeud()
    {
        return this.lstNoeud;
    }

    public ArrayList<Arete> getLstArete()
    {
        return this.lstArete;
    }

    public String getNomImage() {
        return nomImage;
    }

    public Joueur getJoueur(int i)
    {
        return this.lstJoueur.get(i);
    }

}