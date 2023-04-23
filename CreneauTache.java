public class CreneauTache {
    private Creneau creneau;
    private TacheSimple tache ;
    public TacheSimple getTache() {
        return tache;
    }
    public void setTache(TacheSimple tache) {
        this.tache = tache;
    }
    public CreneauTache(Creneau creneau, TacheSimple tache) {
        this.creneau = creneau;
        this.tache = tache;
    }
    public Creneau getCreneau() {
        return creneau;
    }
    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
    public void afficherCreneauTache(){
        creneau.afficher();
        tache.afficher();
    }
}
