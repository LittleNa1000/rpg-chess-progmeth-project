package constant;

public class UnitConstant {
        static final public String NORMAL_UNIT_NAME = "NORMAL UNIT";
        static final public int NORMAL_UNIT_MAX_HP = 30;
        static final public int NORMAL_UNIT_POWER = 3;
        static final public String NORMAL_UNIT_IMAGE_URL_1 = "normal-unit-1.png";
        static final public String NORMAL_UNIT_IMAGE_URL_2 = "normal-unit-2.png";
        static final public int[][] NORMAL_UNIT_MOVE = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
                        { 1, -1 },
                        { 1, 0 }, { 1, 1 } };
        static final public int[][] NORMAL_UNIT_ATTACK = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

        static final public String FLYING_UNIT_NAME = "FLYING UNIT";
        static final public int FLYING_UNIT_MAX_HP = 25;
        static final public int FLYING_UNIT_POWER = 3;
        static final public String FLYING_UNIT_IMAGE_URL_1 = "flying-unit.jpg";
        static final public String FLYING_UNIT_IMAGE_URL_2 = "flying-unit.jpg";
        static final public int[][] FLYING_UNIT_MOVE = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
        static final public int[][] FLYING_UNIT_ATTACK = NORMAL_UNIT_ATTACK;

        static final public String SHOOTER_UNIT_NAME = "SHOOTER UNIT";
        static final public int SHOOTER_UNIT_MAX_HP = 35;
        static final public int SHOOTER_UNIT_POWER = 3;
        static final public String SHOOTER_UNIT_IMAGE_URL_1 = "shooter-unit.png";
        static final public String SHOOTER_UNIT_IMAGE_URL_2 = "shooter-unit.png";
        static final public int[][] SHOOTER_UNIT_MOVE = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
        static final public int[][] SHOOTER_UNIT_ATTACK = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
                        { 1, -1 }, { 1, 0 }, { 1, 1 }, { -2, -2 }, { -2, 0 }, { -2, 2 }, { 0, -2 }, { 0, 2 }, { 2, -2 },
                        { 2, 0 },
                        { 2, 2 } };

        static final public String VENOM_UNIT_NAME = "VENOM UNIT";
        static final public int VENOM_UNIT_MAX_HP = 35;
        static final public int VENOM_UNIT_POISON_POWER = 1;
        static final public int VENOM_UNIT_POISON_ROUND = 3;
        static final public String VENOM_UNIT_IMAGE_URL_1 = "venom-unit.jpg";
        static final public String VENOM_UNIT_IMAGE_URL_2 = "venom-unit.jpg";
        static final public int[][] VENOM_UNIT_MOVE = FLYING_UNIT_MOVE;
        static final public int[][] VENOM_UNIT_ATTACK = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

        static final public String HEALER_UNIT_NAME = "HEALER UNIT";
        static final public int HEALER_UNIT_MAX_HP = 35;
        static final public int HEALER_UNIT_HEALING_POINT = 3;
        static final public String HEALER_UNIT_IMAGE_URL_1 = "healer-unit.png";
        static final public String HEALER_UNIT_IMAGE_URL_2 = "healer-unit.png";
        static final public int[][] HEALER_UNIT_MOVE = NORMAL_UNIT_MOVE;
        static final public int[][] HEALER_UNIT_HEAL_RANGE = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
                        { 1, -1 },
                        { 1, 0 }, { 1, 1 } };

        static final public String FREEZER_UNIT_NAME = "FREEZER UNIT";
        static final public int FREEZER_UNIT_MAX_HP = 35;
        static final public int FREEZER_UNIT_STUN_ROUND = 3;
        static final public String FREEZER_UNIT_IMAGE_URL_1 = "freezer-unit.jpeg";
        static final public String FREEZER_UNIT_IMAGE_URL_2 = "freezer-unit.jpeg";
        static final public int[][] FREEZER_UNIT_MOVE = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
                        { 1, -1 },
                        { 1, 0 }, { 1, 1 }, { -2, -2 }, { -2, -1 }, { -2, 0 }, { -2, 1 }, { -2, 2 }, { -1, -2 },
                        { -1, 2 },
                        { 0, -2 }, { 0, 2 }, { 1, -2 }, { 1, 2 }, { 2, -2 }, { 2, -1 }, { 2, 0 }, { 2, 1 }, { 2, 2 } };
        static final public int[][] FREEZER_UNIT_FREEZE_RANGE = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
}
