package gui;

import javafx.scene.image.Image;

public class Conn {
    public static String fichier= "profils/Invite";
    public static boolean estConnecte = false;
    public static Image skinActuelBalle = new Image("file:images/Balle/balle1.png");
    public static Image skinActuelRaquette = new Image("file:images/Raquette/skin1.png");
    public static void connexion(String file){
        fichier = "profils/"+file;
        estConnecte = true;
    }

    public static void deconnexion(){
        fichier = "profils/Invite";
        estConnecte = false;

    }

    public static String profil(){
        return fichier.substring(8);
    }

    public static int argent(){
        return (int)Integer.valueOf(Fichiers.readLigne(6,fichier).substring(10));
    }

    
}
