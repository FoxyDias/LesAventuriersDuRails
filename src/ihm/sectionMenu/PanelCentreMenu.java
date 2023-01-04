package ihm.sectionMenu;

import main.Controleur;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;


public class PanelCentreMenu extends JPanel implements ActionListener
{
	private JButton btnCreerPartieMulti;
	private JButton btnCreerPartieSolo;
	private JButton btnRejoindrePartie;
	private JButton btnCopierIP;
	private JButton btnQuitter;
	private JButton btnCreerPartiePopUp;
	private JButton btnRejoindreMultiPopUp;

	private JTextField txtNbJoueursMiniCreer;
	private JTextField txtNbJoueursMaxiCreer;
	private JTextField txtMotDePasseCreer;
	private JTextField txtPortMachineCreer;
	private JTextField txtPortMachineRejoindre; 
	private JTextField txtIPMachineRejoindre;
	private JTextField txtMotDePasseRejoindre;
	private JTextField txtNbJoueursLocal;

	private JDialog dialogCreerPartie;
	private JDialog dialogRejoindrePartie;

	public PanelCentreMenu(Controleur ctrl)
	{
		/**
		 * Création des boutons
		 */
		JLabel lblPartieMulti		= new JLabel("Partie multijoueur");
		JLabel lblPartieSolo		= new JLabel("Partie locale");
		JLabel lblNbJoueursLocal	= new JLabel("Nombre de joueurs : ");

		JLabel lblNbJoueursMini 		= new JLabel("Nombre de joueurs minimum : ");
		JLabel lblNbJoueursMaxi 		= new JLabel("Nombre de joueurs maximum : ");
		JLabel lblMotDePasse 			= new JLabel("Mot de passe pour la partie : ");
		JLabel lblPortMachine 			= new JLabel("Port de la machine : ");

		JLabel lblIPMachine 			= new JLabel("IP de la machine à rejoindre : ");
		JLabel lblPortMachineRejoindre	= new JLabel("Port de la machine : ");
		JLabel lblMotDePasseRejoindre 	= new JLabel("Mot de passe de la partie : ");

		JPanel panelCreerPartieSolo			= new JPanel(new BorderLayout(0,20));
		JPanel panelTitreCreerPartieSolo	= new JPanel();
		JPanel panelGrillePartieSolo 		= new JPanel(new GridLayout(3,3));


		JPanel panelCreerPartieMult 		= new JPanel(new BorderLayout(0,20));
		JPanel panelTitreCreerPartieMult	= new JPanel();
		JPanel panelGrillePartieMult		= new JPanel(new GridLayout(3,3));

		JPanel panelRelierPartieMult		= new JPanel(new BorderLayout(1,2));

		panelCreerPartieSolo.setBorder(BorderFactory.createLineBorder(Color.black));
		panelCreerPartieMult.setBorder(BorderFactory.createLineBorder(Color.black));


		this.btnCreerPartieMulti	= new JButton("Créer une partie");
		this.btnCreerPartieSolo		= new JButton("Créer une partie");
		this.btnRejoindrePartie		= new JButton("Rejoindre une partie");
		this.btnCopierIP			= new JButton("Copier mon IP");
		this.btnQuitter				= new JButton("Quitter");
		this.btnCreerPartiePopUp	= new JButton("Créer");
		this.btnRejoindreMultiPopUp	= new JButton("Rejoindre");

		this.txtNbJoueursMiniCreer		= new JTextField();
		this.txtNbJoueursMaxiCreer		= new JTextField();
		this.txtMotDePasseCreer			= new JTextField();
		this.txtPortMachineCreer		= new JTextField();
		this.txtPortMachineRejoindre	= new JTextField();
		this.txtIPMachineRejoindre		= new JTextField();
		this.txtMotDePasseRejoindre		= new JTextField();
		this.txtNbJoueursLocal			= new JTextField();


		/**
		 * Positionnement des composants
		 */

		panelTitreCreerPartieSolo.add(lblPartieSolo);

		panelGrillePartieSolo.add(lblNbJoueursLocal);
		panelGrillePartieSolo.add(new JLabel());
		panelGrillePartieSolo.add(this.txtNbJoueursLocal);

		panelGrillePartieSolo.add(new JLabel());
		panelGrillePartieSolo.add(new JLabel());
		panelGrillePartieSolo.add(new JLabel());

		panelGrillePartieSolo.add(new JLabel());
		panelGrillePartieSolo.add(this.btnCreerPartieSolo);
		panelGrillePartieSolo.add(new JLabel());

		panelCreerPartieSolo.add(panelTitreCreerPartieSolo, BorderLayout.NORTH);
		panelCreerPartieSolo.add(panelGrillePartieSolo, BorderLayout.CENTER);

		panelTitreCreerPartieMult.add(lblPartieMulti);
		
		panelTitreCreerPartieMult.add(new JLabel());
		panelTitreCreerPartieMult.add(new JLabel());
		panelTitreCreerPartieMult.add(new JLabel());

		panelGrillePartieMult.add(this.btnCreerPartieMulti);
		panelGrillePartieMult.add(new JLabel());
		panelGrillePartieMult.add(this.btnRejoindrePartie);

		panelTitreCreerPartieMult.add(new JLabel());
		panelTitreCreerPartieMult.add(new JLabel());
		panelTitreCreerPartieMult.add(new JLabel());

		panelRelierPartieMult.add(panelCreerPartieSolo);
		panelRelierPartieMult.add(panelCreerPartieMult);

		this.add(panelRelierPartieMult);


		/**
		 * Activation des composants
		 */

		this.btnCreerPartieMulti.addActionListener(this);
		this.btnCreerPartieSolo.addActionListener(this);
		this.btnRejoindrePartie.addActionListener(this);
		this.btnCopierIP.addActionListener(this);
		this.btnQuitter.addActionListener(this);
		this.btnCreerPartiePopUp.addActionListener(this);
		this.btnRejoindreMultiPopUp.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{

	}
}
