package main;

import ihm.FrameJeu;
import ihm.sectionJeu.PanelDispoParam;
import ihm.sectionMenu.PanelCentreMenu;

import metier.Metier;
import metier.Arete;
import metier.Joueur;
import metier.CarteObjectif;
import metier.CarteWagon;
import metier.Noeud;

import java.awt.Color;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.formdev.flatlaf.FlatLightLaf;

public class Controleur {

    private Metier metier;
    private FrameJeu ihm;
    private int nbPiocheWagon = 0;
    private PanelDispoParam panelDispoParam = new PanelDispoParam(this);

    public Controleur()
    {
        this.metier = new Metier( this );
        this.ihm = new FrameJeu(this, "init");
    }

    public void lancerPartie(String nom)
    {
        this.metier.lancerPartie();
        this.ihm.changerPanel(nom);
    }

    public void recapFinPartie(){
        panelDispoParam.recapFinPartie();
    }
    public void ajouterPoint() { this.metier.ajouterPoint(); }

    public String getNomImage() {
        return this.metier.getNomImage();
    }

    public ArrayList<Joueur> getLstJoueur() { return this.metier.getLstJoueur(); }


    public int getNbJoueurMax() {
        return this.metier.getNbJoueurMax();
    }

    public int getNbJoueurMinDoubleArete() {
        return this.metier.getNbJoueurMinDoubleArete();
    }

    public int getNbWagonDebutPartie() {
        return this.metier.getNbWagonDebutPartie();
    }

    public int getNbWagonFinPartie() {
        return this.metier.getNbWagonFinPartie();
    }

    public int getNbPointsPlusLongChemin() {
        return this.metier.getNbPointsPlusLongChemin();
    }

    public ArrayList<Color> getLstCouleurJoueur() {
        return this.metier.getLstCouleurJoueur();
    }

    public ArrayList<CarteObjectif> getListCarteObjectif()
    {
        return this.metier.getListCarteObjectif();
    }

    public String getVersoCarteWagon(){
        return this.metier.getVersoCarteWagon();
    }

    public String getVersoCarteObjectif() {
        return this.metier.getVersoCarteObjectif();
    }

    public ArrayList<CarteWagon> getLstCarteWagon() {
        return this.metier.getLstCarteWagon();
    }

    public int[] getPointsTaille() {
        return this.metier.getPointsTaille();
    }

    public ArrayList<CarteObjectif> getLstCarteObjectif() {
        return this.metier.getLstCarteObjectif();
    }

    public ArrayList<Noeud> getLstNoeud()
    {
        return this.metier.getLstNoeud();
    }

    public ArrayList<Arete> getLstArete()
    {
        return this.metier.getLstArete();
    }

    public ArrayList<CarteObjectif> getLstPiocheObjectifs() {
        return metier.getLstPiocheObjectifs();
    }

    public ArrayList<CarteWagon> getLstPiocheWagon() {
        return metier.getLstPiocheWagon();
    }

    public int getIntJoueurActuel() {
        return metier.getIntJoueurActuel();
    }

    public int getNbPiocheWagon() {
        return nbPiocheWagon;
    }

    public PanelCentreMenu getPanelCentreMenu() {
		
		if (this.ihm == null) return null;
		
		return this.ihm.getPanelCentreMenu(); 
	}

    public Joueur getEstJoueurCourant()
    {
        return this.metier.getEstJoueurCourant();
    }

    public Joueur getJoueurPlusLong() { return this.metier.getJoueurLePlusLong(); }

    public void avancerJoueur()
    {
        this.nbPiocheWagon = 0;
        this.metier.avancerJoueur();
        this.griserCarte(true);
        this.ihm.changerCouleurPanel();
        this.ihm.majIhm();
    }

    public void griserCarte(boolean b)
    {
        this.ihm.griserCarteObjectif(b);
        this.ihm.griserCarteWagon(b);
    }

    public void ajouterNbPiocheWagon() {
        if(nbPiocheWagon >= 1) {
            this.avancerJoueur();
        }
        else {
            this.nbPiocheWagon++;
            this.griserCarte(false);
        }
    }

    public void changerCouleurPanel()
    {
        this.ihm.changerCouleurPanel();
    }

    public double getWidthPanel()
    {
        return this.metier.getWidthPanel();
    }

    public boolean melangeWagon()
    {
        return this.metier.melangeWagon();
    }
    public boolean priseVoie(Joueur j , Arete a )
    {
        return this.metier.priseVoie(j,a);
    }

    public boolean priseVoieNeutre(Joueur j, Arete a , Color c )
    {
        return this.metier.priseVoieNeutre(j,a,c);
    }
    public double getHeightPanel()
    {
        return this.metier.getHeightPanel();
    }

    public void setNbJoueurPartie(int n ){this.metier.setNbJoueurPartie(n);}

    public int getNbJoueurPartie() { return this.metier.getNbJoueurPartie(); }

    public String getMoyenDeTransport() { return this.metier.getMoyenDeTransport(); }

    public void repiocherCarteObjectif(int i) {
        this.metier.repiocherCarteObjectif(i);
    }
    public void repiocherCarteWagon(int i) { this.metier.repiocherCarteWagon(i); }

    public void chercheCheminLeplusPlong() {
        this.metier.chercheCheminLeplusPlong();
    }


	public void setEnabled(boolean b) { this.ihm.setEnabled(b); }

    public void setNbJoueur(int n) { this.metier.setNbJoueurPartie(n); }

	public void lireXml(String fichier) { this.metier.lireXml(fichier); }

	public void changerPanel(String nom)
	{
		this.ihm.changerPanel(nom);
	}

    public Joueur getJoueur(int i) { return this.metier.getJoueur(i); }

    public void inverseEtatBtn(JButton btn)
    {
        if(btn.isOpaque())
		{
			btn.setOpaque(false);
			btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		}
		else
		{
			btn.setOpaque(true);
			btn.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}
    }


    public Color RGBtoColor(String couleur) { return this.metier.RGBtoColor(couleur); }

    /**
     * Le main du projet
     * @param args
     */
    public static void main(String[] args) {

		FlatLightLaf.setup();
        new Controleur();
    }

}
