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

    private LocalDate date ;
    public void setDate(LocalDate date){
        this.date = date ;
    }
    @FXML
    void okModifier(ActionEvent event) {

      tache.setEtat(EtatTache.valueOf(nouveauEtatField.getText()));
       user.getPlanner().updateUser(user) ;

    }

    public void init(){
        ObservableList<String> listViewPlanning = FXCollections.observableArrayList();
    
                listViewPlanning.add(tache.toString() );

        listView.setItems(listViewPlanning) ;
       

    }
   
}
