package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.*; 
import javafx.scene.text.Text;
import java.io.IOException;
import javafx.scene.control.*;

public class Boutique extends View{
    protected Button boutique, buyskin1, buyskin2, buyskin3, buyskin4, equip1, equip2, equip3, equip4, suivant;
    protected final Text text1, text2, text3, text4, money;
    protected Slider slider;
    protected PorteFeuille pf;
    protected int ligne;
    protected String type;
    
    public Boutique(Pane root, SceneAll sceneAll, String titre, String type, int ligneDebut)  {
        super(root, sceneAll, titre);
        this.type = type;
        this.ligne = ligneDebut;

        title.setX(this.width/2-170);

        slider = new Slider(0,1000,0);
        pf= new PorteFeuille(new Banque());
        pf.ajouter(new Monnaie(Conn.argent()));

        Image image1 = new Image("file:images/"+type+"/skin1.png");
        ImageView skin1 = new ImageView(image1);
        skin1.setFitWidth(150);
        skin1.setFitHeight(150);
        skin1.setPreserveRatio(true);
        skin1.setX(1000 * scale / 2 - 300);
        skin1.setY(900 * scale / 2 - 120);

        Image image2 = new Image("file:images/"+type+"/skin2.png");
        ImageView skin2 = new ImageView(image2);
        skin2.setFitWidth(150);
        skin2.setFitHeight(150);
        skin2.setPreserveRatio(true);
        skin2.setX(1000 * scale / 2 );
        skin2.setY(900 * scale / 2 - 120);

        Image image3 = new Image("file:images/"+type+"/skin3.png");
        ImageView skin3 = new ImageView(image3);
        skin3.setFitWidth(150);
        skin3.setFitHeight(150);
        skin3.setPreserveRatio(true);
        skin3.setX(1000 * scale / 2 + 300);
        skin3.setY(900 * scale / 2 - 120);

        Image image4 = new Image("file:images/"+type+"/skin4.png");
        ImageView skin4 = new ImageView(image4);
        skin4.setFitWidth(150);
        skin4.setFitHeight(150);
        skin4.setPreserveRatio(true);
        skin4.setX(1000 * scale / 2 + 600);
        skin4.setY(900 * scale / 2 - 120);
        
        Image image5 = new Image("file:images/coin.png");
        ImageView coin = new ImageView(image5);
        coin.setFitWidth(50);
        coin.setFitHeight(50);
        coin.setPreserveRatio(true);
        coin.setX(this.width - 50);
        coin.setY(0);

        text1 = new Text("");
        text1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        text1.setX(1000 * scale / 2 - 280);
        text1.setY(900 * scale / 2 + 70);
        text2 = new Text("");
        text2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        text2.setX(1000 * scale / 2 + 20);
        text2.setY(900 * scale / 2 + 70);
        text3 = new Text("");
        text3.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        text3.setX(1000 * scale / 2 + 320);
        text3.setY(900 * scale / 2 + 70);
        text4 = new Text("");
        text4.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        text4.setX(1000 * scale / 2 + 620);
        text4.setY(900 * scale / 2 + 70);

        money = new Text(String.valueOf(Conn.argent()));
        money.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        money.setX(this.width-125);
        money.setY(30);


        equip1 = new Button("Equiper");
        equip2 = new Button("Equiper");
        equip3 = new Button("Equiper");
        equip4 = new Button("Equiper");

        buyskin1 = new Button("Acheter");
        
        equip1.setLayoutX(1000 * scale / 2 - 280);
        equip1.setLayoutY(900 * scale / 2 + 150);
        equip1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        equip1.setOnAction(e -> {
            switch(type){
                case "Balle" :
                Conn.skinActuelBalle = image1; break;
                case "Raquette" : 
                Conn.skinActuelRaquette = image1; break;

            }
            equip1.setDisable(true);
            equip2.setDisable(false);
            equip3.setDisable(false);
            equip4.setDisable(false);
        });
        if(Fichiers.readLigne(ligne, Conn.fichier).equals("Skin 1 : true")){
            root.getChildren().add(equip1);
            
        }else{
            buyskin1.setLayoutX(1000 * scale / 2 - 280);
            buyskin1.setLayoutY(900 * scale / 2 + 150);
            buyskin1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            root.getChildren().add(buyskin1);
        }
        
        

        buyskin2 = new Button("Acheter");
        
        equip2.setLayoutX(1000 * scale / 2+20);
        equip2.setLayoutY(900 * scale / 2 + 150);
        equip2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        equip2.setOnAction(e -> {
                
            switch(type){
                case "Balle" :
                Conn.skinActuelBalle = image2; break;
                case "Raquette" : 
                Conn.skinActuelRaquette = image2; break;

            }
            equip1.setDisable(false);
            equip2.setDisable(true);
            equip3.setDisable(false);
            equip4.setDisable(false);
        });
        if(Fichiers.readLigne(ligne+1, Conn.fichier).equals("Skin 2 : true")){
            
            root.getChildren().add(equip2);

        }else{
            buyskin2.setLayoutX(1000 * scale / 2+20);
            buyskin2.setLayoutY(900 * scale / 2 + 150);
            buyskin2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            root.getChildren().add(buyskin2);
        }


        

        buyskin3 = new Button("Acheter");
        equip3.setLayoutX(1000 * scale / 2 + 320);
        equip3.setLayoutY(900 * scale / 2 + 150);
        equip3.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        equip3.setOnAction(e -> {
            switch(type){
                case "Balle" :
                Conn.skinActuelBalle = image3; break;
                case "Raquette" : 
                Conn.skinActuelRaquette = image3; break;

            }
            equip1.setDisable(false);
            equip2.setDisable(false);
            equip3.setDisable(true);
            equip4.setDisable(false);
        });

        if(Fichiers.readLigne(ligne+2, Conn.fichier).equals("Skin 3 : true")){
            
            root.getChildren().add(equip3);
            
        }else{
            buyskin3.setLayoutX(1000 * scale / 2 + 320);
            buyskin3.setLayoutY(900 * scale / 2 + 150);
            buyskin3.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            root.getChildren().add(buyskin3);
        }

        

        buyskin4 = new Button("Acheter");
            equip4.setLayoutX(1000 * scale / 2 + 620);
            equip4.setLayoutY(900 * scale / 2 + 150);
            equip4.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            
            equip4.setOnAction(e -> {
                switch(type){
                    case "Balle" :
                    Conn.skinActuelBalle = image4; break;
                    case "Raquette" : 
                    Conn.skinActuelRaquette = image4; break;

                }
                equip1.setDisable(false);
                equip2.setDisable(false);
                equip3.setDisable(false);
                equip4.setDisable(true);

                
            });
        if(Fichiers.readLigne(ligne+3, Conn.fichier).equals("Skin 4 : true")){
            root.getChildren().add(equip4);
            
        }else{
            buyskin4.setLayoutX(1000 * scale / 2 + 620);
            buyskin4.setLayoutY(900 * scale / 2 + 150);
            buyskin4.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

            root.getChildren().add(buyskin4);
        }
        boutique = new Button("Menu");
        boutique.setLayoutX(5);
        boutique.setLayoutY(5);
        boutique.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        boutique.setOnAction(event -> {
            sceneAll.goMenu(this.root);
        });


        buyskin1.setOnAction(event -> {
            root.getChildren().add(slider);
            slider.setLayoutX(1000 * scale / 2+200);
            slider.setLayoutY(900 * scale / 2 + 300);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            buyskin1.setDisable(true);
            buyskin2.setDisable(true);
            buyskin3.setDisable(true);
            buyskin4.setDisable(true);
            Button button = new Button("Payez en Pirate-Money");
            button.setLayoutX(1000 * scale / 2+200);
            button.setLayoutY(900 * scale / 2 + 350);
            button.setOnAction(e -> {
                if(pf.transaction(new Monnaie((int)slider.getValue()-1000))){
                    root.getChildren().remove(buyskin1);
                    root.getChildren().add(equip1);

                    try {
                        Fichiers.remplacerLigne(ligne,"Skin 1 : true","profils/"+Conn.profil());
                        int x =Conn.argent()+(int)slider.getValue()-1000;
                        Fichiers.remplacerLigne(6,"Monnaie : "+ x,"profils/"+Conn.profil());
                    } catch (IOException e1) {
                        System.out.println("Problème de fichier");
                    }
                }else{
                    Text texte1 = new Text("Pas assez d'argent");
                    texte1.setLayoutX(1000 * scale / 2 - 280);
                    texte1.setLayoutY(900 * scale / 2 + 250);
                    texte1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                    root.getChildren().add(texte1);
                    buyskin1.setDisable(false);
                }
                root.getChildren().remove(slider);
                root.getChildren().remove(button);
                buyskin2.setDisable(false);
                buyskin3.setDisable(false);
                buyskin4.setDisable(false);
                money.setText(String.valueOf(Conn.argent()));
            });
            root.getChildren().add(button);
        });


        buyskin2.setOnAction(event -> {
            root.getChildren().add(slider);
            slider.setLayoutX(1000 * scale / 2+200);
            slider.setLayoutY(900 * scale / 2 + 300);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            buyskin1.setDisable(true);
            buyskin2.setDisable(true);
            buyskin3.setDisable(true);
            buyskin4.setDisable(true);
            Button button = new Button("Payez en Pirate-Money");
            button.setLayoutX(1000 * scale / 2+200);
            button.setLayoutY(900 * scale / 2 + 350);
            button.setOnAction(e -> {
                if(pf.transaction(new Monnaie((int)slider.getValue()-2000))){
                    root.getChildren().remove(buyskin2);
                    root.getChildren().add(equip2);

                    try {
                        Fichiers.remplacerLigne(ligne +1,"Skin 2 : true","profils/"+Conn.profil());
                        int x =Conn.argent()+(int)slider.getValue()-2000;
                        Fichiers.remplacerLigne(6,"Monnaie : "+ x,"profils/"+Conn.profil());
                    } catch (IOException e1) {
                        System.out.println("Problème de fichier");
                    }
                }else{
                    Text texte1 = new Text("Pas assez d'argent");
                    texte1.setLayoutX(1000 * scale / 2 - 20);
                    texte1.setLayoutY(900 * scale / 2 + 150);
                    texte1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                    root.getChildren().add(texte1);
                    buyskin2.setDisable(false);
                }
                root.getChildren().remove(slider);
                root.getChildren().remove(button);
                buyskin1.setDisable(false);
                buyskin3.setDisable(false);
                buyskin4.setDisable(false);
                money.setText(String.valueOf(Conn.argent()));
            });
            root.getChildren().add(button);
        });

        buyskin3.setOnAction(event -> {
            root.getChildren().add(slider);
            slider.setLayoutX(1000 * scale / 2+200);
            slider.setLayoutY(900 * scale / 2 + 300);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            buyskin1.setDisable(true);
            buyskin2.setDisable(true);
            buyskin3.setDisable(true);
            buyskin4.setDisable(true);
            Button button = new Button("Payez en Pirate-Money");
            button.setLayoutX(1000 * scale / 2+200);
            button.setLayoutY(900 * scale / 2 + 350);
            button.setOnAction(e -> {
                if(pf.transaction(new Monnaie((int)slider.getValue()-3000))){
                    root.getChildren().remove(buyskin3);
                    root.getChildren().add(equip3);
                    try {
                        Fichiers.remplacerLigne(ligne+2,"Skin 3 : true","profils/"+Conn.profil());
                        int x =Conn.argent()+(int)slider.getValue()-3000;
                        Fichiers.remplacerLigne(6,"Monnaie : "+ x ,"profils/"+Conn.profil());
                    } catch (IOException e1) {
                        System.out.println("Problème de fichier");
                    }
                }else{
                    Text texte1 = new Text("Pas assez d'argent");
                    texte1.setLayoutX(1000 * scale / 2 + 320);
                    texte1.setLayoutY(900 * scale / 2 + 150);
                    texte1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                    root.getChildren().add(texte1);
                    buyskin3.setDisable(false);
                }
                root.getChildren().remove(slider);
                root.getChildren().remove(button);
                buyskin2.setDisable(false);
                buyskin1.setDisable(false);
                buyskin4.setDisable(false);
                money.setText(String.valueOf(Conn.argent()));
            });
            root.getChildren().add(button);
        });

        buyskin4.setOnAction(event -> {
            root.getChildren().add(slider);
            slider.setLayoutX(1000 * scale / 2+200);
            slider.setLayoutY(900 * scale / 2 + 300);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            buyskin1.setDisable(true);
            buyskin2.setDisable(true);
            buyskin3.setDisable(true);
            buyskin4.setDisable(true);
            Button button = new Button("Payez en Pirate-Money");
            button.setLayoutX(1000 * scale / 2+200);
            button.setLayoutY(900 * scale / 2 + 350);
            button.setOnAction(e -> {
                if(pf.transaction(new Monnaie((int)slider.getValue()-4000))){
                    root.getChildren().remove(buyskin4);
                    root.getChildren().add(equip4);
                    try {
                        Fichiers.remplacerLigne(ligne+3,"Skin 4 : true","profils/"+Conn.profil());
                        int x =Conn.argent()+(int)slider.getValue()-4000;
                        Fichiers.remplacerLigne(6,"Monnaie : "+x,"profils/"+Conn.profil());
                    } catch (IOException e1) {
                        System.out.println("Problème de fichier");
                    }
                }else{
                    Text texte1 = new Text("Pas assez d'argent");
                    texte1.setLayoutX(1000 * scale / 2 +620);
                    texte1.setLayoutY(900 * scale / 2 + 250);
                    texte1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                    root.getChildren().add(texte1);
                    buyskin4.setDisable(false);
                }
                root.getChildren().remove(slider);
                root.getChildren().remove(button);
                buyskin2.setDisable(false);
                buyskin3.setDisable(false);
                buyskin1.setDisable(false);
                money.setText(String.valueOf(Conn.argent()));
            });
            root.getChildren().add(button);
        });

        suivant  = new Button("Boutique suivante");
        suivant.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        suivant.setOnAction(event -> {
            sceneAll.goBoutiqueBalle(this.root);
        });

        suivant.setLayoutX(this.width-300);
        suivant.setLayoutY(this.height/2);

        
        root.getChildren().addAll(title,skin1,skin2,skin3,skin4, coin, text1, text2, text3, text4, money, boutique, suivant);
    }
}
