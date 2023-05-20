import java.util.*;
import java.time.*;

public class TacheDécomposable extends Tache {
    private List<Tache> listeSousTaches = new ArrayList<>();

    public List<Tache> getListeSousTaches() {
        return listeSousTaches;
    }

    public void setListeSousTaches(List<Tache> listeSousTaches) {
        this.listeSousTaches = listeSousTaches;
    }

    public TacheDécomposable(Catégorie catégorie, LocalDate deadlineDate, LocalTime deadlineHeure, Priorité priorité,
            long durée, String nom) {
        super(catégorie, deadlineDate, deadlineHeure, priorité, durée, nom);
    }

    public void ajouterSousTache(TacheSimple tache) {
        listeSousTaches.add(tache);
    }
}
