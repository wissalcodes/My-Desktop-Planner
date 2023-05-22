package Control;
import java.util.Iterator;

import Noyau.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ConsulterProjetController {
    Utilisateur user ;

    public void setUtilisateur(Utilisateur user){
    
        this.user = user ;
    }
    
    
        
            @FXML
            private ListView<String> listView;
        
       
        public void initialiser(){
    
            ObservableList<String> listViewPlanning = FXCollections.observableArrayList();
            Iterator<Projet> it = user.getHitoriqueProjets().iterator();
            while (it.hasNext()) {
                Projet projet = it.next();
                    listViewPlanning.add(projet.toString());
            }
    
            listView.setItems(listViewPlanning) ;
            
        }   
}
