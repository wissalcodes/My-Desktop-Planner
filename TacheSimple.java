import java.util.Date;
import java.util.Locale.Category;

public class TacheSimple extends Tache {
    public TacheSimple(Category catégorie, Date deadline, Priorité priorité, int durée, String nom, int période) {
        super(catégorie, deadline, priorité, durée, nom);
        this.période = période;
    }

    private int période;

    public int getPériode() {
        return période;
    }

    public void setPériode(int période) {
        this.période = période;
    }
}
