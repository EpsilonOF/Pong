package gui;

import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Profil extends View{
    private final Button menu, creer, connecter, deconnecter, submit;
    private final PasswordField mdp;
    private final Text erreur;
    public Profil(Pane root, SceneAll sceneAll, String titre) {
        super(root, sceneAll, titre);

        menu = new Button("Menu");
        menu.setLayoutX(5);
        menu.setLayoutY(5);
        menu.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        menu.setOnAction(event -> {
            sceneAll.goMenu(this.root);
        });

        creer = new Button("Nouveau profil");
        creer.setLayoutX(1000 * scale / 2 +200);
        creer.setLayoutY(600 * scale / 2);
        creer.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        creer.setOnAction(event ->{
            sceneAll.goCreerProfil(this.root);
        });

        connecter = new Button("Se connecter");
        connecter.setLayoutX(1000 * scale / 2 -100);
        connecter.setLayoutY(600 * scale / 2);
        connecter.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        connecter.setOnAction(event ->{
            sceneAll.goConnexion(this.root);
        });
        deconnecter = new Button("Déconnexion");
        deconnecter.setLayoutX(1000 * scale / 2 -100);
        deconnecter.setLayoutY(600 * scale / 2+100);
        deconnecter.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        deconnecter.setOnAction(event ->{
            Conn.deconnexion();
            sceneAll.goProfil(this.root);
        });

        erreur = new Text("Changer de mot de passe");
        erreur.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        erreur.setLayoutX(1000 * scale / 2 -100);
        erreur.setLayoutY(600 * scale / 2-20);


        mdp = new PasswordField();
        mdp.setLayoutX(1000 * scale / 2 -100);
        mdp.setLayoutY(600 * scale / 2);

        submit =new Button("Valider");
        submit.setLayoutX(1000 * scale / 2 -100);
        submit.setLayoutY(600 * scale / 2+50);
        submit.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        submit.setOnAction(event ->{
            if(!(mdp.getText() != null && !mdp.getText().isEmpty())) {
                erreur.setText("Vous n'avez pas entré de mot de passe");
            } else if(mdp.getText().length() <6) {
                erreur.setText("Le nouveau mot de passe est trop court");
            }else{
                try {
                    Fichiers.remplacerLigne(1,mdp.getText(),Conn.fichier);
                } catch (IOException e) {
                    System.out.println("erreur");
                }
                mdp.clear();
                erreur.setText("Votre mot de passe a bien été modifié");
            }

        });

        



        if(Conn.estConnecte){
            title.setText(Conn.profil());
            root.getChildren().addAll(title, menu,deconnecter, mdp, erreur, submit);
        }else{
        root.getChildren().addAll(title, menu,creer, connecter);
        }
    }
}
