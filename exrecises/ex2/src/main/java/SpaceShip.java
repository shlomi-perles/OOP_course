import java.awt.Image;

import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 */
public abstract class SpaceShip {

    // Health constants
    final private static int HEALTH_INIT = 22;
    final private static int HEALTH_LOSS = 1;

    // Energy constants
    final private static int ENERGY_INIT = 210;
    final private static int SHIELD_ENERGY_BONUS = 18;
    final private static int NO_SHIELD_ENERGY_LOSS = 10;
    final private static int SHOT_ENERGY_PRICE = 19;
    final private static int TELEPORT_ENERGY_PRICE = 140;
    final private static int SHIELD_ENERGY_PRICE = 3;

    // Game rules constants
    final private static int COOL_DOWN_TIME = 7;
    final private static int DEAD = 0;

    // Spaceship params
    private SpaceShipPhysics physics;
    private int energy;
    private int health;
    private int maxEnergy;
    private int coolDownTimer;
    private boolean shieldActive;
    private Image shipImage;

    /**
     * SpaceShip constructor
     */
    SpaceShip() {
        reset();
    }


    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game The game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        if (coolDownTimer > 0) {
            --coolDownTimer;
        }

        if (shieldActive) {
            setShipImageNoShield();
            shieldActive = false;
        }
        actionByShipType(game);

        if (energy < maxEnergy) {
            ++energy;
        }
    }

    /**
     * This method implements the unique behavior for each spacecship
     *
     * @param game SpaceWars object contain game's data
     */
    public abstract void actionByShipType(SpaceWars game);


    /**
     * This method is called every time a collision with this ship occurs.
     */
    public void collidedWithAnotherShip() {
        if (shieldActive) {
            maxEnergy += SHIELD_ENERGY_BONUS;
            energy += SHIELD_ENERGY_BONUS;
        } else {
            maxEnergy = Math.max(maxEnergy - NO_SHIELD_ENERGY_LOSS, 0);
            energy = Math.min(energy, maxEnergy);
            health -= HEALTH_LOSS;
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        energy = ENERGY_INIT;
        health = HEALTH_INIT;
        maxEnergy = ENERGY_INIT;
        physics = new SpaceShipPhysics();
        coolDownTimer = 0;
        shieldActive = false;
        setShipImageNoShield();
    }

    /**
     * Checks if this ship is dead.
     *
     * @return True if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return health <= DEAD;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return The physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shieldActive) {
            maxEnergy -= Math.max(NO_SHIELD_ENERGY_LOSS, 0);
            energy = Math.min(energy, maxEnergy);
            health -= HEALTH_LOSS;
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return The image of this ship.
     */
    public Image getImage() {
        return shipImage;
    }

    public void setImage(Image image) {
        shipImage = image;
    }

    /**
     * set spaceship image without shield
     */
    public abstract void setShipImageNoShield();

    /**
     * set spaceship image with a shield
     */
    public abstract void setShipImageShield();

    /**
     * Attempts to fire a shot.
     *
     * @param game The game object.
     */
    public void fire(SpaceWars game) {
        if (coolDownTimer <= 0 && energy >= SHOT_ENERGY_PRICE) {
            game.addShot(physics);
            energy -= SHOT_ENERGY_PRICE;
            coolDownTimer = COOL_DOWN_TIME;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (energy >= SHIELD_ENERGY_PRICE) {
            shieldActive = true;
            energy -= SHIELD_ENERGY_PRICE;
            setShipImageShield();
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (energy >= TELEPORT_ENERGY_PRICE) {
            physics = new SpaceShipPhysics();
            energy -= TELEPORT_ENERGY_PRICE;
        }
    }
}
