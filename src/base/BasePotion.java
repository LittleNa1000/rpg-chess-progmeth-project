package base;

public abstract class BasePotion {
    private String name;

    public BasePotion(String name) {
        this.name = name;
    }

    public abstract void consume();
}
