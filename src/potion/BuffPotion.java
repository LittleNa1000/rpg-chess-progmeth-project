package potion;

import base.BasePotion;
import base.BaseUnit;
import constant.PotionConstant;

public class BuffPotion extends BasePotion {

    public BuffPotion() {
        super(PotionConstant.BUFF_POTION_NAME, PotionConstant.BUFF_POTION_IMAGE);
    }

    @Override
    public void consume(BaseUnit unit) {
        // TODO Auto-generated method stub

    }
}
