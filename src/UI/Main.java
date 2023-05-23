package UI ;
import java.io.IOException;

import Control.AuthenticationController;
import Noyau.*;


import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage)   {

try{
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml")) ;
      Parent root = loader.load() ;
     AuthenticationController controller = loader.getController() ;
   //  Utilisateur user1 = new Utilisateur("") ;
    MyDesktopPlanner planner = new MyDesktopPlanner() ;
   // planner.ajouterUtilisateur(user1);
    planner.chargerUtilisateursFichier(); 
     controller.setMyDesktop(planner);
    // planner.chargerUtilisateursFichier(); 
     Scene s = new Scene(root);
     primaryStage.setScene(s);
     primaryStage.show(); 

}
catch(IOException e){
    e.printStackTrace();
}
    }
    
    public static void main(String[] args){
        
    launch(args);
    }

}
