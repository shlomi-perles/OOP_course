import oop.ex2.GameGUI;

/**
 * this class implement the humen ship
 */
public class HumanShip extends SpaceShip {

    /**
     * set human ship image without shield
     */
    public void setShipImageNoShield() {
        setImage(GameGUI.SPACESHIP_IMAGE);
    }

    /**
     * set enemy ship image with a shield
     */
    public void setShipImageShield() {
        setImage(GameGUI.SPACESHIP_IMAGE_SHIELD);
    }

    /**
     * this function implement human ship behavior
     *
     * @param game SpaceWars object contain game's data
     */
    public void actionByShipType(SpaceWars game) {
        GameGUI gui = game.getGUI();
        int angle = 0;
        boolean accelerate = false;

        if (gui.isTeleportPressed()) {
            teleport();
        }

        if (gui.isRightPressed()) {
            --angle;
        }

        if (gui.isLeftPressed()) {
            ++angle;
        }

        if (gui.isUpPressed()) {
            accelerate = true;
        }

        getPhysics().move(accelerate, angle);

        if (gui.isShieldsPressed()) {
            shieldOn();
        }

        if (gui.isShotPressed()) {
            fire(game);
        }
    }
}
