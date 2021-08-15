import oop.ex2.GameGUI;

/**
 * this class implement enemy ships abstruct and set enemy image
 */
public abstract class EnemyShip extends SpaceShip {
    /**
     * set enemy ship image without shield
     */
    public void setShipImageNoShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE);
    }

    /**
     * set enemy ship image with a shield
     */
    public void setShipImageShield() {
        setImage(GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD);
    }
}
