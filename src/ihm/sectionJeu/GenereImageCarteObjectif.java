package ihm.sectionJeu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.BasicStroke;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import metier.CarteObjectif;

public class GenereImageCarteObjectif extends JPanel
{
	private CarteObjectif carteObjectif;
	private String pathImg;

	public GenereImageCarteObjectif(CarteObjectif carteObjectif,String pathImg)
	{
		this.carteObjectif = carteObjectif;
		this.pathImg = pathImg;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		try 	{g.drawImage(ImageIO.read(new File(this.pathImg)),0, 0, null);}
		catch 	(IOException e) {e.printStackTrace();}

		g.fillOval(this.carteObjectif.getNoeudArr().getNomX(), this.carteObjectif.getNoeudArr().getNomY(), 10, 10);
		g.fillOval(this.carteObjectif.getNoeudDep().getNomX(), this.carteObjectif.getNoeudDep().getNomY(), 10, 10);

		g2d.setStroke(new BasicStroke(2)); 
		g2d.drawLine(this.carteObjectif.getNoeudArr().getNomX(), this.carteObjectif.getNoeudArr().getNomY(),
					 this.carteObjectif.getNoeudDep().getNomX(), this.carteObjectif.getNoeudDep().getNomY() );
	}

}
