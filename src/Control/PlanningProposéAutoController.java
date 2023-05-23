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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import javafx.scene.control.ListView;

public class PlanningProposéAutoController {
    private Utilisateur user ;
    private Planning planing ;
    ArrayList<Tache> listTaches = new ArrayList<>() ;
    public void setUtilisateur(Utilisateur user){
    
        this.user = user ;
    }

    public void setPlaning(Planning p){
    
        this.planing = p ;
    }

public void init(){
    ObservableList<String> listViewPlanning = FXCollections.observableArrayList();
    Iterator<Journée> it = planing.getJournéesPlanifiées().iterator();
    while (it.hasNext()) {
        Journée journée = it.next();
        if (! journée.getListCreneauxTaches().isEmpty()) {
            listViewPlanning.add(journée.toString() );
        }
    }

    listView.setItems(listViewPlanning) ;

}
    @FXML
    private Button accepterButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button refuserButton;

    @FXML
    void AccepterPlanning(ActionEvent event) {
        user.getCalendrierPerso().getJournéesCalendrier().addAll(planing.getJournéesPlanifiées());
        user.getHistoriquePlannings().add(planing);
        reponseLable.setText("Planing Ajouté avec succées ") ;
    }

    @FXML
    private Label reponseLable;

    @FXML
    void refuserPlanning(ActionEvent event) {
        reponseLable.setText("Planing Refusé ") ;

    }

}

