package base;

import javafx.scene.image.Image;

public abstract class BasePotion {
    private String name;
    private Image image;
    private int age;

    public BasePotion(String name, Image image, int age) {
        setName(name);
        setImage(image);
        setAge(age);
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

    public abstract void consume(BaseUnit unit);
}
