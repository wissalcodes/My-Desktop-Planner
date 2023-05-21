package Noyau ;

public class CreneauTache {
    private static int dureeMinimale = 30 ;     //durée minimale de 30 minutes, sera vérifié lors de la décomposition d'un créneau
    public static int getDureeMinimale() {
        return dureeMinimale;
    }
    public static void setDureeMinimale(int dureeMinimale) {
        CreneauTache.dureeMinimale = dureeMinimale;
    }
    private Creneau creneau;
    private TacheSimple tache ;
    private boolean estBloqué = false;  //estBloqué est mise à vrai lorsque la tache ne peut pas remplace ni déplacée
    public CreneauTache(Creneau creneau, TacheSimple tache) {
        this.creneau = creneau;
        this.tache = tache;
    }
    
    public boolean isEstBloqué() {
        return estBloqué;
    }
    public void setEstBloqué(boolean estBloqué) {
        this.estBloqué = estBloqué;
    }
    public TacheSimple getTache() {
        return tache;
    }
    public void setTache(TacheSimple tache) {
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
    @Override
    public String toString() {
        return "CreneauTache [" + creneau + ", " + tache + ", estBloqué=" + estBloqué + "]";
    }
}
