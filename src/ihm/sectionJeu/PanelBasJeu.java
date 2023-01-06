package ihm.sectionJeu;

import main.Controleur;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;

public class PanelBasJeu extends JPanel
{
	private Controleur ctrl;

	public PanelBasJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
		this.setPreferredSize(new Dimension(0,70));
		JLabel lblJoueur;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		for(int i = 0; i < this.ctrl.getNbJoueurMax(); i++)
		{
			lblJoueur = new JLabel("Joueur " + (i + 1));
			lblJoueur.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			this.add(lblJoueur);
		}
		/**
		 * Positionnement des composants
		 */


		/**
		 * Activation des composants
		 */
	}
}
