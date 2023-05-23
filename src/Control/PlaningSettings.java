package Control;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Noyau.DateDébutException;
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
import javafx.stage.Stage;

/*********************CONTROLLEUR DE LA PAGE DU FIXEMENT DE LA PERIODE DU PLANING A PLANIFIER AUTOMATIQUEMENT***********************/

public class PlaningSettings   {
    Utilisateur user ;

    public void setUtilisateur(Utilisateur user){
    
        this.user = user ;
    }

    public LocalDate formatLocalDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date.format(formatter));
    }
    

    @FXML
    private DatePicker dateDebutPicker;

    @FXML
    private DatePicker dateFinPicker;

    @FXML
    private Button nextButton;

    @FXML
    void goFixéCreneauLibre(ActionEvent event) throws DateDébutException { //allez a la page suivante : fixement des creneaux libres
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/fixerCreneauLibre.fxml"));
            Parent root = loader.load() ; 
            FixerCreneauLibreController controleur = loader.getController() ;
             controleur.setUtilisateur(this.user);
            user.setDateLimite(getDateFin());
            user.setStartDay(getDateDébut());
              Planning planing =  user.fixerPériodePlanning() ;
              controleur.setPlaning(planing )  ;
              controleur.setDateDebut(planing.getDateDébut().toString());
             System.out.println(planing.toString() ) ;
            Scene s = new Scene(root);
            Stage calendarStage = new Stage() ;
            calendarStage.setScene(s);
            calendarStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        } 


    }

    public LocalDate getDateDébut() {
        return formatLocalDate(dateDebutPicker.getValue()) ;
    }

    public LocalDate getDateFin() {
        return formatLocalDate(dateFinPicker.getValue()) ;
    }

}