/**
 * this class implement the special ship
 */
public class SpecialShip extends EnemyShip {
    /**
     * crazy spaceship! The spacecraft performs loops and fires in all directions.
     *
     * @param game SpaceWars object contain game's data
     */
    public void actionByShipType(SpaceWars game) {
        getPhysics().move(false, 1);
        fire(game);
    }
}
