package Noyau  ;

import java.util.*;
import java.awt.Color;
import java.io.*;
import org.w3c.dom.css.RGBColor;
import java.time.*;

public class MyDesktopPlanner implements Serializable {
    private static final String fileName = "fichierDémo.dat";
    private ArrayList<Utilisateur> listUtilisateurs = new ArrayList<>();

    public void chargerUtilisateursFichier() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            listUtilisateurs = (ArrayList<Utilisateur>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users from file: " + e.getMessage());
        }
    }

    public void updateUser(Utilisateur updatedUser) {
        int index = signedUp(updatedUser.getPseudo());
        if (index != -1) {
            listUtilisateurs.set(index, updatedUser);
            sauvegarderUtilisateursFichier(); // Save the updated users list to the file
        } else {
            System.err.println("Invalid index for user update.");
        }
    }

    public int signedUp(String pseudo) {
        for (int i = 0; i < listUtilisateurs.size(); i++) {
            if (listUtilisateurs.get(i).getPseudo().equals(pseudo)) {
                return i;
            }
        }
        return -1; // Return -1 if user not found
    }

    public void sauvegarderUtilisateursFichier() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(listUtilisateurs);
            System.out.println("Sauvegardé");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des utilisateurs" + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "MyDesktopPlanner [listUtilisateurs=" + listUtilisateurs + "]";
    }

    public ArrayList<Utilisateur> getListUtilisateurs() {
        return listUtilisateurs;
    }

    public void setListUtilisateurs(ArrayList<Utilisateur> listUtilisateurs) {
        this.listUtilisateurs = listUtilisateurs;
    }

    public boolean rechercheUtilisateur(String pseudo) {
        return listUtilisateurs.contains(new Utilisateur(pseudo));
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        boolean userExists = false;
        for (Utilisateur existingUser : listUtilisateurs) {
            if (existingUser.getPseudo().equals(utilisateur.getPseudo())) {
                userExists = true;
                break;
            }
        }

        if (!userExists) {
            utilisateur.setPlanner(this);
            listUtilisateurs.add(utilisateur);
            sauvegarderUtilisateursFichier(); // Save the updated users list to the file
            System.out.println("Utilisateur créé avec succès.");
        } else {
            System.out.println("Un utilisateur avec ce pseudo existe déjà.\nVeuillez ressaisir un pseudo.");
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

    // retourne l'objet Utilisateur qui est authentifié
    public Utilisateur authentification(String pseudo) {
        try {
            if (listUtilisateurs.contains(getUtilisateurParPseudo(pseudo)) == false) {
                throw new UtilisateurIntrouvableException();
            } else {
                System.out.println("> Utilisateur authentifié avec succès.");
                System.out.println("            **Calendrier**          ");
                System.out.println(getUtilisateurParPseudo(pseudo).getCalendrierPerso());
                System.out.println("            **Mes Plannings**          ");
                System.out.println(getUtilisateurParPseudo(pseudo).getHitoriqueProjets());
                return (getUtilisateurParPseudo(pseudo));
            }
        } catch (UtilisateurIntrouvableException e) {
            System.out.println("> Ce pseudo ne correspond à aucun utilisateur.");
            return (null);
        }
    }
}