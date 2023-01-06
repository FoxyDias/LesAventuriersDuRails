package ihm.sectionMenu;

import main.Controleur;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;


public class PanelHautMenu extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JDialog jdImporteImageManquante;

	private JButton btnImport;
	private JButton btnImportImage;
	private JPanel panelInformation;
	private JLabel lblInformationMappe;

	private File FileImagenew;

	private String cheminFichier;

	private boolean validFichier;

	public PanelHautMenu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */

		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());


		this.validFichier = false;

		JLabel lblTitre 	= new JLabel(new ImageIcon("donnee/image/titre.png"));

		JPanel panelTitre 	= new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		JPanel panelImport 	= new JPanel();
		
		this.panelInformation = new JPanel(new FlowLayout(FlowLayout.CENTER,0,40));
		this.btnImport = new JButton("Importer un fichier XML");
		this.lblInformationMappe  = new JLabel("Aucune mappe n'est chargée. Importez en une pour commencer à jouer.");

		/**
		 * Positionnement des composants
		 */

		this.lblInformationMappe.setFont(new Font("", Font.BOLD, 13));
		panelTitre.add(lblTitre);
		panelImport.add(this.btnImport);
		panelInformation.add(lblInformationMappe);

		this.add(panelTitre, BorderLayout.NORTH);
		this.add(panelImport, BorderLayout.CENTER);
		this.add(panelInformation, BorderLayout.SOUTH);

		/**
	     * Activation des composants
		 */

		this.btnImport.addActionListener(this);
		this.btnImport.setBackground(Color.WHITE);

		new Thread() 
		{
			@Override
			public void run() {
				try {
					Thread.sleep(400);
					PanelHautMenu.this.ctrl.getPanelCentreMenu().setEnabled(false);

				} catch (Exception e) {}
			}
		}.start();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == this.btnImport)
		{
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("Format XML", "xml");
			JFileChooser jFileChooser = new JFileChooser(new File("donnee/xml"));
			
			jFileChooser.setAcceptAllFileFilterUsed(false);
			jFileChooser.setFileFilter(filtre);
			
			int res = jFileChooser.showOpenDialog(null);

			if(res == JFileChooser.APPROVE_OPTION)
			{
				try {

					File file = jFileChooser.getSelectedFile();
					Files.copy(file.toPath(), Paths.get("donnee/xml/"	+ file.getName()));
					
					if(file.exists())
					{
						JLabel lblVide = new JLabel("", JLabel.CENTER);
						JPanel panelImage = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 40));

						this.ctrl.lireXml("donnee/xml/"+file.getName());
						this.panelInformation.setLayout(new GridLayout(10,2,0,5));
						
			
						this.lblInformationMappe.setText(lblVide.getText());
						this.panelInformation.add(new JLabel());
						this.panelInformation.add(new JLabel("Nombre de joueurs maximum  : " 						, JLabel.RIGHT));
						this.panelInformation.add(new JLabel("" + this.ctrl.getNbJoueurMax()						, JLabel.LEFT));
						this.panelInformation.add(new JLabel("Nombre de couleur / joueur : "      					, JLabel.RIGHT));	
						this.panelInformation.add(new JLabel("" + this.ctrl.getLstCouleurJoueur().size()			, JLabel.LEFT));
						this.panelInformation.add(new JLabel("Nombre de noeud dans la mappe : "						, JLabel.RIGHT));
						this.panelInformation.add(new JLabel("" + this.ctrl.getLstNoeud().size()					, JLabel.LEFT));
						this.panelInformation.add(new JLabel("Nombre d'arête dans la mappe : " 				 		, JLabel.RIGHT));
						this.panelInformation.add(new JLabel("" + this.ctrl.getLstArete().size()					, JLabel.LEFT));
						this.panelInformation.add(new JLabel("Nombre de joueurs minimum pour arête double  : "		, JLabel.RIGHT));
						this.panelInformation.add(new JLabel("" + this.ctrl.getNbJoueurMinDoubleArete()				, JLabel.LEFT));
						this.panelInformation.add(new JLabel("Nombre de points pour le plus long chemin : "			, JLabel.RIGHT));
						this.panelInformation.add(new JLabel("" + this.ctrl.getNbPointsPlusLongChemin()				, JLabel.LEFT));
						this.panelInformation.add(new JLabel("Nombre de carte(s) objectif  : " 						, JLabel.RIGHT));
						this.panelInformation.add(new JLabel("" + this.ctrl.getLstCarteObjectif().size()			, JLabel.LEFT));
						this.panelInformation.add(new JLabel("Nombre de wagons restants pour arrêter la partie : "	, JLabel.RIGHT)); 	
						this.panelInformation.add(new JLabel("" + this.ctrl.getNbWagonDebutPartie()					, JLabel.LEFT));
						this.panelInformation.add(new JLabel("Nombre de wagons / joueurs : " 						, JLabel.RIGHT));
						this.panelInformation.add(new JLabel("" + this.ctrl.getNbWagonFinPartie()					, JLabel.LEFT));

						// Récuperer l'image
						panelImage.add(new JLabel(new ImageIcon(this.ctrl.getNomImage())));

						String nomImage = this.ctrl.getNomImage();
						this.FileImagenew =new File(nomImage);
						this.validFichier = this.FileImagenew.exists();
						if(!this.validFichier)
						{
							try
							{	
								this.jdImporteImageManquante = new JDialog();
								this.btnImportImage = new JButton("Importer l'image manquante");
								this.btnImportImage.addActionListener(this);
								this.jdImporteImageManquante.setTitle("Erreur");
								this.jdImporteImageManquante.setSize(300, 100);
								this.jdImporteImageManquante.setLocationRelativeTo(null);
								this.jdImporteImageManquante.setModal(true);
								// ne pas pouvoir fermer la fenetre
								this.jdImporteImageManquante.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
								this.jdImporteImageManquante.setResizable(false);
								this.jdImporteImageManquante.setLayout(new BorderLayout());
								this.jdImporteImageManquante.add(btnImportImage, BorderLayout.SOUTH);
								this.jdImporteImageManquante.add(new JLabel("L'image " + nomImage +  " n'existe pas, placer la dans le dossier image"), BorderLayout.CENTER);
								//FileNameExtensionFilter filtreImage = new FileNameExtensionFilter("Format XML", "png")
								this.jdImporteImageManquante.setVisible(true);
							}
							catch(Exception e1){e1.printStackTrace();}
						}
						this.FileImagenew =new File(this.ctrl.getNomImage());
						this.ctrl.getPanelCentreMenu().setEnabled(true);
					}
				} catch (IOException e1) {e1.printStackTrace();}
			}
		}

		if(e.getSource() == this.btnImportImage)
		{
			JFileChooser jFileChooserRemplacement = new JFileChooser(new File("donnee/xml"));
			int res2 = jFileChooserRemplacement.showOpenDialog(null);

			
			if(res2 == JFileChooser.APPROVE_OPTION)
			{
				File fileRemplacement = jFileChooserRemplacement.getSelectedFile();									
				try {
					Files.copy(fileRemplacement.toPath(), Paths.get("importe/"	+ fileRemplacement.getName()));
				} catch (IOException e2) { e2.printStackTrace();}																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																										
				this.cheminFichier = fileRemplacement.getName();
				this.validFichier = this.ctrl.getNomImage().equals("importe/"+cheminFichier);
				this.FileImagenew =new File(cheminFichier);

				if(this.validFichier)
					this.jdImporteImageManquante.dispose();
				//import/FortniteMappe.png
				//importe/FortniteMappe.png
				//this.jdImporteImageManquante.dispose();		
			}
		}		
	}
}
