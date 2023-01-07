package ihm.sectionMenu;

import main.Controleur;

import javax.swing.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.FlowLayout;

public class PanelCentreMenu extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton btnCreerPartieMulti;
	private JButton btnCreerPartieSolo;
	private JButton btnRejoindrePartie;
	private JButton btnCopierIP;
	private JButton btnLancerPartiePopUp;
	private JButton btnRejoindreMultiPopUp;

	private JTextField txtNbJoueursMiniCreer;
	private JTextField txtPortMachineCreer;
	private JTextField txtPortMachineRejoindre; 
	private JTextField txtIPMachineRejoindre;
	private JTextField txtNbJoueursLocal;

	private JPasswordField txtMotDePasseCreer;
	private JPasswordField txtMotDePasseRejoindre;

	private JDialog dialogCreerPartie;


	/* --- JDIALOG --- */
	private JButton btnValider;
	private JButton btnCarteObjectif1;
	private JButton btnCarteObjectif2;
	private JButton btnCarteObjectif3;
	private JDialog dialog;

	private int nbPopUP = 1;

	public PanelCentreMenu(Controleur ctrl)
	{
		/**
		 * Création des boutons
		 */

		this.ctrl = ctrl;
		
		JLabel lblPartieMulti		= new JLabel("Jouer en multijoueur");
		JLabel lblPartieSolo		= new JLabel("Jouer en local");
		JLabel lblNbJoueursLocal	= new JLabel("Nombre de joueurs : ");

		lblPartieSolo.setFont(new Font("", Font.BOLD, 13));
		lblPartieMulti.setFont(new Font("", Font.BOLD, 13));

		JPanel panelCreerPartieSolo		= new JPanel(new BorderLayout());
		JPanel panelTitreCreerPartieSolo= new JPanel();
		JPanel panelGrillePartieSolo 	= new JPanel(new GridLayout(3,3,20,20));	
		JPanel panelCreerPartieMult 	= new JPanel(new BorderLayout());
		JPanel panelTitreCreerPartieMult= new JPanel();
		JPanel panelGrillePartieMult	= new JPanel(new GridLayout(3,3,20,20));

		JPanel panelRelierPartieMult	= new JPanel(new GridLayout(1,2,40,0));

		Border border = BorderFactory.createLineBorder(Color.black, 1);

		panelCreerPartieSolo.setBorder(border);
		panelCreerPartieMult.setBorder(border);


		this.btnCreerPartieMulti	= new JButton("Créer une partie");
		this.btnCreerPartieSolo		= new JButton("Créer une partie");
		this.btnRejoindrePartie		= new JButton("Rejoindre une partie");
		this.btnCopierIP			= new JButton("Copier mon adresse IP");
		this.btnLancerPartiePopUp	= new JButton("Lancer la partie");
		this.btnRejoindreMultiPopUp	= new JButton("Rejoindre");

		this.txtNbJoueursMiniCreer		= new JTextField();
		this.txtPortMachineCreer		= new JTextField();
		this.txtPortMachineRejoindre	= new JTextField();
		this.txtIPMachineRejoindre		= new JTextField();
		this.txtNbJoueursLocal			= new JTextField();

		this.txtMotDePasseCreer			= new JPasswordField();
		this.txtMotDePasseRejoindre		= new JPasswordField();

		panelTitreCreerPartieSolo.setBorder(BorderFactory.createLineBorder(Color.black));
		panelTitreCreerPartieMult.setBorder(BorderFactory.createLineBorder(Color.black));

		this.btnCreerPartieMulti	.setBackground(Color.WHITE);
		this.btnCreerPartieSolo		.setBackground(Color.WHITE);
		this.btnRejoindrePartie		.setBackground(Color.WHITE);
		this.btnCopierIP			.setBackground(Color.WHITE);
		this.btnLancerPartiePopUp	.setBackground(Color.WHITE);
		this.btnRejoindreMultiPopUp	.setBackground(Color.WHITE);

		this.txtNbJoueursMiniCreer	.setHorizontalAlignment(JTextField.CENTER);
		this.txtPortMachineCreer	.setHorizontalAlignment(JTextField.CENTER);
		this.txtPortMachineRejoindre.setHorizontalAlignment(JTextField.CENTER);
		this.txtIPMachineRejoindre	.setHorizontalAlignment(JTextField.CENTER);

		this.txtIPMachineRejoindre	.setBorder(border);
		this.txtMotDePasseCreer		.setBorder(border);
		this.txtMotDePasseRejoindre	.setBorder(border);
		this.txtNbJoueursLocal		.setBorder(border);
		this.txtNbJoueursMiniCreer	.setBorder(border);
		this.txtPortMachineCreer	.setBorder(border);
		this.txtPortMachineRejoindre.setBorder(border);


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

		/* A vraiment optimiser */
		this.txtNbJoueursMiniCreer.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				char c = e.getKeyChar();
				if(!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))
					e.consume();
			}
		});

		this.txtPortMachineCreer.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				char c = e.getKeyChar();
				if(!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))
					e.consume();
			}
		});

		this.txtPortMachineRejoindre.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				char c = e.getKeyChar();
				if(!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))
					e.consume();
			}
		});

		this.txtNbJoueursLocal.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				char c = e.getKeyChar();
				if(!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))
					e.consume();
			}
		});
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnCreerPartieMulti)
		{
			JPanel panelPopUp = new JPanel(new GridLayout(3,4,0,10));
			JPanel panelBtnPopUp = new JPanel();

			this.dialogCreerPartie = new JDialog();
			this.dialogCreerPartie.setTitle("Créer une partie");
			this.dialogCreerPartie.setLayout(new BorderLayout());
			this.dialogCreerPartie.setBounds(165, 500, 535, 200);

			/* -- */
			panelPopUp.add(new JLabel());
			panelPopUp.add(new JLabel("Nb joueurs partie :"));
			panelPopUp.add(this.txtNbJoueursMiniCreer);
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
			JPanel panelPopUp = new JPanel(new GridLayout(3,4,0,5));
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

			//Empecher une autre saisie que cette syntaxe 172.192.1.23
		
			this.dialogCreerPartie.add(panelPopUp, BorderLayout.CENTER);
			this.dialogCreerPartie.add(panelBtnPopUp, BorderLayout.SOUTH);
			this.dialogCreerPartie.setVisible(true);
		}

		if(e.getSource() == this.btnCreerPartieSolo)
		{			
			int nbJoueurs = Integer.parseInt(this.txtNbJoueursLocal.getText());
			
			if(this.txtNbJoueursLocal.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Veuillez remplir le nombre de joueurs", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(nbJoueurs < 1 || nbJoueurs > this.ctrl.getNbJoueurMax()){
				JOptionPane.showMessageDialog(null, "Veuillez renseignez un nombre de joueurs entre 1 et le nombre de joueurs maximale de la mappe", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				this.ctrl.setNbJoueurPartie(Integer.parseInt(this.txtNbJoueursLocal.getText()));
				this.ctrl.changerPanel("Jeu");
		
				this.creerPopUpCarteObjectif();


			}
		}

		if(e.getSource() == this.btnLancerPartiePopUp)
		{
			String port = this.txtPortMachineCreer.getText();  
			String motDePasse = this.txtMotDePasseCreer.getText();

			if( this.txtNbJoueursMiniCreer.getText().equals("0") || this.txtNbJoueursMiniCreer.getText().equals("" +  this.ctrl.getNbJoueurMax()) || port.equals("") || motDePasse.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
			{
				this.ctrl.changerPanel("Jeu");
				this.dialogCreerPartie.dispose();
			}
		}

		if(e.getSource() == this.btnRejoindreMultiPopUp)
		{
			String ip = this.txtIPMachineRejoindre.getText();
			String port = this.txtPortMachineRejoindre.getText();
			String motDePasse = this.txtMotDePasseRejoindre.getText();

			if(ip.equals("") || motDePasse.equals("") || port.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
			{
				this.ctrl.changerPanel("Jeu");
				this.dialogCreerPartie.dispose();
 			}
		}

		if(e.getSource() == this.btnCopierIP)
		{
			/* Copier l'adresse IP de la machine */
			try {
				InetAddress ip = InetAddress.getLocalHost();
				String ipString = ip.getHostAddress();
				StringSelection ss = new StringSelection(ipString);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			} catch (UnknownHostException e1) {e1.printStackTrace();}
		}


		/* ------------------------------ */
		/* 		 BOUTON JDIALOG 		  */
		/* ------------------------------ */

		if(e.getSource() == this.btnValider)
		{
			if(!(this.btnCarteObjectif1.isOpaque() && this.btnCarteObjectif2.isOpaque() ||  
				 this.btnCarteObjectif1.isOpaque() && this.btnCarteObjectif3.isOpaque() || 
				 this.btnCarteObjectif2.isOpaque() && this.btnCarteObjectif1.isOpaque() || 
				 this.btnCarteObjectif2.isOpaque() && this.btnCarteObjectif3.isOpaque() ||
				 this.btnCarteObjectif3.isOpaque() && this.btnCarteObjectif1.isOpaque() ||
				 this.btnCarteObjectif3.isOpaque() && this.btnCarteObjectif2.isOpaque()))
			{
				JOptionPane.showMessageDialog(null, "Veuillez choisir 2 cartes objectifs", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
			{
				this.dialog.dispose();

				while(this.nbPopUP < this.ctrl.getNbJoueurPartie())
				{
					this.nbPopUP++;
					this.creerPopUpCarteObjectif();
					
				}
			}
		}

		if(e.getSource() == this.btnCarteObjectif1)
		{
			this.inverseEtatBtn(this.btnCarteObjectif1);
		}

		if(e.getSource() == this.btnCarteObjectif2)
		{
			this.inverseEtatBtn(this.btnCarteObjectif2);
		}

		if(e.getSource() == this.btnCarteObjectif3)
		{
			this.inverseEtatBtn(this.btnCarteObjectif3);
		}
	}

	public PanelCentreMenu getPanelCentreMenu() { return this;} 

	@Override
	public void setEnabled(boolean b) 
	{
		this.btnCreerPartieMulti.setEnabled(b);
		this.btnCreerPartieSolo.setEnabled(b);
		this.txtNbJoueursLocal.setEnabled(b);
	}

	public void inverseEtatBtn(JButton btn)	
	{
		if(btn.isOpaque())
		{
			btn.setOpaque(false);
			btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		}
		else
		{
			btn.setOpaque(true);
			btn.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}
	}

	public JDialog creerPopUpCarteObjectif()
	{
		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER,0,75));

		this.dialog = new JDialog();
		this.dialog.setTitle("Joueur " + this.nbPopUP + ", choisissez au moins deux cartes objectifs");
		this.dialog.setLayout(new GridLayout(2,3));
		this.dialog.setBounds(500, 400, 1000, 400);
		this.dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.dialog.setResizable(false);

		this.btnCarteObjectif1 = new JButton(new ImageIcon(this.ctrl.getVersoCarteObjectif()));
		this.btnCarteObjectif2 = new JButton(new ImageIcon(this.ctrl.getVersoCarteObjectif()));
		this.btnCarteObjectif3 = new JButton(new ImageIcon(this.ctrl.getVersoCarteObjectif()));
		this.btnValider = new JButton("Valider");

		this.btnCarteObjectif1.setBackground(Color.WHITE);
		this.btnCarteObjectif2.setBackground(Color.WHITE);
		this.btnCarteObjectif3.setBackground(Color.WHITE);
		this.btnValider.setBackground(Color.WHITE);

		panelBtn.add(this.btnValider);
	
		this.dialog.add(btnCarteObjectif1);
		this.dialog.add(btnCarteObjectif2);
		this.dialog.add(btnCarteObjectif3);
		this.dialog.add(new JLabel());
		this.dialog.add(panelBtn);
		this.dialog.add(new JLabel());
		this.dialog.setVisible(true);

		this.btnCarteObjectif1.addActionListener(this);
		this.btnCarteObjectif2.addActionListener(this);
		this.btnCarteObjectif3.addActionListener(this);
		this.btnValider.addActionListener(this);


		return this.dialog;

	}
}
