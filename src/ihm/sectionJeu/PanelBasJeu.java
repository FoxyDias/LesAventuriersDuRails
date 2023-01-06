package ihm.sectionJeu;

import main.Controleur;
import metier.CarteWagon;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

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
			JButton btnPrendCarte = new JButton();
			/* Ajouter la fonctionnalitée de changmeent d'image quand il n'y a plus de carte d'un certain type */
			ImageIcon btnIcon = new ImageIcon("donnee/imageCarte/voiture.jpg");
			btnIcon.setImage(btnIcon.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_DEFAULT));
			btnPrendCarte.setIcon(btnIcon);
			this.add(btnPrendCarte);			
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

			this.add(this.btnPrendCarte);
			this.btnPrendCarte.addActionListener(this);
		}

		public void majMarcher(CarteWagon carteWagon)
		{
			this.carteWagon = carteWagon;
			this.setBackground(getBackground());
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

