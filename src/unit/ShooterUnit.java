package unit;

import base.Attackable;
import base.BaseUnit;
import constant.UnitConstant;

public class ShooterUnit extends BaseUnit implements Attackable {
    public ShooterUnit() {
        super(UnitConstant.SHOOTER_UNIT_NAME, UnitConstant.SHOOTER_UNIT_MAX_HP, UnitConstant.SHOOTER_UNIT_POWER);
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        super.move();
    }

    @Override
    public void attackUnit() {
        // TODO Auto-generated method stub

    }
}
