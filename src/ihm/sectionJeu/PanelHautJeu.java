package ihm.sectionJeu;

import main.Controleur;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;

public class PanelHautJeu extends JPanel
{
	private Controleur ctrl;

	private JLabel lblTour;

	public PanelHautJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		JPanel panelTourJoueur = new JPanel(new GridLayout(this.ctrl.getNbJoueurMax(),1, 0,5));
		JPanel panelProfilJoueur = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.lblTour = new JLabel("C'est au tour de ");

		/**
		 * Positionnement des composants
		 */

		for(int i = 0; i < this.ctrl.getNbJoueurMax(); i++)
		{
			panelTourJoueur.add(new JLabel("Joueur " + (i + 1)));
		}

		panelTourJoueur.setBorder(border);
		panelProfilJoueur.setBorder(border);


		this.add(panelTourJoueur, BorderLayout.EAST);
		this.add(panelProfilJoueur, BorderLayout.WEST);

		/**
		 * Activation des composants
		 */
	}
}
