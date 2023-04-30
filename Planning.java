import java.time.LocalDate;
import java.util.* ;
public class Planning {
    public Planning(LocalDate dateDébut, LocalDate dateFin, List<Journée> journéesPlanifiées) {
        this.dateDébut = dateDébut;
        this.dateFin = dateFin;
        this.journéesPlanifiées = journéesPlanifiées;
    }
    private LocalDate dateDébut ; 
    private LocalDate dateFin ;
    private List<Journée> journéesPlanifiées ;
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
    public List<Journée> getJournéesPlanifiées() {
        return journéesPlanifiées;
    }
    public void setJournéesPlanifiées(List<Journée> journéesPlanifiées) {
        this.journéesPlanifiées = journéesPlanifiées;
    }
    @Override
    public String toString() {
        return "Planning [dateDébut=" + dateDébut + ", dateFin=" + dateFin + ", journéesPlanifiées="
                + journéesPlanifiées + "]";
    }
}
