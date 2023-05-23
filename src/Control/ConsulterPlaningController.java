package Control;
import java.io.IOException;
import java.util.Iterator;

import Noyau.*;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class ConsulterPlaningController {
private Utilisateur user ;



public void setUtilisateur(Utilisateur user){

    this.user = user ;
}
@FXML
private Button modifierEtatTacheButton;

    
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

    @FXML
void modifierEtatClick(ActionEvent event) {
    try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/modifierTache.fxml"));
        Parent root = loader.load() ; 

        modifierTacheController controleur = loader.getController() ;
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
}
