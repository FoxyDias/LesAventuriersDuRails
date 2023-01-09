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
	private AfficherCarteObjectif[] carteObjectifInfo;
	private JButton btnValider;
	private JButton btnAnnuler;
	protected int nbPopUp = 1;
	private   int cpt 	  = 0;

	public PanelGaucheJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl   = ctrl;
		this.setLayout(new GridLayout(6,1,0,0));


		JPanel panelHautGrid = new JPanel(new BorderLayout());
		JPanel panelHautLbl = new JPanel();
		JPanel panelHautImg = new JPanel();

		JPanel panelMilieuGrid = new JPanel(new BorderLayout());
		JPanel panelMilieuLbl = new JPanel();			
		JPanel panelMilieuImg = new JPanel();

		JLabel lblPiocheCarteWagon		= new JLabel("Piocher une carte " + this.ctrl.getMoyenDeTransport(), JLabel.CENTER);
		JLabel lblPiocheCarteObjectif	= new JLabel("Piocher une carte objectif", JLabel.CENTER);
		ImageIcon imageIconBtnPiocheCarteObjectif = new ImageIcon(this.ctrl.getNomImage());
		ImageIcon imageIconBtnPiocheCarteWagon = new ImageIcon("donnee/imageCarte/voiture.jpg");

		lblPiocheCarteWagon.setFont(new Font("", Font.BOLD, 16));
		lblPiocheCarteObjectif.setFont(new Font("", Font.BOLD, 16));

		imageIconBtnPiocheCarteObjectif.setImage(imageIconBtnPiocheCarteObjectif.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_DEFAULT));
		imageIconBtnPiocheCarteWagon.setImage(imageIconBtnPiocheCarteWagon.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_DEFAULT));

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
		panelHautLbl.add(lblPiocheCarteWagon);
		panelHautImg.add(this.btnPiocheCarteWagon);
		panelHautGrid.add(panelHautLbl, BorderLayout.NORTH);
		panelHautGrid.add(panelHautImg, BorderLayout.CENTER);

		panelMilieuLbl.add(lblPiocheCarteObjectif);
		panelMilieuImg.add(this.btnPiocheCarteObjectif);
		panelMilieuGrid.add(panelMilieuLbl, BorderLayout.NORTH);
		panelMilieuGrid.add(panelMilieuImg, BorderLayout.CENTER);

		this.add(new JPanel());
		this.add(panelMilieuGrid);
		this.add(new JPanel());
		this.add(panelHautGrid);
		this.add(new JPanel());
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
			JDialog jDialog = new JDialog();
			jDialog.setLayout(new GridLayout(2,1));
			JPanel panelLabel = new JPanel();
			JPanel panelCouleur = new JPanel(new GridLayout(1,3));
			JPanel panelSetBk = new JPanel();
			panelSetBk.setBackground(PanelGaucheJeu.this.ctrl.getLstCarteWagon().get(0).getColor());
			panelSetBk.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			JLabel  lblCouleurChoisis = new JLabel("Vous avez pioché une carte de couleur : " + PanelGaucheJeu.this.ctrl.getLstCarteWagon().get(0).getColor(), JLabel.CENTER); 

			
			cpt++;
			jDialog.setBounds(650, 250, 600, 300);
			jDialog.setResizable(false);
			jDialog.setModal(true);
			jDialog.setTitle("Visualisation de la carte piochée ");

			panelSetBk.setBackground(PanelGaucheJeu.this.ctrl.getLstCarteWagon().get(0).getColor());

			panelLabel.add(lblCouleurChoisis);
			panelCouleur.add(new JPanel());
			panelCouleur.add(panelSetBk);
			panelCouleur.add(new JPanel());
			
			this.ctrl.getEstJoueurCourant().getMainWagon().add(this.ctrl.getLstCarteWagon().get(0));
			this.ctrl.getLstCarteWagon().remove(0);

			jDialog.add(panelLabel);
			jDialog.add(panelCouleur);
			jDialog.setVisible(true);
		}
		if(this.cpt == 2)
		{
			this.ctrl.avancerJoueur();
			this.cpt = 0;
		}
	
		if(e.getSource() == this.btnArreterPartie)
		{
			if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment arrêter la partie ?", "Fin de partie", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				this.ctrl.changerPanel("Menu");
			
		}

		if(e.getSource() == this.btnFinDuTour)
		{
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

				for(int index = 0; index < this.carteObjectifInfo.length; index++)
					if(this.carteObjectifInfo[index].isSelectionner()) {
						this.ctrl.getEstJoueurCourant().ajouterCarteObjectif(this.carteObjectifInfo[index].getCarteObjectif());
						this.ctrl.repiocherCarteObjectif(index);
					}

				this.ctrl.avancerJoueur();
			}
		}

		if(e.getSource() == this.btnAnnuler)
		{
			this.dialog.dispose();
		}
	}

	
	public class PanelDispoParam extends JPanel implements ActionListener
	{
		private Controleur ctrl;
		private JButton btnVisualisation;
		private JButton btnFinTour;
		private JButton btnFinPartie;
		private JButton btnValiderRecap;

		private JDialog dialogRecap;

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
				if(this.ctrl.getEstJoueurCourant().getMainWagon().size() != 0 || this.ctrl.getEstJoueurCourant().getMainObjectif().size() != 0 )
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
					this.dialogRecap = new JDialog();
					JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,50));
					this.dialogRecap.setTitle("Récapitulatif de la partie");
					this.dialogRecap.setBounds(650,350,500,500);
					this.dialogRecap.setResizable(false);
					this.dialogRecap.setModal(true);
					this.dialogRecap.setLayout(new GridLayout(5,1,0,5));

					this.btnValiderRecap = new JButton("Quitter");

					panelBtn.add(this.btnValiderRecap);

					this.dialogRecap.add(new JLabel("Joueur : " + this.ctrl.getJoueur(this.ctrl.getIntJoueurActuel()) , JLabel.CENTER));
					this.dialogRecap.add(new JLabel("Nombre de points cummulés avec les chemins : ", JLabel.CENTER));
					this.dialogRecap.add(new JLabel("Nombre de points cummulés avec les cartes objectifs : ", JLabel.CENTER));
					this.dialogRecap.add(new JLabel("Nombre de points du plus long chemin : ", JLabel.CENTER));
					this.dialogRecap.add(panelBtn);

					this.btnValiderRecap.addActionListener(this);

					this.dialogRecap.setVisible(true);
				}
			}

			if(e.getSource() == this.btnFinTour)
			{
				this.ctrl.avancerJoueur();
			}

			if(e.getSource() == this.btnValiderRecap)
			{	
				this.dialogRecap.dispose();
				this.ctrl.changerPanel("Menu");
			}
		}
	}

	public class AfficherCarteObjectif extends JPanel implements ActionListener
	{
		private CarteObjectif carteObjectif;
		private JButton btnChoixCarte;
		private GenereImageCarteObjectif affichageObjectif;
		private boolean selection;

		public AfficherCarteObjectif(CarteObjectif carteObjectif)
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

		this.carteObjectifInfo = new AfficherCarteObjectif[3];
		this.btnValider = new JButton("Valider");
		this.btnAnnuler = new JButton("Annuler");

		JPanel panelDispoCarte = new JPanel(new GridLayout(1,3));
		JPanel panelBtn		   = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,  50));

		for(int index = 0; index < 3; index++)
		{
			this.carteObjectifInfo[index] = new AfficherCarteObjectif(this.ctrl.getLstPiocheObjectifs().get(index));
			panelDispoCarte.add(this.carteObjectifInfo[index]);
		}

		btnValider.setBackground(Color.WHITE);

		panelBtn.add(this.btnValider);
		panelBtn.add(this.btnAnnuler);

		this.dialog.add(panelDispoCarte,BorderLayout.CENTER);
		this.dialog.add(panelBtn,BorderLayout.SOUTH);

		this.btnValider.addActionListener(this);
		this.btnAnnuler.addActionListener(this);

		this.dialog.setVisible(true);

		return this.dialog;
	}
}
