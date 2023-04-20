import java.util.Date;
import java.util.Locale.Category;
public class Tache {
    protected Category catégorie;
    protected Date deadline;
    protected Priorité priorité;
    protected int durée;
    protected String nom;
    public Tache(Category catégorie, Date deadline, Priorité priorité, int durée, String nom) {
        this.catégorie = catégorie;
        this.deadline = deadline;
        this.priorité = priorité;
        this.durée = durée;
        this.nom = nom;
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
    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public Category getCatégorie() {
        return catégorie;
    }
    public void setCatégorie(Category catégorie) {
        this.catégorie = catégorie;
    }
}
