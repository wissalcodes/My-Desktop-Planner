package Noyau;

import Control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.*;

import javax.swing.event.ListDataEvent;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Utilisateur implements Serializable {
    private MyDesktopPlanner planner;
    private String pseudo;
    private int nbMinTaches = 1;
    private Set<Planning> historiquePlannings = new TreeSet<>();
    protected Set<Catégorie> listeCatégories = new HashSet<>();
    protected List<Tache> listeTachesUnscheduled = new ArrayList<>();
    private static int duréeMinimale = 30;
    //
    private int nbTachesRéalisées = 0 ;
 public void setNbTacheRealisé(int i){
    nbTachesRéalisées = i ;
 }
 
 public int getNbTachesRéalisées(){
    return this.nbTachesRéalisées ;
 }
    
   // private transient PlaningSettings controleurSettings ;
 private LocalDate startDate ;
 private LocalDate dateLimite ;
 public void setStartDay( LocalDate startDate){
    this.startDate = startDate ;
 }
 public void setDateLimite( LocalDate dateLimite){
    this.dateLimite = dateLimite ;
 }
    /*public void setControeurSetting(PlaningSettings controleurSettings){
        this.controleurSettings = controleurSettings ;
    }*/

    public MyDesktopPlanner getPlanner() {
        return planner;
    }

    // durée minimale de 30 minutes, sera vérifié lors de la décomposition d'un
    // créneau
    public Set<Catégorie> getListeCatégories() {
        return listeCatégories;
    }

    public void setListeCatégories(Set<Catégorie> listeCatégories) {
        this.listeCatégories = listeCatégories;
        planner.updateUser(this);

    }

    public Set<Planning> getHistoriquePlannings() {
        return historiquePlannings;
    }

    public void setHistoriquePlannings(Set<Planning> historiquePlannings) {
        this.historiquePlannings = historiquePlannings;
        planner.updateUser(this);

    }

    private List<Projet> historiqueProjets = new ArrayList<>();
    private Calendrier calendrierPerso = new Calendrier();
    private List<Catégorie> listCatégories;

    public Utilisateur(String pseudo) {
        this.pseudo = pseudo;
        planner = new MyDesktopPlanner();
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
        planner.updateUser(this);

    }

    public int getNbMinTaches() {
        return nbMinTaches;
    }

    public void setNbMinTaches(int nbMinTaches) {
        this.nbMinTaches = nbMinTaches;
        planner.updateUser(this);

    }

    public List<Projet> getHitoriqueProjets() {
        return historiqueProjets;
    }

    public void setHitoriqueProjets(List<Projet> hitoriqueProjets) {
        this.historiqueProjets = hitoriqueProjets;
        planner.updateUser(this);
    }

    public List<Catégorie> getListCatégories() {
        return listCatégories;
    }

    public void setListCatégories(List<Catégorie> listCatégories) {
        this.listCatégories = listCatégories;
        planner.updateUser(this);
    }

    /*
     * public void planifierProjetManuel(Projet projet) {
     * ArrayList<Tache> listeTachesProjet = new ArrayList<>(null);
     * listeTachesProjet = projet.getListeTaches();
     * Scanner scanner = new Scanner(System.in);
     * Iterator iterator = listeTachesProjet.iterator();
     * while (iterator.hasNext()) {
     * // à remplacer par le controlleur de la planification d'une seule tache
     * manuelle
     * TacheSimple tache = (TacheSimple) iterator.next();
     * System.out.println("Introduisez la journée yyyy-mm-dd: ");
     * String dateTacheString = scanner.nextLine();
     * LocalDate dateTache = LocalDate.parse(dateTacheString);
     * System.out.println("Introduisez l'heure début du créneau souhaité: HH:mm");
     * String creneauString = scanner.nextLine();
     * LocalTime heureDebut = LocalTime.parse(creneauString);
     * LocalTime heureFin = heureDebut.plusMinutes(tache.getDurée());
     * Creneau creneau = new Creneau(heureDebut, heureFin);
     * planifierTacheManuelle(dateTache, creneau, tache, );
     * }
     * // planner.updateUser(this);
     * }
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Utilisateur other = (Utilisateur) obj;
        return Objects.equals(pseudo, other.pseudo);
    }

    public Planning fixerPériodePlanning( ) throws DateDébutException {
       // Scanner scanner = new Scanner(System.in);
        //System.out.print("Date de début du planning (yyyy-mm-dd): ");
        
      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/planningSettings.fxml"));
       // Parent root = loader.load() ;
       // PlaningSettings controleur = loader.getController() ;
      //  LocalDate startDate = LocalDate.now();
        //LocalDate startDate = controleurSettings.getDateDébut() ;

        if (this.startDate.isBefore(LocalDate.now()))
            throw new DateDébutException();

        TreeSet<Journée> journéesPlanifiées = new TreeSet<>();
        //System.out.print("Date de fin du planning (yyyy-mm-dd): ");
       // LocalDate dateLimite = controleurSettings.getDateFin() ;

        // Itérer sur l'ensemble des journées du calendrier personnel de l'utilisateur
        // authentifié
        for (Journée journée : getCalendrierPerso().getJournéesCalendrier()) {
            LocalDate date = journée.getDate();
            // Vérifier si la journée appartient à l'intervalle introduit par l'utilisateur
            if (!date.isBefore(startDate) && !date.isAfter(dateLimite)) {
                journéesPlanifiées.add(journée);
            }
        }
        // Créer le nouveau planning
        Planning planning = new Planning(startDate, dateLimite, journéesPlanifiées);
        planner.updateUser(this);
        return planning;
    }

    public Planning fixerCréneauxLibres(Planning planning) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Choisissez une option:\n1. Définir des créneaux libres constants pour tout le planning.\n2. Définir des créneaux libres personnalisés par journée");
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
                System.out.println(
                        "Les créneaux libres pour la journée: " + journee.getDate() + ", sous le format HH-HH,HH-HH");
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
        planner.updateUser(this);
        return planning;
    }

    public Journée planifierTacheManuelle(LocalDate tacheDate, Creneau creneau, TacheSimple tache, boolean bloqué) {
        Scanner scanner1 = new Scanner(System.in);
        Journée journée = calendrierPerso.getJournéeByDate(tacheDate);
        // cas 1 : ce créneau ne figure pas dans les créneaux libres
        if (journée.getListCreneauxLibres().contains(creneau) == false) {
            // S'il est occupé par une autre tache
            Iterator iterator = journée.getListCreneauxTaches().iterator();
            while (iterator.hasNext()) {
                CreneauTache creneauTache = (CreneauTache) iterator.next();
                if (hasIntersection(creneauTache.getCreneau(), creneau)) {

                    PlanificationManuelleController.impossiblePlanifier();

                    /*
                     * //System.out.println(
                     * "Impossible de programmer la tache dans ce créneau, car il est réservé pour une deuxième tache.\n1. L'ajouter à la liste des taches non-planifiées.\n2. Planifier dans un autre créneau"
                     * );
                     * int option = Integer.parseInt(scanner1.nextLine());
                     * if (option == 1) {
                     * listeTachesUnscheduled.add(tache);
                     * break;
                     * } else if (option == 2) {
                     * Scanner scanner = new Scanner(System.in);
                     * System.out.println("Introduisez la journée yyyy-mm-dd: ");
                     * String dateTacheString = scanner.nextLine();
                     * LocalDate dateTache = LocalDate.parse(dateTacheString);
                     * System.out.println("Introduisez l'heure début du créneau souhaité: HH:mm");
                     * String creneauString = scanner1.nextLine();
                     * LocalTime heureDebut = LocalTime.parse(creneauString);
                     * LocalTime heureFin = heureDebut.plusMinutes(tache.getDurée());
                     * creneau = new Creneau(heureDebut, heureFin);
                     * planifierTacheManuelle(tacheDate, creneau, tache);
                     * break;
                     * }
                     */
                }
            }
            /*
             * System.out.
             * println("Souhaitez vous bloquer ce créneau pour cette tache? (1/0)"); // le
             * créneau ne sera
             * // pas // touchée lors
             * // de la
             * // replanification // //
             * // replanification
             */
            CreneauTache creneauTache = new CreneauTache(creneau, tache);
            if (bloqué) {
                creneauTache.setEstBloqué(true);
            }
            journée.getListCreneauxTaches().add(creneauTache);
            System.out.println("Tache programmée avec succès.");
            calendrierPerso.journéesCalendrier.add(journée);
            // System.out.println(calendrierPerso);

        } else {
            // si la tache est programmée dans un créneau qui était libre, il faut mettre à
            // jour les créneaux libres
            Iterator iterator = journée.getListCreneauxLibres().iterator();
            while (iterator.hasNext()) {
                Creneau creneau2 = (Creneau) iterator.next();
                if (creneau2.contientCreneau(creneau)) { // Si le créneau spécifié pour la tache est inclu dans un
                                                         // créneau libre de la journée
                    TreeSet<Creneau> resultatDécompositionCreneau = creneau2.decompositionCreneau(creneau); // Décomposer
                                                                                                            // le
                                                                                                            // créneau
                                                                                                            // en
                                                                                                            // question
                    // Mise-à-jour des créneaux libres de la journée en question
                    journée.getListCreneauxLibres().addAll(resultatDécompositionCreneau);
                    CreneauTache creneauTache = new CreneauTache(creneau, tache);
                    journée.getListCreneauxTaches().add(creneauTache);
                    calendrierPerso.journéesCalendrier.add(journée);
                    // System.out.println(calendrierPerso);
                }
            }
        }
        planner.updateUser(this);

        return (journée);
    }

    @Override
    public String toString() {
        return "Utilisateur [pseudo=" + pseudo + ", nbMinTaches=" + nbMinTaches + ", historiquePlannings="
                + historiquePlannings + ", listeCatégories=" + listeCatégories + ", historiqueProjets="
                + historiqueProjets + ", calendrierPerso=" + calendrierPerso + ", listCatégories=" + listCatégories
                + "]";
    }

    // Programmation d'un ensemble de taches automatiquement / proposition du
    // système
    public Planning planifierEnsembleTaches(Planning planning, ArrayList<Tache> listTaches) {
        // le planning passé en argument correspond à celui défini par la période de
        // l'utilisateur
        // 1.Ordonner l'ensemble des taches selon les critères
        listTaches = listTachesOrdonnées(listTaches);
        LocalDate lastDateWithTask = planning.getDateDébut();

        // 2. parcourir les journées correspondante dans le calendrier (ou dans le
        // planning, puisqu'on initialise le planning depuis le calendrier)
        LocalDate dateJournée = planning.getDateDébut();
        Journée journée = calendrierPerso.getJournéeByDate(dateJournée);
        // tant qu'il reste des taches à planifier dans le planning,

        Iterator iteratorTaches = listTaches.iterator(); // itérer les journées
        while (iteratorTaches.hasNext()) {
            Tache tache = (Tache) iteratorTaches.next();
            Iterator<Journée> iteratorJournéesPlanning = planning.getJournéesPlanifiées().iterator();
            while (iteratorJournéesPlanning.hasNext() && tache.getEtat() == EtatTache.UNSCHEDULED) {
                journée = iteratorJournéesPlanning.next();
                TreeSet<Creneau> listCreneauxLibres = journée.getListCreneauxLibres();
                Iterator<Creneau> iteratorCréneauxLibres = listCreneauxLibres.iterator();

                while (iteratorCréneauxLibres.hasNext()) {
                    Creneau creneauLibre = iteratorCréneauxLibres.next();

                    if (tache.getDeadlineDate().isAfter(journée.getDate()) ||
                            (tache.getDeadlineDate().isEqual(journée.getDate())
                                    && tache.getDeadlineHeure().isAfter(creneauLibre.getHeureFin()))) {
                        LocalTime début = creneauLibre.getHeureDebut();
                        LocalTime fin = creneauLibre.getHeureFin();
                        Duration duration = Duration.between(début, fin);
                        long durationMinutes = duration.toMinutes(); // durationMinutes est la durée maximale du créneau
                        if (durationMinutes >= tache.getDurée() && tache instanceof TacheSimple) {
                            // On planifie la tache
                            // Tester si décomposer le créneau ou bien l'allouer entièrement à la tache
                            if (durationMinutes - tache.getDurée() > duréeMinimale) { // Il y'a décomposition
                                
                                LocalTime creneau1fin = début.plusMinutes(tache.getDurée());
                                LocalTime creneau2Début = début.plusMinutes(tache.getDurée());
                                Creneau creneau1 = new Creneau(début, creneau1fin);
                                Creneau creneau2 = new Creneau(creneau2Début, fin);

                                CreneauTache creneauTache = new CreneauTache(creneau1, (TacheSimple) tache);
                                journée.getListCreneauxTaches().add(creneauTache);
                                // MàJ des créneaux libres de la journée
                                journée.getListCreneauxLibres().remove(creneauLibre);
                                journée.getListCreneauxLibres().add(creneau2);
                                planning.getJournéesPlanifiées().add(journée);
                            } else {
                                CreneauTache creneauTache = new CreneauTache(creneauLibre, (TacheSimple) tache);
                                journée.getListCreneauxTaches().add(creneauTache);
                                // MàJ des créneaux libres de la journée
                                journée.getListCreneauxLibres().remove(creneauLibre);
                                planning.getJournéesPlanifiées().add(journée);
                            }
                            // Marquer la tache comme non réalisée
                            tache.setEtat(EtatTache.INPROGRESS);
                            iteratorTaches.remove();// Supprimer la tache depuis la liste
                            break; // Quitter la boucle une fois la tache est programmée
                        } else {
                            // la durée de créneau est inférieure à celle de la tache à programmer ou la
                            // tache est décomposable
                            if (tache instanceof TacheDécomposable) { // si la tache est décomposable
                                if (tache.getDurée() > durationMinutes) {
                                    TacheDécomposable tacheDecomposable = (TacheDécomposable) tache;
                                    // on extrait une sous tache de la meme durée que le créneau
                                    TacheSimple sousTache = new TacheSimple(tache.catégorie, tache.getDeadlineDate(),
                                            tache.deadlineHeure, tache.getPriorité(), durationMinutes,
                                            tache.getNom() + (tacheDecomposable.getListeSousTaches().size() + 1), 0);
                                    // MAJ de son état
                                    sousTache.setEtat(EtatTache.INPROGRESS);
                                    // ajouter la sous-tache à la liste des sous taches de la tache décomposable
                                    tacheDecomposable.ajouterSousTache(sousTache);
                                    tacheDecomposable.setDurée(tacheDecomposable.getDurée() - sousTache.getDurée());
                                    CreneauTache creneauTache = new CreneauTache(creneauLibre, sousTache);
                                    // Programmation de la sous-tache
                                    journée.getListCreneauxTaches().add(creneauTache);
                                    // MàJ des créneaux libres de la journée
                                    iteratorCréneauxLibres.remove();
                                    tache = tacheDecomposable;
                                } else {
                                    TacheDécomposable tacheDecomposable = (TacheDécomposable) tache;
                                    // on extrait une sous tache de la meme durée que le créneau
                                    TacheSimple sousTache = new TacheSimple(tache.catégorie, tache.getDeadlineDate(),
                                            tache.deadlineHeure, tache.getPriorité(), tache.getDurée(),
                                            tache.getNom() + (tacheDecomposable.getListeSousTaches().size() + 1), 0);
                                    sousTache.setEtat(EtatTache.INPROGRESS);
                                    CreneauTache creneauTache = new CreneauTache(creneauLibre, sousTache);
                                    // Programmation de la sous-tache
                                    tache.setEtat(EtatTache.INPROGRESS);
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
                if (tache.getEtat() == EtatTache.INPROGRESS) {
                    lastDateWithTask = journée.getDate();
                    break; // Quitter la boucle si la tache a été programmée
                }
            }
        }
        // Sauvegarder les taches non programmées dans l'utilisateur
        ArrayList<Tache> listeTachesUnscheduled = new ArrayList<>();
        for (Tache tache : listTaches) {
            if (tache.getEtat() == EtatTache.UNSCHEDULED) {
                listeTachesUnscheduled.add(tache);
            }
        }
       // Modification de la date de fin du planning
      /*   if (!listeTachesUnscheduled.isEmpty()) {
            //appel a etaler ou pas
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("\n\nListe des taches non planifiés: " + listeTachesUnscheduled);
            System.out.println(
                    "\nIl reste des taches qui n'ont pas pu etre planifiées.Souhaitez-vous étaler la période du planning jusqu'à la planification de toutes les taches? (1/0)");
            int option = Integer.parseInt(scanner1.nextLine());
            if (option == 0) {
                 //pas
                // Sauvegarder les taches avec l'état "UNSCHEDULED"
                this.listeTachesUnscheduled = listeTachesUnscheduled;
                planning.setDateFin(lastDateWithTask);
            } else {
                //etaler
                // Les planifier dans les jours qui suivent.
                // 1. Spécifier les créneaux libres des jours qui suivent
                TreeSet<Journée> journéesPlanifiées = new TreeSet<>();
                LocalDate dateLimite = lastDateWithTask.plusDays(3);
                // Itérer sur l'ensemble des journées du calendrier personnel de l'utilisateur
                // authentifié
                for (Journée j : getCalendrierPerso().getJournéesCalendrier()) {
                    LocalDate date = j.getDate();
                    // Vérifier si la journée appartient à l'intervalle introduit par l'utilisateur
                    if (!date.isBefore(lastDateWithTask.plusDays(1)) && !date.isAfter(dateLimite)) {
                        journéesPlanifiées.add(j);
                    }
                }
                // Créer le nouveau planning
                Planning planningSuite = new Planning(lastDateWithTask.plusDays(1), dateLimite, journéesPlanifiées);
                planifierEnsembleTaches(fixerCréneauxLibres(planningSuite), listeTachesUnscheduled);
                planning.getJournéesPlanifiées().addAll(planningSuite.getJournéesPlanifiées());
                listeTachesUnscheduled.clear();
            }
            // afficher et retourner le planning proposé par le système
            System.out.println("\n\nPlanning proposé " + planning);
        }*/
        planner.updateUser(this);
        return (planning);
    }

    // Méthode qui ordonne l'ensemble de taches selon le deadlineDate, puis
    // deadlineHeure, puis par la priorité de la tache
    public ArrayList<Tache> listTachesOrdonnées(ArrayList<Tache> listTaches) {
        // Sort the tasks by deadline date, deadline time, and priority
        listTaches.sort(Comparator.comparing(Tache::getDeadlineDate)
                .thenComparing(Tache::getDeadlineHeure)
                .thenComparing(Tache::getPriorité));
        return listTaches;
    }

    public void planifier() {
        System.out.println(
                "Choisissez une option:\n1. Planification manuelle d'une tache\n2. Planification d'un ensemble de taches\n3. Planification d'une tache avant une date limite\n4. Planifier un projet");
        Scanner scanner1 = new Scanner(System.in);
        // dans la planification d'un ensemble de taches, on a automatique et manuel.
        int option = Integer.parseInt(scanner1.nextLine());
        if (option == 1) {
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

            System.out.println("Entrez la catégorie de la tache (STUDIES, WORK, COOKING..):");
            String categorieTache = scanner.nextLine();
            Catégorie categorie = new Catégorie(categorieTache, new Color(0, 0, 0));
            TacheSimple tache = new TacheSimple(categorie, dateLimite, heureLimite, priorite, dureeTache, nomTache,
                    0);
            // demander le créneau:
            System.out.println("Introduisez la journée yyyy-mm-dd: ");
            String dateTacheString = scanner.nextLine();
            LocalDate dateTache = LocalDate.parse(dateTacheString);
            System.out.println("Introduisez l'heure début du créneau souhaité: HH:mm");
            String creneauString = scanner.nextLine();
            LocalTime heureDebut = LocalTime.parse(creneauString);
            LocalTime heureFin = heureDebut.plusMinutes(dureeTache);
            Creneau creneau = new Creneau(heureDebut, heureFin);
            planifierTacheManuelle(dateTache, creneau, tache, false);
        }
        if (option == 2) {
            Planning planning = new Planning();
            try {
                planning = this.fixerPériodePlanning();
            } catch (DateDébutException e) {
                System.out.println("> Erreur: la date de début planning est antérieure à la date du jour!");
                planifier();
            }
            ArrayList<Tache> listTaches = new ArrayList<>();
            planning = fixerCréneauxLibres(planning);
            /// EXEMPLE DE TEST

            Catégorie c1 = new Catégorie("Studies", new Color(0, 0, 0));
            Catégorie c2 = new Catégorie("cooking", new Color(255, 255, 255));
            LocalDate date1 = LocalDate.parse("2023-06-20");
            LocalTime time1 = LocalTime.now();
            LocalDate date2 = LocalDate.parse("2023-05-29");
            LocalTime time2 = LocalTime.parse("22:00");
            LocalDate date3 = LocalDate.parse("2023-05-28");
            LocalTime time3 = LocalTime.parse("18:00");
            LocalDate date4 = LocalDate.parse("2023-05-28");
            LocalTime time4 = LocalTime.parse("17:00");
            TacheSimple tache1 = new TacheSimple(c1, date1, time1, Priorité.LOW, 50, "Tache1", 0);
            TacheSimple tache2 = new TacheSimple(c2, date2, time2, Priorité.HIGH, 100, "Tache2", 0);
            TacheSimple tache4 = new TacheSimple(c2, date4, time4, Priorité.MEDIUM, 100, "Tache4", 0);
            TacheDécomposable tache5 = new TacheDécomposable(c2, LocalDate.parse("2023-05-30"), time3, Priorité.LOW,
                    200, "Tache5");
            listTaches.add(tache1);
            listTaches.add(tache2);
            listTaches.add(tache4);
            listTaches.add(tache5);
            planning = planifierEnsembleTaches(planning, listTaches);
            // Accepter le planning
            System.out.println("1. Accepter le planning\n2.Refuser le planning");
            option = Integer.parseInt(scanner1.nextLine());
            if (option == 1) { // Insérer le planning dans le calendrier
                calendrierPerso.getJournéesCalendrier().addAll(planning.getJournéesPlanifiées());
                historiquePlannings.add(planning);
                System.out.println("\n\n\n*** Calendrier de " + pseudo + "\n\n" + calendrierPerso);
            }
            if (option == 2) {
                System.out.println("Planning supprimé avec succès.");
            }
            // Not yet.
            // if(option ==3){
            // System.out.println("*****Modification du planning*****");
            // System.out.println("1. Permuter entre 2 taches\n2. Changer le créneau d'une
            // tache");
            // option = Integer.parseInt(scanner1.nextLine());
            // if(option==1){

            // }if(option==2){

            // }else{
            // System.out.println("choix invalide");
            // }

            // }
            // getCalendrierPerso().journéesCalendrier.addAll(planning.getJournéesPlanifiées());
            // proposition du système.

        }
        if (option == 3) { // Planification d'une tache avant une date limite
            TacheSimple tache3 = new TacheSimple(new Catégorie("kk", new Color(0, 0, 0)),
                    LocalDate.parse("2023-05-29"), LocalTime.parse("22:00"), Priorité.HIGH, 100, "AnaTacheSamta",
                    0);
            planifierTacheAvantDateLimite(tache3, LocalDate.parse("2023-05-21"));
        }
        if (option == 4) {
            ArrayList<Tache> listeTachesProjet = new ArrayList<>();

            System.out.println("\nIntroduisez les informations du projet:\n > Nom projet: ");
            Scanner scanner = new Scanner(System.in);
            String nomProjet = scanner.nextLine();

            System.out.println("> Description du projet: ");
            String description = scanner.nextLine();

            System.out.println("> Taches du projet (tapez stop pour quitter la saisie)");

            System.out.println("Entrez le nom de la tache:");
            String nomTache = scanner.nextLine();
            if (nomTache == "stop") {

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

                System.out.println("Entrez la catégorie de la tache (STUDIES, WORK, COOKING..):");
                String categorieTache = scanner.nextLine();
                Catégorie categorie = new Catégorie(categorieTache, new Color(0, 0, 0));
                TacheSimple tache = new TacheSimple(categorie, dateLimite, heureLimite, priorite, dureeTache, nomTache,
                        0);
                listeTachesProjet.add(tache);
            } else {
                Planning planning = new Planning();
                try {
                    planning = this.fixerPériodePlanning();
                } catch (DateDébutException e) {
                    System.out.println("> Erreur: la date de début planning est antérieure à la date du jour!");
                    planifier();
                }
                Projet projet = new Projet(nomProjet, description, listeTachesProjet);
                planifierProjet(planning, projet);
                historiqueProjets.add(projet);
            }
        }
        planner.updateUser(this);
    }

    // Planification automatique d'une tache
    void planifierTacheAvantDateLimite(Tache tache, LocalDate dateLimite) {
        TreeSet<Journée> journéesPlanifiées = new TreeSet<>();

        // A partir du calendrier, tirer tous les jours à partir du jour courant
        // Itérer sur l'ensemble des journées du calendrier personnel de l'utilisateur
        // authentifié
        for (Journée j : getCalendrierPerso().getJournéesCalendrier()) {
            LocalDate date = j.getDate();
            if (!date.isBefore(LocalDate.now().minusDays(1)) && !date.isAfter(dateLimite)) {
                journéesPlanifiées.add(j);
            }
        }
        // créer un planning depuis la journée courante jusqu'à la date limite
        Planning planning = new Planning(LocalDate.now(), dateLimite, journéesPlanifiées);
        Iterator<Journée> iteratorJournéesPlanning = planning.getJournéesPlanifiées().iterator();
        // Chercher s'il existe un créneau libre qui vérifie la priorité et le deadline
        // de la tache
        while (iteratorJournéesPlanning.hasNext() && tache.getEtat() == EtatTache.UNSCHEDULED) {
            Journée journée = iteratorJournéesPlanning.next();
            TreeSet<Creneau> listCreneauxLibres = journée.getListCreneauxLibres();
            Iterator<Creneau> iteratorCréneauxLibres = listCreneauxLibres.iterator();
            while (iteratorCréneauxLibres.hasNext()) {
                Creneau creneauLibre = iteratorCréneauxLibres.next();
                if (tache.getDeadlineDate().isAfter(journée.getDate()) ||
                        (tache.getDeadlineDate().isEqual(journée.getDate())
                                && tache.getDeadlineHeure().isAfter(creneauLibre.getHeureFin()))) {
                    LocalTime début = creneauLibre.getHeureDebut();
                    LocalTime fin = creneauLibre.getHeureFin();
                    Duration duration = Duration.between(début, fin);
                    long durationMinutes = duration.toMinutes(); // durationMinutes est la durée maximale du créneau
                    if (durationMinutes >= tache.getDurée() && tache instanceof TacheSimple) {
                        // On planifie la tache
                        // Tester si décomposer le créneau ou bien l'allouer entièrement à la tache
                        if (durationMinutes - tache.getDurée() > duréeMinimale) { // Il y'a décomposition
                            LocalTime creneau1fin = début.plusMinutes(tache.getDurée());
                            LocalTime creneau2Début = début.plusMinutes(tache.getDurée());
                            Creneau creneau1 = new Creneau(début, creneau1fin);
                            Creneau creneau2 = new Creneau(creneau2Début, fin);

                            CreneauTache creneauTache = new CreneauTache(creneau1, (TacheSimple) tache);
                            journée.getListCreneauxTaches().add(creneauTache);
                            // MàJ des créneaux libres de la journée
                            journée.getListCreneauxLibres().remove(creneauLibre);
                            journée.getListCreneauxLibres().add(creneau2);
                            planning.getJournéesPlanifiées().add(journée);
                        } else {
                            CreneauTache creneauTache = new CreneauTache(creneauLibre, (TacheSimple) tache);
                            journée.getListCreneauxTaches().add(creneauTache);
                            // MàJ des créneaux libres de la journée
                            journée.getListCreneauxLibres().remove(creneauLibre);
                            planning.getJournéesPlanifiées().add(journée);
                        }
                        // Marquer la tache comme non réalisée
                        tache.setEtat(EtatTache.INPROGRESS);

                        break; // Quitter la boucle une fois la tache est programmée
                    } else {
                        // la durée de créneau est inférieure à celle de la tache à programmer ou la
                        // tache est décomposable
                        if (tache instanceof TacheDécomposable) { // si la tache est décomposable
                            if (tache.getDurée() > durationMinutes) {
                                TacheDécomposable tacheDecomposable = (TacheDécomposable) tache;
                                // on extrait une sous tache de la meme durée que le créneau
                                TacheSimple sousTache = new TacheSimple(tache.catégorie, tache.getDeadlineDate(),
                                        tache.deadlineHeure, tache.getPriorité(), durationMinutes,
                                        tache.getNom() + (tacheDecomposable.getListeSousTaches().size() + 1), 0);
                                // MAJ de son état
                                sousTache.setEtat(EtatTache.INPROGRESS);
                                // ajouter la sous-tache à la liste des sous taches de la tache décomposable
                                tacheDecomposable.ajouterSousTache(sousTache);
                                tacheDecomposable.setDurée(tacheDecomposable.getDurée() - sousTache.getDurée());
                                CreneauTache creneauTache = new CreneauTache(creneauLibre, sousTache);
                                // Programmation de la sous-tache
                                journée.getListCreneauxTaches().add(creneauTache);
                                // MàJ des créneaux libres de la journée
                                iteratorCréneauxLibres.remove();
                                tache = tacheDecomposable;
                            } else {
                                TacheDécomposable tacheDecomposable = (TacheDécomposable) tache;
                                // on extrait une sous tache de la meme durée que le créneau
                                TacheSimple sousTache = new TacheSimple(tache.catégorie, tache.getDeadlineDate(),
                                        tache.deadlineHeure, tache.getPriorité(), tache.getDurée(),
                                        tache.getNom() + (tacheDecomposable.getListeSousTaches().size() + 1), 0);
                                sousTache.setEtat(EtatTache.INPROGRESS);
                                CreneauTache creneauTache = new CreneauTache(creneauLibre, sousTache);
                                // Programmation de la sous-tache
                                tache.setEtat(EtatTache.INPROGRESS);
                                journée.getListCreneauxTaches().add(creneauTache);
                                planning.getJournéesPlanifiées().add(journée);
                                iteratorCréneauxLibres.remove();
                                // iteratorTaches.next();
                            }
                        }
                    }
                } else {
                    System.out.println("Le deadline de cette tache a été dépassé");
                }
            }
            if (tache.getEtat() == EtatTache.UNSCHEDULED) {
                System.out.println(
                        "Le système n'a pas trouvé un créneau qui satisfait la priorité et le deadline de cette tache dans la période définie.\nSouhaitez vous mettre-à-jour ce planning en considérant cette tache? (1/0)");
                Scanner scanner = new Scanner(System.in);
                int option = Integer.parseInt(scanner.nextLine());
                if (option == 1) { // Chercher toutes les taches planifiées dans le planning dont le créneau n'est
                                   // pas bloqué
                    ArrayList<Tache> listTaches = new ArrayList<>();
                    // ajouter la tache à planifier
                    listTaches.add(tache);
                    Iterator iteratorJournéesPlannning = planning.getJournéesPlanifiées().iterator();
                    while (iteratorJournéesPlanning.hasNext()) {
                        journée = iteratorJournéesPlanning.next();
                        Iterator iteratorCouplesCréneauTache = journée.getListCreneauxTaches().iterator();
                        while (iteratorCouplesCréneauTache.hasNext()) {
                            CreneauTache creneauTache = (CreneauTache) iteratorCouplesCréneauTache.next();
                            if (!creneauTache.isEstBloqué()) { // Si le créneau correspondant n'est pas bloqué
                                tache = creneauTache.getTache(); // récupérer la tache
                                listTaches.add(tache); // l'ajouter à la liste des taches à replanifier
                                System.out.println("tache ajoutée à la replanification" + tache);
                            }
                        }
                    }
                    planifierEnsembleTaches(planning, listTachesOrdonnées(listTaches));
                }
            } else {
                System.out.println("Tache programmée avec succès.");
            }
        }
        planner.updateUser(this);
    }

    public void planifierProjet(Planning planning, Projet projet) {
        ArrayList<Tache> listeTachesProjet = new ArrayList<>(null);
        listeTachesProjet = projet.getListeTaches();
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "1. Planification manuelle des taches du projet\n2. Planification automatique des taches du projet");
        int option = Integer.parseInt(scanner.nextLine());
        if (option == 1) { // Planification manuelle des taches du projet
            Iterator iterator = listeTachesProjet.iterator();
            while (iterator.hasNext()) {
                TacheSimple tache = (TacheSimple) iterator.next();
                System.out.println("Introduisez la journée yyyy-mm-dd: ");
                String dateTacheString = scanner.nextLine();
                LocalDate dateTache = LocalDate.parse(dateTacheString);
                System.out.println("Introduisez l'heure début du créneau souhaité: HH:mm");
                String creneauString = scanner.nextLine();
                LocalTime heureDebut = LocalTime.parse(creneauString);
                LocalTime heureFin = heureDebut.plusMinutes(tache.getDurée());
                Creneau creneau = new Creneau(heureDebut, heureFin);
                planifierTacheManuelle(dateTache, creneau, tache, false);

            }
        } else if (option == 2) { // Planification automatique des taches du projet
            planifierEnsembleTaches(planning, listeTachesProjet);
        }
        historiqueProjets.add(projet);
        planner.updateUser(this);
    }

    public boolean hasIntersection(Creneau creneau1, Creneau creneau2) {
        return creneau1.getHeureDebut().isBefore(creneau2.getHeureFin())
                && creneau1.getHeureFin().isAfter(creneau2.getHeureDebut()) ||
                creneau1.getHeureDebut().equals(creneau2.getHeureFin())
                || creneau1.getHeureFin().equals(creneau2.getHeureDebut());
    }

    public void setPlanner(MyDesktopPlanner planner) {
        this.planner = planner;
    }

    public void consulterHistoriquePlannings() {
        Iterator<Planning> iterator = historiquePlannings.iterator();
        while (iterator.hasNext()) {
            Planning planning = (Planning) iterator.next();
            planning.consulterPlanning();
            //int tabBadge[] = planning.getNbBadges() ;
        }
    }
}