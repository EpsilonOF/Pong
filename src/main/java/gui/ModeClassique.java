package gui;

import javafx.scene.layout.Pane;
import model.Court;

public class ModeClassique extends ModeDeJeu {

	public ModeClassique(Pane root, Court court, SceneAll sceneAll) {
		super(root, court, sceneAll);
		mode = "Classique";
	}
}