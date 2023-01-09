package ihm.sectionJeu;

import main.Controleur;
import javax.swing.JPanel;

import java.awt.BorderLayout;


public class PanelJeu extends JPanel
{
	private Controleur ctrl; 

	private PanelHautJeu panelHautJeu;
	private PanelGaucheJeu panelGaucheJeu;
	private PanelCentreJeu panelCentre;
	private PanelDroiteJeu panelDroiteJeu;

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
		this.panelDroiteJeu = new PanelDroiteJeu(this.ctrl);

		/**
		 * Positionnement des composants
		 */

		this.add(this.panelHautJeu, BorderLayout.NORTH);
		this.add(this.panelDroiteJeu, BorderLayout.EAST);
		this.add(this.panelGaucheJeu, BorderLayout.WEST);
		this.add(this.panelCentre, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	/**
	 * Méthode qui permet de changer la couleur du panel
	 */
	public void changerCouleurPanel()
	{
		this.panelHautJeu.changerCouleurPanel();
	}

    public void majIhm() {
		this.panelHautJeu.majIhm();
    }
}