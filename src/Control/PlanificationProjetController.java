package Control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlanificationProjetController {

    private  Utilisateur user ;

    public void setUtilisateur(Utilisateur user){
        this.user = user ; 
    }
    @FXML
    private Button ajouterLesachesButton;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField nomProjectTextField;

    @FXML
    void ajouterLesTaches(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/PlanificationManTacheProjet.fxml"));
            Parent root = loader.load() ; 

            PlannificationManuelleTacheProject controleur = loader.getController() ;
            controleur.setUtilisateur(this.user);
            controleur.setNomProjet(nomProjectTextField.getText());
            controleur.setDescription(descriptionTextField.getText());

            Scene s = new Scene(root);
            Stage calendarStage = new Stage() ;
            calendarStage.setScene(s);
            calendarStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        } 

    }

}




