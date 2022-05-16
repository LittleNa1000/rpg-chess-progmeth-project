package unit;

import base.BaseUnit;
import base.Debuffable;
import constant.UnitConstant;

public class FreezerUnit extends BaseUnit implements Debuffable {
    private int stunRound;

    public FreezerUnit() {
        super(UnitConstant.FREEZER_UNIT_NAME, UnitConstant.FREEZER_UNIT_MAX_HP, 0, UnitConstant.FREEZER_UNIT_IMAGE_URL);
        this.stunRound = UnitConstant.FREEZER_UNIT_STUN_ROUND;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        super.move();
    }

    @Override
    public void debuffUnit() {
        // TODO Auto-generated method stub

    }
}
