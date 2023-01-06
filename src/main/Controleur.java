package main;

import ihm.FrameJeu;
import ihm.sectionMenu.PanelCentreMenu;
import metier.*;

import java.awt.*;
import java.util.ArrayList;

import com.formdev.flatlaf.FlatLightLaf;

public class Controleur {

    private Metier metier;
    private FrameJeu ihm;

    public Controleur()
    {
        this.metier = new Metier( this );
        this.ihm = new FrameJeu(this, "init");
    }

    public void lancerPartie(String nom)
    {
        this.ihm.changerPanel(nom);
        this.metier.lancerPartie();
    }

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

	public PanelCentreMenu getPanelCentreMenu() { 
		
		if (this.ihm == null) return null;
		
		return this.ihm.getPanelCentreMenu(); 
	}

    public Joueur getEstJoueurCourant()
    {
        return this.metier.getEstJoueurCourant();
    }

    public void avancerJoueur()
    {
        this.metier.avancerJoueur();
        this.ihm.changerCouleurPanel();
    }

    public double getWidthPanel()
    {
        return this.metier.getWidthPanel();
    }

    public double getHeightPanel()
    {
        return this.metier.getHeightPanel();
    }

    

    public void setNbJoueurPartie(int n ){this.metier.setNbJoueurPartie(n);}

    public int getNbJoueurPartie() { return this.metier.getNbJoueurPartie(); }


	public void setEnabled(boolean b) { this.ihm.setEnabled(b); }

    public void setNbJoueur(int n) { this.metier.setNbJoueurPartie(n); }

	public void lireXml(String fichier) { this.metier.lireXml(fichier); }

	public void changerPanel(String nom)
	{
		this.ihm.changerPanel(nom);
	}

    public Joueur getJoueur(int i) { return this.metier.getJoueur(i); }

    /**
     * Le main du projet
     * @param args
     */
    public static void main(String[] args) {

		FlatLightLaf.setup();
        new Controleur();
    }
}
