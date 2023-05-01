import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Creneau implements Comparable<Creneau>{
    private LocalTime heureDebut;
    private LocalTime heureFin;
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
 
    public void afficher(){
        System.out.println(heureDebut + " - " + heureFin);
    }

    @Override
    public String toString() {
        return "Creneau [heureDebut=" + heureDebut + ", heureFin=" + heureFin + "]";
    }
    public boolean contientCreneau(Creneau creneau) {
        if (creneau.getHeureDebut().compareTo(heureDebut) >= 0 && creneau.getHeureFin().compareTo(heureFin) <= 0) {
            return true;
        }
        return false;
    }
    public Set<Creneau> decompositionCreneau(Creneau creneau) {
        Set<Creneau> result = new TreeSet<>(); 
        if (!contientCreneau(creneau)) {
            // Si le sous-creneau n'est pas contenu dans ce créneau, retourner le créneau d'origine
            result.add(this);
        } else if (creneau.getHeureDebut().isBefore(heureDebut) && creneau.getHeureFin().isBefore(heureFin)) {
            // Si le sous-creneau chevauche le début de ce créneau
            result.add(new Creneau(heureDebut, creneau.getHeureFin()));
            result.add(new Creneau(creneau.getHeureFin(), heureFin));
        } else if (creneau.getHeureDebut().isAfter(heureDebut) && creneau.getHeureFin().isAfter(heureFin)) {
            // Si le sous-creneau chevauche la fin de ce créneau
            result.add(new Creneau(heureDebut, creneau.getHeureDebut()));
            result.add(new Creneau(creneau.getHeureFin(), heureFin));
        } else if (creneau.getHeureDebut().equals(heureDebut) && creneau.getHeureFin().equals(heureFin)) {
            // Si le sous-creneau est identique à ce créneau, retourner le créneau d'origine
            result.add(this);
        } else {
            // Si le sous-creneau est à l'intérieur de ce créneau
            result.add(new Creneau(heureDebut, creneau.getHeureDebut()));
            result.add(new Creneau(creneau.getHeureFin(), heureFin));
        }
    
        return result;
    }
    public int compareTo(Creneau other) {
        return this.heureDebut.compareTo(other.getHeureDebut());
    }
}
