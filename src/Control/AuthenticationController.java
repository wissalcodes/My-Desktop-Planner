package Control ;

import java.io.IOException;

import Noyau.MyDesktopPlanner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AuthenticationController {

    private MyDesktopPlanner planner ; 

    @FXML
    private Button authetifierButton;

    @FXML
    private Button inscrireButton;

    @FXML
    private TextField pseudoTextField;

    @FXML
    private Button quitterButton;

/* 
    public AuthenticationController(MyDesktopPlanner planner){

        this.planner = planner ;

    }
    */
    public void setMyDesktop(MyDesktopPlanner planner){
        this.planner = planner ; 
    }

    void afficherPage(String path){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load() ; 
            Scene s = new Scene(root);
            Stage calendarStage = new Stage() ;
            calendarStage.setScene(s);
            calendarStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
      
    }
      
    @FXML
    void clickAuthentifier(ActionEvent event) {
       String pseudo = pseudoTextField.getText() ;
       boolean existe = this.planner.rechercheUtilisateur(pseudo);

        if(existe){
             afficherPage("/UI/Calendar.fxml");
        }
        else{
            matriculeNonTrouve(pseudo);
        }
    }

    @FXML
    void inscrire(ActionEvent event) {
     // FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Inscription.fxml"));
           afficherPage("/UI/Inscription.fxml");    }

    @FXML
    void quitter(ActionEvent event) {
        Platform.exit();

    }

    public void matriculeNonTrouve(String m) { 

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Matricule introuvable");
        alert.setHeaderText(null);

        alert.setContentText("le matricule recherché"+ m + " n'existe pas!" + 
        "Veuillez introduire un autre matricule");

        alert.showAndWait();
        }

}

