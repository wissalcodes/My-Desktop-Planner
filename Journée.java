import java.time.LocalDate;
import java.util.*;

import javax.imageio.ImageTranscoder;

public class Journée implements Comparable<Journée> {
    public Journée(LocalDate date) {
        this.date = date;
    }

    public Journée() {
    }

    private LocalDate date; // Date de la journée sous le format aaaa-mm-jj
    private TreeSet<CreneauTache> listCreneauxTaches = new TreeSet<>(
            Comparator.comparing(CreneauTache::getCreneau, Comparator.comparing(Creneau::getHeureDebut)));
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
        return date + " - Créneaux libres :  " + listCreneauxLibres + "\n (Creneau-Tache)" + listCreneauxTaches;
    }

    @Override
    public int compareTo(Journée o) {
        return this.date.compareTo(o.date);
    }

    void introduireEtatsTaches() {
        Iterator iterator = listCreneauxTaches.iterator();
        while (iterator.hasNext()) {
            CreneauTache creneauTache = (CreneauTache) iterator.next();
            Tache tache = creneauTache.getTache();
            Scanner scanner = new Scanner(System.in);
            System.out.print("** Etat de réalisation de la tache: " + tache.getNom());
            String étatString = scanner.nextLine();

            EtatTache etat = EtatTache.valueOf(étatString);
            if (etat == EtatTache.COMPLETED) {
                nbTachesRéalisées++;
            }
            tache.setEtat(etat);
        }
        if (nbTachesRéalisées / Utilisateur.nbMinTaches >= 5) {
            badgeJournalier = Badge.GOOD;
        }
        if (nbTachesRéalisées / Utilisateur.nbMinTaches >= 15) {
            badgeJournalier = Badge.VERYGOOD;
        }
        if (nbTachesRéalisées / Utilisateur.nbMinTaches >= 45) {
            badgeJournalier = Badge.VERYGOOD;
        }
        félicitation();
    }

    public double rendementJournalier() {
        return (nbTachesRéalisées / nbTachesPrévues);
    }

    public void félicitation() {
        if (Utilisateur.nbMinTaches < nbTachesRéalisées) {
            System.out.println("Félicitations! Vous avez atteint le seuil minimal de taches à accomplir par jour.");
        }
    }

}
