import java.util.*;

public class MyDesktopPlanner {
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
        return null; // no matching Utilisateur found
    }
    public Utilisateur authentification(String pseudo){
        try{
            if (listUtilisateurs.contains(getUtilisateurParPseudo(pseudo))== false){
                throw new UtilisateurIntrouvableException() ;
            }
            else {
                return (getUtilisateurParPseudo(pseudo));
            }
        }
        catch(UtilisateurIntrouvableException e){
            System.out.println("Ce pseudo ne correspond à aucun utilisateur.");
            return(null);
        }
    }
    
}
