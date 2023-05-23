package Control;

import java.io.IOException;

import Noyau.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*********************CONTROLLEUR DE LA PAGE DU MENU DE FONCTIONNALITEES***********************/

public class PlanificationPageController {

    private Utilisateur user ;
    public void setUtilisateur(Utilisateur user){
        this.user=user ;
    
    }

    @FXML
    private Button automatiqueLabel;

    @FXML
    private Button avantDateLimiteLabel;

    @FXML
    private Button consulterPlanningLabel;

    @FXML
    private Button consulterPlanningLabel1;

    @FXML
    private Button manuelleLabel;

    @FXML
    private Button projectLabel;
    
    @FXML
    private Button consulterProjectButtron;

    @FXML
    void afficherLesProjetsClick(ActionEvent event) { //allez au affichage des projets

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/consulterProjet.fxml"));
            Parent root = loader.load() ; 

            ConsulterProjetController controleur = loader.getController() ;
            controleur.setUtilisateur(user);
            controleur.initialiser();

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
    void afficherLesPlanningClick(ActionEvent event) { //aller au affichage des plannings
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/consulterPlanning.fxml"));
            Parent root = loader.load() ; 

            ConsulterPlaningController controleur = loader.getController() ;
            controleur.setUtilisateur(user);
            controleur.initialiser();

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
    void planificationAutomatiqueClick(ActionEvent event) { //allez a la planification automatique
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/planningSettings.fxml"));
            Parent root = loader.load() ; 

            PlaningSettings controleur = loader.getController() ;
            controleur.setUtilisateur(this.user);

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
    void planifierProjetClick(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/PlanificationProject.fxml"));
            Parent root = loader.load() ; 

            PlanificationProjetController controleur = loader.getController() ;
            controleur.setUtilisateur(user);
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

 

    @FXML
    void planifierTacheManuelleClick(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/PlanificationManuelle.fxml"));
            Parent root = loader.load() ; 

            PlanificationManuelleController controleur = loader.getController() ;
            controleur.setUtilisateur(this.user);

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
    private Button statistiqueButton;
    @FXML
    void statistiqueClick(ActionEvent event) {
  try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/statistique.fxml"));
            Parent root = loader.load() ; 

            StatistiqueController controleur = loader.getController() ;
            controleur.setUtilisateur(this.user);
            controleur.mettreAjOUR();
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


