import java.time.LocalDate;
import java.util.* ;
public class Planning {
    public Planning(LocalDate dateDébut, LocalDate dateFin, TreeSet<Journée> journéesPlanifiées) {
        this.dateDébut = dateDébut;
        this.dateFin = dateFin;
        this.journéesPlanifiées = journéesPlanifiées;
    }
    public Planning(){
    }
    // public Journée getJournéeByDate(LocalDate date) {
    //     for (Journée journée : journéesCalendrier) {
    //         if (journée.getDate().equals(date)) {
    //             return journée;
    //         }
    //     }
    //     return null;
    // }
    private LocalDate dateDébut ; 
    private LocalDate dateFin ;
    private TreeSet<Journée> journéesPlanifiées ;
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
        return "Planning [dateDébut=" + dateDébut + ", dateFin=" + dateFin + ", journéesPlanifiées="
                + journéesPlanifiées + "]";
    }
}
