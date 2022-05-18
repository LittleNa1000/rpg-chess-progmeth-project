package constant;

import base.BasePotion;
import javafx.scene.image.Image;
import potion.BuffPotion;
import potion.HealingPotion;
import potion.ToxicPotion;
import util.StringUtil;

public class PotionConstant {
    static final public String HEALING_POTION_NAME = "HEALING POTION";
    static final public int HEALING_POTION_ABLILITY = 5;
    static final public Image HEALING_POTION_IMAGE = new Image(StringUtil.getImageUrl("potion/healing-potion.png"));

    static final public String TOXIC_POTION_NAME = "TOXIC POTION";
    static final public int TOXIC_POTION_ABLILITY = 3;
    static final public Image TOXIC_POTION_IMAGE = new Image(StringUtil.getImageUrl("potion/toxic-potion.png"));

    static final public String BUFF_POTION_NAME = "BUFF POTION";
    static final public Image BUFF_POTION_IMAGE = new Image(StringUtil.getImageUrl("potion/buff-potion.png"));

    static final public BasePotion[] ALL_POTIONS = { new HealingPotion(), new ToxicPotion(), new BuffPotion() };
}