import java.io.Serializable;

public enum EtatTache implements Serializable {

    UNSCHEDULED,
    NOTREALIZED,
    COMPLETED,
    INPROGRESS,
    DELAYED;
}
