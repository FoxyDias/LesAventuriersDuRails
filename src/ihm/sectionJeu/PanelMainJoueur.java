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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import main.Controleur;
import metier.CarteObjectif;
import metier.CarteWagon;

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
			if(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().size() > 1)
			{
				this.scrollBar.setMinimum(0);
				this.scrollBar.setMaximum(1);
				this.lblInfoNumeroCarte.setText("1 / " + PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().size());
				ImageIcon icon = new ImageIcon(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon().get(this.scrollBar.getValue()).getRecto());
				icon.setImage(icon.getImage().getScaledInstance(this.panelCoulCarteWagon.getWidth() -5,this.panelCoulCarteWagon.getHeight()-10,Image.SCALE_DEFAULT));
				this.lblCoulCarteWagon.setIcon(icon);
			}
			else
			{
				this.scrollBar.setEnabled(false);
				this.lblInfoNumeroCarte.setText("0/0");
				this.lblCoulCarteWagon.setText("Vous posez aucun carte wagon");
			}
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
		private JLabel lblInfoObjectif;
		private CarteObjectif carteObjectif;
		private GenereImageCarteObjectif affichageCarte;
		private JScrollBar scrollBar;

		public PanelDispoCarteObjectif()
		{
			this.setLayout(new BorderLayout());
			this.lblInfoNumeroCarte = new JLabel("", JLabel.CENTER);
			this.lblInfoObjectif = new JLabel("",JLabel.CENTER);

			this.scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
			this.scrollBar.setUnitIncrement(1);
			this.scrollBar.setBlockIncrement(1);
			this.scrollBar.setBackground(Color.WHITE);
			this.scrollBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			this.initValeurCarteObjectif();

			JPanel panelDispoInfo = new JPanel(new GridLayout(2,1));

			panelDispoInfo.add(this.lblInfoNumeroCarte);
			panelDispoInfo.add(this.lblInfoObjectif);
		
			this.add(panelDispoInfo,BorderLayout.NORTH);
			this.add(this.affichageCarte,BorderLayout.CENTER);
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
			
			this.lblInfoObjectif.setText("Objectif : " + this.carteObjectif.getNoeudDep().getNom() + " à " + this.carteObjectif.getNoeudArr().getNom());

			if(this.carteObjectif.isAccomplie()) this.lblInfoObjectif.setForeground(Color.GREEN);
			else this.lblInfoObjectif.setForeground(Color.RED);
	
		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			
			if(e.getSource() == this.scrollBar)
			{
				
				this.scrollBar.setMaximum(PanelMainJoueur.this.ctrl.getEstJoueurCourant().getNbCarteObjectif());
				this.lblInfoNumeroCarte.setText((e.getValue()+1)+ " / " + this.scrollBar.getMaximum());
				this.carteObjectif = PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainObjectif().get(e.getValue());
				this.affichageCarte.setCarteObjectif(this.carteObjectif);

				this.lblInfoObjectif.setText("Objectif : " + this.carteObjectif.getNoeudDep().getNom() + " à " + this.carteObjectif.getNoeudArr().getNom());

				if(this.carteObjectif.isAccomplie()) this.lblInfoObjectif.setForeground(Color.GREEN);
				else this.lblInfoObjectif.setForeground(Color.RED);
			}	
		}
	}

	public class PanelInformationCarte extends JPanel 
	{
		public ArrayList<JLabel> listInformation;

		public PanelInformationCarte()
		{
			this.listInformation = new ArrayList<JLabel>();
			
			this.initInformation();

			this.setLayout(new GridLayout(this.listInformation.size(),1));

			for(JLabel lblInfoCarte : this.listInformation)
				this.add(lblInfoCarte);
		
		}

		private void initInformation()
		{
			HashMap<Color,ArrayList<CarteWagon>> tmpCouleur = new HashMap<Color,ArrayList<CarteWagon>>();


			for(CarteWagon wagon : PanelMainJoueur.this.ctrl.getEstJoueurCourant().getMainWagon())
			{
				if(!tmpCouleur.containsKey(wagon.getColor()))
					tmpCouleur.put(wagon.getColor(), new ArrayList<CarteWagon>());

				tmpCouleur.get(wagon.getColor()).add(wagon);
			}

			for(Color c : tmpCouleur.keySet())
				this.add(this.creerLabelInfo(c, tmpCouleur.get(c).size()));

		}

		private JLabel creerLabelInfo(Color c, Integer nbCarte)
		{
			return new JLabel("Wagon de couleur " + new ColorUtils().getColorNameFromColor(c) + " : " + nbCarte,JLabel.CENTER);
		}

		public class ColorUtils {

			/**
			 * Initialize the color list that we have.
			 */
			private ArrayList<ColorName> initColorList() {
				ArrayList<ColorName> colorList = new ArrayList<ColorName>();
				colorList.add(new ColorName("AliceBlue", 0xF0, 0xF8, 0xFF));
				colorList.add(new ColorName("AntiqueWhite", 0xFA, 0xEB, 0xD7));
				colorList.add(new ColorName("Aqua", 0x00, 0xFF, 0xFF));
				colorList.add(new ColorName("Aquamarine", 0x7F, 0xFF, 0xD4));
				colorList.add(new ColorName("Azure", 0xF0, 0xFF, 0xFF));
				colorList.add(new ColorName("Beige", 0xF5, 0xF5, 0xDC));
				colorList.add(new ColorName("Bisque", 0xFF, 0xE4, 0xC4));
				colorList.add(new ColorName("Black", 0x00, 0x00, 0x00));
				colorList.add(new ColorName("BlanchedAlmond", 0xFF, 0xEB, 0xCD));
				colorList.add(new ColorName("Blue", 0x00, 0x00, 0xFF));
				colorList.add(new ColorName("BlueViolet", 0x8A, 0x2B, 0xE2));
				colorList.add(new ColorName("Brown", 0xA5, 0x2A, 0x2A));
				colorList.add(new ColorName("BurlyWood", 0xDE, 0xB8, 0x87));
				colorList.add(new ColorName("CadetBlue", 0x5F, 0x9E, 0xA0));
				colorList.add(new ColorName("Chartreuse", 0x7F, 0xFF, 0x00));
				colorList.add(new ColorName("Chocolate", 0xD2, 0x69, 0x1E));
				colorList.add(new ColorName("Coral", 0xFF, 0x7F, 0x50));
				colorList.add(new ColorName("CornflowerBlue", 0x64, 0x95, 0xED));
				colorList.add(new ColorName("Cornsilk", 0xFF, 0xF8, 0xDC));
				colorList.add(new ColorName("Crimson", 0xDC, 0x14, 0x3C));
				colorList.add(new ColorName("Cyan", 0x00, 0xFF, 0xFF));
				colorList.add(new ColorName("DarkBlue", 0x00, 0x00, 0x8B));
				colorList.add(new ColorName("DarkCyan", 0x00, 0x8B, 0x8B));
				colorList.add(new ColorName("DarkGoldenRod", 0xB8, 0x86, 0x0B));
				colorList.add(new ColorName("DarkGray", 0xA9, 0xA9, 0xA9));
				colorList.add(new ColorName("DarkGreen", 0x00, 0x64, 0x00));
				colorList.add(new ColorName("DarkKhaki", 0xBD, 0xB7, 0x6B));
				colorList.add(new ColorName("DarkMagenta", 0x8B, 0x00, 0x8B));
				colorList.add(new ColorName("DarkOliveGreen", 0x55, 0x6B, 0x2F));
				colorList.add(new ColorName("DarkOrange", 0xFF, 0x8C, 0x00));
				colorList.add(new ColorName("DarkOrchid", 0x99, 0x32, 0xCC));
				colorList.add(new ColorName("DarkRed", 0x8B, 0x00, 0x00));
				colorList.add(new ColorName("DarkSalmon", 0xE9, 0x96, 0x7A));
				colorList.add(new ColorName("DarkSeaGreen", 0x8F, 0xBC, 0x8F));
				colorList.add(new ColorName("DarkSlateBlue", 0x48, 0x3D, 0x8B));
				colorList.add(new ColorName("DarkSlateGray", 0x2F, 0x4F, 0x4F));
				colorList.add(new ColorName("DarkTurquoise", 0x00, 0xCE, 0xD1));
				colorList.add(new ColorName("DarkViolet", 0x94, 0x00, 0xD3));
				colorList.add(new ColorName("DeepPink", 0xFF, 0x14, 0x93));
				colorList.add(new ColorName("DeepSkyBlue", 0x00, 0xBF, 0xFF));
				colorList.add(new ColorName("DimGray", 0x69, 0x69, 0x69));
				colorList.add(new ColorName("DodgerBlue", 0x1E, 0x90, 0xFF));
				colorList.add(new ColorName("FireBrick", 0xB2, 0x22, 0x22));
				colorList.add(new ColorName("FloralWhite", 0xFF, 0xFA, 0xF0));
				colorList.add(new ColorName("ForestGreen", 0x22, 0x8B, 0x22));
				colorList.add(new ColorName("Fuchsia", 0xFF, 0x00, 0xFF));
				colorList.add(new ColorName("Gainsboro", 0xDC, 0xDC, 0xDC));
				colorList.add(new ColorName("GhostWhite", 0xF8, 0xF8, 0xFF));
				colorList.add(new ColorName("Gold", 0xFF, 0xD7, 0x00));
				colorList.add(new ColorName("GoldenRod", 0xDA, 0xA5, 0x20));
				colorList.add(new ColorName("Gray", 0x80, 0x80, 0x80));
				colorList.add(new ColorName("Green", 0x00, 0x80, 0x00));
				colorList.add(new ColorName("GreenYellow", 0xAD, 0xFF, 0x2F));
				colorList.add(new ColorName("HoneyDew", 0xF0, 0xFF, 0xF0));
				colorList.add(new ColorName("HotPink", 0xFF, 0x69, 0xB4));
				colorList.add(new ColorName("IndianRed", 0xCD, 0x5C, 0x5C));
				colorList.add(new ColorName("Indigo", 0x4B, 0x00, 0x82));
				colorList.add(new ColorName("Ivory", 0xFF, 0xFF, 0xF0));
				colorList.add(new ColorName("Khaki", 0xF0, 0xE6, 0x8C));
				colorList.add(new ColorName("Lavender", 0xE6, 0xE6, 0xFA));
				colorList.add(new ColorName("LavenderBlush", 0xFF, 0xF0, 0xF5));
				colorList.add(new ColorName("LawnGreen", 0x7C, 0xFC, 0x00));
				colorList.add(new ColorName("LemonChiffon", 0xFF, 0xFA, 0xCD));
				colorList.add(new ColorName("LightBlue", 0xAD, 0xD8, 0xE6));
				colorList.add(new ColorName("LightCoral", 0xF0, 0x80, 0x80));
				colorList.add(new ColorName("LightCyan", 0xE0, 0xFF, 0xFF));
				colorList.add(new ColorName("LightGoldenRodYellow", 0xFA, 0xFA, 0xD2));
				colorList.add(new ColorName("LightGray", 0xD3, 0xD3, 0xD3));
				colorList.add(new ColorName("LightGreen", 0x90, 0xEE, 0x90));
				colorList.add(new ColorName("LightPink", 0xFF, 0xB6, 0xC1));
				colorList.add(new ColorName("LightSalmon", 0xFF, 0xA0, 0x7A));
				colorList.add(new ColorName("LightSeaGreen", 0x20, 0xB2, 0xAA));
				colorList.add(new ColorName("LightSkyBlue", 0x87, 0xCE, 0xFA));
				colorList.add(new ColorName("LightSlateGray", 0x77, 0x88, 0x99));
				colorList.add(new ColorName("LightSteelBlue", 0xB0, 0xC4, 0xDE));
				colorList.add(new ColorName("LightYellow", 0xFF, 0xFF, 0xE0));
				colorList.add(new ColorName("Lime", 0x00, 0xFF, 0x00));
				colorList.add(new ColorName("LimeGreen", 0x32, 0xCD, 0x32));
				colorList.add(new ColorName("Linen", 0xFA, 0xF0, 0xE6));
				colorList.add(new ColorName("Magenta", 0xFF, 0x00, 0xFF));
				colorList.add(new ColorName("Maroon", 0x80, 0x00, 0x00));
				colorList.add(new ColorName("MediumAquaMarine", 0x66, 0xCD, 0xAA));
				colorList.add(new ColorName("MediumBlue", 0x00, 0x00, 0xCD));
				colorList.add(new ColorName("MediumOrchid", 0xBA, 0x55, 0xD3));
				colorList.add(new ColorName("MediumPurple", 0x93, 0x70, 0xDB));
				colorList.add(new ColorName("MediumSeaGreen", 0x3C, 0xB3, 0x71));
				colorList.add(new ColorName("MediumSlateBlue", 0x7B, 0x68, 0xEE));
				colorList.add(new ColorName("MediumSpringGreen", 0x00, 0xFA, 0x9A));
				colorList.add(new ColorName("MediumTurquoise", 0x48, 0xD1, 0xCC));
				colorList.add(new ColorName("MediumVioletRed", 0xC7, 0x15, 0x85));
				colorList.add(new ColorName("MidnightBlue", 0x19, 0x19, 0x70));
				colorList.add(new ColorName("MintCream", 0xF5, 0xFF, 0xFA));
				colorList.add(new ColorName("MistyRose", 0xFF, 0xE4, 0xE1));
				colorList.add(new ColorName("Moccasin", 0xFF, 0xE4, 0xB5));
				colorList.add(new ColorName("NavajoWhite", 0xFF, 0xDE, 0xAD));
				colorList.add(new ColorName("Navy", 0x00, 0x00, 0x80));
				colorList.add(new ColorName("OldLace", 0xFD, 0xF5, 0xE6));
				colorList.add(new ColorName("Olive", 0x80, 0x80, 0x00));
				colorList.add(new ColorName("OliveDrab", 0x6B, 0x8E, 0x23));
				colorList.add(new ColorName("Orange", 0xFF, 0xA5, 0x00));
				colorList.add(new ColorName("OrangeRed", 0xFF, 0x45, 0x00));
				colorList.add(new ColorName("Orchid", 0xDA, 0x70, 0xD6));
				colorList.add(new ColorName("PaleGoldenRod", 0xEE, 0xE8, 0xAA));
				colorList.add(new ColorName("PaleGreen", 0x98, 0xFB, 0x98));
				colorList.add(new ColorName("PaleTurquoise", 0xAF, 0xEE, 0xEE));
				colorList.add(new ColorName("PaleVioletRed", 0xDB, 0x70, 0x93));
				colorList.add(new ColorName("PapayaWhip", 0xFF, 0xEF, 0xD5));
				colorList.add(new ColorName("PeachPuff", 0xFF, 0xDA, 0xB9));
				colorList.add(new ColorName("Peru", 0xCD, 0x85, 0x3F));
				colorList.add(new ColorName("Pink", 0xFF, 0xC0, 0xCB));
				colorList.add(new ColorName("Plum", 0xDD, 0xA0, 0xDD));
				colorList.add(new ColorName("PowderBlue", 0xB0, 0xE0, 0xE6));
				colorList.add(new ColorName("Purple", 0x80, 0x00, 0x80));
				colorList.add(new ColorName("Red", 0xFF, 0x00, 0x00));
				colorList.add(new ColorName("RosyBrown", 0xBC, 0x8F, 0x8F));
				colorList.add(new ColorName("RoyalBlue", 0x41, 0x69, 0xE1));
				colorList.add(new ColorName("SaddleBrown", 0x8B, 0x45, 0x13));
				colorList.add(new ColorName("Salmon", 0xFA, 0x80, 0x72));
				colorList.add(new ColorName("SandyBrown", 0xF4, 0xA4, 0x60));
				colorList.add(new ColorName("SeaGreen", 0x2E, 0x8B, 0x57));
				colorList.add(new ColorName("SeaShell", 0xFF, 0xF5, 0xEE));
				colorList.add(new ColorName("Sienna", 0xA0, 0x52, 0x2D));
				colorList.add(new ColorName("Silver", 0xC0, 0xC0, 0xC0));
				colorList.add(new ColorName("SkyBlue", 0x87, 0xCE, 0xEB));
				colorList.add(new ColorName("SlateBlue", 0x6A, 0x5A, 0xCD));
				colorList.add(new ColorName("SlateGray", 0x70, 0x80, 0x90));
				colorList.add(new ColorName("Snow", 0xFF, 0xFA, 0xFA));
				colorList.add(new ColorName("SpringGreen", 0x00, 0xFF, 0x7F));
				colorList.add(new ColorName("SteelBlue", 0x46, 0x82, 0xB4));
				colorList.add(new ColorName("Tan", 0xD2, 0xB4, 0x8C));
				colorList.add(new ColorName("Teal", 0x00, 0x80, 0x80));
				colorList.add(new ColorName("Thistle", 0xD8, 0xBF, 0xD8));
				colorList.add(new ColorName("Tomato", 0xFF, 0x63, 0x47));
				colorList.add(new ColorName("Turquoise", 0x40, 0xE0, 0xD0));
				colorList.add(new ColorName("Violet", 0xEE, 0x82, 0xEE));
				colorList.add(new ColorName("Wheat", 0xF5, 0xDE, 0xB3));
				colorList.add(new ColorName("White", 0xFF, 0xFF, 0xFF));
				colorList.add(new ColorName("WhiteSmoke", 0xF5, 0xF5, 0xF5));
				colorList.add(new ColorName("Yellow", 0xFF, 0xFF, 0x00));
				colorList.add(new ColorName("YellowGreen", 0x9A, 0xCD, 0x32));
				return colorList;
			}
		
			/**
			 * Get the closest color name from our list
			 * 
			 * @param r
			 * @param g
			 * @param b
			 * @return
			 */
			public String getColorNameFromRgb(int r, int g, int b) {
				ArrayList<ColorName> colorList = initColorList();
				ColorName closestMatch = null;
				int minMSE = Integer.MAX_VALUE;
				int mse;
				for (ColorName c : colorList) {
					mse = c.computeMSE(r, g, b);
					if (mse < minMSE) {
						minMSE = mse;
						closestMatch = c;
					}
				}
		
				if (closestMatch != null) {
					return closestMatch.getName();
				} else {
					return "No matched color name.";
				}
			}
		
			/**
			 * Convert hexColor to rgb, then call getColorNameFromRgb(r, g, b)
			 * 
			 * @param hexColor
			 * @return
			 */
			public String getColorNameFromHex(int hexColor) {
				int r = (hexColor & 0xFF0000) >> 16;
				int g = (hexColor & 0xFF00) >> 8;
				int b = (hexColor & 0xFF);
				return getColorNameFromRgb(r, g, b);
			}
		
			public String getColorNameFromColor(Color color) {
				if(color == null)
					return "Joker";

				return getColorNameFromRgb(color.getRed(), color.getGreen(),
						color.getBlue());
			}
		
			/**
			 * SubClass of ColorUtils. In order to lookup color name
			 * 
			 * @author Xiaoxiao Li
			 * 
			 */
			public class ColorName {
				public int r, g, b;
				public String name;
		
				public ColorName(String name, int r, int g, int b) {
					this.r = r;
					this.g = g;
					this.b = b;
					this.name = name;
				}
		
				public int computeMSE(int pixR, int pixG, int pixB) {
					return (int) (((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b)
							* (pixB - b)) / 3);
				}
		
				public int getR() {return r;}
		
				public int getG() {return g;}
		
				public int getB() {return b;}
		
				public String getName() {return name;}
			}
		}
	}
}
