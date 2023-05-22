import java.util.*;
import java.time.*;
import java.awt.Color;
import java.net.http.WebSocket.Listener;

public class Main {
    public static void main(String[] args) {
        MyDesktopPlanner planner = new MyDesktopPlanner();
        planner.chargerUtilisateursFichier();
        Utilisateur user = new Utilisateur("Wissal");
        planner.ajouterUtilisateur(user);
        planner.authentification("Wissal");
        System.out.println("mon calendrier: " + user.calendrierPerso);
        System.out.println("plannings: " + user.historiquePlannings);
        // Scanner scanner = new Scanner(System.in);
        // Planning planning = new Planning();
        // planning.setBadge(1);
        // try {
        // planning = user.fixerPériodePlanning();
        // } catch (DateDébutException e) {
        // System.out.println("> Erreur: la date de début planning est antérieure à la
        // date du jour!");
        // user.planifier();
        // }
        // ArrayList<Tache> listTaches = new ArrayList<>();
        // System.out.println("Entrez le nombre de taches dans le planning:");
        // int numTaches = Integer.parseInt(scanner.nextLine());

        // for (int i = 0; i < numTaches; i++) {
        // System.out.println("Entrez le nom de la tache n° " + i);
        // String nomTache = scanner.nextLine();

        // System.out.println("Entrez la durée de la tache (en minutes):");
        // int dureeTache = Integer.parseInt(scanner.nextLine());

        // System.out.println("Entrez la priorité de la tache (HIGH, MEDIUM, ou LOW):");
        // String prioriteTache = scanner.nextLine();
        // Priorité priorite = Priorité.valueOf(prioriteTache);

        // System.out.println("Entrez la date limite de la tache (format:
        // aaaa-mm-jj):");
        // String dateLimiteString = scanner.nextLine();
        // LocalDate dateLimite = LocalDate.parse(dateLimiteString);

        // System.out.println("Entrez l'heure limite de la tache (format: hh:mm:ss):");
        // String heureLimiteString = scanner.nextLine();
        // LocalTime heureLimite = LocalTime.parse(heureLimiteString);

        // System.out.println("Entrez la catégorie de la tache (STUDIES,
        // WORK,COOKING..):");
        // String categorieTache = scanner.nextLine();
        // Catégorie categorie = new Catégorie(categorieTache, new Color(0, 0, 0));
        // System.out.println("Tache simple ? (1/0)");
        // int estSimple = Integer.parseInt(scanner.nextLine());
        // if (estSimple == 0) {
        // TacheDécomposable tache = new TacheDécomposable(categorie, dateLimite,
        // heureLimite, priorite,
        // dureeTache, nomTache);
        // listTaches.add(tache);
        // }
        // if (estSimple == 1) {
        // TacheSimple tache = new TacheSimple(categorie, dateLimite, heureLimite,
        // priorite, dureeTache,
        // nomTache, 0);
        // listTaches.add(tache);
        // }
        // }
        // planning = user.fixerCréneauxLibres(planning);
        // planning = user.planifierEnsembleTaches(planning, listTaches);

        // System.out.println("1. Accepter le planning\n2. Refuser le planning");
        // int option = Integer.parseInt(scanner.nextLine());

        // if (option == 1) {
        // // Insérer le planning dans le calendrier
        // user.calendrierPerso.getJournéesCalendrier().addAll(planning.getJournéesPlanifiées());
        // user.historiquePlannings.add(planning);
        // user.getPlanner().updateUser(user);
        // System.out.println("Calendrier: " + user.getCalendrierPerso());
        // } else if (option == 2) {
        // System.out.println("Planning supprimé avec succès.");
        // }
        // // user.planifierEnsembleTaches(null, null);
        // System.out.println("\n\n\nMes plannings après planification:" +
        // user.getHistoriquePlannings());

    }
}
