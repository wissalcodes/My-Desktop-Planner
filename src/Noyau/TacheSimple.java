package Noyau;

import java.util.Date;
import java.io.Serializable;
import java.time.*;

public class TacheSimple extends Tache implements Serializable {
    public TacheSimple(Catégorie catégorie, LocalDate deadlineDate, LocalTime deadlineHeure, Priorité priorité,
            long durée, String nom, int période) {
        super(catégorie, deadlineDate, deadlineHeure, priorité, durée, nom);
        this.période = période;
    }

    private int période = 0;

    public int getPériode() {
        return période;
    }

    public void setPériode(int période) {
        this.période = période;
    }

    public void afficher() {
        super.afficher();
        System.out.println("Période: " + période);
    }
}