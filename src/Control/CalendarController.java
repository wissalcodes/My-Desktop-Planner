package Control;
import java.util.*;
import javafx.scene.paint.Color ;

import javafx.scene.paint.Color;

import Noyau.*;

import Noyau.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CalendarController {

    Utilisateur currentUser ; 

    public void setUtilisateur(Utilisateur user ){
        this.currentUser = user ; 
    }

    @FXML
    private AnchorPane anchorPane00;

    @FXML
    private AnchorPane anchorPane01;

    @FXML
    private AnchorPane anchorPane02;

    @FXML
    private AnchorPane anchorPane03;

    @FXML
    private AnchorPane anchorPane04;

    @FXML
    private AnchorPane anchorPane05;

    @FXML
    private GridPane bigGridPane;

    @FXML
    private Label categorieLabel0;

    @FXML
    private Label dateLabel0;

    @FXML
    private Label dateLabel1;

    @FXML
    private GridPane firstGridPane;

    @FXML
    private Label heureDebutLabel0;

    @FXML
    private Label heureDebutLabel1;

    @FXML
    private Label heureDebutLabel11;

    @FXML
    private Label heureDebutLabel111;

    @FXML
    private Label heureDebutLabel2;

    @FXML
    private Label heureFinLabel0;

    @FXML
    private Label heureFinLabel1;

    @FXML
    private Label heureFinLabel11;

    @FXML
    private Label heureFinLabel111;

    @FXML
    private Label heureFinLabel2;

    @FXML
    private GridPane secondGridPane;

    @FXML
    private Label tankNameLabel0;

    @FXML
    private Label tankNameLabel1;

    @FXML
    private Label tankNameLabel2;

    @FXML
    private Label tankNameLabel3;

    @FXML
    private Label tankNameLabel4;

    @FXML
    private Label taskEtatLabel0;

    @FXML
    private Label taskEtatLabel1;

    @FXML
    private Label taskEtatLabel11;

    @FXML
    private Label taskEtatLabel111;

    @FXML
    private Label taskEtatLabel2;

    @FXML
    private Button taskInfoButton0;

    @FXML
    private Button taskInfoButton1;

    @FXML
    private Button taskInfoButton11;

    @FXML
    private Button taskInfoButton111;

    @FXML
    private Button taskInfoButton2;

    @FXML
    void afficherInfoTask(ActionEvent event) {

    }

   
    public void SetInfoTache(CreneauTache creneauTache){
        Tache tache = creneauTache.getTache() ;
        Creneau creneau = creneauTache.getCreneau() ;
        heureDebutLabel0.setText(creneau.getHeureDebutString());
        heureFinLabel0.setText(creneau.getHeureFinString() ) ;
        categorieLabel0.setText(tache.getCatégorie().getCategorieName());;
        taskEtatLabel0.setText(tache.getEtat().name());
        tankNameLabel0.setText(tache.getNom());
        anchorPane00.setStyle("-fx-background-color: " + tache.getCatégorie().toFXColor() + ";");               
    }

    public void initCalendar(Utilisateur currentUser){
        this.currentUser = currentUser ;
        Set<Planning> listeDesPlanning = currentUser.getHistoriquePlannings() ;
        Iterator<Planning> itPlanning = listeDesPlanning.iterator() ;

       while( itPlanning.hasNext()){
            Planning planning = itPlanning.next() ;
            Set<Journée> listeDesJournée = planning.getJournéesPlanifiées() ;
             Iterator<Journée> itjournée = listeDesJournée.iterator() ;

                while(itjournée.hasNext()){
                    Journée journée = itjournée.next() ;
                    System.out.println(journée.getDateString());
                    dateLabel0.setText(journée.getDateString());
                    Set<CreneauTache> listeCrenauTache = journée.getListCreneauxTaches() ;
                    Iterator<CreneauTache> itCrenauTache = listeCrenauTache.iterator() ;

                        while(itCrenauTache.hasNext()){
                                CreneauTache creneauTache = itCrenauTache.next() ;
                               SetInfoTache(creneauTache);
                               
                                

                        }
                }




       }
        
    }

}
