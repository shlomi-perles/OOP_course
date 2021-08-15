/**
 * this class implement the aggressive ship
 */
public class AggressiveShip extends EnemyShip {
    private final static double SHOOTING_ANGLE = 0.21;

    /**
     * this method implement aggressive ship behavior
     *
     * @param game SpaceWars object contain game's data
     */
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
}
