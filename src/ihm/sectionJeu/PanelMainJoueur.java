package ihm.sectionJeu;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import main.Controleur;
import metier.CarteObjectif;

public class PanelMainJoueur extends JPanel 
{
	private Controleur ctrl;


	public PanelMainJoueur(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setSize(600,600);
		this.setLayout(new GridLayout(2,1,5,5));
		

		this.add(new PanelDispoCarteWagon());
		this.add(new PanelDispoCarteObjectif());	
		this.repaint();	
	}

	public class PanelDispoCarteWagon extends JPanel implements AdjustmentListener
	{
		private JLabel lblInfoNumeroCarte;
		private JPanel panelCoulCarteWagon;
		private JScrollBar scrollBar;

		public PanelDispoCarteWagon()
		{
			this.setLayout(new BorderLayout());
			this.lblInfoNumeroCarte = new JLabel("0 / " + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon(),JLabel.CENTER);
			this.panelCoulCarteWagon = new JPanel();

			this.scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
			this.scrollBar.setUnitIncrement(1);
			this.scrollBar.setBlockIncrement(1);
			this.scrollBar.setBackground(Color.WHITE);
			
			this.initValeurCarteWagon();

			this.add(this.lblInfoNumeroCarte,BorderLayout.NORTH);
			this.add(this.panelCoulCarteWagon,BorderLayout.CENTER);
			this.add(this.scrollBar,BorderLayout.SOUTH);			

			this.scrollBar.addAdjustmentListener(this);
		}

		public void initValeurCarteWagon()
		{
			ImageIcon icon = new ImageIcon(PanelMainJoueur.this.ctrl.getLstCarteWagon().get(0).getRecto());
            icon.setImage(icon.getImage().getScaledInstance(this.getWidth() -5,this.getHeight()-10,Image.SCALE_DEFAULT));
			JLabel lblImage = new JLabel(icon);

			this.scrollBar.setMinimum(0);
			this.scrollBar.setMaximum(1);
			this.lblInfoNumeroCarte.setText("1 / " + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon());
			this.panelCoulCarteWagon.add(lblImage);
			this.repaint();

		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			
			if(e.getSource() == this.scrollBar)
			{
				ImageIcon icon = new ImageIcon(PanelMainJoueur.this.ctrl.getLstCarteWagon().get(0).getRecto());
				icon.setImage(icon.getImage().getScaledInstance(this.getWidth() -5,this.getHeight()-10,Image.SCALE_DEFAULT));
				JLabel lblImage = new JLabel(icon);

				this.scrollBar.setMaximum(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon());
				this.lblInfoNumeroCarte.setText((e.getValue()+1)+ " / " + this.scrollBar.getMaximum());
				if(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().get(this.scrollBar.getValue()).getColor() != null)
					this.panelCoulCarteWagon.add(lblImage);
			
				this.repaint();
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
			this.lblInfoNumeroCarte = new JLabel("1 / " + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainObjectif().size(),JLabel.CENTER);
			this.carteObjectif = PanelMainJoueur.this.ctrl.getLstCarteObjectif().get(0);

			this.scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
			this.scrollBar.setUnitIncrement(1);
			this.scrollBar.setBlockIncrement(1);
			this.scrollBar.setBackground(Color.WHITE);

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
			this.carteObjectif = PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainObjectif().get(0);
			this.affichageCarte = new GenereImageCarteObjectif(this.carteObjectif,PanelMainJoueur.this.ctrl.getNomImage(),PanelMainJoueur.this.ctrl.getWidthPanel(),PanelMainJoueur.this.ctrl.getHeightPanel());
			this.repaint();
		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			
			if(e.getSource() == this.scrollBar)
			{
				
				this.scrollBar.setMaximum(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteObjectif());
				this.lblInfoNumeroCarte.setText((e.getValue()+1)+ " / " + this.scrollBar.getMaximum());
				this.carteObjectif = PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainObjectif().get(e.getValue());
				this.affichageCarte = new GenereImageCarteObjectif(this.carteObjectif,PanelMainJoueur.this.ctrl.getNomImage(),PanelMainJoueur.this.ctrl.getWidthPanel(),PanelMainJoueur.this.ctrl.getHeightPanel());
				this.repaint();
			}	
		}
	}
}
