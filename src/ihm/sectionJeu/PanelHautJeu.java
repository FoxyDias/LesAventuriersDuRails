package ihm.sectionJeu;

import main.Controleur;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import java.awt.*;

public class PanelHautJeu extends JPanel
{
	private Controleur ctrl;
	private ArrayList<JLabel> alJoueur;
	private ArrayList<JLabel> alNbPionsWagon;
	private ArrayList<JLabel> alNbCarteObjectif;
	private ArrayList<JLabel> alNbPointsChemin;
	private ArrayList<JLabel> alNbCarteWagons;

	private JPanel panelInfosJoueur;
	private int nbCarte = 4;

	public PanelHautJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(0,80));
	
		JPanel panelProfilJoueur 	= new JPanel(new GridLayout(1,this.ctrl.getNbJoueurPartie(),10,0));
		this.alJoueur 				= new ArrayList<JLabel>();
		this.alNbPionsWagon 		= new ArrayList<JLabel>();
		this.alNbCarteObjectif 		= new ArrayList<JLabel>();
		this.alNbPointsChemin		= new ArrayList<JLabel>();
		this.alNbCarteWagons		= new ArrayList<JLabel>();
 
		for(int i = 0; i < this.ctrl.getNbJoueurPartie(); i++)
		{
			this.panelInfosJoueur = new JPanel(new GridLayout(5,1,5,3));
			this.alJoueur.add(new JLabel("Joueur " + (i+1), JLabel.CENTER));
			this.alNbPionsWagon.add(new JLabel("Nombre de pions " + this.ctrl.getMoyenDeTransport() + " : " + this.ctrl.getNbWagonDebutPartie(), JLabel.CENTER));
			this.alNbCarteObjectif.add(new JLabel("Nombre de cartes objectifs : " + this.ctrl.getEstJoueurCourant().getNbCarteObjectif(), JLabel.CENTER));
			this.alNbPointsChemin.add(new JLabel("Nombre de points chemin : " + this.ctrl.getEstJoueurCourant().getNbPointsChemin(), JLabel.CENTER));
			this.alNbCarteWagons.add(new JLabel("Nombre de cartes wagons : " + nbCarte, JLabel.CENTER));
			panelInfosJoueur.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			panelInfosJoueur.add(this.alJoueur.get(i));
			panelInfosJoueur.add(this.alNbPionsWagon.get(i));
			panelInfosJoueur.add(this.alNbCarteObjectif.get(i));
			panelInfosJoueur.add(this.alNbPointsChemin.get(i));
			panelInfosJoueur.add(this.alNbCarteWagons.get(i));
			panelProfilJoueur.add(panelInfosJoueur);

			for(int j = 0; j<4; j++)
			{
				this.ctrl.getLstJoueur().get(i).ajouterCarteWagon(this.ctrl.getLstCarteWagon().get(0));
				this.ctrl.getLstCarteWagon().remove(0);
			}
		}
		/**
		 * Positionnement des composants
		 */

		this.add(panelProfilJoueur);
	}

	/**
	 * Méthode permettant de changer la couleur du joueur courant
	 */
	public void changerCouleurPanel()
	{
		for(int i = 0; i < this.ctrl.getNbJoueurPartie(); i++)
		{
			if(this.ctrl.getJoueur(i).equals(this.ctrl.getEstJoueurCourant()))
			{
				this.alJoueur.get(i).setForeground(this.ctrl.getEstJoueurCourant().getCouleur());
				this.alNbPionsWagon.get(i).setForeground(this.ctrl.getEstJoueurCourant().getCouleur());
				this.alNbCarteObjectif.get(i).setForeground(this.ctrl.getEstJoueurCourant().getCouleur());
				this.alNbPointsChemin.get(i).setForeground(this.ctrl.getEstJoueurCourant().getCouleur());
				this.alNbCarteWagons.get(i).setForeground(this.ctrl.getEstJoueurCourant().getCouleur());

				this.alJoueur.get(i).setFont(new Font("", Font.BOLD, 14));
				this.alNbCarteObjectif.get(i).setFont(new Font("", Font.BOLD, 14));
				this.alNbPionsWagon.get(i).setFont(new Font("", Font.BOLD, 14));
				this.alNbPointsChemin.get(i).setFont(new Font("", Font.BOLD, 14));
				this.alNbCarteWagons.get(i).setFont(new Font("", Font.BOLD, 14)); 
			}
			else
			{
				this.alJoueur.get(i).setForeground(Color.BLACK);
				this.alNbPionsWagon.get(i).setForeground(Color.BLACK);
				this.alNbCarteObjectif.get(i).setForeground(Color.BLACK);
				this.alNbPointsChemin.get(i).setForeground(Color.BLACK);
				this.alNbCarteWagons.get(i).setForeground(Color.BLACK);

				this.alJoueur.get(i).setFont(new Font("", Font.BOLD, 12));
				this.alNbCarteObjectif.get(i).setFont(new Font("", Font.BOLD, 12));
				this.alNbPionsWagon.get(i).setFont(new Font("", Font.BOLD, 12));
				this.alNbPointsChemin.get(i).setFont(new Font("", Font.BOLD, 12));
				this.alNbCarteWagons.get(i).setFont(new Font("", Font.BOLD, 12));
			}
		}
	}

	public void majIhm()
	{
		for(int i = 0; i < this.ctrl.getNbJoueurPartie(); i++)
		{
			this.alNbPionsWagon.get(i).setText("Nombre de pions " 					+ this.ctrl.getMoyenDeTransport() + " : " + this.ctrl.getJoueur(i).getNbWagons());
			this.alNbCarteObjectif.get(i).setText("Nombre de cartes objectifs : "	+ this.ctrl.getJoueur(i).getNbCarteObjectif());
			this.alNbPointsChemin.get(i).setText("Nombre de points chemin : " 		+ this.ctrl.getJoueur(i).getNbPointsChemin());
			this.alNbCarteWagons.get(i).setText("Nombre de cartes wagons : " 		+ this.ctrl.getJoueur(i).getMainWagon().size());
		}
	}
}
