package ihm.sectionJeu;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;

import main.Controleur;
import metier.CarteObjectif;

public class PanelMainJoueur extends JPanel 
{
	private Controleur ctrl;


	public PanelMainJoueur(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setSize(600,1000);
		this.setLayout(new GridLayout(2,1,5,5));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));


		this.add(new PanelDispoCarteWagon());
		this.add(new PanelDispoCarteObjectif());	
		this.add(new PanelInformationCarte());
		this.repaint();	
	}

	public class PanelDispoCarteWagon extends JPanel implements AdjustmentListener
	{
		private JLabel lblInfoNumeroCarte;
		private JPanel panelCoulCarteWagon;
		private JLabel lblCoulCarteWagon;
		private JScrollBar scrollBar;

		public PanelDispoCarteWagon()
		{
			this.setLayout(new BorderLayout());
		
			this.lblInfoNumeroCarte = new JLabel("", JLabel.CENTER);
			this.panelCoulCarteWagon = new JPanel();
			this.lblCoulCarteWagon = new JLabel();

			this.scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
			this.scrollBar.setUnitIncrement(1);
			this.scrollBar.setBlockIncrement(1);
			this.scrollBar.setBackground(Color.WHITE);
			this.scrollBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			this.initValeurCarteWagon();

			this.panelCoulCarteWagon.add(this.lblCoulCarteWagon);

			this.add(this.lblInfoNumeroCarte,BorderLayout.NORTH);
			this.add(this.scrollBar,BorderLayout.SOUTH);
			this.add(this.panelCoulCarteWagon,BorderLayout.CENTER);

			this.scrollBar.addAdjustmentListener(this);
		}

		public void initValeurCarteWagon()
		{
			this.scrollBar.setMinimum(0);
			this.scrollBar.setMaximum(1);
			this.lblInfoNumeroCarte.setText("1 / " + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().size());
			ImageIcon icon = new ImageIcon(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().get(this.scrollBar.getValue()).getRecto());
			icon.setImage(icon.getImage().getScaledInstance(this.panelCoulCarteWagon.getWidth() -5,this.panelCoulCarteWagon.getHeight()-10,Image.SCALE_DEFAULT));
			this.lblCoulCarteWagon.setIcon(icon);
		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {

			if(e.getSource() == this.scrollBar)
			{
				this.scrollBar.setMaximum(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().size());
				this.lblInfoNumeroCarte.setText((e.getValue()+1)+ " / " + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().size());
				ImageIcon icon = new ImageIcon(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().get(this.scrollBar.getValue()).getRecto());
				icon.setImage(icon.getImage().getScaledInstance(this.panelCoulCarteWagon.getWidth() -5,this.panelCoulCarteWagon.getHeight()-10,Image.SCALE_DEFAULT));
				this.lblCoulCarteWagon.setIcon(icon);
			}

		}
	}

	public class PanelDispoCarteObjectif extends JPanel implements AdjustmentListener
	{
		private JLabel lblInfoNumeroCarte;
		private CarteObjectif carteObjectif;
		private GenereImageCarteObjectif affichageCarte;
		private JScrollBar scrollBar;

		public PanelDispoCarteObjectif()
		{
			this.setLayout(new BorderLayout());
			this.lblInfoNumeroCarte = new JLabel("", JLabel.CENTER);

			this.scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
			this.scrollBar.setUnitIncrement(1);
			this.scrollBar.setBlockIncrement(1);
			this.scrollBar.setBackground(Color.WHITE);
			this.scrollBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			this.initValeurCarteObjectif();
			
			this.affichageCarte = new GenereImageCarteObjectif(this.carteObjectif, PanelMainJoueur.this.ctrl.getNomImage(),PanelMainJoueur.this.ctrl.getWidthPanel(),PanelMainJoueur.this.ctrl.getHeightPanel());

			this.add(this.lblInfoNumeroCarte,BorderLayout.NORTH);
			this.add(affichageCarte,BorderLayout.CENTER);
			this.add(this.scrollBar,BorderLayout.SOUTH);

			this.scrollBar.addAdjustmentListener(this);
		}

		public void initValeurCarteObjectif()
		{
			this.scrollBar.setMinimum(0);
			this.scrollBar.setMaximum(1);
			this.lblInfoNumeroCarte.setText("1 / " + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainObjectif().size());
			this.carteObjectif = PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainObjectif().get(this.scrollBar.getValue());
			this.affichageCarte = new GenereImageCarteObjectif(this.carteObjectif,PanelMainJoueur.this.ctrl.getNomImage(),PanelMainJoueur.this.ctrl.getWidthPanel(),PanelMainJoueur.this.ctrl.getHeightPanel());
		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			
			if(e.getSource() == this.scrollBar)
			{
				
				this.scrollBar.setMaximum(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteObjectif());
				this.lblInfoNumeroCarte.setText((e.getValue()+1)+ " / " + this.scrollBar.getMaximum());
				this.carteObjectif = PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainObjectif().get(e.getValue());
				this.affichageCarte.setCarteObjectif(this.carteObjectif);
			}	
		}
	}

	public class PanelInformationCarte extends JPanel 
	{
		public PanelInformationCarte()
		{
			this.setLayout(new GridLayout(8,2,0,5));
			
			JLabel lblNbCouleurRouge	= new JLabel("" + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon(), JLabel.CENTER);
			JLabel lblLibelleRouge		= new JLabel(" Cartes rouges : ", JLabel.LEFT);
			JLabel lblNbCouleurBleu		= new JLabel("" + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon(), JLabel.CENTER);
			JLabel lblLibelleBleu		= new JLabel(" Cartes bleues : ", JLabel.LEFT);
			JLabel lblNbCouleurVert		= new JLabel("" +PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon(), JLabel.CENTER);
			JLabel lblLibelleVert		= new JLabel(" Cartes vertes : ", JLabel.LEFT);
			JLabel lblNbCouleurJaune	= new JLabel("" + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon(), JLabel.CENTER);
			JLabel lblLibelleJaune		= new JLabel(" Cartes jaunes : ", JLabel.LEFT);
			JLabel lblNbCouleurRose 	= new JLabel("" + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon(), JLabel.CENTER);
			JLabel lblLibelleRose		= new JLabel(" Cartes roses : ", JLabel.LEFT);
			JLabel lblNbCouleurOrange	= new JLabel("" + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon(), JLabel.CENTER);
			JLabel lblLibelleOrange		= new JLabel(" Cartes oranges : ", JLabel.LEFT);
			JLabel lblNbCouleurGris		= new JLabel("" + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon(), JLabel.CENTER);
			JLabel lblLibelleGris		= new JLabel(" Cartes grises : ", JLabel.LEFT);
			JLabel lblNbCouleurMulti 	= new JLabel("" + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon(), JLabel.CENTER);
			JLabel lblLibelleMulti		= new JLabel(" Cartes multicouleurs : ", JLabel.LEFT);

			this.add(lblLibelleRouge);
			this.add(lblNbCouleurRouge);
			this.add(lblLibelleBleu);
			this.add(lblNbCouleurBleu);
			this.add(lblLibelleVert);
			this.add(lblNbCouleurVert);
			this.add(lblLibelleJaune);
			this.add(lblNbCouleurJaune);
			this.add(lblLibelleRose);
			this.add(lblNbCouleurRose);
			this.add(lblLibelleOrange);
			this.add(lblNbCouleurOrange);
			this.add(lblLibelleGris);
			this.add(lblNbCouleurGris);
			this.add(lblLibelleMulti);
			this.add(lblNbCouleurMulti);
		}
	}
}
