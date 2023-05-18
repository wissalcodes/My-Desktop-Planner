package Noyau ;


import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class Utilisateur {
    private String pseudo;
    private int nbMinTaches = 1 ; 
    private List<Planning> historiquePlannings; 
    protected Set<Catégorie> listeCatégories = new HashSet<>();
    public Set<Catégorie> getListeCatégories() {
        return listeCatégories;
    }

    public void setListeCatégories(Set<Catégorie> listeCatégories) {
        this.listeCatégories = listeCatégories;
    }

    public List<Planning> getHistoriquePlannings() {
        return historiquePlannings;
    }
    
    public void setHistoriquePlannings(List<Planning> historiquePlannings) {
        this.historiquePlannings = historiquePlannings;
    }

    private List<Projet> historiqueProjets;  
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
        return historiqueProjets;
    }

    public void setHitoriqueProjets(List<Projet> hitoriqueProjets) {
        this.historiqueProjets = hitoriqueProjets;
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
        System.out.println("Choisissez une option:\n1. Définir des créneaux libres constants pour tout le planning.\n2. Définir des créneaux libres personnalisés par journée");
        int option = Integer.parseInt(scanner.nextLine());
    
        if (option == 1) {
            System.out.println("Les créneaux libres pour tout le planning, sous le format HH-HH,HH-HH");
            String input = scanner.nextLine();
            String[] slots = input.split(",");
            for (String slot : slots) {
                String[] times = slot.split("-");
                LocalTime heureDebut = LocalTime.parse(times[0], DateTimeFormatter.ofPattern("HH:mm:ss"));
                LocalTime heureFin = LocalTime.parse(times[1], DateTimeFormatter.ofPattern("HH:mm:ss"));
                Creneau creneau = new Creneau(heureDebut, heureFin);   
                 for (Journée journee : planning.getJournéesPlanifiées()) {
                    journee.getListCreneauxLibres().add(creneau);
                }
            }
        } else if (option == 2) {
            for (Journée journee : planning.getJournéesPlanifiées()) {
                System.out.println("Les créneaux libres pour la journée: " + journee.getDate() + ", sous le format HH-HH,HH-HH");
                String input = scanner.nextLine();
                String[] slots = input.split(",");
                for (String slot : slots) {
                    String[] times = slot.split("-");
                    LocalTime heureDebut = LocalTime.parse(times[0], DateTimeFormatter.ofPattern("HH:mm:ss"));
                    LocalTime heureFin = LocalTime.parse(times[1], DateTimeFormatter.ofPattern("HH:mm:ss"));
                    Creneau creneau = new Creneau(heureDebut, heureFin);                  journee.getListCreneauxLibres().add(creneau);
                }
            }
        } else {
            System.out.println("Option invalide.");
        }
        return planning;
    }
    public Journée planifierTacheManuelle(LocalDate tacheDate, Creneau creneau, TacheSimple tache){
        Journée journée = calendrierPerso.getJournéeByDate(tacheDate);
       //cas 1 : ce créneau ne figure pas dans les créneaux libres ni dans les créneax déjas occupés par des taches.
        //c'est le cas trvial, on crée le couple CreneauTache et on l'ajoute à la liste des creaneauTaches de la journée
        if (journée.getListCreneauxLibres().contains(creneau)==false){
           System.out.println("Souhaitez vous bloquer ce créneau pour cette tache?"); //le créneau ne sera pas touchée lors de la replanification
            
           CreneauTache creneauTache = new CreneauTache(creneau, tache);
            journée.getListCreneauxTaches().add(creneauTache);
        }

        else{
            //2 cas
            // si la tache est programmée dans un créneau qui était libre, il ne devient plus libre (du moins la partie qui était libre)
            Iterator iterator = journée.getListCreneauxLibres().iterator();
            while(iterator.hasNext()){
                Creneau creneau2 = (Creneau) iterator.next();
                if (creneau2.contientCreneau(creneau)){
                  Set<Creneau> resultatDécompositionCreneau =  creneau2.decompositionCreneau(creneau);
                }
    
            }
        }
        calendrierPerso.journéesCalendrier.add(journée);
        System.out.println("Calendrier apres planification manuelle d'une tache");
        System.out.println(calendrierPerso);
        return(journée);
    }

    @Override
    public String toString() {
        return "Utilisateur [pseudo=" + pseudo + ", nbMinTaches=" + nbMinTaches + ", historiquePlannings="
                + historiquePlannings + ", listeCatégories=" + listeCatégories + ", historiqueProjets="
                + historiqueProjets + ", calendrierPerso=" + calendrierPerso + ", listCatégories=" + listCatégories
                + "]";
    }
    //Dans le cas de la replanification, ou bien si on essaie de programmer un

}