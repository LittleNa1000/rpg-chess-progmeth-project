package potion;

import base.BasePotion;
import base.BaseUnit;
import constant.PotionConstant;

public class HealingPotion extends BasePotion {
    private final int healingPoint;

    public HealingPotion() {
        super(PotionConstant.HEALING_POTION_NAME, PotionConstant.HEALING_POTION_IMAGE);
        this.healingPoint = PotionConstant.HEALING_POTION_ABLILITY;
    }

    public int getHealingPoint() {
        return healingPoint;
    }

    @Override
    public void consume(BaseUnit unit) {
        // TODO Auto-generated method stub

    }
}
