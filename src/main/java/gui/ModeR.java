package gui;

import javafx.scene.layout.Pane;
import model.CourtR;
import javafx.animation.AnimationTimer;


public class ModeR extends ModeDeJeu {

	public ModeR(Pane root, CourtR court, SceneAll sceneAll) {
		super(root, court, sceneAll);
		mode = "Robot";

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
                racketB.setY(court.getBallY() * scale + xMargin - 65);
                ball.setX(court.getBallX() * scale + xMargin);
                ball.setY(court.getBallY() * scale);

                score.setText(court.getScorePlayerA() + " - " + court.getScorePlayerB());
                argent.setText(String.valueOf(Conn.argent()));
            }
        }.start();
    }
}