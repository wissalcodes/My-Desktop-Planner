import java.util.*;
import java.time.* ; 
import java.awt.Color;
import java.net.http.WebSocket.Listener;
public class Main {
    public static void main  (String[] args) {
        MyDesktopPlanner app = new MyDesktopPlanner() ; 
        Utilisateur user = new Utilisateur("Pookie");
        //Planning planning =  user.planifierEnsembleTaches(listTaches);
        app.ajouterUtilisateur(user);
        //Utilisateur user2 = new Utilisateur("Pookie");

        //app.ajouterUtilisateur(user2);
       app.authentification("Pookie");
       user.planifier();
        //Programmer un ensemble 
    }
}
