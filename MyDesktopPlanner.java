import java.util.*;
import java.awt.Color;
import org.w3c.dom.css.RGBColor;
import java.time.* ;

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

    public boolean rechercheUtilisateur(String pseudo) {
        return listUtilisateurs.contains(new Utilisateur(pseudo));
    }
   public void ajouterUtilisateur(Utilisateur utilisateur){
    if(rechercheUtilisateur(utilisateur.getPseudo()))
    {
        System.out.println("Utilisateur créé avec succès.\nIntroduisez les catégories souhaitées, suivies de leur couleur (au format RGB séparé par des virgules). Tapez 'stop' pour arrêter la saisie.");
        Scanner scanner = new Scanner(System.in);
        String categorieNom;
        do {
            System.out.print("Nom de la catégorie : ");
            categorieNom = scanner.nextLine();
            if (!categorieNom.equalsIgnoreCase("stop")) {
                System.out.print("Couleur de la catégorie (au format RGB séparé par des virgules) : ");
                String[] rgbString = scanner.nextLine().split(",");
                int r = Integer.parseInt(rgbString[0].trim());
                int g = Integer.parseInt(rgbString[1].trim());
                int b = Integer.parseInt(rgbString[2].trim());
                Color couleur = new Color(r, g, b);
                Catégorie categorie = new Catégorie(categorieNom, couleur);
                utilisateur.listeCatégories.add(categorie);
            }
        } while (!categorieNom.equalsIgnoreCase("stop"));
        listUtilisateurs.add(utilisateur);

    }
    else{
        System.out.println("Un utilisateur avec ce pseudo existe déjà.\nVeuillez resaisir un pseudo");
    }
}
    public Utilisateur getUtilisateurParPseudo(String pseudo) {
        for (Utilisateur u : listUtilisateurs) {
            if (u.getPseudo().equals(pseudo)) {
                return u;
            }
        }
        return null; // aucun utilisateur n'est trouvé
    }
    //retourne l'objet Utilisateur qui est authentifié
    public Utilisateur authentification(String pseudo){
        try{
            if (listUtilisateurs.contains(getUtilisateurParPseudo(pseudo))== false){
                throw new UtilisateurIntrouvableException() ;
            }
            else {
                System.out.println("> Utilisateur authentifié avec succès.");
                System.out.println("            **Calendrier**          ");
                System.out.println(getUtilisateurParPseudo(pseudo).getCalendrierPerso());
                return (getUtilisateurParPseudo(pseudo));
            }
        }
        catch(UtilisateurIntrouvableException e){
            System.out.println("> Ce pseudo ne correspond à aucun utilisateur.");
            return(null);
        }
    }  
 
    
}


