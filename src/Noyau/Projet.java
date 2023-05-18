package Noyau ;

import java.util.List;

public class Projet {
    private String nom ;
    private String description;
    private List<Tache> listeTaches;
    public Projet(String nom, String description, List<Tache> listeTaches) {
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
    public List<Tache> getListeTaches() {
        return listeTaches;
    }
    public void setListeTaches(List<Tache> listeTaches) {
        this.listeTaches = listeTaches;
    }
    @Override
    public String toString() {
        return "Projet [nom=" + nom + ", description=" + description + ", listeTaches=" + listeTaches + "]";
    }

}
