package base;

import util.StringUtil;

public abstract class BaseUnit {
    private final int maxHealth;
    private String name;
    private int power;
    private int currentHealth;
    private int stunRoundLeft;
    private int venomRoundLeft;
    private String imageUrl;

    public BaseUnit(String name, int maxHealth, int power, String imageUrl) {
        this.maxHealth = maxHealth;
        this.name = name;
        this.power = power;
        this.currentHealth = maxHealth;
        this.stunRoundLeft = 0;
        this.venomRoundLeft = 0;
        this.imageUrl = imageUrl;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public String getImageUrl() {
        return StringUtil.getImageUrl(imageUrl);
    }

    public int getMaxHealth() {
        return maxHealth;
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

    public void setCurrentHealth(int currentHealth) {
        if (currentHealth < 0)
            currentHealth = 0;
        this.currentHealth = currentHealth;
    }

    public void reduceHealth(int damage) {
        setCurrentHealth(currentHealth - damage);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setStunRoundLeft(int stunRoundLeft) {
        this.stunRoundLeft = stunRoundLeft;
    }

    public void setVenomRoundLeft(int venomRoundLeft) {
        this.venomRoundLeft = venomRoundLeft;
    }
}
