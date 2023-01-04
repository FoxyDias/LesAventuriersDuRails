package ihm.sectionMenu;

import main.Controleur;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class PanelMenu extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private PanelHautMenu panelHautMenu;
	private PanelCentreMenu panelCentreMenu;

	public PanelMenu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */

		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.panelHautMenu = new PanelHautMenu(this.ctrl);
		this.panelCentreMenu = new PanelCentreMenu(this.ctrl);
		 
	    /**
		 * Positionnement des composants
		 */

		this.add(panelHautMenu, BorderLayout.NORTH);
		this.add(panelCentreMenu, BorderLayout.CENTER);
		this.add(new JButton("test"), BorderLayout.SOUTH);

		/**
	     * Activation des composants
		 */
	}

	public void actionPerformed(ActionEvent e)
	{

	}
}
