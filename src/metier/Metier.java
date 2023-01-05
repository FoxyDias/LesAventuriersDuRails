package metier;

import main.Controleur;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.*;

public class Metier {

    private Controleur ctrl;

    private ArrayList<Noeud> lstNoeud;
    private ArrayList<Arete> lstArete;
    private ArrayList<CarteObjectif> lstCarteObjectif;
    private ArrayList<CarteWagon> lstCarteWagon;

    private ArrayList<String> lstCouleurJoueur;
    private HashMap<String, Integer> hsmCouleurWagon;
    private HashMap<String, String> hsmImageWagon;
    private String versoCarteObjectif;
    private String versoCarteWagon;

    private ArrayList<Joueur> lstJoueur;


    private int nbJoueurMax, nbJoueurPartie, nbJoueurMinDoubleArete , nbWagonDebutPartie ,nbWagonFinPartie , nbPointsPlusLongChemin ;
    private int[] pointsTaille;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;

        this.lstNoeud = new ArrayList<Noeud>();
        this.lstArete = new ArrayList<Arete>();
        this.lstCarteObjectif = new ArrayList<CarteObjectif>();
        this.lstCarteWagon = new ArrayList<CarteWagon>();


        this.lstCouleurJoueur = new ArrayList<String>();
        this.hsmCouleurWagon = new HashMap<String, Integer>();
        this.hsmImageWagon = new HashMap<String, String>();

