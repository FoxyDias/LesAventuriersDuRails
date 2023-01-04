/**
 * @author Decharrois Adrien
 * @version 1.0
 * @date 2023-01-03
 */

package ihm;

import main.Controleur;
import ihm.accueil.PanelDispoBtn;
import ihm.accueil.PanelXmlInfo;
import ihm.jeu.PanelPreparationJeu;

import java.awt.*;
import javax.swing.*;

public class FrameJeu extends JFrame
{
    Controleur ctrl;

	protected static Font POLICE_DEFAULT = new Font("Broadway", Font.BOLD, 50);
	private Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();

	private int longueur, hauteur;

	private PanelDispoBtn panelDispoBtn;


    public FrameJeu(Controleur ctrl)
    {
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setTitle("Les aventuriers du rail");

		this.panelDispoBtn = new PanelDispoBtn(this.ctrl);

		this.longueur = this.tailleEcran.width - (int) (this.tailleEcran.width * 0.01);
		this.hauteur = this.tailleEcran.height - (int) (this.tailleEcran.height * 0.06);

		this.setSize(longueur, hauteur);
		this.setLayout(new BorderLayout());
		this.setLocation(0,0);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * Positionnement des composants
		 */
		this.add(this.panelDispoBtn, BorderLayout.WEST);
		this.setVisible(true);
	}

	/**
	 * 
	 * 
	 */
	public void panelSelectionner(PanelXmlInfo panelXmlInfo)
	{
		this.panelInit.panelSelectionner(panelXmlInfo);
	}


	/**
	 * Permet de changer de panel selon le nom
	 * @param nom
	 * @return nothing
	 */
	public void changerPanel(String nom)
	{
		switch(nom)
		{
			case "jouer":
				break;

			case "importerXML":
				break;
		}
	}
}
