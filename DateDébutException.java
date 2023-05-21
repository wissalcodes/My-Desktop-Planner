import java.io.Serializable;

public class DateDébutException extends Exception implements Serializable {
    // lancée quand l'utilisateur choisit une date de début antérieure à la date du
    // jour lors d'un planning
}
