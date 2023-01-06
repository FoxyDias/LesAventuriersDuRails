package ihm.sectionJeu;

import main.Controleur;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class PanelJeu extends JPanel
{
	private Controleur ctrl; 

	private PanelHautJeu panelHautJeu;
	private PanelGaucheJeu panelGaucheJeu;
	private PanelCentreJeu panelCentre;
	private PanelBasJeu panelBasJeu;

	public PanelJeu(Controleur ctrl)
	{
		/** 
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
		this.panelHautJeu = new PanelHautJeu(this.ctrl);
		this.panelGaucheJeu = new PanelGaucheJeu(this.ctrl);
		this.panelCentre = new PanelCentreJeu(this.ctrl);
		this.panelBasJeu = new PanelBasJeu(this.ctrl);

		/**
		 * Positionnement des composants
		 */

		this.add(this.panelHautJeu, BorderLayout.NORTH);
		this.add(this.panelGaucheJeu, BorderLayout.WEST);
		this.add(this.panelCentre, BorderLayout.CENTER);
		this.add(this.panelBasJeu, BorderLayout.SOUTH);

		/**
		 * Activation des composants
		 */

		this.setVisible(true);
	}

	public void changerCouleurPanel()
	{
		this.panelHautJeu.changerCouleurPanel();
	}
}