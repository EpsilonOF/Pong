package gui;

import javafx.scene.layout.Pane;
import model.CourtModeVitesse;

public class ModeVitesse extends ModeDeJeu {

    
    public ModeVitesse(Pane root, CourtModeVitesse court, SceneAll sceneAll) {
        super(root, court, sceneAll);
        mode = "Vitesse";
        
    }
}