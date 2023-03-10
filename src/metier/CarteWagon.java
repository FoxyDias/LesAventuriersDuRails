package metier;

import java.awt.Color;

public class CarteWagon {

    private String couleur;
    private Color color;
    private String recto;

    public CarteWagon(String couleur) {
        this.couleur = couleur;
        if(this.couleur.equals("Joker"))
            //this.color = new Color(255, 255, 255);
            this.color = null;
        else
            this.color = new Color(Integer.parseInt(couleur.split(",")[0]), Integer.parseInt(couleur.split(",")[1]) , Integer.parseInt(couleur.split(",")[2]));
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setRecto(String recto) {
        this.recto = recto;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public String getRecto() {
        return recto;
    }

    public Color getColor() {
        return color;
    }
}
