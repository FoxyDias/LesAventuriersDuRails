package ihm.sectionMenu;

import main.Controleur;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
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

import java.awt.*;
import java.awt.event.*;


import java.util.ArrayList;


import java.awt.event.MouseEvent;
import javax.swing.filechooser.FileSystemView;


import metier.Arete;
import metier.Noeud;

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

		JLabel lblTitre 	= new JLabel("Les aventuriers du rail");

		JPanel panelTitre 	= new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));
		JPanel panelImport 	= new JPanel();
		
		this.panelInformation = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40)); 
		this.btnImport = new JButton("Importer un fichier XML");
		this.lblInformationMappe  = new JLabel("Aucune mappe n'est chargée. Importez en une pour commencer à jouer.");

		/**
		 * Positionnement des composants
		 */

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
		lblTitre.setFont(new Font("Serif", Font.ROMAN_BASELINE, 50));

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
						this.ctrl.lireXml("donnee/xml/"+file.getName());
						this.panelInformation.setLayout(new GridLayout(10, 0,0,5));

						

						this.lblInformationMappe.setText(lblVide.getText());
						this.panelInformation.add(new JLabel("Nombre de joueurs maximum  : " 						+ this.ctrl.getNbJoueurMax()			, JLabel.CENTER));
						this.panelInformation.add(new JLabel("Nombre de couleur / joueur : "      					+ this.ctrl.getLstCouleurJoueur().size(), JLabel.CENTER));
						this.panelInformation.add(new JLabel("Nombre de noeud dans la mappe : "						+ this.ctrl.getLstNoeud().size()		, JLabel.CENTER));
						this.panelInformation.add(new JLabel("Nombre d'arête dans la mappe : "  					+ this.ctrl.getLstArete().size()		, JLabel.CENTER));
						this.panelInformation.add(new JLabel("Nombre de joueurs minimum pour arête double  : " 		+ this.ctrl.getNbJoueurMinDoubleArete()	, JLabel.CENTER));
						this.panelInformation.add(new JLabel("Nombre de points pour le plus long chemin : " 		+ this.ctrl.getNbPointsPlusLongChemin()	, JLabel.CENTER));
						this.panelInformation.add(new JLabel("Nombre de carte(s) objectif  : " 						+ this.ctrl.getLstCarteObjectif().size(), JLabel.CENTER));
						this.panelInformation.add(new JLabel("Nombre de wagons restants pour arrêter la partie : " 	+ this.ctrl.getNbWagonFinPartie()		, JLabel.CENTER));
						this.panelInformation.add(new JLabel("Nombre de wagons / joueurs : " 						+ this.ctrl.getNbWagonDebutPartie()		, JLabel.CENTER));
				

						String nomImage = this.ctrl.getNomImage();
						this.FileImagenew =new File(nomImage);

						while(!FileImagenew.exists())
						{
							System.out.println("Sheeeeeesh1");
							try{
								if(!this.validFichier)
								{
									this.jdImporteImageManquante = new JDialog();
									this.btnImportImage = new JButton("Importé le fichier manquant");
									this.btnImportImage.addActionListener(this);
									this.jdImporteImageManquante.setTitle("Erreur");
									this.jdImporteImageManquante.setSize(300, 100);
									this.jdImporteImageManquante.setLocationRelativeTo(null);
									this.jdImporteImageManquante.setModal(true);
									this.jdImporteImageManquante.setResizable(false);
									this.jdImporteImageManquante.setLayout(new BorderLayout());
									this.jdImporteImageManquante.add(btnImportImage, BorderLayout.SOUTH);
									this.jdImporteImageManquante.add(new JLabel("L'image " + nomImage +  " n'existe pas, veuillez la placer dans le dossier image"), BorderLayout.CENTER);
									//FileNameExtensionFilter filtreImage = new FileNameExtensionFilter("Format XML", "png")
									this.jdImporteImageManquante.setVisible(true);
									this.validFichier = true;
									
								}
								else
								{
									this.jdImporteImageManquante.dispose();
									this.FileImagenew =new File(this.ctrl.getNomImage());
								}
								

							}


							catch(Exception e1){e1.printStackTrace();}
						}
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
				System.out.print("Sheeeesh2");
				File fileRemplacement = jFileChooserRemplacement.getSelectedFile();									
				try {
					Files.copy(fileRemplacement.toPath(), Paths.get("importe/"	+ fileRemplacement.getName()));
				} catch (IOException e2) { e2.printStackTrace();}																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																										
				this.cheminFichier = this.ctrl.getNomImage();

				this.validFichier = this.ctrl.getNomImage() == cheminFichier;
				this.jdImporteImageManquante.dispose();
				this.FileImagenew =new File(cheminFichier);
				//this.jdImporteImageManquante.dispose();
			}

		}

		

		
	}
}
