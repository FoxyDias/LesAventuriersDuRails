package ihm.sectionJeu;

import main.Controleur;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class PanelJeu extends JPanel
{
	private Controleur ctrl; 

	public PanelJeu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		JLabel lbl = new JLabel("PanelJeu");

		this.add(lbl);
	}
}