package ihm;

import main.Controleur;
import ihm.sectionMenu.PanelCentreMenu;
import ihm.sectionMenu.PanelMenu;
import ihm.sectionJeu.PanelJeu;

import java.awt.*;
import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class FrameJeu extends JFrame
{
    private Controleur ctrl;

	/* En fonction de la résolution d'écrans des ordinateurs */
	private Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	private int longueur, hauteur;

	private PanelMenu panelMenu;
	private PanelJeu panelJeu;

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
		this.setTitle("Les aventuriers du rail");
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

	public void themebox(int themebox){
		System.out.println("THEMEBOX FLAG1");
		try{
			if(themebox == 1){
				UIManager.setLookAndFeel(new FlatLightLaf());
				System.out.println("THEMEBOX FLAG2");
			}
			else if(themebox == 2){
				UIManager.setLookAndFeel(new FlatDarkLaf());
				System.out.println("THEMEBOX FLAG3");
			}
			else if(themebox == 3){
				UIManager.setLookAndFeel(new FlatIntelliJLaf());
				System.out.println("THEMEBOX FLAG4");
			}
			else if(themebox == 4){
				UIManager.setLookAndFeel(new FlatDarculaLaf());
				System.out.println("THEMEBOX FLAG5");
			}
		}catch(Exception e){
			System.out.println("Error setting native LAF: " + e);
		}
	}

    public void majIhm() {
		this.panelJeu.majIhm();
    }
}
