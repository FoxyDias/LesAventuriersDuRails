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
	private JButton btnLancerPartiePopUp;
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
		JLabel lblPartieMulti		= new JLabel("Jouer en multijoueur");
		JLabel lblPartieSolo		= new JLabel("Jouer en local");
		JLabel lblNbJoueursLocal	= new JLabel("Nombre de joueurs : ");

		JLabel lblIPMachine 			= new JLabel("IP de la machine à rejoindre : ");
		JLabel lblPortMachineRejoindre	= new JLabel("Port de la machine : ");
		JLabel lblMotDePasseRejoindre 	= new JLabel("Mot de passe de la partie : ");

		JPanel panelCreerPartieSolo			= new JPanel(new BorderLayout());
		JPanel panelTitreCreerPartieSolo	= new JPanel();
		JPanel panelGrillePartieSolo 		= new JPanel(new GridLayout(3,3,20,20));	


		JPanel panelCreerPartieMult 		= new JPanel(new BorderLayout());
		JPanel panelTitreCreerPartieMult	= new JPanel();
		JPanel panelGrillePartieMult		= new JPanel(new GridLayout(3,3,20,20));

		JPanel panelRelierPartieMult		= new JPanel(new GridLayout(1,2,40,0));

		panelCreerPartieSolo.setBorder(BorderFactory.createLineBorder(Color.black));
		panelCreerPartieMult.setBorder(BorderFactory.createLineBorder(Color.black));


		this.btnCreerPartieMulti	= new JButton("Créer une partie");
		this.btnCreerPartieSolo		= new JButton("Créer une partie");
		this.btnRejoindrePartie		= new JButton("Rejoindre une partie");
		this.btnCopierIP			= new JButton("Copier mon adresse IP");
		this.btnLancerPartiePopUp	= new JButton("Lancer la partie");
		this.btnRejoindreMultiPopUp	= new JButton("Rejoindre");

		this.txtNbJoueursMiniCreer		= new JTextField();
		this.txtNbJoueursMaxiCreer		= new JTextField();
		this.txtMotDePasseCreer			= new JTextField();
		this.txtPortMachineCreer		= new JTextField();
		this.txtPortMachineRejoindre	= new JTextField();
		this.txtIPMachineRejoindre		= new JTextField();
		this.txtMotDePasseRejoindre		= new JTextField();
		this.txtNbJoueursLocal			= new JTextField();

		panelTitreCreerPartieSolo.setBorder(BorderFactory.createLineBorder(Color.black));
		panelTitreCreerPartieMult.setBorder(BorderFactory.createLineBorder(Color.black));

		this.btnCreerPartieMulti	.setBackground(Color.WHITE);
		this.btnCreerPartieSolo		.setBackground(Color.WHITE);
		this.btnRejoindrePartie		.setBackground(Color.WHITE);
		this.btnCopierIP			.setBackground(Color.WHITE);
		this.btnLancerPartiePopUp	.setBackground(Color.WHITE);
		this.btnRejoindreMultiPopUp	.setBackground(Color.WHITE);

		/**
		 * Positionnement des composants
		 */


		panelTitreCreerPartieMult.add(lblPartieMulti);
		
		for(int i = 1; i <= 3; i++)
			panelGrillePartieMult.add(new JLabel());

		panelGrillePartieMult.add(this.btnCreerPartieMulti);
		panelGrillePartieMult.add(new JLabel());
		panelGrillePartieMult.add(this.btnRejoindrePartie);

		for(int i = 1; i <= 3; i++)
			panelGrillePartieMult.add(new JLabel());

		panelCreerPartieMult.add(panelTitreCreerPartieMult, BorderLayout.NORTH);
		panelCreerPartieMult.add(new JPanel(), BorderLayout.EAST);
		panelCreerPartieMult.add(new JPanel(), BorderLayout.WEST);
		panelCreerPartieMult.add(panelGrillePartieMult, BorderLayout.CENTER);

		panelTitreCreerPartieSolo.add(lblPartieSolo);


		for(int i = 1; i <= 3; i++)
			panelGrillePartieSolo.add(new JLabel());

		panelGrillePartieSolo.add(lblNbJoueursLocal);
		panelGrillePartieSolo.add(new JLabel());
		panelGrillePartieSolo.add(this.txtNbJoueursLocal);

		panelGrillePartieSolo.add(new JLabel());
		panelGrillePartieSolo.add(this.btnCreerPartieSolo);
		panelGrillePartieSolo.add(new JLabel());

		panelCreerPartieSolo.add(panelTitreCreerPartieSolo, BorderLayout.NORTH);
		panelCreerPartieSolo.add(new JPanel(), BorderLayout.EAST);
		panelCreerPartieSolo.add(new JPanel(), BorderLayout.WEST);
		panelCreerPartieSolo.add(panelGrillePartieSolo, BorderLayout.CENTER);
		panelCreerPartieSolo.add(new JPanel(), BorderLayout.SOUTH);
		

		panelRelierPartieMult.add(panelCreerPartieMult);
		panelRelierPartieMult.add(panelCreerPartieSolo);

		this.add(panelRelierPartieMult);

		/**
		 * Activation des composants
		 */

		this.btnCreerPartieMulti.addActionListener(this);
		this.btnCreerPartieSolo.addActionListener(this);
		this.btnRejoindrePartie.addActionListener(this);
		this.btnCopierIP.addActionListener(this);
		this.btnLancerPartiePopUp.addActionListener(this);
		this.btnRejoindreMultiPopUp.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnCreerPartieMulti)
		{
			JPanel panelPopUp = new JPanel(new GridLayout(4,4,10,10));
			JPanel panelBtnPopUp = new JPanel();

			this.dialogCreerPartie = new JDialog();
			this.dialogCreerPartie.setTitle("Créer une partie");
			this.dialogCreerPartie.setLayout(new BorderLayout());
			this.dialogCreerPartie.setBounds(165, 400, 535, 200);

			panelPopUp.add(new JLabel());
			panelPopUp.add(new JLabel("Nb joueurs mini :"));
			panelPopUp.add(this.txtNbJoueursMiniCreer);
			panelPopUp.add(new JLabel());
			/* -- */
			panelPopUp.add(new JLabel());
			panelPopUp.add(new JLabel("Nb joueurs maxi :"));
			panelPopUp.add(this.txtNbJoueursMaxiCreer);
			panelPopUp.add(new JLabel());
			/* -- */
			panelPopUp.add(new JLabel());
			panelPopUp.add(new JLabel("Password partie :"));
			panelPopUp.add(this.txtMotDePasseCreer);
			panelPopUp.add(new JLabel());
			/* -- */
			panelPopUp.add(new JLabel());
			panelPopUp.add(new JLabel("Port machine :"));
			panelPopUp.add(this.txtPortMachineCreer);
			panelPopUp.add(new JLabel());
			/* -- */
			panelBtnPopUp.add(this.btnCopierIP);
			panelBtnPopUp.add(this.btnLancerPartiePopUp);

			this.dialogCreerPartie.add(panelPopUp, BorderLayout.CENTER);
			this.dialogCreerPartie.add(panelBtnPopUp, BorderLayout.SOUTH);
			this.dialogCreerPartie.setVisible(true);
		}

		if(e.getSource() == this.btnRejoindrePartie)
		{
			JPanel panelPopUp = new JPanel(new GridLayout(3,4,10,10));
			JPanel panelBtnPopUp = new JPanel();

			this.dialogCreerPartie = new JDialog();
			this.dialogCreerPartie.setTitle("Rejoindre une partie");
			this.dialogCreerPartie.setLayout(new BorderLayout());
			this.dialogCreerPartie.setBounds(165, 400, 535, 200);

			panelPopUp.add(new JLabel());
			panelPopUp.add(new JLabel("Adresse IP hôte :"));
			panelPopUp.add(this.txtIPMachineRejoindre);
			panelPopUp.add(new JLabel());
			/* -- */
			panelPopUp.add(new JLabel());
			panelPopUp.add(new JLabel("Port machine hôte :"));
			panelPopUp.add(this.txtPortMachineRejoindre);
			panelPopUp.add(new JLabel());
			/* -- */
			panelPopUp.add(new JLabel());
			panelPopUp.add(new JLabel("Password hôte :"));
			panelPopUp.add(this.txtMotDePasseRejoindre);
			panelPopUp.add(new JLabel());
			/* -- */
			panelBtnPopUp.add(this.btnRejoindreMultiPopUp);

			this.dialogCreerPartie.add(panelPopUp, BorderLayout.CENTER);
			this.dialogCreerPartie.add(panelBtnPopUp, BorderLayout.SOUTH);
			this.dialogCreerPartie.setVisible(true);
		}

		if(e.getSource() == this.btnCreerPartieSolo)
		{

		}
	}
}
