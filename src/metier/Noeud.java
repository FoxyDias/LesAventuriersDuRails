package metier;

import java.util.ArrayList;

public class Noeud
{
    private String nom;
    private int posX;
    private int posY;
    private int nomX;
    private int nomY;

    private ArrayList<Arete> alArrete;

    /**
     * Constructeur de la classe noeud
     * @param nom  le nom de la noeud
     * @param posX la coordonnée posX de la Noeud
     * @param posY la coordonnée posY de la noeud
     */
    public Noeud(String nom, int posX, int posY, int nomX, int nomY)
    {
        this.nom  = nom;
        this.posX = posX;
        this.posY = posY;
        this.nomX = nomX;
        this.nomY = nomY;

        this.alArrete = new ArrayList<Arete>();
    }

    /**
     * Ajoute les arete qui appartienne aux noeud
     * @param arete
     */

    
    public void ajoutArete(Arete arete){
        this.alArrete.add(arete);
    }
    /**
     * Supprime une arete 
     * @param arete
     */
    public void supprArete(Arete arete){
        this.alArrete.remove(arete);
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public ArrayList<Arete> getArrayArete(){
        return this.alArrete;
    }
    /**
        * Retourne le nom de la noeud
        * @return le nom de la noeud
     */
    public String getNom()
    {
        return this.nom;
    }
    /**
        * Retourne la coordonnée posX de la noeud
        * @return la coordonnée posX de la noeud
     */
    public int getX()
    {
        return this.posX;
    }
    /**
        * Retourne la coordonnée posY de la noeud
        * @return la coordonnée posY de la noeud
     */
    public int getY()
    {
        return this.posY;
    }
    
    /**
        * Retourne la coordonnée posX du nom de la noeud
        * @return la coordonnée posX du nom de la noeud
     */
    public int getNomX()
    {
        return this.nomX;
    }
    /**
        * Retourne la coordonnée posY du nom de la noeud
        * @return la coordonnée posY du nom de la noeud
     */
    public int getNomY()
    {
        return this.nomY;
    }

    public void setX(int x){
        this.posX = x;
    }

    public void setY(int y){
        this.posY = y;
    }

    public void setNomX(int x){
        this.nomX = x;
    }

    public void setNomY(int y){
        this.nomY = y;
    }

    /**
     * Modifie la position X du noeud
     * @param posX
     */
    public void setPosX(int posX){
        this.posX = posX;
    }

    /**
     * Modifie la position Y du noeud
     * @param posY
     */
    public void setPosY(int posY){
        this.posY = posY;
    }

    /**
        *Retourne l'objet sous forme String
        * @return l'objet sous forme String
     */
    @Override
    public String toString()
    {
        return this.nom + " (" + posX + "," + posY + ")";
    }
}