package UI ;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.Iterator;

import Control.AuthenticationController;
import Noyau.*;

/*//for no errors :
-the path in the getRessource() containes only the file name since it is in the same folder with the classe that called it
- the controller in the fxml file had to begin with all the packages that contain it , ex for this case Control.AuthetificationController is what
has to be written in the controller place of scene builder in the authentification page
*/

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import java.awt.Color;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage)   {
       /* 
 // LES CATEGORIES 
        Catégorie cat1 = new Catégorie ( "STUDY", Color.BLUE) ;
        Catégorie cat2 = new Catégorie("HEALTH", Color.GREEN);
        Set<Catégorie> listeCategorie = new HashSet<>() ;
        listeCategorie.add(cat1);
        listeCategorie.add(cat2) ;
    
 // LES TACHES AVEC LEURS CRENEAU 
         TacheSimple tache1 = new TacheSimple(cat1, LocalDate.of(2023, 05, 23),LocalTime.of(15, 30
         ), Priorité.HIGH,30 , "REMISE TP",0 ) ;

          Creneau creneau1 = new Creneau(LocalTime.of(15, 30),LocalTime.of(16, 00)) ;
        CreneauTache creneauTache1 = new CreneauTache(creneau1, tache1) ;

          TacheSimple tache2 = new TacheSimple(cat2,formatLocalDate(LocalDate.of(2023, 05, 23) ), LocalTime.of(16, 00) , 
          Priorité.MEDIUM , 60 , "YOGA " , 0) ; 
          Creneau creneau2 = new Creneau(LocalTime.of(16, 00),LocalTime.of(17, 00)) ;
          CreneauTache creneauTache2 = new CreneauTache(creneau2, tache2) ;

// LES JOURNEES AVEC LEURS HASHSET DE TACHES 
          TreeSet<CreneauTache> listCreneauxTaches = new TreeSet<>(
            Comparator.comparing(CreneauTache::getCreneau, Comparator.comparing(Creneau::getHeureDebut)));
            listCreneauxTaches.add(creneauTache1) ;
            listCreneauxTaches.add(creneauTache2) ;

          Journée journée1 = new Journée(formatLocalDate(LocalDate.of(2023,05,23)) ) ;
          journée1.setListCreneauxTaches(listCreneauxTaches);

// LES PLANNING  AVEC LERUS LISTE DE JOURNEES
            TreeSet<Journée> listeDesJournée1 = new TreeSet();
            listeDesJournée1.add(journée1) ;
            Planning planning1 = new Planning(formatLocalDate(LocalDate.of(2023, 05, 23))
            , formatLocalDate(LocalDate.of(23, 05, 23)),
             listeDesJournée1) ;
// LES USERS AVEC LEURS PLANNING ET CALENDRIERS ET LISTES DES CATEGORIES
 
             Calendrier calendrier1 = new Calendrier() ;
           /*   Utilisateur user1 = new Utilisateur("FARAH") ;
             //user1.setCalendrierPerso(calendrier1);
             //calendrier1.getJournéesCalendrier().add(journée1) ;
              //calendrier1.toString() ;
             calendrier1.afficherLesJournéePlanifié();
            // System.out.println(calendrier1);*/

            /*  user1.setListeCatégories(listeCategorie);
             TreeSet<Planning> listeDesPlannings = new TreeSet<>() ;
             listeDesPlannings.add(planning1);
             user1.setHistoriquePlannings(listeDesPlannings);

// MY DESKTOP PLANNER ET SA LISTE DES USERS
        ArrayList<Utilisateur> userSet = new ArrayList<Utilisateur>();
        userSet.add(user1);
        MyDesktopPlanner planner = new MyDesktopPlanner() ;
        planner.setListUtilisateurs(userSet); */



try{
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml")) ;
      Parent root = loader.load() ;
     AuthenticationController controller = loader.getController() ;
   //  Utilisateur user1 = new Utilisateur("") ;
    MyDesktopPlanner planner = new MyDesktopPlanner() ;
   // planner.ajouterUtilisateur(user1);
    planner.chargerUtilisateursFichier(); 
     controller.setMyDesktop(planner);
    // planner.chargerUtilisateursFichier(); 
     Scene s = new Scene(root);
     primaryStage.setScene(s);
     primaryStage.show(); 

}
catch(IOException e){
    e.printStackTrace();
}
    }

    public static void main(String[] args){
        

    launch(args);
    }

    public LocalDate formatLocalDate(LocalDate date) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      return LocalDate.parse( date.format(formatter)) ;
}
}
