package potion;

import base.BasePotion;
import base.BaseUnit;
import constant.PotionConstant;
import util.AudioUtil;

public class HealingPotion extends BasePotion {
    private final int healingPoint;

    public HealingPotion() {
        super(PotionConstant.HEALING_POTION_NAME, PotionConstant.HEALING_POTION_IMAGE,
                PotionConstant.HEALING_POTION_AGE, PotionConstant.HEALING_POTION_AGE);
        this.healingPoint = PotionConstant.HEALING_POTION_ABLILITY;
    }

    public int getHealingPoint() {
        return healingPoint;
    }

    @Override
    public void consume(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.setCurrentHealth(Math.min(unit.getMaxHealth(), unit.getCurrentHealth() + healingPoint));
        AudioUtil.playSound("heal.wav", 0.5);
    }
}
