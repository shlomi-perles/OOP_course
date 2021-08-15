import java.util.Random;

/**
 * this class implement the drunkard ship
 */
public class DrunkardShip extends EnemyShip {
    private final static int HICCUP_FREQUENCY = 60;

    /**
     * this function implement drunkard ship behavior
     *
     * @param game SpaceWars object contain game's data
     */
    public void actionByShipType(SpaceWars game) {
        Random rand = new Random();
        getPhysics().move(rand.nextBoolean(), 1);
        if (rand.nextInt(HICCUP_FREQUENCY) == 0) fire(game);

    }
}
