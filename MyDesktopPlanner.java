import java.util.List;
import java.util.ArrayList;

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
        if (listUtilisateurs.get(i) != pseudo ){
            i++;
        }
        else{
            return true;
        }
       }
       return trouv;
    }

    public Utilisateur authentification(String pseudo, String motDePasse){
        if (utilisateurInscrit(pseudo)==false){
            System.out.println("\nErreur d'authentification: cet utilisateur n'est pas encore inscrit.");
            return null;
        }
        else{
            Client c = getClientByUsername(nomUtilisateur);
            if (c.getCompte().getMotDePasse() == motDePasse){
                System.out.println("\n>> Client connecté.");
                return c;
            }
            else{
                System.out.println("\n>> Mot de passe incorrect.");
                return null;
            }
        }
    }
    public boolean estClient(Client client){
        boolean trouv = false;
        int i = 0;
       while ( i < listeClients.length && !trouv){
        if ((listeClients[i].getPrenom() != client.getPrenom()) | (listeClients[i].getNom() != client.getNom())){
            i++;
        }
        else{
            return true;
        }
       }
       return trouv;
    }
    public boolean nomUtilisateurPris(Client client){
        boolean trouv = false;
        int i = 0;
       while ( i < listeClients.length && !trouv){
        if (listeClients[i].getCompte().getNomUtilisateur() != client.getCompte().getNomUtilisateur()){
            i++;
        }
        else{
            return true;
        }
       }
       return trouv;
    }
    public void consultClients(){
        for( int i = 0 ; i < listeClients.length ; i++){
            listeClients[i].clientInfo();
        }
    }
}
