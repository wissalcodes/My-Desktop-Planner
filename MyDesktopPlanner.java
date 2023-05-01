import java.util.*;
import java.time.* ;

public class MyDesktopPlanner {
    private Utilisateur utilisateurAuthentifié = new Utilisateur("",new Calendrier());
    public Utilisateur getUtilisateurAuthentifié() {
        return utilisateurAuthentifié;
    }
    public void setUtilisateurAuthentifié(Utilisateur utilisateurAuthentifié) {
        this.utilisateurAuthentifié = utilisateurAuthentifié;
    }
    private Set<Utilisateur> listUtilisateurs = new HashSet<>();
    @Override
    public String toString() {
        return "MyDesktopPlanner [listUtilisateurs=" + listUtilisateurs + "]";
    }
    public Set<Utilisateur> getListUtilisateurs() {
        return listUtilisateurs;
    }
    public void setListUtilisateurs(Set<Utilisateur> listUtilisateurs) {
        this.listUtilisateurs = listUtilisateurs;
    }

   public boolean rechercheUtilisateur(String pseudo){  //recherche un utilisateur dans la liste des utilisateurs et retourne vrai s'il est inscrit
     boolean trouv = false;
     
     for (Iterator<Utilisateur> iterator = listUtilisateurs.iterator(); iterator.hasNext();){
        Utilisateur u = (Utilisateur) iterator.next();
        if (u.getPseudo() == pseudo){
            trouv = true;
        }
     }
    return trouv;
   }
    public void ajouterUtilisateur(Utilisateur utilisateur){
        listUtilisateurs.add(utilisateur);
    }
    public Utilisateur getUtilisateurParPseudo(String pseudo) {
        for (Utilisateur u : listUtilisateurs) {
            if (u.getPseudo().equals(pseudo)) {
                return u;
            }
        }
        return null; // aucun utilisateur n'est trouvé
    }

    public Utilisateur authentification(String pseudo){
        try{
            if (listUtilisateurs.contains(getUtilisateurParPseudo(pseudo))== false){
                throw new UtilisateurIntrouvableException() ;
            }
            else {
                System.out.println("> Utilisateur authentifié avec succès.");
                System.out.println("            **Calendrier**          ");
                System.out.println(getUtilisateurParPseudo(pseudo).getCalendrierPerso());
                utilisateurAuthentifié = getUtilisateurParPseudo(pseudo);
                return (getUtilisateurParPseudo(pseudo));
            }
        }
        catch(UtilisateurIntrouvableException e){
            System.out.println("> Ce pseudo ne correspond à aucun utilisateur.");
            return(null);
        }
    }  
    public void planifier(){
        Planning planning = utilisateurAuthentifié.fixerPériodePlanning();
        utilisateurAuthentifié.fixerCréneauxLibres(planning);
        utilisateurAuthentifié.getCalendrierPerso().journéesCalendrier.addAll(planning.getJournéesPlanifiées());
        System.out.println(utilisateurAuthentifié);
    }  
}
