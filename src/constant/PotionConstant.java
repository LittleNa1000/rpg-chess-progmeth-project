package constant;

import javafx.scene.image.Image;
import util.StringUtil;

public class PotionConstant {
    static final public int ROUND_PER_POTION = 3;
    static final public int MAX_POTION_ON_BOARD = 3;
    static final public int POTION_TYPES = 3;

    static final public String HEALING_POTION_NAME = "HEALING POTION";
    static final public int HEALING_POTION_AGE = 7;
    static final public int HEALING_POTION_ABLILITY = 5;
    static final public Image HEALING_POTION_IMAGE = new Image(StringUtil.getImageUrl("potions/healing-potion.png"));

    static final public String TOXIC_POTION_NAME = "TOXIC POTION";
    static final public int TOXIC_POTION_AGE = 9;
    static final public int TOXIC_POTION_ABLILITY = 3;
    static final public Image TOXIC_POTION_IMAGE = new Image(StringUtil.getImageUrl("potions/toxic-potion.png"));

    static final public String BUFF_POTION_NAME = "BUFF POTION";
    static final public int BUFF_POTION_AGE = 4;
    static final public Image BUFF_POTION_IMAGE = new Image(StringUtil.getImageUrl("potions/buff-potion.png"));

    static final public int BUFF_POTION_POWER = 3;
    static final public int BUFF_POTION_HEALING_POINT = 3;
    static final public int BUFF_POTION_STUN_ROUND = 2;
    static final public int BUFF_POTION_POISON_ROUND = 2;

    static final public String POTION_AGE_COLOR = "#6B885E";
}
