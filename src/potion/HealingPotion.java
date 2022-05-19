package potion;

import base.BasePotion;
import base.BaseUnit;
import constant.PotionConstant;
import util.AudioUtil;

public class HealingPotion extends BasePotion {
    private final int HEALING_POINT;

    public HealingPotion() {
        super(PotionConstant.HEALING_POTION_NAME, PotionConstant.HEALING_POTION_IMAGE,
                PotionConstant.HEALING_POTION_AGE, PotionConstant.HEALING_POTION_AGE);
        this.HEALING_POINT = PotionConstant.HEALING_POTION_ABLILITY;
    }

    public int getHealingPoint() {
        return HEALING_POINT;
    }

    @Override
    public void consume(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.setCurrentHealth(Math.min(unit.getMaxHealth(), unit.getCurrentHealth() + HEALING_POINT));
        AudioUtil.playSound("heal.wav", 0.5);
    }
}
