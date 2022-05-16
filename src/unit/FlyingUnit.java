package unit;

import base.Attackable;
import base.BaseUnit;
import constant.UnitConstant;

public class FlyingUnit extends BaseUnit implements Attackable {
    public FlyingUnit() {
        super(UnitConstant.FLYING_UNIT_NAME, UnitConstant.FLYING_UNIT_MAX_HP, UnitConstant.FLYING_UNIT_POWER,
                UnitConstant.FLYING_UNIT_IMAGE_URL);
    }

    @Override
    public void attackUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.reduceHealth(this.getPower());
    }
}
