package base;

import util.StringUtil;

public abstract class BaseUnit implements Moveable {
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

    @Override
    public void move() {
        // TODO Auto-generated method stub

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

}
