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

import javax.swing.ImageIcon;


public class PanelGaucheJeu extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnPiocheCarteWagon;
	private JButton btnFinDuTour;
	private JButton btnPiocheCarteObjectif;
	private JButton btnArreterPartie;

	/* ----- JDialog ---  */
	private JDialog dialog;
	private JButton btnCarteObjectif1;
	private JButton btnCarteObjectif2;
	private JButton btnCarteObjectif3;
	private JButton btnValider;

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
			JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER,0,75));

			this.dialog = new JDialog();
			this.dialog.setTitle("Joueur " + this.ctrl.getEstJoueurCourant() + ", choisissez une de ces 3 cartes objectif :");
			this.dialog.setLayout(new GridLayout(2,3));
			this.dialog.setBounds(500, 400, 1000, 400);
			this.dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			this.dialog.setResizable(false);

			this.btnCarteObjectif1 = new JButton(new ImageIcon(this.ctrl.getVersoCarteObjectif()));
			this.btnCarteObjectif2 = new JButton(new ImageIcon(this.ctrl.getVersoCarteObjectif()));
			this.btnCarteObjectif3 = new JButton(new ImageIcon(this.ctrl.getVersoCarteObjectif()));
			this.btnValider = new JButton("Valider");

			this.btnCarteObjectif1.setBackground(Color.WHITE);
			this.btnCarteObjectif2.setBackground(Color.WHITE);
			this.btnCarteObjectif3.setBackground(Color.WHITE);
			this.btnValider.setBackground(Color.WHITE);

			panelBtn.add(this.btnValider);
		
			this.dialog.add(btnCarteObjectif1);
			this.dialog.add(btnCarteObjectif2);
			this.dialog.add(btnCarteObjectif3);
			this.dialog.add(new JLabel());
			this.dialog.add(panelBtn);
			this.dialog.add(new JLabel());
			this.dialog.setVisible(true);

			this.btnCarteObjectif1.addActionListener(this);
			this.btnCarteObjectif2.addActionListener(this);
			this.btnCarteObjectif3.addActionListener(this);
			this.btnValider.addActionListener(this);
		}

		if(e.getSource() == this.btnCarteObjectif1)
		{
			this.ctrl.inverseEtatBtn(this.btnCarteObjectif1);
		}

		if(e.getSource() == this.btnCarteObjectif2)
		{
			this.ctrl.inverseEtatBtn(this.btnCarteObjectif2);
		}

		if(e.getSource() == this.btnCarteObjectif3)
		{
			this.ctrl.inverseEtatBtn(this.btnCarteObjectif3);
		}

		
		if(e.getSource() == this.btnValider)
		{
			//Vérification que l'utilisateur a bien choisi une carte objectif
			if(this.btnCarteObjectif1.isOpaque() && !(this.btnCarteObjectif2.isOpaque()) && !(this.btnCarteObjectif3.isOpaque()) 	|| 
			   this.btnCarteObjectif2.isOpaque() && !(this.btnCarteObjectif1.isOpaque()) && !(this.btnCarteObjectif3.isOpaque()) 	||
			   this.btnCarteObjectif3.isOpaque() && !(this.btnCarteObjectif1.isOpaque()) && !(this.btnCarteObjectif2.isOpaque()) 	||
			   this.btnCarteObjectif1.isOpaque() && this.btnCarteObjectif2.isOpaque() && !(this.btnCarteObjectif3.isOpaque()) 		||
			   this.btnCarteObjectif1.isOpaque() && this.btnCarteObjectif3.isOpaque() && !(this.btnCarteObjectif2.isOpaque()) 		||
			   this.btnCarteObjectif2.isOpaque() && this.btnCarteObjectif3.isOpaque() && !(this.btnCarteObjectif1.isOpaque()) 		||
			   this.btnCarteObjectif1.isOpaque() && this.btnCarteObjectif2.isOpaque() && this.btnCarteObjectif3.isOpaque())
			{
				this.dialog.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Veuillez choisir au moins 1 carte objectifs", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
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
