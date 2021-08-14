import oop.ex2.GameGUI;
import java.util.Random;

public class DrunkardShip extends SpaceShip{
    public void actionByShipType(SpaceWars game) {
        Random rand = new Random();
        getPhysics().move(rand.nextBoolean(), 1);
    }

    public void setShipImageNoShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE);
    }

    public void setShipImageShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD);
    }
}
