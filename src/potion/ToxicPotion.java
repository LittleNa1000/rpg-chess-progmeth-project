package potion;

import base.BasePotion;
import base.BaseUnit;
import constant.PotionConstant;

public class ToxicPotion extends BasePotion {
    private final int toxicPower;

    public ToxicPotion() {
        super(PotionConstant.TOXIC_POTION_NAME, PotionConstant.TOXIC_POTION_IMAGE, PotionConstant.TOXIC_POTION_AGE);
        this.toxicPower = PotionConstant.TOXIC_POTION_ABLILITY;
    }

    public int getToxicPower() {
        return toxicPower;
    }

    @Override
    public void consume(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.reduceHealth(toxicPower);
    }
}
