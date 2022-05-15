package potion;

import base.BasePotion;
import constant.PotionConstant;

public class HealingPotion extends BasePotion {
    private final int healingPoint;

    public HealingPotion() {
        super(PotionConstant.HEALING_POTION_NAME);
        this.healingPoint = PotionConstant.HEALING_POTION_ABLILITY;
    }

    @Override
    public void consume() {
        // TODO Auto-generated method stub

    }
}
