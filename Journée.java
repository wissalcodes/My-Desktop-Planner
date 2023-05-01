import java.time.LocalDate;
import java.util.* ;
public class Journée implements Comparable<Journée> {
    public Journée(LocalDate date) {
        this.date = date;
    }
    private LocalDate date; //Date de la journée sous le format aaaa-mm-jj
    private Set<CreneauTache> listCreneauxTaches = new HashSet<>();  //Les couples créneaux taches sont uniques, il ne peut pas y'avoir le meme couple. c'est plus utile quand on aura de remplacer le couple.
    private Badge badgeJournalier;
    private int nbTachesPrévues ;
    private int nbTachesRéalisées;
    private Set<Creneau> listCreneauxLibres= new HashSet<>(); 
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Set<CreneauTache> getListCreneauxTaches() {
        return listCreneauxTaches;
    }
    public void setListCreneauxTaches(Set<CreneauTache> listCreneauxTaches) {
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
    public Set<Creneau> getListCreneauxLibres() {
        return listCreneauxLibres;
    }
    public void setListCreneauxLibres(Set<Creneau> listCreneauxLibres) {
        this.listCreneauxLibres = listCreneauxLibres;
    }
    @Override
    public String toString() {
        return "\nJournée [date=" + date + "\n Créneaux libres= "+ listCreneauxLibres;
    }
    @Override
    public int compareTo(Journée o) {
        return this.date.compareTo(o.date);
    }
    
}
