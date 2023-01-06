package ihm.sectionJeu;

import main.Controleur;
import metier.CarteWagon;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Dimension;

import java.awt.Color;

public class PanelHautJeu extends JPanel
{
	private Controleur ctrl;

	private JLabel lblTour;
	private JLabel lblProfil;
	private JPanel panelDispoCarte;
	private int carteDispoMarchée;

	public PanelHautJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(0,70));

		JPanel panelTourJoueur = new JPanel(new GridLayout(this.ctrl.getNbJoueurMax(),1, 15,15));
		JPanel panelProfilJoueur = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 50));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.lblTour = new JLabel("C'est au tour de ");
		this.panelDispoCarte = new JPanel(new GridLayout(1,5));
		this.carteDispoMarchée = 0;

		/**
		 * Positionnement des composants
		 */

		 //this.majCarteMarcher();
		
		/*
		for(int i = 0; i < this.ctrl.getNbJoueurMax(); i++)
		{
			panelTourJoueur.add(new JLabel("Joueur " + (i + 1)));
			lblProfil = new JLabel("Joueur " + (i + 1));
			lblProfil.setBorder(border);
			panelProfilJoueur.add(lblProfil);
		}
		*/

		panelTourJoueur.setBorder(border);
		panelProfilJoueur.setBorder(border);

		this.add(panelDispoCarte, BorderLayout.EAST);
		this.add(panelProfilJoueur);

		/**
		 * Activation des composants
		 */
	}

	public void majCarteMarcher()
	{
		
		while(this.carteDispoMarchée < 5 /*&& this.ctrl.get().size() > 0*/)
		{
			JPanel tmp = new JPanel();
			CarteWagon carteWagon = this.ctrl.getLstCarteWagon().remove((int)(Math.random()*this.ctrl.getLstCarteWagon().size()));
			System.out.println( "n°"+this.carteDispoMarchée + carteWagon);
			tmp.setBackground(carteWagon.getColor());
			this.panelDispoCarte.add(tmp);
			this.carteDispoMarchée++;
		}
	}
}
