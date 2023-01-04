/**
 * @author Decharrois Adrien
 * @version 1.0
 * @date 2023-01-03
 */

package ihm.accueil;

import ihm.jeu.PanelPreparationJeu;
import main.Controleur;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PanelDispoBtn extends JPanel implements ActionListener 
{
    Controleur ctrl;

    private JButton btnJouer;
    private JButton btnImporterXML;
    private JButton btnQuitter;
    private PanelListeMappes panelListeMappes;
    private PanelPreparationJeu panelPreparationJeu;

    public PanelDispoBtn(Controleur ctrl)
    {
        /**
		 * Création des composants
		 */
        this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		JPanel panelPlacementBtn = new JPanel(new GridLayout(4,1,30,30)); 
		Dimension tailleEcran 	 = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (double) (tailleEcran.getWidth() / 8);
		int length = (int) (double) (tailleEcran.getHeight() / 2.5);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

		this.setBorder(BorderFactory.createEmptyBorder(length, width, width, length));
		this.setBackground(Color.WHITE);

		this.btnJouer = new JButton("Jouer");
		this.btnQuitter = new JButton("Quitter");
		this.btnImporterXML = new JButton("Importer XML");

		this.btnJouer.setBorder(border);
		this.btnQuitter.setBorder(border);
		this.btnImporterXML.setBorder(border);

		this.btnJouer.setBackground(Color.WHITE);
		this.btnQuitter.setBackground(Color.WHITE);
		this.btnImporterXML.setBackground(Color.WHITE);

		/**
		 * Placement des composants
		 */
		panelPlacementBtn.add(this.btnJouer);
		panelPlacementBtn.add(this.btnImporterXML);
		panelPlacementBtn.add(this.btnQuitter);

		panelPlacementBtn.setOpaque(false);

		this.add(panelPlacementBtn);

		/**
		 * Activation des composants
		 */
		this.btnJouer.addActionListener(this);
		this.btnImporterXML.addActionListener(this);
		this.btnQuitter.addActionListener(this);
    }


	/**
	 * Permet de créer une interaction homme-machine
	 * @param e
	 * @return void
	 */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource() == this.btnJouer)
        {
            this.panelPreparationJeu = new PanelPreparationJeu(ctrl);
            this.add( this.panelListeMappes );

			/**
			 * Vérifier si rien est sélectionné en faisant pop l'erreur cf this.ctrl.getPanelSelectionner() == null
			 */
        }
        else if( e.getSource() == this.btnImporterXML )
        {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers XML", "xml");
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int res = fileChooser.showOpenDialog(null);

			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);

			if(res == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				try {Files.copy(file.toPath(), Paths.get("importe/" + file.getName()));}
				catch (IOException e1) {e1.printStackTrace();}
       		}
		}
        else if(e.getSource() == this.btnQuitter)
       	{
			System.exit(0);
        }
    }
}

