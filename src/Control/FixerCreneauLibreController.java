package Control;


import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import Noyau.MyDesktopPlanner;
import Noyau.Planning;
import Noyau.Utilisateur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage ;
import Noyau.*;
public class FixerCreneauLibreController    {

   // LocalDate dateDebut ; 
    //LocalDate dateFin ; 
    Planning planning = new Planning() ;

   /*  public void setDateDebut( LocalDate dateDebut ){
        this.dateDebut = dateDebut ;
    }
    
    public void setDateFin( LocalDate dateFin ){
        this.dateFin = dateFin ;
    }*/

    Utilisateur user ;

    public void setUtilisateur(Utilisateur user){
    
        this.user = user ;
    }
    @FXML
    private Button ajouterUnAutreLibreButton;

    @FXML
    private Label dateDebutLabel;

    @FXML
    private Button lesMemeCrenauButton;

    @FXML
    private Button nextJournéeButton;

    @FXML
    private Button nextPageButton;
    @FXML
    private TextField heureDebutTextField;

    @FXML
    private TextField heureFinTextField;

    @FXML
    private Label doneLabel;

    private Set<Journée> setJournée = new TreeSet<>() ;

    @FXML
    void ajouterUnAutreCrenauLibre(ActionEvent event) {
        
    }
     public void setDateDebut(String dateDebut){
        this.dateDebutLabel.setText(dateDebut);
     }

    @FXML
    void goChoisirTache(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ajouterTachePlanificationAuto.fxml"));
            Parent root = loader.load() ; 

            PlanificationAutoControlleur controleur = loader.getController() ;
            controleur.setUtilisateur(user);
            controleur.setPlaning(planning);
           // controleur.initialiser();

            Scene s = new Scene(root);
            Stage calendarStage = new Stage() ;
            calendarStage.setScene(s);
            calendarStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        } 

    }
    public LocalTime convertToTime(String timeString) {
        return LocalTime.parse(timeString);
    }

    @FXML
    void goNextJournéeClick(ActionEvent event) {



    }

    @FXML
    void lesMemeCreneau(ActionEvent event) {
       

        Creneau creneau = new Creneau(convertToTime(heureDebutTextField.getText()), 
        convertToTime(heureFinTextField.getText())) ;
            for (Journée journee : planning.getJournéesPlanifiées()) {
                journee.getListCreneauxLibres().add(creneau);
            }
        doneLabel.setText("DONE !");
        user.getPlanner().updateUser(user);

    }

    public void setPlaning(Planning planing) {
        this.planning = planing ;
    }



/*//Scanner scanner = new Scanner(System.in);
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
      dateDebutLabel.setText(journee.getDate().toString());

            Creneau creneau = new Creneau(heureDebut, heureFin);
            journee.getListCreneauxLibres().add(creneau);

        }
    }
} else {
    System.out.println("Option invalide.");
}
planner.updateUser(this);*/

}
