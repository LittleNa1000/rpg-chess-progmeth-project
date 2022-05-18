package base;

import javafx.scene.image.Image;

public abstract class BasePotion {
    private String name;
    private Image image;

    public BasePotion(String name, Image image) {
        this.name = name;
        this.image = image;
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

    public abstract void consume(BaseUnit unit);
}
