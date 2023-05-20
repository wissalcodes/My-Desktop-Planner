import java.util.*;
import java.time.*;
import java.awt.Color;
import java.net.http.WebSocket.Listener;

public class Main {
    public static void main(String[] args) {
        MyDesktopPlanner app = new MyDesktopPlanner();
        Utilisateur user = new Utilisateur("Pookie");
        app.ajouterUtilisateur(user);
        app.authentification("Pookie");
        user.planifier(); // Test planification automatique
        user.planifier(); // Test planification d'une tache avant une date limite
    }
}
