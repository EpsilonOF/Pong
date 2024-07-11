package gui;

import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.TextField;


public class ParameterView extends View {

    private final Button menu, valider;
    private final Text commande,labelh1,labelh2,labelb1,labelb2;
    private final TextField haut1,bas1,haut2,bas2;

    public ParameterView(Pane root, SceneAll sceneAll, String titre) {
        super(root, sceneAll, titre);

        haut1 = new TextField();
        haut1.setLayoutX(1000 * scale / 2);
        haut1.setLayoutY(600 * scale / 2);
        labelh1 = new Text("Joueur 1 UP :");
        labelh1.setLayoutX(1000 * scale / 2- 150);
        labelh1.setLayoutY(600 * scale / 2+15);
        labelh1.setFont(Font.font("Verdana", 20));

        haut2 = new TextField();
        haut2.setLayoutX(1000 * scale / 2);
        haut2.setLayoutY(600 * scale / 2+60);
        labelh2 = new Text("Joueur 2 UP :");
        labelh2.setLayoutX(1000 * scale / 2- 150);
        labelh2.setLayoutY(600 * scale / 2+75);
        labelh2.setFont(Font.font("Verdana", 20));

        bas1 = new TextField();
        bas1.setLayoutX(1000 * scale / 2);
        bas1.setLayoutY(600 * scale / 2+30);
        labelb1 = new Text("Joueur 1 DOWN :");
        labelb1.setLayoutX(1000 * scale / 2- 189);
        labelb1.setLayoutY(600 * scale / 2+45);
        labelb1.setFont(Font.font("Verdana", 20));

        bas2 = new TextField();
        bas2.setLayoutX(1000 * scale / 2);
        bas2.setLayoutY(600 * scale / 2+90);
        labelb2 = new Text("Joueur 2 DOWN :");
        labelb2.setLayoutX(1000 * scale / 2- 189);
        labelb2.setLayoutY(600 * scale / 2+105);
        labelb2.setFont(Font.font("Verdana", 20));



        commande = new Text("Commandes :\n- Joueur 1 : "+Fichiers.readLigne(2, Conn.fichier)+ " et "+Fichiers.readLigne(3, Conn.fichier)+"\n- Joueur 2 : "+Fichiers.readLigne(4, Conn.fichier)+" et "+Fichiers.readLigne(5, Conn.fichier));
        commande.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        commande.setFill(Color.BLACK);
        commande.setX(1000 * scale / 2-200);
        commande.setY(600 * scale / 2-200);

        menu = new Button("Menu");
        menu.setLayoutX(5);
        menu.setLayoutY(5);
        menu.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        menu.setOnAction(event -> {
            sceneAll.goMenu(this.root);
        });

        valider = new Button("Valider");
        valider.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        valider.setLayoutX(1000*scale/2);
        valider.setLayoutY(600*scale/2+120);
        valider.setOnAction(event -> {
            if (!haut1.getText().isEmpty()) {
                try {
                    Fichiers.remplacerLigne(2, haut1.getText(), Conn.fichier);
                } catch (IOException e) {
                    System.out.println("erreur");
                }
            }
            if (!bas1.getText().isEmpty()) {
                try {
                    Fichiers.remplacerLigne(3, bas1.getText(), Conn.fichier);
                } catch (IOException e) {
                    System.out.println("erreur");
                }
            }
            if (!haut2.getText().isEmpty()) {
                try {
                    Fichiers.remplacerLigne(4, haut2.getText(), Conn.fichier);
                } catch (IOException e) {
                    System.out.println("erreur");
                }
            }
            if (!bas2.getText().isEmpty()) {
                try {
                    Fichiers.remplacerLigne(5, bas2.getText(),Conn.fichier);
                } catch (IOException e) {
                    System.out.println("erreur");
                }
            }
        });
        
        root.getChildren().addAll(title, menu, commande,haut1,haut2,bas1,bas2,valider,labelb1,labelb2,labelh1,labelh2);
    }
}