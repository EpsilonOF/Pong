package gui;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.scene.paint.Color;



public class Play extends View {
    private Button play, backToMenu;
    private Text touches;
    private Text commande;
    Text test;
    public Play(Pane root, SceneAll sceneAll, String titre, String mode, boolean j4){
        super(root, sceneAll, titre);
        play = new Button("Jouer");
        play.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        play.setLayoutX(500);
        play.setLayoutY(400);
        play.setOnAction(event -> {
            switch(mode){
                case "Classique": 
                    sceneAll.goModeClassique(root);
                return;
                case "GRaquettes": 
                    sceneAll.goModeGrandeRaquettes(root);
                return;
                case "Vitesse": 
                    sceneAll.goModeVitesse(root);
                return;
                case "DeuxBoules": 
                    sceneAll.goModeDeuxBoules(root);
                return;
                case "4Raquettes":
                    if(j4){
                        sceneAll.goMode4Raquettes4(root);
                    }else sceneAll.goMode4Raquettes(root);
                return;
                case "Robot":
                    sceneAll.goModeRobots(root);
                return;
            }
        });
        if(mode=="4Raquettes" && j4){
            commande = new Text("Commandes :\n- Joueur 1 (gauche) : "+Fichiers.readLigne(2, Conn.fichier)+ " et "+Fichiers.readLigne(3, Conn.fichier)+"\n-Joueur 2 (haut) :  Q et D\n- Joueur 3 (droite) : "+Fichiers.readLigne(4, Conn.fichier)+" et "+Fichiers.readLigne(5, Conn.fichier)+"\n-Joueur 4 (bas) : K et M");
            commande.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        }else if(mode=="4Raquettes"){
            commande = new Text("Commandes :\n- Joueur 1 : "+Fichiers.readLigne(2, Conn.fichier)+ ", "+Fichiers.readLigne(3, Conn.fichier)+" et Q,D\n- Joueur 2 : "+Fichiers.readLigne(4, Conn.fichier)+", "+Fichiers.readLigne(5, Conn.fichier)+" et K,M");
            commande.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        }else{
            commande = new Text("Commandes :\n- Joueur 1 : "+Fichiers.readLigne(2, Conn.fichier)+ " et "+Fichiers.readLigne(3, Conn.fichier)+"\n- Joueur 2 : "+Fichiers.readLigne(4, Conn.fichier)+" et "+Fichiers.readLigne(5, Conn.fichier));
            commande.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        }
        commande.setFill(Color.BLACK);
        commande.setX(1000 * scale / 2);
        commande.setY(600 * scale / 3);
        touches = new Text("Touches pour jouer : \n");
        touches.setLayoutX(100);
        touches.setLayoutY(100);
        backToMenu = new Button("Menu");
        backToMenu.setLayoutX(5);
        backToMenu.setLayoutY(5);
        backToMenu.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        backToMenu.setOnAction(event -> {
            sceneAll.goMenu(root);
        });
        root.getChildren().addAll(play, commande, backToMenu);
    }
    
}
