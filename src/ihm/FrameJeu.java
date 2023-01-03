package ihm;

import ihm.jeu.PanelPreparationJeu;
import ihm.mappes.PanelListeMappes;
import main.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameJeu extends JFrame implements ActionListener {

    Controleur ctrl;

    private JButton btnLancer;
    private JButton btnLstMap;
    private JButton btnQuitter;
    private PanelListeMappes panelListeMappes;
    private PanelPreparationJeu panelPreparationJeu;

    public FrameJeu(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout( new GridLayout(3,1));

        this.btnLancer  = new JButton("Lancer");
        this.btnLstMap  = new JButton("Mappes");
        this.btnQuitter = new JButton("Quitter");

        this.btnLancer.addActionListener(this);
        this.btnLstMap.addActionListener(this);
        this.btnQuitter.addActionListener(this);

        this.add( this.btnLancer );
        this.add( this.btnLstMap );
        this.add( this.btnQuitter );

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

        if( e.getSource() == this.btnLancer )
        {
            this.panelPreparationJeu = new PanelPreparationJeu(ctrl);
            this.add( this.panelListeMappes );
        }
        else if( e.getSource() == this.btnLstMap )
        {
            this.panelListeMappes = new PanelListeMappes(ctrl);
            this.add( this.panelListeMappes );
        }
        else if( e.getSource() == this.btnQuitter )
        {
            this.dispose();
        }

    }
}
