import oop.ex2.*;

/**
 * This class has a single static method. It is used by the supplied driver to create all the spaceship
 * objects according to the command line arguments.
 */
public class SpaceShipFactory {
    /**
     * The function create spaceship objects according to the command line arguments.
     *
     * @param args Program arguments.
     * @return Array filled with spaceships.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] ships = new SpaceShip[args.length];
        for (String space : args) {
            switch(space){
                case "h":

            }
        }
        return ships;
    }
}
