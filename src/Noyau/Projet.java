package Noyau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Projet implements Serializable {
    private String nom;
    private String description;
    private ArrayList<Tache> listeTaches;

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

    @Override
    public String toString() {
        return "Projet [nom=" + nom + ", description=" + description + ", listeTaches=" + listeTaches + "]";
    }

}
