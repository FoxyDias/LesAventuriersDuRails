package ihm.sectionMenu;

import main.Controleur;
import javax.swing.*;
import java.awt.BorderLayout;

public class PanelMenu extends JPanel
{
	private Controleur ctrl;

	private PanelHautMenu panelHautMenu;
	private PanelCentreMenu panelCentreMenu;
	private PanelBasMenu panelBasMenu;

	public PanelMenu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */

		this.ctrl = ctrl;
		this.setLayout(new BorderLayout(0,30));

		this.panelHautMenu		= new PanelHautMenu(this.ctrl);
		this.panelCentreMenu	= new PanelCentreMenu(this.ctrl);
		this.panelBasMenu 		= new PanelBasMenu(this.ctrl);
		 
	    /**
		 * Positionnement des composants
		 */

		this.add(this.panelHautMenu, BorderLayout.NORTH);
		this.add(this.panelCentreMenu, BorderLayout.CENTER);
		this.add(this.panelBasMenu, BorderLayout.SOUTH);
	}

	public PanelCentreMenu getPanelCentreMenu() { return this.panelCentreMenu; }
}
