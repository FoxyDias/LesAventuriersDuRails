package metier;


public class CarteObjectif {

    private Noeud noeudDep;
    private Noeud noeudArr;
    private boolean accomplie;
    private int nbPoints;


    public CarteObjectif(Noeud noeudDep, Noeud noeudArr, int nbPoints) {
        this.noeudDep = noeudDep;
        this.noeudArr = noeudArr;
        this.nbPoints = nbPoints;
        this.accomplie = false;
    }

    public Noeud getNoeudDep()  {return this.noeudDep;}

    public Noeud getNoeudArr()  {return this.noeudArr;}

    public int getNbPoints()    {return this.nbPoints;}

    public boolean isAccomplie() {return this.accomplie;}
}
