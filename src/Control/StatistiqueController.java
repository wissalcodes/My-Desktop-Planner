package Control;

import java.util.Iterator;
import java.util.TreeSet;
import Noyau.*;
import Noyau.Calendrier;
import Noyau.CreneauTache;
import Noyau.Journée;
import Noyau.Planning;
import Noyau.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
/*********************CONTROLLEUR DE LA PAGE DES STATISTIQUES ***********************/

public class StatistiqueController {
    private Utilisateur user ;
    public void setUtilisateur(Utilisateur user){
        this.user=user ;
    
    }
    @FXML
    private Label nbExcelent;

    @FXML
    private Label nbGood;

    @FXML
    private Label nbVeryGood;


    public void setNbGood(String nbGood){
        this.nbGood.setText(nbGood); ;
    }

    public void setNbVeryGood(String nbGood){
        this.nbVeryGood.setText(nbGood); ;
    }

    public void setNbExcelent(String nbGood){
        this.nbExcelent.setText(nbGood);
    }  


  public void mettreAjOUR(){

    int nbTacheRealisé = user.getNbTachesRéalisées() ;
    if (nbTacheRealisé>= user.getNbMinTaches() ){
       nbGood.setText(String.valueOf( nbTacheRealisé /user.getNbMinTaches()));  
    }
    if (nbTacheRealisé>= 2*user.getNbMinTaches() ){ // si il atteint 2 fois le nombre minimales des tache a realisé 
        nbVeryGood.setText(String.valueOf( nbTacheRealisé /(2*user.getNbMinTaches() )));  
    }
    if (nbTacheRealisé>= 3*user.getNbMinTaches() ){
        nbExcelent.setText(String.valueOf( nbTacheRealisé /(3*user.getNbMinTaches())));  
    }
    user.getPlanner().updateUser(user) ;

  
       
    }
}

    
 

