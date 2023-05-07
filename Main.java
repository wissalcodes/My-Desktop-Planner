public class Main {
    public static void main(String[] args){
        MyDesktopPlanner app = new MyDesktopPlanner() ; 
        Utilisateur user = new Utilisateur("Wissal",new Calendrier());
        app.ajouterUtilisateur(user);
        app.authentification("Wissal");
        app.planifier();
        //Programmer un ensemble 
    }
}
