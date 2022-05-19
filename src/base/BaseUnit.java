package base;

import javafx.scene.image.Image;

public abstract class BaseUnit {
    private final int MAX_HEALTH;
    private String name;
    private int power;
    private int currentHealth;
    private int stunRoundLeft;
    private int venomRoundLeft;
    private Image image;
    private Image movePattern;
    private Image atkPattern;

    public BaseUnit(String name, int maxHealth, int power, Image image, Image movePattern, Image atkPattern) {
        this.MAX_HEALTH = maxHealth;
        setName(name);
        setPower(power);
        setCurrentHealth(maxHealth);
        setStunRoundLeft(0);
        setVenomRoundLeft(0);
        setImage(image);
        setMovePattern(movePattern);
        setAtkPattern(atkPattern);
    }

    public abstract void upgrade();

    public int getCurrentHealth() {
        return currentHealth;
    }

    public Image getImage() {
        return image;
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getStunRoundLeft() {
        return stunRoundLeft;
    }

    public int getVenomRoundLeft() {
        return venomRoundLeft;
    }

    public Image getMovePattern() {
        return this.movePattern;
    }

    public void setMovePattern(Image movePattern) {
        this.movePattern = movePattern;
    }

    public Image getAtkPattern() {
        return this.atkPattern;
    }

    public void setAtkPattern(Image atkPattern) {
        this.atkPattern = atkPattern;
    }

    public void setCurrentHealth(int currentHealth) {
        if (currentHealth < 0)
            currentHealth = 0;
        this.currentHealth = currentHealth;
    }

    public void reduceHealth(int damage) {
        setCurrentHealth(currentHealth - damage);
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setStunRoundLeft(int stunRoundLeft) {
        if (stunRoundLeft < 0)
            stunRoundLeft = 0;
        this.stunRoundLeft = stunRoundLeft;
    }

    public void setVenomRoundLeft(int venomRoundLeft) {
        if (venomRoundLeft < 0)
            venomRoundLeft = 0;
        this.venomRoundLeft = venomRoundLeft;
    }
}
