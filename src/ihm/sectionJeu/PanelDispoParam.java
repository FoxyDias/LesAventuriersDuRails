package ihm.sectionJeu;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.awt.event.ActionEvent;

import main.Controleur;
import metier.Joueur;

public class PanelDispoParam extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private JButton btnVisualisation;
    private JButton btnFinPartie;
    private JButton btnValiderRecap;
    private JDialog dialogRecap;

    private JTable tableRecap;
    private Object[][] donnees;
    private String[] entetes = {"Nom", "Nb points chemins", "Nb points objectifs", "Plus long chemins", "Bonus","Malus de cartes non remplies", "Nb points total"};

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
        /*if(JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir arrêter la partie ?", "Arrêt de la partie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE))
            return;*/

        this.ctrl.chercheCheminLeplusPlong();
        this.ctrl.ajouterPoint();

        this.dialogRecap = new JDialog();
        this.dialogRecap.setLayout(new BorderLayout());
        this.dialogRecap.setTitle("Récapitulatif et score de la partie");
        this.dialogRecap.setBounds(400,300,1200,500);
        this.dialogRecap.setResizable(false);
        this.dialogRecap.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
        this.dialogRecap.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JPanel panelBtn 		= new JPanel(new GridLayout(2,1,0,10));

        this.btnValiderRecap 	= new JButton("Quitter");
        this.btnValiderRecap.setBackground(Color.WHITE);
        this.donnees 			= new Object[this.ctrl.getNbJoueurPartie()][7];
        this.tableRecap 		= new TableResultat(this.ctrl, this.donnees, this.entetes);
        this.tableRecap.setEnabled(false);

        Collections.sort(this.ctrl.getLstJoueur());

        for(int i = 0; i < this.ctrl.getNbJoueurPartie(); i++)
        {
            this.donnees[i][0] = this.ctrl.getJoueur(i);
            this.donnees[i][1] = this.ctrl.getJoueur(i).getNbPointsChemin();
            this.donnees[i][2] = this.ctrl.getJoueur(i).completeCarteObjectif();
            this.donnees[i][3] = this.ctrl.getJoueur(i).getRouteLaPlusLongue();
            if(this.ctrl.getJoueur(i) == this.ctrl.getJoueurPlusLong())
                this.donnees[i][4] = this.ctrl.getNbPointsPlusLongChemin();
            else
                this.donnees[i][4] = 0;
            this.donnees[i][5] = this.ctrl.getJoueur(i).getMalusCarteObjectif();
            this.donnees[i][6] = this.ctrl.getJoueur(i).getNbPointsTotal();
        }
        
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);

        for(int i = 0; i < this.tableRecap.getColumnCount(); i++)
            this.tableRecap.getColumnModel().getColumn(i).setCellRenderer(custom);

        panelBtn.add(new JLabel(this.ctrl.getLstJoueur().get(0) + " est le gagnant de la partie !", JLabel.CENTER));
        panelBtn.add(this.btnValiderRecap, BorderLayout.SOUTH);

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

    public class TableResultat extends JTable
    {
        private Controleur ctrl;

        public TableResultat(Controleur ctrl, Object[][] donnee, String[] enTete)
        {
            super(donnee, enTete);
            this.ctrl = ctrl;

        }

        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component result = super.prepareRenderer(renderer, row, column);
            Color gold = new Color(255,215,0);
            Color silver = new Color(192,192,192);
            Color bronze = new Color(205,127,50);
            result.setBackground(this.ctrl.getJoueur(0).getNbPointsTotal() == (int)this.getValueAt(row, 6) ? gold : 
                                (this.ctrl.getJoueur(1).getNbPointsTotal() == (int)this.getValueAt(row, 6) ? silver : 
                                (this.ctrl.getJoueur(2).getNbPointsTotal() == (int)this.getValueAt(row, 6) ? bronze : Color.WHITE)));
            return result;
        }
    }
}