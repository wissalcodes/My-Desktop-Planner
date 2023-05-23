package Control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.TreeSet;


import Noyau.*;
import Noyau.CreneauTache;
import Noyau.Journée;
import Noyau.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class modifierTacheController {

    private Utilisateur user ;



public void setUtilisateur(Utilisateur user){

    this.user = user ;}
    
    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField heureField;

    @FXML
    private Button rechercherButoon;


    public LocalTime convertToTime(String timeString) {
        return LocalTime.parse(timeString);
    }

    public LocalDate formatLocalDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse( date.format(formatter)) ;
    }

    
    @FXML
    void rechercher(ActionEvent event) {
     LocalDate date = formatLocalDate( datePicker.getValue()) ;
     LocalTime heureDebut = convertToTime(heureField.getText());
     Calendrier calendar = user.getCalendrierPerso() ;
     boolean trouve = false ; 
    Journée journee = calendar.getJournéeByDate(date) ;
    TreeSet<CreneauTache> listCreneauxTaches  = journee.getListCreneauxTaches() ;
    Iterator<CreneauTache> it = listCreneauxTaches.iterator() ;
        while(it.hasNext() && !trouve ){
            CreneauTache cr = it.next() ;
              if( cr.getCreneau().getHeureDebut() == heureDebut ){
                Tache tache = cr.getTache() ;
                trouve=true ;
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/changerL'etat.fxml"));
                    Parent root = loader.load() ; 
        
                    changerEtatTacheControlleur controleur = loader.getController() ;
                    controleur.setUtilisateur(user);
                    controleur.setDate(date) ;
                    controleur.setTache(tache);
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

        
     

    }

}


