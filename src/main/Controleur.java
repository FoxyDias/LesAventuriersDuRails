package main;

import ihm.FrameJeu;
import ihm.sectionMenu.PanelCentreMenu;
import metier.Arete;
import metier.CarteObjectif;
import metier.Metier;
import metier.Noeud;

import java.util.ArrayList;
import java.util.HashMap;

public class Controleur {

    private Metier metier;
    private FrameJeu ihm;

    public Controleur()
    {
        this.metier = new Metier( this );
        this.ihm = new FrameJeu(this, "init");
    }

    public ArrayList<Noeud> getLstNoeud()
    {
        return this.metier.getLstNoeud();
    }

    public ArrayList<Arete> getLstArete()
    {
        return this.metier.getLstArete();
    }

    public HashMap<String,Integer> getLstCouleurWagon() {
        return this.metier.getHsmCouleurWagon();
    }

    public HashMap<String,String> getHsmImageWagon() {
        return this.metier.getHsmImageWagon();
    }

    public ArrayList<String> getLstCouleurJoueur() {
        return this.metier.getLstCouleurJoueur();
    }

    public ArrayList<CarteObjectif> getLstCarteObjectif()
    {
        return this.metier.getListCarteObjectif();
    }

	public PanelCentreMenu getPanelCentreMenu() { return this.ihm.getPanelCentreMenu(); }

    /**
     * Le main du projet
     * @param args
     */
    public static void main(String[] args) {
        new Controleur();
    }
}
