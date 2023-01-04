/**
 * @author Decharrois Adrien
 * @version 1.0
 * @date 2023-01-03
 */

package ihm.accueil;

import main.Controleur;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.*;

import javax.swing.*;

public class PanelListeMappes extends JPanel 
{

    private Controleur ctrl;
	private JScrollPane scrollPane;
	private String[] repertoireImporte;
	private PanelXmlInfo panelXmlInfo;

	/**
	 * Initialise le répertoire importerXML
	 */
	private void initRepertoireImporte()
	{
		try{Files.createDirectories(Paths.get("importerXML"));}
		catch(Exception e){e.printStackTrace();}

		this.repertoireImporte = new File(Paths.get("importerXML").toFile().getAbsolutePath()).list();
	}

    public PanelListeMappes(Controleur ctrl)
    {
		/**
		 * Création des composants
		 */
		 
        this.ctrl = ctrl;
		this.setLayout(new BorderLayout(0,10));
		this.initRepertoireImporte();

		this.setBackground(Color.WHITE);
    }

	/**
	 * 
	 */
	public void panelSelectionner(PanelXmlInfo panelXmlInfo)
	{
		if(this.panelXmlInfo == null )
		{
			this.panelXmlInfo = panelXmlInfo;
		}
		else
		{
			this.panelXmlInfo.changerBordure(BorderFactory.createLineBorder());
		}
	}
}
