import java.util.*;
import java.time.*;
import java.awt.Color;
import java.net.http.WebSocket.Listener;

public class Main {
    public static void main(String[] args) {
        MyDesktopPlanner planner = new MyDesktopPlanner();
        planner.chargerUtilisateursFichier();
        Utilisateur user = new Utilisateur("Wissal");
        // planner.ajouterUtilisateur(user);
        planner.authentification("Amina");
        // Planification d'une tache manuelle et bloquer son créneau
        user.planifier();

    }
}
