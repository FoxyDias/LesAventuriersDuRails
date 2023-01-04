package ihm.sectionMenu;

import main.Controleur;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class PanelBasMenu extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnQuitter;

	public PanelBasMenu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.btnQuitter = new JButton("Fermer l'application.");
		
		/**
		 * Positionnement des composants
		 */

		this.add(this.btnQuitter);

		/**
		 * Activation des composants
		 */

		this.btnQuitter.addActionListener(this);

		this.btnQuitter.setBackground(Color.WHITE);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnQuitter)
		{
			System.exit(0);
		}
	}
}
