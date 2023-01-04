/**
 * @author Decharrois Adrien
 * @version 1.0
 * @date 2023-01-03
 */

package ihm.accueil;

import main.Controleur;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelXmlInfo extends JPanel implements ActionListener
{
	private Controleur ctrl; 
	private JButton btnXML;	

	private String nomFichier;

	public PanelXmlInfo(Controleur ctrl, String nomFichier)
	{
		/**
		 * Création des composants
		 */

		this.ctrl = ctrl;
		this.nomFichier = nomFichier;

		this.setLayout(new BorderLayout());
		this.setBackground(new Color(206,209,217));

		this.btnXML = new JButton();
		this.btnXML.setBackground(Color.WHITE);

		/**
		 * Positionnement des composants
		 */

		this.add(new JLabel(nomFichier, JLabel.CENTER), BorderLayout.NORTH);
		this.add(this.btnXML, BorderLayout.CENTER);

		/**
		 * Activation des composants
		 */
		this.btnXML.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e )
	{
		/*if(e.getSource() == this.btnXML)
			this.ctrl.panelSelectionner(this); */
	}

	public void changerBordure(Border bordure)	{ this.btnXML.setBorder(bordure);}
}
