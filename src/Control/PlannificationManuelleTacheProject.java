package Control;

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


public class PlannificationManuelleTacheProject {
     

    private String nomProjet ;
    private String description ; 


   private  Utilisateur user ;

    public void setUtilisateur(Utilisateur user){
        this.user = user ; 
    }
    public void setNomProjet(String nomProjet){
        this.nomProjet = nomProjet ; 
    }
    public void setDescription (String description){
        this.description = description ; 
    }

    private ArrayList <Tache> listeTache = new ArrayList<>() ;

    @FXML
    private Button ajouterUneAutreTacheButton;

    @FXML
    private CheckBox bloquécheck;

    @FXML
    private TextField categorieTextFiel;

    @FXML
    private ColorPicker colorPicker;

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
    private Button planifierLeProjet;

    @FXML
    private TextField prioritéTextField;
    @FXML
    private Button doneButton;

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
    void doneClick(ActionEvent event) {
        Projet projet = new Projet(nomProjet,description,listeTache) ;
        projet.setListeTaches(listeTache);
        List<Projet> historiqueProjets = user.getHitoriqueProjets();

        historiqueProjets.add(projet) ;
        Iterator<Projet> itP = historiqueProjets.iterator();
       
        while(itP.hasNext()){
       System.out.println( itP.next().toString())  ;
        }
     }
    

    @FXML
    void ajouterUneAutreTacheButton(ActionEvent event) {

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

        user.planifierTacheManuelle(formatLocalDate( dateJournée ), creneau, tache, bloqué) ;
        listeTache.add(tache) ;

    }

}


