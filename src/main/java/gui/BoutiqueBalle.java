package gui;

import javafx.scene.layout.Pane;

public class BoutiqueBalle extends Boutique{
    public BoutiqueBalle(Pane root, SceneAll sceneAll, String titre){
        super(root,sceneAll,titre, "Balle",8);
        text1.setText("Eternel Rose\nPrix : 1000P");
        text2.setText("Lebron James\nPrix : 2000P");
        text3.setText("Yannick Noah\nPrix : 3000P");
        text4.setText("Tempete de Glace\nPrix : 4000P");
        suivant.setOnAction((event) ->{
            sceneAll.goBoutiqueRaquette(root);
        });
        

    }
}
