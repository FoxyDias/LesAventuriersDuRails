package ihm.sectionJeu;

import main.Controleur;
import metier.*;
import java.awt.*;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class PanelCentreJeu extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
	private Controleur ctrl;

	public PanelCentreJeu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		/**
		 * Activation des composants
		 */

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public void paintComponent(java.awt.Graphics g)
	{
		super.paintComponent(g);

		Image img = null;

		try { img = ImageIO.read(new File(this.ctrl.getNomImage()));}
		catch (IOException e) { e.printStackTrace(); }

		g.drawImage(img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_AREA_AVERAGING), 0, 0, this);
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		// Dessiner le texte	
		g.setFont(new Font("default", Font.BOLD, 12));
		ArrayList<Arete> areteDoubleDessine = new ArrayList<Arete>();

		Dimension testTaille = this.getSize();

		double xMax = testTaille.getWidth() -26;
		double yMax = testTaille.getHeight() -26;

		double width=this.ctrl.getWidthPanel(); 
		double height=this.ctrl.getHeightPanel();

	
		
		double multiX = (xMax/width);
		double multiY = (yMax/height);
		// draw les arete
		for (Arete a : this.ctrl.getLstArete()) 
		{	
			int nb = a.getWagon();
			int fromSize = 20;
			int toSize = 20;
			String c = a.getCouleur();

			Noeud from = a.getNoeudDep();
			Noeud to = a.getNoeudArr();

			boolean aretePrise= a.getEstOccupe();
			
			Color coulJoueur =null;

			if(aretePrise)
			{
				coulJoueur = a.getOccupateur().getCouleur();
			}



			if (to.getX() != 0 && to.getY() != 0) {
				if (from.getX() != 0 && from.getY() != 0) {

					double fromX =(from.getX() + fromSize / 2) *multiX;
					double fromY =(from.getY() + fromSize / 2)*multiY;

					double toX = (to.getX() + toSize / 2)*multiX;
					double toY = (to.getY() + toSize / 2)*multiY;

					if(a.getEstDouble() && !areteDoubleDessine.contains(a))
					{
						areteDoubleDessine.add(a);
						areteDoubleDessine.add(a.getAreteDouble());

						Color c1 = null;
						if(a.getAreteDouble().getOccupateur() != null)
						{
							c1 = a.getAreteDouble().getOccupateur().getCouleur();
						}
						if((fromX > toX && toY < fromY) || (fromX < toX && toY > fromY) )
						{
							
							drawArete(fromX+5, fromY-5, toX+5, toY-5, nb, c, aretePrise , coulJoueur,g);//Haut gauche
							drawArete(fromX-5, fromY+5, toX-5, toY+5, a.getAreteDouble().getWagon(), a.getAreteDouble().getCouleur(),a.getAreteDouble().getEstOccupe() , c1, g);//Bas droit
						}
						else
						{
							drawArete(fromX+5, fromY+5, toX+5, toY+5, nb, c,aretePrise , coulJoueur, g);//Haut droit 
							
							drawArete(fromX-5, fromY-5, toX-5, toY-5, a.getAreteDouble().getWagon(), a.getAreteDouble().getCouleur(),a.getAreteDouble().getEstOccupe() , c1, g);//Bas gauche
						}
					}
					else 
						if(!areteDoubleDessine.contains(a))
							drawArete(fromX, fromY, toX, toY, nb, c,aretePrise , coulJoueur, g);
				}
			}
		}

		// draw les noeuds
		for ( Noeud n : this.ctrl.getLstNoeud() )
			if ( n.getX() != 0 && n.getY() != 0)
				drawNoeud( n, g);
	}

	private void drawNoeud(Noeud noeud, Graphics g)
	{
		int size = 26;

		Dimension testTaille = this.getSize();

		double xMax = testTaille.getWidth() -26;
		double yMax = testTaille.getHeight() -26;

		double width=this.ctrl.getWidthPanel(); 
		double height=this.ctrl.getHeightPanel();

		double multiX = (xMax/width);
		double multiY = (yMax/height);

		// draw la Noeud
		g.setColor(Color.BLACK);
		g.fillOval((int) (noeud.getX()*multiX), (int) (noeud.getY()*multiY), size, size);
		g.drawOval((int) (noeud.getX()*multiX), (int) (noeud.getY()*multiY), size, size);

		g.setColor(Color.WHITE);
		g.fillOval((int)(noeud.getX()*multiX)+size/4, (int)(noeud.getY()*multiY)+size/4, size/2, size/2);

		// draw l'ID de la noeud
		g.setColor(Color.BLACK);
		String str = String.valueOf(noeud.getNom());

		g.drawRect((int)(noeud.getNomX()*multiX) + size/2 - g.getFontMetrics().stringWidth(str)/2, (int)(noeud.getNomY()*multiY) - size, g.getFontMetrics().stringWidth(str), 20);
		g.setColor(Color.WHITE);
		g.fillRect((int)(noeud.getNomX()*multiX) + size/2 - g.getFontMetrics().stringWidth(str)/2, (int)(noeud.getNomY()*multiY) - size, g.getFontMetrics().stringWidth(str), 22);




		g.setColor(Color.BLACK);
		g.drawString(str, (int) (noeud.getNomX()*multiX) + size/2 - g.getFontMetrics().stringWidth(str)/2, (int)(noeud.getNomY()*multiY) -10);

	}

	private void drawArete(double fromX, double fromY, double toX, double toY, int nbWagon, String c , boolean aretePrise , Color couleurJoueur, Graphics g)
	{
	
		// draw la valeur de l'arete
		//from string : "java.awt.Color[r=0,g=0,b=0]" to : 0,0,0
		
		//now remove "r=" and "g=" and "b="
		String[] rgb;
		if(!c.equals("Neutre"))
		{
			rgb = c.substring(1, c.length()-1).split(",");
			rgb[0] = rgb[0].substring(2);
			rgb[1] = rgb[1].substring(2);
			rgb[2] = rgb[2].substring(2);
		}
		else
		{
			rgb = new String[3];
			rgb[0] = "195";
			rgb[1] = "195";
			rgb[2] = "195";
		}


		g.setColor(new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));

		//on vas dessiner les arêtes découpées en fonction du nombre de wagon 
		for(int n= 0 ; n<=nbWagon-1; n++)
		{
			((Graphics2D) g).setStroke(new BasicStroke(15));
			g.setColor(Color.BLACK);
			g.drawLine((int)(fromX + (toX-fromX)/nbWagon *n),
			(int)(fromY + (toY-fromY)/nbWagon *n),
			(int)(fromX + (toX-fromX)/nbWagon *(n+1)),
			(int)(fromY + (toY-fromY)/nbWagon *(n+1)));
				
			//Dessine les contours du trait en noir 
			((Graphics2D) g).setStroke(new BasicStroke(10));
			g.setColor(new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
			g.drawLine((int)(fromX + (toX-fromX)/nbWagon *n),
			(int)(fromY + (toY-fromY)/nbWagon *n),
			(int)(fromX + (toX-fromX)/nbWagon *(n+1)),
			(int)(fromY + (toY-fromY)/nbWagon *(n+1)));

			if(aretePrise)
			{
				//Dessine les contours du trait en noir 
				((Graphics2D) g).setStroke(new BasicStroke(6));
				g.setColor(Color.BLACK);
				g.drawLine((int)(fromX + (toX-fromX)/nbWagon *n),
				(int)(fromY + (toY-fromY)/nbWagon *n),
				(int)(fromX + (toX-fromX)/nbWagon *(n+1)),
				(int)(fromY + (toY-fromY)/nbWagon *(n+1)));


				((Graphics2D) g).setStroke(new BasicStroke(4));
				g.setColor(couleurJoueur);
				g.drawLine((int)(fromX + (toX-fromX)/nbWagon *n),
				(int)(fromY + (toY-fromY)/nbWagon *n),
				(int)(fromX + (toX-fromX)/nbWagon *(n+1)),
				(int)(fromY + (toY-fromY)/nbWagon *(n+1)));
			}
		}
		//g.drawLine(fromX, fromY, toX, toY);
		((Graphics2D) g).setStroke(new BasicStroke(1));
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.repaint();
	}


	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if(this.ctrl.getNbPiocheWagon()>=1)
		{
			JOptionPane.showMessageDialog(null, "Vous ne pouvez pas piocher des carte et prendre des voies en même temps. ");
			return;
		}
		int cliqueX = e.getX()-20;
		int cliqueY = e.getY()-8;

		ArrayList<Arete> lstAretee = new ArrayList<Arete>();
			
		for(Arete a : this.ctrl.getLstArete())
		{
			Dimension testTaille = this.getSize();

			double xMax = testTaille.getWidth() -26;
			double yMax = testTaille.getHeight() -26;

			double width=this.ctrl.getWidthPanel(); 
			double height=this.ctrl.getHeightPanel();

			double multiX = (xMax/width);
			double multiY = (yMax/height);


			// AB vecteur de l'arete 
			double aX = a.getNoeudArr().getX()* multiX;
			double aY = a.getNoeudArr().getY()* multiY;
			double bX = a.getNoeudDep().getX()* multiX;
			double bY = a.getNoeudDep().getY()* multiY;

			//AB vecteur de l'arete 
			double vecteurAbX = bX - aX;
			double vecteurAbY = bY - aY;

			// AC vecteur entre point A et le point cliqué
			double vecteurAcX  = cliqueX - aX;
			double vecteurAcY = cliqueY - aY;

			// produit scalaire entre AB et AC
			double produitScalaireACAB = vecteurAbX * vecteurAcX + vecteurAbY * vecteurAcY;

			// produit scalaire entre AB et AB
			double produitScalaireABAB = vecteurAbX * vecteurAbX + vecteurAbY * vecteurAbY;

			// distance entre le point cliqué et le point A

			double distance = produitScalaireACAB / produitScalaireABAB;

			double vecteurAhX = distance * vecteurAbX;
			double vecteurAhY = distance * vecteurAbY;

			double vecteurHcX = vecteurAcX - vecteurAhX;
			double vecteurHcY = vecteurAcY - vecteurAhY;

			double distanceHC = Math.sqrt(Math.pow(vecteurHcX,2) + Math.pow(vecteurHcY,2));

			if(0<= distance && distance <= 1 && distanceHC <= 8)
			{
				if(!a.getEstOccupe())
					lstAretee.add(a);
				else 
					JOptionPane.showMessageDialog(null, "Cette voie et déja occupé par un Joueur ");
				
			}
		}
		if(lstAretee.size() == 0){return;}

		if(lstAretee.size() == 1 )
		{
			
			Arete a = lstAretee.get(0);
			Joueur joueur = this.ctrl.getEstJoueurCourant();

			if(this.ctrl.priseVoie(joueur, a))
			{
				if(a.getWagon() > joueur.getNbWagons())
				{
					JOptionPane.showMessageDialog(null, "Il reste " + joueur.getNbWagons() + " wagons pour vous, fin du jeu au prochain tour");
					return;
				}

				if((this.ctrl.getNbJoueurPartie() < this.ctrl.getNbJoueurMinDoubleArete()) && 
				(a.getEstDouble() &&(a.getEstOccupe() || a.getAreteDouble().getEstOccupe())))
				{
					JOptionPane.showMessageDialog(null, "Impossible car il y a moins de " + this.ctrl.getNbJoueurMinDoubleArete() + " joueurs pour jouer avec les arêtes doubles.");
					return;
				}
				

				a.setEstOccupe(true);
				a.setOccupateur(joueur);
				joueur.ajouterArete(a);
				joueur.removeNbWagons(a.getWagon());
				this.repaint();
				this.ctrl.avancerJoueur();
			}
		}
		else {
			Arete aTmp = lstAretee.get(0);
			if((this.ctrl.getNbJoueurPartie() < this.ctrl.getNbJoueurMinDoubleArete()) && 
				(aTmp.getEstDouble() &&(aTmp.getEstOccupe() || aTmp.getAreteDouble().getEstOccupe())))
			{
				JOptionPane.showMessageDialog(null, "Impossible car il y a moins de " + this.ctrl.getNbJoueurMinDoubleArete() + " joueurs pour jouer avec les arêtes doubles.");
				return;
			}
			else
			{
				JDialog dialog = new JDialog();
				int cpt = 2;
				dialog.setTitle("Choix de l'arête parmis les chemins doubles");
				dialog.setBounds(800, 400, 350, 300);
				dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
				dialog.setResizable(false);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

				for(Arete a : lstAretee)
				{
					JButton btn = new JButton("Chemin numéro " + cpt + " : " + a.getNoeudArr().getNom() + " - " + a.getNoeudDep().getNom());
					cpt--;
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Joueur joueur = ctrl.getEstJoueurCourant();
							if(ctrl.priseVoie(joueur, a))
							{
								a.setEstOccupe(true);
								a.setOccupateur(joueur);
								joueur.ajouterArete(a);
								joueur.removeNbWagons(a.getWagon());
								ctrl.avancerJoueur();
							}
							dialog.dispose(); 
							PanelCentreJeu.this.repaint();
						}
					});
					dialog.add(btn);
				}
				dialog.setVisible(true);
			}
		}

		this.ctrl.getEstJoueurCourant().completeCarteObjectif();
	}
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
