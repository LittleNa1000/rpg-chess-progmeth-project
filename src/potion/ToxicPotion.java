package potion;

import base.BasePotion;
import base.BaseUnit;
import constant.PotionConstant;
import util.AudioUtil;

public class ToxicPotion extends BasePotion {
    private final int TOXIC_POWER;

    public ToxicPotion() {
        super(PotionConstant.TOXIC_POTION_NAME, PotionConstant.TOXIC_POTION_IMAGE, PotionConstant.TOXIC_POTION_AGE,
                PotionConstant.TOXIC_POTION_AGE);
        this.TOXIC_POWER = PotionConstant.TOXIC_POTION_ABILITY;
    }

    public int getToxicPower() {
        return TOXIC_POWER;
    }

    @Override
    public void consume(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.reduceHealth(TOXIC_POWER);
        AudioUtil.playSound("poison.wav");
    }
}
