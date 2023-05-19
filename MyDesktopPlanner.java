import java.util.*;
import java.awt.Color;
import org.w3c.dom.css.RGBColor;
import java.time.* ;

public class MyDesktopPlanner {
    protected Utilisateur utilisateurAuthentifié = new Utilisateur("");
    public Utilisateur getUtilisateurAuthentifié() {
        return utilisateurAuthentifié;
    }
    public void setUtilisateurAuthentifié(Utilisateur utilisateurAuthentifié) {
        this.utilisateurAuthentifié = utilisateurAuthentifié;
    }
    private Set<Utilisateur> listUtilisateurs = new HashSet<>();
    @Override
    public String toString() {
        return "MyDesktopPlanner [listUtilisateurs=" + listUtilisateurs + "]";
    }
    public Set<Utilisateur> getListUtilisateurs() {
        return listUtilisateurs;
    }
    public void setListUtilisateurs(Set<Utilisateur> listUtilisateurs) {
        this.listUtilisateurs = listUtilisateurs;
    }

    public boolean rechercheUtilisateur(String pseudo) {
        return listUtilisateurs.contains(new Utilisateur(pseudo));
    }
   public void ajouterUtilisateur(Utilisateur utilisateur){
    if(rechercheUtilisateur(utilisateur.getPseudo())==false)
    {
        System.out.println("Utilisateur créé avec succès.\nIntroduisez les catégories souhaitées, suivies de leur couleur (au format RGB séparé par des virgules). Tapez 'stop' pour arrêter la saisie.");
        Scanner scanner = new Scanner(System.in);
        String categorieNom;
        do {
            System.out.print("Nom de la catégorie : ");
            categorieNom = scanner.nextLine();
            if (!categorieNom.equalsIgnoreCase("stop")) {
                System.out.print("Couleur de la catégorie (au format RGB séparé par des virgules) : ");
                String[] rgbString = scanner.nextLine().split(",");
                int r = Integer.parseInt(rgbString[0].trim());
                int g = Integer.parseInt(rgbString[1].trim());
                int b = Integer.parseInt(rgbString[2].trim());
                Color couleur = new Color(r, g, b);
                Catégorie categorie = new Catégorie(categorieNom, couleur);
                utilisateur.listeCatégories.add(categorie);
            }
        } while (!categorieNom.equalsIgnoreCase("stop"));
        listUtilisateurs.add(utilisateur);

    }
    else{
        System.out.println("Un utilisateur avec ce pseudo existe déjà.\nVeuillez resaisir un pseudo");
    }
}
    public Utilisateur getUtilisateurParPseudo(String pseudo) {
        for (Utilisateur u : listUtilisateurs) {
            if (u.getPseudo().equals(pseudo)) {
                return u;
            }
        }
        return null; // aucun utilisateur n'est trouvé
    }

    public Utilisateur authentification(String pseudo){
        try{
            if (listUtilisateurs.contains(getUtilisateurParPseudo(pseudo))== false){
                throw new UtilisateurIntrouvableException() ;
            }
            else {
                System.out.println("> Utilisateur authentifié avec succès.");
                System.out.println("            **Calendrier**          ");
                System.out.println(getUtilisateurParPseudo(pseudo).getCalendrierPerso());
                utilisateurAuthentifié = getUtilisateurParPseudo(pseudo);
                return (getUtilisateurParPseudo(pseudo));
            }
        }
        catch(UtilisateurIntrouvableException e){
            System.out.println("> Ce pseudo ne correspond à aucun utilisateur.");
            return(null);
        }
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
            utilisateurAuthentifié.planifierTacheManuelle(dateTache, creneau, tache);
        }
        if (option == 2){
            Planning planning = new Planning();
            System.out.println("inside option 2");
            try {
               planning = utilisateurAuthentifié.fixerCréneauxLibres(utilisateurAuthentifié.fixerPériodePlanning());
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
               listTaches.add(tache1);
               listTaches.add(tache2);
               listTaches.add(tache3);
               listTaches.add(tache4);    
               utilisateurAuthentifié.planifierEnsembleTaches(planning, listTaches);
               utilisateurAuthentifié.getCalendrierPerso().journéesCalendrier.addAll(planning.getJournéesPlanifiées());
               //proposition du système.
            }
            catch(DateDébutException e){
                System.out.println("> Erreur: la date de début planning est antérieure à la date du jour!");
            }
        }
        }
    
}


