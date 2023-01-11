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

import javax.swing.ImageIcon;
import java.awt.Image;

public class PanelGaucheJeu extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnPiocheCarteWagon;
	private JButton btnPiocheCarteObjectif;

	/* ----- JDialog ---  */
	private JDialog dialog;
	private AfficherCarteObjectif[] carteObjectifInfo;
	private JButton btnValider;
	protected int nbPopUp = 1;

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
		ImageIcon imageIconBtnPiocheCarteWagon = new ImageIcon("donnee/imageCarte/" + this.ctrl.getVersoCarteWagon()); 

		lblPiocheCarteWagon.setFont(new Font("", Font.BOLD, 16));
		lblPiocheCarteObjectif.setFont(new Font("", Font.BOLD, 16));

		imageIconBtnPiocheCarteObjectif.setImage(imageIconBtnPiocheCarteObjectif.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_DEFAULT));
		imageIconBtnPiocheCarteWagon.setImage(imageIconBtnPiocheCarteWagon.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_DEFAULT));

		this.btnPiocheCarteWagon 		= new JButton(imageIconBtnPiocheCarteWagon);
		this.btnPiocheCarteObjectif 	= new JButton(imageIconBtnPiocheCarteObjectif);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.btnPiocheCarteWagon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.btnPiocheCarteObjectif.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.btnPiocheCarteWagon.setBackground(Color.WHITE);
		this.btnPiocheCarteObjectif.setBackground(Color.WHITE);


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
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnPiocheCarteWagon)
		{
			JDialog jDialog = new JDialog();
			jDialog.setBounds(650, 350, 600, 300);
			jDialog.setResizable(false);
			jDialog.setModal(true);
			jDialog.setTitle("Vous avez pioché une carte " + this.ctrl.getMoyenDeTransport() + " de couleur : " + this.ctrl.getLstCarteWagon().get(0).getColor());
			jDialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);

			if(this.ctrl.getLstCarteWagon().get(0).getColor() == null)
			{
				jDialog.setTitle("Vous avez pioché une carte " + this.ctrl.getMoyenDeTransport() + " multicolore");
			}
			
			ImageIcon icon = new ImageIcon(this.ctrl.getLstCarteWagon().get(0).getRecto());

            icon.setImage(icon.getImage().getScaledInstance(jDialog.getWidth() -5,jDialog.getHeight()-10,Image.SCALE_DEFAULT));

			JLabel lblImage = new JLabel(icon);
			
			this.ctrl.getEstJoueurCourant().getMainWagon().add(this.ctrl.getLstCarteWagon().get(0));
			this.ctrl.getLstCarteWagon().remove(0);

			this.ctrl.griserCarte(false);
			this.ctrl.ajouterNbPiocheWagon();

			jDialog.add(lblImage);
			jDialog.setVisible(true);
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
						this.ctrl.getListCarteObjectif().remove(this.carteObjectifInfo[index].getCarteObjectif());
						this.ctrl.repiocherCarteObjectif(index);
					}
				this.ctrl.avancerJoueur();
			}
		}
	}

	public void griserCarteObjectif(boolean b) {
		this.btnPiocheCarteObjectif.setEnabled(b);
	}

	/**
	 * Sous classe à déplacer
	 */
	

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
		this.dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);

		this.carteObjectifInfo = new AfficherCarteObjectif[3];
		this.btnValider = new JButton("Valider");

		JPanel panelDispoCarte = new JPanel(new GridLayout(1,3));
		JPanel panelBtn		   = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,  50));

		for(int index = 0; index < 3; index++)
		{
			this.carteObjectifInfo[index] = new AfficherCarteObjectif(this.ctrl.getLstPiocheObjectifs().get(index));
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
