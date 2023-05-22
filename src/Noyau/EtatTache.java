package Noyau;

import java.io.Serializable;

public enum EtatTache implements Serializable {
    UNSCHEDULED, // S'il rdt
    NOTREALIZED,
    COMPLETED,
    INPROGRESS,
    DELAYED;
}
