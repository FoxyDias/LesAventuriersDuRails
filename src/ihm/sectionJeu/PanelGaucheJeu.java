package ihm.sectionJeu;

import main.Controleur;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelGaucheJeu extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnPiocheCarteWagon;
	private JButton btnFinDuTour;
	private JButton btnPiocheCarteObjectif;



	public PanelGaucheJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(7,1, 15,15));

		JLabel lblPiocheCarteWagon		= new JLabel("Pioche carte wagon");
		JLabel lblPiocheCarteObjectif	= new JLabel("Pioche carte objectif");
		JLabel lblNbCarteRestant 		= new JLabel("Nombre de carte restant : "); 
		JLabel lblNbCarteRestant2		= new JLabel("Nombre de carte restant : "); 

		this.btnPiocheCarteWagon 		= new JButton("");
		this.btnPiocheCarteObjectif 	= new JButton("");
		this.btnFinDuTour 				= new JButton("Fin du tour");

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.btnPiocheCarteWagon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.btnPiocheCarteObjectif.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.btnFinDuTour.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.btnPiocheCarteWagon.setBackground(Color.WHITE);
		this.btnPiocheCarteObjectif.setBackground(Color.WHITE);
		this.btnFinDuTour.setBackground(Color.WHITE);


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

		/**
		 * Activation des composants
		 */

		this.btnPiocheCarteWagon.addActionListener(this);
		this.btnPiocheCarteObjectif.addActionListener(this);
		this.btnFinDuTour.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnPiocheCarteWagon)
		{
		}
	}
}
