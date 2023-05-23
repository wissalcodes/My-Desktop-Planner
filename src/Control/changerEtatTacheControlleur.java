package Control;
import java.time.LocalDate;
import java.util.Iterator;

import Noyau.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/*********************CONTROLLEUR DE LA PAGE DU CHANGEMANT DE L'ETAT D'UNE TACHE CHOISI ***********************/

public class changerEtatTacheControlleur {

    private Utilisateur user ; 

    public void setUtilisateur(Utilisateur user){
        this.user = user ;
    }

    private Tache tache;
    public void setTache(Tache t){
        this.tache = t ;    }

    @FXML
    private ListView<String> listView;

    @FXML
    private Button modifierButton;

    @FXML
    private TextField nouveauEtatField;

 

    @FXML
    void okModifier(ActionEvent event) {

      tache.setEtat(EtatTache.valueOf(nouveauEtatField.getText()));
     if(nouveauEtatField.getText().equalsIgnoreCase("COMPLETED")){
        user.setNbTacheRealisé( user.getNbTachesRéalisées()+1);
     }
        
        user.getPlanner().updateUser(user) ;
      
     

    }

    public void init(){
        ObservableList<String> listViewPlanning = FXCollections.observableArrayList();
    
                listViewPlanning.add(tache.toString() );

        listView.setItems(listViewPlanning) ;
       

    }
   
}
