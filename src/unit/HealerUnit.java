package unit;

import base.BaseUnit;
import base.Buffable;
import constant.UnitConstant;

public class HealerUnit extends BaseUnit implements Buffable {
    private int healingPoint;

    public HealerUnit() {
        super(UnitConstant.HEALER_UNIT_NAME, UnitConstant.HEALER_UNIT_MAX_HP, 0);
        this.healingPoint = UnitConstant.HEALER_UNIT_HEALING_POINT;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        super.move();
    }

    @Override
    public void buffUnit() {
        // TODO Auto-generated method stub

    }
}