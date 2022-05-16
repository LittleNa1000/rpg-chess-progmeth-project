package unit;

import base.Attackable;
import base.BaseUnit;
import constant.UnitConstant;

public class ShooterUnit extends BaseUnit implements Attackable {
    public ShooterUnit() {
        super(UnitConstant.SHOOTER_UNIT_NAME, UnitConstant.SHOOTER_UNIT_MAX_HP, UnitConstant.SHOOTER_UNIT_POWER,
                UnitConstant.SHOOTER_UNIT_IMAGE_URL);
    }

    @Override
    public void attackUnit(BaseUnit unit) {
        // TODO Auto-generated method stub

        unit.reduceHealth(this.getPower());
    }
}
