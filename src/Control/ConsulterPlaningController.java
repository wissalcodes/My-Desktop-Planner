package Control;
import java.util.Iterator;

import Noyau.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


public class ConsulterPlaningController {
Utilisateur user ;

public void setUtilisateur(Utilisateur user){

    this.user = user ;
}


    
        @FXML
        private ListView<String> listView;
    
   
    

    public void initialiser(){

        ObservableList<String> listViewPlanning = FXCollections.observableArrayList();
        Iterator<Journée> it = user.getCalendrierPerso().getJournéesCalendrier().iterator();
        while (it.hasNext()) {
            Journée journée = it.next();
            if (! journée.getListCreneauxTaches().isEmpty()) {
                listViewPlanning.add(journée.toString() );
            }
        }

        listView.setItems(listViewPlanning) ;
        
    }
}
