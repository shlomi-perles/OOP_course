import java.awt.Image;

import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 */
public class SpaceShip {

    // Health constants
    final private static int HEALTH_INIT = 22;
    final private static int HEALTH_LOSS = 1;

    // Energy constants
    final private static int ENERGY_INIT = 210;
    final private static int BASH_ENERGY_LOSS = 18;
    final private static int ENERGY_LOSS = 18;

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game The game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        // your code goes here
    }

    /**
     * This method is called every time a collision with this ship occurs.
     */
    public void collidedWithAnotherShip() {
        // your code goes here
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        // your code goes here
    }

    /**
     * Checks if this ship is dead.
     *
     * @return True if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        // your code goes here
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return The physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        // your code goes here
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        // your code goes here
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return The image of this ship.
     */
    public Image getImage() {
        // your code goes here
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game The game object.
     */
    public void fire(SpaceWars game) {
        // your code goes here
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        // your code goes here
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        // your code goes here
    }
}
