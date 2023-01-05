package ihm.sectionJeu;

import main.Controleur;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelGaucheJeu extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnPiocheCarteWagon;
	private JButton btnFinDuTour;
	private JButton btnPiocheCarteObjectif;
	private JButton btnArreterPartie;

	public PanelGaucheJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(8,1, 5,0));

		JLabel lblPiocheCarteWagon		= new JLabel("Pioche carte wagon");
		JLabel lblPiocheCarteObjectif	= new JLabel("Pioche carte objectif");
		JLabel lblNbCarteRestant 		= new JLabel("Nombre de carte restant : "); 
		JLabel lblNbCarteRestant2		= new JLabel("Nombre de carte restant : "); 

		this.btnPiocheCarteWagon 		= new JButton("");
		this.btnPiocheCarteObjectif 	= new JButton("");
		this.btnFinDuTour 				= new JButton("Fin du tour");
		this.btnArreterPartie 			= new JButton("Arreter la partie");

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.btnPiocheCarteWagon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.btnPiocheCarteObjectif.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.btnFinDuTour.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.btnArreterPartie.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.btnPiocheCarteWagon.setBackground(Color.WHITE);
		this.btnPiocheCarteObjectif.setBackground(Color.WHITE);
		this.btnFinDuTour.setBackground(Color.WHITE);
		this.btnArreterPartie.setBackground(Color.WHITE);


		/**
		 * Positionnement des composants
		 */

		this.add(lblPiocheCarteWagon);
		this.add(this.btnPiocheCarteWagon);
		this.add(lblNbCarteRestant);
		this.add(this.btnFinDuTour);
		this.add(lblPiocheCarteObjectif);
		this.add(this.btnPiocheCarteObjectif);
		this.add(lblNbCarteRestant2);
		this.add(this.btnArreterPartie);

		/**
		 * Activation des composants
		 */

		this.btnPiocheCarteWagon.addActionListener(this);
		this.btnPiocheCarteObjectif.addActionListener(this);
		this.btnFinDuTour.addActionListener(this);
		this.btnArreterPartie.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnPiocheCarteWagon)
		{

		}

		if(e.getSource() == this.btnArreterPartie)
		{
			JOptionPane.showMessageDialog(null, "Voulez-vous vraiment arrêter la partie ?", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
