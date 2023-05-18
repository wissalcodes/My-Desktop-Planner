package Noyau ;


import java.time.*;
public class Tache {
    @Override
    public String toString() {
        return "Tache [catégorie=" + catégorie + ", deadlineDate=" + deadlineDate + ", deadlineHeure=" + deadlineHeure
                + ", priorité=" + priorité + ", durée=" + durée + ", nom=" + nom + ", etat=" + etat + "]";
    }
    public Tache(Catégorie catégorie, LocalDate deadlineDate, LocalTime deadlineHeure, Priorité priorité, int durée,
            String nom) {
        this.catégorie = catégorie;
        this.deadlineDate = deadlineDate;
        this.deadlineHeure = deadlineHeure;
        this.priorité = priorité;
        this.durée = durée;
        this.nom = nom;
    }
    protected Catégorie catégorie;
    protected LocalDate deadlineDate;
    protected LocalTime deadlineHeure;
    protected Priorité priorité;
    protected int durée;
    protected String nom;
    protected EtatTache etat = EtatTache.NOTREALIZED; 
   
    public EtatTache getEtat() {
        return etat;
    }
    public void setEtat(EtatTache etat) {
        this.etat = etat;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getDurée() {
        return durée;
    }
    public void setDurée(int durée) {
        this.durée = durée;
    }
    public Priorité getPriorité() {
        return priorité;
    }
    public void setPriorité(Priorité priorité) {
        this.priorité = priorité;
    }
    public Catégorie getCatégorie() {
        return catégorie;
    }
    public void setCatégorie(Catégorie catégorie) {
        this.catégorie = catégorie;
    }
    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }
    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
    public LocalTime getDeadlineHeure() {
        return deadlineHeure;
    }
    public void setDeadlineHeure(LocalTime deadlineHeure) {
        this.deadlineHeure = deadlineHeure;
    }
    public void afficher() {
        System.out.println("Nom de la tâche: " + nom);
        System.out.println("Durée de la tâche: " + durée);
        System.out.println("Priorité de la tâche: " + priorité.toString());
        System.out.println("Date limite de la tâche: " + deadlineDate + " " + deadlineHeure);
        System.out.println("Catégorie de la tâche: " + catégorie.toString());
    
        if (etat != null) {
            System.out.println("Etat de la tâche: " + etat.toString());
        }
    }

}
