package potion;

import base.BasePotion;
import constant.PotionConstant;

public class ToxicPotion extends BasePotion {
    private final int toxicPower;

    public ToxicPotion() {
        super(PotionConstant.TOXIC_POTION_NAME);
        this.toxicPower = PotionConstant.TOXIC_POTION_ABLILITY;
    }

    @Override
    public void consume() {
        // TODO Auto-generated method stub

    }
}
