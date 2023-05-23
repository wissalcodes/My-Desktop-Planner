package Control;
import java.util.Iterator;

import Noyau.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
/*********************CONTROLLEUR DE LA PAGE D'AFFICHAGE DES PROJETS ***********************/


public class ConsulterProjetController {
    Utilisateur user ;
    @FXML
    private ListView<String> listView;
    public void setUtilisateur(Utilisateur user){
    
        this.user = user ;
    }
       
        public void initialiser(){ // afficher les projets
    
            ObservableList<String> listViewPlanning = FXCollections.observableArrayList();
            Iterator<Projet> it = user.getHitoriqueProjets().iterator();
            while (it.hasNext()) {
                Projet projet = it.next();
                    listViewPlanning.add(projet.toString());
            }
    
            listView.setItems(listViewPlanning) ;
            
        }   
}
