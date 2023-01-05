package ihm.sectionMenu;

import main.Controleur;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JButton;
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

	private JButton btnImport;
	private JPanel panelInformation;
	private JLabel lblInformationMappe;

	public PanelHautMenu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */

		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		
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
				
						this.ctrl.getPanelCentreMenu().setEnabled(true);
					}
					} catch (IOException e1) {e1.printStackTrace();}
			}
		}
	}
}
