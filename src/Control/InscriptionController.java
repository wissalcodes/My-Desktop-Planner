package Control;

import Noyau.Calendrier;
import Noyau.MyDesktopPlanner;
import Noyau.Utilisateur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

public class InscriptionController {

   private MyDesktopPlanner planner ;

   public void setMyDesktop(MyDesktopPlanner planner){
    this.planner = planner ; 
}

    @FXML
    private TextField inscriptionTextField;

    @FXML
    private Button okButton;

     @FXML
    private Button retournerButton;
    
    @FXML
    private Label ajoutSuccesLabel;
        
    @FXML
     void okInscription(ActionEvent event) {
      String pseudo = inscriptionTextField.getText() ; 
      Utilisateur user = planner.getUtilisateurParPseudo(pseudo);
        
        if( user == null) { // pseudo non existant deja 
        Calendrier calendrier = new Calendrier() ; // creation du nouveau user 
        Utilisateur newUser = new Utilisateur(pseudo , calendrier);
        planner.getListUtilisateurs().add(newUser) ; 
        ajoutSuccesLabel.setText("Utilisateur ajouté avec succées ! ");
        planner.afficherListeUtilisateur() ;
      }
      else{ // pseudo deja existant 
        utilisateurExistantAlerte(pseudo);
      }

    }

    public void utilisateurExistantAlerte(String pseudo){
        Alert alerte = new Alert(AlertType.INFORMATION) ;
        alerte.setTitle("Utilisateur Deja Existant");
        alerte.setHeaderText(null);
        alerte.setContentText("Le pseudo que vous avez saisie existe deja. " +
        "Veuillez saisir un autre pseudo ");
        alerte.showAndWait() ;
    
    }

    @FXML
    void retourner(ActionEvent event) {
        Scene InscriptionScene = retournerButton.getScene() ; 
        Window InscriptionWindow = InscriptionScene.getWindow() ;
        InscriptionWindow.hide(); 

    }


}

