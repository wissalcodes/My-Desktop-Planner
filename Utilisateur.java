import java.util.*;
import java.awt.Color;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class Utilisateur {
    private String pseudo;
    private int nbMinTaches = 1 ; 
    private List<Planning> historiquePlannings; 
    protected Set<Catégorie> listeCatégories = new HashSet<>();
    protected List<Tache> listeTachesUnscheduled = new ArrayList<>();
    private static int duréeMinimale = 30 ;     //durée minimale de 30 minutes, sera vérifié lors de la décomposition d'un créneau
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
    private Calendrier calendrierPerso = new Calendrier();
    private List<Catégorie> listCatégories ; 

    public Utilisateur(String pseudo) {
        this.pseudo = pseudo;
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
    
 public Planning fixerPériodePlanning() throws DateDébutException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Date de début du planning (yyyy-mm-dd): ");
        LocalDate startDate = LocalDate.now();
        startDate = LocalDate.parse(scanner.nextLine());  
        System.out.print("Date de fin du planning (yyyy-mm-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        TreeSet<Journée> journéesPlanifiées = new TreeSet<>();
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
                LocalTime heureDebut = LocalTime.parse(times[0], DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime heureFin = LocalTime.parse(times[1], DateTimeFormatter.ofPattern("HH:mm"));
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
                    LocalTime heureDebut = LocalTime.parse(times[0], DateTimeFormatter.ofPattern("HH:mm"));
                    LocalTime heureFin = LocalTime.parse(times[1], DateTimeFormatter.ofPattern("HH:mm"));
                    Creneau creneau = new Creneau(heureDebut, heureFin);               
                    journee.getListCreneauxLibres().add(creneau);
                
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


    //Programmation d'un ensemble de taches automatiquement / proposition du système
    public Planning planifierEnsembleTaches(Planning planning, ArrayList<Tache> listTaches) 
    {
        //le planning passé en argument correspond à celui défini par la période de l'utilisateur
        // 1.Ordonner l'ensemble des taches selon les critères
        listTaches = listTachesOrdonnées(listTaches);
        // 2. parcourir les journées correspondante dans le calendrier (ou dans le planning, puisqu'on initialise le planning depuis le calendrier)
        LocalDate dateJournée = planning.getDateDébut();
        Journée journée = calendrierPerso.getJournéeByDate(dateJournée);
        //tant qu'il reste des taches à planifier dans le planning,
        
        Iterator iteratorTaches = listTaches.iterator(); //itérer les journées 
        while (iteratorTaches.hasNext()) {
            Tache tache = (Tache) iteratorTaches.next();        
            Iterator<Journée> iteratorJournéesPlanning = planning.getJournéesPlanifiées().iterator();
            System.out.println(tache);
            while (iteratorJournéesPlanning.hasNext() && tache.getEtat() == EtatTache.UNSCHEDULED) {
                 journée = iteratorJournéesPlanning.next();
                TreeSet<Creneau> listCreneauxLibres = journée.getListCreneauxLibres();
                Iterator<Creneau> iteratorCréneauxLibres = listCreneauxLibres.iterator();
        
                while (iteratorCréneauxLibres.hasNext()) {
                    Creneau creneauLibre = iteratorCréneauxLibres.next();
        
                    if (tache.getDeadlineDate().isAfter(journée.getDate()) ||
                            (tache.getDeadlineDate().isEqual(journée.getDate()) && tache.getDeadlineHeure().isAfter(creneauLibre.getHeureFin()))) {
        
                        LocalTime début = creneauLibre.getHeureDebut();
                        LocalTime fin = creneauLibre.getHeureFin();
                        Duration duration = Duration.between(début, fin);
                 
                        long durationMinutes = duration.toMinutes();    //durationMinutes est la durée maximale du créneau
                        if (durationMinutes >= tache.getDurée() && tache instanceof TacheSimple) {
                         // On planifie la tache
                         //Tester si décomposer le créneau ou bien l'allouer entièrement à la tache
                    if (durationMinutes - tache.getDurée() > duréeMinimale ) { //Il y'a décomposition
                        LocalTime creneau1fin = début.plusMinutes(tache.getDurée());
                        LocalTime creneau2Début = début.plusMinutes(tache.getDurée());
                        Creneau creneau1 = new Creneau(début, creneau1fin);
                        Creneau creneau2 = new Creneau(creneau2Début, fin);
                        
                        CreneauTache creneauTache = new CreneauTache(creneau1, (TacheSimple) tache);
                        journée.getListCreneauxTaches().add(creneauTache);
                        
                        //MàJ des créneaux libres de la journée
                        journée.getListCreneauxLibres().remove(creneauLibre);
                        journée.getListCreneauxLibres().add(creneau2);
                        planning.getJournéesPlanifiées().add(journée);
                    } else {
                        CreneauTache creneauTache = new CreneauTache(creneauLibre, (TacheSimple) tache);
                        journée.getListCreneauxTaches().add(creneauTache);
                        //MàJ des créneaux libres de la journée
                        journée.getListCreneauxLibres().remove(creneauLibre);
                        planning.getJournéesPlanifiées().add(journée);
                    }       
                    //Marquer la tache comme non réalisée
                    tache.setEtat(EtatTache.NOTREALIZED);
                    iteratorTaches.remove();// Supprimer la tache depuis la liste
                            break; // Quitter la boucle une fois la tache est programmée
                        } 
                        else{  
                            //la durée de créneau est inférieure à celle de la tache à programmer ou la tache est décomposable
                            if(tache instanceof TacheDécomposable ){ //si la tache est décomposable
                               if(tache.getDurée()>durationMinutes){   
                                   TacheDécomposable tacheDecomposable = (TacheDécomposable) tache;
                                   // on extrait une sous tache de la meme durée que le créneau
                                   TacheSimple sousTache = new TacheSimple(tache.catégorie, tache.getDeadlineDate(), tache.deadlineHeure, tache.getPriorité(), durationMinutes,tache.getNom()+(tacheDecomposable.getListeSousTaches().size()+1),0); 
                                   //MAJ de son état
                                   sousTache.setEtat(EtatTache.NOTREALIZED);
                                   //ajouter la sous-tache à la liste des sous taches de la tache décomposable
                                   tacheDecomposable.ajouterSousTache(sousTache);
                                   tacheDecomposable.setDurée(tacheDecomposable.getDurée()-sousTache.getDurée());
                                   CreneauTache creneauTache = new CreneauTache(creneauLibre, sousTache);
                                   //Programmation de la sous-tache
                                   journée.getListCreneauxTaches().add(creneauTache);
                                  System.out.println("Hada l creneau li programmit: " + creneauTache);
                                  //MàJ des créneaux libres de la journée
                                  iteratorCréneauxLibres.remove();
                                  //la tache est considérée
                                   tache = tacheDecomposable;
                                   System.out.println("Tache après prog ss : "+tache);
                                  //    tache.setEtat(EtatTache.NOTREALIZED);
                               } 
                               else{ 
                                TacheDécomposable tacheDecomposable = (TacheDécomposable) tache;
                                System.out.println("im last sous tache, this me: " + tacheDecomposable);

                                // on extrait une sous tache de la meme durée que le créneau
                                TacheSimple sousTache = new TacheSimple(tache.catégorie, tache.getDeadlineDate(), tache.deadlineHeure, tache.getPriorité(), tache.getDurée(),tache.getNom()+(tacheDecomposable.getListeSousTaches().size()+1),0); 
                                sousTache.setEtat(EtatTache.NOTREALIZED);
                                CreneauTache creneauTache = new CreneauTache(creneauLibre, sousTache);
                                //Programmation de la sous-tache
                                 tache.setEtat(EtatTache.NOTREALIZED);
                                journée.getListCreneauxTaches().add(creneauTache);
                                planning.getJournéesPlanifiées().add(journée);
                                iteratorCréneauxLibres.remove();
                               // iteratorTaches.next();
                                break;
                               }
                             
                            }
                        }
                    } else {
                        System.out.println("Le deadline de cette tache a été dépassé");
                    }
                }
                if (tache.getEtat() == EtatTache.NOTREALIZED) {
                    break; // Quitter la boucle si la tache a été programmée
                }
            }
        }
        //Sauvegarder les taches non programmées dans l'utilisateur
        ArrayList<Tache> listeTachesUnscheduled = new ArrayList<>();
        for (Tache tache : listTaches) {
            if (tache.getEtat() == EtatTache.UNSCHEDULED) {
                listeTachesUnscheduled.add(tache);
            }
        }
        this.listeTachesUnscheduled = listeTachesUnscheduled ;
        System.out.println("\n\nListe des taches non planifiés: " + this.listeTachesUnscheduled);

      System.out.println("\n\nPlanning proposé "+ planning);
        // afficher et retourner le planning proposé par le système
        return(planning);
    }
    //Méthode qui ordonne l'ensemble de taches selon le deadlineDate, puis deadlineHeure, puis par la priorité de la tache
    public ArrayList<Tache> listTachesOrdonnées(ArrayList<Tache> listTaches) {
        // Sort the tasks by deadline date, deadline time, and priority
        listTaches.sort(Comparator.comparing(Tache::getDeadlineDate)
                .thenComparing(Tache::getDeadlineHeure)
                .thenComparing(Tache::getPriorité));
      return listTaches;
    }

    public void planifier(){
        System.out.println("Choisissez une option:\n1. Planification manuelle d'une tache\n2. Planification d'un ensemble de taches\n");
        Scanner scanner1 = new Scanner(System.in);
        //dans la planification d'un ensemble de taches, on a automatique et manuel.
        int option = Integer.parseInt(scanner1.nextLine());
        if (option == 1){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrez le nom de la tache:");
            String nomTache = scanner.nextLine();
            
            System.out.println("Entrez la durée de la tache (en minutes):");
            int dureeTache = Integer.parseInt(scanner.nextLine());
            
            System.out.println("Entrez la priorité de la tache (HIGH, MEDIUM, ou LOW):");
            String prioriteTache = scanner.nextLine();
            Priorité priorite = Priorité.valueOf(prioriteTache);
            
            System.out.println("Entrez la date limite de la tache (format: aaaa-mm-jj):");
            String dateLimiteString = scanner.nextLine();
            LocalDate dateLimite = LocalDate.parse(dateLimiteString);
            
            System.out.println("Entrez l'heure limite de la tache (format: hh:mm:ss):");
            String heureLimiteString = scanner.nextLine();
            LocalTime heureLimite = LocalTime.parse(heureLimiteString);
            
            System.out.println("Entrez la catégorie de la tache (PERSONNELLE, PROFESSIONNELLE, ou AUTRE):");
            String categorieTache = scanner.nextLine();
            Catégorie categorie = new Catégorie(categorieTache,new Color(0,0,0));
            TacheSimple tache = new TacheSimple(categorie, dateLimite, heureLimite, priorite, dureeTache, nomTache,0);
            System.out.println(tache);
            //demander le créneau:
            System.out.println("Introduisez la journée yyyy-mm-dd: ");
            String dateTacheString = scanner.nextLine();
            LocalDate dateTache = LocalDate.parse(dateTacheString);
            System.out.println("Introduisez le créneau souhaité: HH:mm-HH:mm");
            String creneauString = scanner.nextLine();
            String[] creneauTimes = creneauString.split("-");
            LocalTime heureDebut = LocalTime.parse(creneauTimes[0]);
            LocalTime heureFin = LocalTime.now();
          
                heureFin = LocalTime.parse(creneauTimes[1]);
         
            Creneau creneau = new Creneau(heureDebut, heureFin);
            planifierTacheManuelle(dateTache, creneau, tache);
        }
        if (option == 2){
            Planning planning = new Planning();
            System.out.println("inside option 2");
            try {
               planning = fixerCréneauxLibres(this.fixerPériodePlanning());
               ArrayList<Tache> listTaches = new ArrayList<>();
               Catégorie c1 = new Catégorie("Studies",new Color(0,0,0));
               Catégorie c2 = new Catégorie("cooking",new Color(255,255,255));
               LocalDate date1 = LocalDate.parse("2023-06-20");
               LocalTime time1 = LocalTime.now();
               LocalDate date2 = LocalDate.parse("2023-05-29");
               LocalTime time2 = LocalTime.parse("22:00");
               LocalDate date3 = LocalDate.parse("2023-05-28");
               LocalTime time3 = LocalTime.parse("18:00");
               LocalDate date4 = LocalDate.parse("2023-05-28");
               LocalTime time4 = LocalTime.parse("17:00");
               TacheSimple tache1 = new TacheSimple(c1,date1,time1,Priorité.LOW,50,"Tache1",0);
               TacheSimple tache2 = new TacheSimple(c2,date2,time2,Priorité.HIGH,100,"Tache2",0);
               TacheSimple tache3 = new TacheSimple(c2,date3,time3,Priorité.HIGH,100,"Tache3",0);
               TacheSimple tache4 = new TacheSimple(c2,date4,time4,Priorité.MEDIUM,100,"Tache4",0);
               TacheDécomposable tache5 = new TacheDécomposable(c2,LocalDate.parse("2023-05-30"),time3,Priorité.LOW,200,"Tache5");
               listTaches.add(tache1);
               listTaches.add(tache2);
               listTaches.add(tache3);
               listTaches.add(tache4);
               listTaches.add(tache5);    
               planifierEnsembleTaches(planning, listTaches);
                //Accepter le planning / refuser le planning / modifier le planning
                //getCalendrierPerso().journéesCalendrier.addAll(planning.getJournéesPlanifiées());
                //proposition du système.
            }
            catch(DateDébutException e){
                System.out.println("> Erreur: la date de début planning est antérieure à la date du jour!");
            }
        }
        }
}