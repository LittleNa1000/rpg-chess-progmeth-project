package unit;

import base.Attackable;
import base.BaseUnit;
import constant.UnitConstant;

public class NormalUnit extends BaseUnit implements Attackable {

    public NormalUnit() {
        super(UnitConstant.NORMAL_UNIT_NAME, UnitConstant.NORMAL_UNIT_MAX_HP, UnitConstant.NORMAL_UNIT_POWER,
                UnitConstant.NORMAL_UNIT_IMAGE_URL);
    }

    @Override
    public void attackUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        if (!(unit instanceof FlyingUnit))
            unit.reduceHealth(this.getPower());
    }
}
