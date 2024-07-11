package model;
public class CourtR extends Court{

    private final RacketController playerA;
     
    public CourtR(RacketController playerA, double width, double height) {
        super(playerA,null,width, height);
        this.playerA = playerA;
        reset();
    }

    @Override
    public void update(double deltaT){
        super.SpeedUp(deltaT); 

        switch (playerA.getState()) {
            case GOING_UP:
                racketA -= racketSpeed * deltaT;
                if (racketA < 0.0) racketA = 0.0;
                break;
            case IDLE:
                break;
            case GOING_DOWN:
                racketA += racketSpeed * deltaT;
                if (racketA + racketSize > height) racketA = height - racketSize;
                break;

            case GOING_LEFT:
                break;
            case GOING_RIGHT:
                break;
        }
        if (getBallY() > getRacketB()){
            setRacketB(getRacketB() + getRacketSpeed() * deltaT * getCoefB());
            setCoefB(0.4);
            if (getRacketB() + getRacketSpeed() * deltaT * getCoefB() > getHeight()){
                setRacketB(getHeight() - getRacketSize());
            }
        }
        else if (getBallY() < getRacketB()){
            setRacketB(getRacketB() - getRacketSpeed() * deltaT * getCoefB()) ;
            setCoefB(0.4);
            if (getRacketB() < 0.0){
                setRacketB(0.0);

            }
        }
        else {
            setCoefB(0.4);
        }

        if (updateBall(deltaT))
            reset();
    }
    
}


