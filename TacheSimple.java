import java.util.Date;
import java.time.* ; 
public class TacheSimple extends Tache {
    public TacheSimple(Catégorie catégorie, LocalDate deadlineDate, LocalTime deadlineHeure, Priorité priorité, int durée, String nom, int période) {
        super(catégorie,deadlineDate,deadlineHeure,priorité,durée,nom);
        this.période = période;
    }

    private int période = 0;

    public int getPériode() {
        return période;
    }

    public void setPériode(int période) {
        this.période = période;
    }

    public void afficher(){
        super.afficher();
        System.out.println("Période: " + période);
    }
}
