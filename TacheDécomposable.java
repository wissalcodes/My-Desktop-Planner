import java.util.*;
import java.time.* ;

public class TacheDécomposable extends Tache {
    private List<Tache> listeSousTaches;
    public List<Tache> getListeSousTaches() {
        return listeSousTaches;
    }
    public void setListeSousTaches(List<Tache> listeSousTaches) {
        this.listeSousTaches = listeSousTaches;
    }
    public TacheDécomposable(Catégorie catégorie, LocalDate deadlineDate, LocalTime deadlineHeure, Priorité priorité, int durée,String nom, List<Tache> listeSousTaches) {
        super(catégorie,deadlineDate,deadlineHeure,priorité,durée,nom);
        this.listeSousTaches = listeSousTaches ;
    }
}
