import java.util.*;

public class Projet {
    private String nom;
    private String description;
    private ArrayList<Tache> listeTaches = new ArrayList<>();

    public Projet(String nom, String description, ArrayList<Tache> listeTaches) {
        this.nom = nom;
        this.description = description;
        this.listeTaches = listeTaches;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Tache> getListeTaches() {
        return listeTaches;
    }

    public void setListeTaches(ArrayList<Tache> listeTaches) {
        this.listeTaches = listeTaches;
    }

    public EtatTache évaluerProjet() {
        Iterator<Tache> iterator = listeTaches.iterator();
        EtatTache état = EtatTache.COMPLETED;
        while (iterator.hasNext()) {
            Tache tache = (Tache) iterator.next();
            if (tache.getEtat() != EtatTache.COMPLETED) {
                return (EtatTache.INPROGRESS);
            }
        }
        return (état);
    }

    @Override
    public String toString() {
        return "Projet [nom=" + nom + ", description=" + description + ", listeTaches=" + listeTaches + "]";
    }

}
