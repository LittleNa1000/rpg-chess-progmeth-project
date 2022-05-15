package base;

public abstract class BaseUnit implements Moveable {
    private final int maxHealth;
    private String name;
    private int power;
    private int currentHealth;

    public BaseUnit(String name, int maxHealth, int power) {
        this.maxHealth = maxHealth;
        this.name = name;
        this.power = power;
        this.currentHealth = maxHealth;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }
}
