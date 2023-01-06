package ihm.sectionJeu;

import main.Controleur;
import metier.CarteObjectif;
import metier.CarteWagon;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Collections;

public class PanelBasJeu extends JPanel
{
	private Controleur ctrl;
	

	public PanelBasJeu(Controleur ctrl)
	{
		this.ctrl = ctrl;
		/**
		 * Création des composants
		 */
		this.setLayout(new GridLayout(1,5));
		
		for(int index = 0; index < 5; index++)
		{
			this.add(new PanelPiocheMarcher(this.ctrl.getLstCarteWagon().remove(0)));
		}
		/**
		 * Positionnement des composants
		 */


		/**
		 * Activation des composants
		 */
	}

	public class PanelPiocheMarcher extends JPanel implements ActionListener
	{
		private CarteWagon carteWagon;
		private JButton btnPrendCarte;

		public PanelPiocheMarcher(CarteWagon carteWagon)
		{
			this.carteWagon = carteWagon;
			this.setBackground(this.carteWagon.getColor());
			this.setBorder(BorderFactory.createLineBorder(Color.black));

			this.btnPrendCarte = new JButton("PrendCarte");

			this.add(this.btnPrendCarte);

			this.btnPrendCarte.addActionListener(this);
		}

		public void majMacher(CarteWagon carteWagon)
		{
			this.carteWagon = carteWagon;
			this.setBackground(this.carteWagon.getColor());
			this.repaint();
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == this.btnPrendCarte)
			{
				System.out.println(carteWagon.getCouleur());
				this.majMacher(PanelBasJeu.this.ctrl.getLstCarteWagon().remove(0));
			}
		}

	}
	
}

