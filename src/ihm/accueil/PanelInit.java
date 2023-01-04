package ihm.accueil;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import main.Controleur;


public class PanelInit extends JPanel
{
	Controleur ctrl;
	private PanelDispoBtn panelDispoBtn;
	private PanelListeMappes panelListeMappes;

	public PanelInit(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.panelDispoBtn = new PanelDispoBtn(ctrl);
		this.panelListeMappes = new PanelListeMappes(ctrl);

		/**
		 * Positionnement des composants
		 */
		this.add(this.panelDispoBtn, BorderLayout.CENTER);
		this.add(this.panelListeMappes, BorderLayout.SOUTH);
	}

	/** 
	 * Permet de selectionner le panel
	 * @param panelXmlInfo
	 */
	 public void panelSelectionner(PanelXmlInfo panelXmlInfo) { this.panelListeMappes.panelSelectionner(panelXmlInfo);}

	 /**
	  * Mise à jour de l'XML importé
	  */
	//public void majPanelImporter() {this.panelListeMappes.majPanelImporter();}

}