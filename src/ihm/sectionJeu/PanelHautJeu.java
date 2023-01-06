package ihm.sectionJeu;

import main.Controleur;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.util.ArrayList;

import java.awt.Color;

public class PanelHautJeu extends JPanel
{
	private Controleur ctrl;

	private ArrayList<JLabel> alProfilJoueur;

	public PanelHautJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(0,70));

		JPanel panelProfilJoueur = new JPanel(new GridLayout(1, this.ctrl.getNbJoueurMax()));
		
		this.alProfilJoueur = new ArrayList<JLabel>();


		this.alProfilJoueur = new ArrayList<JLabel>();

		for(int i = 0; i < this.ctrl.getNbJoueurMax(); i++)
		{
			this.alProfilJoueur.add(new JLabel("Joueur " + (i + 1)));
			//this.alProfilJoueur.add(new JLabel("Nombre de cartes wagons :" + this.ctrl.getJoueur(i).getNbCarteWagon()));
			//this.alProfilJoueur.add(new JLabel("Nombre de cartes objectif :" + this.ctrl.getJoueur(i).getNbCarteDestination()));
			this.alProfilJoueur.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			panelProfilJoueur.add(this.alProfilJoueur.get(i));
		}

		/**
		 * Positionnement des composants
		 */

		this.add(panelProfilJoueur);

		/**
		 * Activation des composants
		 */
	}
}
