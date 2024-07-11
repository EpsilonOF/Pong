package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.effect.Glow; 
import javafx.scene.image.*; 
import javafx.scene.text.*;

public class MenuView extends View {

    private final Button boutique, parameter, mode,profil;

    public MenuView(Pane root, SceneAll sceneAll, String titre) {
        super(root, sceneAll, titre);

        Image image = new Image("https://cdn.pixabay.com/photo/2012/04/02/16/25/pong-24876_960_720.png");
        ImageView imageView = new ImageView(image); 
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);  
        imageView.setLayoutX(width/3 -300); 
        imageView.setLayoutY(height/3 -100);

        title.setLayoutX(width/3 -250);
        title.setLayoutY(height/3 -100);

        
        Glow glow = new Glow(); 
        glow.setLevel(10); 
        imageView.setEffect(glow);   
        imageView.setX(1000 * scale / 2 - 150);
        imageView.setY(600 * scale / 2 - 120);

        parameter = new Button("Parametres");
        parameter.setLayoutX(width -150);
        parameter.setLayoutY(0);
        parameter.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        parameter.setOnAction(event -> {
            sceneAll.goParameter(this.root);
        });
        
        mode = new Button("Modes de jeu");  
        mode.setLayoutX(width/3 + 300);
        mode.setLayoutY(height/2);
        mode.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        mode.setOnAction(event -> {
            sceneAll.goModesDeJeu(this.root);
        });
        profil = new Button("Profil");
        profil.setLayoutX(0);
        profil.setLayoutY(0);
        profil.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        profil.setOnAction(event -> {
            sceneAll.goProfil(this.root);
        });

        boutique = new Button("Boutique");
        boutique.setLayoutX(width/3+50);
        boutique.setLayoutY(height/2);
        boutique.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        boutique.setOnAction(event -> {
            sceneAll.goBoutiqueBalle(this.root);
        });

   
        root.getChildren().addAll(title, parameter, mode, profil, imageView,boutique);
    }
}