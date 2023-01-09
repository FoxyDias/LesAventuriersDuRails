package ihm.sectionJeu;

import main.Controleur;
import metier.CarteWagon;

import javax.swing.JPanel;
import javax.swing.JButton;

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
		this.setPreferredSize(new java.awt.Dimension(0, 100));
		

		/**
		 * Positionnement des composants
		 */
		for(int index = 0; index < 5; index++)
		{
			Collections.shuffle(this.ctrl.getLstCarteWagon());
			this.add(new PanelPiocheMarcher(this.ctrl.getLstCarteWagon().remove(0)));
			
			//this.add(new PanelPiocheMarcher(this.ctrl.getLstCarteWagon().remove(0)));
		}

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

			this.btnPrendCarte = new JButton("prendre carte");

			this.add(this.btnPrendCarte);
			this.setBackground(this.carteWagon.getColor());
			this.btnPrendCarte.addActionListener(this);
		}

		public void majMarcher(CarteWagon carteWagon)
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
				PanelBasJeu.this.ctrl.getEstJoueurCourant().ajouterCarteWagon(this.carteWagon);
				this.majMarcher(PanelBasJeu.this.ctrl.getLstCarteWagon().remove(0));
			}
		}

	}
	
}

