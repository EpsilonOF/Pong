package gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Connexion extends View{
    private final Button menu, submit;
    private final Text labelp, labelmdp, erreur;
    private final TextField pseudo;
    private final PasswordField mdp;
    public Connexion(Pane root, SceneAll sceneAll, String titre) {
        super(root, sceneAll, titre);
        
        pseudo = new TextField ();
        mdp = new PasswordField();

        pseudo.setLayoutX(1000*scale /2-100);
        pseudo.setLayoutY(600*scale /2);

        mdp.setLayoutX(1000*scale /2-100);
        mdp.setLayoutY(600*scale /2+30);


        labelp= new Text("Pseudo :");
        labelp.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 20));
        labelp.setFill(Color.BLACK);
        labelp.setX(1000 * scale / 2-260);
        labelp.setY(600 * scale / 2+17);

        labelmdp = new Text("Mot de passe :");
        labelmdp.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 20));
        labelmdp.setFill(Color.BLACK);
        labelmdp.setX(1000 * scale / 2-260);
        labelmdp.setY(600 * scale / 2+47);

        erreur  = new Text("");
        erreur.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 20));
        erreur.setFill(Color.BLACK);
        erreur.setX(1000 * scale / 2-100);
        erreur.setY(600 * scale / 2-10);

        menu = new Button("Menu");
        menu.setLayoutX(5);
        menu.setLayoutY(5);
        menu.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        menu.setOnAction(event -> {
            sceneAll.goMenu(this.root);
        });

        submit = new Button("Entrer");
        submit.setLayoutX(1000 * scale / 2 +200);
        submit.setLayoutY(600 * scale / 2);
        submit.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        submit.setOnAction(event ->{
            if (!(pseudo.getText() != null && !pseudo.getText().isEmpty())) {
                erreur.setText("il n'y a pas de pseudo");

            }else if(!(mdp.getText() != null && !mdp.getText().isEmpty())) {
                erreur.setText("il n'y a pas de mdp");
            }else{
                if(!(Fichiers.readLigne(1,"profils/"+pseudo.getText()).equals(mdp.getText()))){
                    erreur.setText("Le mot de passe ou le profil est incorrect");
                }else{
                    mdp.clear();
                    erreur.setText("Bienvenue "+ pseudo.getText()+'!');
                    Conn.connexion(pseudo.getText());
                    pseudo.clear();
                }
            }

        });

        

        
        root.getChildren().addAll(title, menu, labelmdp,labelp ,mdp, pseudo, submit, erreur);
    }
}
