import oop.ex2.GameGUI;

public class AggressiveShip extends SpaceShip {
    private final static double SHOOTING_ANGLE = 0.21;

    public void actionByShipType(SpaceWars game) {
        int angle = 0;
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angleToClosestShip = getPhysics().angleTo(closestShip.getPhysics());

        if (angleToClosestShip > 0) {
            angle++;
        } else if (angleToClosestShip < 0) {
            angle--;
        }

        getPhysics().move(true, angle);

        if (Math.abs(angleToClosestShip) <= SHOOTING_ANGLE) fire(game);
    }

    public void setShipImageNoShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE);
    }

    public void setShipImageShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD);
    }
}