        this.lstJoueur = new ArrayList<Joueur>();
    }

    public void lancerPartie()
    {
        boolean dernierTour = false;
        int nbTour = 0;
        
        while(!dernierTour)
        {
            for(Joueur j : lstJoueur)
            {
                System.out.println("Choix : ");
                System.out.println("Piocher des Cartes");
                System.out.println("Prendre Possesion d'une route");
                System.out.println("Piocher des cartes Objectifs");
            }
        }
    }

    public void lireXml(String pathXml)
    {
        org.jdom2.Document document;
        Element racine;

        SAXBuilder sxb = new SAXBuilder();
        try {
            // On crée un nouveau document JDOM avec en argument le
            //fichier XML
            // Le parsing est terminé
            document = sxb.build(pathXml);
        } catch (Exception e)
        {

            System.out.println("ça crash");
            return;
        }

        this.lstNoeud = new ArrayList<Noeud>();
        this.lstArete = new ArrayList<Arete>();
        this.lstCarteObjectif = new ArrayList<CarteObjectif>();
        this.lstCarteWagon = new ArrayList<CarteWagon>();
        this.hsmCouleurWagon = new HashMap<String, Integer>();
        this.hsmImageWagon = new HashMap<String, String>();

        racine = document.getRootElement();

        System.out.println("Test1");

        // List listVilles = racine.getChildren("noeud");
        // Iterator i = listVilles.iterator();
        List<Element> lstNoeud = racine.getChildren ( "mappe" ).get(0).getChildren("noeud");
        List<Element> lstArete = racine.getChildren ( "mappe" ).get(0).getChildren("arete");
        List<Element> lstObjectif = racine.getChildren ( "mappe" ).get(0).getChildren("carteObjectif");
        List<Element> lstWagon = racine.getChildren ( "mappe" ).get(0).getChildren("carteWagon");
        List<Element> lstInformation = racine.getChildren ( "mappe" ).get(0).getChildren("details");
        List<Element> lstCouleurJoueur = racine.getChildren ( "mappe" ).get(0).getChildren("CouleurJoueurList");
        List<Element> lstPoints = racine.getChildren ( "mappe" ).get(0).getChildren("points").get(0).getChildren("pointTaille");

        for(Element courant : lstNoeud) {

            String nomVille = courant.getAttributeValue("nom");
            int x = Integer.parseInt(courant.getChild("coordonees").getAttributeValue("x"));
            int y = Integer.parseInt(courant.getChild("coordonees").getAttributeValue("y"));
            int nomX = Integer.parseInt(courant.getChild("coordoneesNom").getAttributeValue("x"));
            int nomY = Integer.parseInt(courant.getChild("coordoneesNom").getAttributeValue("y"));

            //System.out.println("Noeud : " + nomVille + " x : " + x + " y : " + y + " nomX : "+ nomX + " nomY : "+ nomY);

            this.creeNoeud(nomVille, x, y, nomX, nomY);
        }

        for(Element w : lstWagon)
        {
            String c = w.getChild("couleur").getText();

            this.hsmCouleurWagon.put(c, Integer.parseInt(w.getChild("nombre").getText()));
            this.hsmImageWagon.put(c, w.getChild("recto").getText());


            //this.creeCarteWagon(new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));

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
            this.creeArete(n1, n2, couleur,nbW);
        }

        for(Element d : lstInformation)
        {
            this.nbJoueurMinDoubleArete = Integer.parseInt(d.getChild("nbJoueurMinDoubleArete").getText());
            this.nbJoueurMax = Integer.parseInt(d.getChild("nbJoueurMax").getText());
            this.nbWagonDebutPartie = Integer.parseInt(d.getChild("nbWagonDebutPartie").getText());
            this.nbWagonFinPartie = Integer.parseInt(d.getChild("nbWagonFinPartie").getText());
            this.nbPointsPlusLongChemin = Integer.parseInt(d.getChild("nbPointsPlusLongChemin").getText());
        }

        for(Element o : lstObjectif)
        {
            Noeud n1 = this.lstNoeud.get(Integer.parseInt(o.getChild("noeudDep").getText()));
            Noeud n2 = this.lstNoeud.get(Integer.parseInt(o.getChild("noeudArr").getText()));
            int points = Integer.parseInt(o.getChild("points").getText());

            this.creeCarteObjectif(n1, n2, points);

        }

        int n =0;
        int maxTaille = 0;
        int taille = 0;
        int point = 0;

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
            point = Integer.parseInt(p.getChild("points").getText());

            this.pointsTaille[taille] = point;
            this.pointsTaille[taille] = point;

        }

        for(Element c : lstCouleurJoueur)
        {
            String couleur = c.getChild("couleurJoueur").getText();
            this.lstCouleurJoueur.add(couleur);
        }

    }

    public void creeNoeud(String nom, int x, int y, int nomX , int nomY)
    {
        Noeud n = new Noeud( nom, x, y, nomX, nomY);
        lstNoeud.add( n );
    }

    public void creeNoeud(String nom, int x, int y )
    {
        Noeud n = new Noeud( nom, x, y, x-15, y+15 );
        lstNoeud.add( n );
    }

    //En partant sur la base que l'on utilise une liste déroulante pour la couleur ET pour les ville
    public void creeArete(Noeud n1 , Noeud n2 , String c, int nbW )
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

    public void creeCarteObjectif( Noeud noeudDep, Noeud noeudArr, int nbW )
    {
        CarteObjectif co = new CarteObjectif( noeudDep, noeudArr, nbW );
        this.lstCarteObjectif.add( co );
    }

    public void creeCarteWagon( Color c )
    {
        CarteWagon cw = new CarteWagon( c );
        this.lstCarteWagon.add( cw );
    }

    public ArrayList<Joueur> getLstJoueur() { return this.lstJoueur; }

    public int getNbJoueurPartie() {
        return nbJoueurPartie;
    }

    public int getNbJoueurMax() {
        return nbJoueurMax;
    }

    public int getNbJoueurMinDoubleArete() {
        return nbJoueurMinDoubleArete;
    }

    public int getNbWagonDebutPartie() {
        return nbWagonDebutPartie;
    }

    public int getNbWagonFinPartie() {
        return nbWagonFinPartie;
    }

    public int getNbPointsPlusLongChemin() {
        return nbPointsPlusLongChemin;
    }

    public ArrayList<String> getLstCouleurJoueur() {
        return lstCouleurJoueur;
    }

    public ArrayList<CarteObjectif> getListCarteObjectif()
    {
        return this.lstCarteObjectif;
    }

    public HashMap<String, Integer> getHsmCouleurWagon() {
        return hsmCouleurWagon;
    }

    public HashMap<String, String> getHsmImageWagon() {
        return hsmImageWagon;
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

    public int[] getPointsTaille() {
        return pointsTaille;
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

    public void setNbJoueur(int n) { this.nbJoueurPartie = n; }


}
