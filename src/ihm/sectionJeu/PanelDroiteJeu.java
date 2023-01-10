package ihm.sectionJeu;

import main.Controleur;
import metier.CarteWagon;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PanelDroiteJeu extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private PanelPiocheMarcher[] tabPanelPioche;

	public PanelDroiteJeu(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.tabPanelPioche = new PanelPiocheMarcher[this.ctrl.getLstPiocheWagon().size()];
		/**
		 * Création des composants
		 */
		this.setLayout(new GridLayout(5,1,5,5));
		this.setPreferredSize(new java.awt.Dimension(200, 0));

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

	public void griserCarteWagon(boolean b) {
		for(int j=0; j<this.tabPanelPioche.length; j++)
		{
			if(this.tabPanelPioche[j].getCarteWagon().getCouleur().equals("Joker"))
				this.tabPanelPioche[j].getBtnPrendCarte().setEnabled(b);
		}
	}

	public class PanelPiocheMarcher extends JPanel
	{
		private CarteWagon carteWagon;
		private JButton btnPrendCarte;

		public PanelPiocheMarcher( Controleur ctrl, PanelDroiteJeu panelDroiteJeu, CarteWagon carteWagon) {
			
			this.carteWagon = carteWagon;
			this.btnPrendCarte = new JButton();

			this.add(this.btnPrendCarte);

			this.btnPrendCarte.addActionListener(panelDroiteJeu);
		}

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);

			if(this.carteWagon.getRecto() == null) {
				this.btnPrendCarte.setBackground(this.carteWagon.getColor());
			}
			else
			{
				ImageIcon icon = new ImageIcon(this.carteWagon.getRecto());
				icon.setImage(icon.getImage().getScaledInstance(this.getWidth()-10,this.getHeight()-10,Image.SCALE_DEFAULT));
				this.btnPrendCarte.setIcon(icon);
			}

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
		for(int i = 0; i < this.ctrl.getLstPiocheWagon().size(); i++)
		{
			if (e.getSource() == this.tabPanelPioche[i].getBtnPrendCarte()) {

				this.ctrl.getEstJoueurCourant().ajouterCarteWagon(this.tabPanelPioche[i].getCarteWagon());

				if(this.tabPanelPioche[i].getCarteWagon().getCouleur().equals("Joker") && this.ctrl.getNbPiocheWagon()!=1)
					this.ctrl.avancerJoueur();
				else {
					this.ctrl.ajouterNbPiocheWagon();

					this.ctrl.repiocherCarteWagon(i);

					this.tabPanelPioche[i].majMarcher(this.ctrl.getLstPiocheWagon().get(i));

					if(this.ctrl.getNbPiocheWagon()>=1) {
						if (this.tabPanelPioche[i].getCarteWagon().getCouleur().equals("Joker")) {
							this.tabPanelPioche[i].getBtnPrendCarte().setEnabled(false);
						}

					return;
					}
				}
				this.ctrl.repiocherCarteWagon(i);
				this.tabPanelPioche[i].majMarcher(this.ctrl.getLstPiocheWagon().get(i));
				this.verifierPioche();
			}
		}
	}

	private void verifierPioche()
	{
		int j = 0;
		for (int i = 0; i < this.ctrl.getLstPiocheWagon().size(); i++)
		{
			// SI il y a 3 carte joker, on affiche de nouvelles cartes
			if(this.tabPanelPioche[i].getCarteWagon().getCouleur().equals("Joker"))
			{	
				j++;
			}
		}

		if(j >= 3)
		{
			for (int i = 0; i < this.ctrl.getLstPiocheWagon().size(); i++)
			{
				// SI il y a 3 carte joker, on affiche de nouvelles cartes
				
				this.ctrl.repiocherCarteWagon(i);
				this.tabPanelPioche[i].majMarcher(this.ctrl.getLstPiocheWagon().get(i));
				
			}
		}
	}
}

