import oop.ex2.GameGUI;

public class RunnerShip extends SpaceShip {
    private final static double TELEPORT_RANGE = 0.25;
    private final static double TELEPORT_ANGLE = 0.23;


    public void actionByShipType(SpaceWars game) {
        int angle = 0;
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angleToClosestShip = getPhysics().angleTo(closestShip.getPhysics());
        double distanceToClosestShip = getPhysics().distanceFrom(closestShip.getPhysics());

        if (angleToClosestShip > 0) {
            angle++;
        } else if (angleToClosestShip < 0) {
            angle--;
        }

        getPhysics().move(true, angle);

        if (Math.abs(angleToClosestShip) <= TELEPORT_ANGLE && angleToClosestShip <= TELEPORT_RANGE) {
            teleport();
        }
    }

    public void setShipImageNoShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE);
    }

    public void setShipImageShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD);
    }
}
