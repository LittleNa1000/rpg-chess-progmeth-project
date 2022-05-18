package base;

import javafx.scene.image.Image;

public abstract class BasePotion {
    private String name;
    private Image image;
    private int age;
    private int maxAge;

    public BasePotion(String name, Image image, int age, int maxAge) {
        setName(name);
        setImage(image);
        setAge(age);
        setMaxAge(maxAge);
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0)
            age = 0;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public abstract void consume(BaseUnit unit);
}
