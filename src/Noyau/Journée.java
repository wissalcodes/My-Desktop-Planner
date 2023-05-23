package Noyau;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Journée implements Comparable<Journée>, Serializable {

    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    public Journée(LocalDate date) {
        this.date = date;
    }

    public Journée() {
    }

    private LocalDate date ; // Date de la journée sous le format aaaa-mm-jj
    private TreeSet<CreneauTache> listCreneauxTaches = new TreeSet<>();
    private Badge badgeJournalier;
    private int nbTachesPrévues;
    private int nbTachesRéalisées;
    private TreeSet<Creneau> listCreneauxLibres = new TreeSet<>();

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TreeSet<CreneauTache> getListCreneauxTaches() {
        return listCreneauxTaches;
    }

    public void setListCreneauxTaches(TreeSet<CreneauTache> listCreneauxTaches) {
        this.listCreneauxTaches = listCreneauxTaches;
    }

    public Badge getBadgeJournalier() {
        return badgeJournalier;
    }

    public void setBadgeJournalier(Badge badgeJournalier) {
        this.badgeJournalier = badgeJournalier;
    }

    public int getNbTachesPrévues() {
        return nbTachesPrévues;
    }

    public void setNbTachesPrévues(int nbTachesPrévues) {
        this.nbTachesPrévues = nbTachesPrévues;
    }

    public int getNbTachesRéalisées() {
        
        return nbTachesRéalisées;
    }

    public void setNbTachesRéalisées(int nbTachesRéalisées) {
        this.nbTachesRéalisées = nbTachesRéalisées;
    }

    public TreeSet<Creneau> getListCreneauxLibres() {
        return listCreneauxLibres;
    }

    public void setListCreneauxLibres(TreeSet<Creneau> listCreneauxLibres) {
        this.listCreneauxLibres = listCreneauxLibres;
    }

    @Override
    public String toString() {
        String p = "";
        Iterator<CreneauTache> it = listCreneauxTaches.iterator();
        while (it.hasNext()) {
            CreneauTache cr = it.next();
            p = p + cr.toString() + "\n";
        }
        return date + /* " - Créneaux libres :  " + listCreneauxLibres + */ "\n (Creneau-Tache)" + p;

        // return date + /* " - Créneaux libres : " + listCreneauxLibres +*/ "\n
        // (Creneau-Tache)" + listCreneauxTaches;

    }

    @Override
    public int compareTo(Journée o) {
        return this.date.compareTo(o.date);
    }

}
