import java.util.* ;
import java.time.* ; 
public class Calendrier {
    Set <Journée> journéesCalendrier= new HashSet<>(); 
    public void afficherCalendrier(){
    }
    public Set<Journée> getJournéesCalendrier() {
        return journéesCalendrier;
    }
    public void setJournéesCalendrier(Set<Journée> journéesCalendrier) {
        this.journéesCalendrier = journéesCalendrier;
    }
    @Override
    public String toString() {
        return "\n\t\t\t\t\t***Calendrier***\n" + journéesCalendrier + "]";
    }
    LocalDate currentDate = LocalDate.now();
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();
    int currentDay = currentDate.getDayOfMonth();
    
    public Calendrier(){
        int day = 1;
        int month = currentMonth;
        int year = currentYear;
        List<Journée> sortedJournées = new ArrayList<>();
while (month <= currentMonth + 1) {
    LocalDate date = LocalDate.of(year, month, day);
    sortedJournées.add(new Journée(date));
    if (day == date.lengthOfMonth()) {
        day = 1;
        month++;
    } else {
        day++;
    }
}
Collections.sort(sortedJournées);
journéesCalendrier = new LinkedHashSet<>(sortedJournées);
}

}
