package gui;

import javafx.scene.layout.Pane;

public class BoutiqueRaquette extends Boutique {
    public BoutiqueRaquette(Pane root, SceneAll sceneAll, String titre){
        super(root,sceneAll,titre, "Raquette",13);
        text1.setText("Deep Dark\nPrix : 1000P");
        text2.setText("Miraculous\nPrix : 2000P");
        text3.setText("0/20\nPrix : 3000P");
        text4.setText("Excalipong\nPrix : 4000P");
        suivant.setOnAction((event) ->{
            sceneAll.goBoutiqueBalle(this.root);
        });
        

    }
}
