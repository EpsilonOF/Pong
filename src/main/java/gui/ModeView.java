package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.*;
import javafx.stage.Stage;

public class ModeView extends View {

    private final Button menu, raquettes, vitesse, deuxBoules, classique, raquettes4, robot, unJoueur, deuxJoueurs, quatresJoueurs;
    private Stage stage;
    private Scene scene;
    public boolean j4 = false;

    public void goPlay(String titre){
        root.getChildren().clear();
        var play = new Play(root, sceneAll, "Play", titre, j4);
        try{
            stage.setScene(scene);
            stage.show();
            play.animate();
        }catch(NullPointerException e){}

    }
    public ModeView(Pane root, SceneAll sceneAll, String titre) {
        super(root, sceneAll, titre);
        title.setX(1000 * scale / 2 - 300);
        classique = new Button("Mode classique");
        classique.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        classique.setLayoutX(1000 * scale / 2 - 450);
        classique.setLayoutY(600 * scale / 2);
        classique.setOnAction(event -> {
            goPlay("Classique");
        });
        raquettes = new Button("Mode grandes raquettes");
        raquettes.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        raquettes.setLayoutX(1000 * scale / 2 - 200);
        raquettes.setLayoutY(600 * scale / 2);
        raquettes.setOnAction(event -> {
            goPlay("GRaquettes");
        });

        vitesse = new Button("Mode vitesse");
        vitesse.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        vitesse.setLayoutX(1000 * scale / 2 + 150);
        vitesse.setLayoutY(600 * scale / 2);
        vitesse.setOnAction(event -> {
            goPlay("Vitesse");
        });
        
        deuxBoules = new Button("Mode deux boules");
        deuxBoules.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        deuxBoules.setLayoutX(1000 * scale / 2 + 400);
        deuxBoules.setLayoutY(600 * scale / 2);
        deuxBoules.setOnAction(event -> {
            goPlay("DeuxBoules");
        });       
        raquettes4 = new Button("Mode 4 raquettes");
        raquettes4.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        raquettes4.setLayoutX(1000 * scale / 2 - 200);
        raquettes4.setLayoutY(700 * scale / 2);
        raquettes4.setOnAction(event -> {
            goPlay("4Raquettes");
        });
        robot = new Button("Mode contre un bot");
        robot.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        robot.setLayoutX(1000 * scale / 2 + 150);
        robot.setLayoutY(700 * scale / 2);
        robot.setOnAction(event -> {
            goPlay("Robot");
        });
        menu = new Button("Menu");
        menu.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        menu.setLayoutX(5);
        menu.setLayoutY(5);
        menu.setOnAction(event -> {
            sceneAll.goMenu(root);
        });
        raquettes.setDisable(true);
        vitesse.setDisable(true);
        deuxBoules.setDisable(true);
        classique.setDisable(true);
        raquettes4.setDisable(true);
        robot.setDisable(true);
        unJoueur = new Button("1 joueur");
        unJoueur.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        unJoueur.setLayoutX(1000 * scale / 2 - 50);
        unJoueur.setLayoutY(900 * scale / 2);
        unJoueur.setOnAction( event -> {
            raquettes.setDisable(true);
            vitesse.setDisable(true);
            deuxBoules.setDisable(true);
            classique.setDisable(true);
            raquettes4.setDisable(true);
            robot.setDisable(false);
            j4 = false;
        });
        deuxJoueurs = new Button("2 joueurs");
        deuxJoueurs.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        deuxJoueurs.setLayoutX(1000 * scale / 2 + 90);
        deuxJoueurs.setLayoutY(900 * scale / 2);
        deuxJoueurs.setOnAction(event -> {
            raquettes.setDisable(false);
            vitesse.setDisable(false);
            deuxBoules.setDisable(false);
            classique.setDisable(false);
            raquettes4.setDisable(false);
            robot.setDisable(true);
            j4 = false;
        });
        quatresJoueurs = new Button("4 joueurs");
        quatresJoueurs.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        quatresJoueurs.setLayoutX(1000 * scale / 2 + 240);
        quatresJoueurs.setLayoutY(900 * scale / 2);
        quatresJoueurs.setOnAction(event -> {
            raquettes.setDisable(true);
            vitesse.setDisable(true);
            deuxBoules.setDisable(true);
            classique.setDisable(true);
            raquettes4.setDisable(false);
            robot.setDisable(true);
            j4 = true;
        });
        root.getChildren().addAll(title, menu, raquettes, vitesse, deuxBoules, classique, raquettes4, robot, unJoueur, deuxJoueurs, quatresJoueurs);
    }
}
