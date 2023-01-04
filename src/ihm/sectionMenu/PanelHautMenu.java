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

public class PanelHautMenu extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton btnImport;

	public PanelHautMenu(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */

		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		
		JLabel lblTitre 			= new JLabel("Les aventuriers du rail");
		JLabel lblInformationMappe  = new JLabel("Aucune mappe n'est chargée. [Résumé des informations de la mappe]");

		JPanel panelTitre 		= new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));
		JPanel panelImport 		= new JPanel();
		JPanel panelInformation = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40)); 

		this.btnImport = new JButton("Importer un fichier XML");

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
		lblTitre.setFont(new Font("Broadway", Font.BOLD, 50));
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
				File file = jFileChooser.getSelectedFile();
				try {
					Files.copy(file.toPath(), Paths.get("donnee/xml/"+file.getName()));
					} catch (IOException e1) {e1.printStackTrace();}

				//this.ctrl.lireXml("donnee/xml/"+file.getName());	
			}
		}
	}
}
