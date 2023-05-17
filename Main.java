import java.util.*;
import java.time.* ; 
import java.awt.Color;
import java.net.http.WebSocket.Listener;
public class Main {
    public static void main  (String[] args) {
        MyDesktopPlanner app = new MyDesktopPlanner() ; 
        Utilisateur user = new Utilisateur("Pookie",new Calendrier());
        //Planning planning =  user.planifierEnsembleTaches(listTaches);
        app.ajouterUtilisateur(user);
        app.authentification("Pookie");
        app.planifier();
        //Programmer un ensemble 
    }
}
