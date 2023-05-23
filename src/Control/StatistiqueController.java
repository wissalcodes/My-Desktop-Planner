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

public class StatistiqueController {
    int nb = 0 ;
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
 public void init(){
    int nbGood = 0;
    int nbEx = 0;
    int nbV = 0 ;
    Iterator<Planning> iterator = user.getHistoriquePlannings().iterator();
    while (iterator.hasNext()) {

        Planning planning = (Planning) iterator.next();
        planning.consulterBadgesPlanning();
        int tabBadge[] = planning.getNbBadges() ;
        System.out.println(tabBadge[0]);
        setNbGood(String.valueOf(nbGood+tabBadge[0]));
        setNbVeryGood(String.valueOf(nbV+tabBadge[1]));
        setNbExcelent(String.valueOf(nbEx+tabBadge[2]));

user.getPlanner().updateUser(user);
        
    }
}

   public void mettreAjOUR(){
    Calendrier calendar = user.getCalendrierPerso() ;
    TreeSet<Journée> journéesCalendrier = calendar.getJournéesCalendrier() ;
    Iterator<Journée> it = journéesCalendrier.iterator() ;
    nbGood.setText(String.valueOf(nb));
    nb++ ;
    /*while(it.hasNext()){
        Journée journee = it.next() ;
        TreeSet<CreneauTache> crenauTacheList = journee.getListCreneauxTaches() ;
           Iterator<CreneauTache> ittache = crenauTacheList.iterator() ;
            while(ittache.hasNext()){
                CreneauTache crenauTache = ittache.next() ;
                Tache tache = crenauTache.getTache();
                
        
                    user.setNbTacheRealisé(user.getNbTachesRéalisées() + 1 );
            
             
            int i = user.getNbTachesRéalisées() ;

            if ( i > user.getNbMinTaches() ) {
               // badgeJournalier = Badge.GOOD;
               int nbBgood = Integer.parseInt(nbGood.getText()) +1 ;
               setNbGood(String.valueOf(nbBgood ));
            }
            if ( i > user.getNbMinTaches()+2 ) {
                int nbBV = Integer.parseInt(nbVeryGood.getText()) +1 ;
                setNbGood(String.valueOf(nbBV ));
            }
            if (i > user.getNbMinTaches()+4) {
                int nbBEXC = Integer.parseInt(nbExcelent.getText()) +1 ;
                setNbGood(String.valueOf(nbBEXC));
            }
            }*/
        
user.getPlanner().updateUser(user);
       
    }
   }
    
 

