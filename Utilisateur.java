import java.util.*;
import java.time.*;
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
    
    public Planning fixerPériodePlanning() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Date de début du planning (yyyy-mm-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        try{
        if(startDate.isBefore(LocalDate.now())){
            throw new DateDébutException();
        }
    }
    catch(DateDébutException e){
        System.out.println("> Erreur: la date de début planning est antérieure à la date du jour!");
    }
        System.out.print("Date de fin du planning (yyyy-mm-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        List<Journée> journéesPlanifiées = new ArrayList<>();
        // Itérer sur l'ensemble des journées du calendrier personnel de l'utilisateur authentifié
        for (Journée journée : getCalendrierPerso().getJournéesCalendrier() ) {
            LocalDate date = journée.getDate();
            // Vérifier si la journée appartient à l'intervalle introduit par l'utilisateur
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                journéesPlanifiées.add(journée);
            }
        }
        // Créer le nouveau planning
        Planning planning = new Planning(startDate, endDate, journéesPlanifiées);
        return planning;
    }
    public Planning fixerCréneauxLibres(Planning planning) {
        Scanner scanner = new Scanner(System.in);
        for (Journée journée : planning.getJournéesPlanifiées()) {
            System.out.println("Les créneaux libres pour la journée: " + journée.getDate() + ", sous le format HH-HH,HH-HH");
            String input = scanner.nextLine();
            String[] slots = input.split(",");
                for (String slot : slots) {
                String[] times = slot.split("-");
                int heureDébut = Integer.parseInt(times[0]);
                int heureFin = Integer.parseInt(times[1]);
                Creneau creneau = new Creneau(LocalTime.of(heureDébut, 0), LocalTime.of(heureFin, 0));
                journée.getListCreneauxLibres().add(creneau);
            }
        }
        return planning;
    }
    public Journée planifierTacheManuelle(LocalDate tacheDate, Creneau creneau, Tache tache){
        Journée journée = calendrierPerso.getJournéeByDate(tacheDate);
        Iterator iterator = journée.getListCreneauxLibres().iterator();
        while(iterator.hasNext()){
            Creneau creneau2 = (Creneau) iterator.next();
            if (creneau2.contientCreneau(creneau)){
              Set<Creneau> resultatDécompositionCreneau =  creneau2.decompositionCreneau(creneau);
            }

        }
    }

}