package Control;


import javafx.scene.control.Label;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
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

/*********************CONTROLLEUR DE LA PAGE DE FIXEMENT DE L'ENSEMBLE DES TACHES A PLANIFIER AUTOMATIQUEMENT ***********************/

public class PlanificationAutoControlleur {

    private Utilisateur user ;
    private Planning planing ;
    private ArrayList<Tache> listTaches = new ArrayList<>() ;
    public void setUtilisateur(Utilisateur user){
    
        this.user = user ;
    }

    public void setPlaning(Planning p){
    
        this.planing = p ;
    }

    @FXML
    private CheckBox bloquécheck;

    @FXML
    private TextField categorieTextFiel;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private DatePicker dateLimitePicker;

    @FXML
    private CheckBox decomposableCheck;

    @FXML
    private TextField duréeField;

    @FXML
    private TextField heurelimiteField;

    @FXML
    private Button lancerLaPlanificationButton;

    @FXML
    private TextField nomTacheField;

    @FXML
    private TextField prioritéTextField;

    @FXML
    private Button validerButton;

    @FXML
    private Label okValideLabel;

    
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
    void ajouterAuListeDesTaches(ActionEvent event) {
        
        String nomTache = nomTacheField.getText() ;
        long durée = Integer.parseInt(duréeField.getText());
        LocalDate dateLimite = dateLimitePicker.getValue() ;
        String heureLimite = heurelimiteField.getText() ;
        String priorité = prioritéTextField.getText() ;
        String categorieName = categorieTextFiel.getText() ;
        Color couleurCategorie = colorPicker.getValue() ;
        boolean bloqué = bloquécheck.isSelected() ;
        boolean decomposable = decomposableCheck.isSelected() ;  

        Catégorie categorie = new Catégorie(categorieName, toAwtColor(couleurCategorie) );
        Tache tache;
         if ( !decomposable){
        TacheSimple tacheS = new TacheSimple(categorie,formatLocalDate(dateLimite), convertToTime (heureLimite), 
        Priorité.valueOf(priorité), durée, nomTache, 0) ;
        tache=tacheS ;
         }
         else{
            TacheDécomposable tacheD = new TacheDécomposable(categorie,formatLocalDate(dateLimite), convertToTime (heureLimite), 
            Priorité.valueOf(priorité), durée, nomTache);
            tache = tacheD;
         }

         listTaches.add(tache) ;

    }

    @FXML
    void planifierEtNextPage(ActionEvent event) { //afficher la page suivante : affichage du planing proposé
       Planning p =  user.planifierEnsembleTaches(planing, listTaches) ;
    //   System.out.println("-------------------------------");
      // System.out.println(p.toString());

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/PlanningProposéAuto.fxml"));
            Parent root = loader.load() ; 

            PlanningProposéAutoController controleur = loader.getController() ;
            controleur.setUtilisateur(this.user);
            controleur.setPlaning(planing);
            controleur.init();
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
