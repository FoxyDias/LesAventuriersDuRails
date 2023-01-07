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

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;


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
		this.setLayout(new GridLayout(5,1,0,0));
		this.setPreferredSize(new Dimension(350, 0));

		JLabel lblPiocheCarteWagon		= new JLabel("Pioche carte wagon", JLabel.CENTER);
		JLabel lblPiocheCarteObjectif	= new JLabel("Pioche carte objectif", JLabel.CENTER);

		this.btnPiocheCarteWagon 		= new JButton("");
		this.btnPiocheCarteObjectif 	= new JButton("");
		this.btnFinDuTour 				= new JButton("Fin du tour");
		this.btnArreterPartie 			= new JButton("Arrêter la partie");

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

		this.add(lblPiocheCarteObjectif);
		this.add(this.btnPiocheCarteObjectif);
		this.add(lblPiocheCarteWagon);
		this.add(this.btnPiocheCarteWagon);
		this.add(new PanelDispoParam(this.ctrl));
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
			System.out.println("Pioche carte wagon");

		}

		if(e.getSource() == this.btnArreterPartie)
		{
			if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment arrêter la partie ?", "Fin de partie", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				this.ctrl.changerPanel("Menu");
			
		}

		if(e.getSource() == this.btnFinDuTour)
		{
			System.out.println("Fin du tour");
			this.ctrl.avancerJoueur();
		}

		if(e.getSource() == this.btnPiocheCarteObjectif)
		{
			System.out.println("Pioche carte wagon");
		}
	}

	
	public class PanelDispoParam extends JPanel implements ActionListener
	{
		private Controleur ctrl;
		private JButton btnVisualisation;
		private JButton btnFinTour;
		private JButton btnFinPartie;

		public PanelDispoParam(Controleur ctrl)
		{
			/**
			 * Création des composants
			 */
			this.ctrl = ctrl;

			this.setLayout(new BorderLayout());
			this.btnVisualisation = new JButton("Visualiser mes cartes");
			this.btnFinPartie = new JButton("Arrêter la partie");
			this.btnFinTour = new JButton("Fin du tour");

			JPanel panelVisualiser  = new JPanel(new FlowLayout(FlowLayout.CENTER,0, 60));
			JPanel panelDispoBtnBas = new JPanel(new GridLayout(1,2));

			/**
			 * Positionnement des composants
			 */
			 
			panelVisualiser.add(this.btnVisualisation);

			panelDispoBtnBas.add(this.btnFinPartie);
			panelDispoBtnBas.add(this.btnFinTour);

			this.add(panelVisualiser, BorderLayout.CENTER);
			this.add(panelDispoBtnBas,BorderLayout.SOUTH);

			/**
			 * Activation des composants
			 */
			this.btnVisualisation.addActionListener(this);
			this.btnFinPartie.addActionListener(this);
			this.btnFinTour.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == this.btnVisualisation)
			{
				if(this.ctrl.getEstJoueurCourant().getMainWagon().size() != 0)
				{
					JDialog jDialog = new JDialog();
					jDialog.add(new PanelMainJoueur(ctrl));
					jDialog.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Vous n'avez aucune carte");
				}
			}

			if(e.getSource() == this.btnFinPartie)
			{
				if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment arrêtez la partie ?", "Fin de partie", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{
					this.ctrl.changerPanel("Menu");
				}
			}

			if(e.getSource() == this.btnFinTour)
			{
				this.ctrl.avancerJoueur();
			}
		}
	}
}
