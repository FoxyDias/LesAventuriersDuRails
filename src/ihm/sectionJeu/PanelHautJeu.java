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
	private ArrayList<JLabel> alNbCarteWagon;
	private ArrayList<JLabel> alNbCarteObjectif;

	private JPanel panelInfosJoueur;

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
		this.alNbCarteWagon 		= new ArrayList<JLabel>();
		this.alNbCarteObjectif 		= new ArrayList<JLabel>();
 
		for(int i = 0; i < this.ctrl.getNbJoueurPartie(); i++)
		{
			this.panelInfosJoueur = new JPanel(new GridLayout(3,1,10,0));
			this.alJoueur.add(new JLabel("Joueur " + (i+1), JLabel.CENTER));
			this.alNbCarteWagon.add(new JLabel("Nombre de cartes wagons : " + this.ctrl.getJoueur(i).getNbCarteWagon(), JLabel.CENTER));
			this.alNbCarteObjectif.add(new JLabel("Nombre de cartes objectifs : " + this.ctrl.getJoueur(i).getNbCarteObjectif(), JLabel.CENTER));
			panelInfosJoueur.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			panelInfosJoueur.add(this.alJoueur.get(i));
			panelInfosJoueur.add(this.alNbCarteWagon.get(i));
			panelInfosJoueur.add(this.alNbCarteObjectif.get(i));
			panelProfilJoueur.add(panelInfosJoueur);
		}
		/**
		 * Positionnement des composants
		 */

		this.add(panelProfilJoueur);

		/**
		 * Activation des composants
		 */
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
				this.alNbCarteWagon.get(i).setForeground(this.ctrl.getEstJoueurCourant().getCouleur());
				this.alNbCarteObjectif.get(i).setForeground(this.ctrl.getEstJoueurCourant().getCouleur());

				this.alJoueur.get(i).setFont(new Font("", Font.BOLD, 14));
				this.alNbCarteObjectif.get(i).setFont(new Font("", Font.BOLD, 14));
				this.alNbCarteWagon.get(i).setFont(new Font("", Font.BOLD, 14));
			}
			else
			{
				this.alJoueur.get(i).setForeground(Color.BLACK);
				this.alNbCarteWagon.get(i).setForeground(Color.BLACK);
				this.alNbCarteObjectif.get(i).setForeground(Color.BLACK);

				this.alJoueur.get(i).setFont(new Font("", Font.BOLD, 12));
				this.alNbCarteObjectif.get(i).setFont(new Font("", Font.BOLD, 12));
				this.alNbCarteWagon.get(i).setFont(new Font("", Font.BOLD, 12));
			}
		}
	}
}
