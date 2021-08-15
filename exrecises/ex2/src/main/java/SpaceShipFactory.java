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
        for (int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "h":
                    HumanShip humanShip = new HumanShip();
                    ships[i] = humanShip;
                    break;

                case "d":
                    DrunkardShip drunkShip = new DrunkardShip();
                    ships[i] = drunkShip;
                    break;

                case "r":
                    RunnerShip runnerShip = new RunnerShip();
                    ships[i] = runnerShip;
                    break;

                case "a":
                    AggressiveShip aggressiveShip = new AggressiveShip();
                    ships[i] = aggressiveShip;
                    break;

                case "b":
                    BasherShip basherShip = new BasherShip();
                    ships[i] = basherShip;
                    break;

                case "s":
                    SpecialShip specialShip = new SpecialShip();
                    ships[i] = specialShip;
                    break;
                default:
                    ships[i] = null;
                    break;
            }
        }
        return ships;
    }
}
