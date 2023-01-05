package ihm.sectionJeu;

import main.Controleur;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class PanelJeu extends JPanel
{
	private Controleur ctrl; 

	private PanelHautJeu panelHautJeu;

	public PanelJeu(Controleur ctrl)
	{
		/** 
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
		this.panelHautJeu = new PanelHautJeu(this.ctrl);

		/**
		 * Positionnement des composants
		 */

		this.add(this.panelHautJeu, BorderLayout.NORTH);

		/**
		 * Activation des composants
		 */

		this.setVisible(true);
	}
}