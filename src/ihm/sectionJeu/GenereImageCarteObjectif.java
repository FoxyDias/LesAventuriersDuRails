package ihm.sectionJeu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.imageio.ImageIO;

import javax.swing.JPanel;


import metier.CarteObjectif;
import metier.Noeud;

public class GenereImageCarteObjectif extends JPanel
{
	private CarteObjectif carteObjectif;
	private String pathImg;
	private double width;
	private double height;

	public GenereImageCarteObjectif(CarteObjectif carteObjectif,String pathImg,double width, double height)
	{
		this.carteObjectif = carteObjectif;
		this.pathImg = pathImg;

		this.width = width;
		this.height = height;
	}

	public void setCarteObjectif(CarteObjectif carteObjectif) {
		this.carteObjectif = carteObjectif;
		this.repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		try 	{g.drawImage(ImageIO.read(new File(this.pathImg)).getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT),0, 0, null);}
		catch 	(IOException e) {e.printStackTrace();}

		this.drawNoeud(this.carteObjectif.getNoeudArr(), g);
		this.drawNoeud(this.carteObjectif.getNoeudDep(), g);

		g2d.setStroke(new BasicStroke(10));
		g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.DARK_GRAY);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(this.getWidth() - 55, this.getHeight()-55, 50, 50);

		g2d.setColor(Color.BLACK);
		g2d.drawRect(this.getWidth() - 55, this.getHeight()-55, 50, 50);
		g2d.setFont(new Font("Courier", Font.BOLD, 40));
		g2d.drawString(this.carteObjectif.getNbPoints() +"", this.getWidth() - 50, this.getHeight()-15);
	}

	private void drawNoeud(Noeud noeud, Graphics g)
	{
		int size = 26;

		Dimension testTaille = this.getSize();

		double xMax = testTaille.getWidth() -26;
		double yMax = testTaille.getHeight() -26;

		double width= this.width; 
		double height=this.height;

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

}
