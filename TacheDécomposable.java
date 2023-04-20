import java.util.Date;
import java.util.Locale.Category;
import java.util.ArrayList;
import java.util.List;

public class TacheDécomposable extends Tache {
    private List<Tache> listeSousTaches;
    public List<Tache> getListeSousTaches() {
        return listeSousTaches;
    }
    public void setListeSousTaches(List<Tache> listeSousTaches) {
        this.listeSousTaches = listeSousTaches;
    }
    public TacheDécomposable(Category catégorie, Date deadline, Priorité priorité, int durée, String nom, List<Tache> listeSousTaches) {
        super(catégorie, deadline, priorité, durée, nom);
        this.listeSousTaches = listeSousTaches;
    }    
}
