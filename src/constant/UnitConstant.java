package constant;

import javafx.scene.image.Image;
import util.StringUtil;

public class UnitConstant {
        static final public String NORMAL_UNIT_NAME = "NORMAL UNIT";
        static final public int NORMAL_UNIT_MAX_HP = 15;
        static final public int NORMAL_UNIT_POWER = 3;
        static final public Image NORMAL_UNIT_IMAGE_1 = new Image(StringUtil.getImageUrl("normal-unit-1.png"));
        static final public Image NORMAL_UNIT_IMAGE_2 = new Image(StringUtil.getImageUrl("normal-unit-2.png"));
        static final public Image NORMAL_UNIT_IMAGE_MOVE = new Image(StringUtil.getImageUrl("move-1.jpg"), 125, 125,
                        false, false);
        static final public Image NORMAL_UNIT_IMAGE_ATTACK = new Image(StringUtil.getImageUrl("attack-2.jpg"), 125, 125,
                        false, false);
        static final public int[][] NORMAL_UNIT_MOVE = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
                        { 1, -1 },
                        { 1, 0 }, { 1, 1 } };
        static final public int[][] NORMAL_UNIT_ATTACK = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

        static final public String FLYING_UNIT_NAME = "FLYING UNIT";
        static final public int FLYING_UNIT_MAX_HP = 13;
        static final public int FLYING_UNIT_POWER = 3;
        static final public Image FLYING_UNIT_IMAGE_1 = new Image(StringUtil.getImageUrl("flying-unit.jpg"));
        static final public Image FLYING_UNIT_IMAGE_2 = new Image(StringUtil.getImageUrl("flying-unit.jpg"));
        static final public Image FLYING_UNIT_IMAGE_MOVE = new Image(StringUtil.getImageUrl("move-2.jpg"), 125, 125,
                        false, false);
        static final public Image FLYING_UNIT_IMAGE_ATTACK = new Image(StringUtil.getImageUrl("attack-2.jpg"), 125, 125,
                        false, false);
        static final public int[][] FLYING_UNIT_MOVE = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
        static final public int[][] FLYING_UNIT_ATTACK = NORMAL_UNIT_ATTACK;

        static final public String SHOOTER_UNIT_NAME = "SHOOTER UNIT";
        static final public int SHOOTER_UNIT_MAX_HP = 18;
        static final public int SHOOTER_UNIT_POWER = 4;
        static final public Image SHOOTER_UNIT_IMAGE_1 = new Image(StringUtil.getImageUrl("shooter-unit.png"));
        static final public Image SHOOTER_UNIT_IMAGE_2 = new Image(StringUtil.getImageUrl("shooter-unit.png"));
        static final public Image SHOOTER_UNIT_IMAGE_MOVE = new Image(StringUtil.getImageUrl("move-4.jpg"), 125, 125,
                        false, false);
        static final public Image SHOOTER_UNIT_IMAGE_ATTACK = new Image(StringUtil.getImageUrl("attack-3.jpg"), 125,
                        125, false, false);
        static final public int[][] SHOOTER_UNIT_MOVE = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
        static final public int[][] SHOOTER_UNIT_ATTACK = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
                        { 1, -1 }, { 1, 0 }, { 1, 1 }, { -2, -2 }, { -2, 0 }, { -2, 2 }, { 0, -2 }, { 0, 2 }, { 2, -2 },
                        { 2, 0 },
                        { 2, 2 } };

        static final public String VENOM_UNIT_NAME = "VENOM UNIT";
        static final public int VENOM_UNIT_MAX_HP = 18;
        static final public int VENOM_UNIT_POISON_POWER = 3;
        static final public int VENOM_UNIT_POISON_ROUND = 3;
        static final public Image VENOM_UNIT_IMAGE_1 = new Image(StringUtil.getImageUrl("venom-unit.jpg"));
        static final public Image VENOM_UNIT_IMAGE_2 = new Image(StringUtil.getImageUrl("venom-unit.jpg"));
        static final public Image VENOM_UNIT_IMAGE_MOVE = new Image(StringUtil.getImageUrl("move-2.jpg"), 125, 125,
                        false, false);
        static final public Image VENOM_UNIT_IMAGE_ATTACK = new Image(StringUtil.getImageUrl("attack-4.jpg"), 125, 125,
                        false, false);
        static final public int[][] VENOM_UNIT_MOVE = FLYING_UNIT_MOVE;
        static final public int[][] VENOM_UNIT_ATTACK = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

        static final public String HEALER_UNIT_NAME = "HEALER UNIT";
        static final public int HEALER_UNIT_MAX_HP = 18;
        static final public int HEALER_UNIT_HEALING_POINT = 5;
        static final public Image HEALER_UNIT_IMAGE_1 = new Image(StringUtil.getImageUrl("healer-unit.png"));
        static final public Image HEALER_UNIT_IMAGE_2 = new Image(StringUtil.getImageUrl("healer-unit.png"));
        static final public Image HEALER_UNIT_IMAGE_MOVE = new Image(StringUtil.getImageUrl("move-1.jpg"), 125, 125,
                        false, false);
        static final public Image HEALER_UNIT_IMAGE_ATTACK = new Image(StringUtil.getImageUrl("attack-1.jpg"), 125, 125,
                        false, false);
        static final public int[][] HEALER_UNIT_MOVE = NORMAL_UNIT_MOVE;
        static final public int[][] HEALER_UNIT_HEAL_RANGE = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
                        { 1, -1 },
                        { 1, 0 }, { 1, 1 } };

        static final public String FREEZER_UNIT_NAME = "FREEZER UNIT";
        static final public int FREEZER_UNIT_MAX_HP = 18;
        static final public int FREEZER_UNIT_STUN_ROUND = 3;
        static final public Image FREEZER_UNIT_IMAGE_1 = new Image(StringUtil.getImageUrl("freezer-unit.jpeg"));
        static final public Image FREEZER_UNIT_IMAGE_2 = new Image(StringUtil.getImageUrl("freezer-unit.jpeg"));
        static final public Image FREEZER_UNIT_IMAGE_MOVE = new Image(StringUtil.getImageUrl("move-3.jpg"), 125, 125,
                        false, false);
        static final public Image FREEZER_UNIT_IMAGE_ATTACK = new Image(StringUtil.getImageUrl("attack-4.jpg"), 125,
                        125, false, false);
        static final public int[][] FREEZER_UNIT_MOVE = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
                        { 1, -1 },
                        { 1, 0 }, { 1, 1 }, { -2, -2 }, { -2, -1 }, { -2, 0 }, { -2, 1 }, { -2, 2 }, { -1, -2 },
                        { -1, 2 },
                        { 0, -2 }, { 0, 2 }, { 1, -2 }, { 1, 2 }, { 2, -2 }, { 2, -1 }, { 2, 0 }, { 2, 1 }, { 2, 2 } };
        static final public int[][] FREEZER_UNIT_FREEZE_RANGE = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
}
