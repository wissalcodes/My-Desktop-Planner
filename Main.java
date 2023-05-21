import java.util.*;
import java.time.*;
import java.awt.Color;
import java.net.http.WebSocket.Listener;

public class Main {
    public static void main(String[] args) {
        MyDesktopPlanner planner = new MyDesktopPlanner();
        planner.chargerUtilisateursFichier();
        Utilisateur user = new Utilisateur("Wissal");
        planner.authentification("Wissal");
        System.out.println(planner.getListUtilisateurs());
    }
}
