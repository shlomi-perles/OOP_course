/**
 * this class implement the runner ship
 */
public class RunnerShip extends EnemyShip {
    private final static double TELEPORT_RANGE = 0.25;
    private final static double TELEPORT_ANGLE = 0.23;


    /**
     * this function implement runner ship behavior
     *
     * @param game SpaceWars object contain game's data
     */
    public void actionByShipType(SpaceWars game) {
        int angle = 0;
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angleToClosestShip = getPhysics().angleTo(closestShip.getPhysics());
        double distanceToClosestShip = getPhysics().distanceFrom(closestShip.getPhysics());

        if (angleToClosestShip > 0) {
            --angle;
        } else if (angleToClosestShip < 0) {
            ++angle;
        }

        getPhysics().move(true, angle);

        if (Math.abs(angleToClosestShip) <= TELEPORT_ANGLE && distanceToClosestShip <= TELEPORT_RANGE) {
            teleport();
        }
    }
}
