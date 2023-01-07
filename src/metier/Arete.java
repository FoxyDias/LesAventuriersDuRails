package metier;

public class Arete {

    private Noeud noeudDep;
    private Noeud noeudArr;
    private String couleur;
    private int   nbWagon;
    private boolean estDouble;
    private boolean estOccupe;
    private Arete areteDouble;
    private Joueur occupateur;

    /**
    * Constructeur de la classe arrete
    * @param noeudDep la noeudDep de départ
    * @param noeudArr la noeudDep d'arrivée
    * @param couleur la couleur de l'arrete
    * @param nbWagon le nombre de nbWagon
    */
    public Arete(Noeud noeudDep, Noeud noeudArr, String couleur, int nbWagon) {
        this.noeudDep = noeudDep;
        this.noeudArr = noeudArr;
        this.couleur  = couleur;
        this.nbWagon  = nbWagon;
        this.estDouble = false;
        this.estOccupe = false;
        this.areteDouble = null;
        this.occupateur = null;



        this.noeudDep.ajoutArete(this);
        this.noeudArr.ajoutArete(this);
    }

    public boolean getEstDouble() {
        return this.estDouble;
    }
    public boolean getEstOccupe() {
        return this.estOccupe;
    }

    public void setEstOccupe(boolean estOccupe) {
        this.estOccupe = estOccupe;
    }

    public void setEstDouble(boolean estDouble){
        this.estDouble = estDouble;
    }

    public void setAreteDouble(Arete areteDouble){
        this.areteDouble = areteDouble;
    }

    public Arete getAreteDouble(){
        return this.areteDouble;
    }

    public void setOccupateur(Joueur occupateur){
        this.occupateur = occupateur;
    }

    public Joueur getOccupateur(){
        return this.occupateur;
    }


    public void supprArete() {
        this.noeudDep.supprArete(this);
        this.noeudArr.supprArete(this);
    }

    public Noeud getNoeudDep()  {return this.noeudDep;}
    public Noeud getNoeudArr()  {return this.noeudArr;}
    public String getCouleur() {
        return this.couleur;
    }
    public int   getWagon()   {
        return this.nbWagon;
    }

    public void setNoeud(Noeud noeudDep){
        this.noeudDep = noeudDep;
    }
    public void setNoeud2(Noeud noeudArr){
        this.noeudArr = noeudArr;
    }
    public void setCouleur(String couleur){
        this.couleur = couleur;
    }
    public void setWagon(int nbWagon){
        this.nbWagon = nbWagon;
    }

    public String toString() {
        return "Arete [noeudDep=" + noeudDep + ", noeudArr=" + noeudArr + ", couleur=" + couleur + ", nbWagon=" + nbWagon + ", estDouble=" + estDouble + ", estOccupe=" + estOccupe
                + "]";
    }
}
