package ihm.sectionJeu;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import main.Controleur;

public class PanelDispoParam extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private JButton btnVisualisation;
    private JButton btnFinPartie;
    private JButton btnValiderRecap;
    private JDialog dialogRecap;

    private JTable tableRecap;
    private Object[][] donnees;
    private String[] entetes = {"Nom","Couleur", "Nb points chemins", "Nb points objectifs", "Nb points plus long chemins", "Nb points total"};

    public PanelDispoParam(Controleur ctrl)
    {
        /**
         * Création des composants
         */
        this.ctrl = ctrl;

        this.setLayout(new BorderLayout());
        this.btnVisualisation = new JButton("Visualiser mes cartes");
        this.btnFinPartie = new JButton("Arrêter la partie");

        JPanel panelVisualiser  = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        JPanel panelDispoBtnBas = new JPanel(new GridLayout(1,2));

        /**
         * Positionnement des composants
         */
            
        panelVisualiser.add(this.btnVisualisation);
        panelDispoBtnBas.add(this.btnFinPartie);

        this.add(panelVisualiser, BorderLayout.CENTER);
        this.add(panelDispoBtnBas,BorderLayout.SOUTH);

        /**
         * Activation des composants
         */
        this.btnVisualisation.addActionListener(this);
        this.btnFinPartie.addActionListener(this);
    }

    public void recapFinPartie()
    {
        //JOPtionPane --> Etes vous sur d'arreter la partie ?
        JOptionPane jop = new JOptionPane();
        jop.showConfirmDialog(null, "Etes vous sur de vouloir arrêter la partie ?", "Arrêt de la partie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        jop.setVisible(true);
        
        this.dialogRecap = new JDialog();
        this.dialogRecap.setLayout(new BorderLayout());
        this.dialogRecap.setTitle("Récapitulatif et score de la partie");
        this.dialogRecap.setBounds(400,300,1200,500);
        this.dialogRecap.setResizable(false);
        this.dialogRecap.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);

        JPanel panelBtn 		= new JPanel(new FlowLayout(FlowLayout.CENTER, 0,50));

        this.btnValiderRecap 	= new JButton("Quitter");
        this.btnValiderRecap.setBackground(Color.WHITE);
        this.donnees 			= new Object[this.ctrl.getNbJoueurPartie()][6];
        this.tableRecap 		= new JTable(this.donnees, this.entetes);
        this.tableRecap.setEnabled(false);

        for(int i = 0; i < this.ctrl.getNbJoueurPartie(); i++)
        {
            this.donnees[i][0] = this.ctrl.getJoueur(i);
            this.donnees[i][1] = this.ctrl.getJoueur(i).getCouleur().toString().replace("java.awt.Color", "");
            this.donnees[i][2] = this.ctrl.getJoueur(i).getNbPointsChemin();
            this.donnees[i][3] = this.ctrl.getJoueur(i);
            //this.donnees[i][2] = this.ctrl.getJoueur(i).getNbPointsObjectif();
            this.donnees[i][4] = this.ctrl.getJoueur(i).getRouteLaPlusLongue();
            this.donnees[i][5] = this.ctrl.getJoueur(i);
            //this.donnees[i][5] = this.ctrl.getJoueur(i).getNbPointsTotal();
        }
        
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);


        for(int i = 0; i < this.tableRecap.getColumnCount(); i++)
            this.tableRecap.getColumnModel().getColumn(i).setCellRenderer(custom);

        panelBtn.add(this.btnValiderRecap);

        this.dialogRecap.add(this.tableRecap.getTableHeader(), BorderLayout.NORTH);
        this.dialogRecap.add(this.tableRecap, BorderLayout.CENTER);
        this.dialogRecap.add(panelBtn, BorderLayout.SOUTH);

        this.btnValiderRecap.addActionListener(this);

        this.dialogRecap.setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.btnVisualisation)
        {
            if(this.ctrl.getEstJoueurCourant().getMainWagon().size() != 0 || this.ctrl.getEstJoueurCourant().getMainObjectif().size() != 0 )
            {
                JDialog jDialog = new JDialog();
                jDialog.setBounds(650, 250, 600, 600);
                jDialog.setResizable(false);
                jDialog.setModal(true);
                jDialog.setTitle("Visualisation de vos cartes");
                jDialog.add(new PanelMainJoueur(ctrl));
                jDialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);

                jDialog.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Vous n'avez aucune carte");
            }
        }

        if(e.getSource() == this.btnFinPartie)
            this.recapFinPartie();

        if(e.getSource() == this.btnValiderRecap)
        {	
            this.dialogRecap.dispose();
            this.ctrl.changerPanel("Menu");
        }
    }
}