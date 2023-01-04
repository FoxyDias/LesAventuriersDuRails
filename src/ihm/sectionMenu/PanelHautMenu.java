package ihm.sectionMenu;

import main.Controleur;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

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
		lblTitre.setFont(new Font("Broadway", Font.BOLD, 50));
		JLabel lblInformationMappe  = new JLabel("A remplir");

		JPanel panelTitre 		= new JPanel();
		JPanel panelImport 		= new JPanel(new GridLayout(1,3,5,5));
		JPanel panelInformation = new JPanel(); 

		this.btnImport = new JButton("Importer un fichier XML");

		/**
		 * Positionnement des composants
		 */

		panelTitre.add(lblTitre, JLabel.CENTER);
	

		panelImport.add(new JLabel());
		panelImport.add(this.btnImport);
		panelImport.add(new JLabel());

		panelInformation.add(lblInformationMappe);

		this.add(panelTitre, BorderLayout.NORTH);
		this.add(panelImport, BorderLayout.CENTER);
		this.add(panelInformation, BorderLayout.SOUTH);

		/**
	     * Activation des composants
		 */

		this.btnImport.addActionListener(this);
		this.btnImport.setBackground(Color.WHITE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnImport)
			System.out.println("Import");
	}
}
