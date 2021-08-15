/**
 * this class implement the aggressive ship
 */
public class BasherShip extends EnemyShip {
    private final static double SHIELD_RANGE = 0.21;

    /**
     * this function implement basher ship behavior
     *
     * @param game SpaceWars object contain game's data
     */
    public void actionByShipType(SpaceWars game) {
        int angle = 0;
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angleToClosestShip = getPhysics().angleTo(closestShip.getPhysics());

        if (angleToClosestShip > 0) {
            ++angle;
        } else if (angleToClosestShip < 0) {
            --angle;
        }

        getPhysics().move(true, angle);

        if (getPhysics().distanceFrom(closestShip.getPhysics()) <= SHIELD_RANGE) shieldOn();
    }
}
