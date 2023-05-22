import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Planning implements Comparable<Planning>, Serializable {
    public Planning(LocalDate dateDébut, LocalDate dateFin, TreeSet<Journée> journéesPlanifiées) {
        this.dateDébut = dateDébut;
        this.dateFin = dateFin;
        this.journéesPlanifiées = journéesPlanifiées;
    }

    public Planning() {
    }

    private LocalDate dateDébut;
    private LocalDate dateFin;
    private TreeSet<Journée> journéesPlanifiées;
    protected int nbBadges[] = { 0, 0, 0 }; // nombre de badge GOOD[0],VERYGOOD[1],EXCELLENT[2] obtenus pendant le
                                            // planning

    public void setBadge(int i) {
        nbBadges[i] += 1;
    }

    public int[] getNbBadges() {
        return nbBadges;
    }

    public void setNbBadges(int[] nbBadges) {
        this.nbBadges = nbBadges;
    }

    private int nbEncouragement;

    public int getNbEncouragement() {
        return nbEncouragement;
    }

    public void setNbEncouragement(int nbEncouragement) {
        this.nbEncouragement = nbEncouragement;
    }

    public LocalDate getDateDébut() {
        return dateDébut;
    }

    public void setDateDébut(LocalDate dateDébut) {
        this.dateDébut = dateDébut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public TreeSet<Journée> getJournéesPlanifiées() {
        return journéesPlanifiées;
    }

    public void setJournéesPlanifiées(TreeSet<Journée> journéesPlanifiées) {
        this.journéesPlanifiées = journéesPlanifiées;
    }

    @Override
    public String toString() {
        return "Planning [\ndateDébut=" + dateDébut + ",\ndateFin=" + dateFin + ", \njournéesPlanifiées="
                + journéesPlanifiées + "]" + "\nBadges: " + "\nGOOD - " + nbBadges[0] + "\nVERYGOOD - " + nbBadges[1]
                + "\nEXCELLENT - " + nbBadges[2];
    }

    @Override
    public int compareTo(Planning other) {
        return this.dateDébut.compareTo(other.getDateDébut());
    }

    public void consulterBadgesPlanning() {
        Iterator<Journée> iterator = journéesPlanifiées.iterator();
        while (iterator.hasNext()) {
            Journée j = iterator.next();
            if (j.getBadgeJournalier() == Badge.GOOD) {
                nbBadges[0]++;
            }
            if (j.getBadgeJournalier() == Badge.VERYGOOD) {
                nbBadges[1]++;
            }
            if (j.getBadgeJournalier() == Badge.EXCELLENT) {
                nbBadges[2]++;
            }
        }
    }

    public double moyenneRendementJournalier() {
        Iterator<Journée> iterator = journéesPlanifiées.iterator();
        double sum = 0;
        while (iterator.hasNext()) {
            Journée j = iterator.next();
            sum += j.rendementJournalier();
        }
        return (sum / journéesPlanifiées.size());
    }

    public int nbEncouragementsSystème() {
        Iterator<Journée> iterator = journéesPlanifiées.iterator();
        int sum = 0;
        while (iterator.hasNext()) {
            Journée j = iterator.next();
            if (j.getNbTachesRéalisées() > j.getNbTachesPrévues()) {
                sum++;
            }
        }
        return (sum);
    }

    public void consulterPlanning() {
        consulterBadgesPlanning();
        Iterator<Journée> iterator = journéesPlanifiées.iterator();
        int sum = 0;
        while (iterator.hasNext()) {
            Journée j = iterator.next();
            sum += j.getNbTachesRéalisées();
        }
        System.out.println("Nombre de taches réalisées dans le planning: " + sum);
    }
}
