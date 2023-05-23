package Control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Noyau.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Noyau.*;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import java.awt.color.*;
/*********************CONTROLLEUR DE LA PAGE DE LA PLANIFICATION MANUELLE ***********************/


public class PlanificationManuelleController {

    private Utilisateur user ;
    
    public void setUtilisateur(Utilisateur user){
        this.user=user ;
    
    }
    

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox bloquécheck;

    @FXML
    private TextField categorieTextFiel;

    @FXML
    private DatePicker dateLimitePicker;

    @FXML
    private TextField duréeField;

    @FXML
    private TextField heuredEbutField;

    @FXML
    private TextField heurelimiteField;

    @FXML
    private DatePicker journéePicker;

    @FXML
    private TextField nomTacheField;

    @FXML
    private Button planifierTacheManuelle;

    @FXML
    private TextField prioritéTextField;

public java.awt.Color toAwtColor(javafx.scene.paint.Color fxColor){
        int red = (int) (fxColor.getRed() * 255);
        int green = (int) (fxColor.getGreen() * 255);
        int blue = (int) (fxColor.getBlue() * 255);
        int alpha = (int) (fxColor.getOpacity() * 255);
        java.awt.Color awtColor = new java.awt.Color(red, green, blue, alpha) ;
        return awtColor ;
    }

    public LocalTime convertToTime(String timeString) {
        return LocalTime.parse(timeString);
    }

    public LocalDate formatLocalDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse( date.format(formatter)) ;
    }

    @FXML
    void planifierTacheManuelle(ActionEvent event) {

    String nomTache = nomTacheField.getText() ;
    LocalDate dateJournée = journéePicker.getValue() ;
    String heureDebut = heuredEbutField.getText() ;
    long durée = Integer.parseInt(duréeField.getText());
    LocalDate dateLimite = dateLimitePicker.getValue() ;
    String heureLimite = heurelimiteField.getText() ;
    String priorité = prioritéTextField.getText() ;
    String categorieName = categorieTextFiel.getText() ;
    Color couleurCategorie = colorPicker.getValue() ;
    boolean bloqué = bloquécheck.isSelected() ;
   

    Catégorie categorie = new Catégorie(categorieName, toAwtColor(couleurCategorie) );
   
    TacheSimple tache = new TacheSimple(categorie,formatLocalDate(dateLimite), convertToTime (heureLimite), 
    Priorité.valueOf(priorité), durée, nomTache, 0) ;

    Creneau creneau = new Creneau(convertToTime(heureDebut), convertToTime(heureDebut).plusMinutes(durée)) ;

    CreneauTache creneauTache = new CreneauTache(creneau, tache) ;

    creneauTache.setEstBloqué(bloqué);
try{
    user.planifierTacheManuelle(formatLocalDate( dateJournée ), creneau, tache, bloqué) ;

}
catch(DeadlinePassedException e){
    dateImpossible() ;
    System.out.println("DATE DE LA TACHE AVANT LA DATE D'AUJOURDUI !");
}
 
  user.getPlanner().updateUser(user); // sauvgarder les changement du user

    }

    public static void impossiblePlanifier() { 

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Planification Impossible ");
        alert.setHeaderText(null);

        alert.setContentText("Impossible de programmer la tache dans ce créneau,"+",car il est réservé pour une deuxième tache. Veuillez le Planifier dans un autre créneau");
        alert.showAndWait();
        }

        public void dateImpossible() { 
 
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Date journée impossible");
            alert.setHeaderText(null);
    
            alert.setContentText("LA DATE DE LA TACHE EST AVANT LA DATE D'AUJOURD'HUI !" + 
            "Veuillez introduire une autre date de journée");
    
            alert.showAndWait();
            }
}