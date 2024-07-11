package gui;
import javafx.scene.paint.Color;
import model.*;

public class Temporaire {
    public static double courtLargeur, courtLongueur, ballSpeedX, ballSpeedY, ballPosX, ballPosY, ballRadius,
    posRacketA, posRacketB, posRacketA2, posRacketB2, racketSize, ballSpeedX2, ballSpeedY2, ballPosX2, ballPosY2, racketThickness;
    public static int scoreA, scoreB;
    public static String mode;
    public static void reprendre(Court court){
        court.setBallX(ballPosX);
        court.setBallY(ballPosY);
        court.setBallSpeedX(ballSpeedX);
        court.setBallSpeedY(ballSpeedY);
        court.setRacketA(posRacketA);
        court.setRacketB(posRacketB);
        court.setScorePlayerA(scoreA);
        court.setScorePlayerB(scoreB);
    }
    public static void reprendreGR(CourtGrandesRaquettes court){
        reprendre(court);
        court.setRacketSize(racketSize);
    }
    public static void reprendre4(Court4Raquettes court){
        reprendre(court);
        court.setRacketA2(posRacketA2);
        court.setRacketB2(posRacketB2);
    }
    public static void reprendre44(Court4Raquettes4 court, Mode4Raquettes m){
        reprendre(court);
        court.setRacketA2(posRacketA2);
        court.setRacketB2(posRacketB2);
        m.racketA2.setFill(Color.RED);
        m.racketB2.setFill(Color.ORANGE);
    }
    public static void reprendreDB(CourtDeuxBoules court){
        reprendre(court);
        court.setBallX2(ballPosX2);
        court.setBallY2(ballPosY2);
        court.setBallSpeedX2(ballSpeedX2);
        court.setBallSpeedY2(ballSpeedY2);
    }
}
