package unit;

import base.Attackable;
import base.BaseUnit;
import constant.UnitConstant;
import logic.SquareOwnerState;

public class ShooterUnit extends BaseUnit implements Attackable {
    public ShooterUnit(SquareOwnerState state) {
        super(UnitConstant.SHOOTER_UNIT_NAME, UnitConstant.SHOOTER_UNIT_MAX_HP, UnitConstant.SHOOTER_UNIT_POWER,
                state == SquareOwnerState.PLAYER1 ? UnitConstant.SHOOTER_UNIT_IMAGE_URL_1
                        : UnitConstant.SHOOTER_UNIT_IMAGE_URL_2);
    }

    @Override
    public void attackUnit(BaseUnit unit) {
        // TODO Auto-generated method stub

        unit.reduceHealth(this.getPower());
    }
}
