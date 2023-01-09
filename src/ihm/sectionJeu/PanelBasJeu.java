package ihm.sectionJeu;

import main.Controleur;
import metier.CarteWagon;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class PanelBasJeu extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private PanelPiocheMarcher[] tabPanelPioche;

	public PanelBasJeu(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.tabPanelPioche = new PanelPiocheMarcher[this.ctrl.getLstPiocheWagon().size()];
		/**
		 * Création des composants
		 */
		this.setLayout(new GridLayout(1,5));
		this.setPreferredSize(new java.awt.Dimension(0, 100));

		/**
		 * Positionnement des composants
		 */
		for( int i=0; i<this.ctrl.getLstPiocheWagon().size(); i++ )
		{
			CarteWagon cw = this.ctrl.getLstCarteWagon().get(i);
			PanelPiocheMarcher pan = new PanelPiocheMarcher(ctrl, this, cw);
			this.tabPanelPioche[i] = pan;
			this.add(pan);
		}
	}

	public class PanelPiocheMarcher extends JPanel
	{
		private Controleur ctrl;
		private PanelBasJeu papa;
		private CarteWagon carteWagon;
		private JButton btnPrendCarte;

		public PanelPiocheMarcher( Controleur ctrl, PanelBasJeu papa, CarteWagon carteWagon) {
			this.ctrl = ctrl;
			this.papa = papa;
			this.carteWagon = carteWagon;

			this.btnPrendCarte = new JButton();

			this.add(this.btnPrendCarte);

			this.btnPrendCarte.addActionListener(papa);

			this.setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {

			if(this.carteWagon.getRecto() == null) {
				this.btnPrendCarte.setBackground(this.carteWagon.getColor());
			}
			else
			{
				ImageIcon icon = new ImageIcon(this.carteWagon.getRecto());
				icon.getImage().getScaledInstance(this.btnPrendCarte.getWidth(),this.btnPrendCarte.getHeight(),Image.SCALE_DEFAULT);
				this.btnPrendCarte.setIcon(icon);
			}
			super.paintComponent(g);
		}

		public void majMarcher(CarteWagon carteWagon)
		{
			this.carteWagon = carteWagon;
			this.repaint();
		}

		public JButton getBtnPrendCarte() {
			return btnPrendCarte;
		}

		public CarteWagon getCarteWagon() {
			return carteWagon;
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		for(int i = 0; i<this.ctrl.getLstPiocheWagon().size(); i++)
		{
			if (e.getSource() == this.tabPanelPioche[i].getBtnPrendCarte()) {

				PanelBasJeu.this.ctrl.getEstJoueurCourant().ajouterCarteWagon(this.tabPanelPioche[i].getCarteWagon());
				this.ctrl.repiocherCarteWagon(i);

				this.tabPanelPioche[i].majMarcher(this.ctrl.getLstPiocheWagon().get(i));

				this.ctrl.ajouterNbPiocheWagon();
				if(this.ctrl.getNbPiocheWagon()==2)
				{
					this.ctrl.ajouterNbPiocheWagon();
					this.ctrl.avancerJoueur();
				}

			}
		}
	}
	
}

