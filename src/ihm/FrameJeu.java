package ihm;

import main.Controleur;
import ihm.sectionMenu.PanelCentreMenu;
import ihm.sectionMenu.PanelMenu;
import ihm.sectionJeu.PanelJeu;

import java.awt.*;
import javax.swing.*;

/* Imports pour les thèmes */
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
/*------------------------ */

public class FrameJeu extends JFrame
{
    private Controleur ctrl;

	/* En fonction de la résolution d'écrans des ordinateurs */
	private Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	private int longueur, hauteur;

	private PanelMenu panelMenu;
	private PanelJeu  panelJeu;

    public FrameJeu(Controleur ctrl, String nom)
    {
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("donnee/image/iconApp.png"));

		this.longueur = this.tailleEcran.width - (int) (this.tailleEcran.width * 0.01);
		this.hauteur  = this.tailleEcran.height - (int) (this.tailleEcran.height * 0.06);

		this.panelMenu = new PanelMenu(this.ctrl);

		this.setSize(longueur, hauteur);
		this.setLocation(0,0);
		this.setTitle("Les Aventuriers du Rail");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.themebox(1);

		/**
		 * Positionnement des composants
		 */
		this.add(this.panelMenu);
		
		this.setVisible(true);
	}


	public PanelCentreMenu getPanelCentreMenu() { return this.panelMenu.getPanelCentreMenu(); }

	public void setEnabled(boolean b) { this.panelMenu.getPanelCentreMenu().setEnabled(b); }

	public void changerPanel(String nom)
	{
		switch (nom)
		{
			case "Menu":
				this.panelMenu = new PanelMenu(this.ctrl);
				setContentPane(panelMenu);
				revalidate();
				repaint();
				break;

			case "Jeu":
				this.panelJeu = new PanelJeu(this.ctrl);
				setContentPane(panelJeu);
				revalidate();
				repaint();
				this.panelJeu.changerCouleurPanel();
				break;
		}
	}

	public void changerCouleurPanel()
	{
		this.panelJeu.changerCouleurPanel();
	}

	/**
	 * Mise à disposition de différents thèmes pour le jeu
	 * @param themebox
	 */
	public void themebox(int themebox){
		try{
			if(themebox == 1){
				UIManager.setLookAndFeel(new FlatLightLaf());
			}
			else if(themebox == 2){
				UIManager.setLookAndFeel(new FlatDarkLaf());
			}
			else if(themebox == 3){
				UIManager.setLookAndFeel(new FlatDarculaLaf());
			}
		}catch(Exception e){
			System.out.println("Error setting native LAF: " + e);
		}
	}

    public void majIhm() {
		this.panelJeu.majIhm();
    }

    public void griserCarteObjectif(boolean b) {
		this.panelJeu.griserCarteObjectif(b);
    }

	public void griserCarteWagon(boolean b) {
		this.panelJeu.griserCarteWagon(b);
	}
}