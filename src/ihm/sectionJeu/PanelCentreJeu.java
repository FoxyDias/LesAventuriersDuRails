package ihm.sectionJeu;

import main.Controleur;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCentreJeu extends JPanel
{
	private Controleur ctrl;


	public PanelCentreJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;

		JLabel lblCentre = new JLabel("Centre");

		/**
		 * Positionnement des composants
		 */
		this.add(lblCentre);

		/**
		 * Activation des composants
		 */
	}
}
