package unit;

import base.Attackable;
import base.BaseUnit;
import constant.UnitConstant;
import logic.SquareOwnerState;

public class NormalUnit extends BaseUnit implements Attackable {

    public NormalUnit(SquareOwnerState state) {
        super(UnitConstant.NORMAL_UNIT_NAME, UnitConstant.NORMAL_UNIT_MAX_HP, UnitConstant.NORMAL_UNIT_POWER,
                state == SquareOwnerState.PLAYER1 ? UnitConstant.NORMAL_UNIT_IMAGE_URL_1
                        : UnitConstant.NORMAL_UNIT_IMAGE_URL_2);
    }

    @Override
    public void attackUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        if (!(unit instanceof FlyingUnit))
            unit.reduceHealth(this.getPower());
    }
}
