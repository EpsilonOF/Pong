package gui;

import javafx.scene.layout.Pane;
import model.CourtGrandesRaquettes;
import javafx.animation.AnimationTimer;

public class ModeGrandesRaquettes extends ModeDeJeu {
    
    public ModeGrandesRaquettes(Pane root, CourtGrandesRaquettes court, SceneAll sceneAll) {
        super(root, court, sceneAll);
        mode = "GrandesRaquettes";
    }

    @Override
    public void animate() {
        new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                court.update((now - last) * 1.0e-9); // convert nanoseconds to seconds
                last = now;
                raquette.setY(court.getRacketA() * scale);
                racketB.setY(court.getRacketB() * scale);
                ball.setX(court.getBallX() * scale + xMargin);
                ball.setY(court.getBallY() * scale);

                // on réduit bien la taille des raquettes
                raquette.setFitHeight(court.getRacketSize() * scale);
                racketB.setHeight(court.getRacketSize() * scale);

                score.setText(court.getScorePlayerA() + " - " + court.getScorePlayerB());
            }
        }.start();
    }
}
