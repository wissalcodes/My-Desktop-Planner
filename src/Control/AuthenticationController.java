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

/*********************CONTROLLEUR DE LA PAGE D'AUTHENTIFICATION ***********************/
public class AuthenticationController {

    protected MyDesktopPlanner planner = new MyDesktopPlanner() ; 

    @FXML
    private Button authetifierButton;

    @FXML
    private Button inscrireButton;

    @FXML
    private TextField pseudoTextField;

    @FXML
    private Button quitterButton;


    public void setMyDesktop(MyDesktopPlanner planner){
        this.planner = planner ; 
    }

   
      
    @FXML
        void clickAuthentifier(ActionEvent event) {
            String pseudo = pseudoTextField.getText();
            boolean existe = this.planner.rechercheUtilisateur(pseudo);
        
            if (existe) {
                try {
                    planner.chargerUtilisateursFichier();     // Read data from the file

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/PlannificationPage.fxml"));
                    Parent root = loader.load();
                    PlanificationPageController controleur = loader.getController();
                    controleur.setUtilisateur(planner.getUtilisateurParPseudo(pseudo));
        
                    Scene s = new Scene(root);
                    Stage calendarStage = new Stage();
                    calendarStage.setScene(s);
                    calendarStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                matriculeNonTrouve(pseudo);
            }
        }

    @FXML
    void inscrire(ActionEvent event) { 
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Inscription.fxml"));
            Parent root = loader.load() ; 
            InscriptionController inscrCtrl = loader.getController() ;
            inscrCtrl.setMyDesktop(planner);
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
    void quitter(ActionEvent event) {
        Platform.exit();

    }

    public void matriculeNonTrouve(String m) { 

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pseduo introuvable");
        alert.setHeaderText(null);

        alert.setContentText("le pseudo recherché "+ m + " n'existe pas!" + 
        "Veuillez introduire un autre pseudo");

        alert.showAndWait();
        }

}

