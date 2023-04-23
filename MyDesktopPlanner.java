import java.util.List;

public class MyDesktopPlanner {
    private List<Utilisateur> listUtilisateurs;
    public List<Utilisateur> getListUtilisateurs() {
        return listUtilisateurs;
    }
    public void setListUtilisateurs(List<Utilisateur> listUtilisateurs) {
        this.listUtilisateurs = listUtilisateurs;
    }
    public boolean utilisateurInscrit(String pseudo){
        boolean trouv = false;
        int i = 0;
       while ( i < listUtilisateurs.size() && !trouv){
        if (listUtilisateurs.get(i).getPseudo() != pseudo ){
            i++;
        }
        else{
            return true;
        }
       }
       return trouv;
    }
    public Utilisateur getUtilisateurParPseudo(String pseudo){
            int i =0, trouv = 0 ;
            while ( i < listUtilisateurs.size() && trouv == 0){
                if (listUtilisateurs.get(i).getPseudo() != pseudo ){
                    i++;
                }
                else{
                    trouv = 1 ; 
                }
            }
            return (listUtilisateurs.get(i));
    }
    public Utilisateur authentification(String pseudo){
        try{
            if (utilisateurInscrit(pseudo)== false){
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
