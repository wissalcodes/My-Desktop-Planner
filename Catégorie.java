import java.awt.Color;
import java.io.Serializable;

public class Catégorie implements Serializable {
    private String categorie;
    private Color couleur;

    public Catégorie(String categorie, Color couleur) {
        this.categorie = categorie;
        this.couleur = couleur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "Catégorie [categorie=" + categorie + ", couleur=" + couleur + "]";
    }
}
