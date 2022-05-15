package base;

public abstract class BaseUnit implements Moveable {
    private final int maxHealth;
    private String name;
    private int power;
    private int currentHealth;
    private int stunRoundLeft;
    private int venomRoundLeft;

    public BaseUnit(String name, int maxHealth, int power) {
        this.maxHealth = maxHealth;
        this.name = name;
        this.power = power;
        this.currentHealth = maxHealth;
        this.stunRoundLeft = 0;
        this.venomRoundLeft = 0;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }
}
