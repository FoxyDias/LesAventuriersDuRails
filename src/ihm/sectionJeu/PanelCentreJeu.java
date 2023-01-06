package ihm.sectionJeu;

import main.Controleur;
import metier.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class PanelCentreJeu extends JPanel
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
		 * Positionnement des composants
		 */

		/**
		 * Activation des composants
		 */
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
		for (Arete a : this.ctrl.getLstArete()) {
			
			int nb = a.getWagon();
			int fromSize = 20;
			int toSize = 20;
			String c = a.getCouleur();

			Noeud from = a.getNoeudDep();
			Noeud to = a.getNoeudArr();

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

						if((fromX > toX && toY < fromY) || (fromX < toX && toY > fromY) )
						{
							//Haut gauche
							//Bas droit
							drawArete(fromX+5, fromY-5, toX+5, toY-5, nb, c, g);
							drawArete(fromX-5, fromY+5, toX-5, toY+5, a.getAreteDouble().getWagon(), a.getAreteDouble().getCouleur(), g);
						}
						else
						{
							//Haut droit 
							//Bas gauche	
							drawArete(fromX+5, fromY+5, toX+5, toY+5, nb, c, g);
							drawArete(fromX-5, fromY-5, toX-5, toY-5, a.getAreteDouble().getWagon(), a.getAreteDouble().getCouleur(), g);
						}
					}
					else
					{
						if(!areteDoubleDessine.contains(a))
							drawArete(fromX, fromY, toX, toY, nb, c, g);
					}
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


		/*
		double multiX = 1;
		double multiY = 1;
		*/
		
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

	private void drawArete(double fromX, double fromY, double toX, double toY, int nbWagon, String c , Graphics g)
	{
	
		// draw la valeur de l'arete
		//System.out.print(c);
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
		}
		//g.drawLine(fromX, fromY, toX, toY);
		((Graphics2D) g).setStroke(new BasicStroke(1));
	}
}
