package ihm.sectionJeu;

import main.Controleur;
import metier.CarteObjectif;

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
import java.awt.Font;
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
	private afficherCarteObjectif[] carteObjectifInfo;
	private JButton btnValider;
	protected int nbPopUp = 1;

	public PanelGaucheJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(5,1,0,0));
		this.setPreferredSize(new Dimension(350, 0));

		JLabel lblPiocheCarteWagon		= new JLabel("Piocher une carte " + this.ctrl.getMoyenDeTransport(), JLabel.CENTER);
		JLabel lblPiocheCarteObjectif	= new JLabel("Piocher une carte objectif", JLabel.CENTER);
		ImageIcon imageIconBtnPiocheCarteObjectif = new ImageIcon(this.ctrl.getNomImage());
		ImageIcon imageIconBtnPiocheCarteWagon = new ImageIcon("donnee/imageCarte/voiture.jpg");

		lblPiocheCarteWagon.setFont(new Font("", Font.BOLD, 16));
		lblPiocheCarteObjectif.setFont(new Font("", Font.BOLD, 16));

		imageIconBtnPiocheCarteObjectif.setImage(imageIconBtnPiocheCarteObjectif.getImage().getScaledInstance(250, 150, java.awt.Image.SCALE_DEFAULT));
		imageIconBtnPiocheCarteWagon.setImage(imageIconBtnPiocheCarteWagon.getImage().getScaledInstance(300, 100, java.awt.Image.SCALE_DEFAULT));

		this.btnPiocheCarteWagon 		= new JButton(imageIconBtnPiocheCarteWagon);
		this.btnPiocheCarteObjectif 	= new JButton(imageIconBtnPiocheCarteObjectif);
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
			this.creerPopUpCarteObjectif();
		}

		if(e.getSource() == this.btnValider)
		{
			int nbSelectionner = 0;

			if(this.carteObjectifInfo[0].isSelectionner()) nbSelectionner++;
			if(this.carteObjectifInfo[1].isSelectionner()) nbSelectionner++;
			if(this.carteObjectifInfo[2].isSelectionner()) nbSelectionner++;

			if(nbSelectionner < 1)
			{
				JOptionPane.showMessageDialog(null, "Veuillez choisir 1 carte objectif", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
			{
				this.dialog.dispose();
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
					jDialog.setBounds(650, 250, 600, 600);
					jDialog.setResizable(false);
					jDialog.setModal(true);
					jDialog.setTitle("Visualisation de vos cartes");
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


	public class afficherCarteObjectif extends JPanel implements ActionListener
	{
		private CarteObjectif carteObjectif;
		private JButton btnChoixCarte;
		private GenereImageCarteObjectif affichageObjectif;
		private boolean selection;

		public afficherCarteObjectif(CarteObjectif carteObjectif)
		{

			JLabel lblObjectif = new JLabel("Objectif : " + carteObjectif.getNoeudDep().getNom() + " à " + carteObjectif.getNoeudArr().getNom());
			lblObjectif.setHorizontalAlignment(JLabel.CENTER);
			lblObjectif.setFont(new Font("", Font.BOLD, 13));
			lblObjectif.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


			this.setLayout(new BorderLayout());
			this.carteObjectif = carteObjectif;
			this.btnChoixCarte = new JButton("Choisir carte");
			this.affichageObjectif = new GenereImageCarteObjectif(this.carteObjectif, PanelGaucheJeu.this.ctrl.getNomImage(), PanelGaucheJeu.this.ctrl.getWidthPanel(), PanelGaucheJeu.this.ctrl.getHeightPanel());
			this.selection = false;
			
			btnChoixCarte.setBackground(Color.WHITE);

			this.add(lblObjectif,BorderLayout.NORTH);
			this.add(this.affichageObjectif,BorderLayout.CENTER);
			this.add(this.btnChoixCarte,BorderLayout.SOUTH);

			this.btnChoixCarte.addActionListener(this);			
		}

		public boolean isSelectionner(){return this.selection;}

		public CarteObjectif getCarteObjectif()	{return this.carteObjectif;}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == this.btnChoixCarte)
			{
				
				if(!this.selection) this.affichageObjectif.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				else this.affichageObjectif.setBorder(BorderFactory.createEmptyBorder());

				this.selection = !this.selection;
			}	
		}
	}


	public JDialog creerPopUpCarteObjectif()
	{
		this.dialog = new JDialog();
		this.dialog.setTitle("Joueur " + this.nbPopUp + ", choisissez au moins une carte objectif");
		this.dialog.setLayout(new BorderLayout());
		this.dialog.setBounds(500, 400, 1000, 400);
		this.dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.dialog.setResizable(false);

		this.carteObjectifInfo = new afficherCarteObjectif[3];
		this.btnValider = new JButton("Valider");

		JPanel panelDispoCarte = new JPanel(new GridLayout(1,3));
		JPanel panelBtn		   = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,  50));

		for(int index = 0; index < 3; index++)
		{
			this.carteObjectifInfo[index] = new afficherCarteObjectif(this.ctrl.getLstCarteObjectif().remove(0));
			panelDispoCarte.add(this.carteObjectifInfo[index]);
		}

		btnValider.setBackground(Color.WHITE);

		panelBtn.add(this.btnValider);

		this.dialog.add(panelDispoCarte,BorderLayout.CENTER);
		this.dialog.add(panelBtn,BorderLayout.SOUTH);

		this.btnValider.addActionListener(this);

		this.dialog.setVisible(true);

		return this.dialog;
	}
}
