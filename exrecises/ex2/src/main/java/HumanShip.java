import oop.ex2.GameGUI;

public class HumanShip extends SpaceShip {

    public void setShipImageNoShield() {
        setImage(GameGUI.SPACESHIP_IMAGE);
    }

    public void setShipImageShield() {
        setImage(GameGUI.SPACESHIP_IMAGE_SHIELD);
    }

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
