import java.time.LocalTime;

public class Creneau {
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private boolean estOccupé = false;
    private static int dureeMinimale = 30 ;     //durée minimale de 30 minutes
    public static void setDureeMinimale(int dureeMinimale) {
        Creneau.dureeMinimale = dureeMinimale;
    }

    public static int getDureeMinimale() {
        return dureeMinimale;
    }

    public Creneau(LocalTime heureDebut, LocalTime heureFin) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }
    
    public LocalTime getHeureDebut() {
        return heureDebut;
    }
    
    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }
    
    public LocalTime getHeureFin() {
        return heureFin;
    }
    
    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }
    public boolean estOccupé(){
        return estOccupé;
    }

    public void setEstOccupé(boolean estOccupé) {
        this.estOccupé = estOccupé;
    }
    public void afficher(){
        System.out.println(heureDebut + " - " + heureFin);
        System.out.println("Etat: " + estOccupé);
    }
}
