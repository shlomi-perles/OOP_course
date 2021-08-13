import oop.ex2.GameGUI;

public class BasherShip extends SpaceShip {
    private final static double SHIELD_RANGE = 0.21;

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

        if (getPhysics().distanceFrom(closestShip.getPhysics()) <= SHIELD_RANGE) shieldOn();
    }

    public void setShipImageNoShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE);
    }

    public void setShipImageShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD);
    }
}
