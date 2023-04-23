public class Utilisateur {
    private String pseudo;
    private int nbMinTaches = 1 ; 
    
    private Calendrier calendrierPerso;

    public Utilisateur(String pseudo, Calendrier calendrierPerso) {
        this.pseudo = pseudo;
        this.calendrierPerso = calendrierPerso;
    }

    // getters and setters
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Calendrier getCalendrierPerso() {
        return calendrierPerso;
    }

    public void setCalendrierPerso(Calendrier calendrierPerso) {
        this.calendrierPerso = calendrierPerso;
    }
    public int getNbMinTaches() {
        return nbMinTaches;
    }

    public void setNbMinTaches(int nbMinTaches) {
        this.nbMinTaches = nbMinTaches;
    }
}