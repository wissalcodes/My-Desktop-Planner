import java.util.*;
import java.time.*;

public class TacheDécomposable extends Tache {
    private List<TacheSimple> listeSousTaches = new ArrayList<>();

    public List<TacheSimple> getListeSousTaches() {
        return listeSousTaches;
    }

    public void setListeSousTaches(List<TacheSimple> listeSousTaches) {
        this.listeSousTaches = listeSousTaches;
    }

    public TacheDécomposable(Catégorie catégorie, LocalDate deadlineDate, LocalTime deadlineHeure, Priorité priorité,
            long durée, String nom) {
        super(catégorie, deadlineDate, deadlineHeure, priorité, durée, nom);
    }

    public void ajouterSousTache(TacheSimple tache) {
        listeSousTaches.add(tache);
    }

    public EtatTache évaluerTache() {
        Iterator<TacheSimple> iterator = listeSousTaches.iterator();
        EtatTache état = EtatTache.COMPLETED;
        while (iterator.hasNext()) {
            Tache tache = (Tache) iterator.next();
            if (tache.getEtat() != EtatTache.COMPLETED) {
                return (EtatTache.INPROGRESS);
            }
        }
        return (état);
    }
}
