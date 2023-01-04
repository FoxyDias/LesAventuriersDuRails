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
		this.setBackground(Color.WHITE);
		this.initRepertoireImporte();

		PanelXmlInfo[] tabPanelAffichageImporte = new PanelXmlInfo[this.repertoireImporte.length];
		JPanel panelAffichageImporte = new JPanel(new GridLayout(tabPanelAffichageImporte.length-1,1));

		for(int i = 0; i < tabPanelAffichageImporte.length; i++)
		{
			if(!this.repertoireImporte[i].equals("xml"))
			{
				tabPanelAffichageImporte[i] = new PanelXmlInfo(this.ctrl, this.repertoireImporte[i]);
				panelAffichageImporte.add(tabPanelAffichageImporte[i]);
			}
		}

		this.scrollPane = new JScrollPane(panelAffichageImporte, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.add(new JLabel("Liste des fichiers XML importées", JLabel.CENTER), BorderLayout.NORTH);
		this.add(this.scrollPane, BorderLayout.CENTER);
	}

	/**
	 * Permet de stocker le panelSelectionner, si un panelSelectionner est déjà présent, l'ancien panel se déselectionne et prend l'instance
	 * du nouveau panel en le sélectionnant
	 * @param panelXmlInfo
	 */
	public void panelSelectionner(PanelXmlInfo panelXmlInfo)
	{
		if(this.panelXmlInfo == null )
		{
			this.panelXmlInfo = panelXmlInfo;
		}
		else
		{
			this.panelXmlInfo.changerBordure(BorderFactory.createEmptyBorder());
			this.panelXmlInfo.inverserEtat();
			this.panelXmlInfo = panelXmlInfo;
		}
		this.panelXmlInfo.changerBordure(BorderFactory.createLineBorder(Color.RED, 2));
		this.panelXmlInfo.inverserEtat();
	}

	/**
	 * Mise à jour du panel important les XML
	 */

	public void majPanelImporte()
	{
		this.initRepertoireImporte();

		PanelXmlInfo[] tabPanelAffichageImporte = new PanelXmlInfo[this.repertoireImporte.length];
		JPanel panelAffichageImporte = new JPanel(new GridLayout(tabPanelAffichageImporte.length-1,1));

		for(int i = 0; i < tabPanelAffichageImporte.length; i++)
		{
			if(!this.repertoireImporte[i].equals("xml"))
			{
				tabPanelAffichageImporte[i] = new PanelXmlInfo(this.ctrl, this.repertoireImporte[i]);
				panelAffichageImporte.add(tabPanelAffichageImporte[i]);
			}
			this.add(panelAffichageImporte);
			this.ctrl.changerPanel("init");
		}
	}
}
