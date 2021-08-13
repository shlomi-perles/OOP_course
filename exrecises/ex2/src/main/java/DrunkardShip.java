import oop.ex2.GameGUI;
public class DrunkardShip extends SpaceShip{
    public void actionByShipType(SpaceWars game) {

    }

    public void setShipImageNoShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE);
    }

    public void setShipImageShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD);
    }
}
