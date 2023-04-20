import java.time.LocalTime;

public class CrenauOccupé extends Creneau{
private Tache tache ; 
    public Tache getTache() {
    return tache;
}
public void setTache(Tache tache) {
    this.tache = tache;
}
public CrenauOccupé(LocalTime heureDebut, LocalTime heureFin,Tache tache) {
super(heureDebut, heureFin);
this.tache = tache;
}
public boolean estLibre(){
    return false;
}

}
