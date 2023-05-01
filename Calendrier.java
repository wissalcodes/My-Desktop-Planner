import java.util.* ;
import java.time.* ; 

public class Calendrier {
    TreeSet<Journée> journéesCalendrier = new TreeSet<>(); 

    public void afficherCalendrier() {
        System.out.println(journéesCalendrier);
    }
    
    public TreeSet<Journée> getJournéesCalendrier() {
        return journéesCalendrier;
    }

    public void setJournéesCalendrier(TreeSet<Journée> journéesCalendrier) {
        this.journéesCalendrier = journéesCalendrier;
    }

    @Override
    public String toString() {
        return "\n\t\t\t\t\t***Calendrier***\n" + journéesCalendrier;
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
    }

    public Journée getJournéeByDate(LocalDate date) {
        for (Journée journée : journéesCalendrier) {
            if (journée.getDate().equals(date)) {
                return journée;
            }
        }
        return null;
    }
}