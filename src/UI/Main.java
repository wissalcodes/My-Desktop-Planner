package UI ;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import Control.AuthenticationController;
import Noyau.*;

/*//for no errors :
-the path in the getRessource() containes only the file name since it is in the same folder with the classe that called it
- the controller in the fxml file had to begin with all the packages that contain it , ex for this case Control.AuthetificationController is what
has to be written in the controller place of scene builder in the authentification page
*/

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage)   {
        
        Calendrier calendrier1 = new Calendrier() ;
        Utilisateur user1 = new Utilisateur("FARAH", calendrier1) ;

        Set<Utilisateur> userSet = new HashSet<Utilisateur>();

        userSet.add(user1) ;

        MyDesktopPlanner planner = new MyDesktopPlanner(userSet) ;

try{
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml")) ;
      Parent root = loader.load() ;
     AuthenticationController controller = loader.getController() ;

     controller.setMyDesktop(planner);
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
