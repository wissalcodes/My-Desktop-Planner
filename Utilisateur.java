import java.util.*;
public class Utilisateur {
    private String pseudo;
    private int nbMinTaches = 1 ; 
    private List<Planning> historiquePlannings; 
    public List<Planning> getHistoriquePlannings() {
        return historiquePlannings;
    }

    public void setHistoriquePlannings(List<Planning> historiquePlannings) {
        this.historiquePlannings = historiquePlannings;
    }

    private List<Projet> hitoriqueProjets;  
    private Calendrier calendrierPerso;
    private List<Catégorie> listCatégories ; 

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

    public List<Projet> getHitoriqueProjets() {
        return hitoriqueProjets;
    }

    public void setHitoriqueProjets(List<Projet> hitoriqueProjets) {
        this.hitoriqueProjets = hitoriqueProjets;
    }

    public List<Catégorie> getListCatégories() {
        return listCatégories;
    }

    public void setListCatégories(List<Catégorie> listCatégories) {
        this.listCatégories = listCatégories;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Utilisateur other = (Utilisateur) obj;
        if (pseudo == null) {
            if (other.pseudo != null)
                return false;
        } else if (!pseudo.equals(other.pseudo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur [pseudo=" + pseudo + ", nbMinTaches=" + nbMinTaches + ", historiquePlannings="
                + historiquePlannings + ", hitoriqueProjets=" + hitoriqueProjets + ", calendrierPerso="
                + calendrierPerso + ", listCatégories=" + listCatégories + "]";
    }
}