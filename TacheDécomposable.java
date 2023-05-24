import java.util.*;
import java.io.Serializable;
import java.time.*;

public class TacheDécomposable extends Tache implements Serializable {
    private List<TacheSimple> listeSousTaches = new ArrayList<>();

    public List<TacheSimple> getListeSousTaches() {
        return listeSousTaches;
    }

    public void setListeSousTaches(List<TacheSimple> listeSousTaches) {
        this.listeSousTaches = listeSousTaches;
    }

    public TacheDécomposable(Catégorie catégorie, LocalDate string, LocalTime string2, Priorité priorité,
            long durée, String nom) {
        super(catégorie, string, string2, priorité, durée, nom);
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
