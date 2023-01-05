/**
 * @author Decharrois Adrien
 * @version 1.0
 * @date 2023-01-03
 */

package ihm;

import main.Controleur;
import ihm.sectionMenu.PanelCentreMenu;
import ihm.sectionMenu.PanelMenu;

import java.awt.*;
import javax.swing.*;

public class FrameJeu extends JFrame
{
    Controleur ctrl;

	private Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	private int longueur, hauteur;

	private PanelMenu panelMenu;

    public FrameJeu(Controleur ctrl, String nom)
    {
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;

		this.longueur = this.tailleEcran.width - (int) (this.tailleEcran.width * 0.01);
		this.hauteur = this.tailleEcran.height - (int) (this.tailleEcran.height * 0.06);
		this.panelMenu = new PanelMenu(this.ctrl);


		this.setSize(longueur, hauteur);
		this.setLocation(0,0);
		this.setUndecorated(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * Positionnement des composants
		 */

		this.add(this.panelMenu);
		this.setVisible(true);
	}

	public PanelCentreMenu getPanelCentreMenu() { return this.panelMenu.getPanelCentreMenu(); }

	public void setEnabled(boolean b) { this.panelMenu.getPanelCentreMenu().setEnabled(b); }
}
