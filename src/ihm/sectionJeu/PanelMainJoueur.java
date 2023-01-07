package ihm.sectionJeu;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import main.Controleur;
import metier.Joueur;

public class PanelMainJoueur extends JPanel 
{
	private Controleur ctrl;


	public PanelMainJoueur(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(2,1));
		

		this.add(new PanelDispoCarteWagon(this.ctrl.getEstJoueurCourant()));

		
	}

	public class PanelDispoCarteWagon extends JPanel implements AdjustmentListener
	{
		private JLabel lblInfoNumeroCarte;
		private JPanel panelCoulCarteWagon;
		private JScrollBar scrollBar;

		public PanelDispoCarteWagon(Joueur joueurActif)
		{
			this.setLayout(new BorderLayout());
			this.lblInfoNumeroCarte = new JLabel("0/" + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon(),JLabel.CENTER);
			this.panelCoulCarteWagon = new JPanel();

			this.scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
			this.initValeur();

			this.add(this.lblInfoNumeroCarte,BorderLayout.NORTH);
			this.add(this.panelCoulCarteWagon,BorderLayout.CENTER);
			this.add(this.scrollBar,BorderLayout.SOUTH);			

			this.scrollBar.addAdjustmentListener(this);
		}

		public void initValeur()
		{
			this.scrollBar.setMinimum(0);
			this.scrollBar.setMaximum(1);
			this.lblInfoNumeroCarte.setText("1/"+this.scrollBar.getMaximum());
			this.panelCoulCarteWagon.setBackground(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().get(0).getColor());

		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			
			if(e.getSource() == this.scrollBar)
			{
				this.scrollBar.setMaximum(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteWagon());
				this.lblInfoNumeroCarte.setText((e.getValue()+1)+ "/" + this.scrollBar.getMaximum());
				if(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().get(this.scrollBar.getValue()).getColor() != null)
					this.panelCoulCarteWagon.setBackground(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().get(scrollBar.getValue()).getColor());
			}
			
		}
	}

	public class PanelDispoCarteObjectif extends JPanel
	{
		public PanelDispoCarteObjectif(Joueur joueurActif)
		{

		}
	}

	
}
